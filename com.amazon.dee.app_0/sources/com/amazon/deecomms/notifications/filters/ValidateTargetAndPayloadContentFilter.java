package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.calling.model.notification.CallInvite;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.notifications.PushProcessStatus;
import com.amazon.deecomms.notifications.PushTypeHelper;
/* loaded from: classes12.dex */
public class ValidateTargetAndPayloadContentFilter implements Task<PushProcessStatus, Bundle> {
    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        String string = bundle.getString("target");
        if (TextUtils.isEmpty(string)) {
            return PushProcessStatus.MISSING_TARGET;
        }
        if (!Constants.VALID_NOTIFICATION_TARGETS.contains(string)) {
            return PushProcessStatus.UNSUPPORTED_TARGET;
        }
        JacksonJSONConverter jacksonJSONConverter = new JacksonJSONConverter();
        String string2 = bundle.getString(Constants.AMP_KEY);
        if ("MESSAGE".equals(string)) {
            if (PushTypeHelper.determineType(string2) == PushTypeHelper.PushType.Invalid) {
                return PushProcessStatus.UNKNOWN_PUSH_TYPE;
            }
        } else if (Constants.NOTIFICATION_TARGET_CALLING.equals(string)) {
            MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_CALLING, 1.0d);
            CallInvite callInvite = (CallInvite) jacksonJSONConverter.fromJson(string2, CallInvite.class);
            if (callInvite == null) {
                return PushProcessStatus.MISSING_CALLING_PUSH_PAYLOAD;
            }
            if (callInvite.getTokenInfo() == null) {
                return PushProcessStatus.MISSING_CALLING_TOKEN_INFO;
            }
            if (TextUtils.isEmpty(callInvite.getTokenInfoVersion())) {
                return PushProcessStatus.MISSING_CALLING_TOKEN_INFO_VERSION;
            }
            if (TextUtils.isEmpty(callInvite.getTokenValue())) {
                return PushProcessStatus.MISSING_CALLING_TOKEN_VALUE;
            }
            if (TextUtils.isEmpty(callInvite.getTokenVersion())) {
                return PushProcessStatus.MISSING_CALLING_TOKEN_VERSION;
            }
        }
        return PushProcessStatus.CONTINUE;
    }
}
