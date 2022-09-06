package com.amazon.mobile.heremapsexplore.Listeners;

import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import com.amazon.mobile.heremapsexplore.HereMapExploreView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapCameraObserver;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapScene;
/* loaded from: classes13.dex */
public class LoadSceneListener extends HereMapExploreViewListener implements MapScene.LoadSceneCallback {

    /* loaded from: classes13.dex */
    private static class MapCameraListener extends HereMapExploreViewListener implements MapCameraObserver {
        private Handler panHandler;
        private Runnable panRunnable;

        /* loaded from: classes13.dex */
        private static class PanRunnable extends HereMapExploreViewListener implements Runnable {
            public PanRunnable(HereMapExploreView hereMapExploreView) {
                super(hereMapExploreView);
            }

            @Override // java.lang.Runnable
            public void run() {
                HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
                if (hereMapExploreView != null) {
                    hereMapExploreView.onRegionChangeComplete();
                }
            }
        }

        public MapCameraListener(HereMapExploreView hereMapExploreView) {
            super(hereMapExploreView);
            this.panHandler = new Handler();
            this.panRunnable = new PanRunnable(hereMapExploreView);
        }

        @Override // com.here.sdk.mapview.MapCameraObserver
        public void onCameraUpdated(@NonNull MapCamera.State state) {
            if (this.weakHereMapExploreView.get() != null) {
                this.panHandler.removeCallbacks(this.panRunnable);
                this.panHandler.postDelayed(this.panRunnable, 200L);
            }
        }
    }

    public LoadSceneListener(HereMapExploreView hereMapExploreView) {
        super(hereMapExploreView);
    }

    @Override // com.here.sdk.mapview.MapScene.LoadSceneCallback
    public void onLoadScene(@Nullable MapError mapError) {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (mapError == null && hereMapExploreView != null) {
            hereMapExploreView.setMapCamera(hereMapExploreView.getMapView().getCamera());
            hereMapExploreView.getMapCamera().addObserver(new MapCameraListener(hereMapExploreView));
            hereMapExploreView.configureMapGestures();
            hereMapExploreView.setReactData();
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading map failed: mapError: ");
        outline107.append(mapError.name());
        Log.e(Constants.LOG_TAG, outline107.toString());
    }
}
