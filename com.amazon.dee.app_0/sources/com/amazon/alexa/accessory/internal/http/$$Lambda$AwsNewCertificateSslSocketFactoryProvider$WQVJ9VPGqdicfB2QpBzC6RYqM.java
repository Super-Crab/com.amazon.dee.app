package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import javax.net.ssl.SSLContext;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQV-J9VPGqdi-cfB2QpBzC6RYqM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQVJ9VPGqdicfB2QpBzC6RYqM implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQVJ9VPGqdicfB2QpBzC6RYqM INSTANCE = new $$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQVJ9VPGqdicfB2QpBzC6RYqM();

    private /* synthetic */ $$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQVJ9VPGqdicfB2QpBzC6RYqM() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        SSLContext sSLContext;
        sSLContext = SSLContext.getInstance((String) obj);
        return sSLContext;
    }
}
