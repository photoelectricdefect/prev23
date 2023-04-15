parser grammar PrevParser;

@header {

	package prev23.phase.synan;
	
	import java.util.*;
	
	import prev23.common.report.*;
	import prev23.phase.lexan.*;
	
	import prev23.data.ast.tree.*;
	import prev23.data.ast.tree.decl.*;
	import prev23.data.ast.tree.expr.*;
	import prev23.data.ast.tree.stmt.*;
	import prev23.data.ast.tree.type.*;

}

@members {
    public Location location(Token token) { 
		if(token != null) {
			return new Location((prev23.data.sym.Token) token); 
		}
		else {
			return new Location(0,0,0,0);
		}
	}

    public Location location(Locatable locat) { 
		if(locat != null) {
			return new Location(locat); 
		}
		else {
			return new Location(0,0,0,0);
		}
	}

    public Location location(Token token1, Token token2) { 
		return new Location((prev23.data.sym.Token) token1, (prev23.data.sym.Token) token2); 
	}
    
	public Location location(Token token1, Locatable locat2) { 
		return new Location((prev23.data.sym.Token) token1, locat2); 
	}
    
	public Location location(Locatable locat1, Token token2) { 
		return new Location(locat1, (prev23.data.sym.Token) token2); 
	}
    
	public Location location(Locatable locat1, Locatable locat2) { 
		return new Location(locat1, locat2); 
	}	
}

options{
    tokenVocab=PrevLexer;
}

source returns [AstTree ast]
  : declarations EOF {
		$ast=$declarations.ast;
	};

declarations returns [AstTrees<AstTrees<AstDecl>> ast] locals [Vector<AstTrees<AstDecl>> nodes=new Vector<>()] : (type_declarations {
	$nodes.add($type_declarations.ast);
} | function_declarations {
	$nodes.add($function_declarations.ast);
} | variable_declarations {
	$nodes.add($variable_declarations.ast);
})+ {
	$ast=new AstTrees("Declarations",$nodes);
}; 

type_declarations returns [AstTrees<AstDecl> ast] locals [Vector<AstDecl> nodes=new Vector<>()] : KEY_TYP id_0=ID SYM_EQ tp_0=type {
	$nodes.add(new AstTypDecl(location($id_0,$tp_0.ast), $id_0.getText(), $tp_0.ast));
} (SYM_COMMA id_1=ID SYM_EQ tp_1=type {
	$nodes.add(new AstTypDecl(location($id_1,$tp_1.ast), $id_1.getText(), $tp_1.ast));
})* SYM_SEMICOLON {
	$ast=new AstTrees("TypeDeclarations",$nodes);
};

function_declarations returns [AstTrees<AstDecl> ast] locals [Vector<AstParDecl> pr_decls=new Vector<>(), Vector<AstDecl> nodes=new Vector<>(),AstStmt nullableStmt=null] : 
KEY_FUN id_0=ID SYM_PARENTH_L (id_1=ID SYM_COLON tp_0=type {
	$pr_decls.add(new AstParDecl(location($id_1,$tp_0.ast),$id_1.getText(),$tp_0.ast));
} (SYM_COMMA id_2=ID SYM_COLON tp_1=type {
	$pr_decls.add(new AstParDecl(location($id_2,$tp_1.ast),$id_2.getText(),$tp_1.ast));	
})*)? SYM_PARENTH_R SYM_COLON tp_2=type (SYM_EQ st_0=statement {
	$nullableStmt=$st_0.ast;
})? {
	Location lc=$nullableStmt!=null?location($id_0,$nullableStmt):location($id_0,$tp_2.ast);
	$nodes.add(new AstFunDecl(lc, $id_0.getText(), new AstTrees("ParamDeclarations",$pr_decls), $tp_2.ast,$nullableStmt));	
	$pr_decls.clear();
	$nullableStmt=null;
}
(SYM_COMMA id_3=ID SYM_PARENTH_L (id_4=ID SYM_COLON tp_3=type {
	$pr_decls.add(new AstParDecl(location($id_4,$tp_3.ast),$id_4.getText(),$tp_3.ast));	
} (SYM_COMMA id_5=ID SYM_COLON tp_4=type {
	$pr_decls.add(new AstParDecl(location($id_5,$tp_4.ast),$id_5.getText(),$tp_4.ast));
})*)? SYM_PARENTH_R SYM_COLON tp_5=type (SYM_EQ st_1=statement {
	$nullableStmt=$st_1.ast;
})? {
	Location lc0=$nullableStmt!=null?location($id_3,$nullableStmt):location($id_3,$tp_5.ast);
	$nodes.add(new AstFunDecl(lc0, $id_3.getText(), new AstTrees("ParamDeclarations", $pr_decls), $tp_5.ast,$nullableStmt));	
	$pr_decls.clear();
	$nullableStmt=null;
})* SYM_SEMICOLON {
	$ast=new AstTrees("FunDeclarations",$nodes);
};

