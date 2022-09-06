package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
class CharacterClassNode extends LeafNode {
    BitSet _characterSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacterClassNode(int i) {
        super(i);
        this._characterSet = new BitSet(257);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _addToken(int i) {
        this._characterSet.set(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _addTokenRange(int i, int i2) {
        while (i <= i2) {
            this._characterSet.set(i);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.SyntaxNode
    public SyntaxNode _clone(int[] iArr) {
        int i = iArr[0];
        iArr[0] = i + 1;
        CharacterClassNode characterClassNode = new CharacterClassNode(i);
        characterClassNode._characterSet = (BitSet) this._characterSet.clone();
        return characterClassNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.oro.text.awk.LeafNode
    public boolean _matches(char c) {
        return this._characterSet.get(c);
    }
}
