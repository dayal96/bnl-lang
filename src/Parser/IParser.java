package Parser;

import Expression.IExpression;

import java.util.List;

/**
 * Created by amogh on 6/26/17.
 */
public interface IParser {
    /**
     * Parse the code to get a list of {@link IExpression}.
     * @return the list of all expressions in input, parsed.
     */
    public List<IExpression> parseCode();
}
