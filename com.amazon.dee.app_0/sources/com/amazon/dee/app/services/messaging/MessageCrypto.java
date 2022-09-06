package com.amazon.dee.app.services.messaging;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface MessageCrypto {
    String decrypt(String str, String str2);

    void expireKeyPair();

    @Nullable
    String getPublicKey();
}
