package ast.types;

import visitor.Visitor;

public class VarType extends AbstractType {
    private static VarType instance;

    private VarType() {}

    public static VarType getInstance() {
        if (instance == null)
            instance = new VarType();
        return instance;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }

    @Override
    public String toString() {
        return "VarType";
    }

    @Override
    public int numberOfBytes() {
        throw new IllegalStateException("VarType has no size: type has not been inferred yet");
    }
}