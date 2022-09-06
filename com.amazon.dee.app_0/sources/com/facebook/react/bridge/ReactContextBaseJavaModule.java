package com.facebook.react.bridge;

import android.app.Activity;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
/* loaded from: classes2.dex */
public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    @Nullable
    private final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule() {
        this.mReactApplicationContext = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ReactApplicationContext getReactApplicationContext() {
        return (ReactApplicationContext) Assertions.assertNotNull(this.mReactApplicationContext, "Tried to get ReactApplicationContext even though NativeModule wasn't instantiated with one");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    @ThreadConfined(ThreadConfined.ANY)
    public final ReactApplicationContext getReactApplicationContextIfActiveOrWarn() {
        if (!this.mReactApplicationContext.hasActiveCatalystInstance() && !this.mReactApplicationContext.isBridgeless()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Catalyst Instance has already disappeared: requested by ");
            outline107.append(getName());
            ReactSoftException.logSoftException("ReactContextBaseJavaModule", new RuntimeException(outline107.toString()));
            return null;
        }
        return this.mReactApplicationContext;
    }

    public ReactContextBaseJavaModule(@Nullable ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }
}
