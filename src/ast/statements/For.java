package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class For extends AbstractStatement {

    private final Statement st1;
    private final Expression expr;
    private final Statement st2;
    private final List<Statement> body;

    public For(int line, int column, Expression expr, List<Statement> body, Statement st1, Statement st2) {
        super(line, column);
        this.expr = expr;
        this.body = body;
        this.st1 = st1;
        this.st2 = st2;
    }

    public Expression getExpr() { return expr;}
    public Statement getSt1() { return st1;}
    public Statement getSt2() { return st2;}
    public List<Statement> getBody() { return body; }

    @Override
    public String toString() {
       return "for (" + expr.toString() + ")" + "{\n" + body.toString() + "\n}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
