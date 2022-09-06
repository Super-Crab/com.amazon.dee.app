package com.amazon.mobile.heremapsexplore.Listeners;

import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapCameraObserver;
/* loaded from: classes13.dex */
public class GeofenceMapCameraListener extends HereMapExploreViewListener implements MapCameraObserver {
    public GeofenceMapCameraListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.mapview.MapCameraObserver
    public void onCameraUpdated(@NonNull MapCamera.State state) {
        if (this.weakHereMapExploreView.get() != null) {
            this.weakHereMapExploreView.get().updateGeofenceAnchorPoint(state);
        }
    }
}
