package com.here.sdk.search;

import androidx.annotation.Nullable;
@FunctionalInterface
/* loaded from: classes3.dex */
public interface PlaceIdSearchCallback {
    void onPlaceIdSearchCompleted(@Nullable SearchError searchError, @Nullable Place place);
}
