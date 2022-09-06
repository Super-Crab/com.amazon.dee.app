package com.dee.app.cachemanager;
/* loaded from: classes9.dex */
public class CacheException extends RuntimeException {
    public CacheException() {
    }

    public CacheException(Throwable th) {
        initCause(th);
    }

    public CacheException(String str) {
        super(str);
    }

    public CacheException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