variable_declarations returns [AstTrees<AstDecl> ast] locals [Vector<AstDecl> nodes=new Vector<>()] : KEY_VAR id_0=ID SYM_COLON tp_0=type {
	$nodes.add(new AstVarDecl(location($id_0,$tp_0.ast),$id_0.getText(),$tp_0.ast));
} (SYM_COMMA id_1=ID SYM_COLON tp_1=type {
	$nodes.add(new AstVarDecl(location($id_1,$tp_1.ast),$id_1.getText(),$tp_1.ast));
})* SYM_SEMICOLON {
	$ast=new AstTrees("VarDeclarations",$nodes);
}; 

type returns [AstType ast] locals [Vector<AstCmpDecl> nodes=new Vector<>()] : KEY_VOID {
		$ast=new AstAtomType(location($KEY_VOID),AstAtomType.Type.VOID);
	} 
	| KEY_CHAR {
		$ast=new AstAtomType(location($KEY_CHAR),AstAtomType.Type.CHAR);
	}
	| KEY_INT {
		$ast=new AstAtomType(location($KEY_INT),AstAtomType.Type.INT);
	}
	| KEY_BOOL {
		$ast=new AstAtomType(location($KEY_BOOL),AstAtomType.Type.BOOL);
	}
	| ID {
		$ast=new AstNameType(location($ID), $ID.getText());
	}
	| SYM_SQBRACK_L expression SYM_SQBRACK_R type {
		$ast=new AstArrType(location($SYM_SQBRACK_L,$type.ast),$type.ast,$expression.ast);
	}
	| SYM_CRCMFLX type {
		$ast=new AstPtrType(location($SYM_CRCMFLX,$type.ast),$type.ast);
	}
	| SYM_CRBRACK_L id_0=ID SYM_COLON tp_0=type {
		$nodes.add(new AstCmpDecl(location($id_0,$tp_0.ast),$id_0.getText(),$tp_0.ast));
	} (SYM_COMMA id_1=ID SYM_COLON tp_1=type {
		$nodes.add(new AstCmpDecl(location($id_1,$tp_1.ast),$id_1.getText(),$tp_1.ast));
	})* SYM_CRBRACK_R {
		$ast=new AstRecType(location($SYM_CRBRACK_L,$SYM_CRBRACK_R),new AstTrees("ComponentDeclarations",$nodes));
	}
	| SYM_PARENTH_L type SYM_PARENTH_R {
		$ast=$type.ast;
		$ast.relocate(location($SYM_PARENTH_L,$SYM_PARENTH_R));
	};

