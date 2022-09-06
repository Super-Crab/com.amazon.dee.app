package com.amazon.mobile.heremapsexplore.Listeners;

import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.TapListener;
/* loaded from: classes13.dex */
public class TapGestureListener extends HereMapExploreViewListener implements TapListener {
    public TapGestureListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.gestures.TapListener
    public void onTap(@NonNull Point2D point2D) {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (hereMapExploreView != null) {
            hereMapExploreView.handleTapGesture(point2D);
        }
    }
}
