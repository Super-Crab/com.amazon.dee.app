package com.amazon.alexa.smarthomecameras.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.R;
import com.amazon.alexa.smarthomecameras.constants.RoutingConstants;
import com.amazon.alexa.smarthomecameras.constants.SharedPreferenceConstants;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceView;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public class PortraitCameraView extends CameraView {
    private static final int LIVE_BADGE_VIEW_TOP_CONSTRAINT = 150;
    private static final String TAG = PortraitCameraView.class.getSimpleName();
    private SharedPreferences.Editor editor;
    private TextView micIconLabel;
    private RoutingRegistry routingRegistry;
    private SharedPreferences sharedPref;
    private RelativeLayout smartAlertsRouteContainer;
    private TextView speakerIconLabel;

    public PortraitCameraView(Context context, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, NetworkService networkService, RoutingRegistry routingRegistry, FeatureServiceV2 featureServiceV2) {
        super(context, entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, networkService, featureServiceV2);
        this.sharedPref = this.context.getSharedPreferences(SharedPreferenceConstants.PREFERENCE_GROUP_NAME, 0);
        this.editor = this.sharedPref.edit();
        this.routingRegistry = routingRegistry;
    }

    private void startLiveViewActivity() {
        if (this.isSurfaceViewInitialized) {
            Intent intent = new Intent(this.context, LiveViewActivity.class);
            intent.putExtra("entityId", this.entityId.getValue());
            this.shouldContinueLiveStream = true;
            intent.putExtra(LiveViewActivity.EXTRA_CAMERA_LABEL, this.cameraLabel.getValue());
            intent.putExtra(LiveViewActivity.EXTRA_DEVICE_PAYLOAD, this.devicePayload);
            this.presenter.onViewPaused(false);
            getContext().startActivity(intent);
        }
        Log.e(TAG, "No active session, cannot launch activity");
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void coachMarkViewed() {
        this.editor.putBoolean(SharedPreferenceConstants.LIVE_VIEW_PORTRAIT_ARROW_COACHMARKS_VIEWED, true);
        this.editor.apply();
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected String getLoggingTag() {
        return TAG;
    }

    @VisibleForTesting
    void inflateLayout() {
        LinearLayout.inflate(this.context, R.layout.portrait, this);
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void initLayout() {
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        inflateLayout();
    }

    public /* synthetic */ void lambda$setupChildViews$0$PortraitCameraView(View view) {
        Log.i(TAG, "Fullscreen button tapped");
        startLiveViewActivity();
    }

    public /* synthetic */ void lambda$setupChildViews$1$PortraitCameraView(View view) {
        this.presenter.onSmartAlertsIngressClicked();
    }

    public /* synthetic */ boolean lambda$setupChildViews$2$PortraitCameraView(PtzListener ptzListener, RTCSurfaceView rTCSurfaceView, View view, MotionEvent motionEvent) {
        if (view.getParent() != null && this.devicePayload.supportsPhysicalTilt()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
        }
        return ptzListener.onTouch(rTCSurfaceView, motionEvent);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void notifyPreferencesFetched() {
        this.smartAlertsRouteContainer.setVisibility(0);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setMicAndSpeakerLabelsGone() {
        this.micIconLabel = (TextView) findView(R.id.mic_icon_label);
        this.speakerIconLabel = (TextView) findView(R.id.speaker_icon_label);
        if (this.devicePayload.isiRobot()) {
            this.micIconLabel.setVisibility(8);
            this.speakerIconLabel.setVisibility(8);
        } else if (!this.devicePayload.isNest() || this.devicePayload.supportsFullDuplexComms()) {
        } else {
            this.micIconLabel.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setSmartAlertsIngressVisible(boolean z) {
        if (z) {
            this.smartAlertsRouteContainer.setVisibility(4);
        } else {
            this.smartAlertsRouteContainer.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void setupChildViews() {
        setupPrivacyBanner();
        ((ImageButton) findView(R.id.fullscreen_button)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$PortraitCameraView$yrdu7amxFm-adiIwldjPz2UK-90
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PortraitCameraView.this.lambda$setupChildViews$0$PortraitCameraView(view);
            }
        });
        ((Button) findView(R.id.sa_ingress_button)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$PortraitCameraView$E75van6Vcw8_k2wHL5S77NYTEJ8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PortraitCameraView.this.lambda$setupChildViews$1$PortraitCameraView(view);
            }
        });
        CardView cardView = (CardView) findView(R.id.card_view);
        if (this.devicePayload.isiRobot()) {
            ((ConstraintLayout.LayoutParams) cardView.getLayoutParams()).dimensionRatio = "h,4:3";
        } else if (this.devicePayload.isNetatmo()) {
            ((ConstraintLayout.LayoutParams) cardView.getLayoutParams()).dimensionRatio = "h,9:16";
        }
        final RTCSurfaceView rTCSurfaceView = (RTCSurfaceView) findView(R.id.video_view);
        final PtzListener createPtzListener = this.ptzListenerFactory.createPtzListener(rTCSurfaceView, this.presenter, this.devicePayload);
        rTCSurfaceView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$PortraitCameraView$gnkhwoUhM2ky3J-VbCirQD0CRYM
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return PortraitCameraView.this.lambda$setupChildViews$2$PortraitCameraView(createPtzListener, rTCSurfaceView, view, motionEvent);
            }
        });
        this.routingRegistry.register(new Route.Builder("v2/cameras/smart-alerts/dashboard-page", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/cameras/smart-alerts/dashboard-page").build());
        this.routingRegistry.register(new Route.Builder(RoutingConstants.SA_LANDING_PAGE_ROUTE, 1).asRoot().withParent(RouteName.MAIN).withTemplate(RoutingConstants.SA_LANDING_PAGE_ROUTE_TEMPLATE).build());
        this.smartAlertsRouteContainer = (RelativeLayout) findView(R.id.sa_ingress_container);
        setLoadingVisible(true);
    }

    protected void setupPrivacyBanner() {
        Set<String> stringSet = this.sharedPref.getStringSet(SharedPreferenceConstants.LIVE_VIEW_PRIVACY_BANNER_VIEWED, null);
        if (stringSet == null || !stringSet.contains(this.entityId.getValue())) {
            ((TextView) findView(R.id.privacy_banner)).setVisibility(0);
            LinearLayout linearLayout = (LinearLayout) findView(R.id.live_badge);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 150;
            linearLayout.setLayoutParams(layoutParams);
            if (stringSet == null) {
                stringSet = new HashSet<>();
            }
            stringSet.add(this.entityId.getValue());
            this.editor.putStringSet(SharedPreferenceConstants.LIVE_VIEW_PRIVACY_BANNER_VIEWED, stringSet);
            this.editor.apply();
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CameraView
    protected void startCoachMarkAnimation() {
        if (this.sharedPref.getBoolean(SharedPreferenceConstants.LIVE_VIEW_PORTRAIT_ARROW_COACHMARKS_VIEWED, false) || !this.devicePayload.supportsPhysicalPan()) {
            return;
        }
        showCoachMarks();
    }
}
