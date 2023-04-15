package prev23.phase.seman;

import prev23.common.report.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;

import java.io.*;
import java.util.*;

/**
 * Name resolver.
 * 
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link prev23.phase.seman.SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, Object> {
	class CtxNameResolver {
		class ScopeDepth {
			private int depth;

			public ScopeDepth(int depth) {
				this.depth=depth;
			}

			public int get() {
				return this.depth;
			}

			public void inc() {
				this.depth++;
			}

			public void dec() {
				this.depth--;
			}
		}

		private Stack<ScopeDepth> scopeDepths;
		
		public void push(int val) {
			this.scopeDepths.push(new ScopeDepth(val));
		}

		public void pop() {
			this.scopeDepths.pop();
		}

		public int peek() {
			return this.scopeDepths.peek().get();
		}

		public void inc() {
			this.scopeDepths.peek().inc();
		}

		public void dec() {
			this.scopeDepths.peek().dec();
		}

		public boolean IsParentAstRecExpr;

		public CtxNameResolver() {
			this.scopeDepths=new Stack<ScopeDepth>();
			this.IsParentAstRecExpr=false;
		}
	}

	private CtxNameResolver ctx;

	private SymbTable symbolTable;

	public NameResolver() {
		this.symbolTable=new SymbTable();
		this.ctx=new CtxNameResolver();
		this.ctx.push(0);
	}

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		try {
			if(trees.title.equals("ParamDeclarations")) {
				for(int i=0;i<2;i++) {
					for (AstTree t : trees) {
						if (t != null) {
							AstParDecl decl=(AstParDecl)t;
							
							if(i==0) {
								t.accept(this, arg);
							}
							else if(i==1) {
								this.symbolTable.ins(decl.name, decl);
							}
						}
					}
				}
			}
			else if(trees.title.equals("FunDeclarations")) {
				this.symbolTable.newScope();
				this.ctx.inc();

				for(int i=0;i<2;i++) {
					for (AstTree t : trees) {
						if (t != null) {
							AstFunDecl decl=(AstFunDecl)t;
							
							if(i==0) {
								this.symbolTable.ins(decl.name, decl);
							}
							else if(i==1) {
								t.accept(this, arg);
							}
						}
					}
				}
			}
			else if(trees.title.equals("VarDeclarations")) {
				for(int i=0;i<2;i++) {
					if(i==1) {
						this.symbolTable.newScope();
						this.ctx.inc();
					}

					for (AstTree t : trees) {
						if (t != null) {
							AstVarDecl decl=(AstVarDecl)t;
							
							if(i==0) {
								t.accept(this, arg);
							}
							else if(i==1) {
								this.symbolTable.ins(decl.name, decl);
							}
						}
					}
				}
			}
			else if(trees.title.equals("TypeDeclarations")) {
				// for(int i=0;i<2;i++) {
				// 	if(i==1) {
				// 		this.symbolTable.newScope();
				// 		this.ctx.inc();
				// 	}

				// 	for (AstTree t : trees) {
				// 		if (t != null) {
				// 			AstTypDecl decl=(AstTypDecl)t;
							
				// 			if(i==0) {
				// 				t.accept(this, arg);
				// 			}
				// 			else if(i==1) {
				// 				this.symbolTable.ins(decl.name, decl);
				// 			}
				// 		}
				// 	}
				// }

				this.symbolTable.newScope();
				this.ctx.inc();

				for(int i=0;i<2;i++) {
					for (AstTree t : trees) {
						if (t != null) {
				 			AstTypDecl decl=(AstTypDecl)t;
							
							if(i==0) {
								this.symbolTable.ins(decl.name, decl);
							}
							else if(i==1) {
								t.accept(this, arg);
							}
						}
					}
				}
			}
			else {
				for (AstTree t : trees) {
					if (t != null) {
						t.accept(this, arg);
					}
				}
			}
		}
		catch(SymbTable.CannotInsNameException ex) {
			throw new Report.Error(trees,String.format("%s: %s", ex.getMessage(), trees.title));			
		}
		catch(Exception ex) {
			throw new Report.Error(trees,getStackTrace(ex));
		}

		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);

		this.symbolTable.newScope();
		this.ctx.push(0);
		this.ctx.inc();

		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);

		while(this.ctx.peek() > 0) {
			this.symbolTable.oldScope();
			this.ctx.dec();
		}

		this.ctx.pop();
		
		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		try {
			SemAn.declaredAt.put(callExpr,this.symbolTable.fnd(callExpr.name));		
		}
		catch(SymbTable.CannotFndNameException ex) {
			throw new Report.Error(callExpr,String.format("%s: AstCallExpr",ex.getMessage()));			
		}
		catch(Exception ex) {
			throw new Report.Error(callExpr,getStackTrace(ex));
		}

		if (callExpr.args != null)
			callExpr.args.accept(this, arg);
		
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		if(!this.ctx.IsParentAstRecExpr) {
			try {
				SemAn.declaredAt.put(nameExpr,this.symbolTable.fnd(nameExpr.name));
			}
			catch(SymbTable.CannotFndNameException ex) {
				throw new Report.Error(nameExpr,String.format("%s: AstNameExpr",ex.getMessage()));			
			}
			catch(Exception ex) {
				throw new Report.Error(nameExpr,getStackTrace(ex));
			}
		}

		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);

		this.ctx.IsParentAstRecExpr=true;

		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);
	
		this.ctx.IsParentAstRecExpr=false;

		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
		this.ctx.push(0);

		if (declStmt.decls != null)
			declStmt.decls.accept(this, arg);
		if (declStmt.stmt != null)
			declStmt.stmt.accept(this, arg);

		while(this.ctx.peek() > 0) {
			this.symbolTable.oldScope();
			this.ctx.dec();
		}

		this.ctx.pop();

		return null;
	}

	// TYPES

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		try {
			SemAn.declaredAt.put(nameType,this.symbolTable.fnd(nameType.name));
		}
		catch(SymbTable.CannotFndNameException ex) {
			throw new Report.Error(nameType,String.format("%s: AstNameType",ex.getMessage()));			
		}
		catch(Exception ex) {
			throw new Report.Error(nameType,getStackTrace(ex));
		}

		return null;
	}
}
