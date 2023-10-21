import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ExpressionParser {
    public static void main(String[] args) {
        String input = "1+2*4-(3*2)";

//        ExprLexer exprLexer = new ExprLexer(CharStreams.fromFileName("input.txt"));
        ExprLexer exprLexer = new ExprLexer(CharStreams.fromString(input));

        ExprParser exprParser = new ExprParser(new CommonTokenStream(exprLexer));

        ExprParser.ExprContext expr = exprParser.expr();
        System.out.println(toString(expr) + " = " + evaluate(expr));
    }

    private static String toString(ExprParser.ExprContext e) {
        if (e.MINUS() != null) {
            return toString(e.expr()) + " - " + toString(e.term());
        }
        if (e.PLUS() != null) {
            return toString(e.expr()) + " + " +  toString(e.term());
        }

        return toString(e.term());

    }

    private static String toString(ExprParser.TermContext t) {
        if (t.MUL() != null) {
            return toString(t.term()) + " * " + toString(t.fact());
        }
        if (t.DIV() != null) {
            return toString(t.term()) + " / " + toString(t.fact());
        }
        return toString(t.fact());
    }

    private static String toString(ExprParser.FactContext f) {
        if (f.INT() != null) {
            return f.INT().getText();
        }
        return "(" + toString(f.expr()) + ")";
    }

    private static int evaluate(ExprParser.ExprContext e) {
        if (e.MINUS() != null) {
            return evaluate(e.expr()) - evaluate(e.term());
        }
        if (e.PLUS() != null) {
            return evaluate(e.expr()) + evaluate(e.term());
        }

        return evaluate(e.term());
    }

    private static int evaluate(ExprParser.TermContext t) {
        if (t.MUL() != null) {
            return evaluate(t.term()) * evaluate(t.fact());
        }
        if (t.DIV() != null) {
            return evaluate(t.term()) / evaluate(t.fact());
        }
        return evaluate(t.fact());
    }

    private static int evaluate(ExprParser.FactContext f) {
        if (f.INT() != null) {
            return Integer.parseInt(f.INT().getText());
        }
        return evaluate(f.expr());
    }
}
