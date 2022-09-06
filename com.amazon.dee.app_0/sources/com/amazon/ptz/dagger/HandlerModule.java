package com.amazon.ptz.dagger;

import android.os.Handler;
import android.os.Looper;
import com.amazon.ptz.util.Qualifiers;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class HandlerModule {
    @Provides
    @Qualifiers.UiThread
    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
