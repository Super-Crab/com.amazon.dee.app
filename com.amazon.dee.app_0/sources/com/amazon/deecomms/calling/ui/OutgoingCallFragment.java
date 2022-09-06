package com.amazon.deecomms.calling.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.ui.DialPad;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class OutgoingCallFragment extends Fragment implements DialPad.DialPadShowCallback {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OutgoingCallFragment.class);
    @Inject
    CapabilitiesManager capabilitiesManager;
    private ImageButton dialPadButton;
    private DialPad dialPadView;
    private AudioManager mAudioManager;
    @ColorInt
    private int mDisabledColor;
    @ColorInt
    private int mDisabledColorText;
    @Inject
    DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;
    @ColorInt
    private int mEnabledColor;
    private View mFragmentView;
    @Inject
    BaseCallingPresenter mPresenter;
    private ImageButton mSpeakerButton;
    private View mainOutgoingCallView;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelCall() {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_END_CALL);
        generateClickstream.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, this.sipClientState.getCallId());
        MetricsHelper.recordSingleOccurrence(generateClickstream);
        CallUtils.cancelOutgoingCall(getActivity().getApplicationContext());
    }

    private void initViews() {
        this.mainOutgoingCallView = this.mFragmentView.findViewById(R.id.main_outgoing_call_view);
        this.dialPadButton = (ImageButton) this.mFragmentView.findViewById(R.id.dialPadToggleButton);
        this.dialPadView = (DialPad) this.mFragmentView.findViewById(R.id.dial_pad_view);
        this.dialPadView.setShowCallback(this);
        CallViewUtils.displayNameAndStatus(this, (TextView) this.mFragmentView.findViewById(R.id.callParticipantName), (TextView) this.mFragmentView.findViewById(R.id.callStatus));
        ImageView imageView = (ImageView) this.mFragmentView.findViewById(R.id.progress_dots);
        boolean z = false;
        imageView.setVisibility(0);
        ((AnimationDrawable) imageView.getBackground()).start();
        this.mSpeakerButton = (ImageButton) this.mFragmentView.findViewById(R.id.speakerButton);
        ImageButton imageButton = (ImageButton) this.mFragmentView.findViewById(R.id.muteButton);
        imageButton.setEnabled(false);
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            imageButton.setColorFilter(this.mDisabledColor);
        }
        if (Utils.areAccessoriesConnected()) {
            LOG.i("Disabling speaker as PCC session is available");
            this.mSpeakerButton.setEnabled(false);
            this.mSpeakerButton.setColorFilter(this.mDisabledColor);
        } else {
            if (!DeviceInfo.isPhone(getContext()) || this.mAudioManager.isSpeakerphoneOn()) {
                z = true;
            }
            this.mSpeakerButton.setEnabled(true);
            setSpeakerIconAssets(z);
        }
        if (DeviceInfo.isPhone(getContext())) {
            this.mSpeakerButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.OutgoingCallFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    boolean z2 = !OutgoingCallFragment.this.mAudioManager.isSpeakerphoneOn();
                    OutgoingCallFragment.this.mAudioManager.setSpeakerphoneOn(z2);
                    OutgoingCallFragment.LOG.i("Tried to turn speakerphone " + z2 + "; speakerphone was set to " + OutgoingCallFragment.this.mAudioManager.isSpeakerphoneOn());
                    CallUtils.notifySpeakerStateChange(OutgoingCallFragment.this.getContext());
                    OutgoingCallFragment.this.setSpeakerIconAssets(z2);
                }
            });
        }
        setOnClickListenerForRejectButton();
        if (this.sipClientState.getCallType() == CallType.PSTN) {
            this.dialPadButton.setEnabled(true);
            this.dialPadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$OutgoingCallFragment$V6afytgW54Kr_P58ed7ExkTH_Fg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OutgoingCallFragment.this.lambda$initViews$0$OutgoingCallFragment(view);
                }
            });
            return;
        }
        this.dialPadButton.setVisibility(8);
    }

    private void initiateCall() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getString("COMMS_ID") == null) {
                return;
            }
            String string = arguments.getString("COMMS_ID");
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1(" CommsID for the Initiate Call Task ");
            outline1.append(LOG.sensitive(string));
            commsLogger.i(outline1.toString());
            CallType fromBundle = CallType.fromBundle(arguments, Constants.CALL_TYPE);
            String fromBundle2 = CallProvider.fromBundle(arguments, Constants.CALL_PROVIDER);
            String string2 = arguments.getString(Constants.DEVICE_GRUU, null);
            String string3 = arguments.getString(Constants.CALL_ID);
            Intent intent = new Intent(getActivity().getApplicationContext(), DeviceCallingAndroidService.class);
            intent.setAction(Constants.MAKE_OUTGOING_CALL);
            intent.putExtra(Constants.CALL_TYPE, fromBundle.toString());
            intent.putExtra(Constants.CALL_PROVIDER, fromBundle2);
            intent.putExtra(Constants.CALLEE_COMMS_ID, string);
            intent.putExtra(Constants.CALL_ID, string3);
            intent.putExtra(Constants.DEVICE_GRUU, string2);
            intent.putExtra(Constants.KEY_RECIPIENT_PHONE_NUMBER, (ContactPhoneNumber) arguments.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER));
            intent.putExtra(Constants.CALL_START_TIME, arguments.getLong(Constants.CALL_START_TIME));
            intent.putExtra(Constants.KEY_CALL_INITIATION_SCREEN_NAME, arguments.getString(Constants.KEY_CALL_INITIATION_SCREEN_NAME));
            if (CommsDaggerWrapper.getComponent().getCapabilitiesManager().isAssistSipCallingEnabled()) {
                intent.putExtra(Constants.CALLING_NEW_ARCHITECTURE, arguments.getBoolean(Constants.CALLING_NEW_ARCHITECTURE));
                intent.putExtra(Constants.GROUP_CALL, arguments.getBoolean(Constants.GROUP_CALL));
            }
            getActivity().getApplicationContext().startService(intent);
            return;
        }
        LOG.w("Required parameters to make the outgoing call have not been recieved");
    }

    private void setOnClickListenerForRejectButton() {
        ((ImageButton) this.mFragmentView.findViewById(R.id.callFinishButton)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.OutgoingCallFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OutgoingCallFragment.this.cancelCall();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpeakerIconAssets(boolean z) {
        this.mSpeakerButton.setSelected(z);
        if (z) {
            this.mSpeakerButton.setContentDescription(getString(R.string.mute_speaker_content_description));
            this.mFragmentView.announceForAccessibility(getString(R.string.speaker_now_unmuted_announcement));
        } else {
            this.mSpeakerButton.setContentDescription(getString(R.string.unmute_speaker_content_description));
            this.mFragmentView.announceForAccessibility(getString(R.string.speaker_now_muted_announcement));
        }
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            if (z) {
                this.mSpeakerButton.setColorFilter(this.mDisabledColor);
            } else {
                this.mSpeakerButton.setColorFilter(this.mEnabledColor);
            }
        }
    }

    public boolean isAppInDriveMode() {
        return this.mDriveModeSharedPreferencesUseCase.isInDriveMode();
    }

    public /* synthetic */ void lambda$initViews$0$OutgoingCallFragment(View view) {
        showDialPad(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        this.mDisabledColor = getResources().getColor(R.color.fiesta_btn_off_30);
        this.mEnabledColor = getResources().getColor(R.color.fiesta_btn_on);
        this.mDisabledColorText = getResources().getColor(R.color.drive_mode_disabled_text);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        LOG.d(" onCreateView of Outgoing call Fragment");
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_OUT_SHOWN);
        this.mAudioManager = (AudioManager) getActivity().getSystemService("audio");
        if (getContext() != null && !DeviceInfo.isPhone(getContext())) {
            if (this.capabilitiesManager.isMosaicThemingEnabled()) {
                i = R.layout.mosaic_outgoing_call_view_tablet;
            } else {
                i = R.layout.outgoing_call_view_tablet;
            }
        } else if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            i = R.layout.mosaic_outgoing_call_view;
        } else if (this.capabilitiesManager.isThemedUIEnabled()) {
            i = R.layout.fiesta_outgoing_call_view;
        } else {
            i = R.layout.outgoing_call_view;
        }
        this.mFragmentView = layoutInflater.inflate(i, viewGroup, false);
        initViews();
        if (!CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent()) {
            initiateCall();
        }
        if (isAppInDriveMode()) {
            getActivity().getWindow().addFlags(128);
        }
        return this.mFragmentView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getWindow().clearFlags(128);
    }

    @Override // com.amazon.deecomms.calling.ui.DialPad.DialPadShowCallback
    public void showDialPad(boolean z) {
        if (z) {
            this.mainOutgoingCallView.setVisibility(8);
            this.dialPadView.setVisibility(0);
            return;
        }
        this.dialPadView.setVisibility(8);
        this.mainOutgoingCallView.setVisibility(0);
    }
}
