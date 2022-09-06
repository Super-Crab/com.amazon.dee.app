package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
abstract class LeafNode extends SyntaxNode {
    static final int _END_MARKER_TOKEN = 256;
    static final int _NUM_TOKENS = 256;
    protected int _position;
    protected BitSet _positionSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LeafNode(int i) {
        this._position = i;
        this._positionSet = new BitSet(i + 1);
        this._positionSet.set(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public final BitSet _firstPosition() {
        return this._positionSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public final void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr) {
        syntaxNodeArr[this._position] = this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public final BitSet _lastPosition() {
        return this._positionSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean _matches(char c);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public final boolean _nullable() {
        return false;
    }
}
