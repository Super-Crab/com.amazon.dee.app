package com.amazon.alexa.presence.service;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.dagger.PresenceComponent;
import com.amazon.alexa.presence.dagger.PresenceComponentSingleton;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class AlexaBeaconDetectorService extends JobIntentService {
    @VisibleForTesting
    static final String ACTION_START_SCAN = "com.amazon.alexa.presence.action.startScan";
    @VisibleForTesting
    static final String ACTION_STOP_SCAN = "com.amazon.alexa.presence.action.stopScan";
    @VisibleForTesting
    static final int JOB_ID = 7536827;
    @VisibleForTesting
    static final String STOP_SCAN_EXTRA = "com.amazon.alexa.presence.stopScan.extra.forceStop";
    private static final String TAG = Presence.tag();
    @Inject
    PresenceIntentHandler mHandler;
    private PresenceComponent mPresenceComponent;

    public AlexaBeaconDetectorService() {
    }

    public static void executeActionToStartScanning(Context context) {
        JobIntentService.enqueueWork(context, AlexaBeaconDetectorService.class, (int) JOB_ID, new Intent(ACTION_START_SCAN));
    }

    private static void executeActionToStopScanning(Context context, boolean z) {
        Intent intent = new Intent(ACTION_STOP_SCAN);
        intent.putExtra(STOP_SCAN_EXTRA, z);
        JobIntentService.enqueueWork(context, AlexaBeaconDetectorService.class, (int) JOB_ID, intent);
    }

    public static void forceStopScanning(Context context) {
        executeActionToStopScanning(context, true);
    }

    public static void stopScanning(Context context) {
        executeActionToStopScanning(context, false);
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mPresenceComponent = PresenceComponentSingleton.getInstance(getApplicationContext());
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        this.mPresenceComponent.inject(this);
        this.mHandler.handle(intent);
    }

    public AlexaBeaconDetectorService(PresenceIntentHandler presenceIntentHandler) {
        this.mHandler = presenceIntentHandler;
    }
}
