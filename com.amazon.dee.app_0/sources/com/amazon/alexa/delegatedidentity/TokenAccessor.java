package com.amazon.alexa.delegatedidentity;
/* loaded from: classes6.dex */
public interface TokenAccessor {
    default String getDelegationTokenForPerson(String str, String str2, Boolean bool) {
        return "";
    }

    default void terminateDelegation(String str) throws Exception {
    }
}
