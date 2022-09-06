package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface f {
    MAPFuture<Bundle> a(Callback callback, ej ejVar, Bundle bundle);

    MAPFuture<Bundle> a(String str, Callback callback, ej ejVar, Bundle bundle);

    MAPFuture<Bundle> a(String str, im imVar, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, ej ejVar);

    void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar);

    @Deprecated
    void a(Activity activity, String str, Bundle bundle, Callback callback, ej ejVar);

    void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, ej ejVar);

    void a(Bundle bundle, Callback callback, ej ejVar);

    void a(RegistrationType registrationType, Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, ej ejVar);

    void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar);

    void b(Activity activity, String str, Bundle bundle, Callback callback, ej ejVar);

    Set<String> getAccounts();

    String getPrimaryAccount();

    boolean isAccountRegistered(String str);

    boolean isDeviceRegistered();

    String r(String str);
}
