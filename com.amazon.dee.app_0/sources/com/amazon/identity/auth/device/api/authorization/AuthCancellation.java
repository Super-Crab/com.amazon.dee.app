package com.amazon.identity.auth.device.api.authorization;

import android.os.Bundle;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
/* loaded from: classes12.dex */
public final class AuthCancellation {
    private final Cause cause;
    private final String description;

    /* loaded from: classes12.dex */
    public enum Cause {
        FAILED_AUTHENTICATION;

        static Cause fromCode(int i) {
            return FAILED_AUTHENTICATION;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthCancellation(Bundle bundle) {
        this(Cause.fromCode(bundle.getInt(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val)), bundle.getString(AuthzConstants.BUNDLE_KEY.ON_CANCEL_DESCRIPTION.val));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthCancellation.class != obj.getClass()) {
            return false;
        }
        AuthCancellation authCancellation = (AuthCancellation) obj;
        if (this.cause != authCancellation.cause) {
            return false;
        }
        String str = this.description;
        if (str == null) {
            if (authCancellation.description != null) {
                return false;
            }
        } else if (!str.equals(authCancellation.description)) {
            return false;
        }
        return true;
    }

    public Cause getCause() {
        return this.cause;
    }

    public String getDescription() {
        return this.description;
    }

    public int hashCode() {
        Cause cause = this.cause;
        int i = 0;
        int hashCode = ((cause == null ? 0 : cause.hashCode()) + 31) * 31;
        String str = this.description;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return String.format("%s {cause='%s', description='%s'}", super.toString(), this.cause.toString(), this.description);
    }

    public AuthCancellation(Cause cause, String str) {
        this.cause = cause;
        this.description = str;
    }
}
