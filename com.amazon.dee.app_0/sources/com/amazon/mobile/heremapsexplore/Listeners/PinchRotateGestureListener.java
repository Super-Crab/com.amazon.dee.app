package com.amazon.mobile.heremapsexplore.Listeners;

import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.here.sdk.core.Angle;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.GestureState;
import com.here.sdk.gestures.PinchRotateListener;
/* loaded from: classes13.dex */
public class PinchRotateGestureListener extends HereMapExploreViewListener implements PinchRotateListener {
    public PinchRotateGestureListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.gestures.PinchRotateListener
    public void onPinchRotate(@NonNull GestureState gestureState, @NonNull Point2D point2D, @NonNull Point2D point2D2, double d, @NonNull Angle angle) {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (hereMapExploreView != null) {
            hereMapExploreView.handlePinchRotateGesture(gestureState, point2D, point2D2, d, angle);
        }
    }
}
