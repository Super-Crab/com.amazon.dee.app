package org.apache.oro.text.awk;
/* loaded from: classes4.dex */
class TokenNode extends LeafNode {
    char _token;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenNode(char c, int i) {
        super(i);
        this._token = c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        char c = this._token;
        int i = iArr[0];
        iArr[0] = i + 1;
        return new TokenNode(c, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.LeafNode
    public boolean _matches(char c) {
        return this._token == c;
    }
}
