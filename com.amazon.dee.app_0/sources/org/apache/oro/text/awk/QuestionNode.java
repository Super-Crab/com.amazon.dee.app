package org.apache.oro.text.awk;
/* loaded from: classes4.dex */
final class QuestionNode extends OrNode {
    static final SyntaxNode _epsilon = new EpsilonNode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuestionNode(SyntaxNode syntaxNode) {
        super(syntaxNode, _epsilon);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.OrNode, org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        return new QuestionNode(this._left._clone(iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.OrNode, org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return true;
    }
}
