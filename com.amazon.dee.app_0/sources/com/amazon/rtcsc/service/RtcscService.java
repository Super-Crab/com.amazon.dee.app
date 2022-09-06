package com.amazon.rtcsc.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class RtcscService extends IntentService {
    private RtcscLogger mLog;
    private RtcscServiceHandler mRtcscServiceHandler;

    public RtcscService() {
        super(RtcscService.class.getName());
        this.mLog = RtcscLogger.getLogger(RtcscService.class);
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        this.mLog.i("onBind called.");
        return this.mRtcscServiceHandler.asBinder();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mRtcscServiceHandler = new RtcscServiceHandler(getApplicationContext());
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        this.mLog.i("onDestroy called.");
        this.mRtcscServiceHandler.cleanUp();
        super.onDestroy();
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onHandleIntent() called with action : ");
        outline107.append(intent.getAction());
        rtcscLogger.i(outline107.toString());
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.mLog.i("onUnbind called.");
        return false;
    }
}
