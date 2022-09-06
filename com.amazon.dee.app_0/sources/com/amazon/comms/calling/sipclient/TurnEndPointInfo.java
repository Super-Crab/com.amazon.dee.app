package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
/* loaded from: classes11.dex */
public final class TurnEndPointInfo {
    @Nonnull
    private final String credential;
    @Nonnull
    private final String url;
    @Nonnull
    private final String username;

    /* loaded from: classes11.dex */
    public static class TurnEndPointInfoBuilder {
        private String credential;
        private String url;
        private String username;

        TurnEndPointInfoBuilder() {
        }

        public TurnEndPointInfo build() {
            return new TurnEndPointInfo(this.url, this.username, this.credential);
        }

        public TurnEndPointInfoBuilder credential(String str) {
            this.credential = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TurnEndPointInfo.TurnEndPointInfoBuilder(url=");
            outline107.append(this.url);
            outline107.append(", username=");
            outline107.append(this.username);
            outline107.append(", credential=");
            return GeneratedOutlineSupport1.outline91(outline107, this.credential, ")");
        }

        public TurnEndPointInfoBuilder url(String str) {
            this.url = str;
            return this;
        }

        public TurnEndPointInfoBuilder username(String str) {
            this.username = str;
            return this;
        }
    }

    TurnEndPointInfo(@Nonnull String str, @Nonnull String str2, @Nonnull String str3) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("username is null");
            }
            if (str3 == null) {
                throw new IllegalArgumentException("credential is null");
            }
            this.url = str;
            this.username = str2;
            this.credential = str3;
            return;
        }
        throw new IllegalArgumentException("url is null");
    }

    public static TurnEndPointInfoBuilder builder() {
        return new TurnEndPointInfoBuilder();
    }

    @Nonnull
    public String getCredential() {
        return this.credential;
    }

    @Nonnull
    public String getUrl() {
        return this.url;
    }

    @Nonnull
    public String getUsername() {
        return this.username;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TurnEndPointInfo(url=");
        outline107.append(getUrl());
        outline107.append(", username=");
        outline107.append(getUsername());
        outline107.append(")");
        return outline107.toString();
    }
}
