package com.amazon.alexa.smarthomecameras.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponentProvider;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraViewScope;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.LandscapeViewModule;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import dagger.Component;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class LiveViewActivity extends Activity {
    public static final String EXTRA_CAMERA_LABEL = "cameraLabel";
    public static final String EXTRA_DEVICE_PAYLOAD = "devicePayload";
    public static final String EXTRA_ENTITY_ID = "entityId";
    private static final String TAG = LiveViewActivity.class.getSimpleName();
    private CameraLabel cameraLabel;
    @Inject
    LandscapeCameraView cameraView;
    private DevicePayload devicePayload;
    private EntityId entityId;
    @Inject
    CamerasMobilyticsService mobilyticsService;
    @Inject
    ActivityOrientationListener orientationEventListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(dependencies = {CameraComponent.class}, modules = {CameraModule.class, LandscapeViewModule.class})
    @CameraViewScope
    /* loaded from: classes10.dex */
    public interface Injector {
        void inject(LiveViewActivity liveViewActivity);
    }

    @VisibleForTesting
    void injectDependencies() {
        CameraComponent cameraComponent = CameraComponentProvider.getCameraComponent();
        if (cameraComponent == null) {
            Log.e(TAG, "Cannot start activity with a null camera component");
            finish();
            return;
        }
        DaggerLiveViewActivity_Injector.builder().cameraComponent(cameraComponent).cameraModule(new CameraModule(this.entityId, this.cameraLabel, this.devicePayload)).landscapeViewModule(new LandscapeViewModule(this)).build().inject(this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.cameraView.onBackPressed();
    }

    @Override // android.app.Activity
    @SuppressLint({"SourceLockedOrientationActivity"})
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.entityId = EntityId.create(intent.getStringExtra("entityId"));
        this.cameraLabel = CameraLabel.create(intent.getStringExtra(EXTRA_CAMERA_LABEL));
        this.devicePayload = (DevicePayload) intent.getParcelableExtra(EXTRA_DEVICE_PAYLOAD);
        if (this.entityId == null || this.cameraLabel == null || this.devicePayload == null) {
            Log.e(TAG, "EntityId or CameraLabel or devicePayload cannot be null");
            finish();
        }
        injectDependencies();
        getWindow().getDecorView().setSystemUiVisibility(OlympusImageProcessingMakernoteDirectory.TagCameraTemperature);
        getWindow().addFlags(512);
        setRequestedOrientation(6);
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.LANDSCAPE_TRANSITION);
        this.orientationEventListener.initialize();
        this.cameraView.initialize();
        setContentView(this.cameraView);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        setRequestedOrientation(-1);
        this.orientationEventListener.disable();
    }
}
