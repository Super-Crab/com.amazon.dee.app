package com.amazon.alexa.accessory.crypto.persistence;

import android.security.keystore.KeyProtection;
import com.google.android.gms.stats.CodePackage;
import com.google.common.base.Supplier;
import java.security.KeyStore;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.crypto.persistence.-$$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIA--gTAynt0tJMbYHD_POA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIAgTAynt0tJMbYHD_POA implements Supplier {
    public static final /* synthetic */ $$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIAgTAynt0tJMbYHD_POA INSTANCE = new $$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIAgTAynt0tJMbYHD_POA();

    private /* synthetic */ $$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIAgTAynt0tJMbYHD_POA() {
    }

    @Override // com.google.common.base.Supplier
    /* renamed from: get */
    public final Object mo8291get() {
        KeyStore.ProtectionParameter build;
        build = new KeyProtection.Builder(3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build();
        return build;
    }
}
