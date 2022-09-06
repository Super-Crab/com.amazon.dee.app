package com.amazon.deecomms.calling.initiation;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
/* loaded from: classes12.dex */
public class LegacyInitiationLogic extends InitiationLogic {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, LegacyInitiationLogic.class);
    private final Activity activity;
    private final CallHelper callHelper;
    private final String callInitScreen;
    private final CallInitiator callInitiator;

    public LegacyInitiationLogic(CommsIdentityManager commsIdentityManager, CallInitiator callInitiator, @NonNull Context context, Activity activity, CallingFacade callingFacade, CallHelper callHelper, String str, String str2) {
        super(context, commsIdentityManager, activity, callingFacade, callHelper, str, str2);
        this.callInitiator = callInitiator;
        this.activity = activity;
        this.callHelper = callHelper;
        this.callInitScreen = str2;
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateAudioCall(String str, String str2) {
        LOG.i("Legacy Audio call requested");
        this.callInitiator.makeAudioCall(this.activity, this.callHelper, str, str2, this.callInitScreen);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateContactDropIn(String str, String str2) {
        LOG.i("Legacy Contact Drop-in call requested");
        this.callInitiator.makeDropInCall(this.activity, this.callHelper, str, str2, this.callInitScreen, null, true);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateDeviceDropIn(String str, String str2, String str3, boolean z, String str4) {
        LOG.i("Legacy Device Drop-in call requested");
        initiateTargetedDropIn(str, str4, str2, str3, z, true);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateDeviceTargetedCall(String str, String str2, String str3, boolean z) {
        LOG.i("Legacy Device call requested");
        this.callInitiator.makeDeviceCall(this.activity, this.callHelper, str, str2, this.callInitScreen, str3, z);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateTargetedDropIn(String str, String str2, String str3, String str4, boolean z, boolean z2) {
        LOG.i("Legacy Targeted Drop-in call requested");
        this.callHelper.withRecipientID(str).withDisplayTitleName(str4).withDropInCall(true).withShouldInitiateDropIn(z2).withVideoCall(z).withDeviceGruu(str3).withPageSourceName(str2).withCallInitScreenName(this.callInitScreen).withCallProvider(CallProvider.A2A).withAlertSource(AlertSource.newClassSource(LegacyInitiationLogic.class.getName())).withNDTCall(true).makeACall(this.activity);
    }

    @Override // com.amazon.deecomms.calling.initiation.InitiationLogic, com.amazon.deecomms.calling.initiation.InitiationLogicContract
    public void initiateVideoCall(String str, String str2) {
        LOG.i("Legacy Video call requested");
        this.callInitiator.makeVideoCall(this.activity, this.callHelper, str, str2, this.callInitScreen);
    }
}
