package com.here.sdk.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.core.threading.TaskHandle;
/* loaded from: classes3.dex */
public final class SearchEngine extends NativeBase {
    public SearchEngine() throws InstantiationErrorException {
        this(make(), null);
        cacheThisInstance();
    }

    protected SearchEngine(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.SearchEngine.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                SearchEngine.disposeNativeHandle(j2);
            }
        });
    }

    public SearchEngine(@NonNull SDKNativeEngine sDKNativeEngine) throws InstantiationErrorException {
        this(make(sDKNativeEngine), null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make() throws InstantiationErrorException;

    private static native long make(@NonNull SDKNativeEngine sDKNativeEngine) throws InstantiationErrorException;

    @NonNull
    public native TaskHandle search(@NonNull GeoCoordinates geoCoordinates, @NonNull SearchOptions searchOptions, @NonNull SearchCallback searchCallback);

    @NonNull
    public native TaskHandle search(@NonNull AddressQuery addressQuery, @NonNull SearchOptions searchOptions, @NonNull SearchCallback searchCallback);

    @NonNull
    public native TaskHandle search(@NonNull CategoryQuery categoryQuery, @NonNull SearchOptions searchOptions, @NonNull SearchCallback searchCallback);

    @NonNull
    public native TaskHandle search(@NonNull PlaceIdQuery placeIdQuery, @Nullable LanguageCode languageCode, @NonNull PlaceIdSearchCallback placeIdSearchCallback);

    @NonNull
    public native TaskHandle search(@NonNull TextQuery textQuery, @NonNull SearchOptions searchOptions, @NonNull SearchCallback searchCallback);

    @Nullable
    public native SearchError setCustomOption(@NonNull String str, @NonNull String str2);

    @NonNull
    public native TaskHandle suggest(@NonNull TextQuery textQuery, @NonNull SearchOptions searchOptions, @NonNull SuggestCallback suggestCallback);
}
