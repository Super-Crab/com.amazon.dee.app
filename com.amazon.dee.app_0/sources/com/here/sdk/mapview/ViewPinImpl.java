package com.here.sdk.mapview;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.mapview.MapView;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ViewPinImpl implements MapView.ViewPin {
    private static final FrameLayout.LayoutParams sOverlayParameter = new FrameLayout.LayoutParams(-2, -2);
    private Anchor2D mAnchorPoint = new Anchor2D(0.5d, 0.5d);
    private GeoCoordinates mGeoCoordinates;
    private View mView;
    private WeakReference<ViewPinsManager> mViewPinsManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPinImpl(@NonNull ViewPinsManager viewPinsManager, @NonNull View view, @NonNull GeoCoordinates geoCoordinates) {
        this.mViewPinsManager = new WeakReference<>(viewPinsManager);
        this.mView = view;
        this.mGeoCoordinates = geoCoordinates;
    }

    @Override // com.here.sdk.mapview.MapView.ViewPin
    public Anchor2D getAnchorPoint() {
        return this.mAnchorPoint;
    }

    @Override // com.here.sdk.mapview.MapView.ViewPin
    public GeoCoordinates getGeoCoordinates() {
        return this.mGeoCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getView() {
        return this.mView;
    }

    @Override // com.here.sdk.mapview.MapView.ViewPin
    public void setAnchorPoint(@NonNull Anchor2D anchor2D) {
        this.mAnchorPoint = new Anchor2D(anchor2D.horizontal, anchor2D.vertical);
        updatePosition();
    }

    @Override // com.here.sdk.mapview.MapView.ViewPin
    public void setGeoCoordinates(@NonNull GeoCoordinates geoCoordinates) {
        this.mGeoCoordinates = geoCoordinates;
        updatePosition();
    }

    @Override // com.here.sdk.mapview.MapView.ViewPin
    public void unpin() {
        ViewPinsManager viewPinsManager = this.mViewPinsManager.get();
        if (viewPinsManager != null) {
            viewPinsManager.unpinView(getView());
        }
        this.mViewPinsManager = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updatePosition() {
        ViewPinsManager viewPinsManager = this.mViewPinsManager.get();
        if (viewPinsManager == null) {
            return;
        }
        Point2D convertToScreenCoordinates = viewPinsManager.convertToScreenCoordinates(this.mGeoCoordinates);
        if (convertToScreenCoordinates == null) {
            viewPinsManager.removeView(this.mView);
            return;
        }
        if (this.mView.getParent() == null) {
            viewPinsManager.addView(this.mView, sOverlayParameter);
        }
        this.mView.setTranslationX((float) (convertToScreenCoordinates.x + (-(this.mView.getMeasuredWidth() * this.mAnchorPoint.horizontal))));
        this.mView.setTranslationY((float) (convertToScreenCoordinates.y + (-(this.mView.getMeasuredHeight() * this.mAnchorPoint.vertical))));
    }
}
