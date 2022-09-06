package com.amazon.alexa.comms.mediaInsights.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnection;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.StaticDeviceDetailsDecorator;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceConfigProvider;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceLogicFactory;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceMessageHandlerFactory;
import java.util.Locale;
/* loaded from: classes6.dex */
public class TracePublisherService extends Service {
    private Messenger messenger;
    private TracePublisherServiceConfiguration serviceConfig;
    private TracePublisherServiceLogic tracePublisherServiceLogic;

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private void setUniqueIdentifierToDeviceDetails(String str) {
        if (isNullOrEmpty(str)) {
            Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, "Failed to set uniqueIdentifier since it is null!");
            return;
        }
        StaticDeviceDetailsDecorator.putIntoStaticDeviceDetailsMap(StaticDeviceDetailsDecorator.StaticDeviceDetailKeys.DSN.toString(), str);
        Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format(Locale.US, "uniqueIdentifier %s was set into the staticDeviceDetailsMap", str));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        setUniqueIdentifierToDeviceDetails(intent.getStringExtra(TracePublisherServiceConnection.UNIQUE_IDENTIFIER));
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        this.serviceConfig = TracePublisherServiceConfigProvider.provideConfig();
        Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format("Configuration details %s", this.serviceConfig.toString()));
        this.tracePublisherServiceLogic = TracePublisherServiceLogicFactory.getInstance(this.serviceConfig, getApplicationContext());
        TracePublisherServiceMessageHandler create = TracePublisherServiceMessageHandlerFactory.create(this.tracePublisherServiceLogic);
        this.messenger = new Messenger(create);
        this.tracePublisherServiceLogic.setServiceHandler(create);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.tracePublisherServiceLogic.forceFlush();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
