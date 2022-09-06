package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
final class CharStringPointer {
    static final char _END_OF_STRING = 65535;
    char[] _array;
    int _offset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharStringPointer(char[] cArr) {
        this(cArr, 0);
    }

    CharStringPointer(char[] cArr, int i) {
        this._array = cArr;
        this._offset = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _decrement() {
        return _decrement(1);
    }

    char _decrement(int i) {
        this._offset -= i;
        if (this._offset < 0) {
            this._offset = 0;
        }
        return this._array[this._offset];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int _getLength() {
        return this._array.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int _getOffset() {
        return this._offset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _getValue() {
        return _getValue(this._offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _getValue(int i) {
        char[] cArr = this._array;
        if (i >= cArr.length || i < 0) {
            return (char) 65535;
        }
        return cArr[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _getValueRelative(int i) {
        return _getValue(this._offset + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _increment() {
        return _increment(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _increment(int i) {
        this._offset += i;
        if (_isAtEnd()) {
            this._offset = this._array.length;
            return (char) 65535;
        }
        return this._array[this._offset];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _isAtEnd() {
        return this._offset >= this._array.length;
    }

    char _postDecrement() {
        char _getValue = _getValue();
        _decrement();
        return _getValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char _postIncrement() {
        char _getValue = _getValue();
        _increment();
        return _getValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setOffset(int i) {
        this._offset = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String _toString(int i) {
        char[] cArr = this._array;
        return new String(cArr, i, cArr.length - i);
    }

    public String toString() {
        return _toString(0);
    }
}
