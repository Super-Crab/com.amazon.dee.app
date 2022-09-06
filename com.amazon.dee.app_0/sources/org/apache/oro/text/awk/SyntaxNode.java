package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
abstract class SyntaxNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SyntaxNode _clone(int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BitSet _firstPosition();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void _followPosition(BitSet[] bitSetArr, SyntaxNode[] syntaxNodeArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BitSet _lastPosition();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean _nullable();
}
