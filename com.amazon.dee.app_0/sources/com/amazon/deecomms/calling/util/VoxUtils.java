package com.amazon.deecomms.calling.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.model.VoxCallInfo;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.rx.SimpleSubscriber;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class VoxUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, VoxUtils.class);
    @Inject
    protected CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    PCCContextProvider pccContextProvider;

    public VoxUtils() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    public void beginCall(@NonNull final VoxCallInfo voxCallInfo) {
        if (TextUtils.isEmpty(this.commsIdentityManager.getCommsId("VoxUtils.beginCall", false))) {
            LOG.d("unable to make vox call - user is not registered, no comms id");
            return;
        }
        LOG.i("We are going to make a Vox call");
        Observable.defer(new Func0() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$VoxUtils$8lSEbFENvgSkuYfc9bycQGtwCTc
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return VoxUtils.this.lambda$beginCall$0$VoxUtils(voxCallInfo);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber) new SimpleSubscriber<String>() { // from class: com.amazon.deecomms.calling.util.VoxUtils.1
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(String str) {
                try {
                    VoxUtils.this.makeCall(str, voxCallInfo);
                } finally {
                    unsubscribe();
                }
            }
        });
    }

    @VisibleForTesting
    public String getContactName(@NonNull VoxCallInfo voxCallInfo) {
        String displayName = voxCallInfo.getDisplayName();
        if (TextUtils.isEmpty(voxCallInfo.getCalleeCommsID())) {
            return voxCallInfo.getDisplayName();
        }
        if (!displayName.equals(Constants.VOX_CALL_UNDEFINED_NAME)) {
            return displayName;
        }
        ThreadUtils.checkNotMainThread();
        ContactEntry contactEntry = new GetOrCreateContact("VoxUtils.getContactName").getContactEntry(voxCallInfo.getCalleeCommsID(), true);
        return contactEntry != null ? ContactUtils.getFullName(contactEntry.getFullContactName()) : displayName;
    }

    public /* synthetic */ Observable lambda$beginCall$0$VoxUtils(VoxCallInfo voxCallInfo) {
        return Observable.just(getContactName(voxCallInfo));
    }

    @VisibleForTesting
    public void makeCall(@NonNull String str, @NonNull VoxCallInfo voxCallInfo) {
        CallHelper callHelper = CommsDaggerWrapper.getComponent().getCallHelper();
        String fromString = CallProvider.fromString(voxCallInfo.getProvider());
        if ("".equalsIgnoreCase(fromString)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Unknown call provider from VOX: ");
            outline1.append(voxCallInfo.getProvider());
            commsLogger.w(outline1.toString());
        }
        if (CallProvider.COBO.equalsIgnoreCase(fromString)) {
            boolean areAccessoriesConnected = Utils.areAccessoriesConnected();
            boolean isCommsNativeDefaulted = DeviceUtils.isCommsNativeDefaulted(this.capabilitiesManager);
            if (areAccessoriesConnected || isCommsNativeDefaulted) {
                String str2 = areAccessoriesConnected ? Constants.ACCESSORIES : "AMPD";
                LOG.e("Received: " + fromString + " when PCC session is available. Recording metric.");
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.CPCC_CALLING_COBO_INSTEAD_OF_PCC, str2 + "." + fromString);
            }
        }
        boolean z = false;
        if (CallProvider.COBO.equalsIgnoreCase(fromString)) {
            callHelper.withRecipientPhoneNumber(CoboUtils.getPhoneNumberFromPstnSipUri(voxCallInfo.getSipUri()), voxCallInfo.getCalleeNumberType());
        } else {
            if (voxCallInfo.getCalleeCommsID().equals(this.commsIdentityManager.getHomeGroupId("VoxUtils.makeCall", false)) && voxCallInfo.getDropIn().booleanValue()) {
                z = true;
            }
            callHelper.withDeviceGruu(voxCallInfo.getSipUri());
        }
        callHelper.withCallProvider(fromString).withRecipientID(voxCallInfo.getCalleeCommsID()).withDropInCall(voxCallInfo.getDropIn().booleanValue()).withAlertSource(AlertSource.newClassSource(VoxUtils.class.getName())).withPageSourceName(MetricKeys.SCREEN_NAME_CONVO_LIST).withCallInitScreenName(Constants.Telemetry.CallInitScreenNames.VOX).withVideoCall(voxCallInfo.getVideo().booleanValue()).withDisplayTitleName(str).withNDTCall(z).withCallID(voxCallInfo.getCallID()).withSRTPKey(voxCallInfo.getSrtpKey()).withVoxInitiated(true).setEnhancedProcessingState(voxCallInfo.getEnhancedProcessingState());
        callHelper.makeACall(null);
    }
}
