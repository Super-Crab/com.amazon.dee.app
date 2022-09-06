package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class MakeNativeCallHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MakeNativeCallHandler.class);
    static final String PHONE_PACKAGE_NAME_NEW = "com.android.server.telecom";
    static final String PHONE_PACKAGE_NAME_OLD = "com.android.phone";
    private final Context context;

    public MakeNativeCallHandler(@NonNull Context context) {
        this.context = context;
    }

    public Boolean initiateNativePhoneCall(@NonNull String str, INoCallPermissionHandler iNoCallPermissionHandler, boolean z) {
        Intent intent = new Intent("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + str));
        intent.setFlags(268435456);
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.CALL_PHONE") == 0) {
            GeneratedOutlineSupport1.outline159("Placing native phone call... ", str, LOG);
            if (z) {
                String str2 = Utils.isLollipopAndAbove() ? PHONE_PACKAGE_NAME_NEW : PHONE_PACKAGE_NAME_OLD;
                CommsLogger commsLogger = LOG;
                commsLogger.i("setting package to " + str2 + " for call intent");
                intent.setPackage(str2);
            }
            this.context.startActivity(intent);
            PCCDirectiveHandler.recordPCCMetric(CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_DIAL), true);
            return true;
        }
        LOG.i("No Permissions to place Native Phone call");
        iNoCallPermissionHandler.handleNoPermission(1, str, MetricKeys.CPCC_CALLING_DIAL);
        return false;
    }
}
