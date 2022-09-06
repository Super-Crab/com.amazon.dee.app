package com.dee.app.cachemanager;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class HttpResponseWrapper implements Serializable {
    public static final int MEMORY_EVICTION_TIER_ONE = 0;
    public static final int MEMORY_EVICTION_TIER_TWO = 1;
    private String contentType;
    private String data;
    private boolean encrypt;
    private int evictionTier;
    private String[] headers;
    private final long modificationDate;
    private int statusCode;
    private String statusText;
    private String uri;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface EvictionTier {
    }

    public HttpResponseWrapper(String str, int i, String str2, String[] strArr, String str3, String str4, int i2) {
        this.encrypt = true;
        this.uri = str;
        this.statusCode = i;
        this.headers = (String[]) strArr.clone();
        this.statusText = str2;
        this.contentType = str3;
        this.data = str4;
        this.evictionTier = i2;
        this.modificationDate = System.currentTimeMillis();
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getData() {
        return this.data;
    }

    public int getEvictionTier() {
        return this.evictionTier;
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public long getLastModifiedDate() {
        return this.modificationDate;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public String getUri() {
        return this.uri;
    }

    public void setData(String str) {
        this.data = str;
    }

    public HttpResponseWrapper(String str, int i) {
        this.encrypt = true;
        this.data = str;
        this.evictionTier = i;
        this.modificationDate = System.currentTimeMillis();
    }

    public HttpResponseWrapper(String str) {
        this.encrypt = true;
        this.data = str;
        this.modificationDate = System.currentTimeMillis();
    }

    public HttpResponseWrapper() {
        this.encrypt = true;
        this.modificationDate = System.currentTimeMillis();
        this.data = "";
    }
}
