package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
@Deprecated
/* loaded from: classes12.dex */
public final class MAPWebViewEventHelper {
    @FireOsSdk
    public static final String AUTHENTICATION_EVENT = "authentication_event";
    @FireOsSdk
    public static final String AUTHENTICATION_ONLY_EVENT = "authentication_only_event";
    @FireOsSdk
    public static final String ERROR_EVENT = "error_event";
    @FireOsSdk
    public static final String ERROR_EVENT_VALUE = "error_event_value";
    @FireOsSdk
    public static final String KEY_ERRORS = "errors";
    @FireOsSdk
    public static final String KEY_EVENT_TYPE = "event_type";
    private final Bundle gF;

    @FireOsSdk
    public MAPWebViewEventHelper(Bundle bundle) {
        this.gF = bundle;
    }

    private boolean aH(String str) {
        Bundle bundle = this.gF;
        if (bundle == null) {
            return false;
        }
        return bundle.containsKey(str);
    }

    @FireOsSdk
    public boolean isAuthenticationEvent() {
        if (!aH("event_type")) {
            return false;
        }
        return this.gF.getString("event_type").equals(AUTHENTICATION_EVENT);
    }

    @FireOsSdk
    public boolean isErrorEvent() {
        if (!aH("event_type")) {
            return false;
        }
        return this.gF.getString("event_type").equals(ERROR_EVENT);
    }
}
