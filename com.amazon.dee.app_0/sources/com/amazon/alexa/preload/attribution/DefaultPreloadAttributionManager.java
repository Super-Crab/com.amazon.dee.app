package com.amazon.alexa.preload.attribution;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.preload.attribution.country.AttributionLocationManager;
import com.amazon.alexa.preload.attribution.utils.DeviceUtils;
import com.amazon.appmanager.lib.PreloadManager;
import com.amazon.maft.metrics.NullMetricsFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes9.dex */
public class DefaultPreloadAttributionManager implements PreloadAttributionManager, AttributionLocationManager.LocationChangeListener {
    @VisibleForTesting
    static final String ATAG_KEY = "atag";
    @VisibleForTesting
    static final String ATAG_SIM_KEY = "atag_sim_test";
    @VisibleForTesting
    static final String DEFAULT_AMOK_ATTRIBUTION_TAG_FORMAT = "alexa-android-any-{0}_{1}-{2}_ampd_00-phone-none";
    @VisibleForTesting
    static final String DEFAULT_DYNAMIC_ATTRIBUTION_TAG_FORMAT = "alexa-android-{0}-{1}-{2}_{3}_ampd_{4}-phone-dynamic";
    private static final String HYPHEN = "-";
    private static final String NO_SIM = "nosim";
    private static final String PERIOD = ".";
    private static final String SHARED_PREFS_FILE_NAME = "AttributionTag";
    private static final String TAG = "DefaultPreloadAttr";
    private AttributionLocationManager mAttributionLocationManager;
    private final AttributionTagListener mAttributionTagListener;
    private String mAttributionTagWithoutRegionId;
    private String mCachedAttributionTag;
    private boolean mCanComputeAttributionTag;
    private final Context mContext;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final DeviceUtils mDeviceUtils;
    private final ExecutorService mExecutorService;
    private final FeatureQueryBridge mFeatureQueryBridge;
    private final PreloadManager mPreloadManager;
    private final List<String> mPropertiesFilePaths;
    private SharedPreferences mSharedPreferences;
    private static final String CONFIG_PATH_SYSTEM = "/system/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_OEM = "/oem/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_CARRIER = "/carrier/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_PRODUCT = "/product/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_VENDOR = "/vendor/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_OPLUS_REGION = "/my_region/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_OPLUS_CARRIER = "/my_carrier/etc/amzn.alexa.properties";
    private static final String CONFIG_PATH_OPLUS_BIGBALL = "/my_bigball/etc/amzn.alexa.properties";
    private static final String[] PROPERTIES_FILE_PATH_LIST = {CONFIG_PATH_SYSTEM, CONFIG_PATH_OEM, CONFIG_PATH_CARRIER, CONFIG_PATH_PRODUCT, CONFIG_PATH_VENDOR, CONFIG_PATH_OPLUS_REGION, CONFIG_PATH_OPLUS_CARRIER, CONFIG_PATH_OPLUS_BIGBALL};

    public DefaultPreloadAttributionManager(@NonNull Context context) {
        this(context, null, null);
    }

    @Nullable
    private String getDeviceType() {
        try {
            return getDeviceTypeInformation().getType();
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to get device type from the current device: ", e);
            return null;
        }
    }

    private DeviceInformation getDeviceTypeInformation() {
        return this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(this.mContext);
    }

    private boolean isCurrentDeviceAMPD() {
        return getDeviceTypeInformation() != null;
    }

