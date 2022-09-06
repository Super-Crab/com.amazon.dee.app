package com.amazon.mobile.heremapsexplore.Listeners;

import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public class OnMapViewReadyListener extends HereMapExploreViewListener implements MapView.OnReadyListener {
    public OnMapViewReadyListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.mapview.MapView.OnReadyListener
    public void onMapViewReady() {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (hereMapExploreView != null) {
            hereMapExploreView.setIsMapReady(true);
            hereMapExploreView.dispatchOnMapReadyEvent();
        }
    }
}
