package prev23.phase.memory;

import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;
import prev23.data.typ.*;
import prev23.data.mem.*;
import prev23.phase.seman.*;

import prev23.common.report.*;

import java.io.*;
import java.util.*;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, Object> {
    class CtxMemEvaluator {
        class CtxData {
			private long offset;

			private long locsSize;

			private long argsSize;

			public long getOffset() {
				return this.offset;
			}

			public void setOffset(long offset) {
				this.offset=offset;
			}

			public void addOffset(long offset) {
				this.offset+=offset;
			}

			public void subOffset(long offset) {
				this.offset-=offset;
			}


			public long getLocsSize() {
				return this.locsSize;
			}

			public void setLocsSize(long locsSize) {
				this.locsSize=locsSize;
			}

			public void addLocsSize(long locsSize) {
				this.locsSize+=locsSize;
			}

			public void subLocsSize(long locsSize) {
				this.locsSize-=locsSize;
			}


			public long getArgsSize() {
				return this.argsSize;
			}

			public void setArgsSize(long argsSize) {
				this.argsSize=Math.max(this.argsSize,argsSize);
			}

			public CtxData() {
			}
		}

		private Stack<CtxData> stackCtxData;

		public CtxData peek() {
			return this.stackCtxData.peek();
		}

		public void push() {
			this.stackCtxData.push(new CtxData());
		}

		public void pop() {
			this.stackCtxData.pop();
		}

		public int getStackSize() {
			return this.stackCtxData.size();
		}

		public CtxMemEvaluator() {
			this.stackCtxData=new Stack<CtxData>();
		}
    }

    private Memory memory;

    private CtxMemEvaluator ctx;

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

	private boolean compareSemTypeStr(SemType semType,String className) {
		return semType.actualType().getClass().getSimpleName().equals(className);
	}

    public MemEvaluator() {
        this.memory=new Memory();
        this.ctx=new CtxMemEvaluator();
    }

	// GENERAL PURPOSE

	// DECLARATIONS

	@Override
	public Object visit(AstCmpDecl cmpDecl, Object arg) {
		if (cmpDecl.type != null)
			cmpDecl.type.accept(this, arg);

        try {
			int stackSize=this.ctx.getStackSize();

			if(stackSize > 0) {
				CtxMemEvaluator.CtxData ctxData=this.ctx.peek();
				SemType semType=SemAn.isType.get(cmpDecl.type).actualType();
				MemAccess memAccess=new MemRelAccess(semType.size(), ctxData.getOffset(), 0);
				ctxData.addOffset(semType.size());
				Memory.accesses.put(cmpDecl,memAccess);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(cmpDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
        CtxMemEvaluator.CtxData ctxData=null;
		int stackSizeOld=this.ctx.getStackSize();

		try {
			this.ctx.push();
			ctxData=this.ctx.peek();
			ctxData.setLocsSize(0);
			ctxData.setArgsSize(0);
		}
		catch(Exception ex) {
			throw new Report.Error(funDecl,getStackTrace(ex));
		}

		ctxData.setOffset(0);
		
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
				
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
		
		ctxData.setOffset(0);
		
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);

        try {
			MemLabel memLabel=stackSizeOld==0 ? new MemLabel(funDecl.name) : new MemLabel();
			MemFrame memFrame=new MemFrame(memLabel, stackSizeOld, ctxData.getLocsSize(), ctxData.getArgsSize());
			Memory.frames.put(funDecl,memFrame);
			this.ctx.pop();
		}
		catch(Exception ex) {
			throw new Report.Error(funDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		if (parDecl.type != null)
			parDecl.type.accept(this, arg);

        try {
			int stackSize=this.ctx.getStackSize();

			if(stackSize > 0) {
				CtxMemEvaluator.CtxData ctxData=this.ctx.peek();
				SemType semType=SemAn.isType.get(parDecl.type).actualType();
				ctxData.addOffset(semType.size());
				MemAccess memAccess=new MemRelAccess(semType.size(), ctxData.getOffset(), this.ctx.getStackSize());
				Memory.accesses.put(parDecl,memAccess);
			}

		}
		catch(Exception ex) {
			throw new Report.Error(parDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);

        try {
			int stackSize=this.ctx.getStackSize();
			SemType semType=SemAn.isType.get(varDecl.type).actualType();
			MemAccess memAccess=null;	

			if(stackSize<=0) {
				memAccess=new MemAbsAccess(semType.size(), new MemLabel(varDecl.name));
			}
			else {
				CtxMemEvaluator.CtxData ctxData=this.ctx.peek();				
				ctxData.addLocsSize(semType.size());
				ctxData.subOffset(semType.size());
				memAccess=new MemRelAccess(semType.size(), ctxData.getOffset(), this.ctx.getStackSize());
			}

			Memory.accesses.put(varDecl,memAccess);
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
			int stackSize=this.ctx.getStackSize();

			if(stackSize>0) {
				SemType semType=SemAn.ofType.get(atomExpr).actualType();
			
				if(compareSemTypeStr(semType,"SemPtr")) {
					SemPtr semPtr=(SemPtr)semType;
					
					if(compareSemTypeStr(semPtr.baseType,"SemChar")) {
						SemChar semChar= (SemChar)(semPtr.baseType).actualType();
						MemLabel memLabel=new MemLabel();
						MemAbsAccess memAbsAccess=new MemAbsAccess((atomExpr.value.length() + 1) * semChar.size(), memLabel, atomExpr.value);
						Memory.strings.put(atomExpr,memAbsAccess);
					}
				}
			}			
		}
		catch(Exception ex) {
			throw new Report.Error(atomExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		if (callExpr.args != null)
			callExpr.args.accept(this, arg);

        try {
			int stackSize=this.ctx.getStackSize();

			if(stackSize>0) {
				long argsSize=0;
			
				for(AstExpr expr : callExpr.args) {
					SemType semType=SemAn.ofType.get(expr).actualType();
					argsSize+=semType.size();
				}			
			
				//Should you also check return type size
				CtxMemEvaluator.CtxData ctxData=this.ctx.peek();
				AstFunDecl astFunDecl=(AstFunDecl)SemAn.declaredAt.get(callExpr);
				SemType semType=SemAn.isType.get(astFunDecl.type).actualType();
				long funReturnSize=semType.size();
				ctxData.setArgsSize(Math.max(argsSize,funReturnSize));
			}			
		}
		catch(Exception ex) {
			throw new Report.Error(callExpr,getStackTrace(ex));
		}


		return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		CtxMemEvaluator.CtxData ctxData=null;
		
		try {
			this.ctx.push();
			ctxData=this.ctx.peek();
		}
		catch(Exception ex) {
			throw new Report.Error(recType,getStackTrace(ex));
		}

		ctxData.setOffset(0);

		if (recType.comps != null)
			recType.comps.accept(this, arg);

        try {
			this.ctx.pop();
		}
		catch(Exception ex) {
			throw new Report.Error(recType,getStackTrace(ex));
		}


		return null;
	}
}
