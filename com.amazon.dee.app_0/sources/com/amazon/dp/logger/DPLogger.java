package com.amazon.dp.logger;

import android.os.Build;
import android.util.Log;
import com.amazon.dp.logger.DPLoggerBase;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class DPLogger extends DPLoggerBase {
    private static final String BUILD_TYPE = Build.TYPE;
    private static final String ENG = "eng";
    private static final boolean FORCE_ALL_LOGGING_ON = false;
    protected static final int MAX_TAG_SIZE = 23;
    protected final String mName;

    /* renamed from: com.amazon.dp.logger.DPLogger$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel = new int[DPLoggerBase.DPLevel.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.FATAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public DPLogger() {
        this.mName = ensureSafeName(removePackageName(GeneratedOutlineSupport1.outline195()[1].getClassName()));
    }

    private String ensureSafeName(String str) {
        if (str == null) {
            Log.w("DPLogger", "Got a null DPLogger name; using an empty string instead");
            return "";
        } else if (str.length() <= 23) {
            return str;
        } else {
            String substring = str.substring(0, 23);
            Log.i("DPLogger", DPFormattedMessage.toDPFormat("DPLogger", "Name was too long. Truncating", "original name", str, "truncated name", substring));
            return substring;
        }
    }

    private static String removePackageName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || lastIndexOf > str.length() + (-1)) ? str : str.substring(lastIndexOf + 1);
    }

    @Override // com.amazon.dp.logger.DPLoggerBase
    protected boolean isEnabled(DPLoggerBase.DPLevel dPLevel) {
        int ordinal = dPLevel.ordinal();
        int i = 4;
        if (ordinal == 0) {
            i = 7;
        } else if (ordinal == 1) {
            i = 6;
        } else if (ordinal == 2) {
            i = 5;
        } else if (ordinal != 3) {
            i = ordinal != 4 ? 2 : 3;
        }
        return Log.isLoggable(this.mName, i) || ENG.equals(BUILD_TYPE);
    }

    @Override // com.amazon.dp.logger.DPLoggerBase
    protected void log(DPLoggerBase.DPLevel dPLevel, DPFormattedMessage dPFormattedMessage) {
        Throwable throwable = dPFormattedMessage.getThrowable();
        int ordinal = dPLevel.ordinal();
        if (ordinal == 0) {
            if (throwable == null) {
                Log.wtf(this.mName, dPFormattedMessage.toString());
            } else {
                Log.wtf(this.mName, dPFormattedMessage.toString(), throwable);
            }
        } else if (ordinal == 1) {
            if (throwable == null) {
                Log.e(this.mName, dPFormattedMessage.toString());
            } else {
                Log.e(this.mName, dPFormattedMessage.toString(), throwable);
            }
        } else if (ordinal == 2) {
            if (throwable == null) {
                Log.w(this.mName, dPFormattedMessage.toString());
            } else {
                Log.w(this.mName, dPFormattedMessage.toString(), throwable);
            }
        } else if (ordinal == 3) {
            if (throwable == null) {
                Log.i(this.mName, dPFormattedMessage.toString());
            } else {
                Log.i(this.mName, dPFormattedMessage.toString(), throwable);
            }
        } else if (ordinal != 4) {
            if (throwable == null) {
                dPFormattedMessage.toString();
            } else {
                dPFormattedMessage.toString();
            }
        } else if (throwable == null) {
            dPFormattedMessage.toString();
        } else {
            dPFormattedMessage.toString();
        }
    }

    public DPLogger(Class<?> cls) {
        this.mName = ensureSafeName(cls.getSimpleName());
    }

    public DPLogger(String str) {
        this.mName = ensureSafeName(str);
    }
}
