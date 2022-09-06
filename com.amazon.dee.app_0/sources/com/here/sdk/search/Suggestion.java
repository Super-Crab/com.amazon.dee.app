package com.here.sdk.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public final class Suggestion extends NativeBase {
    protected Suggestion(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.Suggestion.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Suggestion.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public native Map<HighlightType, List<IndexRange>> getHighlights();

    @Nullable
    public native String getHref();

    @Nullable
    public native Place getPlace();

    @NonNull
    public native String getTitle();

    @NonNull
    public native SuggestionType getType();
}
