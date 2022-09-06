package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        SSLSocketFactory socketFactory;
        socketFactory = ((SSLContext) obj).getSocketFactory();
        return socketFactory;
    }
}
