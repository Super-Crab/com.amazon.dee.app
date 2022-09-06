package com.amazon.alexa.handsfree.settings.contract;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes8.dex */
public interface SettingsSetupFlowContract {
    public static final String AIS_FLOW_ONGOING = "AIS_FLOW_ONGOING";
    public static final String PREFERENCE_FILE_NAME = "FalcoSetupPrefs";

    boolean canEnableWakeWord(Context context);

    Intent getPendingSetupIntent(Context context, SetupFlow setupFlow);

    boolean hasPendingSetup(Context context, SetupFlow setupFlow);
}
