package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider;
import java.security.KeyStore;
import java.security.cert.Certificate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM implements AwsNewCertificateSslSocketFactoryProvider.KeyStoreSetCertificateEntryWrapper {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM() {
    }

    @Override // com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider.KeyStoreSetCertificateEntryWrapper
    public final void setCertificateEntry(KeyStore keyStore, String str, Certificate certificate) {
        keyStore.setCertificateEntry(str, certificate);
    }
}
