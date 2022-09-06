package com.amazon.deecomms.common.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import com.google.common.base.Strings;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public final class ContactUtils {
    private static final String JP_DISPLAY_NAME_FORMAT = "{1} {0}";

    private ContactUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00db, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String determineContactDisplayName(@androidx.annotation.Nullable com.amazon.deecomms.contacts.model.FullContactName r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.common.util.ContactUtils.determineContactDisplayName(com.amazon.deecomms.contacts.model.FullContactName, boolean):java.lang.String");
    }

    @NonNull
    public static String getAvatarDisplayString(@Nullable ContactName contactName) {
        char c = Constants.DEFAULT_IMAGE_CHAR;
        if (contactName != null) {
            if (contactName.getFirstName() != null) {
                c = contactName.getFirstName().charAt(0);
            } else if (contactName.getLastName() != null) {
                c = contactName.getLastName().charAt(0);
            }
        }
        return String.valueOf(c);
    }

    @NonNull
    public static String getFullName(@Nullable FullContactName fullContactName) {
        return determineContactDisplayName(fullContactName, true);
    }

    @NonNull
    public static String getPartialName(@Nullable ContactName contactName) {
        String stringFromResource = Utils.getStringFromResource(R.string.unknown_contact);
        if (contactName != null) {
            stringFromResource = contactName.getFirstName();
            if (stringFromResource == null || stringFromResource.trim().isEmpty()) {
                stringFromResource = contactName.getLastName();
            }
            if (stringFromResource == null || TextUtils.isEmpty(stringFromResource)) {
                stringFromResource = contactName.getNickName();
            }
            if (stringFromResource == null || TextUtils.isEmpty(stringFromResource)) {
                stringFromResource = contactName.getSourceNickName();
            }
        }
        return TextUtils.isEmpty(stringFromResource) ? Utils.getStringFromResource(R.string.unknown_contact) : stringFromResource;
    }

    public static boolean isCommsIdForPstnAccount(@NonNull String str) {
        try {
            return CommunicableEntity.ENTITY_ID_PROVIDER_PSTN.equals(CommunicableEntity.fromCommsID(str).getEntityIDProvider());
        } catch (MalformedCommsIDException | IllegalArgumentException unused) {
            return false;
        }
    }

    @NonNull
    public static String getFullName(@Nullable ContactName contactName) {
        String stringFromResource = Utils.getStringFromResource(R.string.unknown_contact);
        if (contactName != null) {
            if (contactName.getFirstName() != null && contactName.getLastName() != null) {
                stringFromResource = MessageFormat.format("JP".equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false)) ? JP_DISPLAY_NAME_FORMAT : Utils.getStringFromResource(R.string.full_name_format), contactName.getFirstName(), contactName.getLastName());
            } else {
                stringFromResource = getPartialName(contactName);
            }
        }
        return TextUtils.isEmpty(stringFromResource) ? Utils.getStringFromResource(R.string.unknown_contact) : stringFromResource;
    }

    @NonNull
    public static String getAvatarDisplayString(@Nullable String str) {
        return String.valueOf(!Strings.isNullOrEmpty(str) ? str.charAt(0) : Constants.DEFAULT_IMAGE_CHAR);
    }

    @NonNull
    public static String getPartialName(@Nullable FullContactName fullContactName) {
        return determineContactDisplayName(fullContactName, false);
    }
}
