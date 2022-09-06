package com.amazon.deecomms.calling.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
/* loaded from: classes12.dex */
public class CallUIHandler extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallUIHandler.class);
    private final ActivitiesManager activitiesManager;
    private final CallInitiationAuthority callInitiationAuthority;
    private IntentFilter mCallHandlerFilter;
    private LocalBroadcastManager mLocalBroadcastManager;

    public CallUIHandler(@NonNull ActivitiesManager activitiesManager, @NonNull CallInitiationAuthority callInitiationAuthority) {
        this.activitiesManager = activitiesManager;
        this.callInitiationAuthority = callInitiationAuthority;
    }

    private LocalBroadcastManager getLocalBroadcastManager(Context context) {
        if (this.mLocalBroadcastManager == null) {
            this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        }
        return this.mLocalBroadcastManager;
    }

    public boolean isCallIntent(@Nullable Intent intent) {
        return (intent == null || intent.getComponent() == null || (!CallActivity.class.getName().equals(intent.getComponent().getClassName()) && !NewCallActivity.class.getName().equals(intent.getComponent().getClassName()))) ? false : true;
    }

    public boolean isRegistered() {
        return this.mCallHandlerFilter != null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            LOG.e("Intent is null and cannot perform any action");
            return;
        }
        String action = intent.getAction();
        if (Constants.SHOW_CALL_UI.equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString(Constants.LAUNCH_FRAGMENT_KEY);
                if (string == null) {
                    LOG.e("The fragment to be launched was not specified in the intent");
                    CallUtils.handleCallInitiationErrors();
                    return;
                }
                String str = "";
                String fromString = CallProvider.fromString(intent.hasExtra(Constants.CALL_PROVIDER) ? intent.getStringExtra(Constants.CALL_PROVIDER) : str);
                if (intent.hasExtra(Constants.Calling.CSP_ID)) {
                    str = intent.getStringExtra(Constants.Calling.CSP_ID);
                }
                if (this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.fromNullable(fromString), Optional.fromNullable(str))) {
                    LOG.i("[Comms-calling]: Start a NewCallActivity.");
                    intent.setClass(context, NewCallActivity.class);
                } else {
                    LOG.i("[Comms-calling]: Start an old CallActivity.");
                    intent.setClass(context, CallActivity.class);
                }
                intent.setFlags(268435456);
                if (!Constants.FRAGMENT_END_CALL_KEY.equals(string)) {
                    CommsDaggerWrapper.getComponent().getCallManager().setCallActivityLaunchedOnce(true);
                }
                LOG.d("Starting Call Activity");
                context.startActivity(intent);
                return;
            }
            LOG.e("The specified bundle to CallHandler is null");
            CallUtils.handleCallInitiationErrors();
        } else if (Constants.CLEAR_CALL_ACTIVITY.equals(action)) {
            Activity topActivity = this.activitiesManager.getTopActivity();
            if (topActivity != null && (topActivity instanceof CallActivity)) {
                topActivity.finish();
            } else {
                LOG.i("Top activity is null or not of type CallActivity. CallActivity need not be finished.");
            }
        } else {
            GeneratedOutlineSupport.outline3("Unknown Action : ", action, LOG);
        }
    }

    public void register(Context context) {
        if (!isRegistered()) {
            this.mCallHandlerFilter = new IntentFilter();
            this.mCallHandlerFilter.addAction(Constants.SHOW_CALL_UI);
            this.mCallHandlerFilter.addAction(Constants.CLEAR_CALL_ACTIVITY);
            getLocalBroadcastManager(context).registerReceiver(this, this.mCallHandlerFilter);
        }
    }

    public void unregister(Context context) {
        if (isRegistered()) {
            this.mCallHandlerFilter = null;
            getLocalBroadcastManager(context).unregisterReceiver(this);
        }
    }

    public CallUIHandler(@NonNull ActivitiesManager activitiesManager, @NonNull CallInitiationAuthority callInitiationAuthority, @NonNull LocalBroadcastManager localBroadcastManager) {
        this.activitiesManager = activitiesManager;
        this.mLocalBroadcastManager = localBroadcastManager;
        this.callInitiationAuthority = callInitiationAuthority;
    }
}
