package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.PushProcessStatus;
/* loaded from: classes12.dex */
public class ValidateApplicationIdFilter implements Task<PushProcessStatus, Bundle> {
    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        String string = bundle.getString("application");
        if (TextUtils.isEmpty(string)) {
            return PushProcessStatus.MISSING_APPLICATION_ID;
        }
        if (!string.equals(CommsDaggerWrapper.getComponent().getPushNotificationManager().getEpmsCommsAppId())) {
            return PushProcessStatus.INVALID_APPLICATION_ID;
        }
        return PushProcessStatus.CONTINUE;
    }
}
