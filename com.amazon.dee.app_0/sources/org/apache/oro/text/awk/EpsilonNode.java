package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
final class EpsilonNode extends SyntaxNode {
    BitSet _positionSet = new BitSet(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        return new EpsilonNode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _firstPosition() {
        return this._positionSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public BitSet _lastPosition() {
        return this._positionSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public boolean _nullable() {
        return true;
    }
}
