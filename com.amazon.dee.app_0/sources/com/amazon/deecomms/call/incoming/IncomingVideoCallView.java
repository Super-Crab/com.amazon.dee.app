package com.amazon.deecomms.call.incoming;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.ui.OptInUI;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.perms.PermissionsHelper;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes12.dex */
public class IncomingVideoCallView extends IncomingCallView {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + IncomingVideoCallView.class);
    private Context context;
    private boolean mIsThemedUIEnabled;
    private boolean shouldShowRTTMessage;
    private SipClientState sipClientState;

    public IncomingVideoCallView(@NonNull Context context, @NonNull SelfViewManager selfViewManager, @NonNull CapabilitiesManager capabilitiesManager, @NonNull SipClientState sipClientState, boolean z) {
        super(context);
        this.mIsThemedUIEnabled = capabilitiesManager.isThemedUIEnabled();
        if (this.mIsThemedUIEnabled) {
            LayoutInflater.from(context).inflate(R.layout.fiesta_incoming_video_call_view, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.incoming_video_call_view, this);
        }
        selfViewManager.maximizeSelfView();
        selfViewManager.showScrim();
        this.context = context;
        this.shouldShowRTTMessage = z;
        this.sipClientState = sipClientState;
    }

    private void init() {
        int color = getResources().getColor(R.color.video_button_text_color);
        ((TextView) findViewById(R.id.callStatus)).setTextColor(color);
        this.mCallerName.setTextColor(color);
    }

    private void setupRTT() {
        TextView textView = (TextView) findViewById(R.id.callStatus);
        textView.setText(this.context.getResources().getString(R.string.rtt_enabled));
        textView.setMaxLines(2);
        textView.setEllipsize(null);
        textView.setVisibility(0);
    }

    @Override // com.amazon.deecomms.call.incoming.IncomingCallView
    public void bindOnce(String str, String str2, String str3, final Activity activity) {
        ImageButton imageButton;
        super.bindOnce(str, str2, str3, activity);
        if (this.mIsThemedUIEnabled) {
            imageButton = (ImageButton) findViewById(R.id.acceptVideoCallButton);
        } else {
            init();
            imageButton = (ImageButton) findViewById(R.id.answerVideoCallButton);
        }
        if (this.shouldShowRTTMessage) {
            setupRTT();
        }
        imageButton.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.call.incoming.IncomingVideoCallView.1
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                Context context = IncomingVideoCallView.this.getContext();
                String[] checkPermissions = PermissionsHelper.checkPermissions(context, PermissionsHelper.getPermissionListForVideoCalling());
                if (checkPermissions.length > 0) {
                    String deniedCallingPermissionsRationale = PermissionsHelper.getDeniedCallingPermissionsRationale(context, true);
                    IncomingVideoCallView.LOG.i(" Requesting permissions to accept Video Call");
                    PermissionsHelper.requestPermission(activity, deniedCallingPermissionsRationale, checkPermissions, 7, MetricKeys.ALERT_PERM_MIC_AND_CAMERA, MetricKeys.SCREEN_NAME_INCOMING_VIDEO_CALL, AlertSource.newClassSource(IncomingVideoCallView.this), true, (DialogInterface.OnDismissListener) null);
                    return;
                }
                OptInUI optInUI = new OptInUI();
                SipHeaders incomingHeaders = IncomingVideoCallView.this.sipClientState.getCurrentActiveCall().getIncomingHeaders();
                if (optInUI.isNeeded(IncomingVideoCallView.this.sipClientState, incomingHeaders != null && incomingHeaders.contains(Constants.GROUPID_HEADER))) {
                    IncomingVideoCallView.LOG.i("Opt-in is needed");
                    optInUI.show(Constants.INCOMING, activity);
                    return;
                }
                IncomingVideoCallView.LOG.i(" Accepting Incoming Video Call as user as opt-in is not needed");
                CallUtils.acceptIncomingCall(context, true);
            }
        });
    }
}
