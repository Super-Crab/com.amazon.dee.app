package com.here.sdk.mapview;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapView;
import java.util.LinkedList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ViewPinsManager extends FrameLayout implements MapCameraObserver {
    private GeoConverter mConverter;
    private int mHeight;
    private List<ViewPinImpl> mViewPins;
    private int mWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPinsManager(Context context) {
        super(context);
        this.mViewPins = new LinkedList();
    }

    private ViewPinImpl findViewPin(@NonNull View view) {
        for (ViewPinImpl viewPinImpl : this.mViewPins) {
            if (viewPinImpl.getView() == view) {
                return viewPinImpl;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Point2D convertToScreenCoordinates(@NonNull GeoCoordinates geoCoordinates) {
        Point2D apply;
        GeoConverter geoConverter = this.mConverter;
        if (geoConverter != null && (apply = geoConverter.apply(geoCoordinates)) != null) {
            double d = apply.x;
            if (d >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && d < this.mWidth) {
                double d2 = apply.y;
                if (d2 >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && d2 < this.mHeight) {
                    return apply;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ViewPinImpl> getViewPins() {
        return this.mViewPins;
    }

    @Override // com.here.sdk.mapview.MapCameraObserver
    public void onCameraUpdated(@NonNull MapCamera.State state) {
        updatePositions();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public MapView.ViewPin pinView(@NonNull View view, @NonNull GeoCoordinates geoCoordinates) {
        if (findViewPin(view) != null) {
            return null;
        }
        ViewPinImpl viewPinImpl = new ViewPinImpl(this, view, geoCoordinates);
        this.mViewPins.add(viewPinImpl);
        viewPinImpl.getView().measure(this.mWidth, this.mHeight);
        viewPinImpl.updatePosition();
        return viewPinImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(GeoConverter geoConverter, int i, int i2) {
        this.mConverter = geoConverter;
        this.mWidth = i;
        this.mHeight = i2;
        measure(i, i2);
        for (ViewPinImpl viewPinImpl : this.mViewPins) {
            viewPinImpl.getView().measure(this.mWidth, this.mHeight);
            viewPinImpl.updatePosition();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unpinView(@NonNull View view) {
        ViewPinImpl findViewPin = findViewPin(view);
        if (findViewPin == null) {
            return;
        }
        this.mViewPins.remove(findViewPin);
        removeView(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updatePositions() {
        for (ViewPinImpl viewPinImpl : this.mViewPins) {
            viewPinImpl.updatePosition();
        }
    }
}
