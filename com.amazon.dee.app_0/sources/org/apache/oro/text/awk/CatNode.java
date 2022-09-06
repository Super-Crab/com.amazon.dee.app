package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
final class CatNode extends SyntaxNode {
    SyntaxNode _left;
    SyntaxNode _right;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        CatNode catNode = new CatNode();
        catNode._left = this._left._clone(iArr);
        catNode._right = this._right._clone(iArr);
        return catNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _firstPosition() {
        if (this._left._nullable()) {
            BitSet _firstPosition = this._left._firstPosition();
            BitSet _firstPosition2 = this._right._firstPosition();
            BitSet bitSet = new BitSet(Math.max(_firstPosition.size(), _firstPosition2.size()));
            bitSet.or(_firstPosition2);
            bitSet.or(_firstPosition);
            return bitSet;
        }
        return this._left._firstPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr) {
        this._left._followPosition(bitSetArr, syntaxNodeArr);
        this._right._followPosition(bitSetArr, syntaxNodeArr);
        BitSet _lastPosition = this._left._lastPosition();
        BitSet _firstPosition = this._right._firstPosition();
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
        if (this._right._nullable()) {
            BitSet _lastPosition = this._left._lastPosition();
            BitSet _lastPosition2 = this._right._lastPosition();
            BitSet bitSet = new BitSet(Math.max(_lastPosition.size(), _lastPosition2.size()));
            bitSet.or(_lastPosition2);
            bitSet.or(_lastPosition);
            return bitSet;
        }
        return this._right._lastPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return this._left._nullable() && this._right._nullable();
    }
}
