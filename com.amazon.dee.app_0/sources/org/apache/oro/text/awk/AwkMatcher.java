package org.apache.oro.text.awk;

import java.io.IOException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
/* loaded from: classes4.dex */
public final class AwkMatcher implements PatternMatcher {
    private AwkPattern __awkPattern;
    private int __beginOffset;
    private int __lastMatchedBufferOffset;
    private AwkStreamInput __streamSearchBuffer;
    private AwkMatchResult __lastMatchResult = null;
    private int[] __offsets = new int[2];
    private AwkStreamInput __scratchBuffer = new AwkStreamInput();

    public AwkMatcher() {
        this.__scratchBuffer._endOfStreamReached = true;
    }

    private int __streamMatchPrefix() throws IOException {
        int i = this.__offsets[0];
        int i2 = this.__streamSearchBuffer._bufferSize + this.__beginOffset;
        int i3 = i;
        int i4 = 1;
        int i5 = -1;
        while (i < i2) {
            int i6 = i + 1;
            char c = this.__streamSearchBuffer._buffer[i];
            AwkPattern awkPattern = this.__awkPattern;
            if (i4 >= awkPattern._numStates) {
                break;
            }
            int[] _getStateArray = awkPattern._getStateArray(i4);
            int i7 = _getStateArray[c];
            if (i7 == 0) {
                this.__awkPattern._createNewState(i4, c, _getStateArray);
                i4 = _getStateArray[c];
            } else {
                i4 = i7;
            }
            if (i4 == -1) {
                break;
            }
            if (this.__awkPattern._endStates.get(i4)) {
                i5 = i6;
            }
            if (i6 == i2) {
                int _reallocate = this.__streamSearchBuffer._reallocate(i3);
                int i8 = this.__beginOffset;
                i = _reallocate + i8;
                i2 = i8 + this.__streamSearchBuffer._bufferSize;
                if (i != i2) {
                    if (i5 != -1) {
                        i5 -= i3;
                    }
                    i3 = 0;
                }
            } else {
                i = i6;
            }
        }
        int[] iArr = this.__offsets;
        iArr[0] = i3;
        iArr[1] = i5 - 1;
        if (i5 != -1 || !this.__awkPattern._matchesNullString) {
            if (this.__awkPattern._hasEndAnchor) {
                AwkStreamInput awkStreamInput = this.__streamSearchBuffer;
                if (!awkStreamInput._endOfStreamReached || i5 < awkStreamInput._bufferSize + this.__beginOffset) {
                    return -1;
                }
            }
            return i5 - i3;
        }
        return 0;
    }

