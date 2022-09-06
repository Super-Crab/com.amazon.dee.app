package com.bugsnag;

import android.content.Context;
import com.amazon.device.messaging.ADMConstants;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Client;
import com.bugsnag.android.Configuration;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
/* loaded from: classes11.dex */
public class BugsnagReactNative extends ReactContextBaseJavaModule {
    static final Logger logger = Logger.getLogger("bugsnag-react-native");
    private String bugsnagAndroidVersion;
    private String libraryVersion;
    private ReactContext reactContext;

    public BugsnagReactNative(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.libraryVersion = null;
        this.bugsnagAndroidVersion = null;
    }

    private void configureRuntimeOptions(Client client, ReadableMap readableMap) {
        ReadableArray array;
        String string;
        String string2;
        String string3;
        client.setIgnoreClasses("com.facebook.react.common.JavascriptException");
        Configuration config = client.getConfig();
        if (readableMap.hasKey("appVersion") && (string3 = readableMap.getString("appVersion")) != null && string3.length() > 0) {
            client.setAppVersion(string3);
        }
        String str = null;
        String string4 = readableMap.hasKey("endpoint") ? readableMap.getString("endpoint") : null;
        if (readableMap.hasKey("sessionsEndpoint")) {
            str = readableMap.getString("sessionsEndpoint");
        }
        if (string4 != null && string4.length() > 0) {
            config.setEndpoints(string4, str);
        } else if (str != null && str.length() > 0) {
            logger.warning("The session tracking endpoint should not be set without the error reporting endpoint.");
        }
        if (readableMap.hasKey("releaseStage") && (string2 = readableMap.getString("releaseStage")) != null && string2.length() > 0) {
            client.setReleaseStage(string2);
        }
        if (readableMap.hasKey("autoNotify")) {
            if (readableMap.getBoolean("autoNotify")) {
                client.enableExceptionHandler();
            } else {
                client.disableExceptionHandler();
            }
        }
        if (readableMap.hasKey("codeBundleId") && (string = readableMap.getString("codeBundleId")) != null && string.length() > 0) {
            client.addToTab(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, "codeBundleId", string);
        }
        if (readableMap.hasKey("notifyReleaseStages") && (array = readableMap.getArray("notifyReleaseStages")) != null && array.size() > 0) {
            String[] strArr = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                strArr[i] = array.getString(i);
            }
            client.setNotifyReleaseStages(strArr);
        }
        if (readableMap.hasKey("automaticallyCollectBreadcrumbs")) {
            config.setAutomaticallyCollectBreadcrumbs(readableMap.getBoolean("automaticallyCollectBreadcrumbs"));
        }
        if (readableMap.hasKey("autoCaptureSessions")) {
            boolean z = readableMap.getBoolean("autoCaptureSessions");
            config.setAutoCaptureSessions(z);
            if (!z) {
                return;
            }
            client.startSession();
        }
    }

    private Client getClient(String str) {
        try {
            return Bugsnag.getClient();
        } catch (IllegalStateException unused) {
            if (str != null) {
                return Bugsnag.init(this.reactContext, str);
            }
            return Bugsnag.init(this.reactContext);
        }
    }

    public static ReactPackage getPackage() {
        return new BugsnagPackage();
    }

    private BreadcrumbType parseBreadcrumbType(String str) {
        BreadcrumbType[] values;
        for (BreadcrumbType breadcrumbType : BreadcrumbType.values()) {
            if (breadcrumbType.toString().equals(str)) {
                return breadcrumbType;
            }
        }
        return BreadcrumbType.MANUAL;
    }

    private Map<String, String> readStringMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            ReadableMap mo6945getMap = readableMap.mo6945getMap(nextKey);
            String string = mo6945getMap.getString("type");
            char c = 65535;
            switch (string.hashCode()) {
                case -1034364087:
                    if (string.equals("number")) {
                        c = 1;
                        break;
                    }
                    break;
                case -891985903:
                    if (string.equals("string")) {
                        c = 2;
                        break;
                    }
                    break;
                case 107868:
                    if (string.equals(com.amazon.alexa.auth.BuildConfig.FLAVOR_authtype)) {
                        c = 3;
                        break;
                    }
                    break;
                case 64711720:
                    if (string.equals("boolean")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                hashMap.put(nextKey, String.valueOf(mo6945getMap.getBoolean("value")));
            } else if (c == 1) {
                hashMap.put(nextKey, String.valueOf(mo6945getMap.getDouble("value")));
            } else if (c == 2) {
                hashMap.put(nextKey, mo6945getMap.getString("value"));
            } else if (c == 3) {
                hashMap.put(nextKey, String.valueOf(readStringMap(mo6945getMap.mo6945getMap("value"))));
            }
        }
        return hashMap;
    }

    public static Client start(Context context) {
        Client init = Bugsnag.init(context);
        init.setAutoCaptureSessions(false);
        return init;
    }

    public static Client startWithApiKey(Context context, String str) {
        Client init = Bugsnag.init(context, str);
        init.setAutoCaptureSessions(false);
        return init;
    }

    public static Client startWithConfiguration(Context context, Configuration configuration) {
        configuration.setAutoCaptureSessions(false);
        return Bugsnag.init(context, configuration);
    }

    @ReactMethod
    public void clearUser() {
        Bugsnag.clearUser();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "BugsnagReactNative";
    }

    @ReactMethod
    public void leaveBreadcrumb(ReadableMap readableMap) {
        Bugsnag.leaveBreadcrumb(readableMap.getString("name"), parseBreadcrumbType(readableMap.getString("type")), readStringMap(readableMap.mo6945getMap("metadata")));
    }

    @ReactMethod
    public void notify(ReadableMap readableMap) {
        notifyBlocking(readableMap, false, null);
    }

    @ReactMethod
    public void notifyBlocking(ReadableMap readableMap, boolean z, Callback callback) {
        if (!readableMap.hasKey("errorClass")) {
            logger.warning("Bugsnag could not notify: No error class");
        } else if (!readableMap.hasKey("stacktrace")) {
            logger.warning("Bugsnag could not notify: No stacktrace");
        } else {
            String string = readableMap.getString("errorClass");
            String string2 = readableMap.getString("errorMessage");
            String string3 = readableMap.getString("stacktrace");
            logger.info(String.format("Sending exception: %s - %s %s\n", string, string2, string3));
            JavaScriptException javaScriptException = new JavaScriptException(string, string2, string3);
            DiagnosticsCallback diagnosticsCallback = new DiagnosticsCallback(this.libraryVersion, this.bugsnagAndroidVersion, readableMap);
            HashMap hashMap = new HashMap();
            String string4 = readableMap.getString("severity");
            String string5 = readableMap.getString("severityReason");
            hashMap.put("severity", string4);
            hashMap.put("severityReason", string5);
            Bugsnag.internalClientNotify(javaScriptException, hashMap, z, diagnosticsCallback);
            if (callback == null) {
                return;
            }
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void setUser(ReadableMap readableMap) {
        String str = null;
        String string = readableMap.hasKey("id") ? readableMap.getString("id") : null;
        String string2 = readableMap.hasKey("email") ? readableMap.getString("email") : null;
        if (readableMap.hasKey("name")) {
            str = readableMap.getString("name");
        }
        Bugsnag.setUser(string, string2, str);
    }

    @ReactMethod
    public void startSession() {
        Bugsnag.startSession();
    }

    @ReactMethod
    public void startWithOptions(ReadableMap readableMap) {
        Client client = getClient(readableMap.hasKey("apiKey") ? readableMap.getString("apiKey") : null);
        this.libraryVersion = readableMap.getString("version");
        this.bugsnagAndroidVersion = client.getClass().getPackage().getSpecificationVersion();
        configureRuntimeOptions(client, readableMap);
        logger.info(String.format("Initialized Bugsnag React Native %s/Android %s", this.libraryVersion, this.bugsnagAndroidVersion));
    }
}
