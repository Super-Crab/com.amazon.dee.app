package com.bugsnag.android.ndk;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.Breadcrumb;
import com.bugsnag.android.Configuration;
import com.bugsnag.android.NativeInterface;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class NativeBridge implements Observer {
    private static final String LOG_TAG = "BugsnagNDK:NativeBridge";
    private static final int METADATA_KEY = 1;
    private static final int METADATA_SECTION = 0;
    private static final int METADATA_VALUE = 2;
    private boolean loggingEnabled;
    private final String reportDirectory = NativeInterface.getNativeReportPath();
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean installed = new AtomicBoolean(false);

    /* renamed from: com.bugsnag.android.ndk.NativeBridge$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bugsnag$android$NativeInterface$MessageType = new int[NativeInterface.MessageType.values().length];

        static {
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.INSTALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.ENABLE_NATIVE_CRASH_REPORTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.DISABLE_NATIVE_CRASH_REPORTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.DELIVER_PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.ADD_BREADCRUMB.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.ADD_METADATA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.CLEAR_BREADCRUMBS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.CLEAR_METADATA_TAB.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.NOTIFY_HANDLED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.NOTIFY_UNHANDLED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.REMOVE_METADATA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.START_SESSION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.STOP_SESSION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_APP_VERSION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_BUILD_UUID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_CONTEXT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_IN_FOREGROUND.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_LOW_MEMORY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_METADATA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_ORIENTATION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_RELEASE_STAGE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_NOTIFY_RELEASE_STAGES.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_USER_ID.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_USER_NAME.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$bugsnag$android$NativeInterface$MessageType[NativeInterface.MessageType.UPDATE_USER_EMAIL.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    public NativeBridge() {
        this.loggingEnabled = true;
        this.loggingEnabled = NativeInterface.getLoggingEnabled();
        File file = new File(this.reportDirectory);
        if (file.exists() || file.mkdirs()) {
            return;
        }
        warn("The native reporting directory cannot be created.");
    }

    public static native void addBreadcrumb(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Object obj);

    public static native void addHandledEvent();

    public static native void addMetadataBoolean(@NonNull String str, @NonNull String str2, boolean z);

    public static native void addMetadataDouble(@NonNull String str, @NonNull String str2, double d);

    public static native void addMetadataString(@NonNull String str, @NonNull String str2, @NonNull String str3);

    public static native void addUnhandledEvent();

    public static native void clearBreadcrumbs();

    public static native void clearMetadataTab(@NonNull String str);

    private void deliverPendingReports() {
        lock.lock();
        try {
            try {
                File file = new File(this.reportDirectory);
                if (file.exists()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            deliverReportAtPath(file2.getAbsolutePath());
                        }
                    }
                } else {
                    warn("Report directory does not exist, cannot read pending reports");
                }
            } catch (Exception e) {
                warn("Failed to parse/write pending reports: " + e);
            }
        } finally {
            lock.unlock();
        }
    }

    public static native void deliverReportAtPath(@NonNull String str);

    public static native void disableCrashReporting();

    public static native void enableCrashReporting();

    private void enableOrDisableReportingIfNeeded(Configuration configuration) {
        if (configuration.shouldNotifyForReleaseStage(configuration.getReleaseStage())) {
            if (!configuration.getDetectNdkCrashes()) {
                return;
            }
            enableCrashReporting();
            return;
        }
        disableCrashReporting();
    }

    private void handleAddBreadcrumb(Object obj) {
        if (obj instanceof Breadcrumb) {
            Breadcrumb breadcrumb = (Breadcrumb) obj;
            HashMap hashMap = new HashMap();
            if (breadcrumb.getMetadata() != null) {
                for (Map.Entry<String, String> entry : breadcrumb.getMetadata().entrySet()) {
                    if (entry.getValue() != null) {
                        hashMap.put(makeSafe(entry.getKey()), makeSafe(entry.getValue()));
                    }
                }
            }
            addBreadcrumb(breadcrumb.getName(), breadcrumb.getType().toString(), breadcrumb.getTimestamp(), hashMap);
            return;
        }
        warn(GeneratedOutlineSupport1.outline70("Attempted to add non-breadcrumb: ", obj));
    }

    private void handleAddMetadata(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 3 && (list.get(0) instanceof String) && (list.get(1) instanceof String)) {
                String makeSafe = makeSafe((String) list.get(0));
                String makeSafe2 = makeSafe((String) list.get(1));
                if (list.get(2) instanceof String) {
                    addMetadataString(makeSafe, makeSafe2, makeSafe((String) list.get(2)));
                    return;
                } else if (list.get(2) instanceof Boolean) {
                    addMetadataBoolean(makeSafe, makeSafe2, ((Boolean) list.get(2)).booleanValue());
                    return;
                } else if (list.get(2) instanceof Number) {
                    addMetadataDouble(makeSafe, makeSafe2, ((Number) list.get(2)).doubleValue());
                    return;
                }
            } else if (list.size() == 2 && (list.get(0) instanceof String) && (list.get(1) instanceof String)) {
                removeMetadata(makeSafe((String) list.get(0)), makeSafe((String) list.get(1)));
                return;
            }
        }
        warn(GeneratedOutlineSupport1.outline70("ADD_METADATA object is invalid: ", obj));
    }

    private void handleAppVersionChange(Object obj) {
        if (obj instanceof String) {
            updateAppVersion(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_APP_VERSION object is invalid: ", obj));
        }
    }

    private void handleBuildUUIDChange(Object obj) {
        if (obj == null) {
            updateBuildUUID("");
        } else if (obj instanceof String) {
            updateBuildUUID(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_BUILD_UUID object is invalid: ", obj));
        }
    }

    private void handleClearMetadataTab(Object obj) {
        if (obj instanceof String) {
            clearMetadataTab(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("CLEAR_METADATA_TAB object is invalid: ", obj));
        }
    }

    private void handleContextChange(Object obj) {
        if (obj == null) {
            updateContext("");
        } else if (obj instanceof String) {
            updateContext(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_CONTEXT object is invalid: ", obj));
        }
    }

    private void handleForegroundActivityChange(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 2) {
                updateInForeground(((Boolean) list.get(0)).booleanValue(), makeSafe((String) list.get(1)));
                return;
            }
        }
        warn(GeneratedOutlineSupport1.outline70("UPDATE_IN_FOREGROUND object is invalid: ", obj));
    }

    private void handleInstallMessage(@NonNull Object obj) {
        lock.lock();
        try {
            if (installed.get()) {
                warn("Received duplicate setup message with arg: " + obj);
            } else if (obj instanceof List) {
                List list = (List) obj;
                if (list.size() > 0 && (list.get(0) instanceof Configuration)) {
                    install(this.reportDirectory + UUID.randomUUID().toString() + ".crash", ((Configuration) list.get(0)).getDetectNdkCrashes(), Build.VERSION.SDK_INT, is32bit());
                    installed.set(true);
                }
            } else {
                warn("Received install message with incorrect arg: " + obj);
            }
        } finally {
            lock.unlock();
        }
    }

    private void handleLowMemoryChange(Object obj) {
        if (obj instanceof Boolean) {
            updateLowMemory(((Boolean) obj).booleanValue());
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_LOW_MEMORY object is invalid: ", obj));
        }
    }

    private void handleNotifyReleaseStagesChange(Object obj) {
        if (obj instanceof Configuration) {
            enableOrDisableReportingIfNeeded((Configuration) obj);
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_NOTIFY_RELEASE_STAGES object is invalid: ", obj));
        }
    }

    private void handleOrientationChange(Object obj) {
        if (obj instanceof Integer) {
            updateOrientation(((Integer) obj).intValue());
        } else if (obj == null) {
            warn("UPDATE_ORIENTATION object is null");
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_ORIENTATION object is invalid: ", obj));
        }
    }

    private void handleReleaseStageChange(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            updateReleaseStage(makeSafe(configuration.getReleaseStage()));
            enableOrDisableReportingIfNeeded(configuration);
            return;
        }
        warn(GeneratedOutlineSupport1.outline70("UPDATE_RELEASE_STAGE object is invalid: ", obj));
    }

    private void handleRemoveMetadata(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 2) {
                removeMetadata(makeSafe((String) list.get(0)), makeSafe((String) list.get(1)));
                return;
            }
        }
        warn(GeneratedOutlineSupport1.outline70("REMOVE_METADATA object is invalid: ", obj));
    }

    private void handleStartSession(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 4) {
                Object obj2 = list.get(0);
                Object obj3 = list.get(1);
                Object obj4 = list.get(2);
                Object obj5 = list.get(3);
                if ((obj2 instanceof String) && (obj3 instanceof String) && (obj4 instanceof Integer) && (obj5 instanceof Integer)) {
                    startedSession((String) obj2, (String) obj3, ((Integer) obj4).intValue(), ((Integer) obj5).intValue());
                    return;
                }
            }
        }
        warn(GeneratedOutlineSupport1.outline70("START_SESSION object is invalid: ", obj));
    }

    private void handleStopSession() {
        stoppedSession();
    }

    private void handleUpdateMetadata(Object obj) {
        if (obj instanceof Map) {
            updateMetadata(obj);
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_METADATA object is invalid: ", obj));
        }
    }

    private void handleUserEmailChange(Object obj) {
        if (obj == null) {
            updateUserEmail("");
        } else if (obj instanceof String) {
            updateUserEmail(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_USER_EMAIL object is invalid: ", obj));
        }
    }

    private void handleUserIdChange(Object obj) {
        if (obj == null) {
            updateUserId("");
        } else if (obj instanceof String) {
            updateUserId(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_USER_ID object is invalid: ", obj));
        }
    }

    private void handleUserNameChange(Object obj) {
        if (obj == null) {
            updateUserName("");
        } else if (obj instanceof String) {
            updateUserName(makeSafe((String) obj));
        } else {
            warn(GeneratedOutlineSupport1.outline70("UPDATE_USER_NAME object is invalid: ", obj));
        }
    }

    public static native void install(@NonNull String str, boolean z, int i, boolean z2);

    private boolean is32bit() {
        for (String str : NativeInterface.getCpuAbi()) {
            if (str.contains("64")) {
                return false;
            }
        }
        return true;
    }

    private String makeSafe(String str) {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes(Charset.defaultCharset()));
    }

    @Nullable
    private NativeInterface.Message parseMessage(@Nullable Object obj) {
        if (!(obj instanceof NativeInterface.Message)) {
            if (obj == null) {
                warn("Received observable update with null Message");
            } else {
                warn(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Received observable update object which is not instance of Message: ")));
            }
            return null;
        }
        NativeInterface.Message message = (NativeInterface.Message) obj;
        if (message.type == NativeInterface.MessageType.INSTALL || installed.get()) {
            return message;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received message before INSTALL: ");
        outline107.append(message.type);
        warn(outline107.toString());
        return null;
    }

    public static native void removeMetadata(@NonNull String str, @NonNull String str2);

    public static native void startedSession(@NonNull String str, @NonNull String str2, int i, int i2);

    public static native void stoppedSession();

    public static native void updateAppVersion(@NonNull String str);

    public static native void updateBuildUUID(@NonNull String str);

    public static native void updateContext(@NonNull String str);

    public static native void updateInForeground(boolean z, @NonNull String str);

    public static native void updateLowMemory(boolean z);

    public static native void updateMetadata(@NonNull Object obj);

    public static native void updateOrientation(int i);

    public static native void updateReleaseStage(@NonNull String str);

    public static native void updateUserEmail(@NonNull String str);

    public static native void updateUserId(@NonNull String str);

    public static native void updateUserName(@NonNull String str);

    private void warn(String str) {
        if (this.loggingEnabled) {
            Log.w(LOG_TAG, str);
        }
    }

    @Override // java.util.Observer
    public void update(@NonNull Observable observable, @Nullable Object obj) {
        NativeInterface.Message parseMessage = parseMessage(obj);
        if (parseMessage == null) {
            return;
        }
        Object obj2 = parseMessage.value;
        switch (parseMessage.type.ordinal()) {
            case 0:
                handleAddBreadcrumb(obj2);
                return;
            case 1:
                handleAddMetadata(obj2);
                return;
            case 2:
                clearBreadcrumbs();
                return;
            case 3:
                handleClearMetadataTab(obj2);
                return;
            case 4:
                deliverPendingReports();
                return;
            case 5:
                handleInstallMessage(obj2);
                return;
            case 6:
                enableCrashReporting();
                return;
            case 7:
                disableCrashReporting();
                return;
            case 8:
                addHandledEvent();
                return;
            case 9:
                addUnhandledEvent();
                return;
            case 10:
                handleRemoveMetadata(obj2);
                return;
            case 11:
                handleStartSession(obj2);
                return;
            case 12:
                stoppedSession();
                return;
            case 13:
                handleAppVersionChange(obj2);
                return;
            case 14:
                handleBuildUUIDChange(obj2);
                return;
            case 15:
                handleContextChange(obj2);
                return;
            case 16:
                handleForegroundActivityChange(obj2);
                return;
            case 17:
                handleLowMemoryChange(obj2);
                return;
            case 18:
                handleUpdateMetadata(obj2);
                return;
            case 19:
                handleOrientationChange(obj2);
                return;
            case 20:
                handleNotifyReleaseStagesChange(obj2);
                return;
            case 21:
                handleReleaseStageChange(obj2);
                return;
            case 22:
                handleUserEmailChange(obj2);
                return;
            case 23:
                handleUserNameChange(obj2);
                return;
            case 24:
                handleUserIdChange(obj2);
                return;
            default:
                return;
        }
    }
}
