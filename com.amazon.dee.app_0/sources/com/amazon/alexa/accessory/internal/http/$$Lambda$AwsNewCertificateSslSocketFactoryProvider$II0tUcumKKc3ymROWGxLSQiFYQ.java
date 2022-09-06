package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider;
import java.io.InputStream;
import java.security.KeyStore;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymRO-WGxLSQiFYQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymROWGxLSQiFYQ implements AwsNewCertificateSslSocketFactoryProvider.KeyStoreLoadWrapper {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymROWGxLSQiFYQ INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymROWGxLSQiFYQ();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymROWGxLSQiFYQ() {
    }

    @Override // com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider.KeyStoreLoadWrapper
    public final void load(KeyStore keyStore, InputStream inputStream, char[] cArr) {
        keyStore.load(inputStream, cArr);
    }
}
