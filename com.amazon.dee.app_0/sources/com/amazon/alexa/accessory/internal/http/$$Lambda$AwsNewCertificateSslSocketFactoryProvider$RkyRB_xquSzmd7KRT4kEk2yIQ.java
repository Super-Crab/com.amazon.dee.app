package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$Rk--yRB_xquSzmd7KRT4kEk2yIQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$RkyRB_xquSzmd7KRT4kEk2yIQ implements WrapperUtil.TwoParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$RkyRB_xquSzmd7KRT4kEk2yIQ INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$RkyRB_xquSzmd7KRT4kEk2yIQ();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$RkyRB_xquSzmd7KRT4kEk2yIQ() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.TwoParamedSupplier
    public final Object get(Object obj, Object obj2) {
        Certificate generateCertificate;
        generateCertificate = ((CertificateFactory) obj).generateCertificate((InputStream) obj2);
        return generateCertificate;
    }
}
