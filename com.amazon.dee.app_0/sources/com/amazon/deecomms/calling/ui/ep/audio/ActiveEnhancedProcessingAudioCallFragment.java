package com.amazon.deecomms.calling.ui.ep.audio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract;
import com.amazon.deecomms.calling.ui.AudioPickerPopUp;
import com.amazon.deecomms.calling.ui.DialPad;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.DeviceInfo;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ActiveEnhancedProcessingAudioCallFragment extends Fragment implements ActiveAudioCallViewContract<ActiveAudioCallPresenterContract>, RealTimeTextViewContract, DialPad.DialPadShowCallback {
    private static final int CALL_DOWNGRADE_MESSAGE_DISMISS_TIMEOUT = 5000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveEnhancedProcessingAudioCallFragment.class);
    private ActiveAudioCallPresenterContract activeAudioCallPresenterContract;
    private TextView activeCallParticipantNameView;
    private AudioPickerPopUp audioPickerPopUp;
    private TextView callDowngradeMessage;
    private TextView callDuration;
    @Inject
    CapabilitiesManager capabilitiesManager;
    private ImageButton dialPadButton;
    private DialPad dialPadView;
    private TextView errorStatus;
    private View mainCallingView;
    private ImageView muteIcon;
    private PopupMenu popupMenu;
    private RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextUnavailableToast realTimeTextUnavailableToast;
    private RealTimeTextView realTimeTextView;
    private ImageButton rejectButton;
    private View rootView;
    private ImageView speakerIcon;

    private void init() {
        this.popupMenu = new PopupMenu(getContext(), this.speakerIcon);
        this.audioPickerPopUp = new AudioPickerPopUp(this.activeAudioCallPresenterContract.getTelecomCallAudioManager(), this.popupMenu);
        this.realTimeTextEnablementAuthority = this.activeAudioCallPresenterContract.getRealTimeTextEnablementAuthority();
        this.realTimeTextUnavailableToast = new RealTimeTextUnavailableToast(getContext());
        this.realTimeTextView = new RealTimeTextView(this.activeAudioCallPresenterContract.getRealTimeTextPresenter(), this.activeAudioCallPresenterContract.getRemoteParticipantName(), this, this.realTimeTextEnablementAuthority);
    }

    private void initializeUIWidgets() {
        this.activeCallParticipantNameView = (TextView) this.rootView.findViewById(R.id.callParticipantName);
        this.callDowngradeMessage = (TextView) this.rootView.findViewById(R.id.callStatus);
        this.callDuration = (TextView) this.rootView.findViewById(R.id.screen_title);
        this.dialPadButton = (ImageButton) this.rootView.findViewById(R.id.dialPadToggleButton);
        this.dialPadView = (DialPad) this.rootView.findViewById(R.id.dial_pad_view);
        this.errorStatus = (TextView) this.rootView.findViewById(R.id.errorStatus);
        this.rejectButton = (ImageButton) this.rootView.findViewById(R.id.callFinishButton);
        this.mainCallingView = this.rootView.findViewById(R.id.main_calling_view);
        this.muteIcon = (ImageButton) this.rootView.findViewById(R.id.muteButton);
        this.speakerIcon = (ImageButton) this.rootView.findViewById(R.id.speakerButton);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void activateCallControls() {
        this.rejectButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.audio.-$$Lambda$ActiveEnhancedProcessingAudioCallFragment$zLDnHhdpwz1BS_U8WXy53EN-9Og
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingAudioCallFragment.this.lambda$activateCallControls$1$ActiveEnhancedProcessingAudioCallFragment(view);
            }
        });
        this.muteIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.audio.-$$Lambda$ActiveEnhancedProcessingAudioCallFragment$dspC5UZjRSs8RZJllvEyiVrgKK0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingAudioCallFragment.this.lambda$activateCallControls$2$ActiveEnhancedProcessingAudioCallFragment(view);
            }
        });
        this.speakerIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.audio.-$$Lambda$ActiveEnhancedProcessingAudioCallFragment$aXeyFJbpUxLbK_Cz8ms9bs-hFHM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingAudioCallFragment.this.lambda$activateCallControls$3$ActiveEnhancedProcessingAudioCallFragment(view);
            }
        });
        this.dialPadView.setShowCallback(this);
        this.dialPadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.audio.-$$Lambda$ActiveEnhancedProcessingAudioCallFragment$YokHFvKD96dITWmkBy0HwwUHNwk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveEnhancedProcessingAudioCallFragment.this.lambda$activateCallControls$4$ActiveEnhancedProcessingAudioCallFragment(view);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void hideCallControls() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void hideCallStatus() {
        this.callDowngradeMessage.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void hideDialpad() {
        this.dialPadButton.setEnabled(false);
        this.dialPadButton.setColorFilter(getResources().getColor(R.color.fiesta_btn_off_30));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void hideErrorMessage() {
        this.errorStatus.setVisibility(8);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void hideRemoteParticipantName() {
        this.activeCallParticipantNameView.setVisibility(4);
    }

    public /* synthetic */ void lambda$activateCallControls$1$ActiveEnhancedProcessingAudioCallFragment(View view) {
        this.activeAudioCallPresenterContract.endCall();
    }

    public /* synthetic */ void lambda$activateCallControls$2$ActiveEnhancedProcessingAudioCallFragment(View view) {
        this.activeAudioCallPresenterContract.toggleMic();
    }

    public /* synthetic */ void lambda$activateCallControls$3$ActiveEnhancedProcessingAudioCallFragment(View view) {
        this.activeAudioCallPresenterContract.toggleSpeaker();
    }

    public /* synthetic */ void lambda$activateCallControls$4$ActiveEnhancedProcessingAudioCallFragment(View view) {
        showDialPad(true);
    }

    public /* synthetic */ void lambda$showCallVideoDowngradedUI$0$ActiveEnhancedProcessingAudioCallFragment() {
        this.activeAudioCallPresenterContract.onCallDowngradeMessageExpiry();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        CommsDaggerWrapper.getComponent().inject(this);
        LOG.i(" onCreateView of ActiveCallFragment ");
        if (getContext() != null && !DeviceInfo.isPhone(getContext())) {
            if (this.capabilitiesManager.isMosaicThemingEnabled()) {
                i = R.layout.mosaic_active_call_view_tablet;
            } else {
                i = R.layout.active_call_view_tablet;
            }
        } else if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            i = R.layout.mosaic_active_call_view;
        } else {
            i = R.layout.fiesta_active_call_view;
        }
        this.rootView = layoutInflater.inflate(i, viewGroup, false);
        initializeUIWidgets();
        init();
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.activeAudioCallPresenterContract.onViewDestroyed();
        RealTimeTextView realTimeTextView = this.realTimeTextView;
        if (realTimeTextView != null) {
            realTimeTextView.unbind();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.activeAudioCallPresenterContract.onViewBackgrounded();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_ACTIVE_SHOWN);
        this.activeAudioCallPresenterContract.onViewForegrounded();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.activeAudioCallPresenterContract.onViewCreated();
        if (this.activeAudioCallPresenterContract.isRTTEnabled()) {
            this.realTimeTextView.bind(this.rootView);
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void setKeyboardPanMechanism() {
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(32);
        }
    }

    @VisibleForTesting
    void setRealTimeTextView(RealTimeTextView realTimeTextView) {
        this.realTimeTextView = realTimeTextView;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showAudioPickerPopup() {
        this.audioPickerPopUp.showPicker();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
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

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showCallDuration(String str) {
        this.callDuration.setText(str);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showCallStatus(String str) {
        this.callDowngradeMessage.setText(str);
        this.callDowngradeMessage.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showCallVideoDowngradedUI() {
        this.dialPadButton.setVisibility(8);
        this.callDowngradeMessage.setMaxLines(Integer.MAX_VALUE);
        this.callDowngradeMessage.setText(getResources().getString(R.string.call_downgrade_message));
        this.callDowngradeMessage.setTextColor(ContextCompat.getColor(getContext(), R.color.call_screen_participant_color));
        this.callDowngradeMessage.setVisibility(0);
        this.callDowngradeMessage.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.ui.ep.audio.-$$Lambda$ActiveEnhancedProcessingAudioCallFragment$yReRNM7CUrGsYXBRKy6jGcw09Uo
            @Override // java.lang.Runnable
            public final void run() {
                ActiveEnhancedProcessingAudioCallFragment.this.lambda$showCallVideoDowngradedUI$0$ActiveEnhancedProcessingAudioCallFragment();
            }
        }, 5000L);
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

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showDialpad() {
        this.dialPadButton.setEnabled(true);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showErrorMessage() {
        this.errorStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void showIncomingMessage(@NonNull String str) {
        this.realTimeTextView.showIncomingMessage(str);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showMicMuted() {
        this.muteIcon.setSelected(true);
        this.muteIcon.setContentDescription(getString(R.string.unmute_mic_content_description));
        this.muteIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
        this.rootView.announceForAccessibility(getString(R.string.mic_now_muted_announcement));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showMicUnMuted() {
        this.muteIcon.setSelected(false);
        this.muteIcon.setContentDescription(getString(R.string.mute_mic_content_description));
        this.muteIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
        this.rootView.announceForAccessibility(getString(R.string.mic_now_unmuted_announcement));
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showRealTimeTextIncapable() {
        if (this.realTimeTextEnablementAuthority.shouldShowRTTUnavailablePopup()) {
            this.realTimeTextUnavailableToast.showRTTIncapableToast(LayoutInflater.from(getContext()));
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showRemoteParticipantName(String str) {
        this.activeCallParticipantNameView.setText(str);
        this.activeCallParticipantNameView.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showSpeakerOff() {
        this.speakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
        this.speakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_on));
        this.speakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_default));
        this.speakerIcon.setSelected(false);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract
    public void showSpeakerOn() {
        this.speakerIcon.setImageResource(R.drawable.fiesta_ic_volume_on);
        this.speakerIcon.setColorFilter(getResources().getColor(R.color.fiesta_btn_off));
        this.speakerIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_btn_enabled_selected));
        this.speakerIcon.setSelected(true);
    }

    @Override // com.amazon.deecomms.calling.contracts.BaseViewContract
    public void setPresenter(ActiveAudioCallPresenterContract activeAudioCallPresenterContract) {
        this.activeAudioCallPresenterContract = activeAudioCallPresenterContract;
    }
}
