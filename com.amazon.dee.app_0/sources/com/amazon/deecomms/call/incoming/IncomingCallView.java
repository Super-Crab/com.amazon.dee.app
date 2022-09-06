package com.amazon.deecomms.call.incoming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public abstract class IncomingCallView extends FrameLayout {
    private static boolean mIsThemedUIEnabled;
    protected TextView mCallerName;
    protected CounterMetric mInboundVideoCallHangUp;

    public IncomingCallView(@NonNull Context context) {
        super(context);
    }

    public static IncomingCallView getInstance(@NonNull Context context, @NonNull SelfViewManager selfViewManager, @NonNull CapabilitiesManager capabilitiesManager, @NonNull SipClientState sipClientState, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        mIsThemedUIEnabled = capabilitiesManager.isThemedUIEnabled();
        return new IncomingVideoCallView(context, selfViewManager, capabilitiesManager, sipClientState, realTimeTextEnablementAuthority.shouldShowRTTMessageOnIncomingScreen());
    }

    public void bindOnce(String str, String str2, String str3, Activity activity) {
        ImageButton imageButton;
        this.mCallerName = (TextView) findViewById(R.id.callParticipantName);
        CallViewUtils.displayNameAndStatus(activity, this.mCallerName, (TextView) findViewById(R.id.callStatus));
        final Context context = getContext();
        if (mIsThemedUIEnabled) {
            imageButton = (ImageButton) findViewById(R.id.callFinishButton);
        } else {
            imageButton = (ImageButton) findViewById(R.id.rejectCallButton);
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.call.incoming.IncomingCallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallType().isVideo()) {
                    MetricsHelper.recordCounterMetric(IncomingCallView.this.mInboundVideoCallHangUp, Double.valueOf(1.0d));
                }
                CallUtils.rejectCall(context);
            }
        });
        Intent intent = new Intent(context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.NOTIFY_INCOMING_CALL);
        context.startService(intent);
    }
}
