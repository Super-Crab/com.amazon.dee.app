package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
class OrNode extends SyntaxNode {
    SyntaxNode _left;
    SyntaxNode _right;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OrNode(SyntaxNode syntaxNode, SyntaxNode syntaxNode2) {
        this._left = syntaxNode;
        this._right = syntaxNode2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        return new OrNode(this._left._clone(iArr), this._right._clone(iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _firstPosition() {
        BitSet _firstPosition = this._left._firstPosition();
        BitSet _firstPosition2 = this._right._firstPosition();
        BitSet bitSet = new BitSet(Math.max(_firstPosition.size(), _firstPosition2.size()));
        bitSet.or(_firstPosition2);
        bitSet.or(_firstPosition);
        return bitSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr) {
        this._left._followPosition(bitSetArr, syntaxNodeArr);
        this._right._followPosition(bitSetArr, syntaxNodeArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _lastPosition() {
        BitSet _lastPosition = this._left._lastPosition();
        BitSet _lastPosition2 = this._right._lastPosition();
        BitSet bitSet = new BitSet(Math.max(_lastPosition.size(), _lastPosition2.size()));
        bitSet.or(_lastPosition2);
        bitSet.or(_lastPosition);
        return bitSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return this._left._nullable() || this._right._nullable();
    }
}
