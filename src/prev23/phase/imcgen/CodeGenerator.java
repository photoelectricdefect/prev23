package prev23.phase.imcgen;

import prev23.common.report.*;
import prev23.phase.seman.*;
import prev23.phase.memory.*;

import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;

import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.mem.*;
import prev23.data.typ.*;

import java.io.*;
import java.util.*;

public class CodeGenerator extends AstFullVisitor<Object, Object> {
	// GENERAL PURPOSE

	class CtxCodeGenerator {
		private Stack<MemFrame> stackFrames;

		public void push(MemFrame memFrame) {
			this.stackFrames.push(memFrame);
		}

		public void pop() {
			this.stackFrames.pop();
		}

		public MemFrame peek() {
			return this.stackFrames.peek();
		}

		public CtxCodeGenerator() {
			this.stackFrames=new Stack<MemFrame>();
		}
	}

	private CtxCodeGenerator ctx;

	private boolean compareSemTypeStr(SemType semType,String className) {
		return semType.actualType().getClass().getSimpleName().equals(className);
	}

	private AstType astNameType2ActualAstType(AstNameType astNameType) throws Exception {
		AstNameDecl astNameDecl = SemAn.declaredAt.get(astNameType);

		if(!(astNameDecl instanceof AstTypDecl)) {
			throw new Exception("Error getting base type, AstNameDecl not instanceof AstTypDecl");
		}

		AstTypDecl astTypDecl = (AstTypDecl)astNameDecl;

		if(astTypDecl.type instanceof AstNameType) {
			return astNameType2ActualAstType((AstNameType)astTypDecl.type);
		}
		else {
			return astTypDecl.type;
		}
	}

	private AstType astPtrType2BaseAstType(AstPtrType astPtrType) throws Exception {
		if(astPtrType.baseType instanceof AstPtrType) {
			return astPtrType2BaseAstType((AstPtrType)astPtrType.baseType);
		}
		else {
			return astPtrType.baseType;
		}		
	}

	private AstType resolveBaseAstType(AstType astType) throws Exception {
		AstType result=astType;
		
		do {
			if(result instanceof AstNameType) {
				result=astNameType2ActualAstType((AstNameType)result);
			}

			if(result instanceof AstPtrType) {
				result=astPtrType2BaseAstType((AstPtrType)result);
			}
		} while(result instanceof AstNameType || result instanceof AstPtrType);

		return result;
	}

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

	public CodeGenerator() {
		this.ctx=new CtxCodeGenerator();
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		try {
			MemFrame memFrame=Memory.frames.get(funDecl);		
			this.ctx.push(memFrame);
		}
		catch(Exception ex) {
			throw new Report.Error(funDecl,getStackTrace(ex));
		}

		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);

