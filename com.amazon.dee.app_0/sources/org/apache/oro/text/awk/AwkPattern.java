package org.apache.oro.text.awk;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.oro.text.regex.Pattern;
/* loaded from: classes4.dex */
public final class AwkPattern implements Pattern, Serializable {
    static final int _INVALID_STATE = -1;
    static final int _START_STATE = 1;
    BitSet _U;
    BitSet _emptySet;
    int _endPosition;
    String _expression;
    boolean[] _fastMap;
    BitSet[] _followSet;
    boolean _matchesNullString;
    Vector[] _nodeList;
    int _numStates;
    int _options;
    Hashtable _stateMap;
    boolean _hasBeginAnchor = false;
    boolean _hasEndAnchor = false;
    Vector _Dtrans = new Vector();
    Vector _stateList = new Vector();
    BitSet _endStates = new BitSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwkPattern(String str, SyntaxTree syntaxTree) {
        this._expression = str;
        this._endPosition = syntaxTree._positions - 1;
        this._followSet = syntaxTree._followSet;
        this._U = new BitSet(syntaxTree._positions);
        this._U.or(syntaxTree._root._firstPosition());
        int[] iArr = new int[256];
        this._Dtrans.addElement(iArr);
        this._Dtrans.addElement(iArr);
        this._numStates = 1;
        if (this._U.get(this._endPosition)) {
            this._endStates.set(this._numStates);
        }
        DFAState dFAState = new DFAState((BitSet) this._U.clone(), this._numStates);
        this._stateMap = new Hashtable();
        this._stateMap.put(dFAState._state, dFAState);
        this._stateList.addElement(dFAState);
        this._stateList.addElement(dFAState);
        this._numStates++;
        BitSet bitSet = this._U;
        bitSet.xor(bitSet);
        this._emptySet = new BitSet(syntaxTree._positions);
        this._nodeList = new Vector[256];
        for (int i = 0; i < 256; i++) {
            this._nodeList[i] = new Vector();
            for (int i2 = 0; i2 < syntaxTree._positions; i2++) {
                if (syntaxTree._nodes[i2]._matches((char) i)) {
                    this._nodeList[i].addElement(syntaxTree._nodes[i2]);
                }
            }
        }
        this._fastMap = syntaxTree.createFastMap();
        this._matchesNullString = this._endStates.get(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _createNewState(int i, int i2, int[] iArr) {
        DFAState dFAState = (DFAState) this._stateList.elementAt(i);
        int size = this._nodeList[i2].size();
        BitSet bitSet = this._U;
        bitSet.xor(bitSet);
        while (true) {
            int i3 = size - 1;
            if (size <= 0) {
                break;
            }
            int i4 = ((LeafNode) this._nodeList[i2].elementAt(i3))._position;
            if (dFAState._state.get(i4)) {
                this._U.or(this._followSet[i4]);
            }
            size = i3;
        }
        if (this._stateMap.containsKey(this._U)) {
            if (this._U.equals(this._emptySet)) {
                iArr[i2] = -1;
                return;
            } else {
                iArr[i2] = ((DFAState) this._stateMap.get(this._U))._stateNumber;
                return;
            }
        }
        int i5 = this._numStates;
        this._numStates = i5 + 1;
        DFAState dFAState2 = new DFAState((BitSet) this._U.clone(), i5);
        this._stateList.addElement(dFAState2);
        this._stateMap.put(dFAState2._state, dFAState2);
        this._Dtrans.addElement(new int[256]);
        if (this._U.equals(this._emptySet)) {
            iArr[i2] = -1;
            return;
        }
        iArr[i2] = this._numStates - 1;
        if (!this._U.get(this._endPosition)) {
            return;
        }
        this._endStates.set(this._numStates - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] _getStateArray(int i) {
        return (int[]) this._Dtrans.elementAt(i);
    }

    @Override // org.apache.oro.text.regex.Pattern
    public int getOptions() {
        return this._options;
    }

    @Override // org.apache.oro.text.regex.Pattern
    public String getPattern() {
        return this._expression;
    }
}
