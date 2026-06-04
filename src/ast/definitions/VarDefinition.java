package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {

    private int offset;

    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
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
