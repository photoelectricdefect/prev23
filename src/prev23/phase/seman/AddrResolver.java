package prev23.phase.seman;

import prev23.common.report.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;

import prev23.data.typ.*;

import java.io.*;
import java.util.*;

public class AddrResolver extends AstFullVisitor<Object, Object> {
	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

	private boolean compareSemType(SemType semType,String className) {
		return semType.actualType().getClass().getSimpleName().equals(className);
	}

	// GENERAL PURPOSE


	// DECLARATIONS


	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		
        try {
			Boolean isAddr=SemAn.isAddr.get(arrExpr.arr);

            if(isAddr!=null && isAddr) {
                SemAn.isAddr.put(arrExpr,true);
            }
		}
		catch(Exception ex) {
			throw new Report.Error(arrExpr,getStackTrace(ex));
		}

        return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		try {
            AstNameDecl astNameDecl = SemAn.declaredAt.get(nameExpr);
    
            if (astNameDecl instanceof AstVarDecl 
            || astNameDecl instanceof AstParDecl) {
                SemAn.isAddr.put(nameExpr,true);
            }
		}
		catch(Exception ex) {
			throw new Report.Error(nameExpr,getStackTrace(ex));
		}

        return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);

        try {
			Boolean isAddr=SemAn.isAddr.get(recExpr.rec);

            if(isAddr!=null && isAddr) {
                SemAn.isAddr.put(recExpr,true);
            }
		}
		catch(Exception ex) {
			throw new Report.Error(recExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		
        try {
			SemType semType=SemAn.ofType.get(sfxExpr.expr).actualType();

            if(compareSemType(semType,"SemPtr")) {
                SemAn.isAddr.put(sfxExpr,true);
            }
		}
		catch(Exception ex) {
			throw new Report.Error(sfxExpr,getStackTrace(ex));
		}
        
        return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		
		try {
			Boolean isAddr=SemAn.isAddr.get(assignStmt.dst);

			if(isAddr == null || !isAddr) {
				throw new Exception("Can not assign to non lvalue dst");
			}
		}
		catch(Exception ex) {
			throw new Report.Error(assignStmt,getStackTrace(ex));
		}
		
		return null;
	}

	// TYPES

}