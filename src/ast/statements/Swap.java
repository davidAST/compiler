package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.beans.IndexedPropertyChangeEvent;

public class Swap extends AbstractStatement {
    // REPRESENTATION -> "print expression;"
    // Example -> "print a + 5;"

    private final Expression expression1;
    private final Expression expression2;

    public Swap(int line, int column, Expression expression1, Expression expression2) {
        super(line, column);
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public Expression getExpression1() {
        return expression1;
    }
    public Expression getExpression2() { return expression2; }

    @Override
    public String toString() {
        return expression1.toString() + "<=>" + expression2.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}