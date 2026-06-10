package ast.expressions;

import ast.types.Type;
import visitor.Visitor;

public class Ternary extends AbstractExpression {

    private final Expression expression1;
    private final Expression expression2;
    private final Expression expression3;

    public Ternary(int line, int column, Expression expression1, Expression expression2, Expression expression3) {
        super(line, column);
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    public Expression getExpression1() { return expression1; }
    public Expression getExpression2() { return expression2; }
    public Expression getExpression3() { return expression3; }

    @Override
    public String toString() {
        return expression1 + " ? " + expression2 + " : " + expression3;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
