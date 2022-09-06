package com.amazon.alexa.smarthomecameras.view;

import android.animation.Animator;
import android.content.Context;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.R;
import com.amazon.alexa.smarthomecameras.constants.ErrorConstants;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.model.SessionId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.alexa.smarthomecameras.util.ErrorMessageProvider;
import com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceView;
import com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import rx.functions.Action1;
/* loaded from: classes10.dex */
public abstract class CameraView extends LinearLayout implements CameraViewContract.View {
    private static final long HANDLER_TIMEOUT_IN_MS = 1000;
    private final String TAG;
    protected final CameraLabel cameraLabel;
    private RelativeLayout coachmarkView;
    protected final Context context;
    protected final DevicePayload devicePayload;
    protected final EntityId entityId;
    private RelativeLayout errorView;
    protected final FeatureServiceV2 featureServiceV2;
    protected volatile boolean isSurfaceViewInitialized;
    private LottieAnimationView leftSwipeAnimation;
    private LinearLayout liveBadgeView;
    private RelativeLayout loadingView;
    private ImageView micButton;
    protected final NetworkService networkService;
    private ImageButton playButton;
    protected final CameraViewContract.Presenter presenter;
    protected final PtzListenerFactory ptzListenerFactory;
    private LottieAnimationView rightSwipeAnimation;
    protected volatile boolean shouldContinueLiveStream;
    private ImageView speakerButton;
    private RTCSurfaceView surfaceView;
    private TextView txtError;
    private TextView txtStatus;

