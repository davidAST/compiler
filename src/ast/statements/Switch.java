package ast.statements;

import ast.Case;
import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class Switch extends AbstractStatement {

    private final Expression expression;
    private final List<Case> cases;

    public Switch(int line, int column, Expression expression, List<Case> cases) {
        super(line, column);
        this.expression = expression;
        this.cases = cases;
    }

    public Expression getExpression() {
        return expression;
    }
    public List<Case> getCases() { return cases; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        cases.forEach(c -> sb.append(c.toString()));
        return "switch " + expression.toString() + sb.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}