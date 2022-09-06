package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.PushProcessStatus;
/* loaded from: classes12.dex */
public class ValidateRecipientFilter implements Task<PushProcessStatus, Bundle> {
    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        String string = bundle.getString(Constants.BUNDLE_KEY_NOTIFICATION_RECIPIENT);
        if (TextUtils.isEmpty(string)) {
            return PushProcessStatus.MISSING_RECIPIENT;
        }
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("ValidateRecipientFilter", false);
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("ValidateRecipientFilter", false);
        if (!commsId.equals(string) && !homeGroupId.equals(string)) {
            return PushProcessStatus.INVALID_RECIPIENT;
        }
        return PushProcessStatus.CONTINUE;
    }
}
