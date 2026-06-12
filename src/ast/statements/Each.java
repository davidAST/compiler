package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class Each extends AbstractStatement {
    private Expression array;
    private Expression param;
    private List<Statement> body;

    public Each(int line, int column, Expression array,
                   Expression param, List<Statement> body) {
        super(line, column);
        this.array = array;
        this.param = param;
        this.body = body;
    }

    public Expression getArray() { return array; }
    public Expression getParam() { return param; }
    public List<Statement> getBody() { return body; }


    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}