package com.amazonaws.mobileconnectors.amazonmobileanalytics;

import android.content.Context;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.DefaultAnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.http.RequestTimingHandler;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.Preconditions;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.DefaultEventClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.FileSessionStore;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.InternalSessionClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.DefaultSessionClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate.EncodingValidator;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate.FileManagerValidator;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate.PermissionValidator;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.AmazonMobileAnalyticsClient;
import com.amazonaws.util.VersionInfoUtils;
import java.util.HashMap;
@Deprecated
/* loaded from: classes13.dex */
public class MobileAnalyticsManager {
    private static final String TAG = "MobileAnalyticsManager";
    private final AnalyticsContext context;
    private final InternalEventClient eventClient;
    private final InternalSessionClient sessionClient;
    private static final String SDK_VERSION = VersionInfoUtils.getVersion();
    private static final String SDK_NAME = "AmazonMobileAnalyticsSDK";
    private static final SDKInfo sdkInfo = new SDKInfo(SDK_NAME, SDK_VERSION);
    private static final PermissionValidator internetPermissionValidator = new PermissionValidator("android.permission.INTERNET");
    private static final PermissionValidator accessNetworkStatePermissionValidator = new PermissionValidator("android.permission.ACCESS_NETWORK_STATE");
    private static final HashMap<String, MobileAnalyticsManager> instanceMap = new HashMap<>();
    private static final EncodingValidator encodingValidator = new EncodingValidator("UTF-8");
    private static final FileManagerValidator fileManagerValidator = new FileManagerValidator();

    MobileAnalyticsManager(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, AnalyticsConfig analyticsConfig, AnalyticsCallback<MobileAnalyticsManager> analyticsCallback) throws InitializationException {
        try {
            Preconditions.checkNotNull(aWSCredentialsProvider, "The ersClient provided must not be null");
            Preconditions.checkNotNull(context, "The application context provided must not be null");
            Preconditions.checkNotNull(analyticsConfig, "The options provided must not be null");
            Preconditions.checkNotNull(str, "The app ID specified must not be null");
            AmazonMobileAnalyticsClient amazonMobileAnalyticsClient = new AmazonMobileAnalyticsClient(aWSCredentialsProvider, analyticsConfig.getClientConfiguration());
            internetPermissionValidator.validate(context);
            accessNetworkStatePermissionValidator.validate(context);
            encodingValidator.validate();
            this.context = new DefaultAnalyticsContext(amazonMobileAnalyticsClient, context, regions, str, sdkInfo, analyticsConfig.getAllowWANDelivery());
            fileManagerValidator.validate(this.context);
            this.eventClient = new DefaultEventClient(this.context, analyticsConfig.getAllowEventCollection());
            this.sessionClient = new DefaultSessionClient(this.context, this.eventClient, new FileSessionStore(this.context));
            this.context.getERSClient().addRequestHandler(new RequestTimingHandler(this.context.getSystem().getConnectivity(), this.eventClient));
            if (analyticsCallback != null) {
                analyticsCallback.onComplete(this);
            }
            this.sessionClient.startSession();
            String.format("Amazon Mobile Analytics SDK(%s) initialization successfully completed", SDK_VERSION);
        } catch (RuntimeException e) {
            throw new InitializationException(e.getLocalizedMessage());
        }
    }

    public static MobileAnalyticsManager getInstance(String str) {
        MobileAnalyticsManager mobileAnalyticsManager;
        synchronized (instanceMap) {
            mobileAnalyticsManager = instanceMap.get(str);
        }
        return mobileAnalyticsManager;
    }

    public static MobileAnalyticsManager getOrCreateInstance(Context context, String str, String str2) throws InitializationException {
        return getOrCreateInstance(context, str, Regions.US_EAST_1, new CognitoCachingCredentialsProvider(context, str2, Regions.US_EAST_1), null, null);
    }

    public EventClient getEventClient() {
        return this.eventClient;
    }

    public SessionClient getSessionClient() {
        return this.sessionClient;
    }

    public static MobileAnalyticsManager getOrCreateInstance(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider) throws InitializationException {
        return getOrCreateInstance(context, str, regions, aWSCredentialsProvider, null, null);
    }

    public static MobileAnalyticsManager getOrCreateInstance(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, AnalyticsConfig analyticsConfig) throws InitializationException {
        return getOrCreateInstance(context, str, regions, aWSCredentialsProvider, analyticsConfig, null);
    }

    public static MobileAnalyticsManager getOrCreateInstance(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, AnalyticsCallback<MobileAnalyticsManager> analyticsCallback) throws InitializationException {
        return getOrCreateInstance(context, str, regions, aWSCredentialsProvider, null, analyticsCallback);
    }

    public static MobileAnalyticsManager getOrCreateInstance(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, AnalyticsConfig analyticsConfig, AnalyticsCallback<MobileAnalyticsManager> analyticsCallback) throws InitializationException {
        MobileAnalyticsManager mobileAnalyticsManager;
        synchronized (instanceMap) {
            if (!instanceMap.containsKey(str)) {
                if (analyticsConfig == null) {
                    analyticsConfig = new AnalyticsConfig();
                }
                instanceMap.put(str, new MobileAnalyticsManager(context, str, regions, aWSCredentialsProvider, analyticsConfig, analyticsCallback));
            }
            mobileAnalyticsManager = instanceMap.get(str);
        }
        return mobileAnalyticsManager;
    }

    MobileAnalyticsManager(Context context, String str, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, AnalyticsConfig analyticsConfig, AnalyticsCallback<MobileAnalyticsManager> analyticsCallback, AnalyticsContext analyticsContext, InternalEventClient internalEventClient, InternalSessionClient internalSessionClient, AmazonMobileAnalyticsClient amazonMobileAnalyticsClient) throws InitializationException {
        try {
            Preconditions.checkNotNull(aWSCredentialsProvider, "The ersClient provided must not be null");
            Preconditions.checkNotNull(context, "The application context provided must not be null");
            Preconditions.checkNotNull(analyticsConfig, "The options provided must not be null");
            Preconditions.checkNotNull(str, "The app ID specified must not be null");
            internetPermissionValidator.validate(context);
            accessNetworkStatePermissionValidator.validate(context);
            encodingValidator.validate();
            this.context = analyticsContext;
            this.sessionClient = internalSessionClient;
            this.eventClient = internalEventClient;
            fileManagerValidator.validate(this.context);
            this.context.getERSClient().addRequestHandler(new RequestTimingHandler(this.context.getSystem().getConnectivity(), internalEventClient));
            if (analyticsCallback != null) {
                analyticsCallback.onComplete(this);
            }
            internalSessionClient.startSession();
            String.format("Amazon Mobile Analytics SDK(%s) initialization successfully completed", SDK_VERSION);
        } catch (RuntimeException e) {
            throw new InitializationException(e.getLocalizedMessage());
        }
    }
}
