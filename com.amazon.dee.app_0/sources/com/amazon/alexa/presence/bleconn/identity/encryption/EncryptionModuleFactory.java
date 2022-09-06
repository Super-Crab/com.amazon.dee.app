package com.amazon.alexa.presence.bleconn.identity.encryption;
/* loaded from: classes9.dex */
public interface EncryptionModuleFactory {
    DecryptionModule buildDecryptionModuleForKey(byte[] bArr);

    EncryptionModule buildEncryptionModuleForKey(byte[] bArr);
}
