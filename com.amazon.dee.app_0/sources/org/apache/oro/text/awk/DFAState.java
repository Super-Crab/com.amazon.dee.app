package org.apache.oro.text.awk;

import java.util.BitSet;
/* loaded from: classes4.dex */
final class DFAState {
    BitSet _state;
    int _stateNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DFAState(BitSet bitSet, int i) {
        this._state = bitSet;
        this._stateNumber = i;
    }
}
