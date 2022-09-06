package com.amazon.deecomms.calling.ui.ep;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.AnimUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class OutgoingEnhancedProcessingVideoCallFragment extends Fragment implements OutgoingVideoCallViewContract<OutgoingCallPresenterContract> {
    private static final int FADING_ANIMATION_TIME = 300;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OutgoingEnhancedProcessingVideoCallFragment.class);
    private TextView callStatus;
    @Inject
    CapabilitiesManager capabilitiesManager;
    private ImageButton endCallButton;
    private LinearLayout endCallButtonLayout;
    private TextView mCalleeName;
    private OutgoingCallPresenterContract outgoingCallPresenterContract;
    private ImageView progressDots;
    private View rootView;
    private TextView screenTitle;
    private VideoDisplayListener videoDisplayListener;

    private void initializeUIWidgets() {
        this.endCallButtonLayout = (LinearLayout) this.rootView.findViewById(R.id.end_call_button_layout);
        this.endCallButton = (ImageButton) this.rootView.findViewById(R.id.callFinishButton);
        this.mCalleeName = (TextView) this.rootView.findViewById(R.id.callParticipantName);
        this.progressDots = (ImageView) this.rootView.findViewById(R.id.progress_dots);
        this.callStatus = (TextView) this.rootView.findViewById(R.id.callStatus);
        this.screenTitle = (TextView) this.rootView.findViewById(R.id.screen_title);
    }

    private int obtainLayoutToInflate() {
        if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            return R.layout.mosaic_outgoing_video_call_view;
        }
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            return R.layout.fiesta_outgoing_video_call_view;
        }
        return R.layout.outgoing_video_call_view;
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void activateCallControls() {
        this.endCallButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$OutgoingEnhancedProcessingVideoCallFragment$UTarJm2CkflMAqU8o_P9CNff2os
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OutgoingEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$1$OutgoingEnhancedProcessingVideoCallFragment(view);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void hideCallScreenTitleMessage() {
        this.screenTitle.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void hideCallStatus() {
        this.callStatus.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void hideRemoteParticipantName() {
        this.mCalleeName.setVisibility(4);
    }

    public /* synthetic */ void lambda$activateCallControls$1$OutgoingEnhancedProcessingVideoCallFragment(View view) {
        this.outgoingCallPresenterContract.endCall();
    }

    public /* synthetic */ void lambda$showCallControls$0$OutgoingEnhancedProcessingVideoCallFragment(View view) {
        if (this.endCallButtonLayout.getVisibility() == 0) {
            this.endCallButtonLayout.setAnimation(AnimUtils.getFadingAnimation(300, false));
            this.endCallButtonLayout.setVisibility(4);
            return;
        }
        this.endCallButtonLayout.setAnimation(AnimUtils.getFadingAnimation(300, true));
        this.endCallButtonLayout.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        OutgoingCallPresenterContract outgoingCallPresenterContract = this.outgoingCallPresenterContract;
        if (outgoingCallPresenterContract != null) {
            outgoingCallPresenterContract.onViewCreated();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LOG.d(" onCreateView of OutgoingEnhancedProcessingVideoCallFragment");
        CommsDaggerWrapper.getComponent().inject(this);
        this.rootView = layoutInflater.inflate(obtainLayoutToInflate(), viewGroup, false);
        initializeUIWidgets();
        return this.rootView;
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener) {
        this.videoDisplayListener = videoDisplayListener;
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showCallControls(boolean z) {
        if (z) {
            this.rootView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$OutgoingEnhancedProcessingVideoCallFragment$5Zl75JqaSglOMlmibVUk9AwA0tY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OutgoingEnhancedProcessingVideoCallFragment.this.lambda$showCallControls$0$OutgoingEnhancedProcessingVideoCallFragment(view);
                }
            });
        } else {
            this.endCallButtonLayout.setVisibility(0);
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showCallScreenTitleMessage(String str) {
        this.screenTitle.setText(str);
        this.screenTitle.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showCallStatus(String str) {
        this.callStatus.setText(str);
        this.callStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showMaximisedSelfView() {
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showOutgoingCallIsInProgress() {
        this.progressDots.setBackground(getResources().getDrawable(R.drawable.progress_dots_white));
        this.progressDots.setVisibility(0);
        ((AnimationDrawable) this.progressDots.getBackground()).start();
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showRemoteParticipantName(String str) {
        this.mCalleeName.setText(str);
        this.mCalleeName.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showSpeakerOff() {
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract
    public void showSpeakerOn() {
    }

    @Override // com.amazon.deecomms.calling.contracts.BaseViewContract
    public void setPresenter(OutgoingCallPresenterContract outgoingCallPresenterContract) {
        this.outgoingCallPresenterContract = outgoingCallPresenterContract;
    }
}
