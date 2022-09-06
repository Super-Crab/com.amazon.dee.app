package com.amazon.alexa.client.metrics.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.Lazy;
/* loaded from: classes6.dex */
public abstract class AsyncMetricsConnector implements MetricsConnector, Handler.Callback {
    private static final int BEGIN_SESSION = 1;
    private static final int END_SESSION = 2;
    private static final int PAUSE_SESSION = 4;
    private static final int RECORD_EVENT = 5;
    private static final int RESUME_SESSION = 3;
    private static final String TAG = "AsyncMetricsConnector";
    protected final Lazy<ClientConfiguration> clientConfiguration;
    protected String configuredServiceName;
    protected final Handler handler;
    private final HandlerThread handlerThread = new HandlerThread(getClass().getName());
    private volatile boolean isInitialized;

    public AsyncMetricsConnector(@NonNull Lazy<ClientConfiguration> lazy) {
        this.clientConfiguration = lazy;
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper(), this);
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void beginSession() {
        this.handler.sendEmptyMessage(1);
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void endSession() {
        this.handler.sendEmptyMessage(2);
    }

    @VisibleForTesting
    public final HandlerThread getHandlerThread() {
        return this.handlerThread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getServiceName(@NonNull DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        String serviceName = defaultAlexaMetricsEvent.getServiceName();
        if (serviceName != null) {
            return serviceName;
        }
        if (this.configuredServiceName == null) {
            this.configuredServiceName = this.clientConfiguration.mo358get().getMetricsServiceName();
        }
        return this.configuredServiceName;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        initialize();
        int i = message.what;
        if (i == 1) {
            onBeginSession();
            return true;
        } else if (i == 2) {
            onEndSession();
            return true;
        } else if (i == 3) {
            onResumeSession();
            return true;
        } else if (i == 4) {
            onPauseSession();
            return true;
        } else if (i != 5) {
            return false;
        } else {
            onRecordEvent((DefaultAlexaMetricsEvent) message.obj);
            return true;
        }
    }

    protected void initialize() {
        if (!this.isInitialized) {
            synchronized (this) {
                if (!this.isInitialized) {
                    this.isInitialized = true;
                    onInitialize();
                }
            }
        }
    }

    protected void onBeginSession() {
    }

    protected void onEndSession() {
    }

    protected void onInitialize() {
    }

    protected void onPauseSession() {
    }

    protected void onRecordEvent(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
    }

    protected void onResumeSession() {
    }

    protected void onShutdown() {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void pauseSession() {
        this.handler.sendEmptyMessage(4);
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        this.handler.sendMessage(this.handler.obtainMessage(5, alexaMetricsEvent));
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void resumeSession() {
        this.handler.sendEmptyMessage(3);
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public final void shutdown() {
        this.handlerThread.quitSafely();
        onShutdown();
    }
}
