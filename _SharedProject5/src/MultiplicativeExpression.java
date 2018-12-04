import java.util.ArrayList;

public class MultiplicativeExpression implements CompoundExpression {

// Data
    CompoundExpression _parent;
    ArrayList<Expression> _children;
    String _data;

// Constructor
    public MultiplicativeExpression() {
        _parent = null;
        _children = new ArrayList<Expression>();
        _data = "*";
    }

// Methods

    /**
     * Adds the specified expression as a child.
     * @param subexpression the child expression to add
     */
    public void addSubexpression(Expression subexpression) {
        _children.add(subexpression);
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
        MultiplicativeExpression copy=new MultiplicativeExpression();
        for(Expression expression:_children){
            copy.addSubexpression(expression.deepCopy());
        }
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
        Expression childToBeRemoved=null;
        for(Expression child : _children) {
            child.flatten();
            childToBeRemoved=child;
            if(child instanceof MultiplicativeExpression) {           // if the child is a multiplicative expression
                for(Expression grandchild : ((MultiplicativeExpression) child).getChildren()) {
                    grandchild.setParent(this);    // set the parents of the grandchildren to this
                    _children.add(grandchild);     // add each of the grandchildren to the children of this
                }
                child.setParent(null);              // get rid of the parent of the redundant child
            }
        }
        if(childToBeRemoved!=null)                  // remove the redundant child from the children
            _children.remove(childToBeRemoved);

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
        stringBuilder.append("*");
        for(Expression expression: _children){
            expression.convertToString(stringBuilder,indentLevel+1);
        }
    }

    private ArrayList<Expression> getChildren(){
        return _children;
    }
}