expression returns [AstExpr ast] locals [Vector<AstExpr> nodes=new Vector<>(), boolean isCast=false,boolean isCall=false] : CONST_VOID {
				$ast=new AstAtomExpr(location($CONST_VOID),AstAtomExpr.Type.VOID,$CONST_VOID.getText());
			}
			| CONST_BOOL {
				$ast=new AstAtomExpr(location($CONST_BOOL),AstAtomExpr.Type.BOOL,$CONST_BOOL.getText());
			}
			| CONST_INT {
				$ast=new AstAtomExpr(location($CONST_INT),AstAtomExpr.Type.INT,$CONST_INT.getText());
			}
			| CONST_PTR {
				$ast=new AstAtomExpr(location($CONST_PTR),AstAtomExpr.Type.PTR,$CONST_PTR.getText());
			}
			| CONST_CHAR {
				$ast=new AstAtomExpr(location($CONST_CHAR),AstAtomExpr.Type.CHAR,$CONST_CHAR.getText());
			}
			| CONST_STR {
				$ast=new AstAtomExpr(location($CONST_STR),AstAtomExpr.Type.STR,$CONST_STR.getText());
			}
			
			| ID (SYM_PARENTH_L (ex_0=expression {
				$nodes.add($ex_0.ast);
			} (SYM_COMMA ex_1=expression {
				$nodes.add($ex_1.ast);
			})*)? SYM_PARENTH_R {
				$isCall=true;
			})? {
				if ($isCall) {
					$ast=new AstCallExpr(location($ID,$SYM_PARENTH_R),$ID.getText(),new AstTrees("Arguments",$nodes));
				}
				else {
					$ast=new AstNameExpr(location($ID),$ID.getText());
				}
			}
			
			| ex_0=expression (SYM_SQBRACK_L ex_1=expression SYM_SQBRACK_R {
				$ast=new AstArrExpr(location($ex_0.ast,$SYM_SQBRACK_R), $ex_0.ast, $ex_1.ast);
			} | SYM_CRCMFLX {
				$ast=new AstSfxExpr(location($ex_0.ast,$SYM_CRCMFLX), AstSfxExpr.Oper.PTR, $ex_0.ast);
			} | SYM_DOT ID {
				$ast=new AstRecExpr(location($ex_0.ast,$ID), $ex_0.ast, new AstNameExpr(location($ID), $ID.getText()));
			})
			
			| op_prefix expression {
				$ast=new AstPfxExpr(location($op_prefix.tk,$expression.ast), $op_prefix.oper, $expression.ast);
			}
			
			| ex_0=expression op_mult ex_1=expression {
				$ast=new AstBinExpr(location($ex_0.ast,$ex_1.ast),$op_mult.oper,$ex_0.ast,$ex_1.ast);
			}
			| ex_0=expression op_add ex_1=expression {
				$ast=new AstBinExpr(location($ex_0.ast,$ex_1.ast),$op_add.oper,$ex_0.ast,$ex_1.ast);			
			}
			| ex_0=expression op_rel ex_1=expression {
				$ast=new AstBinExpr(location($ex_0.ast,$ex_1.ast),$op_rel.oper,$ex_0.ast,$ex_1.ast);
			}
			| ex_0=expression op_conj ex_1=expression {
				$ast=new AstBinExpr(location($ex_0.ast,$ex_1.ast),$op_conj.oper,$ex_0.ast,$ex_1.ast);			
			}
			| ex_0=expression op_disj ex_1=expression {
				$ast=new AstBinExpr(location($ex_0.ast,$ex_1.ast),$op_disj.oper,$ex_0.ast,$ex_1.ast);
			}

			| SYM_PARENTH_L expression (SYM_COLON type {
				$isCast=true;
			})? SYM_PARENTH_R {
				if ($isCast) {
					$ast=new AstCastExpr(location($SYM_PARENTH_L,$SYM_PARENTH_R),$expression.ast,$type.ast);
				}
				else {
					$ast=$expression.ast;
					$ast.relocate(location($SYM_PARENTH_L,$SYM_PARENTH_R));
				}
			}
			| KEY_NEW SYM_PARENTH_L type SYM_PARENTH_R {
				$ast=new AstNewExpr(location($KEY_NEW,$SYM_PARENTH_R),$type.ast);
			}
			| KEY_DEL SYM_PARENTH_L expression SYM_PARENTH_R {
				$ast=new AstDelExpr(location($KEY_DEL,$SYM_PARENTH_R),$expression.ast);
			};

