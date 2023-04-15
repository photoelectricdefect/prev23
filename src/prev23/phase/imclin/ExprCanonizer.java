package prev23.phase.imclin;

import java.util.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

import prev23.common.report.*;
import java.io.*;
import java.util.*;

/**
 * Expression canonizer.
 */
public class ExprCanonizer implements ImcVisitor<ImcExpr, Vector<ImcStmt>> {
    private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}
	
	public ExprCanonizer() {}

    public ImcExpr visit(ImcBINOP binOp, Vector<ImcStmt> visArg) {
        try {
			ImcTEMP imcTEMPFst=new ImcTEMP(new MemTemp());
			ImcTEMP imcTEMPSnd=new ImcTEMP(new MemTemp());
			ImcMOVE imcMOVEFst=new ImcMOVE(imcTEMPFst,binOp.fstExpr.accept(this,visArg));
			ImcMOVE imcMOVESnd=new ImcMOVE(imcTEMPSnd,binOp.sndExpr.accept(this,visArg));
			visArg.add(imcMOVEFst);
			visArg.add(imcMOVESnd);

			return new ImcBINOP(binOp.oper,imcTEMPFst,imcTEMPSnd);   
		}
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcCALL call, Vector<ImcStmt> visArg) {
        try {
			Vector<ImcExpr> imcExprArgs=new Vector<ImcExpr>();

			for(ImcExpr imcExpr:call.args) {
				ImcTEMP imcTEMP=new ImcTEMP(new MemTemp());
				ImcMOVE imcMOVE=new ImcMOVE(imcTEMP,imcExpr.accept(this,visArg));
				visArg.add(imcMOVE);
				imcExprArgs.add(imcTEMP);
			}

			return new ImcCALL(call.label,call.offs,imcExprArgs);
		}
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcNAME name, Vector<ImcStmt> visArg) {
        try {
            return name;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcCONST constant, Vector<ImcStmt> visArg) {
        try {
            return constant;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcMEM mem, Vector<ImcStmt> visArg) {
        try {
			return new ImcMEM(mem.addr.accept(this,visArg));
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcTEMP temp, Vector<ImcStmt> visArg) {
        try {
        	return temp;
		}
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public ImcExpr visit(ImcUNOP unOp, Vector<ImcStmt> visArg) {
        try {
			return new ImcUNOP(unOp.oper,unOp.subExpr.accept(this, visArg));
		}
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}
}
