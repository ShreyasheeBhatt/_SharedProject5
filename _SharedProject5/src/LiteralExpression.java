public class LiteralExpression implements Expression {

// Data
    private CompoundExpression _parent;
    private String _data;

    /**
     * Constructor for a LiteralExpression
     * @param s the data which the LiteralExpression contains
     */
    public LiteralExpression(String s){
        _parent=null;
        _data = s;
    }

    /**
     * returns the current parents for the literal expression
     * @return the parent of the literal expression
     */
    public CompoundExpression getParent() {
        return _parent;
    }

    /**
     * Sets a new parent for the literal expression
     * @param parent the new parent for the literal expression
     */
    public void setParent(CompoundExpression parent) {
        _parent = parent;
    }

    /**
     *
     * @return
     */
    public Expression deepCopy() {
        return null;
    }

    /**
     *
     */
    public void flatten() {
        //flattening a literal expression does nothing
    }

    /**
     *
     * @param stringBuilder
     * @param indentLevel
     */
    public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        stringBuilder.append("\n");
        for(int i=0;i<indentLevel;i++){
            stringBuilder.append("\t");
        }
        stringBuilder.append(_data);
    }
}
