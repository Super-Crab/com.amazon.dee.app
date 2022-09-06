package com.amazon.identity.auth.accounts;

import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class AccountManagerConstants {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class GetTokenParams extends a {

        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public enum TOKEN_TYPE {
            ACCESS_TOKEN,
            DELEGATED_ACCESS_TOKEN
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum LOCALE {
        US,
        DE,
        UK,
        JP,
        FR,
        CA,
        ES,
        CN;
        
        private static final String LOCALE_SEPERATOR = "-";

        public String toUrlString() {
            if (!US.equals(this)) {
                return name().toLowerCase(Locale.US) + "-";
            }
            return "";
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum TOKEN_EXCHANGER_TYPE {
        REFRESH_FOR_ACCESS,
        DMS_FOR_ACCESS,
        REFRESH_FOR_COOKIES
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
    }
}