statement returns [AstStmt ast] locals [Vector<AstStmt> nodes=new Vector<>(),boolean isAssignment=false, AstStmt nullableStmt=null] : ex_0=expression (SYM_EQ ex_1=expression {
				$isAssignment=true;
			})? {
				if($isAssignment) {
					$ast=new AstAssignStmt(location($ex_0.ast,$ex_1.ast),$ex_0.ast,$ex_1.ast);
				}
				else {
					$ast=new AstExprStmt(location($ex_0.ast),$ex_0.ast);
				}
			} 
			| KEY_IF expression KEY_THEN st_0=statement (KEY_ELSE st_1=statement {
				$nullableStmt=$st_1.ast;
			})? {
				Location lc=$nullableStmt!=null?location($KEY_IF,$nullableStmt):location($KEY_IF,$st_0.ast);
				$ast=new AstIfStmt(lc, $expression.ast, $st_0.ast, $nullableStmt);
			}
			| KEY_WHILE expression KEY_DO statement {
				$ast=new AstWhileStmt(location($KEY_WHILE,$statement.ast),$expression.ast,$statement.ast);
			}
			| KEY_LET declarations KEY_IN statement {
				$ast=new AstDeclStmt(location($KEY_LET,$statement.ast),$declarations.ast,$statement.ast);
			}
			| SYM_CRBRACK_L st_0=statement {
				$nodes.add($st_0.ast);
			} (SYM_SEMICOLON st_1=statement {
				$nodes.add($st_1.ast);
			})* SYM_CRBRACK_R {
				$ast=new AstStmts(location($SYM_CRBRACK_L,$SYM_CRBRACK_R),new AstTrees("Statements",$nodes));
			};

grp_op_unary : SYM_EXCLM | SYM_PLUS | SYM_MINUS | SYM_CRCMFLX;

op_postfix: SYM_SQBRACK_L | SYM_SQBRACK_R | SYM_CRCMFLX | SYM_DOT; 

op_prefix returns [AstPfxExpr.Oper oper, Token tk] : SYM_EXCLM {
		$oper=AstPfxExpr.Oper.NOT;
		$tk=$SYM_EXCLM;
	} | SYM_PLUS {
		$oper=AstPfxExpr.Oper.ADD;
		$tk=$SYM_PLUS;
	} | SYM_MINUS {
		$oper=AstPfxExpr.Oper.SUB;
		$tk=$SYM_MINUS;	
	} | SYM_CRCMFLX {
		$oper=AstPfxExpr.Oper.PTR;
		$tk=$SYM_CRCMFLX;	
	};

op_mult returns [AstBinExpr.Oper oper, Token tk] : SYM_AST {
	$oper=AstBinExpr.Oper.MUL;
	$tk=$SYM_AST;	
} | SYM_FSLASH {
	$oper=AstBinExpr.Oper.DIV;
	$tk=$SYM_FSLASH;
} | SYM_PRCNT {
	$oper=AstBinExpr.Oper.MOD;
	$tk=$SYM_PRCNT;
}; 

op_add returns [AstBinExpr.Oper oper, Token tk] : SYM_PLUS {
	$oper=AstBinExpr.Oper.ADD;
	$tk=$SYM_PLUS;
} | SYM_MINUS {
	$oper=AstBinExpr.Oper.SUB;
	$tk=$SYM_MINUS;
};

op_rel returns [AstBinExpr.Oper oper, Token tk] : SYM_DBLEQ {
	$oper=AstBinExpr.Oper.EQU;
	$tk=$SYM_DBLEQ;
} | SYM_NEQ {
	$oper=AstBinExpr.Oper.NEQ;
	$tk=$SYM_NEQ;
} | SYM_LT {
	$oper=AstBinExpr.Oper.LTH;
	$tk=$SYM_LT;
} | SYM_GT {
	$oper=AstBinExpr.Oper.GTH;
	$tk=$SYM_GT;
} | SYM_LTE {
	$oper=AstBinExpr.Oper.LEQ;
	$tk=$SYM_LTE;
} | SYM_GTE {
	$oper=AstBinExpr.Oper.GEQ;
	$tk=$SYM_GTE;
};

op_conj returns [AstBinExpr.Oper oper, Token tk] : SYM_AMPERS {
	$oper=AstBinExpr.Oper.AND;
	$tk=$SYM_AMPERS;
};

op_disj returns [AstBinExpr.Oper oper, Token tk] : SYM_VPIPE {
	$oper=AstBinExpr.Oper.OR;
	$tk=$SYM_VPIPE;
};
