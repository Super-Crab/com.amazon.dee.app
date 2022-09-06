package com.amazon.alexa.presence.library;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public final class IdentityHelper {
    private static final String TAG = "com.amazon.alexa.presence.library.IdentityHelper";

    private IdentityHelper() {
    }

    public static String getMapToken() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        if (identityService == null) {
            Log.w(TAG, "IdentityService could not be located");
            return null;
        }
        String accessToken = identityService.getAccessToken(IdentityHelper.class.getName());
        if (!TextUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        return null;
    }

    public static boolean isUserAuthenticated() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        return identityService != null && identityService.isAuthenticated(IdentityHelper.class.getName());
    }

    public static boolean requestRefreshAuthToken() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (identityService != null) {
            identityService.refresh(IdentityHelper.class.getName()).toBlocking().subscribe(new Action1() { // from class: com.amazon.alexa.presence.library.-$$Lambda$IdentityHelper$5YBSKpDTmNNEez7SmhIkfIDdWKM
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    UserIdentity userIdentity = (UserIdentity) obj;
                    atomicBoolean.set(true);
                }
            }, $$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1yDo1fI.INSTANCE);
        }
        return atomicBoolean.get();
    }
}
