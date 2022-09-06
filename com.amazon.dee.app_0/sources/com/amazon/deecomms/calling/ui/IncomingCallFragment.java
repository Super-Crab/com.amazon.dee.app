package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.perms.PermissionsHelper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class IncomingCallFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, IncomingCallFragment.class);
    @Inject
    CapabilitiesManager mCapabilitiesManager;
    private Context mContext;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;

    /* JADX INFO: Access modifiers changed from: private */
    public static void addCommsItemId(CommsMetric commsMetric) {
        commsMetric.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallId());
    }

    private void init(View view) {
        ImageButton imageButton;
        ImageButton imageButton2;
        Bundle arguments = getArguments();
        CallViewUtils.displayNameAndStatus(this, (TextView) view.findViewById(R.id.callParticipantName), (TextView) view.findViewById(R.id.callStatus));
        if (arguments != null) {
            Intent intent = new Intent(this.mContext, DeviceCallingAndroidService.class);
            intent.setAction(Constants.NOTIFY_INCOMING_CALL);
            this.mContext.startService(intent);
        }
        if (this.mCapabilitiesManager.isThemedUIEnabled()) {
            imageButton = (ImageButton) view.findViewById(R.id.acceptCallButton);
        } else {
            imageButton = (ImageButton) view.findViewById(R.id.answerCallButton);
        }
        imageButton.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.calling.ui.IncomingCallFragment.1
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view2) {
                IncomingCallFragment.recordClickMetric(MetricKeys.CALL_ANSWER);
                TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_ANSWER);
                IncomingCallFragment.addCommsItemId(generateClickstream);
                MetricsHelper.startTimerMetric(generateClickstream);
                String[] checkPermissions = PermissionsHelper.checkPermissions(IncomingCallFragment.this.mContext, PermissionsHelper.getPermissionListForAudio());
                if (checkPermissions.length > 0) {
                    PermissionsHelper.requestPermission((Activity) IncomingCallFragment.this.getActivity(), PermissionsHelper.getDeniedCallingPermissionsRationale(IncomingCallFragment.this.mContext, false), checkPermissions, 1, MetricKeys.ALERT_PERM_MIC, MetricKeys.SCREEN_NAME_INCOMING_CALL, AlertSource.newClassSource(IncomingCallFragment.this), true, (DialogInterface.OnDismissListener) null);
                    return;
                }
                IncomingCallFragment.LOG.i(" Answering call ");
                CallUtils.acceptIncomingCall(IncomingCallFragment.this.mContext, false);
            }
        });
        if (this.mCapabilitiesManager.isThemedUIEnabled()) {
            imageButton2 = (ImageButton) view.findViewById(R.id.callFinishButton);
        } else {
            imageButton2 = (ImageButton) view.findViewById(R.id.rejectCallButton);
        }
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.IncomingCallFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                IncomingCallFragment.recordClickMetric(MetricKeys.CALL_DECLINE);
                CallUtils.rejectCall(IncomingCallFragment.this.mContext);
            }
        });
        setupRTT(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordClickMetric(String str) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        addCommsItemId(generateClickstream);
        MetricsHelper.recordSingleOccurrence(generateClickstream);
    }

    private void setupRTT(View view) {
        if (!this.mCapabilitiesManager.isRealTimeTextEnabled() || !this.realTimeTextEnablementAuthority.shouldShowRTTMessageOnIncomingScreen()) {
            return;
        }
        TextView textView = (TextView) view.findViewById(R.id.callStatus);
        textView.setMaxLines(2);
        textView.setEllipsize(null);
        textView.setText(this.mContext.getResources().getString(R.string.rtt_enabled));
        textView.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        CommsDaggerWrapper.getComponent().inject(this);
        LOG.d(" onCreateView of IncomingCallFragment ");
        this.mContext = getContext();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_IN_SHOWN);
        if (this.mCapabilitiesManager.isMosaicThemingEnabled()) {
            inflate = layoutInflater.inflate(R.layout.mosaic_incoming_call_view, viewGroup, false);
        } else if (this.mCapabilitiesManager.isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_incoming_call_view, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.incoming_call_view, viewGroup, false);
        }
        getActivity().getWindow().addFlags(128);
        init(inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getWindow().clearFlags(128);
    }
}