    public CameraView(Context context, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, NetworkService networkService, FeatureServiceV2 featureServiceV2) {
        super(context);
        this.TAG = getLoggingTag();
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(entityId, "EntityId cannot be null");
        Preconditions.checkNotNull(cameraLabel, "Camera label cannot be null");
        Preconditions.checkNotNull(devicePayload, "Device Payload cannot be null");
        Preconditions.checkNotNull(presenter, "Presenter cannot be null");
        Preconditions.checkNotNull(ptzListenerFactory, "PtzListenerFactory cannot be null");
        Preconditions.checkNotNull(networkService, "NetworkService cannot be null");
        Preconditions.checkNotNull(featureServiceV2, "FeatureServiceV2 cannot be null");
        this.context = context;
        this.entityId = entityId;
        this.cameraLabel = cameraLabel;
        this.devicePayload = devicePayload;
        this.presenter = presenter;
        this.ptzListenerFactory = ptzListenerFactory;
        this.networkService = networkService;
        this.featureServiceV2 = featureServiceV2;
        presenter.setCameraView(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$executeOnMainThread$14(Runnable runnable, ConditionVariable conditionVariable) {
        runnable.run();
        conditionVariable.open();
    }

    private void setupViews() {
        this.micButton = (ImageView) findView(R.id.mic_button);
        this.micButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$iH7wflbhXdmbPxRb-_oweBSzSVI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraView.this.lambda$setupViews$0$CameraView(view);
            }
        });
        this.speakerButton = (ImageView) findView(R.id.speaker_button);
        this.speakerButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$03RnITF2CIsxIpAwQfGuP6quR6I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraView.this.lambda$setupViews$1$CameraView(view);
            }
        });
        if (this.devicePayload.isiRobot()) {
            this.micButton.setVisibility(8);
            this.speakerButton.setVisibility(8);
        } else if (this.devicePayload.isNest() && !this.devicePayload.supportsFullDuplexComms()) {
            this.micButton.setVisibility(8);
        }
        this.playButton = (ImageButton) findView(R.id.play_button);
        this.playButton.setVisibility(8);
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$SAheO20TzvaTvUg13Tb3xL1jc8o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraView.this.lambda$setupViews$2$CameraView(view);
            }
        });
        this.txtStatus = (TextView) findView(R.id.txt_status);
        this.txtStatus.setVisibility(8);
        this.liveBadgeView = (LinearLayout) findView(R.id.live_badge);
        this.liveBadgeView.setVisibility(4);
        this.loadingView = (RelativeLayout) findView(R.id.loading_view);
        this.errorView = (RelativeLayout) findView(R.id.error_view);
        this.coachmarkView = (RelativeLayout) findView(R.id.coachmarks_view);
        this.txtError = (TextView) findView(R.id.error_message);
        this.leftSwipeAnimation = (LottieAnimationView) findView(R.id.left_arrow_animation);
        this.rightSwipeAnimation = (LottieAnimationView) findView(R.id.right_arrow_animation);
        this.surfaceView = (RTCSurfaceView) findView(R.id.video_view);
        this.surfaceView.setScalingType(RTCSurfaceViewInterface.ScalingType.SCALE_ASPECT_FILL);
        RTCSurfaceView rTCSurfaceView = this.surfaceView;
        rTCSurfaceView.setOnTouchListener(this.ptzListenerFactory.createPtzListener(rTCSurfaceView, this.presenter, this.devicePayload));
        this.coachmarkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$u29ohjS-_yEehtJGFq40bfLT2Xk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraView.this.lambda$setupViews$3$CameraView(view);
            }
        });
        this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$-VdvZOrD7R7lt5uTQlk2y1H4qUo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CameraView.this.lambda$setupViews$4$CameraView((Boolean) obj);
            }
        });
        setupChildViews();
    }

    private void toggleButtons(final boolean z) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$EjvBOi6tWaEuiiY8hUesGYvgTOE
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$toggleButtons$11$CameraView(z);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public boolean areRequiredPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") == 0;
    }

    protected abstract void coachMarkViewed();

    void executeOnMainThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$ZQsPH4F96IRi639ajtTIKcNsQic
                @Override // java.lang.Runnable
                public final void run() {
                    CameraView.lambda$executeOnMainThread$14(runnable, conditionVariable);
                }
            });
            conditionVariable.block(1000L);
            return;
        }
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    public <T extends View> T findView(@IdRes int i) {
        return (T) findViewById(i);
    }

    public Animator.AnimatorListener getAnimatorListener(final LottieAnimationView lottieAnimationView) {
        return new Animator.AnimatorListener() { // from class: com.amazon.alexa.smarthomecameras.view.CameraView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (lottieAnimationView == CameraView.this.leftSwipeAnimation) {
                    CameraView.this.leftSwipeAnimation.setVisibility(8);
                    CameraView.this.rightSwipeAnimation.setVisibility(0);
                    CameraView.this.rightSwipeAnimation.playAnimation();
                    return;
                }
                CameraView.this.rightSwipeAnimation.setVisibility(8);
                CameraView.this.coachmarkView.setVisibility(8);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        };
    }

    protected abstract String getLoggingTag();

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void hideStatusText() {
        this.txtStatus.setVisibility(8);
    }

    protected abstract void initLayout();

    public void initialize() {
        ThemeUtil.setTheme(this.context);
        initLayout();
        setupViews();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void initializeCameraView(final SessionId sessionId) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$Scbv16MrODu3DYKlosXnyluVjgg
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$initializeCameraView$7$CameraView(sessionId);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public boolean isMicEnabled() {
        return this.micButton.isEnabled();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public boolean isSpeakerEnabled() {
        return this.speakerButton.isEnabled();
    }

    public /* synthetic */ void lambda$initializeCameraView$7$CameraView(SessionId sessionId) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Initializing surface view for session: ");
        outline107.append(sessionId.getValue());
        outline107.toString();
        this.surfaceView.init(sessionId.getValue(), RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW);
        this.isSurfaceViewInitialized = true;
    }

    public /* synthetic */ void lambda$releaseCameraView$8$CameraView() {
        try {
            this.surfaceView.release();
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Release exception occurred: ");
            outline107.append(e.getMessage());
            outline107.toString();
        }
        this.liveBadgeView.setVisibility(4);
        this.isSurfaceViewInitialized = false;
    }

    public /* synthetic */ void lambda$setErrorVisible$10$CameraView(boolean z, String str, String str2) {
        toggleButtons(z);
        if (z) {
            this.errorView.setVisibility(0);
            this.liveBadgeView.setVisibility(4);
            this.txtError.setText(this.context.getString(new ErrorMessageProvider().getErrorMessageId(str, str2).intValue(), this.cameraLabel.getValue()));
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Error view displayed");
            return;
        }
        this.errorView.setVisibility(8);
        this.liveBadgeView.setVisibility(0);
        this.txtError.setText("");
    }

    public /* synthetic */ void lambda$setLoadingVisible$9$CameraView(boolean z) {
        toggleButtons(z);
        if (z) {
            this.loadingView.setVisibility(0);
            this.liveBadgeView.setVisibility(4);
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Loading dots started");
            return;
        }
        this.loadingView.setVisibility(8);
        this.liveBadgeView.setVisibility(0);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Loading dots stopped");
        startCoachMarkAnimation();
    }

    public /* synthetic */ void lambda$setMicrophoneToggleState$5$CameraView(boolean z) {
        if (z) {
            this.micButton.setImageDrawable(this.context.getDrawable(R.drawable.ic_mic_on));
            this.micButton.setBackground(this.context.getDrawable(R.drawable.toggle_on_circle));
            this.micButton.setColorFilter(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        int i = R.drawable.toggle_off_circle;
        this.micButton.setImageDrawable(this.context.getDrawable(R.drawable.ic_mic_off));
        this.micButton.setBackground(this.context.getDrawable(i));
        this.micButton.setColorFilter(-1);
    }

    public /* synthetic */ void lambda$setPlayButtonVisible$12$CameraView(boolean z) {
        if (z) {
            this.playButton.setVisibility(0);
        } else {
            this.playButton.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$setSpeakerToggleState$6$CameraView(boolean z) {
        if (z) {
            this.speakerButton.setImageDrawable(this.context.getDrawable(R.drawable.ic_volume_on));
            this.speakerButton.setBackground(this.context.getDrawable(R.drawable.toggle_on_circle));
            this.speakerButton.setColorFilter(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        int i = R.drawable.toggle_off_circle;
        this.speakerButton.setImageDrawable(this.context.getDrawable(R.drawable.ic_volume_muted));
        this.speakerButton.setBackground(this.context.getDrawable(i));
        this.speakerButton.setColorFilter(-1);
    }

    public /* synthetic */ void lambda$setupViews$0$CameraView(View view) {
        this.presenter.onMicToggleClicked();
    }

    public /* synthetic */ void lambda$setupViews$1$CameraView(View view) {
        this.presenter.onSpeakerToggleClicked();
    }

    public /* synthetic */ void lambda$setupViews$2$CameraView(View view) {
        this.presenter.onPlayButtonClicked();
    }

    public /* synthetic */ void lambda$setupViews$3$CameraView(View view) {
        this.coachmarkView.setVisibility(8);
    }

    public /* synthetic */ void lambda$setupViews$4$CameraView(Boolean bool) {
        if (!bool.booleanValue()) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Network is unreachable");
        }
        setErrorVisible(!bool.booleanValue(), ErrorConstants.NO_NETWORK, "");
        if (!bool.booleanValue()) {
            this.presenter.onNetworkDisconnected();
        }
    }

    public /* synthetic */ void lambda$showCoachMarks$13$CameraView() {
        this.coachmarkView.setVisibility(0);
        this.leftSwipeAnimation.setVisibility(0);
        coachMarkViewed();
        LottieAnimationView lottieAnimationView = this.leftSwipeAnimation;
        lottieAnimationView.addAnimatorListener(getAnimatorListener(lottieAnimationView));
        LottieAnimationView lottieAnimationView2 = this.rightSwipeAnimation;
        lottieAnimationView2.addAnimatorListener(getAnimatorListener(lottieAnimationView2));
        this.leftSwipeAnimation.playAnimation();
    }

    public /* synthetic */ void lambda$toggleButtons$11$CameraView(boolean z) {
        if (z) {
            this.micButton.setEnabled(false);
            this.speakerButton.setEnabled(false);
            this.micButton.setAlpha(0.7f);
            this.speakerButton.setAlpha(0.7f);
            return;
        }
        this.micButton.setEnabled(true);
        this.speakerButton.setEnabled(true);
        this.micButton.setAlpha(1.0f);
        this.speakerButton.setAlpha(1.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.presenter.onViewAttached();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.presenter.onViewDetached();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "View appeared");
            this.presenter.onViewStarted();
        } else if (i == 4 || i != 8) {
        } else {
            this.presenter.onViewPaused(!this.shouldContinueLiveStream);
            if (this.shouldContinueLiveStream) {
                this.shouldContinueLiveStream = false;
            } else {
                this.presenter.onViewHidden();
            }
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void releaseCameraView() {
        if (this.isSurfaceViewInitialized) {
            executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$ymx-78ccfbS_aaa-YzMVwdvf5MA
                @Override // java.lang.Runnable
                public final void run() {
                    CameraView.this.lambda$releaseCameraView$8$CameraView();
                }
            });
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void requestPermissions() {
        this.txtStatus.setVisibility(0);
        this.txtStatus.setText(R.string.permissions_required_message);
        this.loadingView.setVisibility(8);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setErrorVisible(final boolean z, final String str, final String str2) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$y2VYlAYAsIF7cJrS1r-ctDzoVFo
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$setErrorVisible$10$CameraView(z, str, str2);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setLoadingVisible(final boolean z) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$GvaYkbPQurDEYY9c_JSEqvmZ71Q
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$setLoadingVisible$9$CameraView(z);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setMicrophoneToggleState(final boolean z) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$QraC2yIhnDEZCGqGs7hiTw4tju4
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$setMicrophoneToggleState$5$CameraView(z);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setPlayButtonVisible(final boolean z) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$IWB7SqkMxFbqC8a9m_tHk2cKYH4
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$setPlayButtonVisible$12$CameraView(z);
            }
        });
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.View
    public void setSpeakerToggleState(final boolean z) {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$sgP90D-IlYS6bkEgjy3tpTAgDXU
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$setSpeakerToggleState$6$CameraView(z);
            }
        });
    }

    protected abstract void setupChildViews();

    public void showCoachMarks() {
        executeOnMainThread(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CameraView$ZicCrPgpjw8cCgfrb5CRHFeBEZk
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.lambda$showCoachMarks$13$CameraView();
            }
        });
    }

    protected abstract void startCoachMarkAnimation();
}
