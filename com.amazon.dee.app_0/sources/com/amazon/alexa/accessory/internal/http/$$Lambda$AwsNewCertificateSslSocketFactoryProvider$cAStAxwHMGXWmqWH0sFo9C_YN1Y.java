package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y implements AwsNewCertificateSslSocketFactoryProvider.SslContextInitWrapper {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y() {
    }

    @Override // com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider.SslContextInitWrapper
    public final void init(SSLContext sSLContext, KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) {
        sSLContext.init(keyManagerArr, trustManagerArr, secureRandom);
    }
}
