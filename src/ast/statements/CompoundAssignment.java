package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

public class CompoundAssignment extends AbstractStatement {

    private final Expression left;
    private final Expression right;
    private final String operator;

    public CompoundAssignment(int line, int column, Expression left, Expression right, String operator) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() { return operator; }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " = " + right.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
