package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.security.KeyStore;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1X-dvM4gM8T-s  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1XdvM4gM8Ts implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1XdvM4gM8Ts INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1XdvM4gM8Ts();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1XdvM4gM8Ts() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        KeyStore keyStore;
        keyStore = KeyStore.getInstance((String) obj);
        return keyStore;
    }
}
