package com.amazon.identity.kcpsdk.auth;

import com.amazon.identity.kcpsdk.common.ParseError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ParseErrorException extends Exception {
    private static final long serialVersionUID = 1;
    final ParseError mError;

    public ParseErrorException(ParseError parseError) {
        this.mError = parseError;
    }
}
