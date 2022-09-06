package amazon.speech.simclient.metrics.util;

import amazon.speech.util.LogWrapper;
import android.util.Log;
/* loaded from: classes.dex */
public class AndroidLogWrapper implements LogWrapper {
    @Override // amazon.speech.util.LogWrapper
    public void d(String str, String str2) {
    }

    @Override // amazon.speech.util.LogWrapper
    public void d(String str, String str2, Throwable th) {
    }

    @Override // amazon.speech.util.LogWrapper
    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void println(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void wtf(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }
}
