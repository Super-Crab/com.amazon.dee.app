package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface hh {
    MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, gy gyVar, ej ejVar);

    MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> e(String str, String str2, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> f(String str, String str2, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> g(String str, String str2, Bundle bundle, Callback callback, ej ejVar);
}
