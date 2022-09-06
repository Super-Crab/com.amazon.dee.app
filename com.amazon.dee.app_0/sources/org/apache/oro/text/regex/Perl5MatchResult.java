package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
final class Perl5MatchResult implements MatchResult {
    int[] _beginGroupOffset;
    int[] _endGroupOffset;
    String _match;
    int _matchBeginOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Perl5MatchResult(int i) {
        this._beginGroupOffset = new int[i];
        this._endGroupOffset = new int[i];
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int begin(int i) {
        int[] iArr = this._beginGroupOffset;
        if (i < iArr.length) {
            int i2 = iArr[i];
            int i3 = this._endGroupOffset[i];
            if (i2 >= 0 && i3 >= 0) {
                return i2;
            }
            return -1;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int beginOffset(int i) {
        int[] iArr = this._beginGroupOffset;
        if (i < iArr.length) {
            int i2 = iArr[i];
            int i3 = this._endGroupOffset[i];
            if (i2 >= 0 && i3 >= 0) {
                return this._matchBeginOffset + i2;
            }
            return -1;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int end(int i) {
        int[] iArr = this._beginGroupOffset;
        if (i < iArr.length) {
            int i2 = iArr[i];
            int i3 = this._endGroupOffset[i];
            if (i2 >= 0 && i3 >= 0) {
                return i3;
            }
            return -1;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int endOffset(int i) {
        int[] iArr = this._endGroupOffset;
        if (i < iArr.length) {
            int i2 = this._beginGroupOffset[i];
            int i3 = iArr[i];
            if (i2 >= 0 && i3 >= 0) {
                return this._matchBeginOffset + i3;
            }
            return -1;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public String group(int i) {
        int[] iArr = this._beginGroupOffset;
        if (i < iArr.length) {
            int i2 = iArr[i];
            int i3 = this._endGroupOffset[i];
            int length = this._match.length();
            if (i2 < 0 || i3 < 0) {
                return null;
            }
            if (i2 < length && i3 <= length && i3 > i2) {
                return this._match.substring(i2, i3);
            }
            if (i2 > i3) {
                return null;
            }
            return "";
        }
        return null;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int groups() {
        return this._beginGroupOffset.length;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int length() {
        int i = this._endGroupOffset[0] - this._beginGroupOffset[0];
        if (i > 0) {
            return i;
        }
        return 0;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public String toString() {
        return group(0);
    }
}
