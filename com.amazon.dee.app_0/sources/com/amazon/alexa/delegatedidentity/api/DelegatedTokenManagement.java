package com.amazon.alexa.delegatedidentity.api;
/* loaded from: classes6.dex */
public interface DelegatedTokenManagement {
    public static final String NO_DELEGATED_TOKEN = "";

    default String getDelegatedToken(String str, String str2, boolean z) {
        return "";
    }

    default void initiateDelegation(String str, String str2) {
    }

    default void terminateDelegation(String str) {
    }
}
