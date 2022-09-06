package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public final class PatternMatcherInput {
    int _beginOffset;
    int _currentOffset;
    int _endOffset;
    int _matchBeginOffset;
    int _matchEndOffset;
    char[] _originalBuffer;
    char[] _originalCharInput;
    String _originalStringInput;
    char[] _toLowerBuffer;

    public PatternMatcherInput(String str) {
        this(str, 0, str.length());
    }

    public PatternMatcherInput(String str, int i, int i2) {
        this._matchBeginOffset = -1;
        this._matchEndOffset = -1;
        setInput(str, i, i2);
    }

    public PatternMatcherInput(char[] cArr) {
        this(cArr, 0, cArr.length);
    }

    public PatternMatcherInput(char[] cArr, int i, int i2) {
        this._matchBeginOffset = -1;
        this._matchEndOffset = -1;
        setInput(cArr, i, i2);
    }

    public char charAt(int i) {
        return this._originalBuffer[this._beginOffset + i];
    }

    public boolean endOfInput() {
        return this._currentOffset >= this._endOffset;
    }

    public int getBeginOffset() {
        return this._beginOffset;
    }

    public char[] getBuffer() {
        return this._originalBuffer;
    }

    public int getCurrentOffset() {
        return this._currentOffset;
    }

    public int getEndOffset() {
        return this._endOffset;
    }

    public Object getInput() {
        String str = this._originalStringInput;
        return str == null ? this._originalCharInput : str;
    }

    public int getMatchBeginOffset() {
        return this._matchBeginOffset;
    }

    public int getMatchEndOffset() {
        return this._matchEndOffset;
    }

    public int length() {
        return this._endOffset - this._beginOffset;
    }

    public String match() {
        char[] cArr = this._originalBuffer;
        int i = this._matchBeginOffset;
        return new String(cArr, i, this._matchEndOffset - i);
    }

    public String postMatch() {
        char[] cArr = this._originalBuffer;
        int i = this._matchEndOffset;
        return new String(cArr, i, this._endOffset - i);
    }

    public String preMatch() {
        char[] cArr = this._originalBuffer;
        int i = this._beginOffset;
        return new String(cArr, i, this._matchBeginOffset - i);
    }

    public void setBeginOffset(int i) {
        this._beginOffset = i;
    }

    public void setCurrentOffset(int i) {
        this._currentOffset = i;
        setMatchOffsets(-1, -1);
    }

    public void setEndOffset(int i) {
        this._endOffset = i;
    }

    public void setInput(String str) {
        setInput(str, 0, str.length());
    }

    public void setInput(String str, int i, int i2) {
        this._originalStringInput = str;
        this._originalCharInput = null;
        this._toLowerBuffer = null;
        this._originalBuffer = str.toCharArray();
        setCurrentOffset(i);
        setBeginOffset(i);
        setEndOffset(this._beginOffset + i2);
    }

    public void setInput(char[] cArr) {
        setInput(cArr, 0, cArr.length);
    }

    public void setInput(char[] cArr, int i, int i2) {
        this._originalStringInput = null;
        this._toLowerBuffer = null;
        this._originalCharInput = cArr;
        this._originalBuffer = cArr;
        setCurrentOffset(i);
        setBeginOffset(i);
        setEndOffset(this._beginOffset + i2);
    }

    public void setMatchOffsets(int i, int i2) {
        this._matchBeginOffset = i;
        this._matchEndOffset = i2;
    }

    public String substring(int i) {
        int i2 = i + this._beginOffset;
        return new String(this._originalBuffer, i2, this._endOffset - i2);
    }

    public String substring(int i, int i2) {
        return new String(this._originalBuffer, this._beginOffset + i, i2 - i);
    }

    public String toString() {
        return new String(this._originalBuffer, this._beginOffset, length());
    }
}
