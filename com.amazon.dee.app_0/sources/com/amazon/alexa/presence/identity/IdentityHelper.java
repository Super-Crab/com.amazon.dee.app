package com.amazon.alexa.presence.identity;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.presence.Presence;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class IdentityHelper {
    private static final String TAG = Presence.tag();

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getAuthToken() {
        /*
            java.lang.Class<com.amazon.alexa.identity.api.IdentityService> r0 = com.amazon.alexa.identity.api.IdentityService.class
            java.lang.Object r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline21(r0)
            com.amazon.alexa.identity.api.IdentityService r0 = (com.amazon.alexa.identity.api.IdentityService) r0
            java.lang.String r1 = ""
            if (r0 == 0) goto L1d
            java.lang.Class<com.amazon.alexa.presence.identity.IdentityHelper> r2 = com.amazon.alexa.presence.identity.IdentityHelper.class
            java.lang.String r2 = r2.getName()
            com.amazon.alexa.identity.api.UserIdentity r0 = r0.getUser(r2)
            if (r0 == 0) goto L24
            java.lang.String r0 = r0.getAccessToken()
            goto L25
        L1d:
            java.lang.String r0 = com.amazon.alexa.presence.identity.IdentityHelper.TAG
            java.lang.String r2 = "Unable to retrieve user from identity service."
            android.util.Log.w(r0, r2)
        L24:
            r0 = r1
        L25:
            if (r0 == 0) goto L2d
            int r2 = r0.length()
            if (r2 != 0) goto L34
        L2d:
            java.lang.String r2 = com.amazon.alexa.presence.identity.IdentityHelper.TAG
            java.lang.String r3 = "Unable to retrieve MapToken"
            android.util.Log.w(r2, r3)
        L34:
            if (r0 != 0) goto L37
            r0 = r1
        L37:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.presence.identity.IdentityHelper.getAuthToken():java.lang.String");
    }

    @NonNull
    public static String getAuthTokenDirectlyFromMap() {
        String str;
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        if (identityService != null) {
            str = identityService.getAccessToken(IdentityHelper.class.getName());
        } else {
            Log.w(TAG, "Unable to retrieve user from identity service.  Service is null.");
            str = "";
        }
        if (str == null || str.length() == 0) {
            Log.w(TAG, "Unable to retrieve MapToken");
        }
        return str == null ? "" : str;
    }

    public boolean isUserAuthenticated() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        return identityService != null && identityService.isAuthenticated(IdentityHelper.class.getName());
    }

    public boolean requestRefreshAuthToken() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (identityService != null) {
            identityService.refresh(IdentityHelper.class.getName()).toBlocking().subscribe(new Action1() { // from class: com.amazon.alexa.presence.identity.-$$Lambda$IdentityHelper$_-zlhWfv0N1QuhlA1RyV21bZCN4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    UserIdentity userIdentity = (UserIdentity) obj;
                    atomicBoolean.set(true);
                }
            }, $$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc4.INSTANCE);
        }
        return atomicBoolean.get();
    }
}
