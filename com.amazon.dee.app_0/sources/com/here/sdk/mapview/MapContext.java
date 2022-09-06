package com.here.sdk.mapview;

import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.LanguageCode;
/* loaded from: classes3.dex */
class MapContext extends NativeBase {
    protected MapContext(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapContext.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapContext.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public native LanguageCode getPrimaryLanguage();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void pause();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void resume();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setPrimaryLanguage(@Nullable LanguageCode languageCode);
}