		try {
			this.ctx.pop();
		}
		catch(Exception ex) {
			throw new Report.Error(funDecl,getStackTrace(ex));
		}

		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);

		try {
			ImcMEM imcMEMArr=(ImcMEM)ImcGen.exprImc.get(arrExpr.arr);
			ImcExpr imcExprIdx=ImcGen.exprImc.get(arrExpr.idx);
			SemArr semTypeArr=(SemArr)SemAn.ofType.get(arrExpr.arr).actualType();
			SemType semTypeArrElemType=semTypeArr.elemType.actualType();
			long sizeElemType=semTypeArrElemType.size();
			ImcCONST imcCONST=new ImcCONST(sizeElemType);
			ImcBINOP imcBINOP0=new ImcBINOP(ImcBINOP.Oper.MUL,imcExprIdx,imcCONST);
			ImcBINOP imcBINOP1=new ImcBINOP(ImcBINOP.Oper.ADD,imcMEMArr.addr,imcBINOP0);
			ImcMEM imcMEM=new ImcMEM(imcBINOP1);
			ImcGen.exprImc.put(arrExpr,imcMEM);
		}
		catch(Exception ex) {
			throw new Report.Error(arrExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		try {
			ImcExpr imcExpr=null;		    
			
			switch(atomExpr.type) {
				case VOID: {
					imcExpr=new ImcCONST(0);
					
					break;
				}
				case CHAR: {
					long val=atomExpr.value.length()==3?atomExpr.value.charAt(1):atomExpr.value.charAt(2);
					imcExpr=new ImcCONST(val);
					
					break;
				}
				case INT: {
					long val=Long.parseLong(atomExpr.value);
					imcExpr=new ImcCONST(val);
					
					break;
				}
				case BOOL: {
					long val=atomExpr.value.equals("true") ? 1 : 0;
					imcExpr=new ImcCONST(val);

					break;
				}
				case PTR: {
					long val=0;
					imcExpr=new ImcCONST(val);

					break;
				}
				case STR: {
					MemAbsAccess memAbsAccess=Memory.strings.get(atomExpr); 
					imcExpr=new ImcNAME(memAbsAccess.label);
					
					break;
				}
			}
			
			ImcGen.exprImc.put(atomExpr,imcExpr);		
		}
		catch(Exception ex) {
			throw new Report.Error(atomExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		
		try {
			ImcBINOP.Oper oper=null;		    
			
			switch(binExpr.oper) {
				case OR: {
					oper=ImcBINOP.Oper.OR;		
					break;
				}
				case AND: {
					oper=ImcBINOP.Oper.AND;
					break;
				}
				case EQU: {
					oper=ImcBINOP.Oper.EQU;
					break;
				}
				case NEQ: {					
					oper=ImcBINOP.Oper.NEQ;
					break;
				}
				case LTH: {					
					oper=ImcBINOP.Oper.LTH;
					break;					
				}
				case GTH: {
					oper=ImcBINOP.Oper.GTH;
					break;
				}
				case LEQ: {
					oper=ImcBINOP.Oper.LEQ;
					break;
				}
				case GEQ: {
					oper=ImcBINOP.Oper.GEQ;
					break;
				}
				case ADD: {
					oper=ImcBINOP.Oper.ADD;					
					break;
				}
				case SUB: {
					oper=ImcBINOP.Oper.SUB;
					break;
				}
				case MUL: {
					oper=ImcBINOP.Oper.MUL;
					break;
				}
				case DIV: {
					oper=ImcBINOP.Oper.DIV;
					break;
				}
				case MOD: {
					oper=ImcBINOP.Oper.MOD;
					break;
				}
			}
		
			ImcExpr imcExpr0=ImcGen.exprImc.get(binExpr.fstExpr);
			ImcExpr imcExpr1=ImcGen.exprImc.get(binExpr.sndExpr);
			ImcExpr imcExpr=new ImcBINOP(oper,imcExpr0,imcExpr1);
			ImcGen.exprImc.put(binExpr,imcExpr);
		}
		catch(Exception ex) {
			throw new Report.Error(binExpr,getStackTrace(ex));
		}
				
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		if (callExpr.args != null)
			callExpr.args.accept(this, arg);
	
		try {
			AstNameDecl astNameDecl = SemAn.declaredAt.get(callExpr);
			AstFunDecl astFunDecl = (AstFunDecl)astNameDecl;
			MemFrame memFrameCalled = Memory.frames.get(astFunDecl);
			ImcExpr imcExprSL=null;
			
			if(memFrameCalled.depth<=0) {
				imcExprSL=new ImcCONST(0);
			}
			else {
				MemFrame memFrameCalle = this.ctx.peek();
				imcExprSL=new ImcTEMP(memFrameCalled.FP);
				int diffDepth=memFrameCalle.depth-memFrameCalled.depth;
			
				//TODO: should be LTH or LTE, i believe LTE??
				for(int i=0;i<=diffDepth;i++) {
					imcExprSL=new ImcMEM(imcExprSL);
				}
			}

			Vector<ImcExpr> imcExprs=new Vector<>();
			imcExprs.add(imcExprSL);
			Vector<Long> offsets=new Vector<>();
			long offset=new SemPtr(new SemVoid()).size();
			offsets.add(offset);
			
			for(int i=0;i<callExpr.args.size();i++) {
				AstExpr argExpr=callExpr.args.get(i);
				ImcExpr imcExpr=ImcGen.exprImc.get(argExpr);
				imcExprs.add(imcExpr);
				SemType semType=SemAn.ofType.get(argExpr).actualType();
				offset+=semType.size();
				offsets.add(offset);
			}

			ImcExpr imcExpr=new ImcCALL(memFrameCalled.label,offsets,imcExprs);
			ImcGen.exprImc.put(callExpr,imcExpr);
		}
		catch(Exception ex) {
			throw new Report.Error(callExpr,getStackTrace(ex));
		}
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		
		try {
			SemType semType=SemAn.isType.get(castExpr.type);
			ImcExpr imcExpr=ImcGen.exprImc.get(castExpr.expr);
		
			if(compareSemTypeStr(semType,"SemChar")) {
				ImcCONST imcCONST=new ImcCONST(256);
				ImcBINOP imcBINOP=new ImcBINOP(ImcBINOP.Oper.MOD,imcExpr,imcCONST);
				ImcGen.exprImc.put(castExpr,imcBINOP);
			}
			else {
				ImcGen.exprImc.put(castExpr,imcExpr);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(castExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstDelExpr delExpr, Object arg) {
		if (delExpr.expr != null)
			delExpr.expr.accept(this, arg);
		
		try {
			MemLabel memLabel=new MemLabel("del");
			Vector<Long> offs=new Vector<Long>();
			offs.add((long)0);
			offs.add(new SemPtr(new SemVoid()).size());
			Vector<ImcExpr> args=new Vector<ImcExpr>();
			args.add(new ImcCONST(0));
			ImcExpr imcExpr=ImcGen.exprImc.get(delExpr.expr); 
			args.add(imcExpr);
			ImcCALL imcCALL=new ImcCALL(memLabel,offs,args);
			ImcGen.exprImc.put(delExpr,imcCALL);
		}
		catch(Exception ex) {
			throw new Report.Error(delExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		try {
			AstNameDecl astNameDecl = SemAn.declaredAt.get(nameExpr);

			if(astNameDecl instanceof AstMemDecl) {
				AstMemDecl astMemDecl = (AstMemDecl)astNameDecl;
				MemAccess memAccess=Memory.accesses.get(astMemDecl);
				ImcExpr imcExpr=null;

				if(memAccess instanceof MemRelAccess) {
					MemRelAccess memRelAccess=(MemRelAccess)memAccess;
					MemFrame memFrame=this.ctx.peek();
					ImcExpr imcExpr0=new ImcTEMP(memFrame.FP);
					int diffDepth=memFrame.depth-memRelAccess.depth;
				
					for(int i=0;i<=diffDepth;i++) {
						imcExpr0=new ImcMEM(imcExpr0);
					}

					ImcCONST imcCONST=new ImcCONST(memRelAccess.offset);
					ImcBINOP imcBINOP=new ImcBINOP(ImcBINOP.Oper.ADD,imcExpr0,imcCONST);
					imcExpr=new ImcMEM(imcBINOP); 
				}
				else {
					MemAbsAccess memAbsAccess=(MemAbsAccess)memAccess;
					ImcNAME imcNAME=new ImcNAME(memAbsAccess.label);
					imcExpr=new ImcMEM(imcNAME);
				}

				ImcGen.exprImc.put(nameExpr,imcExpr);
				
				return astNameDecl;
			}
		}
		catch(Exception ex) {
			throw new Report.Error(nameExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstNewExpr newExpr, Object arg) {
		if (newExpr.type != null)
			newExpr.type.accept(this, arg);
		
		try {
			MemLabel memLabel=new MemLabel("new");
			Vector<Long> offs=new Vector<Long>();
			offs.add((long)0);
			offs.add(new SemPtr(new SemVoid()).size());
			Vector<ImcExpr> args=new Vector<ImcExpr>();
			args.add(new ImcCONST(0));
			SemType semType=SemAn.isType.get(newExpr.type).actualType(); 
			args.add(new ImcCONST(semType.size()));
			ImcCALL imcCALL=new ImcCALL(memLabel,offs,args);
			ImcGen.exprImc.put(newExpr,imcCALL);
		}
		catch(Exception ex) {
			throw new Report.Error(newExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		
		try {
			SemType semType=SemAn.ofType.get(pfxExpr.expr);
			ImcExpr imcExpr=null;

			switch(pfxExpr.oper) {
				case ADD: {
					imcExpr=ImcGen.exprImc.get(pfxExpr.expr);

					break;
				}
				case SUB: {
					ImcExpr imcExpr0=ImcGen.exprImc.get(pfxExpr.expr);
					imcExpr=new ImcUNOP(ImcUNOP.Oper.NEG,imcExpr0);

					break;
				}
				case NOT: {
					ImcExpr imcExpr0=ImcGen.exprImc.get(pfxExpr.expr);
					imcExpr=new ImcUNOP(ImcUNOP.Oper.NOT,imcExpr0);

					break;
				}
				case PTR: {
					ImcMEM imcMEM=(ImcMEM)ImcGen.exprImc.get(pfxExpr.expr);
					imcExpr=imcMEM.addr;
					
					break;
				}
			}
		
			ImcGen.exprImc.put(pfxExpr,imcExpr);
		}
		catch(Exception ex) {
			throw new Report.Error(pfxExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		Object recExprResult=null;

		if (recExpr.rec != null)
			recExprResult=recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);

		try {
			AstMemDecl astMemDecl = (AstMemDecl)recExprResult;
			AstType astTypeBase=resolveBaseAstType(astMemDecl.type);
			AstRecType astRecType = (AstRecType)astTypeBase;

			for(int i=0;i<astRecType.comps.size();i++) {
				if(astRecType.comps.get(i).name.equals(recExpr.comp.name)) {
					AstCmpDecl astCmpDecl = astRecType.comps.get(i);
					ImcMEM imcMEM=(ImcMEM)ImcGen.exprImc.get(recExpr.rec);
					MemRelAccess memRelAccess=(MemRelAccess)Memory.accesses.get(astCmpDecl);
					ImcCONST imcCONST=new ImcCONST(memRelAccess.offset);
					ImcBINOP imcBINOP=new ImcBINOP(ImcBINOP.Oper.ADD,imcMEM.addr,imcCONST); 
					ImcExpr imcExpr=new ImcMEM(imcBINOP);
					ImcGen.exprImc.put(recExpr,imcExpr);
					
					return astCmpDecl;
				}
			}
		}
		catch(Exception ex) {
			throw new Report.Error(recExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		Object sfxExprResult=null;

		if (sfxExpr.expr != null)
			sfxExprResult=sfxExpr.expr.accept(this, arg);
		
		try {
			ImcExpr imcExprSfx=ImcGen.exprImc.get(sfxExpr.expr);
			//TODO: can you deref null ptr, should you check??
			ImcExpr imcExpr=new ImcMEM(imcExprSfx);
			ImcGen.exprImc.put(sfxExpr,imcExpr);
		}
		catch(Exception ex) {
			throw new Report.Error(sfxExpr,getStackTrace(ex));
		}
		
		return sfxExprResult;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		
		try {
			ImcMEM imcMEM=(ImcMEM)ImcGen.exprImc.get(assignStmt.dst);
			ImcExpr imcExpr=ImcGen.exprImc.get(assignStmt.src);			
			ImcMOVE imcMOVE=new ImcMOVE(imcMEM,imcExpr);
			ImcGen.stmtImc.put(assignStmt,imcMOVE);
		}
		catch(Exception ex) {
			throw new Report.Error(assignStmt,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
		if (declStmt.decls != null)
			declStmt.decls.accept(this, arg);
		if (declStmt.stmt != null)
			declStmt.stmt.accept(this, arg);

		try {
			ImcStmt imcStmt=ImcGen.stmtImc.get(declStmt.stmt);
			ImcGen.stmtImc.put(declStmt,imcStmt);
		}
		catch(Exception ex) {
			throw new Report.Error(declStmt,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		
		try {
			ImcExpr imcExpr=ImcGen.exprImc.get(exprStmt.expr);
			ImcESTMT imcESTMT=new ImcESTMT(imcExpr);
			ImcGen.stmtImc.put(exprStmt,imcESTMT);
		}
		catch(Exception ex) {
			throw new Report.Error(exprStmt,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Object arg) {
		if (ifStmt.cond != null)
			ifStmt.cond.accept(this, arg);
		if (ifStmt.thenStmt != null)
			ifStmt.thenStmt.accept(this, arg);
		if (ifStmt.elseStmt != null)
			ifStmt.elseStmt.accept(this, arg);
		
		try {
			Vector<ImcStmt> vectorImcStmts=new Vector<>();
			ImcExpr imcExprCond=ImcGen.exprImc.get(ifStmt.cond); 

			ImcLABEL imcLABELThen=new ImcLABEL(new MemLabel()); 
			ImcStmt imcStmtThen=ImcGen.stmtImc.get(ifStmt.thenStmt); 
			ImcLABEL imcLABELEnd=new ImcLABEL(new MemLabel()); 

			if(ifStmt.elseStmt!=null) {
				ImcLABEL imcLABELElse=new ImcLABEL(new MemLabel()); 
				ImcStmt imcStmtElse=ImcGen.stmtImc.get(ifStmt.elseStmt); 
				ImcJUMP imcJUMP=new ImcJUMP(imcLABELEnd.label); 
				ImcCJUMP imcCJUMP=new ImcCJUMP(imcExprCond,imcLABELThen.label,imcLABELElse.label);
				vectorImcStmts.add(imcCJUMP);
				vectorImcStmts.add(imcLABELThen);
				vectorImcStmts.add(imcStmtThen);
				vectorImcStmts.add(imcJUMP);
				vectorImcStmts.add(imcLABELElse);
				vectorImcStmts.add(imcStmtElse);
				vectorImcStmts.add(imcLABELEnd);
			}
			else {
				ImcCJUMP imcCJUMP=new ImcCJUMP(imcExprCond,imcLABELThen.label,imcLABELEnd.label);
				vectorImcStmts.add(imcCJUMP);
				vectorImcStmts.add(imcLABELThen);
				vectorImcStmts.add(imcStmtThen);
				vectorImcStmts.add(imcLABELEnd);
			}
			
			ImcSTMTS imcSTMTS=new ImcSTMTS(vectorImcStmts);  
			ImcGen.stmtImc.put(ifStmt,imcSTMTS); 
		}
		catch(Exception ex) {
			throw new Report.Error(ifStmt,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstStmts stmts, Object arg) {
		if (stmts.stmts != null)
			stmts.stmts.accept(this, arg);
		
		try {
			Vector<ImcStmt> vectorImcStmts=new Vector<ImcStmt>();

			for(int i=0;i<stmts.stmts.size();i++) {
				AstStmt astStmt=stmts.stmts.get(i);
				ImcStmt imcStmt=ImcGen.stmtImc.get(astStmt);
				vectorImcStmts.add(imcStmt);
			}

			ImcSTMTS imcSTMTS=new ImcSTMTS(vectorImcStmts);
			ImcGen.stmtImc.put(stmts,imcSTMTS);
		}
		catch(Exception ex) {
			throw new Report.Error(stmts,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		
		try {
			Vector<ImcStmt> vectorImcStmts=new Vector<>();
			ImcExpr imcExprCond=ImcGen.exprImc.get(whileStmt.cond); 

			ImcLABEL imcLABELHead=new ImcLABEL(new MemLabel()); 
			ImcLABEL imcLABELBody=new ImcLABEL(new MemLabel()); 
			ImcStmt imcStmtBody=ImcGen.stmtImc.get(whileStmt.bodyStmt); 
			ImcLABEL imcLABELEnd=new ImcLABEL(new MemLabel()); 
			ImcCJUMP imcCJUMP=new ImcCJUMP(imcExprCond,imcLABELBody.label,imcLABELEnd.label); 
			ImcJUMP imcJUMP=new ImcJUMP(imcLABELHead.label); 

			vectorImcStmts.add(imcLABELHead);
			vectorImcStmts.add(imcCJUMP);
			vectorImcStmts.add(imcLABELBody);
			vectorImcStmts.add(imcStmtBody);
			vectorImcStmts.add(imcJUMP);
			vectorImcStmts.add(imcLABELEnd);

			ImcSTMTS imcSTMTS=new ImcSTMTS(vectorImcStmts);  
			ImcGen.stmtImc.put(whileStmt,imcSTMTS); 
		}
		catch(Exception ex) {
			throw new Report.Error(whileStmt,getStackTrace(ex));
		}

		
		return null;
	}

	// TYPES
}