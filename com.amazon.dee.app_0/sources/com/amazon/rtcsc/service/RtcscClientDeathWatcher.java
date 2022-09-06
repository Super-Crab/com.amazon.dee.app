package com.amazon.rtcsc.service;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class RtcscClientDeathWatcher {
    private final ArrayMap<String, DeathCallBack> mAppClientDeathCallbacks;
    private final Object mDeathCallbackLock;
    private RtcscLogger mLog;
    private final RtcscServiceHandler mRtcscServiceHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class DeathCallBack implements IBinder.DeathRecipient {
        private final RtcscAppInfo mAppInfo;
        private final IBinder mBinder;

        DeathCallBack(IBinder iBinder, RtcscAppInfo rtcscAppInfo) {
            this.mBinder = iBinder;
            this.mAppInfo = rtcscAppInfo;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mBinder.unlinkToDeath(this, 0);
            RtcscClientDeathWatcher.this.cleanClientEntry(this.mAppInfo);
        }

        public IBinder getBinder() {
            return this.mBinder;
        }
    }

    public RtcscClientDeathWatcher(RtcscServiceHandler rtcscServiceHandler) {
        this(rtcscServiceHandler, new ArrayMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanClientEntry(RtcscAppInfo rtcscAppInfo) {
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cleanClientEntry : AppClient ");
        outline107.append(rtcscAppInfo.getAppIdentifier());
        outline107.append(" died, cleaning up.");
        rtcscLogger.i(outline107.toString());
        synchronized (this.mDeathCallbackLock) {
            this.mAppClientDeathCallbacks.remove(rtcscAppInfo.getAppIdentifier());
        }
        this.mRtcscServiceHandler.cleanAppClient(rtcscAppInfo);
    }

    public void deregister(RtcscAppInfo rtcscAppInfo) {
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("deregister : Deregistering DeathCallback for AppClient ");
        outline107.append(rtcscAppInfo.getAppIdentifier());
        rtcscLogger.i(outline107.toString());
        synchronized (this.mDeathCallbackLock) {
            DeathCallBack remove = this.mAppClientDeathCallbacks.remove(rtcscAppInfo.getAppIdentifier());
            if (remove != null) {
                remove.getBinder().unlinkToDeath(remove, 0);
            }
        }
    }

    public void register(IBinder iBinder, RtcscAppInfo rtcscAppInfo) {
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("register : Registering DeathCallback for AppClient ");
        outline107.append(rtcscAppInfo.getAppIdentifier());
        rtcscLogger.i(outline107.toString());
        synchronized (this.mDeathCallbackLock) {
            try {
                if (!this.mAppClientDeathCallbacks.containsKey(rtcscAppInfo.getAppIdentifier())) {
                    DeathCallBack deathCallBack = new DeathCallBack(iBinder, rtcscAppInfo);
                    iBinder.linkToDeath(deathCallBack, 0);
                    this.mAppClientDeathCallbacks.put(rtcscAppInfo.getAppIdentifier(), deathCallBack);
                } else {
                    this.mLog.w("register : Received a register request for a session domain already tracked. Ignoring ..");
                }
            } catch (RemoteException unused) {
                this.mLog.e("register : Failed to register death watcher");
            }
        }
    }

    RtcscClientDeathWatcher(RtcscServiceHandler rtcscServiceHandler, ArrayMap arrayMap) {
        this.mDeathCallbackLock = new Object();
        this.mLog = RtcscLogger.getLogger(RtcscClientDeathWatcher.class);
        if (rtcscServiceHandler != null) {
            this.mRtcscServiceHandler = rtcscServiceHandler;
            this.mAppClientDeathCallbacks = arrayMap;
            return;
        }
        throw new IllegalArgumentException("rtcscServiceHandler cannot be null");
    }
}
