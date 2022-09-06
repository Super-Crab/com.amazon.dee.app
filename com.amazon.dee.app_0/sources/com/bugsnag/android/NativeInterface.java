package com.bugsnag.android;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes.dex */
public class NativeInterface {
    private static Charset UTF8Charset = Charset.defaultCharset();
    @SuppressLint({"StaticFieldLeak"})
    private static Client client;

    /* loaded from: classes.dex */
    public static class Message {
        @NonNull
        public final MessageType type;
        @Nullable
        public final Object value;

        public Message(@NonNull MessageType messageType, @Nullable Object obj) {
            this.type = messageType;
            this.value = obj;
        }
    }

    /* loaded from: classes.dex */
    public enum MessageType {
        ADD_BREADCRUMB,
        ADD_METADATA,
        CLEAR_BREADCRUMBS,
        CLEAR_METADATA_TAB,
        DELIVER_PENDING,
        INSTALL,
        ENABLE_NATIVE_CRASH_REPORTING,
        DISABLE_NATIVE_CRASH_REPORTING,
        NOTIFY_HANDLED,
        NOTIFY_UNHANDLED,
        REMOVE_METADATA,
        START_SESSION,
        STOP_SESSION,
        UPDATE_APP_VERSION,
        UPDATE_BUILD_UUID,
        UPDATE_CONTEXT,
        UPDATE_IN_FOREGROUND,
        UPDATE_LOW_MEMORY,
        UPDATE_METADATA,
        UPDATE_ORIENTATION,
        UPDATE_NOTIFY_RELEASE_STAGES,
        UPDATE_RELEASE_STAGE,
        UPDATE_USER_EMAIL,
        UPDATE_USER_NAME,
        UPDATE_USER_ID
    }

    public static void addToTab(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        getClient().addToTab(str, str2, obj);
    }

    public static void clearTab(@NonNull String str) {
        getClient().clearTab(str);
    }

    @Deprecated
    public static void configureClientObservers(@NonNull Client client2) {
        setClient(client2);
    }

    public static void deliverReport(@Nullable byte[] bArr, @NonNull byte[] bArr2) {
        if (bArr2 == null) {
            return;
        }
        String str = new String(bArr2, UTF8Charset);
        String str2 = bArr == null ? null : new String(bArr, UTF8Charset);
        Client client2 = getClient();
        if (str2 != null && str2.length() != 0 && !client2.getConfig().shouldNotifyForReleaseStage(str2)) {
            return;
        }
        client2.getErrorStore().enqueueContentForDelivery(str);
        client2.getErrorStore().flushAsync();
    }

    public static void disableAnrReporting() {
        getClient().disableAnrReporting();
    }

    public static void disableNdkCrashReporting() {
        getClient().disableNdkCrashReporting();
    }

    public static void disableUncaughtJavaExceptionReporting() {
        getClient().disableExceptionHandler();
    }

    public static void enableAnrReporting() {
        getClient().enableAnrReporting();
    }

    public static void enableNdkCrashReporting() {
        getClient().enableNdkCrashReporting();
    }

    public static void enableUncaughtJavaExceptionReporting() {
        getClient().enableExceptionHandler();
    }

    @NonNull
    public static Map<String, Object> getAppData() {
        HashMap hashMap = new HashMap();
        AppData appData = getClient().getAppData();
        hashMap.putAll(appData.getAppData());
        hashMap.putAll(appData.getAppDataMetaData());
        return hashMap;
    }

    @NonNull
    public static String getAppVersion() {
        return getClient().getConfig().getAppVersion();
    }

    @NonNull
    public static List<Breadcrumb> getBreadcrumbs() {
        return new ArrayList(getClient().breadcrumbs.store);
    }

    @NonNull
    private static Client getClient() {
        Client client2 = client;
        return client2 != null ? client2 : Bugsnag.getClient();
    }

    @Nullable
    public static String getContext() {
        return getClient().getContext();
    }

    @NonNull
    public static String[] getCpuAbi() {
        return getClient().deviceData.cpuAbi;
    }

    @NonNull
    public static Map<String, Object> getDeviceData() {
        HashMap hashMap = new HashMap();
        DeviceData deviceData = getClient().getDeviceData();
        hashMap.putAll(deviceData.getDeviceMetaData());
        hashMap.putAll(deviceData.getDeviceData());
        return hashMap;
    }

    @NonNull
    public static String getEndpoint() {
        return getClient().getConfig().getEndpoint();
    }

    public static boolean getLoggingEnabled() {
        return Logger.getEnabled();
    }

