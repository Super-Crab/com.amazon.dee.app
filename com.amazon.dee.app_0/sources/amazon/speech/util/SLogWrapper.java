package amazon.speech.util;
/* loaded from: classes.dex */
class SLogWrapper implements LogWrapper {
    @Override // amazon.speech.util.LogWrapper
    public void d(String str, String str2) {
    }

    @Override // amazon.speech.util.LogWrapper
    public void d(String str, String str2, Throwable th) {
    }

    @Override // amazon.speech.util.LogWrapper
    public void e(String str, String str2) {
        android.util.Log.e(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void i(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void println(int i, String str, String str2) {
        android.util.Log.println(i, str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void w(String str, String str2) {
        android.util.Log.w(str, str2);
    }

    @Override // amazon.speech.util.LogWrapper
    public void wtf(String str, String str2, Throwable th) {
        android.util.Log.wtf(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void e(String str, String str2, Throwable th) {
        android.util.Log.e(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void i(String str, String str2, Throwable th) {
        android.util.Log.i(str, str2, th);
    }

    @Override // amazon.speech.util.LogWrapper
    public void w(String str, String str2, Throwable th) {
        android.util.Log.w(str, str2, th);
    }
}
