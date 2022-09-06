package com.amazon.deecomms.calling.ui.ep;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextUnavailableToast;
import com.amazon.deecomms.calling.accessibility.RealTimeTextView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.datachannel.handler.CallingDataChannelEventHandler;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract;
import com.amazon.deecomms.calling.incallexperiences.effects.ui.EffectsBottomSheetDialogFragmentView;
import com.amazon.deecomms.calling.incallexperiences.reactions.ui.ReactionsMenuButtonFragment;
import com.amazon.deecomms.calling.ui.AudioPickerPopUp;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.AccessibilityUtils;
import com.amazon.deecomms.common.util.AnimUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.DeviceInfo;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ActiveEnhancedProcessingVideoCallFragment extends Fragment implements ActiveVideoCallViewContract<ActiveVideoCallPresenterContract>, CallTimerManager.NotificationUpdateListener, RealTimeTextViewContract {
    private static final int FADING_ANIMATION_TIME = 300;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveEnhancedProcessingVideoCallFragment.class);
    private static final int TIME_TO_SHOW_NO_BUTTONS = 5000;
    private AudioPickerPopUp audioPickerPopUp;
    private LinearLayout callButtonLayout;
    private TextView callDuration;
    private TextView callStatus;
    private CallingDataChannelEventHandler callingDataChannelHandler;
    @Inject
    CapabilitiesManager capabilitiesManager;
    private TextView contactName;
    private ImageButton effectMenuIcon;
    private EffectsMenuViewContract effectsMenuButtonFragment;
    private EffectsMenuPresenterContract effectsMenuPresenter;
    private ImageButton endCallIcon;
    private Handler mButtonHandler;
    private ImageButton muteIcon;
    private OrientationEventListener orientationEventListener;
    private PopupMenu popupMenu;
    private ActiveVideoCallPresenterContract presenter;
    private ReactionsMenuButtonFragment reactionsMenuButtonFragment;
    private LinearLayout reactionsMenuLayout;
    private ReactionsMenuPresenter reactionsMenuPresenter;
    private RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextUnavailableToast realTimeTextUnavailableToast;
    private RealTimeTextView realTimeTextView;
    private String remoteParticipantName;
    private View rootView;
    private Runnable showNoCallButtons;
    private ImageView speakerIcon;
    private ImageButton toggleCameraIcon;
    private RelativeLayout toggleCameraLayout;
    private VideoDisplayListener videoDisplayListener;
    private ImageButton videoToggleIcon;
    private WindowManager windowManager;
    final Animation fadingAnimationIn = AnimUtils.getFadingAnimation(300, true);
    final Animation fadingAnimationOut = AnimUtils.getFadingAnimation(300, false);
    private int lastRotation = 0;
    private int currentRotation = 0;
    private int callButtonsLayoutID = R.id.call_button_layout;
    private int callControlsLayoutID = R.id.call_controls_layout;
    private int callHeaderLayoutID = R.id.call_screen_header;
    private int reactionsLayoutMenuID = R.id.reactions_layout_menu;

    private void init() {
        this.mButtonHandler = new Handler();
        this.windowManager = (WindowManager) getContext().getApplicationContext().getSystemService("window");
        ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuButtonFragment;
        if (reactionsMenuButtonFragment != null) {
            reactionsMenuButtonFragment.initialize(this.reactionsMenuLayout);
        }
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract == null || !effectsMenuPresenterContract.isEffectsMenuOpen()) {
            initiateCallControlsButtonAnimation();
        }
        EffectsMenuViewContract effectsMenuViewContract = this.effectsMenuButtonFragment;
        if (effectsMenuViewContract != null) {
            ((EffectsBottomSheetDialogFragmentView) effectsMenuViewContract).initialize(this.callButtonLayout);
        }
        this.videoDisplayListener.minimizeVideo(this.currentRotation, true);
        this.popupMenu = new PopupMenu(getContext(), this.speakerIcon);
        this.audioPickerPopUp = new AudioPickerPopUp(this.presenter.getTelecomCallAudioManager(), this.popupMenu);
        this.realTimeTextEnablementAuthority = this.presenter.getRealTimeTextEnablementAuthority();
        this.realTimeTextUnavailableToast = new RealTimeTextUnavailableToast(getContext());
        this.realTimeTextView = new RealTimeTextView(this.presenter.getRealTimeTextPresenter(), this.presenter.getRemoteParticipantName(), this, this.realTimeTextEnablementAuthority);
        if (this.presenter.isRTTEnabled()) {
            this.realTimeTextView.bind(this.rootView);
        }
    }

    private void initializeUIWidgets() {
        View findViewById = this.rootView.findViewById(this.callHeaderLayoutID);
        this.callButtonLayout = (LinearLayout) this.rootView.findViewById(this.callButtonsLayoutID);
        this.callDuration = (TextView) findViewById.findViewById(R.id.screen_title);
        this.contactName = (TextView) this.rootView.findViewById(R.id.callParticipantName);
        this.callStatus = (TextView) this.rootView.findViewById(R.id.callStatus);
        this.reactionsMenuLayout = (LinearLayout) this.rootView.findViewById(this.reactionsLayoutMenuID);
        this.toggleCameraLayout = (RelativeLayout) this.callButtonLayout.findViewById(R.id.mpu_togglecam);
        this.muteIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.muteButton);
        this.endCallIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.callFinishButton);
        this.toggleCameraIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.cameraToggleButton);
        this.videoToggleIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.videoToggleButton);
        this.speakerIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.speakerButton);
        this.effectMenuIcon = (ImageButton) this.callButtonLayout.findViewById(R.id.effectMenuButton);
    }

    private void initiateCallControlsButtonAnimation() {
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract == null || !effectsMenuPresenterContract.isEffectsMenuOpen()) {
            this.showNoCallButtons = new Runnable() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$93nOSwyuVA4Hgzdzr6-o6Ekstqk
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveEnhancedProcessingVideoCallFragment.this.lambda$initiateCallControlsButtonAnimation$7$ActiveEnhancedProcessingVideoCallFragment();
                }
            };
            if (getContext() == null || AccessibilityUtils.isTalkBackEnabled(getContext())) {
                return;
            }
            this.mButtonHandler.removeCallbacks(this.showNoCallButtons);
            this.mButtonHandler.postDelayed(this.showNoCallButtons, 5000L);
        }
    }

    private void setViewVisibility(Set<View> set, int i) {
        for (View view : set) {
            if (view != null) {
                view.setVisibility(i);
            }
        }
    }

    private void setupLayout(@NonNull ViewGroup viewGroup) {
        boolean isMosaicThemingEnabled = this.capabilitiesManager.isMosaicThemingEnabled();
        boolean isThemedUIEnabled = this.capabilitiesManager.isThemedUIEnabled();
        boolean isAudioPickerEnabled = TelecomUtils.isAudioPickerEnabled();
        if (isMosaicThemingEnabled) {
            if (DeviceInfo.isPhone(getContext())) {
                if (isAudioPickerEnabled) {
                    this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.mosaic_active_video_call_view_audiopicker, viewGroup, false);
                } else {
                    this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.mosaic_active_video_call_view, viewGroup, false);
                }
            } else if (isAudioPickerEnabled) {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view_tablet_audiopicker, viewGroup, false);
            } else {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view_tablet, viewGroup, false);
            }
        } else if (!isThemedUIEnabled) {
            if (isAudioPickerEnabled) {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view_audiopicker, viewGroup, false);
            } else {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view, viewGroup, false);
            }
        } else if (DeviceInfo.isPhone(getContext())) {
            if (isAudioPickerEnabled) {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.fiesta_active_video_call_view_audiopicker, viewGroup, false);
            } else {
                this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.fiesta_active_video_call_view, viewGroup, false);
            }
        } else if (isAudioPickerEnabled) {
            this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view_tablet_audiopicker, viewGroup, false);
        } else {
            this.rootView = LayoutInflater.from(getContext()).inflate(R.layout.active_video_call_view_tablet, viewGroup, false);
        }
    }

    private void setupOrientation() {
        this.orientationEventListener = new OrientationEventListener(getActivity(), 3) { // from class: com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment = ActiveEnhancedProcessingVideoCallFragment.this;
                activeEnhancedProcessingVideoCallFragment.currentRotation = activeEnhancedProcessingVideoCallFragment.windowManager.getDefaultDisplay().getRotation();
                if (ActiveEnhancedProcessingVideoCallFragment.this.lastRotation != ActiveEnhancedProcessingVideoCallFragment.this.currentRotation) {
                    ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment2 = ActiveEnhancedProcessingVideoCallFragment.this;
                    activeEnhancedProcessingVideoCallFragment2.resetLayoutAsPerOrientation(activeEnhancedProcessingVideoCallFragment2.currentRotation);
                    if (ActiveEnhancedProcessingVideoCallFragment.this.effectsMenuButtonFragment != null) {
                        ActiveEnhancedProcessingVideoCallFragment.this.effectsMenuButtonFragment.showButton();
                    }
                    ActiveEnhancedProcessingVideoCallFragment.this.presenter.onRotation(ActiveEnhancedProcessingVideoCallFragment.this.lastRotation, ActiveEnhancedProcessingVideoCallFragment.this.currentRotation);
                    ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment3 = ActiveEnhancedProcessingVideoCallFragment.this;
                    activeEnhancedProcessingVideoCallFragment3.lastRotation = activeEnhancedProcessingVideoCallFragment3.currentRotation;
                }
            }
        };
        if (this.orientationEventListener.canDetectOrientation()) {
            this.currentRotation = this.windowManager.getDefaultDisplay().getRotation();
            this.presenter.onOrientationSetup(this.currentRotation);
            this.orientationEventListener.enable();
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void activateCallControls() {
        this.rootView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$fIfiwX1W9Lu8lAI7iThACNaV9SI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$0$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
        this.muteIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$eM0i8gzy_RULUMX94cbDYdVFzn8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$1$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
        this.videoToggleIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$UK6nZbJLXaolE_i2I3Bw7BhYIaw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$2$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
        this.endCallIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$mOo--yEaiD676sLBHaflfmtfLvc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$3$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
        this.toggleCameraIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$e3wqIwIDpGpm9jm71szsZzTt0NU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$4$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
        ImageView imageView = this.speakerIcon;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$SF6QQW1F-ym37rot8zya-FwkJto
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$5$ActiveEnhancedProcessingVideoCallFragment(view);
                }
            });
        }
        this.effectMenuIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$ActiveEnhancedProcessingVideoCallFragment$hxEWc0frkgtnur1xhXMIx22pH7w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$6$ActiveEnhancedProcessingVideoCallFragment(view);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public int getCurrentRotation() {
        return this.lastRotation;
    }

    public void hideCallButtonView() {
        ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuButtonFragment;
        if (reactionsMenuButtonFragment != null) {
            reactionsMenuButtonFragment.showButton();
        }
        this.mButtonHandler.removeCallbacks(this.showNoCallButtons);
        this.callButtonLayout.setAnimation(this.fadingAnimationOut);
        this.callButtonLayout.setVisibility(8);
        this.callDuration.setVisibility(4);
        this.videoDisplayListener.hideScrim();
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void hideCallControls() {
        hideCallButtonView();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void hideCallStatus() {
        this.callStatus.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void hideRemoteParticipantName() {
        this.contactName.setVisibility(4);
    }

    public /* synthetic */ void lambda$activateCallControls$0$ActiveEnhancedProcessingVideoCallFragment(View view) {
        if (this.callButtonLayout.getVisibility() == 0) {
            LOG.i(" Hiding the call buttons in Active Call View ");
            hideCallButtonView();
            return;
        }
        LOG.i(" Showing call buttons in active call view ");
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.realTimeTextView.handleScreenTap();
        }
        this.rootView.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$2jwCiro_wOP10SaVnc7yWx1s01E
            @Override // java.lang.Runnable
            public final void run() {
                ActiveEnhancedProcessingVideoCallFragment.this.showCallButtonView();
            }
        }, 10L);
    }

    public /* synthetic */ void lambda$activateCallControls$1$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.presenter.toggleMic();
    }

    public /* synthetic */ void lambda$activateCallControls$2$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.presenter.toggleVideo();
    }

    public /* synthetic */ void lambda$activateCallControls$3$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.presenter.endCall();
    }

    public /* synthetic */ void lambda$activateCallControls$4$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.presenter.toggleCamera();
    }

    public /* synthetic */ void lambda$activateCallControls$5$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.presenter.toggleSpeaker();
    }

    public /* synthetic */ void lambda$activateCallControls$6$ActiveEnhancedProcessingVideoCallFragment(View view) {
        this.callButtonLayout.setVisibility(8);
        this.videoDisplayListener.hideScrim();
        ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuButtonFragment;
        if (reactionsMenuButtonFragment != null) {
            reactionsMenuButtonFragment.hideButton();
        }
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract != null) {
            effectsMenuPresenterContract.onMenuButtonTapped();
        }
    }

    public /* synthetic */ void lambda$initiateCallControlsButtonAnimation$7$ActiveEnhancedProcessingVideoCallFragment() {
        if (isAdded()) {
            LinearLayout linearLayout = this.callButtonLayout;
            if (linearLayout != null) {
                linearLayout.setAnimation(this.fadingAnimationOut);
                this.callButtonLayout.setVisibility(8);
                this.callDuration.setVisibility(4);
                this.videoDisplayListener.hideScrim();
                if (this.reactionsMenuButtonFragment == null) {
                    return;
                }
                EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
                if (effectsMenuPresenterContract != null && effectsMenuPresenterContract.isEffectsMenuOpen()) {
                    return;
                }
                this.reactionsMenuButtonFragment.showButton();
                return;
            }
            ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuButtonFragment;
            if (reactionsMenuButtonFragment != null) {
                reactionsMenuButtonFragment.showButton();
            }
            LOG.e(" Required call button layout not found. ");
            return;
        }
        LOG.w(" Could not update call buttons; view detached ");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        setupLayout(viewGroup);
        initializeUIWidgets();
        init();
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LOG.d("On Destroy of Active Call Video Fragment.");
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract != null) {
            effectsMenuPresenterContract.tearDownMenu();
        }
        ReactionsMenuPresenter reactionsMenuPresenter = this.reactionsMenuPresenter;
        if (reactionsMenuPresenter != null) {
            reactionsMenuPresenter.tearDownMenu();
        }
        CallingDataChannelEventHandler callingDataChannelEventHandler = this.callingDataChannelHandler;
        if (callingDataChannelEventHandler != null) {
            callingDataChannelEventHandler.shutdown();
        }
        this.realTimeTextView.unbind();
        this.presenter.onViewDestroyed();
        this.orientationEventListener.disable();
    }

    @Override // com.amazon.deecomms.calling.controller.CallTimerManager.NotificationUpdateListener
    public void onDurationUpdated(String str) {
        this.callDuration.setText(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LOG.d("on pause of Active Call Video Fragment.");
        this.presenter.onViewBackgrounded();
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LOG.d("on resume of Active Call Video Fragment.");
        this.presenter.onViewForegrounded();
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.enable();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.presenter.onViewCreated();
        setupOrientation();
    }

    public void resetLayoutAsPerOrientation(int i) {
        final LinearLayout linearLayout = (LinearLayout) this.rootView.findViewById(R.id.call_button_layout_left);
        final LinearLayout linearLayout2 = (LinearLayout) this.rootView.findViewById(R.id.call_controls_layout_left);
        final LinearLayout linearLayout3 = (LinearLayout) this.rootView.findViewById(R.id.call_button_layout_right);
        final LinearLayout linearLayout4 = (LinearLayout) this.rootView.findViewById(R.id.call_controls_layout_right);
        final LinearLayout linearLayout5 = (LinearLayout) this.rootView.findViewById(R.id.call_button_layout);
        final LinearLayout linearLayout6 = (LinearLayout) this.rootView.findViewById(R.id.call_controls_layout);
        final View findViewById = this.rootView.findViewById(R.id.call_screen_header_left);
        final View findViewById2 = this.rootView.findViewById(R.id.call_screen_header_right);
        final View findViewById3 = this.rootView.findViewById(R.id.call_screen_header);
        final LinearLayout linearLayout7 = (LinearLayout) this.rootView.findViewById(R.id.reactions_layout_menu_landscape_left);
        final LinearLayout linearLayout8 = (LinearLayout) this.rootView.findViewById(R.id.reactions_layout_menu_landscape_right);
        final LinearLayout linearLayout9 = (LinearLayout) this.rootView.findViewById(R.id.reactions_layout_menu);
        HashSet<View> hashSet = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment.2
            {
                add(linearLayout6);
                add(linearLayout5);
                add(findViewById3);
                add(linearLayout9);
            }
        };
        HashSet<View> hashSet2 = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment.3
            {
                add(linearLayout2);
                add(linearLayout);
                add(findViewById);
                add(linearLayout7);
            }
        };
        HashSet<View> hashSet3 = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment.4
            {
                add(linearLayout4);
                add(linearLayout3);
                add(findViewById2);
                add(linearLayout8);
            }
        };
        if (i != 1) {
            if (i != 3) {
                if (linearLayout6 != null && findViewById3 != null && linearLayout9 != null) {
                    setViewVisibility(hashSet2, 4);
                    setViewVisibility(hashSet3, 4);
                    setViewVisibility(hashSet, 0);
                    this.callButtonsLayoutID = R.id.call_button_layout;
                    this.callControlsLayoutID = R.id.call_controls_layout;
                    this.callHeaderLayoutID = R.id.call_screen_header;
                    this.reactionsLayoutMenuID = R.id.reactions_layout_menu;
                }
            } else if (linearLayout4 != null && findViewById2 != null && linearLayout8 != null) {
                setViewVisibility(hashSet2, 4);
                setViewVisibility(hashSet3, 0);
                setViewVisibility(hashSet, 4);
                this.callButtonsLayoutID = R.id.call_button_layout_right;
                this.callControlsLayoutID = R.id.call_controls_layout_right;
                this.callHeaderLayoutID = R.id.call_screen_header_right;
                this.reactionsLayoutMenuID = R.id.reactions_layout_menu_landscape_right;
            }
        } else if (linearLayout2 != null && findViewById != null && linearLayout7 != null) {
            setViewVisibility(hashSet2, 0);
            setViewVisibility(hashSet3, 4);
            setViewVisibility(hashSet, 4);
            this.callButtonsLayoutID = R.id.call_button_layout_left;
            this.callControlsLayoutID = R.id.call_controls_layout_left;
            this.callHeaderLayoutID = R.id.call_screen_header_left;
            this.reactionsLayoutMenuID = R.id.reactions_layout_menu_landscape_left;
        }
        initializeUIWidgets();
        init();
        activateCallControls();
    }

    public void setCallingDataChannelHandler(CallingDataChannelEventHandler callingDataChannelEventHandler) {
        this.callingDataChannelHandler = callingDataChannelEventHandler;
    }

    public void setEffectsMenuPresenter(EffectsMenuPresenterContract effectsMenuPresenterContract) {
        this.effectsMenuPresenter = effectsMenuPresenterContract;
        if (effectsMenuPresenterContract.shouldShowMenuButton()) {
            this.effectsMenuButtonFragment = ((EffectsMenuPresenter) effectsMenuPresenterContract).getEffectsMenuView();
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void setKeyboardPanMechanism() {
    }

    public void setReactionsMenuPresenter(ReactionsMenuPresenter reactionsMenuPresenter) {
        this.reactionsMenuPresenter = reactionsMenuPresenter;
        if (reactionsMenuPresenter.shouldShowMenuButton()) {
            this.reactionsMenuButtonFragment = reactionsMenuPresenter.getReactionsMenuView();
        }
    }

    @VisibleForTesting
    void setRealTimeTextView(RealTimeTextView realTimeTextView) {
        this.realTimeTextView = realTimeTextView;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void setRotationSupported() {
        getActivity().setRequestedOrientation(-1);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener) {
        this.videoDisplayListener = videoDisplayListener;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showAudioPickerPopup() {
        this.audioPickerPopUp.showPicker();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showBluetoothHeadsetOn() {
        ImageView imageView = this.speakerIcon;
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(R.drawable.ic_volume_bluetooth);
        this.speakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
        this.speakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
        this.speakerIcon.setSelected(true);
    }

    public void showCallButtonView() {
        ReactionsMenuButtonFragment reactionsMenuButtonFragment = this.reactionsMenuButtonFragment;
        if (reactionsMenuButtonFragment != null) {
            reactionsMenuButtonFragment.hideButton();
        }
        EffectsMenuPresenterContract effectsMenuPresenterContract = this.effectsMenuPresenter;
        if (effectsMenuPresenterContract == null || !effectsMenuPresenterContract.isEffectsMenuOpen()) {
            initiateCallControlsButtonAnimation();
        }
        this.callButtonLayout.setAnimation(this.fadingAnimationIn);
        this.callButtonLayout.setVisibility(0);
        this.callDuration.setVisibility(0);
        this.videoDisplayListener.showScrim();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showCallDuration(String str) {
        this.callDuration.setText(str);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showCallStatus(String str) {
        this.callStatus.setText(str);
        this.callStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showEffectsMenuButton() {
        EffectsMenuViewContract effectsMenuViewContract = this.effectsMenuButtonFragment;
        if (effectsMenuViewContract != null) {
            effectsMenuViewContract.showButton();
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showFrontSelfVideoFeed() {
        this.rootView.announceForAccessibility(getString(R.string.pip_toggled_front_announcement));
        this.toggleCameraIcon.setContentDescription(getString(R.string.pip_switch_to_rear_camera));
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void showIncomingMessage(@NonNull String str) {
        this.realTimeTextView.showIncomingMessage(str);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showMicMuted() {
        this.muteIcon.setSelected(true);
        this.muteIcon.setContentDescription(getString(R.string.unmute_mic_content_description));
        this.muteIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
        this.rootView.announceForAccessibility(getString(R.string.mic_now_muted_announcement));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showMicUnMuted() {
        this.muteIcon.setSelected(false);
        this.muteIcon.setContentDescription(getString(R.string.mute_mic_content_description));
        this.muteIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
        this.rootView.announceForAccessibility(getString(R.string.mic_now_unmuted_announcement));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showRealTimeTextIncapable() {
        if (this.realTimeTextEnablementAuthority.shouldShowRTTUnavailablePopup()) {
            this.realTimeTextUnavailableToast.showRTTIncapableToast(LayoutInflater.from(getContext()));
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showRearSelfVideoFeed() {
        this.rootView.announceForAccessibility(getString(R.string.pip_toggled_rear_announcement));
        this.toggleCameraIcon.setContentDescription(getString(R.string.pip_switch_to_front_camera));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showRemoteParticipantNameIfRequired(String str, boolean z) {
        this.contactName.setText(str);
        if (z) {
            this.contactName.setVisibility(0);
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showSpeakerOff() {
        ImageView imageView = this.speakerIcon;
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(R.drawable.fiesta_ic_volume_on);
        this.speakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
        this.speakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_default));
        this.speakerIcon.setSelected(false);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showSpeakerOn() {
        ImageView imageView = this.speakerIcon;
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(R.drawable.fiesta_ic_volume_on);
        this.speakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
        this.speakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
        this.speakerIcon.setSelected(true);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showToggleCamera() {
        this.toggleCameraLayout.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showVideoOff() {
        this.videoToggleIcon.setContentDescription(getString(R.string.video_toggled_off_announcement));
        this.videoToggleIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.fiesta_btn_off));
        this.videoToggleIcon.setSelected(true);
        this.toggleCameraIcon.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.effects_disabled_button));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showVideoOffCallStatus() {
        this.callStatus.setText(R.string.video_is_off);
        this.callStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void showVideoOn() {
        this.videoToggleIcon.setContentDescription(getString(R.string.video_toggled_on_announcement));
        this.videoToggleIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.fiesta_btn_on));
        this.videoToggleIcon.setSelected(false);
        this.toggleCameraIcon.setBackgroundTintList(null);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract
    public void updateRemoteViewBackground(boolean z) {
        this.videoDisplayListener.onSetBackground(z, false, this.callingDataChannelHandler.isLocalPipShowing());
    }

    @Override // com.amazon.deecomms.calling.contracts.BaseViewContract
    public void setPresenter(ActiveVideoCallPresenterContract activeVideoCallPresenterContract) {
        this.presenter = activeVideoCallPresenterContract;
    }
}
