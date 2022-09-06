package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.contacts.util.ContactNameHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public final class CallViewUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallViewUtils.class);

    private CallViewUtils() {
    }

    public static <T> void displayNameAndStatus(@Nullable T t, @NonNull TextView textView, @NonNull TextView textView2) {
        Context contextFromContainer = getContextFromContainer(t);
        Bundle bundleFromContainer = getBundleFromContainer(t);
        if (bundleFromContainer != null && contextFromContainer != null) {
            textView.setText(getRemoteParticipantDisplayName(bundleFromContainer, contextFromContainer));
            textView.setVisibility(0);
            textView2.setText((CharSequence) null);
            textView2.setVisibility(8);
            String phoneNumberType = getPhoneNumberType(contextFromContainer, (ContactPhoneNumber) bundleFromContainer.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER));
            if (!CallType.PSTN.equals(CallType.fromBundle(bundleFromContainer, Constants.CALL_TYPE)) || TextUtils.isEmpty(phoneNumberType)) {
                return;
            }
            textView2.setText(phoneNumberType);
            textView2.setVisibility(0);
            return;
        }
        LOG.e("displayNameAndStatus: invalid context or extras/arguments");
    }

    @Nullable
    static <T> Bundle getBundleFromContainer(@Nullable T t) {
        if (t instanceof Bundle) {
            return (Bundle) t;
        }
        if (t instanceof Activity) {
            Intent intent = ((Activity) t).getIntent();
            if (intent != null) {
                return intent.getExtras();
            }
        } else if (t instanceof Fragment) {
            return ((Fragment) t).getArguments();
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("getBundleFromContainer: invalid container: " + t);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> String getCallStatus(@Nullable Bundle bundle, @NonNull Context context) {
        if (bundle != null && context != null) {
            String phoneNumberType = getPhoneNumberType(context, (ContactPhoneNumber) bundle.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER));
            return (CallType.PSTN != CallType.fromBundle(bundle, Constants.CALL_TYPE) || TextUtils.isEmpty(phoneNumberType)) ? "" : phoneNumberType;
        }
        LOG.e("callStatus: invalid context or extras/arguments");
        return "";
    }

    @Nullable
    static <T> Context getContextFromContainer(@Nullable T t) {
        if (t instanceof Context) {
            return (Context) t;
        }
        if (t instanceof Fragment) {
            return ((Fragment) t).getContext();
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("getContextFromContainer: invalid container: " + t);
        return null;
    }

    public static String getPhoneNumberType(@NonNull Context context, @Nullable ContactPhoneNumber contactPhoneNumber) {
        if (contactPhoneNumber == null) {
            return null;
        }
        PhoneNumberType type = contactPhoneNumber.getType();
        String rawType = contactPhoneNumber.getRawType();
        if (type == PhoneNumberType.Custom && !TextUtils.isEmpty(rawType)) {
            return rawType;
        }
        if (type == null) {
            return null;
        }
        return context.getString(type.getDisplayResId());
    }

    public static String getRemoteDisplayName(@NonNull SipClientState sipClientState, @NonNull boolean z, @NonNull Context context) {
        String remoteParticipantName = sipClientState.getRemoteParticipantName();
        String remoteParticipantId = sipClientState.getRemoteParticipantId();
        if (StringUtils.isEmpty(remoteParticipantName) && Utils.isRegisteredCommsId(remoteParticipantId)) {
            remoteParticipantName = ContactUtils.getFullName(ContactNameHelper.getActiveContactName());
        }
        GeneratedOutlineSupport.outline5("Group call : ", z, LOG);
        String string = context.getString(R.string.group_calling_caller_name_identifier);
        if (z && remoteParticipantName != null && !remoteParticipantName.startsWith(string)) {
            remoteParticipantName = string + " " + remoteParticipantName;
        }
        if (TextUtils.isEmpty(remoteParticipantName)) {
            LOG.w("getRemoteDisplayName: could not obtain display name");
        }
        return remoteParticipantName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static <T> String getRemoteParticipantDisplayName(@Nullable Bundle bundle, @NonNull Context context) {
        ContactPhoneNumber contactPhoneNumber;
        String str = null;
        if (bundle == null) {
            LOG.w("getRemoteParticipantDisplayName: invalid extras");
            return null;
        }
        String string = bundle.getString(Constants.REMOTE_PARTICIPANT_NAME);
        if (!TextUtils.isEmpty(string)) {
            str = string;
        } else if (Utils.isRegisteredCommsId(bundle.getString("COMMS_ID"))) {
            str = ContactUtils.getFullName(ContactNameHelper.getActiveContactName());
        }
        CallType fromBundle = CallType.fromBundle(bundle, Constants.CALL_TYPE);
        if (TextUtils.isEmpty(str) && fromBundle == CallType.PSTN && (contactPhoneNumber = (ContactPhoneNumber) bundle.getParcelable(Constants.KEY_RECIPIENT_PHONE_NUMBER)) != null) {
            str = CoboUtils.formatPhoneNumberForDisplay(contactPhoneNumber.getPhoneNumber());
        }
        boolean z = bundle.getBoolean(Constants.GROUP_CALL);
        GeneratedOutlineSupport.outline5("Group call : ", z, LOG);
        String string2 = context.getString(R.string.group_calling_caller_name_identifier);
        if (z && str != null && !str.startsWith(string2)) {
            str = string2 + " " + str;
        }
        if (TextUtils.isEmpty(str)) {
            LOG.w("getRemoteParticipantDisplayName: could not obtain display name");
        }
        return str;
    }
}
