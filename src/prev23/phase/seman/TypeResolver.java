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

public class TypeResolver extends AstFullVisitor<Object, Object> {
	// GENERAL PURPOSE
	class CtxTypeResolver {
		private int passCount;

		private boolean isRootVisited;

		public void incPassCount() {
			this.passCount++;
		}

		public void decPassCount() {
			this.passCount--;
		}

		public int getPassCount() {
			return this.passCount;
		}

		public void setFlagRootVisited() {
			this.isRootVisited=true;
		}

		public boolean getFlagRootVisited() {
			return this.isRootVisited;
		}

		public CtxTypeResolver() {
			this.passCount=0;
			this.isRootVisited=false;
		}
	}

	private CtxTypeResolver ctx;

	public TypeResolver() {
		this.ctx=new CtxTypeResolver();
	}

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
	}

	private boolean compareSemTypeStr(SemType semType,String className) {
		return semType.actualType().getClass().getSimpleName().equals(className);
	}

	private boolean compareSemTypeStr(SemType semType0,SemType semType1) {
		return semType0.actualType().getClass().getSimpleName().equals(semType1.actualType().getClass().getSimpleName());
	}

	private boolean compareSemRec(SemRec recType0, SemRec recType1) {
		return compareSemRec_(recType0, recType1, new Vector<Vector<SemType>>());
	}

	private boolean compareSemRec_(SemRec recType0, SemRec recType1, Vector<Vector<SemType>> visited) {
		if(visited.size() > 0) {
			boolean isEqual=false;

			for(int i=0;i<visited.size()&&!isEqual;i++) {
				isEqual|=visited.get(i).get(0).equals(recType0)&&visited.get(i).get(1).equals(recType1);
			}

			if(isEqual) {
				return true;
			}
		}

		Vector<SemType>v=new Vector<>(2);
		v.add(recType0);
		v.add(recType1);
		visited.add(v);

		int n0=recType0.numCmps();
		int n1=recType1.numCmps();

		if(n0!=n1) {
			return false;
		}

		boolean isEqual=true;

		for(int i=0;i<n0&&isEqual;i++) {
			SemType semType0=recType0.cmpType(i).actualType();
			SemType semType1=recType1.cmpType(i).actualType();
			isEqual&=compareSemTypeStr(semType0,semType1);
			
			if(isEqual) {
				if(semType0 instanceof SemPtr) {
					SemPtr semPtr0=(SemPtr)semType0;
					SemPtr semPtr1=(SemPtr)semType1;
					isEqual&=compareSemPtr_(semPtr0,semPtr1,visited);
				}
				else if(semType0 instanceof SemRec) {
					SemRec semRec0=(SemRec)semType0;
					SemRec semRec1=(SemRec)semType1;
					isEqual&=compareSemRec_(semRec0,semRec1,visited);
				}
			}
		}

		return isEqual;
	}

	private boolean compareSemPtr(SemPtr ptrType0, SemPtr ptrType1) {
		return compareSemPtr_(ptrType0, ptrType1, new Vector<Vector<SemType>>());
	}

	private boolean compareSemPtr_(SemPtr ptrType0, SemPtr ptrType1, Vector<Vector<SemType>> visited) {
		if (visited.size() > 0) {
			boolean isEqual=false;

			for(int i=0;i<visited.size()&&!isEqual;i++) {
				isEqual|=visited.get(i).get(0).equals(ptrType0)&&visited.get(i).get(1).equals(ptrType1);
			}

			if(isEqual) {
				return true;
			}
		}
		
		Vector<SemType>v=new Vector<>(2);
		v.add(ptrType0);
		v.add(ptrType1);
		visited.add(v);

		SemType semType0=ptrType0.baseType.actualType();
		SemType semType1=ptrType1.baseType.actualType();

		if(!compareSemTypeStr(semType0,semType1)) {
			return false;
		}

		if(semType0 instanceof SemPtr) {
			SemPtr semPtr0=(SemPtr)semType0;
			SemPtr semPtr1=(SemPtr)semType1;
			
			return compareSemPtr_(semPtr0,semPtr1,visited);
		}
		else if (semType0 instanceof SemRec) {
			SemRec semRec0=(SemRec)semType0;
			SemRec semRec1=(SemRec)semType1;
			
			return compareSemRec_(semRec0,semRec1,visited);
		}

		return true;
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


	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		boolean isRootNode=false;
		boolean isDeclNode=trees.title.equals("Declarations");
		int nPasses=1;

		if(isDeclNode && !this.ctx.getFlagRootVisited()) {
			nPasses=3;
			isRootNode=true;
			this.ctx.setFlagRootVisited();
		}

		for(int i=0;i<nPasses;i++) {
			if(isRootNode) {
				this.ctx.incPassCount();
			}

			for (AstTree t : trees)
				if (t != null)
					t.accept(this, arg);
		}

		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCmpDecl cmpDecl, Object arg) {
		if (cmpDecl.type != null)
			cmpDecl.type.accept(this, arg);
		
        try {
			if(this.ctx.getPassCount()==3) {
				if(compareSemTypeStr(SemAn.isType.get(cmpDecl.type),"SemVoid")) {
					throw new Exception("Void SemRec member not valid");
				}
			}
		}
		catch(Exception ex) {
			throw new Report.Error(cmpDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);

        try {
			if(this.ctx.getPassCount()==3) {
				SemType semType=SemAn.isType.get(funDecl.type);					
			
				if(compareSemTypeStr(semType,"SemRec")) {
					throw new Exception("AstFunDecl return type can not be SemRec");
				}
				else if(compareSemTypeStr(semType,"SemArr")) {
					throw new Exception("AstFunDecl return type can not be SemArr");
				}

				if(funDecl.stmt != null) {
					SemType semTypeStmt=SemAn.ofType.get(funDecl.stmt);					
				
					if(compareSemTypeStr(semTypeStmt,"SemRec")) {
						throw new Exception("AstFunDecl statement type can not be SemRec");
					}
					else if(compareSemTypeStr(semTypeStmt,"SemArr")) {
						throw new Exception("AstFunDecl statement type can not be SemArr");
					}

					if(!compareSemTypeStr(semType,semTypeStmt) || (semType.actualType() instanceof SemPtr && !compareSemPtr((SemPtr)semType.actualType(),(SemPtr)semTypeStmt.actualType()))) {
						throw new Exception(String.format("AstFunDecl return type %s does not match statement type %s",semType.actualType().getClass().getSimpleName(),semTypeStmt.actualType().getClass().getSimpleName()));
					}
				}
			}
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=SemAn.isType.get(parDecl.type);

				if(compareSemTypeStr(semType,"SemVoid")) {
					throw new Exception("AstParDecl can not be of type SemVoid");
				}
				else if(compareSemTypeStr(semType,"SemRec")) {
					throw new Exception("AstParDecl can not be of type SemRec");
				}
				else if(compareSemTypeStr(semType,"SemArr")) {
					throw new Exception("AstParDecl can not be of type SemArr");
				}
			}
		}
		catch(Exception ex) {
			throw new Report.Error(parDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstTypDecl typDecl, Object arg) {
		if (typDecl.type != null)
			typDecl.type.accept(this, arg);

        try {    
			if(this.ctx.getPassCount()==1) {
				SemName semName=new SemName(typDecl.name);
				SemAn.declaresType.put(typDecl,semName);
			}
			else if(this.ctx.getPassCount()==2) {
            	SemName semName=SemAn.declaresType.get(typDecl);
				SemType semType=SemAn.isType.get(typDecl.type);		    
            	semName.define(semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(typDecl,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);

        try {    
			if(this.ctx.getPassCount() == 3) {
				SemType semTypeVar=SemAn.isType.get(varDecl.type);

				if(compareSemTypeStr(semTypeVar,"SemVoid")) {
					throw new Exception("AstVarDecl can not be of type SemVoid");
				}
			}
		}
		catch(Exception ex) {
			throw new Report.Error(varDecl,getStackTrace(ex));
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
			if(this.ctx.getPassCount() == 3) {
				SemType semType=null;		    
				SemType semTypeArr=SemAn.ofType.get(arrExpr.arr);
				SemType semTypeIdx=SemAn.ofType.get(arrExpr.idx);

				if(compareSemTypeStr(semTypeArr,"SemArr") && compareSemTypeStr(semTypeIdx,"SemInt")) {
					SemArr semArr=(SemArr)semTypeArr.actualType();
					semType=semArr.elemType;
				}
				else {
					throw new Exception(String.format("Arr should be of type SemArr, idx should be of type SemInt for node AstArrExpr"));
				}

				SemAn.ofType.put(arrExpr,semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(arrExpr,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
        try {
			if(this.ctx.getPassCount()==1) {
				SemType semType=null;		    
				
				switch(atomExpr.type) {
					case VOID:
						semType=new SemVoid();
						break;
					case CHAR:
						semType=new SemChar();
						break;
					case INT:
						semType=new SemInt();
						break;
					case BOOL:
						semType=new SemBool();
						break;
					case PTR:
						semType=new SemPtr(new SemVoid());
						break;
					case STR:
						semType=new SemPtr(new SemChar());
						break;
				}
				
				SemAn.ofType.put(atomExpr,semType);		
			}
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
			if(this.ctx.getPassCount() == 3) {
				SemType semType=null;		    
				SemType semTypeFstExpr=SemAn.ofType.get(binExpr.fstExpr);
				SemType semTypeSndExpr=SemAn.ofType.get(binExpr.sndExpr);

				switch(binExpr.oper) {
					case OR: {
						if(compareSemTypeStr(semTypeFstExpr,"SemBool") && compareSemTypeStr(semTypeSndExpr,"SemBool")) {
							semType=new SemBool();
						}
						else {
							throw new Exception("Oper OR invalid with type that is not SemBool");
						}
						
						break;
					}
					case AND: {
						if(compareSemTypeStr(semTypeFstExpr,"SemBool") && compareSemTypeStr(semTypeSndExpr,"SemBool")) {
							semType=new SemBool();
						}
						else {
							throw new Exception("Oper AND invalid with type that is not SemBool");
						}
						
						break;
					}
					case EQU: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemBool") 
						&& compareSemTypeStr(semTypeSndExpr,"SemBool")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper EQU invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;
					}
					case NEQ: {					
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemBool") 
						&& compareSemTypeStr(semTypeSndExpr,"SemBool")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper NEQ invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;
					}
					case LTH: {					
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper LTH invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;					
					}
					case GTH: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper GTH invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;
					}
					case LEQ: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper LEQ invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;
					}
					case GEQ: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") 
						&& compareSemTypeStr(semTypeSndExpr,"SemInt")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemChar") 
						&& compareSemTypeStr(semTypeSndExpr,"SemChar")) {}
						else if(compareSemTypeStr(semTypeFstExpr,"SemPtr") 
						&& compareSemTypeStr(semTypeSndExpr,"SemPtr") 
						&& compareSemPtr((SemPtr)semTypeFstExpr.actualType(),(SemPtr)semTypeSndExpr.actualType())) {}
						else {
							throw new Exception(String.format("Oper GEQ invalid with types %s, %s",semTypeFstExpr.actualType().getClass().getSimpleName(),semTypeSndExpr.actualType().getClass().getSimpleName()));
						}
						
						semType=new SemBool();
						break;
					}
					case ADD: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") && compareSemTypeStr(semTypeSndExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper ADD invalid with type that is not SemInt");
						}
						
						break;
					}
					case SUB: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") && compareSemTypeStr(semTypeSndExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper SUB invalid with type that is not SemInt");
						}
						
						break;
					}
					case MUL: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") && compareSemTypeStr(semTypeSndExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper MUL invalid with type that is not SemInt");
						}
						
						break;
					}
					case DIV: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") && compareSemTypeStr(semTypeSndExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper DIV invalid with type that is not SemInt");
						}
						
						break;
					}
					case MOD: {
						if(compareSemTypeStr(semTypeFstExpr,"SemInt") && compareSemTypeStr(semTypeSndExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper MOD invalid with type that is not SemInt");
						}
						
						break;
					}
				}
			
				SemAn.ofType.put(binExpr,semType);
			}
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
			if(this.ctx.getPassCount() == 3) {
				AstNameDecl astNameDecl = SemAn.declaredAt.get(callExpr);
			
				if (!(astNameDecl instanceof AstFunDecl)) {
					throw new Exception("Declaration was not of type AstFunDecl");
				}

				SemType semType=null;
				AstFunDecl astFunDecl = (AstFunDecl)astNameDecl;
				int n0=astFunDecl.pars.size();
				int n1=callExpr.args.size();

				if(n0!=n1) {
					throw new Exception("Number of arguments in AstCallExpr mismatching with AstFunDecl");
				}

				for(int i=0;i<n0;i++) {
					AstParDecl astParDecl=astFunDecl.pars.get(i);
					AstExpr astExpr=callExpr.args.get(i);
					SemType semTypeDecl=SemAn.isType.get(astParDecl.type);
					SemType semTypeCall=SemAn.ofType.get(astExpr);

					if(!compareSemTypeStr(semTypeDecl,semTypeCall)) {
						throw new Exception(String.format("Parameter mismatch in AstCallExpr with types %s, %s",semTypeDecl.actualType().getClass().getSimpleName(),semTypeCall.actualType().getClass().getSimpleName()));
					}

					if(semTypeDecl.actualType() instanceof SemPtr && !compareSemPtr((SemPtr)semTypeDecl.actualType(),(SemPtr)semTypeCall.actualType())) {
						throw new Exception(String.format("Parameter mismatch in AstCallExpr with SemPtr types"));						
					}
				}

				semType=SemAn.isType.get(astFunDecl.type);		
				SemAn.ofType.put(callExpr,semType);
			}
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
			if(this.ctx.getPassCount() == 3) {
				SemType semType=null;		    
				SemType semTypeExpr=SemAn.ofType.get(castExpr.expr);
				SemType semTypeCast=SemAn.isType.get(castExpr.type);

				if((compareSemTypeStr(semTypeExpr,"SemChar")
				||compareSemTypeStr(semTypeExpr,"SemInt")
				||compareSemTypeStr(semTypeExpr,"SemPtr")) 
				&& (compareSemTypeStr(semTypeCast,"SemChar")
				||compareSemTypeStr(semTypeCast,"SemInt")
				||compareSemTypeStr(semTypeCast,"SemPtr"))) {
					semType=semTypeCast;
				}
				else {
					throw new Exception(String.format("Invalid cast types, dst: %s, src: %s",semTypeExpr.actualType().getClass().getSimpleName(),semTypeCast.actualType().getClass().getSimpleName()));
				}

				SemAn.ofType.put(castExpr,semType);
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeExpr=SemAn.ofType.get(delExpr.expr);

				if(compareSemTypeStr(semTypeExpr,"SemPtr")) {
					semType=new SemVoid();							
				}
				else {
					throw new Exception("Can not use delete operator on non SemPtr");
				}

				SemAn.ofType.put(delExpr,semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(delExpr,getStackTrace(ex));
		}
		
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		try {
			if(this.ctx.getPassCount()==3) {
				AstNameDecl astNameDecl = SemAn.declaredAt.get(nameExpr);
		
				if (astNameDecl instanceof AstMemDecl) {
					AstMemDecl astMemDecl = (AstMemDecl)astNameDecl;
					SemType semType=SemAn.isType.get(astMemDecl.type);//.actualType();
					SemAn.ofType.put(nameExpr,semType);
				
					return astMemDecl;
				}
				else if(astNameDecl instanceof AstFunDecl) {
					throw new Exception("AstNameExpr referencing AstFunDecl not valid");
				}
				else if(astNameDecl instanceof AstTypDecl) {
					throw new Exception("AstNameExpr referencing AstTypDecl not valid");
				}
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeNew=SemAn.isType.get(newExpr.type);//.actualType();
				semType=new SemPtr(semTypeNew);
				SemAn.ofType.put(newExpr,semType);
			}
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
			if(this.ctx.getPassCount() == 3) {
				SemType semType=null;		    
				SemType semTypeExpr=SemAn.ofType.get(pfxExpr.expr);

				switch(pfxExpr.oper) {
					case ADD: {
						if(compareSemTypeStr(semTypeExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper ADD invalid with type that is not SemInt");
						}
						
						break;
					}
					case SUB: {
						if(compareSemTypeStr(semTypeExpr,"SemInt")) {
							semType=new SemInt();
						}
						else {
							throw new Exception("Oper SUB invalid with type that is not SemInt");
						}

						break;
					}
					case NOT: {
						if(compareSemTypeStr(semTypeExpr,"SemBool")) {
							semType=new SemBool();
						}
						else {
							throw new Exception("Oper NOT invalid with type that is not SemBool");
						}

						break;
					}
					case PTR: {
						semType=new SemPtr(semTypeExpr);
						break;
					}
				}
			
				SemAn.ofType.put(pfxExpr,semType);
			}
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
			if(this.ctx.getPassCount()==3) {
				if((recExpr.rec instanceof AstNameExpr || recExpr.rec instanceof AstRecExpr || recExpr.rec instanceof AstSfxExpr) && recExprResult instanceof AstMemDecl) {
					AstMemDecl astMemDecl = (AstMemDecl)recExprResult; 
					AstType astTypeBase=resolveBaseAstType(astMemDecl.type);

					if (!(astTypeBase instanceof AstRecType)) {
						throw new Exception("AstMemDecl type was not AstRecType");
					}

					AstRecType astRecType = (AstRecType)astTypeBase;

					for(int i=0;i<astRecType.comps.size();i++) {
						if(astRecType.comps.get(i).name.equals(recExpr.comp.name)) {
							SemAn.ofType.put(recExpr,SemAn.isType.get(astRecType.comps.get(i).type));

							return astRecType.comps.get(i);
						}
					}

					throw new Exception(String.format("No matching record component for %s", recExpr.comp.name));
				}
				else {
					throw new Exception("Invalid AstRecExpr rec");
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeExpr=SemAn.ofType.get(sfxExpr.expr);

				switch(sfxExpr.oper) {
					case PTR: {
						if(compareSemTypeStr(semTypeExpr,"SemPtr")) {
							SemPtr semPtr=(SemPtr)semTypeExpr.actualType();
							semType=semPtr.baseType;							
						}
						else {
							throw new Exception("Oper PTR invalid with type that is not SemPtr");
						}
						break;
					}
				}

				SemAn.ofType.put(sfxExpr,semType);
				
				return sfxExprResult;
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeDst=SemAn.ofType.get(assignStmt.dst);
				SemType semTypeSrc=SemAn.ofType.get(assignStmt.src);

				if(compareSemTypeStr(semTypeDst,"SemInt") 
				&& compareSemTypeStr(semTypeSrc,"SemInt")) {}
				else if(compareSemTypeStr(semTypeDst,"SemChar") 
				&& compareSemTypeStr(semTypeSrc,"SemChar")) {}
				else if(compareSemTypeStr(semTypeDst,"SemBool") 
				&& compareSemTypeStr(semTypeSrc,"SemBool")) {}
				else if(compareSemTypeStr(semTypeDst,"SemPtr") 
				&& compareSemTypeStr(semTypeSrc,"SemPtr") 
				&& compareSemPtr((SemPtr)semTypeDst.actualType(),(SemPtr)semTypeSrc.actualType())) {}
				else {
					throw new Exception(String.format("AstAssignStmt invalid with types %s, %s",semTypeDst.actualType().getClass().getSimpleName(),semTypeSrc.actualType().getClass().getSimpleName()));
				}
				
				semType=new SemVoid();
				SemAn.ofType.put(assignStmt,semType);		
			}
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=SemAn.ofType.get(declStmt.stmt);			
				SemAn.ofType.put(declStmt,semType);		
			}			
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=SemAn.ofType.get(exprStmt.expr);			
				SemAn.ofType.put(exprStmt,semType);		
			}			
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeCond=SemAn.ofType.get(ifStmt.cond);

				if(compareSemTypeStr(semTypeCond,"SemBool")) { 
					semType=new SemVoid();
				}
				else {
					throw new Exception(String.format("AstIfStmt cond should be of type SemBool"));
				}
				
				SemAn.ofType.put(ifStmt,semType);
			}			
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
			if(this.ctx.getPassCount()==3) {
				if(stmts.stmts.size() > 0) {
					SemType semType=SemAn.ofType.get(stmts.stmts.get(stmts.stmts.size()-1));			
					SemAn.ofType.put(stmts,semType);
				}
			}			
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
			if(this.ctx.getPassCount()==3) {
				SemType semType=null;		    
				SemType semTypeCond=SemAn.ofType.get(whileStmt.cond);

				if(compareSemTypeStr(semTypeCond,"SemBool")) { 
					semType=new SemVoid();
				}
				else {
					throw new Exception("AstWhileStmt cond should be of type SemBool");
				}
				
				SemAn.ofType.put(whileStmt,semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(whileStmt,getStackTrace(ex));
		}

		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Object arg) {
		if (arrType.elemType != null)
			arrType.elemType.accept(this, arg);
		if (arrType.numElems != null)
			arrType.numElems.accept(this, arg);

        try {
			if(this.ctx.getPassCount()==2) {
				if(arrType.numElems instanceof AstAtomExpr) {
					SemType semTypeNumElems=SemAn.ofType.get(arrType.numElems);

					if(compareSemTypeStr(semTypeNumElems,"SemInt")) {
						AstAtomExpr astAtomExpr=(AstAtomExpr)arrType.numElems;
						long size=Long.parseLong(astAtomExpr.value);
						
						if(size > 0) {
							SemType semType=new SemArr(SemAn.isType.get(arrType.elemType),size);
							SemAn.isType.put(arrType,semType);
						}
						else {
							throw new Exception("Array size can not be <= 0");
						}
					}
					else {
						throw new Exception("Array size was not of type SemInt");
					}
				}
				else {
					throw new Exception("Node numElems was not of type AstAtomExpr");
				}
			}
			else if(this.ctx.getPassCount()==3) {
				if(compareSemTypeStr(SemAn.isType.get(arrType.elemType),"SemVoid")) {
					throw new Exception("SemArr elemType of type SemVoid not valid");
				}
			}
		}
		catch(NumberFormatException ex) {
			throw new Report.Error(arrType,getStackTrace(ex));
		}
		catch(Exception ex) {
			throw new Report.Error(arrType,getStackTrace(ex));
		}

        return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {        
        try {
			if(this.ctx.getPassCount()==1) {
				SemType semType=null;		    
				
				switch(atomType.type) {
					case VOID: 
						semType=new SemVoid();
						break;
					case CHAR: 
						semType=new SemChar();
						break;
					case INT: 
						semType=new SemInt();
						break;
					case BOOL: 
						semType=new SemBool();
						break;
				}
				
				SemAn.isType.put(atomType,semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(atomType,getStackTrace(ex));
		}
		
        return null;
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
        try {
			if(this.ctx.getPassCount()==2) {
				AstNameDecl astNameDecl = SemAn.declaredAt.get(nameType);
			
				if (astNameDecl instanceof AstTypDecl) {
					AstTypDecl astTypDecl = (AstTypDecl)astNameDecl;
					SemName semName=SemAn.declaresType.get(astTypDecl);
					SemAn.isType.put(nameType,semName);		
				}
				else {
					throw new Exception("Declaration was not of type AstTypDecl");
				}
			}
		}
		catch(Exception ex) {
			throw new Report.Error(nameType,getStackTrace(ex));
		}

		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		if (ptrType.baseType != null)
			ptrType.baseType.accept(this, arg);
		
        try {
			if(this.ctx.getPassCount()==2) {
				SemType semType=new SemPtr(SemAn.isType.get(ptrType.baseType));
				SemAn.isType.put(ptrType,semType);
			}
		}
		catch(Exception ex) {
			throw new Report.Error(ptrType,getStackTrace(ex));
		}

        return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		if (recType.comps != null)
			recType.comps.accept(this, arg);

        try {
			if(this.ctx.getPassCount()==2) {				
				Vector<SemType> semTypes=new Vector<>();
				
				for (AstCmpDecl astCmpDecl : recType.comps) {
					semTypes.add(SemAn.isType.get(astCmpDecl.type));
				}

				SemType semType=new SemRec(semTypes);
				SemAn.isType.put(recType,semType);		
			}
		}
		catch(Exception ex) {
			throw new Report.Error(recType,getStackTrace(ex));
		}
		
		return null;
	}
}