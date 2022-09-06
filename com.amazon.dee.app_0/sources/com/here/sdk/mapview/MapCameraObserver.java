package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.sdk.mapview.MapCamera;
/* loaded from: classes3.dex */
public interface MapCameraObserver {
    void onCameraUpdated(@NonNull MapCamera.State state);
}
