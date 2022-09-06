package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class HandsFreeStateProvider {
    @VisibleForTesting
    static final String KEY_PREVIOUS_HANDS_FREE_STATE = "com.amazon.alexa.handsfree.rcs.apigateway.HandsFree.STATE";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.rcs.apigateway.HandsFree";
    private final Context mContext;

    @Inject
    public HandsFreeStateProvider(Context context) {
        this.mContext = context;
    }

    public Boolean getPreviousHandsFreeState() {
        return Boolean.valueOf(this.mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0).getBoolean(KEY_PREVIOUS_HANDS_FREE_STATE, true));
    }

    public void setHandsFreeState(Boolean bool) {
        this.mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0).edit().putBoolean(KEY_PREVIOUS_HANDS_FREE_STATE, bool.booleanValue()).apply();
    }
}
