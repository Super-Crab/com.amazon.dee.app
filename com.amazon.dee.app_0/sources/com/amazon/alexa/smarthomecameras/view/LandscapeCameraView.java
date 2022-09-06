package com.amazon.alexa.smarthomecameras.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.R;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.constants.SharedPreferenceConstants;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
/* loaded from: classes10.dex */
public class LandscapeCameraView extends CameraView {
    private static final int ANIMATION_DURATION = 200;
    private static final int CHROME_HIDE_INTERVAL = 5000;
    private static final int MAX_TAP_DURATION = 300;
    private static final String TAG = LandscapeCameraView.class.getSimpleName();
    private final Activity activity;
    private ImageButton backButton;
    private ConstraintLayout chromeView;
    private SharedPreferences.Editor editor;
    private final CamerasMobilyticsService mobilyticsService;
    private SharedPreferences sharedPref;
    private long tapInitializeTime;

    public LandscapeCameraView(Activity activity, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, CamerasMobilyticsService camerasMobilyticsService, NetworkService networkService, FeatureServiceV2 featureServiceV2) {
        super(activity, entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, networkService, featureServiceV2);
        this.sharedPref = this.context.getSharedPreferences(SharedPreferenceConstants.PREFERENCE_GROUP_NAME, 0);
        this.editor = this.sharedPref.edit();
        this.activity = activity;
        this.mobilyticsService = camerasMobilyticsService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chromeVisibleWithAnimation(final boolean z) {
        this.chromeView.animate().alpha(z ? 1.0f : 0.0f).setDuration(200L).setListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.smarthomecameras.view.LandscapeCameraView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (z) {
                    LandscapeCameraView.this.chromeView.setVisibility(0);
                    LandscapeCameraView.this.countdownChromeVisbility();
                    return;
                }
                LandscapeCameraView.this.chromeView.setVisibility(8);
            }
        });
    }

    private void cleanupLandscapeView() {
        this.shouldContinueLiveStream = true;
        this.presenter.onViewPaused(false);
        this.activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void countdownChromeVisbility() {
        this.chromeView.postDelayed(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.LandscapeCameraView.2
            @Override // java.lang.Runnable
            public void run() {
                LandscapeCameraView.this.chromeVisibleWithAnimation(false);
            }
        }, 5000L);
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void coachMarkViewed() {
        this.editor.putBoolean(SharedPreferenceConstants.LIVE_VIEW_LANDSCAPE_ARROW_COACHMARKS_VIEWED, true);
        this.editor.apply();
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected String getLoggingTag() {
        return TAG;
    }

    @VisibleForTesting
    void inflateLayout() {
        LinearLayout.inflate(this.context, R.layout.landscape, this);
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void initLayout() {
        setOrientation(0);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        inflateLayout();
        setBackground(this.context.getDrawable(R.color.blackTint));
    }

    public /* synthetic */ boolean lambda$setupChildViews$0$LandscapeCameraView(PtzListener ptzListener, RTCSurfaceView rTCSurfaceView, View view, MotionEvent motionEvent) {
        boolean onTouch = ptzListener.onTouch(rTCSurfaceView, motionEvent);
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1 && Calendar.getInstance().getTimeInMillis() - this.tapInitializeTime < 300) {
                if (this.chromeView.getVisibility() == 0) {
                    chromeVisibleWithAnimation(false);
                } else {
                    chromeVisibleWithAnimation(true);
                }
            }
        } else {
            this.tapInitializeTime = Calendar.getInstance().getTimeInMillis();
        }
        return onTouch;
    }

    public /* synthetic */ void lambda$setupChildViews$1$LandscapeCameraView(View view) {
        Log.i(TAG, "Back button clicked");
        cleanupLandscapeView();
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.LANDSCAPE_BACK_PRESS);
        this.presenter.onBackButtonClicked();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void notifyPreferencesFetched() {
    }

    public void onBackPressed() {
        Log.i(TAG, "Back button clicked");
        cleanupLandscapeView();
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.LANDSCAPE_BACK_PRESS);
        this.presenter.onBackButtonClicked();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onConfigurationChanged ");
        outline107.append(configuration.orientation);
        Log.i(str, outline107.toString());
        if (configuration.orientation == 1) {
            this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.PORTRAIT_TRANSITION);
            cleanupLandscapeView();
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setMicAndSpeakerLabelsGone() {
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setSmartAlertsIngressVisible(boolean z) {
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void setupChildViews() {
        final RTCSurfaceView rTCSurfaceView = (RTCSurfaceView) findView(R.id.video_view);
        this.chromeView = (ConstraintLayout) findView(R.id.chrome_container);
        if (this.devicePayload.isiRobot()) {
            ((ConstraintLayout.LayoutParams) rTCSurfaceView.getLayoutParams()).dimensionRatio = "w,4:3";
        } else if (this.devicePayload.isNetatmo()) {
            ((ConstraintLayout.LayoutParams) rTCSurfaceView.getLayoutParams()).dimensionRatio = "w,9:16";
        }
        countdownChromeVisbility();
        final PtzListener createPtzListener = this.ptzListenerFactory.createPtzListener(rTCSurfaceView, this.presenter, this.devicePayload);
        rTCSurfaceView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$LandscapeCameraView$xobFwtGjSyJhmDocDB_58bMHuf0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LandscapeCameraView.this.lambda$setupChildViews$0$LandscapeCameraView(createPtzListener, rTCSurfaceView, view, motionEvent);
            }
        });
        startCoachMarkAnimation();
        this.backButton = (ImageButton) findView(R.id.back_button);
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$LandscapeCameraView$XVX1lWF37LbuArC3Tx666hHedCE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LandscapeCameraView.this.lambda$setupChildViews$1$LandscapeCameraView(view);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void startCoachMarkAnimation() {
        if (this.sharedPref.getBoolean(SharedPreferenceConstants.LIVE_VIEW_LANDSCAPE_ARROW_COACHMARKS_VIEWED, false) || !this.devicePayload.supportsPhysicalPan()) {
            return;
        }
        showCoachMarks();
    }
}
