package com.amazon.deecomms.calling.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.TimeoutHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class NativeCallPresenter {
    private static final long CALL_ACTION_TIME_OUT_MILLISECONDS = 30000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NativeCallPresenter.class);
    private final AcceptNativeCallHandler acceptNativeCallHandler;
    private final Activity activity;
    private final EndNativeCallHandler endNativeCallHandler;
    private final MakeNativeCallHandler makeNativeCallHandler;
    private final NoCallPermissionHandler noCallPermissionHandler;
    final String[] permissionsNeeded = {"android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS"};
    private final String source;
    private final TelecomManager telecomManager;
    private final TimeoutHelper timeoutHelper;

    public NativeCallPresenter(@NonNull Activity activity, MakeNativeCallHandler makeNativeCallHandler, AcceptNativeCallHandler acceptNativeCallHandler, EndNativeCallHandler endNativeCallHandler, NoCallPermissionHandler noCallPermissionHandler, TimeoutHelper timeoutHelper) {
        this.activity = activity;
        this.telecomManager = (TelecomManager) activity.getSystemService("telecom");
        this.makeNativeCallHandler = makeNativeCallHandler;
        this.acceptNativeCallHandler = acceptNativeCallHandler;
        this.endNativeCallHandler = endNativeCallHandler;
        this.source = getMetricSource(activity.getIntent());
        this.noCallPermissionHandler = noCallPermissionHandler;
        this.timeoutHelper = timeoutHelper;
    }

    private void handlePermissionsDenied() {
        LOG.i("permissions not granted");
        String source = getSource();
        HashMap hashMap = new HashMap();
        hashMap.put("source", source + " denied");
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.PHONE_CALL_PERMS_DIALOG_GRANTED, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, hashMap);
    }

    @SuppressLint({"MissingPermission"})
    private void handlePermissionsGranted() {
        LOG.i("permissions granted");
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.PHONE_CALL_PERMS_DIALOG_GRANTED, 1.0d, GeneratedOutlineSupport1.outline133("source", getSource()));
        if (this.timeoutHelper.hasTimedOut()) {
            return;
        }
        Intent intent = this.activity.getIntent();
        int intExtra = intent.getIntExtra(Constants.NativeCalling.EXTRA_ACTION_AFTER_PERMISSION_GRANTED, 0);
        if (intExtra == 1) {
            String stringExtra = intent.getStringExtra(Constants.NativeCalling.EXTRA_ACTION_PCC);
            if (stringExtra != null && stringExtra.length() <= 0) {
                LOG.w("No pccUri togo with make call action");
            } else {
                this.makeNativeCallHandler.initiateNativePhoneCall(stringExtra, this.noCallPermissionHandler, false);
            }
        } else if (intExtra == 2) {
            this.acceptNativeCallHandler.acceptNativePhoneCall();
        } else if (intExtra != 3) {
            LOG.w("Native calling presenter: Unsupported action");
        } else {
            this.endNativeCallHandler.endNativePhoneCall();
        }
    }

    @NonNull
    @VisibleForTesting
    String getMetricSource(@Nullable Intent intent) {
        String stringExtra = intent != null ? intent.getStringExtra(Constants.NativeCalling.NO_CALL_PERMISSIONS_SOURCE) : null;
        if (stringExtra == null) {
            stringExtra = "UNKNOWN";
        }
        GeneratedOutlineSupport.outline4("Source: ", stringExtra, LOG);
        return stringExtra;
    }

    @NonNull
    public String getSource() {
        return this.source;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPermissionResult() {
        if (PermissionsHelper.checkPermissions(this.activity, this.permissionsNeeded).length == 0) {
            handlePermissionsGranted();
        } else {
            handlePermissionsDenied();
        }
        this.activity.finish();
    }

    @VisibleForTesting
    void requestCallingPermissions(@NonNull String str, @NonNull DialogInterface.OnDismissListener onDismissListener) {
        String[] checkPermissions = PermissionsHelper.checkPermissions(this.activity, this.permissionsNeeded);
        if (checkPermissions.length == 0) {
            LOG.i("No permissions needed.");
            handlePermissionsGranted();
            this.activity.finish();
            return;
        }
        GeneratedOutlineSupport.outline4("RequestingPermissions for source: ", str, LOG);
        for (String str2 : checkPermissions) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("permission: ");
            outline1.append(str2);
            commsLogger.i(outline1.toString());
        }
        PermissionsHelper.requestPermission(this.activity, Utils.getStringFromResource(R.string.phone_call_permission_rationale), checkPermissions, 335, MetricKeys.PHONE_CALL_PERMS_DIALOG, str, AlertSource.newCallSource(NativeCallPresenter.class.getName()), false, onDismissListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showCallingPermissions() {
        this.timeoutHelper.startTimeOut(30000L);
        requestCallingPermissions(this.source, new PermissionsDismissListener(this.activity, MetricKeys.PHONE_CALL_PERMS_DIALOG_GRANTED, this.source, true));
    }
}
