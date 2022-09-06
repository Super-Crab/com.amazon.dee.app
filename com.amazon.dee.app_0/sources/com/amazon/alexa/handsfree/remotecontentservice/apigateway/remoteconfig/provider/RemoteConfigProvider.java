package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class RemoteConfigProvider {
    @VisibleForTesting
    static final String KEY_REMOTE_CONFIG_VALUES = "com.amazon.alexa.handsfree.rcs.apigateway.RemoteConfig.VALUES";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.rcs.apigateway.RemoteConfig";
    private final Context mContext;

    @Inject
    public RemoteConfigProvider(Context context) {
        this.mContext = context;
    }

    public String getRemoteConfig() {
        return this.mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0).getString(KEY_REMOTE_CONFIG_VALUES, null);
    }

    public void setRemoteConfig(String str) {
        this.mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0).edit().putString(KEY_REMOTE_CONFIG_VALUES, str).apply();
    }
}
