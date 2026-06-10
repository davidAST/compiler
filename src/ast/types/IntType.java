package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import codegenerator.CodeGenerator;
import visitor.Visitor;

public class IntType extends AbstractType {
    // REPRESENTATION -> "int"

    private static IntType instance;

    private IntType() {

    }

    public static IntType getInstance() {
        if (instance == null) {
            instance = new IntType();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "IntType";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {}

    @Override
    public void mustBeLogical(Locatable locatable) {}

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        if (type == CharType.getInstance()) {
            return IntType.getInstance();
        } else if (type == this || type == RealType.getInstance() || (type instanceof ErrorType)) {
            return type;
        } else {
            String message = "Incompatible types: " + this.toString() + " and " + type.toString();
            return new ErrorType(message, locatable);
        }
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type modulus(Type type, Locatable locatable) {
        if (type == CharType.getInstance() || type == this) {
            return this;
        } else if (type instanceof ErrorType) {
            return type;
        } else {
            String message = "Incompatible types: " + this.toString() + " and " + type.toString();
            return new ErrorType(message, locatable);
        }
    }

    @Override
    public Type logic(Type type, Locatable locatable) {
        if (type == CharType.getInstance()
                || type == this) {
            return IntType.getInstance();
        } else if (type instanceof ErrorType) {
            return type;
        }
        String message = "Incompatible types: " + this.toString() + " and " + type.toString();
        return new ErrorType(message, locatable);
    }

    @Override
    public Type logic(Locatable locatable) {
        return IntType.getInstance();
    }

    @Override
    public void mustPromoteTo(Type type, Locatable locatable) {
        if (type != this && type != RealType.getInstance() && !(type instanceof ErrorType)) {
            new ErrorType("Type '" + this + "' cannot be promoted to '" + type + "'", locatable);
        }
    }

    @Override
    public Type canBeCastTo(Type type, Locatable locatable) {
        if (type == CharType.getInstance()
                || type == this
                || type == RealType.getInstance()
                || (type instanceof ErrorType)) {
            return type;
        } else {
            return new ErrorType("Cannot cast '" + this + "' to '" + type + "'", locatable);
        }
    }

    @Override
    public Type comparison(Type type, Locatable locatable) {
        if (type == CharType.getInstance()
                || type == this
                || type == RealType.getInstance()) {
            return IntType.getInstance();
        } else if (type instanceof ErrorType) {
            return type;
        } else {
            return new ErrorType("Incompatible types in comparison: '" + this + "' and '" + type + "'", locatable);
        }
    }

    @Override
    public Type ternary(Type type1, Type type2, Locatable locatable) {
        if (type1 instanceof ErrorType) return type1;
        if (type2 instanceof ErrorType) return type2;

        type1.mustBeBuiltIn(locatable);
        type2.mustBeBuiltIn(locatable);
        if (type1 != type2) {
            return new ErrorType("Ternary operator types: " + type1 + " and " + type2 + " are not the same", locatable);
        }
        return type2;
    }

    @Override
    public int numberOfBytes() {
        return 2;
    }

    @Override
    public char suffix() {
        return 'i';
    }

    @Override
    public void convertTo(CodeGenerator cg, Type other) {
        if (other instanceof CharType) {
            cg.i2b();
        } else if (other instanceof RealType) {
            cg.i2f();
        } else if (!(other instanceof IntType)){
            throw new RuntimeException("Invalid cast from " + this + " to " + other);
        }
    }
}