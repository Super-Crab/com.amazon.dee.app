package com.amazon.deecomms.common.util;

import android.text.TextUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
/* loaded from: classes12.dex */
public final class IdentityValidator {
    private IdentityValidator() {
    }

    public static boolean isMyHousehold(String str) {
        return TextUtils.equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("IdentityValidator", false), str);
    }

    public static boolean isSelf(String str) {
        return TextUtils.equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("IdentityValidator", false), str);
    }

    public static boolean isValidCommsId(String str) {
        try {
            CommunicableEntity.fromCommsID(str);
            return true;
        } catch (MalformedCommsIDException | IllegalArgumentException unused) {
            return false;
        }
    }
}
