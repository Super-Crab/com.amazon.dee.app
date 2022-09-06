package com.amazon.mobile.heremapsexplore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.mobile.heremapsexplore.Constants.Events;
import com.amazon.mobile.heremapsexplore.Utilities.UIUtils;
import com.here.sdk.core.Point2D;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public class GeofenceView extends View {
    private static final int FILL_COLOR = UITheme.DarkTheme.getControlActiveWithAlpha(0.2f);
    private static final int STROKE_COLOR = UITheme.DarkTheme.getControlActive();
    private int canvasHeight;
    private int canvasWidth;
    private GeofenceDrawable mDrawable;
    private final float strokeWidth;
    private final float strokeWidthDragging;
    private WeakReference<HereMapExploreView> weakHereMapExploreView;

    public GeofenceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.strokeWidth = UIUtils.dpToPx(this, 1);
        this.strokeWidthDragging = UIUtils.dpToPx(this, 3);
        this.mDrawable = new GeofenceDrawable(FILL_COLOR, STROKE_COLOR);
        this.mDrawable.setShowBorder(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect getBounds() {
        return this.mDrawable.getBounds();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandleDragging(int i) {
        HereMapExploreView hereMapExploreView = this.weakHereMapExploreView.get();
        if (hereMapExploreView == null) {
            return;
        }
        double checkAndApplyRadiusRange = hereMapExploreView.checkAndApplyRadiusRange();
        if (checkAndApplyRadiusRange == hereMapExploreView.getMaxGeofenceRadius() && i > 0) {
            return;
        }
        if (checkAndApplyRadiusRange == hereMapExploreView.getMinGeofenceRadius() && i < 0) {
            return;
        }
        Rect bounds = this.mDrawable.getBounds();
        int i2 = bounds.left;
        int i3 = i2 - i;
        int i4 = this.canvasWidth;
        if (i3 > i4 / 2) {
            int i5 = this.canvasHeight;
            this.mDrawable.setBounds(i4 / 2, i5 / 2, i4 / 2, i5 / 2);
            hereMapExploreView.checkFenceAndPostUpdate(false, Events.ON_GEOFENCE_UPDATING);
        } else {
            this.mDrawable.setBounds(i2 - i, bounds.top - i, bounds.right + i, bounds.bottom + i);
            hereMapExploreView.checkFenceAndPostUpdate(true, Events.ON_GEOFENCE_UPDATING);
        }
        this.mDrawable.setStrokeWidth(this.strokeWidthDragging);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandlePressed() {
        this.mDrawable.setShowBorder(true);
        this.mDrawable.setStrokeWidth(this.strokeWidth);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandleReleased() {
        this.mDrawable.setShowBorder(false);
        invalidate();
        if (this.weakHereMapExploreView.get() != null) {
            this.weakHereMapExploreView.get().checkFenceAndPostUpdate(true, Events.ON_GEOFENCE_UPDATED);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        this.canvasHeight = i2 - (getPaddingBottom() + getPaddingTop());
        this.canvasWidth = i - paddingRight;
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeToAnchor(Point2D point2D) {
        int i = (int) ((this.canvasHeight / 2) - point2D.y);
        int i2 = (int) ((this.canvasWidth / 2) - point2D.x);
        int sqrt = (int) Math.sqrt((i2 * i2) + (i * i));
        GeofenceDrawable geofenceDrawable = this.mDrawable;
        int i3 = this.canvasWidth;
        int i4 = this.canvasHeight;
        geofenceDrawable.setBounds((i3 / 2) - sqrt, (i4 / 2) - sqrt, (i3 / 2) + sqrt, (i4 / 2) + sqrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMapView(HereMapExploreView hereMapExploreView) {
        this.weakHereMapExploreView = new WeakReference<>(hereMapExploreView);
    }
}
