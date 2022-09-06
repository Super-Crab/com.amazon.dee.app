package amazon.speech.util;

import amazon.speech.csmshark.CsmSharkListener;
import amazon.speech.csmshark.ICsmInterceptor;
import amazon.speech.csmshark.Sharkable;
/* loaded from: classes.dex */
public class Log {
    private static final int MAX_CHARS_PER_LOG = 4000;
    private static ICsmInterceptor sCsmInterceptor;
    private static LogWrapper sInstance = new SLogWrapper();

    public static void d(String str, String str2) {
        sInstance.d(str, str2);
    }

    public static void e(String str, String str2) {
        sInstance.e(str, str2);
    }

    public static void i(String str, String str2) {
        sInstance.i(str, str2);
    }

    public static void longMessage(int i, String str, String str2) {
        if (str2 != null) {
            int length = str2.length();
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2 + 4000;
                sInstance.println(i, str, str2.substring(i2, Math.min(length, i3)));
                i2 = i3;
            }
        }
    }

    public static void s(Sharkable sharkable) {
        if (sCsmInterceptor == null) {
            sCsmInterceptor = CsmSharkListener.getInstance();
        }
        ICsmInterceptor iCsmInterceptor = sCsmInterceptor;
        if (iCsmInterceptor != null) {
            iCsmInterceptor.onSharkable(sharkable);
        }
    }

    static void setLogWrapper(LogWrapper logWrapper) {
        sInstance = logWrapper;
    }

    public static void w(String str, String str2) {
        sInstance.w(str, str2);
    }

    public static void wtf(String str, String str2, Throwable th) {
        sInstance.wtf(str, str2, th);
    }

    public static void d(String str, String str2, Throwable th) {
        sInstance.d(str, str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        sInstance.e(str, str2, th);
    }

    public static void i(String str, String str2, Throwable th) {
        sInstance.i(str, str2, th);
    }

    public static void w(String str, String str2, Throwable th) {
        sInstance.w(str, str2, th);
    }
}
