package com.amazon.alexa.biloba.utils;

import android.text.TextUtils;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes6.dex */
public final class CareActorUtil {
    private static final String SPACE = " ";
    private static final String TAG = "CareActorUtil";

    private CareActorUtil() {
    }

    public static String getDisplayName(CareActor careActor) {
        return careActor == null ? "" : !TextUtils.isEmpty(careActor.getNickName()) ? String.format("%1$s", careActor.getNickName()) : !TextUtils.isEmpty(careActor.getFirstName()) ? String.format("%1$s", careActor.getFirstName()) : !TextUtils.isEmpty(careActor.getLastName()) ? String.format("%1$s", careActor.getLastName()) : "";
    }

    public static String getEnclosedNickName(CareActor careActor) {
        return (careActor != null && !TextUtils.isEmpty(careActor.getNickName())) ? String.format("(%1$s)", careActor.getNickName()) : "";
    }

    public static String getFullName(CareActor careActor) {
        StringBuilder sb = new StringBuilder();
        if (careActor == null) {
            return sb.toString();
        }
        if (!AndroidUtils.isEmpty(careActor.getFirstName())) {
            sb.append(careActor.getFirstName());
            sb.append(" ");
        }
        if (!AndroidUtils.isEmpty(careActor.getLastName())) {
            sb.append(careActor.getLastName());
        }
        return sb.toString().trim();
    }

    public static String getSlicedContactId(CareActor careActor) {
        if (careActor != null && careActor.getContactId() != null) {
            String[] split = careActor.getContactId().split(ArcusConfig.PATH_SEPARATOR);
            return split[split.length - 1];
        }
        LogUtils.i(TAG, "ContactId is null returning empty string");
        return "";
    }

    public static boolean isCommsEnabled(CareActor careActor) {
        if (careActor == null) {
            return false;
        }
        if (careActor.getIsCommsProvisioned() != Boolean.TRUE) {
            return "ENABLED".equals(careActor.getCommsStatus());
        }
        return true;
    }
}
