package com.amazon.communication.remotesetting;

import amazon.communication.identity.IRServiceEndpoint;
import android.content.Context;
import com.amazon.communication.ir.IRServiceEndpointImpl;
import com.amazon.communication.ir.InvalidIRFileFormatException;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class RemoteSettingManager {
    public static final String APP_REMOTESETTING_ID = "arn:aws:remote-config:us-west-2:487614624751:appConfig:acs83fgk";
    private static final String DEFAULT_REALM = "USAmazon";
    public static final String DEFAULT_SETTING_FILE_NAME = "defaultSetting.json";
    private static final String DOMAIN_KEY = "ir-master.domain";
    private static final String GATEWAY_SERVICE_NAME = "DPGatewayService";
    private static final String JSON_KEY_HEADER = "IdentityResolver.";
    private static final String SDCS_SERVICE_NAME = "SynchronousDeviceCallingService";
    private static final String SERVICE_ENDPOINT_KEY_DELIMITER = ".";
    private static Configuration cachedConfiguration;
    private static Map<String, IRServiceEndpoint> cachedMap;
    protected static RemoteConfigurationManager localCfgManager;
    private static final DPLogger log = new DPLogger("TComm.RemoteSettingManager");
    public static ConfigurationSyncReceiver mConfigSyncReceiver;
    private static CountDownLatch mLatch;
    public static StageSwitchReceiver mStageSwitchReceiver;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class AttributeUtils {
        private static final String BUILD_TYPE = "ro.build.type";
        private static final String PRODUCT_NAME = "ro.product.name";
        private static final String VERSION_NUMBER = "ro.build.version.number";
        private static ReflectionHelper mReflectionHelper = new ReflectionHelper();

        private AttributeUtils() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getBuildType() {
            ReflectionHelper reflectionHelper = mReflectionHelper;
            return (String) reflectionHelper.invokeHiddenMethodWithDefault(reflectionHelper.getHiddenClass("android.os.SystemProperties"), MetricsConstants.Method.CACHE_GET, BUILD_TYPE, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getDeviceType(Context context) {
            try {
                return DeviceDataStore.getInstance(context).getValue("DeviceType");
            } catch (Exception unused) {
                RemoteSettingManager.log.error("AttributeUtils", "Can't find Device type from MAPr5", new Object[0]);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getDsn(Context context) {
            try {
                return DeviceDataStore.getInstance(context).getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            } catch (Exception unused) {
                RemoteSettingManager.log.error("AttributeUtils", "Can't find DSN from MAPr5", new Object[0]);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getProductName() {
            ReflectionHelper reflectionHelper = mReflectionHelper;
            return (String) reflectionHelper.invokeHiddenMethodWithDefault(reflectionHelper.getHiddenClass("android.os.SystemProperties"), MetricsConstants.Method.CACHE_GET, PRODUCT_NAME, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Integer getVersionNumber() {
            try {
                String str = (String) mReflectionHelper.invokeHiddenMethodWithDefault(mReflectionHelper.getHiddenClass("android.os.SystemProperties"), MetricsConstants.Method.CACHE_GET, VERSION_NUMBER, null);
                if (str != null) {
                    return Integer.valueOf(str);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static void addSettingUpdateListener(SettingUpdateListener settingUpdateListener) {
        ConfigurationSyncService.addSettingUpdateListener(settingUpdateListener);
    }

    private static String buildServiceEndpointKey(String str, String str2, String str3) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116(JSON_KEY_HEADER, str, ".", str2, ".");
        outline116.append(str3);
        return outline116.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RemoteConfigurationManager createDecoratedConfigurationManager(Context context, String str, String str2) throws IOException, JSONException {
        if (context != null) {
            if (str == null) {
                throw new IllegalArgumentException("The app configuration ID may not be null.");
            }
            if (str2 != null) {
                return decorate(RemoteConfigurationManager.forAppId(context.getApplicationContext(), str).withDefaultConfiguration(FileUtils.readJsonFile(context.getApplicationContext().getAssets(), str2)).createOrGet(), context);
            }
            throw new IllegalArgumentException("The name of the file containing the default configuration may not be null.");
        }
        throw new IllegalArgumentException("The context may not be null.");
    }

    private static RemoteConfigurationManager decorate(RemoteConfigurationManager remoteConfigurationManager, Context context) {
        Attributes openAttributes = remoteConfigurationManager.openAttributes();
        String deviceType = AttributeUtils.getDeviceType(context);
        String dsn = AttributeUtils.getDsn(context);
        String buildType = AttributeUtils.getBuildType();
        String productName = AttributeUtils.getProductName();
        Integer versionNumber = AttributeUtils.getVersionNumber();
        if (deviceType != null) {
            openAttributes.addAttribute("DeviceType", deviceType);
            log.verbose("ARCUS Attributes", "DeviceType", deviceType);
        } else {
            log.info("ARCUS Attributes", "Can't find the device type from MAP", new Object[0]);
        }
        if (dsn != null) {
            openAttributes.addAttribute(RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, dsn);
            log.verbose("ARCUS Attributes", "DSN", dsn);
        } else {
            log.info("ARCUS Attributes", "Can't find the DSN from MAP", new Object[0]);
        }
        if (buildType != null) {
            openAttributes.addAttribute("BuildType", buildType);
            log.verbose("ARCUS Attributes", "BuildType", buildType);
        } else {
            log.info("ARCUS Attributes", "Can't find the device build type", new Object[0]);
        }
        if (productName != null) {
            openAttributes.addAttribute(RemoteConfigurationAndroidClientContextDecorator.DEVICE_CODE_NAME, productName);
            log.verbose("ARCUS Attributes", RemoteConfigurationAndroidClientContextDecorator.DEVICE_CODE_NAME, productName);
        } else {
            log.info("ARCUS Attributes", "Can't find the product name", new Object[0]);
        }
        if (versionNumber != null) {
            openAttributes.addAttribute(RemoteConfigurationAndroidClientContextDecorator.BUILD_NUMBER, versionNumber);
            log.verbose("ARCUS Attributes", RemoteConfigurationAndroidClientContextDecorator.BUILD_NUMBER, versionNumber);
        } else {
            log.info("ARCUS Attributes", "Can't find the device build number", new Object[0]);
        }
        return remoteConfigurationManager;
    }

    public static Boolean getBooleanValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return Boolean.valueOf(cachedConfiguration.getAsJsonObject().getBoolean(str));
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static IRServiceEndpoint getCachedEndpoint(String str, String str2) {
        Map<String, IRServiceEndpoint> map = cachedMap;
        return map.get(str + "." + str2);
    }

    public static Double getDoubleValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return Double.valueOf(cachedConfiguration.getAsJsonObject().getDouble(str));
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static Integer getIntValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return Integer.valueOf(cachedConfiguration.getAsJsonObject().getInt(str));
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static String getJsonAsString(String[] strArr) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            JSONObject asJsonObject = cachedConfiguration.getAsJsonObject();
            for (String str : strArr) {
                asJsonObject = asJsonObject.getJSONObject(str);
            }
            return asJsonObject.toString();
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static String getJsonValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return cachedConfiguration.getAsJsonObject().getJSONObject(str).toString();
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static Long getLongValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return Long.valueOf(cachedConfiguration.getAsJsonObject().getLong(str));
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static Boolean getOptBooleanValue(String str, Boolean bool) {
        if (localCfgManager == null) {
            return bool;
        }
        try {
            return Boolean.valueOf(cachedConfiguration.getAsJsonObject().getBoolean(str));
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return bool;
        }
    }

    public static Double getOptDoubleValue(String str, Double d) {
        if (localCfgManager == null) {
            return d;
        }
        try {
            return Double.valueOf(cachedConfiguration.getAsJsonObject().getDouble(str));
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return d;
        }
    }

    public static Integer getOptIntValue(String str, Integer num) {
        if (localCfgManager == null) {
            return num;
        }
        try {
            return Integer.valueOf(cachedConfiguration.getAsJsonObject().getInt(str));
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return num;
        }
    }

    public static String getOptJsonValue(String str, String str2) {
        if (localCfgManager == null) {
            return str2;
        }
        try {
            return cachedConfiguration.getAsJsonObject().getJSONObject(str).toString();
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return str2;
        }
    }

    public static Long getOptLongValue(String str, Long l) {
        if (localCfgManager == null) {
            return l;
        }
        try {
            return Long.valueOf(cachedConfiguration.getAsJsonObject().getLong(str));
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return l;
        }
    }

    public static String getOptStringValue(String str, String str2) {
        if (localCfgManager == null) {
            return str2;
        }
        try {
            return cachedConfiguration.getAsJsonObject().getString(str);
        } catch (JSONException unused) {
            log.info("RemoteSetting", "Can't read value from remote setting; will use default", JSONException.class.getCanonicalName(), "key", str);
            return str2;
        }
    }

    public static String getStringValue(String str) {
        if (localCfgManager == null) {
            return null;
        }
        try {
            return cachedConfiguration.getAsJsonObject().getString(str);
        } catch (JSONException e) {
            log.warn("RemoteSetting", "Can't read value from remote setting", e);
            return null;
        }
    }

    public static void initialize(Context context, CountDownLatch countDownLatch) {
        mLatch = countDownLatch;
        mConfigSyncReceiver = new ConfigurationSyncReceiver();
        mStageSwitchReceiver = new StageSwitchReceiver();
        initialize(context);
    }

    public static void removeSettingUpdateListener(SettingUpdateListener settingUpdateListener) {
        ConfigurationSyncService.removeSettingUpdateListener(settingUpdateListener);
    }

    public static void setCachedConfiguration(Configuration configuration) {
        cachedConfiguration = configuration;
    }

    public static void setCachedMap() {
        try {
            String string = cachedConfiguration.getAsJsonObject().getString("ir-master.domain");
            String buildServiceEndpointKey = buildServiceEndpointKey(GATEWAY_SERVICE_NAME, string, "USAmazon");
            String buildServiceEndpointKey2 = buildServiceEndpointKey(SDCS_SERVICE_NAME, string, "USAmazon");
            String jSONObject = cachedConfiguration.getAsJsonObject().getJSONObject(buildServiceEndpointKey).toString();
            String jSONObject2 = cachedConfiguration.getAsJsonObject().getJSONObject(buildServiceEndpointKey2).toString();
            IRServiceEndpoint parse = IRServiceEndpointImpl.parse(string, "USAmazon", jSONObject);
            IRServiceEndpoint parse2 = IRServiceEndpointImpl.parse(string, "USAmazon", jSONObject2);
            Map<String, IRServiceEndpoint> map = cachedMap;
            map.put("DPGatewayService." + string, parse);
            Map<String, IRServiceEndpoint> map2 = cachedMap;
            map2.put("SynchronousDeviceCallingService." + string, parse2);
        } catch (InvalidIRFileFormatException e) {
            log.error("setCachedMap", "error parsing service endpoint", e);
        } catch (IOException e2) {
            log.error("setCachedMap", "error parsing service endpoint", e2);
        } catch (JSONException e3) {
            log.error("setCachedMap", "Can't read value from remote setting, failed to set cachedMap", e3);
        }
    }

    public static void updateSettings(Context context, JSONObject jSONObject) throws IOException, JSONException, IllegalArgumentException {
        if (context != null && jSONObject != null) {
            RemoteConfigurationManager createDecoratedConfigurationManager = createDecoratedConfigurationManager(context, APP_REMOTESETTING_ID, "defaultSetting.json");
            JSONObject asJsonObject = createDecoratedConfigurationManager.openConfiguration().getAsJsonObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                asJsonObject.put(str, jSONObject.get(str));
            }
            createDecoratedConfigurationManager.overwriteConfiguration(asJsonObject);
            setCachedConfiguration(createDecoratedConfigurationManager.openConfiguration());
            setCachedMap();
            return;
        }
        throw new IllegalArgumentException("The arguments may not be null");
    }

    public static void initialize(final Context context) {
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.amazon.communication.remotesetting.RemoteSettingManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RemoteSettingManager.localCfgManager = RemoteSettingManager.createDecoratedConfigurationManager(context, RemoteSettingManager.APP_REMOTESETTING_ID, "defaultSetting.json");
                    RemoteSettingManager.setCachedConfiguration(RemoteSettingManager.localCfgManager.openConfiguration());
                    Map unused = RemoteSettingManager.cachedMap = new HashMap();
                    RemoteSettingManager.setCachedMap();
                    RemoteSettingManager.log.info("initialize", "RemoteSettingManager successfully initialized.", "app Id", RemoteSettingManager.APP_REMOTESETTING_ID);
                    ConfigurationSyncService.setRemoteConfigurationManager(RemoteSettingManager.localCfgManager);
                    PeriodicConfigSyncUtils.initPeriodicConfigSyncing(context.getApplicationContext());
                    if (RemoteSettingManager.mLatch == null) {
                        return;
                    }
                    RemoteSettingManager.mLatch.countDown();
                } catch (IOException e) {
                    RemoteSettingManager.log.error("initialize", "Unable to initialize configuration manager", e);
                } catch (JSONException e2) {
                    RemoteSettingManager.log.error("initialize", "Unable to initialize configuration manager", e2);
                }
            }
        });
    }
}
