package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4 implements AwsNewCertificateSslSocketFactoryProvider.TrustManagerFactoryInitWrapper {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4 INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4() {
    }

    @Override // com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider.TrustManagerFactoryInitWrapper
    public final void init(TrustManagerFactory trustManagerFactory, KeyStore keyStore) {
        trustManagerFactory.init(keyStore);
    }
}
