package ast.expressions;

import visitor.Visitor;

public class Contains extends AbstractExpression {

    private final Expression array;
    private final Expression element;

    public Contains(int line, int column, Expression array, Expression element) {
        super(line, column);
        this.array = array;
        this.element = element;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getElement() {
        return element;
    }

    @Override
    public String toString() {
        return array + ".contains(" + element + ")";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
