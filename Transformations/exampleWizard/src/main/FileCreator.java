package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

public class FileCreator {

	private String projectName;
	private String templatePath;
	private IFolder templateFolder;
	
	private IProject project;

	public static IPackageFragment modelPack;
	public static IPackageFragment mainPack;
	public static String endOfFile = "/// end of a file ///";
	public static String genName = null;

	public FileCreator(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = br.readLine();
			if (line.startsWith("% transformation") ||
				line.startsWith("%transformation")) {
				projectName = line.substring(line.indexOf(":") + 1).trim();
			}
		} catch (FileNotFoundException e) {
			System.out.println("!! FileCreator e1: !!" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("!! FileCreator e2: !!" + e);
			e.printStackTrace();
		}
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    project = root.getProject(projectName);
		try {
			project.create(null);
			project.open(null);

			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = JavaCore.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);

			IJavaProject javaProject = JavaCore.create(project);

			IFolder binFolder = project.getFolder("bin");
			binFolder.create(false, true, null);
			javaProject.setOutputLocation(binFolder.getFullPath(), null);

			Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
			entries.addAll(Arrays.asList(javaProject.getRawClasspath()));
			IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
			LibraryLocation[] locations = JavaRuntime
					.getLibraryLocations(vmInstall);
			for (LibraryLocation element : locations) {
				entries.add(JavaCore.newLibraryEntry(
						element.getSystemLibraryPath(), null, null));
			}
			javaProject.setRawClasspath(
					entries.toArray(new IClasspathEntry[entries.size()]), null);

			IFolder sourceFolder = project.getFolder("src");
			sourceFolder.create(false, true, null);
			IClasspathEntry entryToRemove = JavaCore.newSourceEntry(javaProject
					.getPath());

			IPackageFragmentRoot proot = javaProject
					.getPackageFragmentRoot(sourceFolder);
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			for (int i = 0; i < oldEntries.length; i++) {
				if (oldEntries[i].equals(entryToRemove)) {
					oldEntries[i] = JavaCore.newSourceEntry(sourceFolder
							.getFullPath());
				}
			}

			templateFolder = project.getFolder("templates");
			templateFolder.create(false, true, null);

			IFile file = project
					.getFile("templates/" + projectName + ".txtjet");
			genName = projectName.substring(0, 1).toUpperCase()
					+ projectName.substring(1) + "Generator";
			templatePath = file.getLocation().toOSString();
			javaProject.setRawClasspath(oldEntries, null);

			modelPack = javaProject.getPackageFragmentRoot(sourceFolder)
					.createPackageFragment("model", false, null);
			mainPack = javaProject.getPackageFragmentRoot(sourceFolder)
					.createPackageFragment("main", false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> createFile(IPackageFragment pack, String fileContent) {
		List<String> classNames = new ArrayList<String>();
		String className = null;
		
		if (fileContent.contains(endOfFile)) {
			String[] data = fileContent.split(endOfFile);
			for (int i = 0; i < data.length; i++) {
				className = createSingleFile(pack, data[i]);
				if (!className.isEmpty()) {
					classNames.add(className);
				}
			}
		} else {
			classNames.add(createSingleFile(pack, fileContent));
		}
		return classNames;
	}

	public void createTemplateFile(List<String> classNames) {
		File file = new File(templatePath);
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write("<%@jet package = \"generators\" imports = \"java.util.* ");
			for(int i = 0; i < classNames.size(); i++){
				output.write(modelPack.getElementName() + "." + classNames.get(i) + " ");
			}
			output.write("\"");
			output.write("class = \"" + genName + "\"%>");
			
			//Eric adding stuff to parse argument
			output.write("\n<% List list = (List)argument;\n");
			
			for(int i = 0; i < classNames.size(); i++){
				String clsName 		= classNames.get(i);
				String clsListName 	= clsName.substring(0,1).toLowerCase()+clsName.substring(1)+"s";
				output.write("List<"+clsName+"> "+clsListName+" = (List<"+clsName+">)list.get("+i+");\n");
			}			
			output.write("%>\n");
   
			output.close();
			templateFolder.refreshLocal(1, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String createSingleFile(IPackageFragment pack, String fileContent) {
		if (!fileContent.contains("public class"))
			return "";
		StringBuffer buffer = new StringBuffer();
		//TODO FIX
		buffer.append("package " + pack.getElementName() + ";\n");
		buffer.append("\n");
		buffer.append(fileContent);
		String className = fileContent.substring(fileContent
				.indexOf("public class"));
		
		className = className.substring(0, className.indexOf("{"));
		className = className.substring("public class ".length());
		String cuName = className + ".java";
		try {
			pack.createCompilationUnit(cuName, buffer.toString(), true, null);
		} catch (JavaModelException e) {
			System.out.println("!! FileCreator::createSingleFile() e1 !! " + e);
			e.printStackTrace();
		}
		return className;
	}
}
