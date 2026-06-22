package ast.definitions;

import ast.expressions.Expression;
import ast.types.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition {

    private int offset;
    private final Expression expression;

    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
        expression = null;
    }

    public VarDefinition(int line, int column, String name, Type type, Expression expression) {
        super(line, column, name, type);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return getName() + ": " + getType().toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }

    public void setOffset(int offset) { this.offset = offset;}

    public int getOffset() {
        return offset;
    }
}