    void _search() throws IOException {
        int __streamMatchPrefix;
        this.__lastMatchResult = null;
        while (true) {
            int i = this.__lastMatchedBufferOffset;
            AwkStreamInput awkStreamInput = this.__streamSearchBuffer;
            if (i >= awkStreamInput._bufferSize + this.__beginOffset) {
                if (awkStreamInput._endOfStreamReached) {
                    this.__streamSearchBuffer = null;
                    return;
                } else if (!awkStreamInput.read()) {
                    return;
                } else {
                    this.__lastMatchedBufferOffset = 0;
                }
            }
            int i2 = this.__lastMatchedBufferOffset;
            while (true) {
                AwkStreamInput awkStreamInput2 = this.__streamSearchBuffer;
                if (i2 < awkStreamInput2._bufferSize + this.__beginOffset) {
                    this.__offsets[0] = i2;
                    if (this.__awkPattern._fastMap[awkStreamInput2._buffer[i2]] && (__streamMatchPrefix = __streamMatchPrefix()) > -1) {
                        this.__lastMatchResult = new AwkMatchResult(new String(this.__streamSearchBuffer._buffer, this.__offsets[0], __streamMatchPrefix), this.__offsets[0]);
                        int[] iArr = this.__offsets;
                        this.__lastMatchedBufferOffset = (__streamMatchPrefix > 0 ? iArr[1] : iArr[0]) + 1;
                        return;
                    } else if (this.__awkPattern._matchesNullString) {
                        this.__lastMatchResult = new AwkMatchResult(new String(), i2);
                        this.__lastMatchedBufferOffset = i2 + 1;
                        return;
                    } else {
                        i2 = this.__offsets[0] + 1;
                    }
                }
            }
            this.__lastMatchedBufferOffset = i2;
        }
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean contains(String str, Pattern pattern) {
        return contains(str.toCharArray(), pattern);
    }

    public boolean contains(AwkStreamInput awkStreamInput, Pattern pattern) throws IOException {
        this.__awkPattern = (AwkPattern) pattern;
        if (this.__awkPattern._hasBeginAnchor) {
            if (awkStreamInput._bufferOffset != 0) {
                this.__lastMatchResult = null;
                return false;
            } else if (awkStreamInput.read() && !this.__awkPattern._fastMap[awkStreamInput._buffer[0]]) {
                this.__lastMatchResult = null;
                return false;
            }
        }
        this.__lastMatchedBufferOffset = awkStreamInput._currentOffset;
        this.__streamSearchBuffer = awkStreamInput;
        this.__beginOffset = 0;
        _search();
        awkStreamInput._currentOffset = this.__lastMatchedBufferOffset;
        AwkMatchResult awkMatchResult = this.__lastMatchResult;
        if (awkMatchResult != null) {
            awkMatchResult._incrementMatchBeginOffset(awkStreamInput._bufferOffset);
            return true;
        }
        return false;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean contains(PatternMatcherInput patternMatcherInput, Pattern pattern) {
        int i;
        this.__awkPattern = (AwkPattern) pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        awkStreamInput._bufferOffset = beginOffset;
        this.__lastMatchedBufferOffset = patternMatcherInput.getCurrentOffset();
        AwkPattern awkPattern = this.__awkPattern;
        if (awkPattern._hasBeginAnchor && ((i = this.__beginOffset) != this.__lastMatchedBufferOffset || !awkPattern._fastMap[this.__scratchBuffer._buffer[i]])) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        AwkStreamInput awkStreamInput2 = this.__scratchBuffer;
        awkStreamInput2._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput2;
        try {
            _search();
        } catch (IOException unused) {
        }
        patternMatcherInput.setCurrentOffset(this.__lastMatchedBufferOffset);
        AwkMatchResult awkMatchResult = this.__lastMatchResult;
        if (awkMatchResult == null) {
            return false;
        }
        patternMatcherInput.setMatchOffsets(awkMatchResult.beginOffset(0), this.__lastMatchResult.endOffset(0));
        return true;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean contains(char[] cArr, Pattern pattern) {
        this.__awkPattern = (AwkPattern) pattern;
        AwkPattern awkPattern = this.__awkPattern;
        if (awkPattern._hasBeginAnchor && !awkPattern._fastMap[cArr[0]]) {
            this.__lastMatchResult = null;
            return false;
        }
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        awkStreamInput._buffer = cArr;
        awkStreamInput._bufferSize = cArr.length;
        this.__beginOffset = 0;
        awkStreamInput._bufferOffset = 0;
        awkStreamInput._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput;
        this.__lastMatchedBufferOffset = 0;
        try {
            _search();
        } catch (IOException unused) {
        }
        return this.__lastMatchResult != null;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public MatchResult getMatch() {
        return this.__lastMatchResult;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matches(String str, Pattern pattern) {
        return matches(str.toCharArray(), pattern);
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matches(PatternMatcherInput patternMatcherInput, Pattern pattern) {
        int i;
        this.__awkPattern = (AwkPattern) pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        awkStreamInput._bufferOffset = beginOffset;
        this.__offsets[0] = patternMatcherInput.getBeginOffset();
        AwkStreamInput awkStreamInput2 = this.__scratchBuffer;
        awkStreamInput2._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput2;
        try {
            i = __streamMatchPrefix();
        } catch (IOException unused) {
            i = -1;
        }
        AwkStreamInput awkStreamInput3 = this.__scratchBuffer;
        int i2 = awkStreamInput3._bufferSize;
        if (i != i2) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(awkStreamInput3._buffer, this.__offsets[0], i2), this.__offsets[0]);
        return true;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matches(char[] cArr, Pattern pattern) {
        int i;
        this.__awkPattern = (AwkPattern) pattern;
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        awkStreamInput._buffer = cArr;
        awkStreamInput._bufferSize = cArr.length;
        this.__beginOffset = 0;
        awkStreamInput._bufferOffset = 0;
        awkStreamInput._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput;
        this.__offsets[0] = 0;
        try {
            i = __streamMatchPrefix();
        } catch (IOException unused) {
            i = -1;
        }
        if (i != cArr.length) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(cArr, 0, i), 0);
        return true;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matchesPrefix(String str, Pattern pattern) {
        return matchesPrefix(str.toCharArray(), pattern, 0);
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matchesPrefix(PatternMatcherInput patternMatcherInput, Pattern pattern) {
        int i;
        this.__awkPattern = (AwkPattern) pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        awkStreamInput._bufferOffset = beginOffset;
        this.__offsets[0] = patternMatcherInput.getCurrentOffset();
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        AwkStreamInput awkStreamInput2 = this.__scratchBuffer;
        awkStreamInput2._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput2;
        try {
            i = __streamMatchPrefix();
        } catch (IOException unused) {
            i = -1;
        }
        if (i < 0) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(this.__scratchBuffer._buffer, this.__offsets[0], i), this.__offsets[0]);
        return true;
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matchesPrefix(char[] cArr, Pattern pattern) {
        return matchesPrefix(cArr, pattern, 0);
    }

    @Override // org.apache.oro.text.regex.PatternMatcher
    public boolean matchesPrefix(char[] cArr, Pattern pattern, int i) {
        int i2;
        this.__awkPattern = (AwkPattern) pattern;
        AwkStreamInput awkStreamInput = this.__scratchBuffer;
        awkStreamInput._buffer = cArr;
        awkStreamInput._bufferSize = cArr.length;
        this.__beginOffset = 0;
        awkStreamInput._bufferOffset = 0;
        awkStreamInput._endOfStreamReached = true;
        this.__streamSearchBuffer = awkStreamInput;
        this.__offsets[0] = i;
        try {
            i2 = __streamMatchPrefix();
        } catch (IOException unused) {
            i2 = -1;
        }
        if (i2 < 0) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(cArr, 0, i2), i);
        return true;
    }
}
