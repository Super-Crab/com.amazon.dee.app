package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.NativeInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class Configuration extends Observable implements Observer {
    static final String DEFAULT_EXCEPTION_TYPE = "android";
    private static final int DEFAULT_MAX_SIZE = 32;
    static final String HEADER_API_KEY = "Bugsnag-Api-Key";
    private static final String HEADER_API_PAYLOAD_VERSION = "Bugsnag-Payload-Version";
    private static final String HEADER_BUGSNAG_SENT_AT = "Bugsnag-Sent-At";
    @NonNull
    private final String apiKey;
    private String appVersion;
    private String buildUuid;
    private String codeBundleId;
    private String context;
    private Delivery delivery;
    private boolean detectNdkCrashes;
    private String[] ignoreClasses;
    private String notifierType;
    private String[] projectPackages;
    private String releaseStage;
    private Integer versionCode;
    private volatile String endpoint = "https://notify.bugsnag.com";
    private volatile String sessionEndpoint = "https://sessions.bugsnag.com";
    @Nullable
    private String[] notifyReleaseStages = null;
    private boolean sendThreads = true;
    private boolean enableExceptionHandler = true;
    private boolean persistUserBetweenSessions = false;
    private long launchCrashThresholdMs = 5000;
    private boolean autoCaptureSessions = true;
    private boolean automaticallyCollectBreadcrumbs = true;
    private boolean callPreviousSigquitHandler = true;
    private boolean detectAnrs = false;
    private long anrThresholdMs = 5000;
    private final Collection<BeforeNotify> beforeNotifyTasks = new ConcurrentLinkedQueue();
    private final Collection<BeforeSend> beforeSendTasks = new ConcurrentLinkedQueue();
    private final Collection<BeforeRecordBreadcrumb> beforeRecordBreadcrumbTasks = new ConcurrentLinkedQueue();
    private final Collection<BeforeSendSession> sessionCallbacks = new ConcurrentLinkedQueue();
    private int maxBreadcrumbs = 32;
    private String errorStore = "/bugsnag-errors/";
    private String sessionStore = "/bugsnag-sessions/";
    @NonNull
    private MetaData metaData = new MetaData();

    public Configuration(@NonNull String str) {
        this.apiKey = str;
        this.metaData.addObserver(this);
        try {
            this.detectNdkCrashes = Class.forName("com.bugsnag.android.BuildConfig").getDeclaredField("DETECT_NDK_CRASHES").getBoolean(null);
        } catch (Throwable unused) {
            this.detectNdkCrashes = false;
        }
    }

    void addBeforeSendSession(BeforeSendSession beforeSendSession) {
        this.sessionCallbacks.add(beforeSendSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeNotify(@NonNull BeforeNotify beforeNotify) {
        if (!this.beforeNotifyTasks.contains(beforeNotify)) {
            this.beforeNotifyTasks.add(beforeNotify);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeRecordBreadcrumb(@NonNull BeforeRecordBreadcrumb beforeRecordBreadcrumb) {
        if (!this.beforeRecordBreadcrumbTasks.contains(beforeRecordBreadcrumb)) {
            this.beforeRecordBreadcrumbTasks.add(beforeRecordBreadcrumb);
        }
    }

    public void beforeSend(@NonNull BeforeSend beforeSend) {
        if (!this.beforeSendTasks.contains(beforeSend)) {
            this.beforeSendTasks.add(beforeSend);
        }
    }

    @Deprecated
    public long getAnrThresholdMs() {
        return this.anrThresholdMs;
    }

    @NonNull
    public String getApiKey() {
        return this.apiKey;
    }

    @NonNull
    public String getAppVersion() {
        return this.appVersion;
    }

    public boolean getAutoCaptureSessions() {
        return this.autoCaptureSessions;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Collection<BeforeNotify> getBeforeNotifyTasks() {
        return this.beforeNotifyTasks;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Collection<BeforeRecordBreadcrumb> getBeforeRecordBreadcrumbTasks() {
        return this.beforeRecordBreadcrumbTasks;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Collection<BeforeSend> getBeforeSendTasks() {
        return this.beforeSendTasks;
    }

    @Nullable
    public String getBuildUUID() {
        return this.buildUuid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getCallPreviousSigquitHandler() {
        return this.callPreviousSigquitHandler;
    }

    @Nullable
    public String getCodeBundleId() {
        return this.codeBundleId;
    }

    @Nullable
    public String getContext() {
        return this.context;
    }

    @NonNull
    public Delivery getDelivery() {
        return this.delivery;
    }

    public boolean getDetectAnrs() {
        return this.detectAnrs;
    }

    public boolean getDetectNdkCrashes() {
        return this.detectNdkCrashes;
    }

    public boolean getEnableExceptionHandler() {
        return this.enableExceptionHandler;
    }

    @NonNull
    public String getEndpoint() {
        return this.endpoint;
    }

    @NonNull
    public Map<String, String> getErrorApiHeaders() {
        HashMap outline133 = GeneratedOutlineSupport1.outline133(HEADER_API_PAYLOAD_VERSION, "4.0");
        outline133.put(HEADER_API_KEY, this.apiKey);
        outline133.put(HEADER_BUGSNAG_SENT_AT, DateUtils.toIso8601(new Date()));
        return outline133;
    }

    public String getErrorStore() {
        return this.errorStore;
    }

    @Nullable
    public String[] getFilters() {
        return this.metaData.getFilters();
    }

    @Nullable
    public String[] getIgnoreClasses() {
        return this.ignoreClasses;
    }

    public long getLaunchCrashThresholdMs() {
        return this.launchCrashThresholdMs;
    }

    public int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    @NonNull
    public MetaData getMetaData() {
        return this.metaData;
    }

    @NonNull
    public String getNotifierType() {
        return this.notifierType;
    }

    @Nullable
    public String[] getNotifyReleaseStages() {
        return this.notifyReleaseStages;
    }

    public boolean getPersistUserBetweenSessions() {
        return this.persistUserBetweenSessions;
    }

    @Nullable
    public String[] getProjectPackages() {
        return this.projectPackages;
    }

    @Nullable
    public String getReleaseStage() {
        return this.releaseStage;
    }

    public boolean getSendThreads() {
        return this.sendThreads;
    }

    @NonNull
    public Map<String, String> getSessionApiHeaders() {
        HashMap outline133 = GeneratedOutlineSupport1.outline133(HEADER_API_PAYLOAD_VERSION, "1.0");
        outline133.put(HEADER_API_KEY, this.apiKey);
        outline133.put(HEADER_BUGSNAG_SENT_AT, DateUtils.toIso8601(new Date()));
        return outline133;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<BeforeSendSession> getSessionCallbacks() {
        return this.sessionCallbacks;
    }

    @NonNull
    public String getSessionEndpoint() {
        return this.sessionEndpoint;
    }

    public String getSessionStore() {
        return this.sessionStore;
    }

    @Nullable
    public Integer getVersionCode() {
        return this.versionCode;
    }

    @Deprecated
    protected boolean inProject(@NonNull String str) {
        return Stacktrace.inProject(str, this.projectPackages);
    }

    public boolean isAutomaticallyCollectingBreadcrumbs() {
        return this.automaticallyCollectBreadcrumbs;
    }

    @Deprecated
    public void setAnrThresholdMs(long j) {
    }

    public void setAppVersion(@NonNull String str) {
        this.appVersion = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_APP_VERSION, str));
    }

    public void setAutoCaptureSessions(boolean z) {
        this.autoCaptureSessions = z;
    }

    public void setAutomaticallyCollectBreadcrumbs(boolean z) {
        this.automaticallyCollectBreadcrumbs = z;
    }

    public void setBuildUUID(@Nullable String str) {
        this.buildUuid = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_BUILD_UUID, str));
    }

    void setCallPreviousSigquitHandler(boolean z) {
        this.callPreviousSigquitHandler = z;
    }

    public void setCodeBundleId(@Nullable String str) {
        this.codeBundleId = str;
    }

    public void setContext(@Nullable String str) {
        this.context = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_CONTEXT, str));
    }

    public void setDelivery(@NonNull Delivery delivery) {
        if (delivery != null) {
            this.delivery = delivery;
            return;
        }
        throw new IllegalArgumentException("Delivery cannot be null");
    }

    public void setDetectAnrs(boolean z) {
        this.detectAnrs = z;
    }

    public void setDetectNdkCrashes(boolean z) {
        this.detectNdkCrashes = z;
    }

    public void setEnableExceptionHandler(boolean z) {
        this.enableExceptionHandler = z;
    }

    @Deprecated
    public void setEndpoint(@NonNull String str) {
        this.endpoint = str;
    }

    public void setEndpoints(@NonNull String str, @NonNull String str2) throws IllegalArgumentException {
        if (!Intrinsics.isEmpty(str)) {
            this.endpoint = str;
            if (Intrinsics.isEmpty(str2)) {
                Logger.warn("The session tracking endpoint has not been set. Session tracking is disabled");
                this.sessionEndpoint = null;
                this.autoCaptureSessions = false;
                return;
            }
            this.sessionEndpoint = str2;
            return;
        }
        throw new IllegalArgumentException("Notify endpoint cannot be empty or null.");
    }

    public void setErrorStore(String str) {
        this.errorStore = str;
    }

    public void setFilters(@Nullable String[] strArr) {
        this.metaData.setFilters(strArr);
    }

    public void setIgnoreClasses(@Nullable String[] strArr) {
        this.ignoreClasses = strArr;
    }

    public void setLaunchCrashThresholdMs(long j) {
        if (j <= 0) {
            this.launchCrashThresholdMs = 0L;
        } else {
            this.launchCrashThresholdMs = j;
        }
    }

    public void setMaxBreadcrumbs(int i) {
        if (i < 0) {
            Logger.warn("Ignoring invalid breadcrumb capacity. Must be >= 0.");
        } else {
            this.maxBreadcrumbs = i;
        }
    }

    public void setMetaData(@NonNull MetaData metaData) {
        this.metaData.deleteObserver(this);
        if (metaData == null) {
            this.metaData = new MetaData();
        } else {
            this.metaData = metaData;
        }
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_METADATA, this.metaData.store));
        this.metaData.addObserver(this);
    }

    public void setNotifierType(@NonNull String str) {
        this.notifierType = str;
    }

    public void setNotifyReleaseStages(@Nullable String[] strArr) {
        this.notifyReleaseStages = strArr;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_NOTIFY_RELEASE_STAGES, this));
    }

    public void setPersistUserBetweenSessions(boolean z) {
        this.persistUserBetweenSessions = z;
    }

    public void setProjectPackages(@Nullable String[] strArr) {
        this.projectPackages = strArr;
    }

    public void setReleaseStage(@Nullable String str) {
        this.releaseStage = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_RELEASE_STAGE, this));
    }

    public void setSendThreads(boolean z) {
        this.sendThreads = z;
    }

    @Deprecated
    public void setSessionEndpoint(@NonNull String str) {
        this.sessionEndpoint = str;
    }

    public void setSessionStore(String str) {
        this.sessionStore = str;
    }

    public void setVersionCode(@Nullable Integer num) {
        this.versionCode = num;
    }

    @Deprecated
    public boolean shouldAutoCaptureSessions() {
        return getAutoCaptureSessions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean shouldIgnoreClass(@Nullable String str) {
        String[] strArr = this.ignoreClasses;
        if (strArr == null) {
            return false;
        }
        return Arrays.asList(strArr).contains(str);
    }

    public boolean shouldNotifyForReleaseStage(@Nullable String str) {
        String[] strArr = this.notifyReleaseStages;
        if (strArr == null) {
            return true;
        }
        return Arrays.asList(strArr).contains(str);
    }

    @Override // java.util.Observer
    public void update(@NonNull Observable observable, @NonNull Object obj) {
        if (obj instanceof NativeInterface.Message) {
            setChanged();
            notifyObservers(obj);
        }
    }
}
