import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.*;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("program2.txt");

        var lexer = new CPLangLexer(input);
        var tokenStream = new CommonTokenStream(lexer);

//        /* Print tokens */
//        tokenStream.fill();
//        List<Token> tokens = tokenStream.getTokens();
//        for (var token : tokens) {
//            var text = token.getText();
//            var type = CPLangLexer.VOCABULARY.getSymbolicName(token.getType());
//
//            System.out.println(text + " : " + type);
//        }

        var parser = new CPLangParser(tokenStream);
        var tree = parser.prog();
        // System.out.println(tree.toStringTree(parser));

        // Visitor-ul de mai jos parcurge arborele de derivare și construiește
        // un arbore de sintaxă abstractă (AST).
        var astConstructionVisitor = new CPLangParserBaseVisitor<ASTNode>() {
            @Override
            public ASTNode visitProg(CPLangParser.ProgContext ctx) {
                List<Expression> exprs = new ArrayList<>();

                for (CPLangParser.DefinitionContext d : ctx.definition()) {
                    exprs.add((Expression) visit(d));
                }

                for (CPLangParser.ExprContext e : ctx.expr()) {
                    exprs.add((Expression) visit(e));
                }

                return new AstProg(ctx.start, exprs);
            }

            @Override
            public ASTNode visitId(CPLangParser.IdContext ctx) {
                return new AstId(ctx.ID().getSymbol());
            }

            @Override
            public ASTNode visitInt(CPLangParser.IntContext ctx) {
                return new AstId(ctx.INT().getSymbol());
            }

            @Override
            public ASTNode visitBool(CPLangParser.BoolContext ctx) {
                return new AstId(ctx.BOOL().getSymbol());
            }

            @Override
            public ASTNode visitFloat(CPLangParser.FloatContext ctx) {
                return new AstId(ctx.FLOAT().getSymbol());
            }

            @Override
            public ASTNode visitIf(CPLangParser.IfContext ctx) {
                return new AstIf((Expression) visit(ctx.cond),
                              (Expression) visit(ctx.thenBranch),
                              (Expression) visit(ctx.elseBranch),
                              ctx.start);
            }

            @Override
            public ASTNode visitVarDef(CPLangParser.VarDefContext ctx) {
                if (ctx.init != null)
                    return new AstVarDef(ctx.type, ctx.name, (Expression) visit(ctx.init));
                else
                    return new AstVarDef(ctx.type, ctx.name);
            }

            @Override
            public ASTNode visitFuncDef(CPLangParser.FuncDefContext ctx) {
                List<Expression> formals = new ArrayList<>();

                if (ctx.formals == null) {
                    return new AstFuncDef(ctx.type, ctx.name, (Expression) visit(ctx.body));
                }

                for (CPLangParser.FormalContext e : ctx.formals) {
                    formals.add((Expression) visit(e));
                }

                return new AstFuncDef(ctx.type, ctx.name, (Expression) visit(ctx.body), formals);
            }

            @Override
            public ASTNode visitFormal(CPLangParser.FormalContext ctx) {
                return new AstFormal(ctx.type, ctx.name);
            }

            @Override
            public ASTNode visitCall(CPLangParser.CallContext ctx) {
                List<Expression> args = new ArrayList<>();

                if (ctx.args == null)
                    return new AstCall(ctx.name);
                else
                    for (CPLangParser.ExprContext e : ctx.args) {
                        args.add((Expression)visit(e));
                    }
                return new AstCall(ctx.name, args);
            }

            @Override
            public ASTNode visitParen(CPLangParser.ParenContext ctx) {
                return super.visitParen(ctx);
            }

            @Override
            public ASTNode visitPlusMinus(CPLangParser.PlusMinusContext ctx) {
                return new AstPlusMinus(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitMultDiv(CPLangParser.MultDivContext ctx) {
                return new AstMultDiv(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitUnaryMinus(CPLangParser.UnaryMinusContext ctx) {
                return new AstUnaryMinus(ctx.start, (Expression)visit(ctx.e));
            }

            @Override
            public ASTNode visitRelational(CPLangParser.RelationalContext ctx) {
                return new AstRelational(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitAssign(CPLangParser.AssignContext ctx) {
                return new AstAssign(ctx.name, (Expression)visit(ctx.e));
            }
        };

        // ast este AST-ul proaspăt construit pe baza arborelui de derivare.
        var ast = astConstructionVisitor.visit(tree);

        // Acest visitor parcurge AST-ul generat mai sus.
        // ATENȚIE! Avem de-a face cu două categorii de visitori:
        // (1) Cei pentru arborele de derivare, care extind
        //     CPLangParserBaseVisitor<T> și
        // (2) Cei pentru AST, care extind ASTVisitor<T>.
        // Aveți grijă să nu îi confundați!
        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            @Override
            public Void visit(AstProg astProg) {
                indent++;
                printIndent("PROGRAM");
                for(Expression e : astProg.exprs)
                    e.accept(this);
                indent--;

                return null;
            }

            @Override
            public Void visit(AstId astId) {
                printIndent("ID " + astId.value);
                return null;
            }

            @Override
            public Void visit(AstInt astInt) {
                printIndent("INT " + astInt.value);
                return null;
            }

            @Override
            public Void visit(AstIf astIF) {
                printIndent("IF");
                indent++;
                astIF.cond.accept(this);
                astIF.thenBranch.accept(this);
                astIF.elseBranch.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstFloat astFloat) {
                printIndent("FLOAT " + astFloat.value);
                return null;
            }

            @Override
            public Void visit(AstBool astBool) {
                printIndent("BOOL " + astBool.value);
                return null;
            }

            @Override
            public Void visit(AstVarDef astVarDef) {
                printIndent("VAR DEF");
                indent++;

                printIndent(astVarDef.type.getText());
                printIndent(astVarDef.name.getText());

                if (astVarDef.value != null) {
                    indent++;
                    astVarDef.value.accept(this);
                    indent--;
                }

                indent--;
                return null;
            }

            @Override
            public Void visit(AstCall call) {
                printIndent("CALL");

                printIndent(call.name.getText());
                if (call.args != null) {
                    for (Expression e : call.args) {
                        indent++;
                        e.accept(this);
                        indent--;
                    }
                }
                return null;
            }

            @Override
            public Void visit(AstUnaryMinus astUnaryMinus) {
                printIndent("UNARY MINUS");
                indent++;
                printIndent("-");
                astUnaryMinus.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstMultDiv astMultDiv) {
                printIndent(astMultDiv.op.getText());
                indent++;
                astMultDiv.left.accept(this);
                astMultDiv.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstPlusMinus astPlusMinus) {
                printIndent(astPlusMinus.op.getText());
                indent++;
                astPlusMinus.left.accept(this);
                astPlusMinus.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstRelational astRelational) {
                printIndent(astRelational.op.getText());
                indent++;
                astRelational.left.accept(this);
                astRelational.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstAssign astAssign) {
                printIndent("ASSIGN");
                indent++;
                printIndent(astAssign.id.getText());
                astAssign.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstFuncDef astFuncDef) {
                printIndent("FUNCTION");
                indent++;
                printIndent(astFuncDef.name.getText());
                indent++;
                if (astFuncDef.formals != null) {
                    for (Expression e : astFuncDef.formals) {
                        e.accept(this);
                    }
                }
                indent++;
                printIndent("FUNCTION BODY");
                astFuncDef.body.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(AstFormal astFormal) {
                printIndent("FORMAL");
                printIndent(astFormal.type.getText());
                printIndent(astFormal.id.getText());
                return null;
            }

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("\t");
                System.out.println(str);
            }
        };

        System.out.println("=== AST ===");
        ast.accept(printVisitor);
    }
}
