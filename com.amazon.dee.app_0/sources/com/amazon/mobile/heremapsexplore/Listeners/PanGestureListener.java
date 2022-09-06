package com.amazon.mobile.heremapsexplore.Listeners;

import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.GestureState;
import com.here.sdk.gestures.PanListener;
/* loaded from: classes13.dex */
public class PanGestureListener extends HereMapExploreViewListener implements PanListener {
    public PanGestureListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.gestures.PanListener
    public void onPan(@NonNull GestureState gestureState, @NonNull Point2D point2D, @NonNull Point2D point2D2, double d) {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (hereMapExploreView != null) {
            hereMapExploreView.handlePanGesture(gestureState, point2D, point2D2, d);
        }
    }
}
