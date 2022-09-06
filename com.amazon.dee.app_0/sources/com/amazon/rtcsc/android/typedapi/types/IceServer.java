package com.amazon.rtcsc.android.typedapi.types;

import lombok.NonNull;
/* loaded from: classes13.dex */
public class IceServer {
    private String credential;
    @NonNull
    private String url;
    private String username;

    public IceServer(@NonNull String str) {
        if (str != null) {
            this.url = str;
            return;
        }
        throw new NullPointerException("url");
    }

    public String getCredential() {
        return this.credential;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public IceServer(@NonNull String str, String str2, String str3) {
        if (str != null) {
            this.url = str;
            this.username = str2;
            this.credential = str3;
            return;
        }
        throw new NullPointerException("url");
    }
}
