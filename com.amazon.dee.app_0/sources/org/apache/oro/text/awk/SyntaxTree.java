package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
final class SyntaxTree {
    BitSet[] _followSet;
    LeafNode[] _nodes;
    int _positions;
    SyntaxNode _root;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SyntaxTree(SyntaxNode syntaxNode, int i) {
        this._root = syntaxNode;
        this._positions = i;
    }

    private void __addToFastMap(BitSet bitSet, boolean[] zArr, boolean[] zArr2) {
        for (int i = 0; i < this._positions; i++) {
            if (bitSet.get(i) && !zArr2[i]) {
                zArr2[i] = true;
                for (int i2 = 0; i2 < 256; i2++) {
                    if (!zArr[i2]) {
                        zArr[i2] = this._nodes[i]._matches((char) i2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _computeFollowPositions() {
        int i = this._positions;
        this._followSet = new BitSet[i];
        this._nodes = new LeafNode[i];
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                this._root._followPosition(this._followSet, this._nodes);
                return;
            } else {
                this._followSet[i2] = new BitSet(this._positions);
                i = i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean[] createFastMap() {
        boolean[] zArr = new boolean[256];
        __addToFastMap(this._root._firstPosition(), zArr, new boolean[this._positions]);
        return zArr;
    }
}
