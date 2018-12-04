public class ParentheticalExpression implements CompoundExpression {

// Data
    CompoundExpression _parent;
    Expression _child;

// Constructor

// Methods

    /**
     * Adds the specified expression as a child.
     * @param subexpression the child expression to add
     */
    public void addSubexpression(Expression subexpression) {
        _child=subexpression;
    }

    /**
     * Returns the expression's parent.
     * @return the expression's parent
     */
    public CompoundExpression getParent() {
        return _parent;
    }

    /**
     * Sets the parent be the specified expression.
     * @param parent the CompoundExpression that should be the parent of the target object
     */
    public void setParent(CompoundExpression parent) {
        _parent = parent;
    }

    /**
     * Creates and returns a deep copy of the expression.
     * The entire tree rooted at the target node is copied, i.e.,
     * the copied Expression is as deep as possible.
     * @return the deep copy
     */
    public Expression deepCopy() {
        ParentheticalExpression copy=new ParentheticalExpression();
        copy.addSubexpression(_child.deepCopy());
        return copy;
    }

    /**
     * Recursively flattens the expression as much as possible
     * throughout the entire tree. Specifically, in every multiplicative
     * or additive expression x whose first or last
     * child c is of the same type as x, the children of c will be added to x, and
     * c itself will be removed. This method modifies the expression itself.
     */
    public void flatten() {
       _child.flatten();
    }

    /**
     * Creates a String representation by recursively printing out (using indentation) the
     * tree represented by this expression, starting at the specified indentation level.
     * @param stringBuilder the StringBuilder to use for building the String representation
     * @param indentLevel the indentation level (number of tabs from the left margin) at which to start
     */
     public void convertToString(StringBuilder stringBuilder, int indentLevel) {
         stringBuilder.append("\n");
         for(int i=0;i<indentLevel;i++){
             stringBuilder.append("\t");
         }
         stringBuilder.append("()");
         _child.convertToString(stringBuilder,indentLevel+1);
     }

}