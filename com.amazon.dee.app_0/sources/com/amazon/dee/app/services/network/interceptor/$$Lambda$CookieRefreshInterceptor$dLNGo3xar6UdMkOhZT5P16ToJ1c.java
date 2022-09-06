package com.amazon.dee.app.services.network.interceptor;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.network.interceptor.-$$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c implements Action1 {
    public static final /* synthetic */ $$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c INSTANCE = new $$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c();

    private /* synthetic */ $$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(CookieRefreshInterceptor.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
