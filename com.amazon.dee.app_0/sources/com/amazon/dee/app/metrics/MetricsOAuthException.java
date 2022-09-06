package com.amazon.dee.app.metrics;
/* loaded from: classes12.dex */
public class MetricsOAuthException extends Exception {
    public MetricsOAuthException() {
        super("Error occured while trying to get the Access Token");
    }

    public MetricsOAuthException(String str) {
        super(str);
    }

    public MetricsOAuthException(Exception exc) {
        super("Error occured while trying to get the Access Token", exc);
    }

    public MetricsOAuthException(String str, Exception exc) {
        super(str, exc);
    }
}
