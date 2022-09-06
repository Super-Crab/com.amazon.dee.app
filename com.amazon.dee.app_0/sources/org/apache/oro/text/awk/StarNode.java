package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
class StarNode extends SyntaxNode {
    SyntaxNode _left;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StarNode(SyntaxNode syntaxNode) {
        this._left = syntaxNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        return new StarNode(this._left._clone(iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _firstPosition() {
        return this._left._firstPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr) {
        this._left._followPosition(bitSetArr, syntaxNodeArr);
        BitSet _lastPosition = _lastPosition();
        BitSet _firstPosition = _firstPosition();
        int size = _lastPosition.size();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                if (_lastPosition.get(i)) {
                    bitSetArr[i].or(_firstPosition);
                }
                size = i;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _lastPosition() {
        return this._left._lastPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return true;
    }
}
