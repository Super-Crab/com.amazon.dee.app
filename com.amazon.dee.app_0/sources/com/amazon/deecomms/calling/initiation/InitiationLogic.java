package com.amazon.deecomms.calling.initiation;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequestBuilder;
import com.amazon.deecomms.calling.ui.COBOWarningActivity;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.external.skype.SkypeClient;
import com.amazon.deecomms.util.Consumer;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class InitiationLogic implements InitiationLogicContract {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, InitiationLogic.class);
    @Nullable
    private final Activity activity;
    @NonNull
    protected final CallHelper callHelper;
    @NonNull
    private final String callInitScreen;
    @NonNull
    private final String callMetricKey;
    @NonNull
    private final CallingFacade callingFacade;
    @NonNull
    private final CommsIdentityManager commsIdentityManager;
    @NonNull
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitiationLogic(@NonNull Context context, @NonNull CommsIdentityManager commsIdentityManager, @Nullable Activity activity, @NonNull CallingFacade callingFacade, @NonNull CallHelper callHelper, @NonNull String str, @NonNull String str2) {
        this.context = context;
        this.commsIdentityManager = commsIdentityManager;
        this.activity = activity;
        this.callingFacade = callingFacade;
        this.callHelper = callHelper;
        this.callMetricKey = str;
        this.callInitScreen = str2;
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateAudioCall(String str, String str2) {
        LOG.i("Audio call requested");
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO")).withContactTarget(str, false).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_AUDIO_CALL);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateCoboCall(String str, String str2, ContactPhoneNumber contactPhoneNumber) {
        if (((Boolean) SharedPreferencesUtils.getCacheValue(this.context, Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, false)).booleanValue()) {
            lambda$initiateCoboCallFromActivity$1$InitiationLogic(str, str2, contactPhoneNumber);
            return;
        }
        SharedPreferencesUtils.persistCacheValues(this.context, Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, true);
        COBOWarningActivity.show(this.context, str, str2, contactPhoneNumber, this.callMetricKey, this.callInitScreen);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    @Deprecated
    public void initiateCoboCallFromActivity(final String str, final String str2, final ContactPhoneNumber contactPhoneNumber) {
        LOG.i("COBO call requested");
        new Consumer() { // from class: com.amazon.deecomms.calling.initiation.-$$Lambda$InitiationLogic$RoYEv-jw69uZya4LLcrQ7fA-26c
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                InitiationLogic.this.lambda$initiateCoboCallFromActivity$0$InitiationLogic((Runnable) obj);
            }
        }.accept(new Runnable() { // from class: com.amazon.deecomms.calling.initiation.-$$Lambda$InitiationLogic$Bes1ENszOu5jAXO3RoZg3aTUny4
            @Override // java.lang.Runnable
            public final void run() {
                InitiationLogic.this.lambda$initiateCoboCallFromActivity$1$InitiationLogic(str, str2, contactPhoneNumber);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    /* renamed from: initiateCoboCallWithoutWarning */
    public void lambda$initiateCoboCallFromActivity$1$InitiationLogic(String str, String str2, ContactPhoneNumber contactPhoneNumber) {
        LOG.i(" Placing an PSTN call ");
        if (str == null) {
            str = "";
        }
        this.callHelper.withRecipientID(str).withDisplayTitleName(str2).withDeviceGruu(null).withPageSourceName(MetricKeys.SCREEN_NAME_CONTACT_DETAILS).withAlertSource(AlertSource.newClassSource(CallInitiator.class.getName())).withNDTCall(false).withCallInitScreenName(this.callInitScreen).withVideoCall(false).withCallProvider(CallProvider.COBO).withRecipientPhoneNumber(contactPhoneNumber).makeACall(this.activity);
        MetricsHelper.recordSingleOccurrenceClickstream(this.callMetricKey);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateContactDropIn(String str, String str2) {
        LOG.i("Contact Drop-in requested");
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO", "VIDEO")).withContactTarget(str, true).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_DROP_IN);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateDeviceDropIn(String str, String str2, String str3, boolean z, String str4) {
        List asList;
        LOG.i("Device Drop-in call requested");
        if (z) {
            asList = Arrays.asList("AUDIO", "VIDEO");
        } else {
            asList = Arrays.asList("AUDIO");
        }
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(asList).withDeviceTarget(str2, true).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_DROP_IN);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateDeviceTargetedCall(String str, String str2, String str3, boolean z) {
        List asList;
        LOG.i("Device call requested");
        if (z) {
            asList = Arrays.asList("AUDIO", "VIDEO");
        } else {
            asList = Arrays.asList("AUDIO");
        }
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(asList).withDeviceTarget(str3, false).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_DROP_IN);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateGroupCall(String str) {
        LOG.i("Group call requested");
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO", "VIDEO")).withGroupTarget(Constants.GROUP_CONTACT, str, false).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_GROUP_CALL);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateGroupDropIn(String str) {
        LOG.i("Chapman call requested");
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO", "VIDEO")).withGroupTarget(Constants.CHAPMAN_GROUP_IDENTITY_TYPE, str, true).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_CHAPMAN_CALL);
        this.callingFacade.beginCall(build);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateSkypeCall(String str) {
        LOG.i("Skype call requested");
        SkypeClient skypeClient = new SkypeClient();
        skypeClient.startSkypeActivity(this.context, skypeClient.createSkypeUri("call", str));
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateTargetedDropIn(String str, String str2, String str3, String str4, boolean z, boolean z2) {
        LOG.i("Targeted Drop-in call requested");
        String commsId = this.commsIdentityManager.getCommsId("InitiationLogic.initiateTargetedDropIn", false);
        if (commsId == null) {
            LOG.e("No commsId found for current user. Dropping the drop in request");
        } else if (commsId.equals(str)) {
            initiateDeviceDropIn(str, str3, str4, z, str2);
        } else {
            initiateContactDropIn(str, str4);
        }
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateVideoCall(String str, String str2) {
        LOG.i("Video call requested");
        InitiateOutboundCallRequest build = new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO", "VIDEO")).withContactTarget(str, false).build();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.INITIATE_VIDEO_CALL);
        this.callingFacade.beginCall(build);
    }

    public /* synthetic */ void lambda$initiateCoboCallFromActivity$0$InitiationLogic(Runnable runnable) {
        CallUtils.showCOBOWarningAlert(this.context, runnable);
    }
}
