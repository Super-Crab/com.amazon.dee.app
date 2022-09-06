package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.notifications.PushProcessStatus;
/* loaded from: classes12.dex */
public class ValidateAmazonPayloadFilter implements Task<PushProcessStatus, Bundle> {
    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        if (TextUtils.isEmpty(bundle.getString(Constants.AMP_KEY))) {
            return PushProcessStatus.MISSING_AMAZON_PAYLOAD;
        }
        return PushProcessStatus.CONTINUE;
    }
}
