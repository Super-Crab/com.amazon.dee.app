package com.amazon.deecomms.call.outgoing;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public abstract class OutgoingCallView extends FrameLayout {
    protected TextView mCalleeName;
    private Context mContext;
    protected CounterMetric mOutboundVideoCallCancel;

    public OutgoingCallView(Context context) {
        super(context);
        this.mContext = context;
    }

    public static OutgoingCallView createInstance(Context context, SelfViewManager selfViewManager, Bundle bundle, boolean z, boolean z2) {
        return new OutgoingVideoCallView(context, selfViewManager, bundle, z, z2);
    }

    private void setCalleeInfo(String str) {
        this.mCalleeName = (TextView) findViewById(R.id.callParticipantName);
        CallViewUtils.displayNameAndStatus(getContext(), this.mCalleeName, (TextView) findViewById(R.id.callStatus));
        if (CallUtils.isDropInCall()) {
            this.mCalleeName.setVisibility(4);
        }
    }

    public void bindOnce(String str, String str2) {
        setCalleeInfo(str);
        ((ImageButton) findViewById(R.id.callFinishButton)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.call.outgoing.OutgoingCallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CallUtils.cancelOutgoingCall(OutgoingCallView.this.mContext);
                if (CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallType().isVideo()) {
                    MetricsHelper.recordCounterMetric(OutgoingCallView.this.mOutboundVideoCallCancel, Double.valueOf(1.0d));
                }
            }
        });
    }
}
