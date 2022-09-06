package com.dee.app.cachemanager;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface Encryptor {
    boolean clearState();

    byte[] decrypt(@NonNull byte[] bArr) throws EncryptorException;

    byte[] encrypt(@NonNull byte[] bArr) throws EncryptorException;

    void init() throws EncryptorException;
}
