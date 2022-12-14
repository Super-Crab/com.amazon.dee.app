package org.json;
/* loaded from: classes5.dex */
public class JSONException extends Exception {
    private Throwable cause;

    public JSONException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public JSONException(Throwable th) {
        super(th.getMessage());
        this.cause = th;
    }
}
