package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PBEProtectionRemoverFactory {
    PBESecretKeyDecryptor createDecryptor(String str) throws PGPException;
}
