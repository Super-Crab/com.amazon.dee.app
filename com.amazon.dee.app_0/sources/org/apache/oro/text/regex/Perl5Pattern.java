package org.apache.oro.text.regex;

import java.io.Serializable;
/* loaded from: classes4.dex */
public final class Perl5Pattern implements Pattern, Serializable, Cloneable {
    static final int _OPT_ANCH = 3;
    static final int _OPT_ANCH_BOL = 1;
    static final int _OPT_ANCH_MBOL = 2;
    static final int _OPT_IMPLICIT = 8;
    static final int _OPT_SKIP = 4;
    int _anchor;
    int _back;
    String _expression;
    boolean _isCaseInsensitive;
    boolean _isExpensive;
    int _minLength;
    char[] _mustString;
    int _mustUtility;
    int _numParentheses;
    int _options;
    char[] _program;
    int _startClassOffset;
    char[] _startString;

    @Override // org.apache.oro.text.regex.Pattern
    public int getOptions() {
        return this._options;
    }

    @Override // org.apache.oro.text.regex.Pattern
    public String getPattern() {
        return this._expression;
    }
}
