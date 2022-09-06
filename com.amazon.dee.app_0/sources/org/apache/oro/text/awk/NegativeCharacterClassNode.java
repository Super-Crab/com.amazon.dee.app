package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
final class NegativeCharacterClassNode extends CharacterClassNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NegativeCharacterClassNode(int i) {
        super(i);
        this._characterSet.set(256);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.CharacterClassNode, org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        int i = iArr[0];
        iArr[0] = i + 1;
        NegativeCharacterClassNode negativeCharacterClassNode = new NegativeCharacterClassNode(i);
        negativeCharacterClassNode._characterSet = (BitSet) this._characterSet.clone();
        return negativeCharacterClassNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.CharacterClassNode, org.apache.oro.text.awk.LeafNode
    public boolean _matches(char c) {
        return !this._characterSet.get(c);
    }
}
