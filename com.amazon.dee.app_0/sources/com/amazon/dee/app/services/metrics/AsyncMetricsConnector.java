package com.amazon.dee.app.services.metrics;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.dee.app.metrics.AlexaMetricsEvent;
/* loaded from: classes12.dex */
public abstract class AsyncMetricsConnector implements MetricsConnector, Handler.Callback {
    static final int BEGIN_SESSION = 1;
    static final int END_SESSION = 2;
    static final int PAUSE_SESSION = 4;
    static final int RECORD_EVENT = 5;
    static final int RECORD_MOBILYTICS_OPERATIONAL_EVENT = 6;
    static final int RECORD_MOBILYTICS_USER_INTERACTION_EVENT = 7;
    static final int RESUME_SESSION = 3;
    static final int USER_IDENTITY_CHANGED = 8;
    @GuardedBy("handlerLock")
    private Handler handler;
    private final Object handlerLock = new Object();
    private volatile boolean isInitialized;

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void beginSession() {
        getHandler().sendEmptyMessage(1);
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void endSession() {
        getHandler().sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Handler getHandler() {
        Handler handler;
        synchronized (this.handlerLock) {
            if (this.handler == null) {
                HandlerThread handlerThread = new HandlerThread(getClass().getName());
                handlerThread.start();
                this.handler = new Handler(handlerThread.getLooper(), this);
            }
            handler = this.handler;
        }
        return handler;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        initialize();
        Object obj = message.obj;
        switch (message.what) {
            case 1:
                onBeginSession();
                return true;
            case 2:
                onEndSession();
                return true;
            case 3:
                onResumeSession();
                return true;
            case 4:
                onPauseSession();
                return true;
            case 5:
                if (!(obj instanceof AlexaMetricsEvent)) {
                    return false;
                }
                onRecordEvent((AlexaMetricsEvent) obj);
                return true;
            case 6:
                if (!(obj instanceof MobilyticsOperationalEvent)) {
                    return false;
                }
                onRecordOperationalEvent((MobilyticsOperationalEvent) obj);
                return true;
            case 7:
                if (!(obj instanceof MobilyticsUserInteractionEvent)) {
                    return false;
                }
                onRecordUserInteractionEvent((MobilyticsUserInteractionEvent) obj);
                return true;
            case 8:
                if (!(obj instanceof UserIdentity)) {
                    return false;
                }
                onUserChangedOrNull((UserIdentity) obj);
                return true;
            default:
                return false;
        }
    }

    void initialize() {
        if (!this.isInitialized) {
            synchronized (this) {
                if (!this.isInitialized) {
                    getHandler();
                    onInitialize();
                    this.isInitialized = true;
                }
            }
        }
    }

    protected abstract void onBeginSession();

    protected abstract void onEndSession();

    protected abstract void onInitialize();

    protected void onPauseSession() {
    }

    protected abstract void onRecordEvent(AlexaMetricsEvent alexaMetricsEvent);

    protected void onRecordOperationalEvent(MobilyticsOperationalEvent mobilyticsOperationalEvent) {
    }

    protected void onRecordUserInteractionEvent(MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
    }

    protected void onResumeSession() {
    }

    protected void onUserChangedOrNull(UserIdentity userIdentity) {
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void pauseSession() {
        getHandler().sendEmptyMessage(4);
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        getHandler().sendMessage(getHandler().obtainMessage(5, alexaMetricsEvent));
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public void recordOperationalEvent(MobilyticsOperationalEvent mobilyticsOperationalEvent) {
        getHandler().sendMessage(getHandler().obtainMessage(6, mobilyticsOperationalEvent));
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public void recordUserInteractionEvent(MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
        getHandler().sendMessage(getHandler().obtainMessage(7, mobilyticsUserInteractionEvent));
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void resumeSession() {
        getHandler().sendEmptyMessage(3);
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void shutdown() {
        synchronized (this.handlerLock) {
            if (this.handler != null) {
                this.handler.getLooper().quitSafely();
            }
        }
    }

    @Override // com.amazon.dee.app.services.metrics.MetricsConnector
    public final void userChanged(UserIdentity userIdentity) {
        getHandler().sendMessage(getHandler().obtainMessage(8, userIdentity));
    }
}
