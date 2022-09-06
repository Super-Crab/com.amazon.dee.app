package com.amazon.alexa.accessory.internal.http;

import androidx.annotation.Nullable;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public interface SslSocketFactoryProvider {
    @Nullable
    SSLSocketFactory provide();
}
