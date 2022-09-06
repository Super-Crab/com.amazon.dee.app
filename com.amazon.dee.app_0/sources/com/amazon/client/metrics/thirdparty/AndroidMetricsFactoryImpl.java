package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
/* loaded from: classes11.dex */
public class AndroidMetricsFactoryImpl extends AbstractMetricsFactoryImpl {
    private final Context mContext;

    private AndroidMetricsFactoryImpl(Context context) {
        this.mContext = context;
    }

    public static synchronized MetricsFactory getInstance(Context context) {
        MetricsFactory metricsFactory;
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (AbstractMetricsFactoryImpl.sMetricsFactory == null) {
                AbstractMetricsFactoryImpl.sMetricsFactory = new AndroidMetricsFactoryImpl(context);
            }
            metricsFactory = AbstractMetricsFactoryImpl.sMetricsFactory;
        }
        return metricsFactory;
    }

    public static synchronized void setCountryOfResidence(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.getInstance(context).setCountryOfResidence(str);
        }
    }

    public static synchronized void setDeviceId(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.getInstance(context).setDeviceSerialNumber(str);
        }
    }

    public static synchronized void setDeviceType(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.getInstance(context).setDeviceType(str);
        }
    }

    public static synchronized void setMetricsConfiguration(MetricsConfiguration metricsConfiguration) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.setMetricsConfiguration(metricsConfiguration);
        }
    }

    public static synchronized void setOAuthHelper(Context context, OAuthHelper oAuthHelper) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.getInstance(context).setOAuthHelper(oAuthHelper);
        }
    }

    public static synchronized void setPreferredMarketplace(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.getInstance(context).setPreferredMarketplace(str);
        }
    }

    public static synchronized void shutdown() {
        synchronized (AndroidMetricsFactoryImpl.class) {
            GenericMetricsServiceAdapter.shutdownInstance();
            AbstractMetricsFactoryImpl.sMetricsService = null;
            AbstractMetricsFactoryImpl.sMetricsFactory = null;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.AbstractMetricsFactoryImpl
    protected IMetricsService getService() {
        if (AbstractMetricsFactoryImpl.sMetricsService == null) {
            AbstractMetricsFactoryImpl.sMetricsService = GenericMetricsServiceAdapter.getInstance(this.mContext).getMetricsService();
        }
        return AbstractMetricsFactoryImpl.sMetricsService;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public String getSessionID() {
        return "";
    }

    @Override // com.amazon.client.metrics.thirdparty.BaseMetricsFactoryImpl
    protected boolean shouldRecordMetrics() {
        return true;
    }

    @Deprecated
    public static synchronized void shutdown(Context context) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            shutdown();
        }
    }
}
