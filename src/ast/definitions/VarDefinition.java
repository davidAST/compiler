package ast.definitions;

import ast.expressions.Expression;
import ast.types.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class VarDefinition extends AbstractDefinition {

    private int offset;
    private List<Expression> expressions = new ArrayList<>();

    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    public VarDefinition(int line, int column, String name, Type type, List<Expression> expressions) {
        super(line, column, name, type);
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() { return expressions; }


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
