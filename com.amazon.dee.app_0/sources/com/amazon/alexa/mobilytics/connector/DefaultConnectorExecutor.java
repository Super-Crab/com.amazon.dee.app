package com.amazon.alexa.mobilytics.connector;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Singleton;
/* loaded from: classes9.dex */
public class DefaultConnectorExecutor implements ConnectorExecutor, Handler.Callback {
    private static final String TAG = Log.tag(DefaultConnectorExecutor.class);
    private final MobilyticsConnector connector;
    @VisibleForTesting(otherwise = 4)
    public Handler handler;
    private HandlerThread handlerThread;

    @Singleton
    /* loaded from: classes9.dex */
    public static class Factory implements ConnectorExecutor.Factory {
        @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor.Factory
        public ConnectorExecutor create(@NonNull MobilyticsConnector mobilyticsConnector) {
            return new DefaultConnectorExecutor(mobilyticsConnector);
        }
    }

    public DefaultConnectorExecutor(@NonNull MobilyticsConnector mobilyticsConnector) {
        this.connector = (MobilyticsConnector) Preconditions.checkNotNull(mobilyticsConnector);
        initialize();
    }

    private void initialize() {
        this.handlerThread = new HandlerThread(this.connector.name());
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper(), this);
    }

    private void postMessage(int i) {
        this.handler.sendEmptyMessage(i);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public String connectorName() {
        return this.connector.name();
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeOnFinalize() {
        postMessage(1);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeOnInitialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        postMessage(0, mobilyticsConfiguration);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent) {
        postMessage(6, mobilyticsEvent);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeSessionPause(@NonNull MobilyticsSession mobilyticsSession) {
        postMessage(3, mobilyticsSession);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeSessionResume(@NonNull MobilyticsSession mobilyticsSession) {
        postMessage(5, mobilyticsSession);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeSessionStart(@NonNull MobilyticsSession mobilyticsSession) {
        postMessage(2, mobilyticsSession);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeSessionStop(@NonNull MobilyticsSession mobilyticsSession) {
        postMessage(4, mobilyticsSession);
    }

    @Override // com.amazon.alexa.mobilytics.connector.ConnectorExecutor
    public void executeUserChanged(@Nullable MobilyticsUser mobilyticsUser) {
        postMessage(7, mobilyticsUser);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    this.connector.onInitialize((MobilyticsConfiguration) message.obj);
                    break;
                case 1:
                    this.connector.onFinalize();
                    break;
                case 2:
                    this.connector.onSessionStart((MobilyticsSession) message.obj);
                    break;
                case 3:
                    this.connector.onSessionPause((MobilyticsSession) message.obj);
                    break;
                case 4:
                    this.connector.onSessionStop((MobilyticsSession) message.obj);
                    break;
                case 5:
                    this.connector.onSessionResume((MobilyticsSession) message.obj);
                    break;
                case 6:
                    this.connector.onRecordEvent((MobilyticsEvent) message.obj);
                    break;
                case 7:
                    this.connector.onUserChanged((MobilyticsUser) message.obj);
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, e, "Error executing action [%d] on connector: [%s]", Integer.valueOf(message.what), this.connector.name());
        }
        return true;
    }

    private void postMessage(int i, @Nullable Object obj) {
        this.handler.sendMessage(this.handler.obtainMessage(i, obj));
    }
}
