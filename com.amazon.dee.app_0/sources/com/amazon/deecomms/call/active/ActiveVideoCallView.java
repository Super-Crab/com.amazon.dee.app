package com.amazon.deecomms.call.active;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.app.RemoteViewManager;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextUnavailableAsyncTask;
import com.amazon.deecomms.calling.accessibility.RealTimeTextUnavailableToast;
import com.amazon.deecomms.calling.accessibility.RealTimeTextView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract;
import com.amazon.deecomms.calling.incallexperiences.effects.ui.EffectsBottomSheetDialogFragmentView;
import com.amazon.deecomms.calling.incallexperiences.reactions.ui.ReactionsMenuButtonFragment;
import com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeButtonPresenter;
import com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeFloatingActionButton;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.AudioPickerPopUp;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.AccessibilityUtils;
import com.amazon.deecomms.common.util.AnimUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.ChangeOrientationListener;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import javax.inject.Inject;
import javax.inject.Named;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes12.dex */
public class ActiveVideoCallView extends ActiveCallView implements MediaListener, CallTimerManager.NotificationUpdateListener, Observer, RealTimeTextViewContract {
    private static final int FADING_ANIMATION_TIME = 300;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveVideoCallView.class);
    private static final int ORIENTATION_LANDSCAPE = 1;
    private static final int ORIENTATION_PORTRAIT = 0;
    private static final int TIME_TO_SHOW_NO_BUTTONS = 5000;
    private Call activeCall;
    private AudioPickerPopUp audioPickerPopUp;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    private ChangeOrientationListener changeOrientationListener;
    @Inject
    CommandProcessor commandProcessor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private EffectsMenuPresenterContract effectsMenuPresenter;
    private EffectsMenuViewContract effectsMenuViewContract;
    @Inject
    EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    private Handler mButtonHandler;
    private LinearLayout mCallButtons;
    private LinearLayout mCallControls;
    private TextView mCallDuration;
    private Context mContext;
    private boolean mIsMosaicThemeEnabled;
    private boolean mIsSpeakerOn;
    private boolean mIsThemedUIEnabled;
    private int mLocalOrientation;
    private int mRemoteOrientation;
    private RemoteViewManager mRemoteViewManager;
    private int mRotation;
    private WebRTCViewRenderer.ScalingType mScalingType;
    private SelfViewManager mSelfViewManager;
    private ImageView mSpeakerIcon;
    private ImageView mToggleCamera;
    private ImageButton mVideoToggleButton;
    private View.OnClickListener mVideoToggleClickListener;
    private RelativeLayout mpuToggleCamera;
    private PopupMenu popupMenu;
    private ReactionsMenuPresenter reactionsMenuPresenter;
    private ReactionsMenuButtonFragment reactionsMenuViewContract;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextPresenter realTimeTextPresenter;
    private RealTimeTextUnavailableToast realTimeTextUnavailableToast;
    private RealTimeTextView realTimeTextView;
    private Runnable showNoButtonsRunnable;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private StoryTimeButtonPresenter storyTimeButtonPresenterContract;
    @Inject
    TelecomBridge telecomBridge;
    private TelecomCallAudioManager telecomCallAudioManager;
    @Inject
    TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;
    private View.OnClickListener toggleCameraOnClickListener;

    public ActiveVideoCallView(@NonNull Context context, @NonNull SelfViewManager selfViewManager, @Nullable RemoteViewManager remoteViewManager, @Nullable ImageView imageView, int i, boolean z, boolean z2) {
        super(context, selfViewManager);
        this.mRotation = 0;
        CommsDaggerWrapper.getComponent().inject(this);
        this.mContext = context;
        this.mSelfViewManager = selfViewManager;
        this.mScalingType = WebRTCViewRenderer.ScalingType.SCALE_ASPECT_BALANCED;
        this.mLocalOrientation = 0;
        this.mRemoteOrientation = 0;
        this.mRotation = i;
        this.mRemoteViewManager = remoteViewManager;
        this.mSelfViewManager.minimizeSelfView(i);
        this.mSelfViewManager.bringToFront();
        this.mSelfViewManager.setContentDescription(Utils.getStringFromResource(R.string.pip_switch_to_rear_camera));
        this.mToggleCamera = imageView;
        this.mIsThemedUIEnabled = z;
        this.mIsMosaicThemeEnabled = z2;
        setupLayout(i);
    }

    private static int getOrientation(int i, int i2, int i3) {
        if (i % 180 != 0) {
            i3 = i2;
            i2 = i3;
        }
        return i2 > i3 ? 1 : 0;
    }

    private String getString(int i) {
        return Utils.getStringFromResource(i);
    }

    private void handleNonTelecomSpeakerButtonClickEvent() {
        this.mIsSpeakerOn = !this.mIsSpeakerOn;
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("speakerButton onClick:enabled="), this.mIsSpeakerOn, LOG);
        this.mAudioManager.setSpeakerphoneOn(this.mIsSpeakerOn);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone ");
        outline1.append(this.mIsSpeakerOn);
        outline1.append("; speakerphone was set to ");
        outline1.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger.i(outline1.toString());
        CallUtils.notifySpeakerStateChange(this.mContext);
        setSpeakerIconState(this.mIsSpeakerOn);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAllCallControls() {
        LOG.i(" Hiding the call buttons in Active Call View ");
        this.mButtonHandler.removeCallbacks(this.showNoButtonsRunnable);
        AnimUtils.fadingAnimation(this.mContext, this.mCallButtons, 300, false);
        this.mCallButtons.setVisibility(8);
        this.mToggleCamera.setVisibility(4);
        this.mCallDuration.setVisibility(4);
        showReactionsButton();
        this.mSelfViewManager.hideScrim();
    }

    private void hideReactionsButton() {
        ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuViewContract;
        if (reactionsMenuButtonFragment != null) {
            reactionsMenuButtonFragment.hideButton();
        }
    }

    private void init() {
        MetricsHelper.startCallDurationTimer(this.activeCall);
        this.mVideoToggleButton = (ImageButton) findViewById(R.id.videoToggleButton);
        this.mVideoToggleButton.setEnabled(true);
        this.mCallButtons = (LinearLayout) findViewById(R.id.call_button_layout);
        this.mCallControls = (LinearLayout) findViewById(R.id.call_controls_layout);
        this.mSpeakerIcon = (ImageView) this.mCallButtons.findViewById(R.id.speakerButton);
        this.mpuToggleCamera = (RelativeLayout) this.mCallButtons.findViewById(R.id.mpu_togglecam);
        if (TelecomUtils.isAudioPickerEnabled()) {
            this.telecomCallAudioManager = new TelecomCallAudioManager(this.telecomBridge, this.mAudioManager);
            this.mIsSpeakerOn = Utils.isFireOS() || !DeviceInfo.isPhone(getContext());
            if (Utils.areAccessoriesConnected()) {
                LOG.i("Disabling speaker as PCC session is available");
                this.mSpeakerIcon.setEnabled(false);
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
            } else {
                this.mSpeakerIcon.setEnabled(true);
            }
            initializeSpeakerIcon();
        }
        if (!this.mIsThemedUIEnabled) {
            int color = ContextCompat.getColor(this.mContext, R.color.video_button_text_color);
            this.mContactName.setTextColor(color);
            this.mCallStatus.setTextColor(color);
        }
        this.mCallDuration = (TextView) findViewById(R.id.screen_title);
        this.mVideoToggleClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveVideoCallView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ActiveVideoCallView.this.mCurrentCall.getMediaStatus().isLocalVideoEnabled()) {
                    ActiveVideoCallView.this.sipClientState.setUserTurnedVideoOff(true);
                    ActiveVideoCallView.this.commandProcessor.enableVideoStreamInVideoCall(false);
                    ActiveVideoCallView.this.mSelfViewManager.hideSelfView();
                    return;
                }
                ActiveVideoCallView.this.sipClientState.setUserTurnedVideoOff(false);
                ActiveVideoCallView.this.commandProcessor.enableVideoStreamInVideoCall(true);
                ActiveVideoCallView.this.mSelfViewManager.showSelfView();
            }
        };
        this.toggleCameraOnClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveVideoCallView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActiveVideoCallView.LOG.i("Toggle Camera Clicked");
                Call currentActiveCall = ActiveVideoCallView.this.sipClientState.getCurrentActiveCall();
                if (currentActiveCall != null) {
                    currentActiveCall.switchCamera();
                }
            }
        };
        if (this.sipClientState.isEnhancedProcessedCall()) {
            this.mpuToggleCamera.findViewById(R.id.cameraToggleButton).setOnClickListener(this.toggleCameraOnClickListener);
            this.mpuToggleCamera.setVisibility(0);
        }
        this.mButtonHandler = new Handler();
        this.showNoButtonsRunnable = new Runnable() { // from class: com.amazon.deecomms.call.active.-$$Lambda$ActiveVideoCallView$lHhLIQ27qQxGk3enhwr2ztX5M1A
            @Override // java.lang.Runnable
            public final void run() {
                ActiveVideoCallView.this.lambda$init$1$ActiveVideoCallView();
            }
        };
        this.telecomCallAudioRouteObservable.addObserver(this);
        this.enhancedProcessingStateObservable.addObserver(this);
        setupRTT();
        setupRTTLayouts();
        setupStoryTime();
        if (this.reactionsMenuViewContract != null && this.capabilitiesManager.isReactionsEnabled()) {
            this.reactionsMenuViewContract.initialize((LinearLayout) findViewById(R.id.reactions_layout_menu));
        }
        if (this.effectsMenuViewContract == null || !this.capabilitiesManager.isWorldsEnabled()) {
            return;
        }
        ((EffectsBottomSheetDialogFragmentView) this.effectsMenuViewContract).initialize(this.mCallButtons);
        this.effectsMenuViewContract.showButton();
    }

    private void initializeSpeakerIcon() {
        if (!Utils.isOreoAndAbove() || !TelecomUtils.isAudioPickerEnabled()) {
            return;
        }
        this.popupMenu = new PopupMenu(this.mContext, this.mSpeakerIcon);
        this.audioPickerPopUp = new AudioPickerPopUp(this.telecomCallAudioManager, this.popupMenu);
        this.mSpeakerIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.-$$Lambda$ActiveVideoCallView$gLd0p0MTMJUMeScdJyQ2VRcZj4M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveVideoCallView.this.lambda$initializeSpeakerIcon$2$ActiveVideoCallView(view);
            }
        });
        Connection currentConnection = this.telecomBridge.getCurrentConnection();
        if (currentConnection != null) {
            if (currentConnection.getCallAudioState() == null) {
                return;
            }
            this.telecomCallAudioManager.setDefaultAudioRoute(true);
            setSpeakerIconState(currentConnection.getCallAudioState());
            return;
        }
        LOG.e("Telecom connection object for the current call is null");
    }

    private void initiateButtonsAnimation() {
        if (!AccessibilityUtils.isTalkBackEnabled(this.mContext)) {
            this.mButtonHandler.postDelayed(this.showNoButtonsRunnable, 5000L);
        }
    }

    private void setRemoteViewScalingType() {
        WebRTCViewRenderer.ScalingType scalingType;
        if (this.mLocalOrientation == this.mRemoteOrientation) {
            scalingType = WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FILL;
        } else {
            scalingType = WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FIT;
        }
        if (this.mScalingType != scalingType) {
            this.mScalingType = scalingType;
            this.mRemoteViewManager.setScalingType(scalingType);
        }
    }

    @TargetApi(26)
    private void setSpeakerIconState(@NonNull CallAudioState callAudioState) {
        if (this.mSpeakerIcon == null) {
            LOG.e("Speaker Icon is not available immediately after rotation");
        } else if (!TelecomUtils.isAudioPickerEnabled()) {
        } else {
            int route = callAudioState.getRoute();
            if (route == 2) {
                LOG.i("Updating audio icon to reflect bluetooth");
                this.mSpeakerIcon.setImageResource(R.drawable.ic_volume_bluetooth);
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
                this.mIsSpeakerOn = false;
            } else if (route != 8) {
                LOG.i("Updating audio icon to reflect earpiece");
                if (this.capabilitiesManager.isThemedUIEnabled()) {
                    this.mSpeakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
                } else {
                    this.mSpeakerIcon.setImageResource(R.drawable.selector_toggle_speaker_source);
                }
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_default));
                this.mIsSpeakerOn = false;
            } else {
                LOG.i("Updating audio icon to reflect speaker");
                if (this.capabilitiesManager.isThemedUIEnabled()) {
                    this.mSpeakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
                } else {
                    this.mSpeakerIcon.setImageResource(R.drawable.selector_toggle_speaker_source);
                }
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
                this.mIsSpeakerOn = true;
            }
        }
    }

    private void setUpToggleCamera() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mToggleCamera.getLayoutParams();
        layoutParams.addRule(13);
        this.mToggleCamera.setLayoutParams(layoutParams);
        this.mToggleCamera.setVisibility(0);
        this.mSelfViewManager.setToggleCamera(this.mToggleCamera, layoutParams);
    }

    private void setupLayout(int i) {
        boolean isAudioPickerEnabled = TelecomUtils.isAudioPickerEnabled();
        if (this.mIsMosaicThemeEnabled) {
            if (DeviceInfo.isPhone(getContext())) {
                if (isAudioPickerEnabled) {
                    LayoutInflater.from(this.mContext).inflate(R.layout.mosaic_active_video_call_view_audiopicker, this);
                } else {
                    LayoutInflater.from(this.mContext).inflate(R.layout.mosaic_active_video_call_view, this);
                }
            } else if (isAudioPickerEnabled) {
                LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view_tablet_audiopicker, this);
            } else {
                LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view_tablet, this);
            }
        } else if (this.mIsThemedUIEnabled) {
            if (DeviceInfo.isPhone(getContext())) {
                if (isAudioPickerEnabled) {
                    LayoutInflater.from(this.mContext).inflate(R.layout.fiesta_active_video_call_view_audiopicker, this);
                } else {
                    LayoutInflater.from(this.mContext).inflate(R.layout.fiesta_active_video_call_view, this);
                }
            } else if (isAudioPickerEnabled) {
                LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view_tablet_audiopicker, this);
            } else {
                LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view_tablet, this);
            }
        } else if (isAudioPickerEnabled) {
            LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view_audiopicker, this);
        } else {
            LayoutInflater.from(this.mContext).inflate(R.layout.active_video_call_view, this);
        }
        this.mSelfViewManager.minimizeSelfView(i);
    }

    private void setupRTT() {
        this.realTimeTextUnavailableToast = new RealTimeTextUnavailableToast(getContext());
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.realTimeTextPresenter = new RealTimeTextPresenter(this, this.sipClientState);
            this.realTimeTextView = new RealTimeTextView(this.realTimeTextPresenter, this.sipClientState.getRemoteParticipantName(), this, this.realTimeTextEnablementAuthority);
        }
    }

    private void setupRTTLayouts() {
        if (this.capabilitiesManager.isRealTimeTextEnabled()) {
            if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
                this.realTimeTextView.bind(this.mCallControls);
                this.realTimeTextPresenter.registerForRTTChanges();
                return;
            }
            new RealTimeTextUnavailableAsyncTask(this.capabilitiesManager, this.sipClientState, this.realTimeTextEnablementAuthority, getContext(), this.realTimeTextUnavailableToast, this.commsIdentityManager.getCommsId("ActiveVideoCallView", false)).execute(new Object[0]);
        }
    }

    private void setupStoryTime() {
        RelativeLayout relativeLayout = (RelativeLayout) this.mCallButtons.findViewById(R.id.storytime_button);
        if (relativeLayout != null) {
            LOG.d("Setting up StoryTime Button.");
            StoryTimeFloatingActionButton storyTimeFloatingActionButton = new StoryTimeFloatingActionButton(relativeLayout);
            boolean isStoryTimeSupported = this.capabilitiesManager.isStoryTimeSupported();
            if (this.sipClientState.getCurrentActiveCall() != null) {
                this.storyTimeButtonPresenterContract = new StoryTimeButtonPresenter(storyTimeFloatingActionButton, this.sipClientState.getCurrentActiveCall(), isStoryTimeSupported);
                storyTimeFloatingActionButton.bindPresenterCallback(this.storyTimeButtonPresenterContract);
                this.storyTimeButtonPresenterContract.setChangeOrientationListener(this.changeOrientationListener);
                return;
            }
            LOG.i("Current call object is null. Skipping creation of StoryTime presenter");
            return;
        }
        LOG.d("No storytime button found!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAllCallControls() {
        LOG.i(" Showing call buttons in active call view ");
        AnimUtils.fadingAnimation(this.mContext, this.mCallButtons, 300, true);
        this.mCallButtons.setVisibility(0);
        this.mToggleCamera.setVisibility(0);
        this.mCallDuration.setVisibility(0);
        this.mSelfViewManager.showScrim();
        hideReactionsButton();
        initiateButtonsAnimation();
    }

    private void showReactionsButton() {
        ReactionsMenuPresenter reactionsMenuPresenter;
        if (this.reactionsMenuViewContract == null || !this.capabilitiesManager.isReactionsEnabled() || (reactionsMenuPresenter = this.reactionsMenuPresenter) == null || !reactionsMenuPresenter.shouldShowMenuButton()) {
            return;
        }
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract != null && effectsMenuPresenterContract.isEffectsMenuOpen()) {
            return;
        }
        this.reactionsMenuViewContract.showButton();
    }

    private void unbindRTTIfNecessary() {
        RealTimeTextView realTimeTextView;
        if (!this.capabilitiesManager.isRealTimeTextEnabled() || !this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall() || (realTimeTextView = this.realTimeTextView) == null) {
            return;
        }
        realTimeTextView.unbind();
    }

    private void updateLocalUI(boolean z) {
        GeneratedOutlineSupport.outline5("Updating local UI with video: ", z, LOG);
        this.mVideoToggleButton.setSelected(!z);
        hidePIPIfNecessary();
        if (!this.sipClientState.isEnhancedProcessedCall()) {
            this.mSelfViewManager.setVideoViewBackground(this.mContext, z, true);
        }
        if (z) {
            this.mVideoToggleButton.setContentDescription(Utils.getStringFromResource(R.string.video_toggled_on_announcement));
        } else {
            int i = this.mRotation;
            if (i != 1 && i != 3) {
                this.mLocalOrientation = 0;
            } else {
                this.mLocalOrientation = 1;
            }
            setRemoteViewScalingType();
            this.mVideoToggleButton.setContentDescription(Utils.getStringFromResource(R.string.video_toggled_off_announcement));
        }
        if (this.mIsThemedUIEnabled) {
            if (z) {
                this.mVideoToggleButton.setColorFilter(ContextCompat.getColor(this.mContext, R.color.fiesta_btn_on));
            } else {
                this.mVideoToggleButton.setColorFilter(ContextCompat.getColor(this.mContext, R.color.fiesta_btn_off));
            }
        }
    }

    private void updateRemoteUI(boolean z) {
        GeneratedOutlineSupport.outline5(" Updating remote UI with video: ", z, LOG);
        this.mRemoteViewManager.setVideoViewBackground(this.mContext, z, false);
        if (z) {
            this.mContactName.setVisibility(8);
            this.mCallStatus.setVisibility(8);
            return;
        }
        this.mContactName.setVisibility(0);
        this.mCallStatus.setText(R.string.video_is_off);
        this.mCallStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.call.active.ActiveCallView
    public void bind(String str) {
        super.bind(str);
        init();
        this.activeCall = this.sipClientState.getCurrentActiveCall();
        MetricsHelper.recordCallConnectedMetrics(this.activeCall);
        Call call = this.activeCall;
        if (call != null) {
            if (call.getLocalVideoViewDimensions() != null) {
                Call.VideoViewDimensions localVideoViewDimensions = this.activeCall.getLocalVideoViewDimensions();
                this.mLocalOrientation = getOrientation(localVideoViewDimensions.getRotation(), localVideoViewDimensions.getVideoWidth(), localVideoViewDimensions.getVideoHeight());
            }
            if (this.activeCall.getRemoteVideoViewDimensions() != null) {
                Call.VideoViewDimensions remoteVideoViewDimensions = this.activeCall.getRemoteVideoViewDimensions();
                this.mRemoteOrientation = getOrientation(remoteVideoViewDimensions.getRotation(), remoteVideoViewDimensions.getVideoWidth(), remoteVideoViewDimensions.getVideoHeight());
            }
            setRemoteViewScalingType();
        }
        updateLocalUI(this.mCurrentCall.getMediaStatus().isLocalVideoEnabled());
        updateRemoteUI(this.mCurrentCall.getMediaStatus().isRemoteVideoEnabled());
        this.mCurrentCall.registerMediaListener(this);
        this.mSelfViewManager.showScrim();
        setUpToggleCamera();
        initiateButtonsAnimation();
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveVideoCallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ActiveVideoCallView.this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
                    ActiveVideoCallView.this.realTimeTextView.handleScreenTap();
                    ActiveVideoCallView.this.showAllCallControls();
                } else if (ActiveVideoCallView.this.mCallButtons.getVisibility() == 0) {
                    ActiveVideoCallView.this.hideAllCallControls();
                } else {
                    ActiveVideoCallView.this.showAllCallControls();
                }
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.deecomms.call.active.-$$Lambda$ActiveVideoCallView$R_v0xln5T-QjvoYmQ2VoRlQ5mqk
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ActiveVideoCallView.this.lambda$bind$0$ActiveVideoCallView(view, motionEvent);
            }
        });
        setContentDescription(Utils.getStringFromResource(R.string.video_call_accessibility_label));
        this.mVideoToggleButton.setOnClickListener(this.mVideoToggleClickListener);
        hidePIPIfNecessary();
    }

    public int getButtonVisibility() {
        return this.mCallButtons.getVisibility();
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void hideCallControls() {
        this.mCallButtons.setVisibility(8);
        showReactionsButton();
    }

    public void hidePIPIfNecessary() {
        if (this.capabilitiesManager.isMPUDynamicPipEnabled() || !this.sipClientState.isEnhancedProcessedCall()) {
            return;
        }
        this.mSelfViewManager.removePIP();
    }

    public /* synthetic */ boolean lambda$bind$0$ActiveVideoCallView(View view, MotionEvent motionEvent) {
        StoryTimeButtonPresenter storyTimeButtonPresenter;
        if (this.mCallButtons.getVisibility() == 0 || (storyTimeButtonPresenter = this.storyTimeButtonPresenterContract) == null || !storyTimeButtonPresenter.isStoryTimeEnabled()) {
            return false;
        }
        return this.storyTimeButtonPresenterContract.processStoryTimeTouchEvents(motionEvent);
    }

    public /* synthetic */ void lambda$init$1$ActiveVideoCallView() {
        if (isAttachedToWindow()) {
            LinearLayout linearLayout = this.mCallButtons;
            if (linearLayout != null) {
                AnimUtils.fadingAnimation(this.mContext, linearLayout, 300, false);
                this.mCallButtons.setVisibility(8);
                this.mToggleCamera.setVisibility(4);
                this.mCallDuration.setVisibility(4);
                PopupMenu popupMenu = this.popupMenu;
                if (popupMenu != null) {
                    popupMenu.dismiss();
                }
                this.mSelfViewManager.hideScrim();
                showReactionsButton();
                return;
            }
            LOG.e(" Required call button layout not found. ");
            return;
        }
        LOG.w(" Could not update call buttons; view detached ");
    }

    public /* synthetic */ void lambda$initializeSpeakerIcon$2$ActiveVideoCallView(View view) {
        if (this.audioPickerPopUp != null && this.telecomCallAudioManager.getSupportedAudioRoutes().size() > 2) {
            this.audioPickerPopUp.showPicker();
        } else {
            handleNonTelecomSpeakerButtonClickEvent();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        LOG.i(" Active video call onAttachedToWindow ");
        super.onAttachedToWindow();
        this.callTimerManager.addListener(this);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_ACTIVE_SHOWN);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchDone(boolean z) {
        if (z) {
            this.mSelfViewManager.setMirror(true);
            announceForAccessibility(Utils.getStringFromResource(R.string.pip_toggled_front_announcement));
            this.mSelfViewManager.setContentDescription(Utils.getStringFromResource(R.string.pip_switch_to_rear_camera));
            return;
        }
        this.mSelfViewManager.setMirror(false);
        announceForAccessibility(Utils.getStringFromResource(R.string.pip_toggled_rear_announcement));
        this.mSelfViewManager.setContentDescription(Utils.getStringFromResource(R.string.pip_switch_to_front_camera));
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchError(String str) {
        GeneratedOutlineSupport.outline3("onCameraSwitchError", str, LOG);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LOG.i(" Active video call onDetachedFromWindow ");
        super.onDetachedFromWindow();
        this.mCurrentCall.unregisterMediaListener(this);
        MetricsHelper.stopCallDurationTimer(this.activeCall);
        this.callTimerManager.removeListener(this);
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.realTimeTextPresenter.unRegisterForRTTChanges();
        }
        StoryTimeButtonPresenter storyTimeButtonPresenter = this.storyTimeButtonPresenterContract;
        if (storyTimeButtonPresenter != null) {
            storyTimeButtonPresenter.releaseStoryTimeResources();
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onDtmfTonesSent(Call call, String str, int i, int i2) {
        LOG.d(String.format("Call (%s) sent dtmf tones (%s)", call.getCallId(), str));
    }

    @Override // com.amazon.deecomms.calling.controller.CallTimerManager.NotificationUpdateListener
    public void onDurationUpdated(String str) {
        this.mCallDuration.setText(str);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameReceived(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameRendered(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onLocalFrameResolutionChanged(int i, int i2, int i3) {
        LOG.i(String.format(Locale.US, "LocalFrameResolutionChanged - videoWidth = %d, videoHeight = %d, rotation = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.mLocalOrientation = getOrientation(i3, i, i2);
        setRemoteViewScalingType();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatsUpdated(Call call) {
        if (this.callHistoryHelper.setCallConnectionType(call)) {
            MetricsHelper.recordCallConnectionTypeMetric(call);
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatusUpdated(Call call, Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(" onMediaStateChange localVideoEnabled ");
        outline1.append(mediaStatus.isLocalVideoEnabled());
        outline1.append(" localAudioEnabled ");
        outline1.append(mediaStatus.isLocalAudioEnabled());
        outline1.append(" remoteVideoEnabled ");
        outline1.append(mediaStatus.isRemoteVideoEnabled());
        outline1.append(" remoteAudioEnabled ");
        outline1.append(mediaStatus.isRemoteAudioEnabled());
        outline1.append(" updateTrigger ");
        outline1.append(mediaStateChangeTrigger);
        outline1.append(" callSide ");
        outline1.append(side);
        commsLogger.i(outline1.toString());
        if (call.getCallId().equals(this.sipClientState.getCallId())) {
            if (side == Call.Side.Local) {
                updateLocalUI(mediaStatus.isLocalVideoEnabled());
                setMicIconState(!mediaStatus.isLocalAudioEnabled());
                return;
            }
            updateRemoteUI(mediaStatus.isRemoteVideoEnabled());
            return;
        }
        LOG.e(" callId doesn't match with current active call ");
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onRemoteFrameResolutionChanged(int i, int i2, int i3) {
        LOG.i(String.format(Locale.US, "RemoteFrameResolutionChanged - videoWidth = %d, videoHeight = %d, rotation = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.mRemoteOrientation = getOrientation(i3, i, i2);
        setRemoteViewScalingType();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onVideoEffectChanged(Call call) {
    }

    @Override // com.amazon.deecomms.call.active.ActiveCallView
    public void setChangeOrientationListener(ChangeOrientationListener changeOrientationListener) {
        this.changeOrientationListener = changeOrientationListener;
        StoryTimeButtonPresenter storyTimeButtonPresenter = this.storyTimeButtonPresenterContract;
        if (storyTimeButtonPresenter != null) {
            storyTimeButtonPresenter.setChangeOrientationListener(changeOrientationListener);
        }
    }

    public void setEffectsMenuPresenter(EffectsMenuPresenterContract effectsMenuPresenterContract) {
        this.effectsMenuPresenter = effectsMenuPresenterContract;
        this.effectsMenuViewContract = ((EffectsMenuPresenter) effectsMenuPresenterContract).getEffectsMenuView();
    }

    public void setReactionsMenuPresenter(ReactionsMenuPresenter reactionsMenuPresenter) {
        this.reactionsMenuPresenter = reactionsMenuPresenter;
        this.reactionsMenuViewContract = reactionsMenuPresenter.getReactionsMenuView();
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void showIncomingMessage(String str) {
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.realTimeTextView.showIncomingMessage(str);
        }
    }

    @Override // java.util.Observer
    @TargetApi(26)
    public void update(@NonNull Observable observable, @NonNull Object obj) {
        if (observable instanceof TelecomCallAudioRouteObservable) {
            if (!TelecomUtils.isAudioPickerEnabled()) {
                return;
            }
            setSpeakerIconState((CallAudioState) obj);
        } else if (!(observable instanceof EnhancedProcessingStateObservable)) {
        } else {
            hidePIPIfNecessary();
        }
    }

    public void updateCallLayout(LinearLayout linearLayout, LinearLayout linearLayout2, View view, LinearLayout linearLayout3, int i) {
        EffectsMenuPresenterContract effectsMenuPresenterContract;
        ReactionsMenuPresenter reactionsMenuPresenter;
        LOG.d("Updating Call Buttons and Screen Header based on orientation");
        this.mCallDuration = (TextView) view.findViewById(R.id.screen_title);
        this.mCallButtons = linearLayout;
        this.mCallControls = linearLayout2;
        this.mMicButton = (ImageButton) linearLayout.findViewById(R.id.muteButton);
        this.mEndCallButton = (ImageButton) linearLayout.findViewById(R.id.callFinishButton);
        this.mVideoToggleButton = (ImageButton) linearLayout.findViewById(R.id.videoToggleButton);
        this.mSpeakerIcon = (ImageView) linearLayout.findViewById(R.id.speakerButton);
        this.mpuToggleCamera = (RelativeLayout) linearLayout.findViewById(R.id.mpu_togglecam);
        if (this.sipClientState.isEnhancedProcessedCall()) {
            this.mpuToggleCamera.findViewById(R.id.cameraToggleButton).setOnClickListener(this.toggleCameraOnClickListener);
            this.mpuToggleCamera.setVisibility(0);
        }
        this.mMicButton.setOnClickListener(this.micButtonClickListener);
        setMicIconState(!this.mCurrentCall.getMediaStatus().isLocalAudioEnabled());
        this.mEndCallButton.setOnClickListener(this.endButtonClickListener);
        this.mVideoToggleButton.setOnClickListener(this.mVideoToggleClickListener);
        updateLocalUI(this.mCurrentCall.getMediaStatus().isLocalVideoEnabled());
        this.mCallButtons.setVisibility(i);
        this.mCallDuration.setVisibility(0);
        this.mToggleCamera.setVisibility(0);
        this.mSelfViewManager.showScrim();
        setUpToggleCamera();
        unbindRTTIfNecessary();
        setupRTTLayouts();
        setupStoryTime();
        if (this.reactionsMenuViewContract != null && this.capabilitiesManager.isReactionsEnabled() && (reactionsMenuPresenter = this.reactionsMenuPresenter) != null && reactionsMenuPresenter.shouldShowMenuButton()) {
            this.reactionsMenuViewContract.initialize(linearLayout3);
            showReactionsButton();
        }
        if (this.effectsMenuViewContract != null && this.capabilitiesManager.isWorldsEnabled() && (effectsMenuPresenterContract = this.effectsMenuPresenter) != null && effectsMenuPresenterContract.shouldShowMenuButton()) {
            ((EffectsBottomSheetDialogFragmentView) this.effectsMenuViewContract).initialize(linearLayout);
            this.effectsMenuViewContract.showButton();
        }
        if (i == 0) {
            initiateButtonsAnimation();
        } else {
            this.mButtonHandler.removeCallbacks(this.showNoButtonsRunnable);
        }
        initializeSpeakerIcon();
    }

    public void updateRotationValue(int i) {
        this.mRotation = i;
    }

    private void setSpeakerIconState(boolean z) {
        if (TelecomUtils.isAudioPickerEnabled()) {
            this.mSpeakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
            if (z) {
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
            } else {
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_default));
            }
        } else {
            this.mSpeakerIcon.setSelected(z);
        }
        if (z) {
            this.mSpeakerIcon.setContentDescription(Utils.getStringFromResource(R.string.mute_speaker_content_description));
            announceForAccessibility(Utils.getStringFromResource(R.string.speaker_now_unmuted_announcement));
        } else {
            this.mSpeakerIcon.setContentDescription(Utils.getStringFromResource(R.string.unmute_speaker_content_description));
            announceForAccessibility(Utils.getStringFromResource(R.string.speaker_now_muted_announcement));
        }
        if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            if (z) {
                this.mSpeakerIcon.setColorFilter(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral40));
            } else {
                this.mSpeakerIcon.setColorFilter(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral10));
            }
        } else if (!this.capabilitiesManager.isThemedUIEnabled()) {
        } else {
            if (z) {
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
            } else {
                this.mSpeakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
            }
        }
    }
}
