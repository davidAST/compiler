package ast.statements;

import ast.expressions.Expression;
import ast.expressions.Variable;
import visitor.Visitor;

import java.util.List;

public class ExpressionStatement extends AbstractStatement {

    private final Expression expression;

    public ExpressionStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
