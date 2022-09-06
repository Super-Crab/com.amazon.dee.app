package com.amazon.identity.platform.setting;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.eg;
import com.amazon.identity.auth.device.mz;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class PlatformSettings {
    private static final String TAG = "PlatformSettings";
    private static PlatformSettings vK;
    private Context mContext;
    private eg mSystemPropertiesWrapper = new eg();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum Namespace {
        DeviceGlobal,
        Default
    }

    public PlatformSettings(Context context) {
        this.mContext = context;
    }

    public static synchronized PlatformSettings aU(Context context) {
        synchronized (PlatformSettings.class) {
            if (vK != null) {
                return vK;
            }
            PlatformSettings platformSettings = new PlatformSettings(context);
            vK = platformSettings;
            return platformSettings;
        }
    }

    public void addListener(Runnable runnable) {
        this.mSystemPropertiesWrapper.a(runnable);
    }

    public String eV(String str) {
        String str2;
        if (mz.iT()) {
            str2 = this.mSystemPropertiesWrapper.b(str, this.mContext.getApplicationContext());
        } else {
            str2 = this.mSystemPropertiesWrapper.get(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    public Boolean f(String str, boolean z) {
        String str2 = this.mSystemPropertiesWrapper.get(str);
        if (TextUtils.isEmpty(str2)) {
            return Boolean.valueOf(z);
        }
        return Boolean.valueOf(Boolean.parseBoolean(str2));
    }
}
