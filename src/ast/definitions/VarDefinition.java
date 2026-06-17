package ast.definitions;

import ast.types.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition {

    private int offset;
    private boolean reference;

    public VarDefinition(int line, int column, String name, Type type, boolean reference) {
        super(line, column, name, type);
        this.reference = reference;
    }

    public boolean isReference() {
        return reference;
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
