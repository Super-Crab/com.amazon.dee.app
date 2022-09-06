package com.amazon.alexa.accessory.internal.util;

import android.util.Log;
/* loaded from: classes.dex */
class DefaultAccessoriesLogger implements AccessoriesLogger {
    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void d(String str, String str2) {
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void d(String str, String str2, Throwable th) {
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void v(String str, String str2) {
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void v(String str, String str2, Throwable th) {
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.amazon.alexa.accessory.internal.util.AccessoriesLogger
    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }
}
