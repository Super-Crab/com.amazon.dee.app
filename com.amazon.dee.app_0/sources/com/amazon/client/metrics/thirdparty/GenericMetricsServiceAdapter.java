package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.amazon.client.metrics.thirdparty.IMetricsService;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
import com.amazon.device.utils.thirdparty.CustomDeviceUtil;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class GenericMetricsServiceAdapter {
    private static final long SHUTDOWN_TIMEOUT_MS = 120000;
    private static final String THREAD_NAME = "MetricsService";
    private static final DPLogger log = new DPLogger("MetricsServiceAdapter");
    private static GenericMetricsServiceAdapter sGenericMetricsServiceAdapter;
    private static MetricsConfiguration sMetricsConfiguration;
    private final IMetricsService mBinder = new IMetricsService.Stub() { // from class: com.amazon.client.metrics.thirdparty.GenericMetricsServiceAdapter.2
        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public boolean getRecordMetricsSetting() {
            return true;
        }

        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException {
            GenericMetricsServiceAdapter.log.verbose("record", GeneratedOutlineSupport1.outline77("[ ", str, " , ", str2, " ]"), new Object[0]);
            Handler handler = GenericMetricsServiceAdapter.this.getHandler();
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.arg2 = i2;
            obtainMessage.obj = new MetricEntry(j, str, str2, DataPointEnvelope.convertFromEnvelopes(list));
            handler.sendMessage(obtainMessage);
        }
    };
    private final Context mContext;
    private final CustomDeviceUtil mDeviceUtil;
    private final Handler mHandler;
    private final HandlerThread mMainThread;
    private final MetricsService mMetricsService;
    private final BaseMetricsServiceFactory mMetricsServiceFactory;

    private GenericMetricsServiceAdapter(Context context) {
        log.debug("initialize", "initialize(context) - Metrics service", new Object[0]);
        this.mContext = context;
        this.mMainThread = new HandlerThread("MetricsService");
        this.mMainThread.start();
        this.mHandler = new Handler(this.mMainThread.getLooper(), new Handler.Callback() { // from class: com.amazon.client.metrics.thirdparty.GenericMetricsServiceAdapter.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return GenericMetricsServiceAdapter.this.handleMessageForService(message);
            }
        });
        try {
            this.mDeviceUtil = new CustomDeviceUtil(context);
            this.mMetricsServiceFactory = new BaseMetricsServiceFactory(context, this.mDeviceUtil, sMetricsConfiguration);
            this.mMetricsService = this.mMetricsServiceFactory.createMetricsService();
        } catch (MetricsConfigurationException e) {
            shutdown();
            throw new RuntimeException(e);
        }
    }

    public static GenericMetricsServiceAdapter getInstance(Context context) {
        if (sGenericMetricsServiceAdapter == null) {
            sGenericMetricsServiceAdapter = new GenericMetricsServiceAdapter(context);
        }
        return sGenericMetricsServiceAdapter;
    }

    public static void setMetricsConfiguration(MetricsConfiguration metricsConfiguration) {
        sMetricsConfiguration = metricsConfiguration;
    }

    private void shutdown() {
        BaseMetricsServiceFactory baseMetricsServiceFactory = this.mMetricsServiceFactory;
        if (baseMetricsServiceFactory != null) {
            baseMetricsServiceFactory.shutdown();
        }
        MetricsService metricsService = this.mMetricsService;
        if (metricsService != null) {
            metricsService.shutdown();
        }
        HandlerThread handlerThread = this.mMainThread;
        if (handlerThread == null) {
            log.debug("shutdown", "HandlerThread is null - nothing to do in shutdown.", new Object[0]);
            return;
        }
        handlerThread.quit();
        try {
            this.mMainThread.join(120000L);
            log.debug("shutdown", "(super) Shutting down...", new Object[0]);
        } catch (InterruptedException e) {
            throw new RuntimeException("System service shutdown failed", e);
        }
    }

    public static void shutdownInstance() {
        GenericMetricsServiceAdapter genericMetricsServiceAdapter = sGenericMetricsServiceAdapter;
        if (genericMetricsServiceAdapter != null) {
            genericMetricsServiceAdapter.shutdown();
            sGenericMetricsServiceAdapter = null;
        }
    }

    protected Context getContext() {
        return this.mContext;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    protected HandlerThread getMainThread() {
        return this.mMainThread;
    }

    public IMetricsService getMetricsService() {
        return sGenericMetricsServiceAdapter.mBinder;
    }

    protected boolean handleMessageForService(Message message) {
        Object obj = message.obj;
        if (obj != null && (obj instanceof MetricEntry) && this.mMetricsService != null) {
            Priority fromInt = Priority.fromInt(message.arg1);
            Channel fromInt2 = Channel.fromInt(message.arg2);
            this.mMetricsService.record((MetricEntry) message.obj, fromInt, fromInt2);
            return true;
        }
        DPLogger dPLogger = log;
        dPLogger.error("handleMessageForService", "Received unknown android.os.Message " + message, new Object[0]);
        return false;
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder.asBinder();
    }

    public void setCountryOfResidence(String str) {
        if (str != null && !str.isEmpty()) {
            this.mDeviceUtil.setCountryOfResidence(str);
            return;
        }
        throw new IllegalArgumentException("countryOfResidence must not be null or empty");
    }

    public void setDeviceSerialNumber(String str) {
        if (str != null && !str.isEmpty()) {
            this.mDeviceUtil.setDeviceSerialNumber(str);
            return;
        }
        throw new IllegalArgumentException("deviceId must not be null or empty");
    }

    public void setDeviceType(String str) {
        if (str != null && !str.isEmpty()) {
            this.mDeviceUtil.setDeviceType(str);
            return;
        }
        throw new IllegalArgumentException("deviceType must not be null or empty");
    }

    public void setOAuthHelper(OAuthHelper oAuthHelper) {
        this.mMetricsServiceFactory.setOAuthHelper(oAuthHelper);
    }

    public void setPreferredMarketplace(String str) {
        if (str != null && !str.isEmpty()) {
            this.mDeviceUtil.setPreferredMarketplace(str);
            return;
        }
        throw new IllegalArgumentException("preferredMarketplace must not be null or empty");
    }
}
