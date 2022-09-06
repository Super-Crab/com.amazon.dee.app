package org.apache.oro.text.awk;

import org.apache.oro.text.regex.MatchResult;
/* loaded from: classes4.dex */
final class AwkMatchResult implements MatchResult {
    private int __length;
    private String __match;
    private int __matchBeginOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwkMatchResult(String str, int i) {
        this.__match = str;
        this.__length = str.length();
        this.__matchBeginOffset = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _incrementMatchBeginOffset(int i) {
        this.__matchBeginOffset += i;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int begin(int i) {
        return i == 0 ? 0 : -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int beginOffset(int i) {
        if (i == 0) {
            return this.__matchBeginOffset;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int end(int i) {
        if (i == 0) {
            return this.__length;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int endOffset(int i) {
        if (i == 0) {
            return this.__matchBeginOffset + this.__length;
        }
        return -1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public String group(int i) {
        if (i == 0) {
            return this.__match;
        }
        return null;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int groups() {
        return 1;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public int length() {
        return this.__length;
    }

    @Override // org.apache.oro.text.regex.MatchResult
    public String toString() {
        return group(0);
    }
}
