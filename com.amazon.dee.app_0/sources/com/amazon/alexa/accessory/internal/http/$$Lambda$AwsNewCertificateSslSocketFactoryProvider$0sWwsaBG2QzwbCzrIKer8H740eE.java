package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import javax.net.ssl.TrustManagerFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        TrustManagerFactory trustManagerFactory;
        trustManagerFactory = TrustManagerFactory.getInstance((String) obj);
        return trustManagerFactory;
    }
}
