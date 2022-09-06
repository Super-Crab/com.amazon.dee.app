package amazon.speech.util;

import amazon.speech.util.DebugUtil;
/* loaded from: classes.dex */
public class DeviceEventEmitter {
    private static final boolean DEBUG_BUILD = DebugUtil.getShouldDebug(DebugUtil.Module.CSM);
    private static final char DELIMITER = '#';
    private static final String TAG = "DeviceEvent";
    private static String mPackageName;
    private final boolean mDebug;
    private final LogWrapper mLogWrapper;

    /* loaded from: classes.dex */
    private static final class Singleton {
        private static final DeviceEventEmitter INSTANCE = new DeviceEventEmitter(new SLogWrapper(), DeviceEventEmitter.DEBUG_BUILD, "");

        private Singleton() {
        }
    }

    DeviceEventEmitter(LogWrapper logWrapper, boolean z, String str) {
        this.mLogWrapper = logWrapper;
        this.mDebug = z;
        mPackageName = str;
    }

    public static DeviceEventEmitter get() {
        return Singleton.INSTANCE;
    }

    private String getLogString(Class cls, String str, int i, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (cls != null && str != null && i >= 0) {
            sb.append(cls.getSimpleName());
            sb.append(DELIMITER);
            sb.append(str);
            sb.append(DELIMITER);
            sb.append(i);
            for (Object obj : objArr) {
                if (obj == null) {
                    sb.append("#null");
                } else {
                    sb.append(DELIMITER + obj.toString().replaceAll("\\s+", " "));
                }
            }
            return sb.toString();
        }
        sb.append("Invalid logging statement: ");
        if (cls != null) {
            sb.append(cls.getSimpleName());
        } else if (str != null) {
            sb.append(str);
        } else {
            sb.append("no class or key");
        }
        this.mLogWrapper.e(TAG, sb.toString());
        return null;
    }

    @Deprecated
    public void emitEvent(Class cls, String str, int i, Object... objArr) {
        String logString = getLogString(cls, str, i, objArr);
        if (logString != null) {
            this.mLogWrapper.i(TAG, logString);
        }
    }

    @Deprecated
    public void emitEventPrivate(Class cls, String str, int i, Object... objArr) {
        String logString;
        if (!this.mDebug || (logString = getLogString(cls, str, i, objArr)) == null) {
            return;
        }
        this.mLogWrapper.i(TAG, logString);
    }

    public void setPackageName(String str) {
        if (str == null) {
            this.mLogWrapper.e(TAG, "packageName is null");
        } else {
            mPackageName = str;
        }
    }

    public void emitEvent(String str, int i, Object... objArr) {
        String logString = getLogString(str, i, objArr);
        if (logString != null) {
            this.mLogWrapper.i(TAG, logString);
        }
    }

    public void emitEventPrivate(String str, int i, Object... objArr) {
        String logString;
        if (!this.mDebug || (logString = getLogString(str, i, objArr)) == null) {
            return;
        }
        this.mLogWrapper.i(TAG, logString);
    }

    private String getLogString(String str, int i, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (str != null && i >= 0) {
            sb.append(mPackageName);
            sb.append(DELIMITER);
            sb.append(str);
            sb.append(DELIMITER);
            sb.append(i);
            for (Object obj : objArr) {
                if (obj == null) {
                    sb.append("#null");
                } else {
                    sb.append(DELIMITER + obj.toString().replaceAll("\\s+", " "));
                }
            }
            return sb.toString();
        }
        sb.append("Invalid logging statement: ");
        if (str != null) {
            sb.append(str);
        } else {
            sb.append("no key");
        }
        this.mLogWrapper.e(TAG, sb.toString());
        return null;
    }
}
