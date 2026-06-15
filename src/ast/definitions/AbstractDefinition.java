package ast.definitions;

import ast.AbstractLocatable;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {

    private Type type;
    private final String name;
    private int scope;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) { this.type = type; }

    public String getName() {
        return name;
    }

    public int getScope() { return scope;}

    public void setScope(int scope) { this.scope = scope;}
}
