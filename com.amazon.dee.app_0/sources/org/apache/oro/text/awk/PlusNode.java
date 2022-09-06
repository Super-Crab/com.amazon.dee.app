package org.apache.oro.text.awk;
/* loaded from: classes4.dex */
final class PlusNode extends StarNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PlusNode(SyntaxNode syntaxNode) {
        super(syntaxNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.StarNode, org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        return new PlusNode(this._left._clone(iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.StarNode, org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return false;
    }
}
