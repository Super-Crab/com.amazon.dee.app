package com.facebook.react.bridge;
/* loaded from: classes2.dex */
public interface JavaJSExecutor {

    /* loaded from: classes2.dex */
    public interface Factory {
        JavaJSExecutor create() throws Exception;
    }

    /* loaded from: classes2.dex */
    public static class ProxyExecutorException extends Exception {
        public ProxyExecutorException(Throwable th) {
            super(th);
        }
    }

    void close();

    String executeJSCall(String str, String str2) throws ProxyExecutorException;

    void loadBundle(String str) throws ProxyExecutorException;

    void setGlobalVariable(String str, String str2);
}
