package com.amazon.identity.auth.device.api.authorization;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.authorization.InternalAuthManager;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class User {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.api.authorization.User";
    private final Map<String, String> userInfo;

    User(Map<String, String> map) {
        this.userInfo = map;
    }

    public static void fetch(Context context, Listener<User, AuthError> listener) {
        fetch(context, InternalAuthManager.getInstance(context), listener);
    }

    static User userFromBundle(Bundle bundle) {
        HashMap hashMap = new HashMap(bundle.size());
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return new User(hashMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || User.class != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        Map<String, String> map = this.userInfo;
        if (map == null) {
            if (user.userInfo != null) {
                return false;
            }
        } else if (!map.equals(user.userInfo)) {
            return false;
        }
        return true;
    }

    public String getUserEmail() {
        return this.userInfo.get(AuthzConstants.PROFILE_KEY.EMAIL.val);
    }

    public String getUserId() {
        return this.userInfo.get(AuthzConstants.PROFILE_KEY.USER_ID.val);
    }

    public Map<String, String> getUserInfo() {
        return this.userInfo;
    }

    public String getUserName() {
        return this.userInfo.get(AuthzConstants.PROFILE_KEY.NAME.val);
    }

    public String getUserPostalCode() {
        return this.userInfo.get(AuthzConstants.PROFILE_KEY.POSTAL_CODE.val);
    }

    public int hashCode() {
        Map<String, String> map = this.userInfo;
        return 31 + (map == null ? 0 : map.hashCode());
    }

    public String toString() {
        return String.format("%s={userInfo=%s}", super.toString(), this.userInfo);
    }

    static void fetch(Context context, InternalAuthManager internalAuthManager, final Listener<User, AuthError> listener) {
        String str = LOG_TAG;
        MAPLog.i(str, context.getPackageName() + " calling fetch");
        Bundle bundle = new Bundle();
        bundle.putBoolean(LWAConstants.PROFILE_BUNDLE_KEY.FAIL_ON_INSUFFICIENT_SCOPE.val, true);
        internalAuthManager.getProfile(context, bundle, new APIListener() { // from class: com.amazon.identity.auth.device.api.authorization.User.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                Listener.this.onError(authError);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle2) {
                Listener.this.onSuccess(User.userFromBundle(bundle2.getBundle(AuthzConstants.BUNDLE_KEY.PROFILE.val)));
            }
        });
    }
}