    @NonNull
    public static Map<String, Object> getMetaData() {
        return new HashMap(getClient().getMetaData().store);
    }

    @NonNull
    public static String getNativeReportPath() {
        return getClient().appContext.getCacheDir().getAbsolutePath() + "/bugsnag-native/";
    }

    @Nullable
    public static String[] getNotifyReleaseStages() {
        return getClient().getConfig().getNotifyReleaseStages();
    }

    @Nullable
    public static String getReleaseStage() {
        return getClient().getConfig().getReleaseStage();
    }

    @NonNull
    public static String getSessionEndpoint() {
        return getClient().getConfig().getSessionEndpoint();
    }

    @NonNull
    public static Map<String, String> getUserData() {
        HashMap hashMap = new HashMap();
        User user = getClient().getUser();
        hashMap.put("id", user.getId());
        hashMap.put("name", user.getName());
        hashMap.put("email", user.getEmail());
        return hashMap;
    }

    public static void leaveBreadcrumb(@NonNull String str, @NonNull BreadcrumbType breadcrumbType) {
        if (str == null) {
            return;
        }
        getClient().leaveBreadcrumb(str, breadcrumbType, Collections.emptyMap());
    }

    public static void notify(@NonNull byte[] bArr, @NonNull byte[] bArr2, @NonNull Severity severity, @NonNull StackTraceElement[] stackTraceElementArr) {
        if (bArr == null || bArr2 == null || stackTraceElementArr == null) {
            return;
        }
        notify(new String(bArr, UTF8Charset), new String(bArr2, UTF8Charset), severity, stackTraceElementArr);
    }

    public static void registerSession(long j, @Nullable String str, int i, int i2) {
        Client client2 = getClient();
        client2.getSessionTracker().registerExistingSession(j > 0 ? new Date(j) : null, str, client2.getUser(), i, i2);
    }

    public static void setAppVersion(@NonNull String str) {
        getClient().setAppVersion(str);
    }

    public static void setBinaryArch(@NonNull String str) {
        getClient().setBinaryArch(str);
    }

    public static void setClient(@NonNull Client client2) {
        client = client2;
    }

    public static void setContext(@Nullable String str) {
        getClient().setContext(str);
    }

    public static void setEndpoint(@NonNull String str) {
        getClient().getConfig().setEndpoint(str);
    }

    public static void setNotifyReleaseStages(@Nullable String[] strArr) {
        getClient().getConfig().setNotifyReleaseStages(strArr);
    }

    public static void setReleaseStage(@Nullable String str) {
        getClient().setReleaseStage(str);
    }

    public static void setSessionEndpoint(@NonNull String str) {
        getClient().getConfig().setSessionEndpoint(str);
    }

    public static void setUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        Client client2 = getClient();
        client2.setUserId(str);
        client2.setUserEmail(str2);
        client2.setUserName(str3);
    }

    public static void leaveBreadcrumb(@NonNull byte[] bArr, @NonNull BreadcrumbType breadcrumbType) {
        if (bArr == null) {
            return;
        }
        getClient().leaveBreadcrumb(new String(bArr, UTF8Charset), breadcrumbType, Collections.emptyMap());
    }

    public static void leaveBreadcrumb(@NonNull String str, @NonNull String str2, @NonNull Map<String, String> map) {
        String upperCase = str2.toUpperCase(Locale.US);
        if (map == null) {
            map = new HashMap<>();
        }
        getClient().leaveBreadcrumb(str, BreadcrumbType.valueOf(upperCase), map);
    }

    public static void notify(@NonNull String str, @NonNull String str2, @NonNull final Severity severity, @NonNull StackTraceElement[] stackTraceElementArr) {
        if (str == null || str2 == null || stackTraceElementArr == null) {
            return;
        }
        getClient().notify(str, str2, stackTraceElementArr, new Callback() { // from class: com.bugsnag.android.NativeInterface.1
            @Override // com.bugsnag.android.Callback
            public void beforeNotify(@NonNull Report report) {
                Error error = report.getError();
                if (error != null) {
                    Severity severity2 = Severity.this;
                    if (severity2 != null) {
                        error.setSeverity(severity2);
                    }
                    error.getExceptions().setExceptionType("c");
                }
            }
        });
    }

    public static void setUser(@Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3) {
        String str = null;
        String str2 = bArr == null ? null : new String(bArr, UTF8Charset);
        String str3 = bArr2 == null ? null : new String(bArr2, UTF8Charset);
        if (bArr3 != null) {
            str = new String(bArr3, UTF8Charset);
        }
        setUser(str2, str3, str);
    }
}
