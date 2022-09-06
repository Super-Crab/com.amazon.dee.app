package com.amazon.deecomms.calling.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CallingInitiationActivity extends AppCompatActivity {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingInitiationActivity.class);
    @Inject
    CommsNotificationManager commsNotificationManager;
    @Inject
    InitiationLogicFactory initiationLogicFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {
        super.onCreate(bundle);
        CommsDaggerWrapper.getComponent().inject(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.get(Constants.NOTIFICATION_ID) == null || extras.getString("COMMS_ID") == null || !Constants.FRAGMENT_OUTGOING_CALL_KEY.equals(extras.getString(Constants.LAUNCH_FRAGMENT_KEY))) {
                LOG.e("Invalid NOTIFICATION_ID, or COMMS_ID or LAUNCH_FRAGMENT_KEY");
                finish();
            }
            InitiationLogicContract create = this.initiationLogicFactory.create(new CallInitiator(MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT), this, this, new CallHelper(), null, "notification");
            String string = extras.getString(Constants.CALL_TYPE);
            GeneratedOutlineSupport.outline4("Start a callback with call type ", string, LOG);
            if (CallType.VIDEO.toString().equals(string)) {
                create.initiateVideoCall(extras.getString("COMMS_ID"), extras.getString(Constants.REMOTE_PARTICIPANT_NAME));
            } else {
                create.initiateAudioCall(extras.getString("COMMS_ID"), extras.getString(Constants.REMOTE_PARTICIPANT_NAME));
            }
            this.commsNotificationManager.cancelNotification(extras.getInt(Constants.NOTIFICATION_ID));
        }
    }
}
