package com.amazon.deecomms.call.outgoing;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.util.AnimUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class OutgoingVideoCallView extends OutgoingCallView {
    private static final int FADING_ANIMATION_TIME = 300;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + OutgoingVideoCallView.class);
    private LinearLayout endCallButtonLayout;
    private Context mContext;
    private boolean mIsMosaicThemeEnabled;
    private boolean mIsThemedUIEnabled;
    private SelfViewManager mSelfViewManager;

    public OutgoingVideoCallView(Context context) {
        this(context, null, null, false, false);
    }

    private void init() {
        if (!this.mIsThemedUIEnabled) {
            this.mCalleeName.setTextColor(ContextCompat.getColor(getContext(), R.color.video_button_text_color));
        }
        this.endCallButtonLayout = (LinearLayout) findViewById(R.id.end_call_button_layout);
        ((TextView) findViewById(R.id.screen_title)).setVisibility(4);
        setUpButtonUIAndTimer();
    }

    private void initiateCall(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getString("COMMS_ID") == null) {
                return;
            }
            String string = bundle.getString("COMMS_ID");
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("CommsID for the Initiate Call Task ");
            outline1.append(LOG.sensitive(string));
            commsLogger.i(outline1.toString());
            CallType fromBundle = CallType.fromBundle(bundle, Constants.CALL_TYPE);
            String string2 = bundle.getString(Constants.DEVICE_GRUU);
            String fromBundle2 = CallProvider.fromBundle(bundle, Constants.CALL_PROVIDER);
            Intent intent = new Intent(this.mContext, DeviceCallingAndroidService.class);
            intent.putExtras(bundle);
            intent.setAction(Constants.MAKE_OUTGOING_CALL);
            intent.putExtra(Constants.CALLEE_COMMS_ID, string);
            intent.putExtra(Constants.CALL_TYPE, fromBundle.toString());
            intent.putExtra(Constants.CALL_PROVIDER, fromBundle2);
            intent.putExtra(Constants.DEVICE_GRUU, string2);
            intent.putExtra(Constants.CALL_START_TIME, bundle.getLong(Constants.CALL_START_TIME));
            if (CommsDaggerWrapper.getComponent().getCapabilitiesManager().isAssistSipCallingEnabled()) {
                intent.putExtra(Constants.CALLING_NEW_ARCHITECTURE, bundle.getBoolean(Constants.CALLING_NEW_ARCHITECTURE));
                intent.putExtra(Constants.GROUP_CALL, bundle.getBoolean(Constants.GROUP_CALL));
            }
            this.mContext.startService(intent);
            return;
        }
        LOG.w("Required parameters to make the outgoing call have not been received");
    }

    private void setSelfView() {
        if (CallUtils.isDropInCall()) {
            this.mSelfViewManager.minimizeSelfView(0);
            this.mSelfViewManager.hideScrim();
            return;
        }
        this.mSelfViewManager.maximizeSelfView();
        this.mSelfViewManager.showScrim();
    }

    private void setUpButtonUIAndTimer() {
        if (CallUtils.isDropInCall()) {
            setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.call.outgoing.OutgoingVideoCallView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (OutgoingVideoCallView.this.endCallButtonLayout.getVisibility() == 0) {
                        AnimUtils.fadingAnimation(OutgoingVideoCallView.this.mContext, OutgoingVideoCallView.this.endCallButtonLayout, 300, false);
                        OutgoingVideoCallView.this.endCallButtonLayout.setVisibility(4);
                        return;
                    }
                    AnimUtils.fadingAnimation(OutgoingVideoCallView.this.mContext, OutgoingVideoCallView.this.endCallButtonLayout, 300, true);
                    OutgoingVideoCallView.this.endCallButtonLayout.setVisibility(0);
                }
            });
        } else {
            this.endCallButtonLayout.setVisibility(0);
        }
    }

    private void showProgressDots() {
        ImageView imageView = (ImageView) findViewById(R.id.progress_dots);
        imageView.setBackground(getResources().getDrawable(R.drawable.progress_dots_white));
        imageView.setVisibility(0);
        ((AnimationDrawable) imageView.getBackground()).start();
    }

    @Override // com.amazon.deecomms.call.outgoing.OutgoingCallView
    public void bindOnce(String str, String str2) {
        super.bindOnce(str, str2);
        init();
        showProgressDots();
    }

    public OutgoingVideoCallView(Context context, SelfViewManager selfViewManager, Bundle bundle, boolean z, boolean z2) {
        super(context);
        this.mContext = context;
        this.mSelfViewManager = selfViewManager;
        setSelfView();
        this.mIsThemedUIEnabled = z;
        this.mIsMosaicThemeEnabled = z2;
        if (this.mIsMosaicThemeEnabled) {
            LayoutInflater.from(context).inflate(R.layout.mosaic_outgoing_video_call_view, this);
        } else if (this.mIsThemedUIEnabled) {
            LayoutInflater.from(context).inflate(R.layout.fiesta_outgoing_video_call_view, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.outgoing_video_call_view, this);
        }
        if (CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent()) {
            LOG.e("There is already an active call. So not initiating a video call.");
        } else {
            initiateCall(bundle);
        }
    }
}
