package com.amazon.deecomms.calling.util;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class CallInitiator {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallInitiator.class);
    @NonNull
    private final String mCallMetricKey;

    public CallInitiator(@NonNull String str) {
        this.mCallMetricKey = str;
    }

    private static boolean prepareCallHelper(@NonNull CallHelper callHelper, @Nullable String str, @Nullable String str2, @NonNull String str3) {
        callHelper.withRecipientID(str).withDisplayTitleName(str2).withDeviceGruu(null).withPageSourceName(MetricKeys.SCREEN_NAME_CONTACT_DETAILS).withAlertSource(AlertSource.newClassSource(CallInitiator.class.getName())).withNDTCall(false).withCallInitScreenName(str3);
        return true;
    }

    public /* synthetic */ void lambda$makePstnCall$0$CallInitiator(String str, CallHelper callHelper, String str2, String str3, ContactPhoneNumber contactPhoneNumber, Activity activity) {
        LOG.i(" Placing an PSTN call ");
        if (str == null) {
            str = "";
        }
        prepareCallHelper(callHelper, str, str2, str3);
        callHelper.withVideoCall(false).withCallProvider(CallProvider.COBO).withRecipientPhoneNumber(contactPhoneNumber).makeACall(activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.mCallMetricKey);
    }

    public void makeAudioCall(@NonNull Activity activity, @NonNull CallHelper callHelper, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        LOG.i(" Placing a video call without dropIn ");
        prepareCallHelper(callHelper, str, str2, str3);
        callHelper.withVideoCall(false).withCallProvider(CallProvider.A2A).makeACall(activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.mCallMetricKey);
    }

    public void makeDeviceCall(@NonNull Activity activity, @NonNull CallHelper callHelper, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull boolean z) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Placing a device ");
        outline1.append(z ? "video" : "audio");
        outline1.append("call");
        commsLogger.i(outline1.toString());
        prepareCallHelper(callHelper, str, str2, str3);
        callHelper.withDeviceGruu(str4).withVideoCall(z).withCallProvider(CallProvider.A2A).makeACall(activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.mCallMetricKey);
    }

    public void makeDropInCall(@NonNull Activity activity, @NonNull CallHelper callHelper, @NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, boolean z) {
        LOG.i(" Placing a video call with dropIn ");
        prepareCallHelper(callHelper, str, str2, str3);
        callHelper.withDeviceGruu(str4).withNDTCall(str4 != null && !str4.isEmpty()).withVideoCall(z).withShouldInitiateDropIn(true).withDropInCall(true).withCallProvider(CallProvider.A2A).makeACall(activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.mCallMetricKey);
    }

    public void makePstnCall(@NonNull final Activity activity, @NonNull final CallHelper callHelper, @Nullable final String str, @NonNull final String str2, @NonNull final ContactPhoneNumber contactPhoneNumber, @NonNull Consumer<Runnable> consumer, @NonNull final String str3) {
        consumer.accept(new Runnable() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$CallInitiator$aWH5sr2NPo5P_12ObqHksrSir9o
            @Override // java.lang.Runnable
            public final void run() {
                CallInitiator.this.lambda$makePstnCall$0$CallInitiator(str, callHelper, str2, str3, contactPhoneNumber, activity);
            }
        });
    }

    public void makeVideoCall(@NonNull Activity activity, @NonNull CallHelper callHelper, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        LOG.i(" Placing a video call without dropIn ");
        prepareCallHelper(callHelper, str, str2, str3);
        callHelper.withVideoCall(true).withCallProvider(CallProvider.A2A).makeACall(activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.mCallMetricKey);
    }
}