    private String removeUnwantedChars(@NonNull String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "");
    }

    @VisibleForTesting
    void addPropertiesFilePath(@NonNull String str) {
        this.mPropertiesFilePaths.add(str);
    }

    @VisibleForTesting
    void computeAttributionTag() {
        this.mAttributionTagWithoutRegionId = getAttributionTagWithoutRegionId();
        if (!TextUtils.isEmpty(this.mAttributionTagWithoutRegionId)) {
            getAttributionLocationManager().registerLocationChangeListener(this);
            synchronized (this) {
                this.mCachedAttributionTag = this.mAttributionTagWithoutRegionId + "-" + getAttributionLocationManager().getLocationString();
                StringBuilder sb = new StringBuilder();
                sb.append("Successfully generated the attribution tag: ");
                sb.append(this.mCachedAttributionTag);
                Log.i(TAG, sb.toString());
            }
        }
        AttributionTagListener attributionTagListener = this.mAttributionTagListener;
        if (attributionTagListener != null) {
            attributionTagListener.onAttributionTagComputed();
        }
        this.mCanComputeAttributionTag = true;
    }

    @VisibleForTesting
    void computeAttributionTagInBackground() {
        this.mExecutorService.submit(new Runnable() { // from class: com.amazon.alexa.preload.attribution.DefaultPreloadAttributionManager.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultPreloadAttributionManager.this.computeAttributionTag();
            }
        });
    }

    @VisibleForTesting
    AttributionLocationManager getAttributionLocationManager() {
        if (this.mAttributionLocationManager == null) {
            this.mAttributionLocationManager = new AttributionLocationManager(this.mContext, getSharedPreferences());
        }
        return this.mAttributionLocationManager;
    }

    @Override // com.amazon.alexa.preload.attribution.PreloadAttributionManager
    @Nullable
    public synchronized String getAttributionTag() {
        if (this.mCachedAttributionTag == null && this.mCanComputeAttributionTag && isCurrentDeviceAMPD()) {
            computeAttributionTagInBackground();
        }
        return this.mCachedAttributionTag;
    }

    @Nullable
    @VisibleForTesting
    String getAttributionTagFromPropertiesFile() {
        Iterator<String> it2 = this.mPropertiesFilePaths.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            if (new File(next).exists()) {
                String str = "Properties file found at: " + next;
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(next));
                    return properties.getProperty(ATAG_KEY);
                } catch (IOException e) {
                    Log.e(TAG, "Failure while reading attribution tag from file: ", e);
                    return null;
                }
            }
        }
    }

    @Nullable
    @VisibleForTesting
    String getAttributionTagWithoutRegionId() {
        String string = getSharedPreferences().getString(ATAG_KEY, "");
        if (TextUtils.isEmpty(string)) {
            try {
                string = this.mPreloadManager.getPreloadAssociateTagWithoutRegionId(this.mContext, new NullMetricsFactory());
            } catch (RemoteException | IllegalArgumentException | SecurityException e) {
                GeneratedOutlineSupport1.outline156("Failed to read the attribution tag from MDIP: ", e, TAG);
            }
            if (TextUtils.isEmpty(string)) {
                string = getAttributionTagFromPropertiesFile();
            }
            if (TextUtils.isEmpty(string)) {
                if (this.mDeviceUtils.isCurrentDeviceAmokEnabled()) {
                    string = getDefaultAmokAttributionTagWithoutRegionId();
                } else if (isCurrentDeviceAMPD()) {
                    string = getDefaultDynamicAttributionTagWithoutRegionId();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                getSharedPreferences().edit().putString(ATAG_KEY, string).apply();
            }
        }
        return string;
    }

    @Nullable
    @VisibleForTesting
    String getDefaultAmokAttributionTagWithoutRegionId() {
        if (this.mDeviceUtils.isCurrentDeviceAmokEnabled()) {
            return MessageFormat.format(DEFAULT_AMOK_ATTRIBUTION_TAG_FORMAT, this.mDeviceUtils.getManufacturer().replace("-", "."), this.mDeviceUtils.getBrand().replace("-", "."), this.mDeviceUtils.getModel().replace("-", "."));
        }
        return null;
    }

    @VisibleForTesting
    String getDefaultDynamicAttributionTagWithoutRegionId() {
        try {
            return MessageFormat.format(DEFAULT_DYNAMIC_ATTRIBUTION_TAG_FORMAT, removeUnwantedChars(getSimCarrierIdName()), removeUnwantedChars(this.mDeviceUtils.getManufacturer()), removeUnwantedChars(this.mDeviceUtils.getModel()), removeUnwantedChars(this.mDeviceUtils.getProduct()), getDeviceType());
        } catch (IllegalArgumentException | NullPointerException e) {
            Log.e(TAG, "Failed to get dynamic attribution tag from the current device: ", e);
            return null;
        }
    }

    @VisibleForTesting
    SharedPreferences getSharedPreferences() {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = this.mContext.getSharedPreferences(SHARED_PREFS_FILE_NAME, 0);
        }
        return this.mSharedPreferences;
    }

    @NonNull
    @VisibleForTesting
    String getSimCarrierIdName() {
        String simOperatorName;
        if (getSharedPreferences().contains(ATAG_SIM_KEY)) {
            return getSharedPreferences().getString(ATAG_SIM_KEY, "");
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (telephonyManager == null || (simOperatorName = telephonyManager.getSimOperatorName()) == null || simOperatorName.trim().length() == 0) {
            return NO_SIM;
        }
        getSharedPreferences().edit().putString(ATAG_SIM_KEY, simOperatorName).apply();
        return simOperatorName;
    }

    @Override // com.amazon.alexa.preload.attribution.country.AttributionLocationManager.LocationChangeListener
    public void onLocationChanged(@NonNull String str) {
        this.mCachedAttributionTag = GeneratedOutlineSupport1.outline92(new StringBuilder(), this.mAttributionTagWithoutRegionId, "-", str);
        AttributionTagListener attributionTagListener = this.mAttributionTagListener;
        if (attributionTagListener != null) {
            attributionTagListener.onAttributionTagComputed();
        }
    }

    public DefaultPreloadAttributionManager(@NonNull Context context, @Nullable AttributionTagListener attributionTagListener, @Nullable FeatureQueryBridge featureQueryBridge) {
        this.mContext = context;
        this.mAttributionTagListener = attributionTagListener;
        this.mFeatureQueryBridge = featureQueryBridge;
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.mPreloadManager = PreloadManager.getDefault();
        this.mPropertiesFilePaths = Arrays.asList(PROPERTIES_FILE_PATH_LIST);
        this.mDeviceUtils = new DeviceUtils();
        this.mDeviceTypeInformationProvider = DeviceTypeInformationProvider.getInstance(context);
        computeAttributionTagInBackground();
    }

    @VisibleForTesting
    DefaultPreloadAttributionManager(@NonNull Context context, @Nullable AttributionTagListener attributionTagListener, @Nullable FeatureQueryBridge featureQueryBridge, @NonNull SharedPreferences sharedPreferences, @NonNull ExecutorService executorService, @NonNull AttributionLocationManager attributionLocationManager, @NonNull PreloadManager preloadManager, @NonNull DeviceUtils deviceUtils, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider) {
        this.mContext = context;
        this.mAttributionTagListener = attributionTagListener;
        this.mFeatureQueryBridge = featureQueryBridge;
        this.mSharedPreferences = sharedPreferences;
        this.mExecutorService = executorService;
        this.mAttributionLocationManager = attributionLocationManager;
        this.mPreloadManager = preloadManager;
        this.mPropertiesFilePaths = new ArrayList();
        this.mDeviceUtils = deviceUtils;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
    }
}
