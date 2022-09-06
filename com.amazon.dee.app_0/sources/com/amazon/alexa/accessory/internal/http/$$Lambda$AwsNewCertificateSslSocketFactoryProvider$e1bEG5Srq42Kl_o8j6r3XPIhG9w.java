package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.security.cert.CertificateFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w implements WrapperUtil.Supplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.Supplier
    public final Object get() {
        CertificateFactory certificateFactory;
        certificateFactory = CertificateFactory.getInstance("X.509");
        return certificateFactory;
    }
}
