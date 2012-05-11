%UML To Java

%UML Tables

%FEATURES
%operation(ID,Name,Type,PID,OwnerScope,Visibility,Ownerid)
operation(1,add,int,1,static,private,1).
operation(2,speak,void,1, ,public,1).
operation(3, doSomething,    void,   2,       , public,  2).
operation(4, multiplyFields, double, 4,       , private, 4).

%attribute(ID,Name,Type,PID,OwnerScope,Visibility,Changeability,Ownerid)
attribute(1,name,String,1, ,public,true,1).
attribute(2,count,int,1,static,public,true,1).
attribute(3, someAttribute, double, 2, static, public, true, 2).
attribute(4, someVector,    Vector, 3,       , public, true, 3).
attribute(5, dataField,     double, 4,       , public, true, 4).
attribute(5, dataField2,    double, 4,       ,private, true, 4).


%parameter(ID,Name,Type,Ownerid)
parameter(1,foo,int,1).
parameter(2,bar,int,1).
parameter(3,words,String,2).
parameter(4, inputArray,  ArrayList, 3).
parameter(5, dataField,   double,    4).
parameter(6, dataField2,  double,    4).

%CLASSIFIERS
%class(ID,Name,PID,isAbstract)
class(1, Class1, public,  1, true).
class(2, Class2, public,  1, false).
class(3, Class3, private, 1, false).
class(4, Class4, public,  1, false).


%NAMESPACES
%namespace(ID,Name)
namespace(1,util).

%package(ID,Name,NSID)
package(1, java, 1).