package com.amazon.client.metrics.common;

import android.content.Context;
import com.amazon.client.metrics.common.configuration.MetricsConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationConverter;
import com.amazon.client.metrics.common.internal.android.AndroidMetricsFactory;
import com.amazon.client.metrics.common.internal.fireos.FireOSMetricsFactory;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import com.amazon.client.metrics.common.transport.OAuthHelper;
import com.amazon.client.metrics.common.transport.internal.android.AndroidOAuthHelperImpl;
/* loaded from: classes11.dex */
public class AndroidMetricsFactoryImpl {
    private static AndroidMetricsFactoryImpl sAndroidMetricsFactoryImpl;
    private static MetricsFactory sMetricsFactory;

    private AndroidMetricsFactoryImpl() {
    }

    public static synchronized MetricsFactory getInstance(Context context) {
        MetricsFactory metricsFactory;
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (context != null) {
                if (sAndroidMetricsFactoryImpl == null) {
                    sAndroidMetricsFactoryImpl = new AndroidMetricsFactoryImpl();
                    sMetricsFactory = getMetricsFactory(context);
                }
                metricsFactory = sMetricsFactory;
            } else {
                throw new NullPointerException("Context may not be null");
            }
        }
        return metricsFactory;
    }

    private static MetricsFactory getMetricsFactory(Context context) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            return FireOSMetricsFactory.getInstance(context);
        }
        return AndroidMetricsFactory.getInstance(context);
    }

    public static synchronized void setCountryOfResidence(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setCountryOfResidence(context, str);
            }
        }
    }

    public static synchronized void setDeviceId(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setDeviceId(context, str);
            }
        }
    }

    public static synchronized void setDeviceType(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setDeviceType(context, str);
            }
        }
    }

    public static synchronized void setMetricsConfiguration(MetricsConfiguration metricsConfiguration) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setMetricsConfiguration(MetricsConfigurationConverter.convertMetricsConfiguration_fromCommonToThirdParty(metricsConfiguration));
            }
        }
    }

    public static synchronized void setOAuthHelper(Context context, OAuthHelper oAuthHelper) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setOAuthHelper(context, new AndroidOAuthHelperImpl(oAuthHelper));
            }
        }
    }

    public static synchronized void setPreferredMarketplace(Context context, String str) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setPreferredMarketplace(context, str);
            }
        }
    }

    public static synchronized void shutdown() {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.shutdown();
            }
        }
    }

    @Deprecated
    public static synchronized void shutdown(Context context) {
        synchronized (AndroidMetricsFactoryImpl.class) {
            if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
                com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.shutdown(context);
            }
        }
    }
}
