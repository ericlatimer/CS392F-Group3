implies(body1, head1).
body(body1, atom1).
body(body1, atom2).
head(head1, atom3).
atom(atom1, hasParent, var1, var2).
atom(atom2, hasBrother, var2, var3).
atom(atom3, hasUncle, var1, var3).
variables(var1, x1).
variables(var2, x2).
variables(var3, x3).

%x1 hasParent x2 and x2 hasBrother x3, therefore x1 hasUncle x3.  