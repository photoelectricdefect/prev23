package prev23.phase.imclin;

import java.util.*;

import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.visitor.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.lin.*;
import prev23.phase.imcgen.*;
import prev23.phase.memory.*;

import prev23.common.report.*;
import java.io.*;
import java.util.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {
	// GENERAL PURPOSE

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

    public ChunkGenerator() {}

	// DECLARATIONS

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);

        try {
            MemFrame memFrame=Memory.frames.get(funDecl);
			Vector<ImcStmt> imcStmts = new Vector<ImcStmt>();
			Vector<ImcStmt> imcStmtsLinearized = new Vector<ImcStmt>();

            MemLabel memLabelEntry=new MemLabel();
            MemLabel memLabelExit=new MemLabel();
			imcStmts.add(new ImcLABEL(memLabelEntry));

			if(funDecl.stmt != null) {
				ImcStmt imcStmtFn = ImcGen.stmtImc.get(funDecl.stmt);
				imcStmts.addAll(imcStmtFn.accept(new StmtCanonizer(), null));
				ImcStmt imcStmtFinal=imcStmts.get(imcStmts.size()-1);
				ImcMOVE imcMOVE=null;

				if(imcStmtFinal instanceof ImcESTMT) {
					ImcESTMT imcESTMTFinal=(ImcESTMT)imcStmtFinal;
					imcMOVE=new ImcMOVE(new ImcTEMP(memFrame.RV),imcESTMTFinal.expr);
				}
				else {
					imcMOVE=new ImcMOVE(new ImcTEMP(memFrame.RV),new ImcCONST(0));
				}

				imcStmts.add(imcMOVE);
			}
			else {
				ImcMOVE imcMOVE=new ImcMOVE(new ImcTEMP(memFrame.RV),new ImcCONST(0));
				imcStmts.add(imcMOVE);
			}

			imcStmts.add(new ImcJUMP(memLabelExit));

			for (ImcStmt s:imcStmts) {
				if (s instanceof ImcCJUMP) {
					ImcCJUMP imcCJUMP=(ImcCJUMP)s;
					MemLabel memLabelN = new MemLabel();
					imcStmtsLinearized.add(new ImcCJUMP(imcCJUMP.cond, imcCJUMP.posLabel, memLabelN));
					imcStmtsLinearized.add(new ImcLABEL(memLabelN));
					imcStmtsLinearized.add(new ImcJUMP(imcCJUMP.negLabel));
				} 
				else {
					imcStmtsLinearized.add(s);
				}
			}

            LinCodeChunk linCodeChunk=new LinCodeChunk(memFrame, imcStmtsLinearized, memLabelEntry, memLabelExit);
            // LinCodeChunk linCodeChunk=new LinCodeChunk(memFrame, imcStmts, memLabelEntry, memLabelExit);
            ImcLin.addCodeChunk(linCodeChunk);
		}
		catch(Exception ex) {
			throw new Report.Error(funDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);
		
        try {
			MemAccess memAccess=Memory.accesses.get(varDecl);
        
            if(memAccess instanceof MemAbsAccess) {
                MemAbsAccess memAbsAccess=(MemAbsAccess)memAccess;
                LinDataChunk linDataChunk=new LinDataChunk(memAbsAccess);
                ImcLin.addDataChunk(linDataChunk);
            }
        }
		catch(Exception ex) {
			throw new Report.Error(varDecl,getStackTrace(ex));
		}

        return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		try {
            if(atomExpr.type==AstAtomExpr.Type.STR) {
    			MemAbsAccess memAbsAccess=Memory.strings.get(atomExpr);
                LinDataChunk linDataChunk=new LinDataChunk(memAbsAccess);
                ImcLin.addDataChunk(linDataChunk);
            }
        }
		catch(Exception ex) {
			throw new Report.Error(atomExpr,getStackTrace(ex));
		}

        return null;
	}

	// // STATEMENTS

	// // TYPES

}
