package com.amazon.identity.auth.device.service;

import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class MAPServiceResult {
    private MAPServiceResult() throws Exception {
        throw new Exception("This class is not instantiable!");
    }

    public static Bundle getOnCancelBundle(int i, String[] strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val, i);
        if (i == 1 && strArr != null) {
            bundle.putStringArray(AuthzConstants.BUNDLE_KEY.REJECTED_SCOPE_LIST.val, strArr);
        }
        return bundle;
    }

    public static Bundle getOnErrorBundle(AuthError authError) {
        return AuthError.getErrorBundle(authError);
    }

    public static Bundle getOnSuccessBundle(String str, String str2) {
        return GeneratedOutlineSupport1.outline11(str, str2);
    }
}
