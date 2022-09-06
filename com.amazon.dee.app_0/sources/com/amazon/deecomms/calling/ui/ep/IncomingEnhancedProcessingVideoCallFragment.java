package com.amazon.deecomms.calling.ui.ep;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract;
import com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.perms.PermissionsHelper;
/* loaded from: classes12.dex */
public class IncomingEnhancedProcessingVideoCallFragment extends Fragment implements IncomingVideoCallViewContract<IncomingCallPresenterContract> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, IncomingEnhancedProcessingVideoCallFragment.class);
    private View answerCallButton;
    private TextView callStatus;
    private TextView callerName;
    private ImageButton endCallButton;
    private IncomingCallPresenterContract incomingCallPresenterContract;
    private View rootView;
    private VideoDisplayListener videoDisplayListener;

    private void initializeUIWidgets() {
        this.answerCallButton = this.rootView.findViewById(R.id.acceptVideoCallButton);
        this.callStatus = (TextView) this.rootView.findViewById(R.id.callStatus);
        this.callerName = (TextView) this.rootView.findViewById(R.id.callParticipantName);
        this.endCallButton = (ImageButton) this.rootView.findViewById(R.id.callFinishButton);
        int color = getResources().getColor(R.color.video_button_text_color);
        this.callStatus.setTextColor(color);
        this.callerName.setTextColor(color);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void activateCallControls() {
        this.answerCallButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$IncomingEnhancedProcessingVideoCallFragment$cpCRcqsTqiQZR6AtAd2Ny45lDKs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IncomingEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$0$IncomingEnhancedProcessingVideoCallFragment(view);
            }
        });
        this.endCallButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.ep.-$$Lambda$IncomingEnhancedProcessingVideoCallFragment$0_NN6sl7L9Z4TrtE89_b7Rj30FE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IncomingEnhancedProcessingVideoCallFragment.this.lambda$activateCallControls$1$IncomingEnhancedProcessingVideoCallFragment(view);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void hideCallControls() {
        this.answerCallButton.setVisibility(4);
        this.endCallButton.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void hideCallStatus() {
        this.callStatus.setVisibility(4);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void hideRemoteParticipantName() {
        this.callerName.setVisibility(4);
    }

    public /* synthetic */ void lambda$activateCallControls$0$IncomingEnhancedProcessingVideoCallFragment(View view) {
        this.incomingCallPresenterContract.checkForPermissionsAndAcceptCall();
    }

    public /* synthetic */ void lambda$activateCallControls$1$IncomingEnhancedProcessingVideoCallFragment(View view) {
        this.incomingCallPresenterContract.rejectCall();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        IncomingCallPresenterContract incomingCallPresenterContract = this.incomingCallPresenterContract;
        if (incomingCallPresenterContract != null) {
            incomingCallPresenterContract.onViewCreated();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fiesta_incoming_video_call_view, viewGroup, false);
        initializeUIWidgets();
        return this.rootView;
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener) {
        this.videoDisplayListener = videoDisplayListener;
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void showCallStatus(@NonNull String str) {
        this.callStatus.setText(str);
        this.callStatus.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void showIncomingCallPermissions(@NonNull String[] strArr) {
        PermissionsHelper.requestPermission((Activity) getActivity(), PermissionsHelper.getDeniedCallingPermissionsRationale(this.rootView.getContext(), true), strArr, 7, MetricKeys.ALERT_PERM_MIC_AND_CAMERA, MetricKeys.SCREEN_NAME_INCOMING_VIDEO_CALL, AlertSource.newClassSource(IncomingEnhancedProcessingVideoCallFragment.class.getName()), true, (DialogInterface.OnDismissListener) null);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void showMaximisedSelfView() {
        this.videoDisplayListener.maximizeVideo(true);
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingVideoCallViewContract
    public void showRemoteParticipantName(@NonNull String str) {
        this.callerName.setText(str);
        this.callerName.setVisibility(0);
    }

    @Override // com.amazon.deecomms.calling.contracts.BaseViewContract
    public void setPresenter(@NonNull IncomingCallPresenterContract incomingCallPresenterContract) {
        this.incomingCallPresenterContract = incomingCallPresenterContract;
    }
}
