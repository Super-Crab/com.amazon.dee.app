package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public class StringSubstitution implements Substitution {
    int _subLength;
    String _substitution;

    public StringSubstitution() {
        this("");
    }

    public StringSubstitution(String str) {
        setSubstitution(str);
    }

    @Override // org.apache.oro.text.regex.Substitution
    public void appendSubstitution(StringBuffer stringBuffer, MatchResult matchResult, int i, PatternMatcherInput patternMatcherInput, PatternMatcher patternMatcher, Pattern pattern) {
        if (this._subLength == 0) {
            return;
        }
        stringBuffer.append(this._substitution);
    }

    public String getSubstitution() {
        return this._substitution;
    }

    public void setSubstitution(String str) {
        this._substitution = str;
        this._subLength = str.length();
    }

    public String toString() {
        return getSubstitution();
    }
}
