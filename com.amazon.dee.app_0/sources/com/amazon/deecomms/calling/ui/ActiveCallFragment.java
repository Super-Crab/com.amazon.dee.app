package com.amazon.deecomms.calling.ui;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.os.Bundle;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextUnavailableAsyncTask;
import com.amazon.deecomms.calling.accessibility.RealTimeTextUnavailableToast;
import com.amazon.deecomms.calling.accessibility.RealTimeTextView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.DialPad;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.DisplayCutoutCalculator;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.util.BuildVersionProviderImpl;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Observable;
import java.util.Observer;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class ActiveCallFragment extends Fragment implements CallTimerManager.NotificationUpdateListener, MediaListener, DialPad.DialPadShowCallback, Observer, RealTimeTextViewContract {
    private static final int CALL_DOWNGRADE_MESSAGE_DISMISS_TIMEOUT = 5000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveCallFragment.class);
    @Nullable
    private Call activeCall;
    private AudioPickerPopUp audioPickerPopUp;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CallManager callManager;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private ImageButton dialPadButton;
    private DialPad dialPadView;
    private final View.OnClickListener endCallOnClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$OmIvYvzJ1NmtPBmDzex4VBqFpA0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ActiveCallFragment.this.lambda$new$0$ActiveCallFragment(view);
        }
    };
    private View fragmentView;
    private AudioManager mAudioManager;
    private TextView mCallDowngradeMessage;
    private TextView mCallDuration;
    @ColorInt
    private int mDisabledColor;
    @Inject
    DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;
    @ColorInt
    private int mEnabledColor;
    private TextView mErrorStatus;
    private boolean mIsSpeakerOn;
    private ImageView mMuteIcon;
    @Inject
    BaseCallingPresenter mPresenter;
    private ImageView mSpeakerIcon;
    private View mainCallingView;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextPresenter realTimeTextPresenter;
    private RealTimeTextUnavailableToast realTimeTextUnavailableToast;
    private RealTimeTextView realTimeTextView;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomBridge telecomBridge;
    private TelecomCallAudioManager telecomCallAudioManager;
    @Inject
    TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;

    private void handleLegacySpeakerButtonClickEvent() {
        this.mIsSpeakerOn = !this.mIsSpeakerOn;
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("speakerButton onClick:enabled="), this.mIsSpeakerOn, LOG);
        this.mAudioManager.setSpeakerphoneOn(this.mIsSpeakerOn);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone ");
        outline1.append(this.mIsSpeakerOn);
        outline1.append("; speakerphone was set to ");
        outline1.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger.i(outline1.toString());
        CallUtils.notifySpeakerStateChange(getContext());
        setSpeakerIconState(this.mIsSpeakerOn);
    }

    private void init() {
        Connection currentConnection;
        Call call = this.activeCall;
        if (call != null) {
            MetricsHelper.startCallDurationTimer(call);
        }
        final TextView textView = (TextView) this.fragmentView.findViewById(R.id.callParticipantName);
        this.mCallDuration = (TextView) this.fragmentView.findViewById(R.id.screen_title);
        if (!this.capabilitiesManager.isThemedUIEnabled()) {
            this.mCallDuration.setTextColor(ContextCompat.getColor(getContext(), R.color.call_screen_timer));
        }
        setupLayouts();
        this.dialPadView.setShowCallback(this);
        CallViewUtils.displayNameAndStatus(this, textView, this.mCallDowngradeMessage);
        ImageButton imageButton = (ImageButton) this.fragmentView.findViewById(R.id.callFinishButton);
        imageButton.setOnClickListener(this.endCallOnClickListener);
        if (Utils.getBooleanPreferenceFromSharedPrefs(getContext(), TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            this.telecomCallAudioManager = new TelecomCallAudioManager(this.telecomBridge, this.mAudioManager);
            this.audioPickerPopUp = new AudioPickerPopUp(this.telecomCallAudioManager, new PopupMenu(getContext(), this.mSpeakerIcon));
        }
        Call call2 = this.activeCall;
        if (call2 != null && Call.Side.Local.equals(call2.getOrigin()) && this.callManager.isCallDowngraded()) {
            replaceDialPadButton(imageButton);
            final int maxLines = this.mCallDowngradeMessage.getMaxLines();
            this.mCallDowngradeMessage.setMaxLines(Integer.MAX_VALUE);
            this.mCallDowngradeMessage.setText(getResources().getString(R.string.call_downgrade_message));
            this.mCallDowngradeMessage.setTextColor(ContextCompat.getColor(getContext(), R.color.call_screen_participant_color));
            this.mCallDowngradeMessage.setVisibility(0);
            this.mCallDowngradeMessage.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$uyi2B5xHXAQgSZAOSWDLyokE_Qc
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveCallFragment.this.lambda$init$1$ActiveCallFragment(maxLines, textView);
                }
            }, 5000L);
            if (!this.mAudioManager.isBluetoothScoOn() && !this.mAudioManager.isBluetoothA2dpOn() && !this.mAudioManager.isWiredHeadsetOn()) {
                this.mAudioManager.setSpeakerphoneOn(true);
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
                outline1.append(this.mAudioManager.isSpeakerphoneOn());
                commsLogger.i(outline1.toString());
            }
        }
        Call call3 = this.activeCall;
        setMicIconState(call3 != null && !call3.getMediaStatus().isLocalAudioEnabled());
        this.mMuteIcon.setEnabled(true);
        if (!Utils.isPermissionGranted(getActivity(), "android.permission.RECORD_AUDIO")) {
            this.mErrorStatus.setVisibility(0);
        }
        this.mMuteIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$-Ee2WP22Al0kQ085UVPw2Qicv3s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveCallFragment.this.lambda$init$2$ActiveCallFragment(view);
            }
        });
        this.mIsSpeakerOn = Utils.isFireOS() || !DeviceInfo.isPhone(getContext()) || this.mAudioManager.isSpeakerphoneOn();
        if (Utils.areAccessoriesConnected() && !Utils.getBooleanPreferenceFromSharedPrefs(getContext(), TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false)) {
            LOG.i("Disabling speaker as PCC session is available");
            this.mSpeakerIcon.setEnabled(false);
            this.mSpeakerIcon.setColorFilter(this.mDisabledColor);
        } else {
            this.mSpeakerIcon.setEnabled(true);
            this.mSpeakerIcon.setColorFilter(this.mEnabledColor);
        }
        setSpeakerIconState(this.mIsSpeakerOn);
        this.mSpeakerIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$UW2YWZxMCy9sxC4fPP7Yn4pcaZY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveCallFragment.this.lambda$init$3$ActiveCallFragment(view);
            }
        });
        if (this.sipClientState.getCallType() == CallType.PSTN) {
            this.dialPadButton.setEnabled(true);
            this.dialPadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$Ji8QdmO22AS1XZVxpT__OKgb0eI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveCallFragment.this.lambda$init$4$ActiveCallFragment(view);
                }
            });
        } else {
            this.dialPadButton.setVisibility(8);
        }
        Call call4 = this.activeCall;
        if (call4 != null) {
            call4.registerMediaListener(this);
        }
        this.telecomCallAudioRouteObservable.addObserver(this);
        if (Utils.isOreoAndAbove() && TelecomUtils.isAudioPickerEnabled() && (currentConnection = this.telecomBridge.getCurrentConnection()) != null && currentConnection.getCallAudioState() != null) {
            this.telecomCallAudioManager.setDefaultAudioRoute(false);
            setSpeakerIconState(currentConnection.getCallAudioState());
        }
        setupRTT();
    }

    private void replaceDialPadButton(ImageButton imageButton) {
        this.dialPadButton.setBackgroundResource(R.drawable.selector_call_end);
        this.dialPadButton.setImageResource(R.drawable.ic_call_end_default_dark_48dp);
        this.dialPadButton.setOnClickListener(this.endCallOnClickListener);
        imageButton.setVisibility(8);
        this.dialPadButton = imageButton;
    }

    private void setMicIconState(boolean z) {
        this.mMuteIcon.setSelected(z);
        if (z) {
            this.mMuteIcon.setContentDescription(getString(R.string.unmute_mic_content_description));
            this.fragmentView.announceForAccessibility(getString(R.string.mic_now_muted_announcement));
        } else {
            this.mMuteIcon.setContentDescription(getString(R.string.mute_mic_content_description));
            this.fragmentView.announceForAccessibility(getString(R.string.mic_now_unmuted_announcement));
        }
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            if (z) {
                this.mMuteIcon.setColorFilter(this.mDisabledColor);
            } else {
                this.mMuteIcon.setColorFilter(this.mEnabledColor);
            }
        }
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
            this.mSpeakerIcon.setContentDescription(getString(R.string.mute_speaker_content_description));
            this.fragmentView.announceForAccessibility(getString(R.string.speaker_now_unmuted_announcement));
        } else {
            this.mSpeakerIcon.setContentDescription(getString(R.string.unmute_speaker_content_description));
            this.fragmentView.announceForAccessibility(getString(R.string.speaker_now_muted_announcement));
        }
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            if (z) {
                this.mSpeakerIcon.setColorFilter(this.mDisabledColor);
            } else {
                this.mSpeakerIcon.setColorFilter(this.mEnabledColor);
            }
        }
    }

    private void setupLayouts() {
        this.mMuteIcon = (ImageButton) this.fragmentView.findViewById(R.id.muteButton);
        this.mSpeakerIcon = (ImageButton) this.fragmentView.findViewById(R.id.speakerButton);
        this.mErrorStatus = (TextView) this.fragmentView.findViewById(R.id.errorStatus);
        this.mCallDowngradeMessage = (TextView) this.fragmentView.findViewById(R.id.callStatus);
        this.mCallDowngradeMessage.setVisibility(8);
        this.mainCallingView = this.fragmentView.findViewById(R.id.main_calling_view);
        this.dialPadButton = (ImageButton) this.fragmentView.findViewById(R.id.dialPadToggleButton);
        this.dialPadView = (DialPad) this.fragmentView.findViewById(R.id.dial_pad_view);
    }

    private void setupRTT() {
        if (this.capabilitiesManager.isRealTimeTextEnabled()) {
            this.realTimeTextUnavailableToast = new RealTimeTextUnavailableToast(getContext());
            if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
                this.realTimeTextPresenter = new RealTimeTextPresenter(this, this.sipClientState);
                this.realTimeTextView = new RealTimeTextView(this.realTimeTextPresenter, this.sipClientState.getRemoteParticipantName(), this, this.realTimeTextEnablementAuthority);
                this.mainCallingView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$ActiveCallFragment$bQDSNE4Tt6BiHEa2YUq1uk93YjM
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActiveCallFragment.this.lambda$setupRTT$5$ActiveCallFragment(view);
                    }
                });
                if (getActivity() != null) {
                    getActivity().getWindow().setSoftInputMode(32);
                }
            }
            setupRTTLayouts();
        }
    }

    private void setupRTTLayouts() {
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.realTimeTextView.bind(this.mainCallingView);
            this.realTimeTextPresenter.registerForRTTChanges();
            return;
        }
        new RealTimeTextUnavailableAsyncTask(this.capabilitiesManager, this.sipClientState, this.realTimeTextEnablementAuthority, getContext(), this.realTimeTextUnavailableToast, this.commsIdentityManager.getCommsId("ActiveCallFragment", false)).execute(new Object[0]);
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void hideCallControls() {
    }

    public boolean isAppInDriveMode() {
        return this.mDriveModeSharedPreferencesUseCase.isInDriveMode();
    }

    public /* synthetic */ void lambda$init$1$ActiveCallFragment(int i, TextView textView) {
        this.mCallDowngradeMessage.setVisibility(8);
        this.mCallDowngradeMessage.setMaxLines(i);
        CallViewUtils.displayNameAndStatus(this, textView, this.mCallDowngradeMessage);
    }

    public /* synthetic */ void lambda$init$2$ActiveCallFragment(View view) {
        Call call = this.activeCall;
        boolean z = call != null && !call.getMediaStatus().isLocalAudioEnabled();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("muteButton onClick:enabled=");
        boolean z2 = !z;
        outline1.append(z2);
        commsLogger.i(outline1.toString());
        CommsDaggerWrapper.getComponent().getCommandProcessor().enableAudio(z);
        this.mAudioManager.setMicrophoneMute(z2);
    }

    public /* synthetic */ void lambda$init$3$ActiveCallFragment(View view) {
        if (Utils.getBooleanPreferenceFromSharedPrefs(getContext(), TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false) && this.audioPickerPopUp != null && this.telecomCallAudioManager.getSupportedAudioRoutes().size() > 2) {
            this.audioPickerPopUp.showPicker();
        } else {
            handleLegacySpeakerButtonClickEvent();
        }
    }

    public /* synthetic */ void lambda$init$4$ActiveCallFragment(View view) {
        showDialPad(true);
    }

    public /* synthetic */ void lambda$new$0$ActiveCallFragment(View view) {
        CallUtils.endActiveCall(getActivity().getApplicationContext());
        this.mErrorStatus.setVisibility(8);
    }

    public /* synthetic */ void lambda$setupRTT$5$ActiveCallFragment(View view) {
        this.realTimeTextView.hideKeyboard();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchDone(boolean z) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchError(String str) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        this.mDisabledColor = getResources().getColor(R.color.fiesta_btn_off);
        this.mEnabledColor = getResources().getColor(R.color.fiesta_btn_on);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        LOG.i(" onCreateView of ActiveCallFragment ");
        this.activeCall = this.sipClientState.getCurrentActiveCall();
        Call call = this.activeCall;
        if (call != null) {
            MetricsHelper.recordCallConnectedMetrics(call);
        } else {
            LOG.w("No active call!");
        }
        if (getContext() != null && !DeviceInfo.isPhone(getContext())) {
            if (this.capabilitiesManager.isMosaicThemingEnabled()) {
                i = R.layout.mosaic_active_call_view_tablet;
            } else {
                i = R.layout.active_call_view_tablet;
            }
        } else if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            i = R.layout.mosaic_active_call_view;
        } else if (this.capabilitiesManager.isThemedUIEnabled()) {
            i = R.layout.fiesta_active_call_view;
        } else {
            i = R.layout.active_call_view;
        }
        this.fragmentView = layoutInflater.inflate(i, viewGroup, false);
        new DisplayCutoutCalculator(new BuildVersionProviderImpl()).adjust(this.fragmentView);
        this.mAudioManager = (AudioManager) getActivity().getApplicationContext().getSystemService("audio");
        init();
        if (isAppInDriveMode()) {
            getActivity().getWindow().addFlags(128);
        }
        return this.fragmentView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Call call = this.activeCall;
        if (call != null) {
            call.unregisterMediaListener(this);
        }
        getActivity().getWindow().clearFlags(128);
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
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("onMediaStateChange localAudioEnabled ");
        outline1.append(mediaStatus.isLocalAudioEnabled());
        outline1.append(" callSide ");
        outline1.append(side);
        outline1.append(" updateTrigger ");
        outline1.append(mediaStateChangeTrigger);
        commsLogger.i(outline1.toString());
        if (call.getCallId().equals(this.sipClientState.getCallId())) {
            if (side != Call.Side.Local) {
                return;
            }
            setMicIconState(!mediaStatus.isLocalAudioEnabled());
            return;
        }
        LOG.e(" callId doesn't match with current active call ");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LOG.d(" onPause of ActiveCallFragment ");
        this.callTimerManager.removeListener(this);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onRemoteFrameResolutionChanged(int i, int i2, int i3) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.callTimerManager.addListener(this);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_ACTIVE_SHOWN);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Call call = this.activeCall;
        if (call != null) {
            MetricsHelper.stopCallDurationTimer(call);
        }
        if (!this.capabilitiesManager.isRealTimeTextEnabled() || !this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            return;
        }
        this.realTimeTextPresenter.unRegisterForRTTChanges();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onVideoEffectChanged(Call call) {
    }

    @Override // com.amazon.deecomms.calling.ui.DialPad.DialPadShowCallback
    public void showDialPad(boolean z) {
        if (z) {
            this.mainCallingView.setVisibility(8);
            this.dialPadView.setVisibility(0);
            return;
        }
        this.dialPadView.setVisibility(8);
        this.mainCallingView.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void showIncomingMessage(String str) {
        if (!this.capabilitiesManager.isRealTimeTextEnabled() || !this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            return;
        }
        this.realTimeTextView.showIncomingMessage(str);
    }

    @Override // java.util.Observer
    @TargetApi(26)
    public void update(@NonNull Observable observable, @NonNull Object obj) {
        if (getContext() == null || !TelecomUtils.isAudioPickerEnabled()) {
            return;
        }
        setSpeakerIconState((CallAudioState) obj);
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
                this.mSpeakerIcon.setColorFilter(this.mDisabledColor);
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
                this.mIsSpeakerOn = false;
            } else if (route != 8) {
                LOG.i("Updating audio icon to reflect earpiece");
                if (this.capabilitiesManager.isThemedUIEnabled()) {
                    this.mSpeakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
                } else {
                    this.mSpeakerIcon.setImageResource(R.drawable.selector_toggle_speaker_source);
                }
                this.mSpeakerIcon.setColorFilter(this.mEnabledColor);
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_default));
                this.mIsSpeakerOn = false;
            } else {
                LOG.i("Updating audio icon to reflect speaker");
                if (this.capabilitiesManager.isThemedUIEnabled()) {
                    this.mSpeakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
                } else {
                    this.mSpeakerIcon.setImageResource(R.drawable.selector_toggle_speaker_source);
                }
                this.mSpeakerIcon.setColorFilter(this.mDisabledColor);
                this.mSpeakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
                this.mIsSpeakerOn = true;
            }
        }
    }
}
