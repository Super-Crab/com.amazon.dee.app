package org.greenrobot.eventbus;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.util.logging.Level;
/* loaded from: classes5.dex */
public interface Logger {

    /* loaded from: classes5.dex */
    public static class JavaLogger implements Logger {
        protected final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    /* loaded from: classes5.dex */
    public static class SystemOutLogger implements Logger {
        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);

    /* loaded from: classes5.dex */
    public static class AndroidLogger implements Logger {
        static final boolean ANDROID_LOG_AVAILABLE;
        private final String tag;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            }
            ANDROID_LOG_AVAILABLE = z;
        }

        public AndroidLogger(String str) {
            this.tag = str;
        }

        public static boolean isAndroidLogAvailable() {
            return ANDROID_LOG_AVAILABLE;
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(mapLevel(level), this.tag, str);
            }
        }

        protected int mapLevel(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                return intValue < 500 ? 2 : 3;
            } else if (intValue < 900) {
                return 4;
            } else {
                return intValue < 1000 ? 5 : 6;
            }
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                int mapLevel = mapLevel(level);
                String str2 = this.tag;
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "\n");
                outline113.append(Log.getStackTraceString(th));
                Log.println(mapLevel, str2, outline113.toString());
            }
        }
    }
}
