package ast;

import ast.expressions.Expression;
import ast.statements.Statement;
import ast.types.Type;
import visitor.Visitor;

import java.util.List;

public class Case extends AbstractLocatable {
    private final Expression expression;
    private final List<Statement> statements;

    public Case(int line, int column, Expression expression, List<Statement> statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
    }

    public Expression getExpression() { return expression; }

    public List<Statement> getStatements() { return statements; }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("case" + expression.toString() + ":");
        for (Statement statement : statements) {
            str.append(statement.toString()).append('\n');
        }
        str.append("break");

        return str.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
