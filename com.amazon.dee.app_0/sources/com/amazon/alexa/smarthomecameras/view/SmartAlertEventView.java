package com.amazon.alexa.smarthomecameras.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.IdRes;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.smarthomecameras.R;
import com.google.common.base.Preconditions;
/* loaded from: classes10.dex */
public class SmartAlertEventView extends LinearLayout {
    private static final String IMAGE_VIEW_DIMENSION_RATIO = "h,320:200";
    private static final String TAG = SmartAlertEventView.class.getSimpleName();
    private final Activity activity;
    private final Context context;
    private final String imageUrl;
    protected ZoomableImageView smartAlertsEventImageView;
    protected ConstraintLayout smartAlertsEventView;

    public SmartAlertEventView(Activity activity, Context context, String str) {
        super(context);
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(str, "imageUrl cannot be null");
        this.activity = activity;
        this.context = context;
        this.imageUrl = str;
    }

    private void initLayout() {
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        inflateLayout();
    }

    private void setupViews() {
        this.smartAlertsEventView = (ConstraintLayout) findView(R.id.sa_event_constraintlayout);
        this.smartAlertsEventImageView = (ZoomableImageView) findView(R.id.sa_event_image_view);
        this.smartAlertsEventImageView.setImageResourceURL(this.imageUrl);
    }

    @VisibleForTesting
    public <T extends View> T findView(@IdRes int i) {
        return (T) findViewById(i);
    }

    @VisibleForTesting
    void inflateLayout() {
        LinearLayout.inflate(this.context, R.layout.sa_event, this);
    }

    public void initialize() {
        ThemeUtil.setTheme(this.context);
        initLayout();
        setupViews();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation == 2) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.smartAlertsEventView);
            constraintSet.setDimensionRatio(R.id.sa_event_image_view, "");
            constraintSet.applyTo(this.smartAlertsEventView);
        } else {
            ConstraintSet constraintSet2 = new ConstraintSet();
            constraintSet2.clone(this.smartAlertsEventView);
            constraintSet2.setDimensionRatio(R.id.sa_event_image_view, IMAGE_VIEW_DIMENSION_RATIO);
            constraintSet2.applyTo(this.smartAlertsEventView);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int round = Math.round(motionEvent.getX());
        int round2 = Math.round(motionEvent.getY());
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (round > childAt.getLeft() && round < childAt.getRight() && round2 > childAt.getTop() && round2 < childAt.getBottom()) {
                Log.i(TAG, "onTouchEvent is not image view");
                if (motionEvent.getAction() == 1) {
                    this.activity.finish();
                }
            }
        }
        return true;
    }
}
