package com.amazon.identity.kcpsdk.common;

import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.me;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class WebResponseParser<T> {
    private static final String TAG = "com.amazon.identity.kcpsdk.common.WebResponseParser";
    private final String uZ;
    private me vb;
    private boolean vd;
    private ParseError va = ParseError.ParseErrorNoError;
    private WebResponseParserState vc = WebResponseParserState.Before_Parse;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum WebResponseParserState {
        Before_Parse,
        Begin_Parse,
        Parsing,
        Completed
    }

    public WebResponseParser(String str) {
        this.uZ = str;
    }

    private void a(WebResponseParserState webResponseParserState) {
        if (this.vc != WebResponseParserState.Before_Parse && webResponseParserState == WebResponseParserState.Begin_Parse) {
            io.c(TAG, "%s: beginParse has been called more than once.", getParserName());
            return;
        }
        WebResponseParserState webResponseParserState2 = this.vc;
        if (webResponseParserState2 == WebResponseParserState.Before_Parse) {
            if (webResponseParserState == WebResponseParserState.Parsing) {
                io.c(TAG, "%s: parseBodyChunk called before beginParse", getParserName());
                return;
            } else if (webResponseParserState == WebResponseParserState.Completed) {
                io.c(TAG, "%s: endParse called before beginParse", getParserName());
                return;
            }
        } else if (webResponseParserState2 == WebResponseParserState.Begin_Parse) {
            if (webResponseParserState == WebResponseParserState.Completed && hd()) {
                this.vd = true;
                return;
            } else if (webResponseParserState == WebResponseParserState.Parsing && !hd()) {
                io.c(TAG, "%s: shouldParseBody is false. parseBodyChunk should not be called", getParserName());
                return;
            }
        } else if (webResponseParserState2 == WebResponseParserState.Completed && webResponseParserState == WebResponseParserState.Parsing) {
            io.c(TAG, "%s: parseBodyChunk called after endParse", getParserName());
            return;
        }
        this.vc = webResponseParserState;
    }

    protected abstract void a(byte[] bArr, long j);

    public ParseError b(byte[] bArr, long j) {
        a(WebResponseParserState.Parsing);
        if (hi() != ParseError.ParseErrorNoError) {
            io.c(TAG, "%s: parseBodyChunk: called after another method returned a parse error.", getParserName());
            return hi();
        }
        a(bArr, j);
        if (hi() == ParseError.ParseErrorMalformedBody) {
            io.a(TAG, "%s: parseBodyChunk: Malformed response. confirm all received data is being properly passed to the parser, device capabilities are set properly, and no server-side behavior changes have occurred.", getParserName());
        }
        return hi();
    }

    protected boolean b(me meVar) {
        return false;
    }

    public void c(me meVar) {
        a(WebResponseParserState.Begin_Parse);
        this.vb = meVar;
        boolean b = b(meVar);
        long iG = this.vb.iG();
        if (iG < 200 || iG >= 300) {
            io.a(TAG, "%s: HTTP Error: %d", getParserName(), Long.valueOf(iG));
            if (b) {
                return;
            }
            b(ParseError.ParseErrorHttpError);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getParserName() {
        return this.uZ;
    }

    public boolean hd() {
        return hi() != ParseError.ParseErrorHttpError;
    }

    public abstract T hf();

    protected abstract void hg();

    public ParseError hh() {
        a(WebResponseParserState.Completed);
        if (hi() != ParseError.ParseErrorNoError) {
            io.c(TAG, "%s: endParse: called after another method returned a parse error.", getParserName());
            return hi();
        }
        hg();
        if (hi() == ParseError.ParseErrorMalformedBody) {
            if (this.vd) {
                io.b(TAG, "%s: endParse called before parseBodyChunk. Confirm that this is by design.", getParserName());
            }
            io.c(TAG, "%s: endParse: Malformed response. Confirm all received data is being properly passed to the parser, device capabilities are set properly, and no server-side behavior changes have occurred.", getParserName());
        }
        return hi();
    }

    public ParseError hi() {
        return this.va;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(ParseError parseError) {
        if (this.va != ParseError.ParseErrorNoError) {
            io.a(TAG, "%s: setParseError has been called more than once.  Was %s, Now %s.", getParserName(), this.va.name(), parseError.name());
        }
        this.va = parseError;
        return true;
    }
}
