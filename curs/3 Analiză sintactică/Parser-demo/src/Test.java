import java.io.IOException;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("program.txt");
        
        var lexer = new CPLangLexer(input);
        var tokenStream = new CommonTokenStream(lexer);
       
        /*
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        for (var token : tokens) {
            var text = token.getText();
            var type = CPLangLexer.VOCABULARY.getSymbolicName(token.getType());
            
            System.out.println(text + " : " + type);
        }
        */
        
        var parser = new CPLangParser(tokenStream);
        var tree = parser.expr();
        System.out.println(tree.toStringTree(parser));
        
        // Interfața CPLangParserListener conține, pentru fiecare alternativă
        // etichetată, câte o pereche de metode enter/exit. Spre exemplu,
        // pentru eticheta if, avem perechea de metode enterIf(IfContext)
        // și exitIf(IfContext). Clasa CPLangParserBaseListener oferă
        // implementări goale ale acestor metode, astfel încât noi să putem
        // supradefini doar metodele de interes.
        //
        // Listenerii au avantajul că parcurgerea arborelui de derivare este
        // realizată automat, pe baza unui walker, ca mai jos. Dezavantajul
        // constă în faptul că este parcurs întregul arbore de derivare, chiar
        // dacă pe noi ne intesează doar anumite noduri particulare.
        var listener = new CPLangParserBaseListener() {
            @Override
            public void exitInt(CPLangParser.IntContext ctx) {
                // Afișăm fiecare literal întreg, când îl întâlnim.
                System.out.println("Found integer literal: " + ctx.INT());
            }
        };
        
        // Un walker realizează o parcurgere în adâncime a arborelui de
        // derivare, invocând la momentul potrivit metodele enter/exit.
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        
        // Interfața CPLangParserVisitor<T> conține câte o metodă pentru fiecare
        // alternativă etichetată. Spre exemplu, pentru eticheta if, avem 
        // metoda T visitIf(IfContext). Clasa CPLangParserBaseVisitor<T> oferă
        // implementări implicite ale acestor metode, astfel încât noi să putem
        // supradefini doar metodele de interes.
        //
        // De remarcat că, spre deosebire de listeneri, metodele de vizitare
        // pot întoarce și o valoare utilă, care poate fi prelucrată recursiv.
        // Acest lucru, alături de faptul că putem vizita doar nodurile de
        // interes pentru noi, constituie avantajul vizitatorilor. Dezavantajul
        // constă tocmai în faptul că e necesară vizitarea explicită a copiilor,
        // mai ales când trebuie să parcurgem întregul arbore.
        //
        // Vizitatorul de mai jos extrage recursiv toate numele de variabilele
        // dintr-o expresie, sub forma unei liste de String-uri.
        var visitor = new CPLangParserBaseVisitor<List<String>>() {
            @Override
            public List<String> visitId(CPLangParser.IdContext ctx) {
                return Arrays.asList(ctx.ID().toString());
            }
            
            @Override
            public List<String> visitInt(CPLangParser.IntContext ctx) {
                // Un întreg nu conține variabile.
                return Arrays.asList();
            }
            
            @Override
            public List<String> visitIf(CPLangParser.IfContext ctx) {
                List<String> list = new LinkedList<String>();
                
                // Vizităm explicit copiii și adăugăm conținutul listelor lor
                // la lista curentă.
                for (var expr : ctx.expr()) {
                    list.addAll(visit(expr));
                }
                
                // Alternativ, putem vizita explicit copiii, prin numele lor.
                /*
                list.addAll(visit(ctx.cond));
                list.addAll(visit(ctx.thenBranch));
                list.addAll(visit(ctx.elseBranch));  
                */
                
                return list;
            }
        };
        
        System.out.println("Variable names: " + visitor.visit(tree));
        
        // Visitor-ul de mai jos parcurge arborele de derivare și construiește
        // un arbore de sintaxă abstractă (AST).
        var astConstructionVisitor = new CPLangParserBaseVisitor<ASTNode>() {
            @Override
            public ASTNode visitId(CPLangParser.IdContext ctx) {
                return new Id(ctx.ID().getSymbol());
            }
            
            @Override
            public ASTNode visitInt(CPLangParser.IntContext ctx) {
                return new Int(ctx.INT().getSymbol());
            }
            
            @Override
            public ASTNode visitIf(CPLangParser.IfContext ctx) {
                return new If((Expression)visit(ctx.cond),
                              (Expression)visit(ctx.thenBranch),
                              (Expression)visit(ctx.elseBranch),
                              ctx.start);
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
            public Void visit(Id id) {
                printIndent("ID " + id.token.getText());
                return null;
            }

            @Override
            public Void visit(Int intt) {
                printIndent("INT " + intt.token.getText());
                return null;
            }

            @Override
            public Void visit(If iff) {
                printIndent("IF");
                indent++;
                iff.cond.accept(this);
                iff.thenBranch.accept(this);
                iff.elseBranch.accept(this);
                indent--;
                return null;
            }
            
            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("\t");
                System.out.println(str);
            }
        };
        
        System.out.println("The AST is");
        ast.accept(printVisitor);
    }
   

}
