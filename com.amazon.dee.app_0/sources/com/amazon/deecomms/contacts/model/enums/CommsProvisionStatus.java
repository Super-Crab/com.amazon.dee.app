package com.amazon.deecomms.contacts.model.enums;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public enum CommsProvisionStatus {
    UNKNOWN,
    AUTO_PROVISIONED,
    PROVISIONED,
    DEPROVISIONED;
    
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsProvisionStatus.class);

    private static boolean compare(@Nullable String str, @NonNull CommsProvisionStatus commsProvisionStatus) {
        if (str != null) {
            try {
                return valueOf(str) == commsProvisionStatus;
            } catch (IllegalArgumentException e) {
                CommsLogger commsLogger = LOG;
                commsLogger.w("Missing Provision Status value: " + str, e);
                return false;
            }
        }
        return false;
    }

    public static boolean isProvisioned(@Nullable String str) {
        return compare(str, PROVISIONED);
    }
}
