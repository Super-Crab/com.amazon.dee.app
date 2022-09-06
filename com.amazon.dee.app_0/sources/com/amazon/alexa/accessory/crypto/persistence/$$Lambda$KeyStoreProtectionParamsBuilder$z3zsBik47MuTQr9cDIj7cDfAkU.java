package com.amazon.alexa.accessory.crypto.persistence;

import android.security.keystore.KeyProtection;
import com.google.common.base.Supplier;
import java.security.KeyStore;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.crypto.persistence.-$$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik4-7MuTQr9cDIj7cDfAkU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik47MuTQr9cDIj7cDfAkU implements Supplier {
    public static final /* synthetic */ $$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik47MuTQr9cDIj7cDfAkU INSTANCE = new $$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik47MuTQr9cDIj7cDfAkU();

    private /* synthetic */ $$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik47MuTQr9cDIj7cDfAkU() {
    }

    @Override // com.google.common.base.Supplier
    /* renamed from: get */
    public final Object mo8291get() {
        KeyStore.ProtectionParameter build;
        build = new KeyProtection.Builder(3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").build();
        return build;
    }
}
