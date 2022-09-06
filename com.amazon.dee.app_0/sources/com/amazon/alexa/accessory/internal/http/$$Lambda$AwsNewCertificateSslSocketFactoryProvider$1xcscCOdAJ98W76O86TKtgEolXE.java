package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        TrustManager[] trustManagers;
        trustManagers = ((TrustManagerFactory) obj).getTrustManagers();
        return trustManagers;
    }
}
