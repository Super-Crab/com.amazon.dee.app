package com.amazon.rtcsc.capabilityagentclient;

import android.content.Context;
import com.amazon.rtcsc.common.AsyncServiceConnectionManager;
import com.amazon.rtcsc.common.RtcscClientHandler;
import com.amazon.rtcsc.common.ServiceConnectionManager;
import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.utils.RtcscLogger;
import lombok.NonNull;
/* loaded from: classes13.dex */
public abstract class RtcscCAClient {
    private final Context mContext;
    private RtcscLogger mLog;
    private RtcscClientHandler mRtcscClientHandler;

    public RtcscCAClient(@NonNull Context context) {
        this(context, null);
    }

    public synchronized RtcscErrorCode connectToRtcscService() {
        this.mLog.i("connectToRtcscService called.");
        if (this.mRtcscClientHandler == null) {
            this.mRtcscClientHandler = setupRtcscClientHandler();
        }
        return RtcscErrorCode.SUCCESS;
    }

    public RtcscErrorCode disconnectFromRtcscService() {
        this.mLog.i("disconnectFromRtcscService called.");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler == null) {
            this.mLog.e("mRtcscClientHandler is null. Did you forget to call registerCAEventListener?");
            return RtcscErrorCode.NULL_CLIENT_HANDLER;
        }
        rtcscClientHandler.teardown();
        this.mRtcscClientHandler = null;
        return RtcscErrorCode.SUCCESS;
    }

    public RtcscErrorCode handleDirective(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                RtcscLogger rtcscLogger = this.mLog;
                rtcscLogger.d("handle directive : " + str + " with payload : " + str2);
                RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
                if (rtcscClientHandler != null) {
                    return rtcscClientHandler.handleDirective(str, str2);
                }
                this.mLog.w("Skipping the handleDirective request. Please register a listener first.");
                return RtcscErrorCode.LISTENER_NOT_FOUND;
            }
            throw new IllegalArgumentException("directivePayload is null");
        }
        throw new IllegalArgumentException("directiveName is null");
    }

    protected abstract void onRtcscServiceConnected();

    protected abstract void onRtcscServiceDisconnected();

    public RtcscErrorCode registerCAEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) {
        this.mLog.i("registerCAEventListener called.");
        if (iRtcscEventListener == null) {
            this.mLog.w("Null EventListener. Aborting...");
            return RtcscErrorCode.NULL_LISTENER;
        }
        if (this.mRtcscClientHandler == null) {
            this.mRtcscClientHandler = setupRtcscClientHandler();
        }
        return this.mRtcscClientHandler.registerCAEventListener(rtcscAppInfo, iRtcscEventListener);
    }

    RtcscClientHandler setupRtcscClientHandler() {
        AsyncServiceConnectionManager asyncServiceConnectionManager = new AsyncServiceConnectionManager();
        asyncServiceConnectionManager.addCallback(new ServiceConnectionManager.Callback() { // from class: com.amazon.rtcsc.capabilityagentclient.RtcscCAClient.1
            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onBind(boolean z) {
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onServiceConnected() {
                RtcscCAClient.this.onRtcscServiceConnected();
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onServiceDisconnected() {
                RtcscCAClient.this.onRtcscServiceDisconnected();
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onUnbind() {
            }
        });
        return new RtcscClientHandler(this.mContext, asyncServiceConnectionManager);
    }

    public RtcscErrorCode unregisterCAEventListener(RtcscAppInfo rtcscAppInfo) {
        this.mLog.i("unregisterCAEventListener called.");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler == null) {
            this.mLog.e("mRtcscClientHandler is null. Did you forget to call registerCAEventListener?");
            return RtcscErrorCode.NULL_CLIENT_HANDLER;
        }
        RtcscErrorCode unregisterCAEventListener = rtcscClientHandler.unregisterCAEventListener(rtcscAppInfo);
        this.mRtcscClientHandler.teardown();
        this.mRtcscClientHandler = null;
        return unregisterCAEventListener;
    }

    RtcscCAClient(@NonNull Context context, RtcscClientHandler rtcscClientHandler) {
        this.mLog = RtcscLogger.getLogger(RtcscCAClient.class);
        if (context != null) {
            this.mContext = context;
            this.mRtcscClientHandler = rtcscClientHandler;
            return;
        }
        throw new IllegalArgumentException("context is null");
    }
}
