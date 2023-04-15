package prev23.phase.imclin;

import java.util.*;

import prev23.common.report.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

import prev23.common.report.*;
import java.io.*;
import java.util.*;

/**
 * Statement canonizer.
 */
public class StmtCanonizer implements ImcVisitor<Vector<ImcStmt>, Object> {
    private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

    public StmtCanonizer() {}

	public Vector<ImcStmt> visit(ImcCJUMP cjump, Object visArg) {
        try {
            Vector<ImcStmt> imcStmts = new Vector<ImcStmt>();
		    imcStmts.add(new ImcCJUMP(cjump.cond.accept(new ExprCanonizer(), imcStmts), cjump.posLabel, cjump.negLabel));
		    
            return imcStmts;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public Vector<ImcStmt> visit(ImcESTMT eStmt, Object visArg) {
        try {
            Vector<ImcStmt> imcStmts = new Vector<ImcStmt>();
            imcStmts.add(new ImcESTMT(eStmt.expr.accept(new ExprCanonizer(), imcStmts)));

            return imcStmts;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public Vector<ImcStmt> visit(ImcJUMP jump, Object visArg) {
        try {
            return new Vector<ImcStmt>(List.of(jump));
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public Vector<ImcStmt> visit(ImcLABEL label, Object visArg) {
        try {
            return new Vector<ImcStmt>(List.of(label));
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public Vector<ImcStmt> visit(ImcMOVE move, Object visArg) {
        try {
            Vector<ImcStmt> imcStmts = new Vector<ImcStmt>();
		    ImcTEMP imcTEMPSrc = new ImcTEMP(new MemTemp());

			//In my implementation ImcMOVE can not be called on any dst other than ImcMEM
            // if (move.dst instanceof ImcMEM) {
                ImcMEM imcMEMmoveDst=(ImcMEM)move.dst;
                ImcTEMP imcTEMPDst = new ImcTEMP(new MemTemp());
                imcStmts.add(new ImcMOVE(imcTEMPDst, imcMEMmoveDst.addr.accept(new ExprCanonizer(), imcStmts)));
                imcStmts.add(new ImcMOVE(imcTEMPSrc, move.src.accept(new ExprCanonizer(), imcStmts)));
                imcStmts.add(new ImcMOVE(new ImcMEM(imcTEMPDst), imcTEMPSrc));
            // }
			// else if(move.dst instanceof ImcTEMP) {}

            return imcStmts;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

	public Vector<ImcStmt> visit(ImcSTMTS stmts, Object visArg) {
        try {
        	Vector<ImcStmt> imcStmts = new Vector<ImcStmt>();

		    for (ImcStmt s : stmts.stmts) {
			    imcStmts.addAll(s.accept(this, null));
		    }

		    return imcStmts;
        }
		catch(Exception ex) {
			throw new Report.Error(getStackTrace(ex));
		}
	}

}
