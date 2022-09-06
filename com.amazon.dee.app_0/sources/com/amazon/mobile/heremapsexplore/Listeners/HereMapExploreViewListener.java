package com.amazon.mobile.heremapsexplore.Listeners;

import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public abstract class HereMapExploreViewListener {
    WeakReference<HereMapExploreView> weakHereMapExploreView;

    public HereMapExploreViewListener(HereMapExploreView hereMapExploreView) {
        this.weakHereMapExploreView = new WeakReference<>(hereMapExploreView);
    }
}
