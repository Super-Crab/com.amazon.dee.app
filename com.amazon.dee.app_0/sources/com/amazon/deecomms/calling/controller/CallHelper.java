package com.amazon.deecomms.calling.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.RealTimeTextMetrics;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.TargetDeviceModel;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetDevices;
import com.amazon.deecomms.common.network.acmsrecipes.GetTargetDevice;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.ContactCardInfo;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CallHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallHelper.class);
    private String callID;
    @Inject
    CallManager callManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private String mACMSRequestId;
    private AlertSource mAlertSource;
    private String mCallInitScreenName;
    private String mCalleeCommsId;
    private ContactPhoneNumber mCalleePhoneNumber;
    @Inject
    Context mContext;
    private String mDeviceGruu;
    private EnhancedProcessingState mEnhancedProcessingState;
    private boolean mIsDeviceOnline;
    private String mPageSource;
    private boolean mShouldInitiateDropIn;
    private long mStartTimeMs;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private String srtpKey;
    private String mDisplayTitleName = "";
    @NonNull
    private CallType mCallType = CallType.AUDIO;
    @NonNull
    private String mCallProvider = "";
    private String mLaunchFragmentKey = Constants.FRAGMENT_OUTGOING_CALL_KEY;

    /* renamed from: com.amazon.deecomms.calling.controller.CallHelper$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$enums$DropInAvailability = new int[DropInAvailability.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$DropInAvailability[DropInAvailability.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$DropInAvailability[DropInAvailability.ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$DropInAvailability[DropInAvailability.HOME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class CallParams {
        String calleeSipUri;
        String callerCommsId;
        Integer errorCode;
        String launchFragmentKey;
        String recipientId;

        CallParams() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class FetchDropInInfo extends AsyncTask<Void, Void, CallParams> {
        @Nullable
        private final Activity activity;
        private boolean canDropInOnContact;

        public FetchDropInInfo(@Nullable Activity activity) {
            this.activity = activity;
        }

        private SetupCallHelper.MetadataBuilder prepareCallInitData() {
            return SetupCallHelper.MetadataBuilder.newBuilder().withCallType(CallHelper.this.mCallType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.SipCallPreparation);
        }

        @VisibleForTesting
        boolean canDropInOnMyDevice(@Nullable DeviceModel deviceModel) {
            int ordinal;
            if (deviceModel == null || deviceModel.getDeviceStatus().getDeviceCommsAvailability() == DeviceCommsAvailability.OFF || (ordinal = deviceModel.getDeviceStatus().getDeviceDropInAvailability().ordinal()) == 0) {
                return false;
            }
            return ordinal == 1 || ordinal == 2;
        }

        @Nullable
        @VisibleForTesting
        protected DeviceModel getDeviceModel(@NonNull String str, @NonNull String str2) {
            List<DeviceModel> devices = getDevices(str);
            if (devices != null && !devices.isEmpty()) {
                for (DeviceModel deviceModel : devices) {
                    if (str2.equals(deviceModel.getDeviceGruu())) {
                        return deviceModel;
                    }
                }
            }
            return null;
        }

        @Nullable
        @VisibleForTesting
        protected List<DeviceModel> getDevices(@NonNull String str) {
            try {
                GetDevices devices = CallHelper.this.getDevices();
                GetDevicesResponse devices2 = devices.getDevices(str);
                CallHelper.this.mACMSRequestId = devices.getRequestId();
                if (devices2 == null) {
                    CallHelper.LOG.w("Invalid GetDevices response");
                    return null;
                }
                return devices2.getDeviceModels();
            } catch (ServiceException | InterruptedException e) {
                CallHelper.LOG.w("Error from GetDevices", e);
                return null;
            }
        }

        @Nullable
        @VisibleForTesting
        CommunicableEntity getEntity(@NonNull String str) {
            try {
                return CommunicableEntity.fromCommsID(str);
            } catch (MalformedCommsIDException unused) {
                CallHelper.LOG.w("Could not determine entity type of callee commsId");
                return null;
            }
        }

        @Nullable
        @VisibleForTesting
        String getParentHomeGroupId(@NonNull String str) {
            ContactEntry fetchContactEntryForCommId = ContactsProviderUtils.fetchContactEntryForCommId(CallHelper.this.mContext, str);
            if (fetchContactEntryForCommId == null) {
                CallHelper.LOG.w("Could not obtain contact information for callee commsId");
                return null;
            }
            ContactCardInfo createContactCardInfo = ContactCardInfo.createContactCardInfo(fetchContactEntryForCommId.getId());
            if (createContactCardInfo == null) {
                CallHelper.LOG.w("Could not obtain home group for callee commsId");
                return null;
            }
            return createContactCardInfo.getParentHomeGroupId();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            String commsID;
            super.onPreExecute();
            CallHelper.LOG.i(" Preparing FetchDropInInfo... ");
            CommunicableEntity entity = getEntity(CallHelper.this.mCalleeCommsId);
            if (entity != null) {
                if (entity.isPerson()) {
                    commsID = getParentHomeGroupId(entity.getCommsID());
                } else {
                    commsID = entity.getCommsID();
                }
                this.canDropInOnContact = !TextUtils.isEmpty(commsID) && ContactsProviderUtils.canIDropInOnHomeGroup(commsID);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @Nullable
        public CallParams doInBackground(Void... voidArr) {
            CallHelper.LOG.i(" FetchDropInInfo... ");
            SetupCallHelper.MetadataBuilder prepareCallInitData = prepareCallInitData();
            CallParams prepareCallParams = CallHelper.this.prepareCallParams();
            if (!this.canDropInOnContact) {
                CallHelper.LOG.w("Stale drop-in permissions");
                prepareCallParams.errorCode = Integer.valueOf(SipStatusCode.FORBIDDEN.getCode());
                SetupCallHelper.recordInitiationMetrics(CallHelper.this.sipClientState.getCallId(), CallHelper.this.sipClientState.getCspId(), prepareCallParams.errorCode.intValue(), prepareCallInitData.withReason("Stale Drop-In Permissions"));
                CallHelper.this.setupNotAvailableScreen(prepareCallParams, Utils.getStringFromResource(R.string.callee_unavailable));
                return prepareCallParams;
            } else if (CallHelper.this.canInitiateDropInCall()) {
                return prepareCallParams;
            } else {
                if (CallHelper.this.mCallType.isDeviceTargeted()) {
                    if (!TextUtils.isEmpty(CallHelper.this.mDeviceGruu)) {
                        prepareCallParams.recipientId = CallHelper.this.mDeviceGruu;
                        prepareCallParams.calleeSipUri = prepareCallParams.recipientId;
                        DeviceModel deviceModel = getDeviceModel(prepareCallParams.callerCommsId, CallHelper.this.mDeviceGruu);
                        if (deviceModel != null) {
                            CallUtils.resetEnhancedProcessingStateIfNeeded(deviceModel, null);
                        }
                        if (CallHelper.this.canIntercomMyHomegroup()) {
                            CallHelper.LOG.i("Intercom call, video stream has already been set.");
                        } else if (!canDropInOnMyDevice(deviceModel)) {
                            CommsLogger commsLogger = CallHelper.LOG;
                            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Cannot drop-in on named target device with Gruu: ");
                            outline1.append(CallHelper.LOG.sensitive(CallHelper.this.mDeviceGruu));
                            commsLogger.e(outline1.toString());
                            prepareCallParams.errorCode = Integer.valueOf(SipStatusCode.FORBIDDEN.getCode());
                            SetupCallHelper.recordInitiationMetrics(CallHelper.this.sipClientState.getCallId(), CallHelper.this.sipClientState.getCspId(), prepareCallParams.errorCode.intValue(), prepareCallInitData.withReason("Targeted device doesn't support drop-in"));
                            CallHelper.this.setupNotAvailableScreen(prepareCallParams, Utils.getStringFromResource(R.string.callee_unavailable));
                            return prepareCallParams;
                        } else {
                            CallHelper.this.withVideoCall(deviceModel.getDeviceStatus() != null && deviceModel.getDeviceStatus().isVideoEnabled());
                            CommsLogger commsLogger2 = CallHelper.LOG;
                            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Drop-In on named target device with Gruu: ");
                            outline12.append(CallHelper.LOG.sensitive(prepareCallParams.recipientId));
                            commsLogger2.i(outline12.toString());
                        }
                    } else {
                        prepareCallInitData.withRequestId(CallHelper.this.mACMSRequestId);
                        if (CallHelper.this.mIsDeviceOnline) {
                            prepareCallInitData.withReason("getDevices returned no device GRUU, device is online");
                        } else {
                            prepareCallInitData.withReason("getDevices returned no device GRUU, device is offline");
                        }
                        SetupCallHelper.recordInitiationMetrics(CallHelper.this.sipClientState.getCallId(), CallHelper.this.sipClientState.getCspId(), SetupCallHelper.ResultType.EXPECTED, Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()), prepareCallInitData);
                        CallHelper.LOG.i("Empty Gruu in NDT, showing end call screen");
                        prepareCallParams.launchFragmentKey = Constants.FRAGMENT_END_CALL_KEY;
                        return prepareCallParams;
                    }
                } else {
                    GetTargetDevice getTargetDevice = new GetTargetDevice();
                    prepareCallInitData.withRequestId(getTargetDevice.getRequestId());
                    try {
                        TargetDeviceModel execute = getTargetDevice.execute(prepareCallParams.recipientId);
                        if (execute == null || execute.getTargetDevice() == null || TextUtils.isEmpty(execute.getTargetDevice().getDeviceGruu())) {
                            CallHelper.LOG.i("No devices available to drop-in");
                            SetupCallHelper.recordInitiationMetrics(CallHelper.this.sipClientState.getCallId(), CallHelper.this.sipClientState.getCspId(), SetupCallHelper.ResultType.EXPECTED, Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()), prepareCallInitData.withReason("getTargetDevice returned no device GRUU"));
                            prepareCallParams.launchFragmentKey = Constants.FRAGMENT_END_CALL_KEY;
                            return prepareCallParams;
                        }
                        CallHelper.this.mDeviceGruu = execute.getTargetDevice().getDeviceGruu();
                        prepareCallParams.recipientId = CallHelper.this.mDeviceGruu;
                        prepareCallParams.calleeSipUri = CallHelper.this.mDeviceGruu;
                        CallUtils.resetEnhancedProcessingStateIfNeeded(null, execute);
                    } catch (ServiceException e) {
                        CallHelper.LOG.e(" Error while retrieving targetDevice ", e);
                        String cspId = CallHelper.this.sipClientState.getCspId();
                        SetupCallHelper.ResultType resultType = SetupCallHelper.ResultType.EXPECTED;
                        Integer valueOf = Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode());
                        StringBuilder outline13 = GeneratedOutlineSupport.outline1("getTargetDevice failed with error: ");
                        outline13.append(e.getMessage());
                        SetupCallHelper.recordInitiationMetrics(null, cspId, resultType, valueOf, prepareCallInitData.withReason(outline13.toString()));
                        return prepareCallParams;
                    }
                }
                return prepareCallParams;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(CallParams callParams) {
            if (callParams == null) {
                CallHelper.LOG.e("Something went wrong while attempting to call");
                CallHelper.this.clearTopActivity();
                CallUtils.handleCallInitiationErrors();
                CallHelper.this.sipClientState.setCallType(CallType.NONE);
                CallHelper.this.sipClientState.setCallProvider("");
                Utils.showDialog(this.activity, R.string.error_title, R.string.generic_error_msg);
            } else if (callParams.errorCode == null) {
                CallHelper.this.mLaunchFragmentKey = callParams.launchFragmentKey;
                CallHelper.this.makeACallInternal();
            } else {
                CallHelper.this.sipClientState.setCallType(CallType.NONE);
                CallHelper.this.sipClientState.setCallProvider("");
                CommsLogger commsLogger = CallHelper.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("FetchDropInInfo error: ");
                outline1.append(callParams.errorCode);
                commsLogger.w(outline1.toString());
            }
        }
    }

    public CallHelper() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private boolean canPlaceCall() {
        return Utils.shouldAllowAlexaCall(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTopActivity() {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.CLEAR_CALL_ACTIVITY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fetchMetaDataAndPlaceTheCall */
    public void lambda$makeACall$0$CallHelper(@Nullable Activity activity) {
        if (this.mCallType.isDropIn()) {
            new FetchDropInInfo(activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            makeACallInternal();
        }
    }

    private String getACMSRequestId() {
        return this.mACMSRequestId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeACallInternal() {
        Intent intent = new Intent();
        intent.putExtra("COMMS_ID", this.mCalleeCommsId);
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, this.mDisplayTitleName);
        intent.putExtra(Constants.CALL_START_TIME, this.mStartTimeMs);
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, this.mLaunchFragmentKey);
        if ((Utils.areAccessoriesConnected() || Utils.isInDriveMode()) && this.mCallType.isFromVox()) {
            withVideoCall(false);
        }
        intent.putExtra(Constants.CALL_TYPE, this.mCallType.toString());
        intent.putExtra(Constants.CALL_PROVIDER, this.mCallProvider);
        intent.putExtra(Constants.KEY_RECIPIENT_PHONE_NUMBER, this.mCalleePhoneNumber);
        intent.putExtra(Constants.KEY_CALL_INITIATION_SCREEN_NAME, this.mCallInitScreenName);
        intent.putExtra(Constants.CALL_ID, this.callID);
        intent.addFlags(268435456);
        intent.putExtra(Constants.DEVICE_GRUU, this.mDeviceGruu);
        intent.setAction(Constants.SHOW_CALL_UI);
        if (Constants.FRAGMENT_END_CALL_KEY.equals(this.mLaunchFragmentKey)) {
            intent.putExtra(Constants.KEY_DROP_IN_NOT_AVAILABLE, true);
            intent.putExtra(Constants.CALL_END_STATUS, Utils.getStringFromResource(R.string.callee_unavailable));
            CallUtils.handleCallInitiationErrors();
        } else {
            intent.putExtra(Constants.SET_SIP_CLIENT_STATE, true);
        }
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    public boolean canInitiateDropInCall() {
        String str;
        return (!this.mShouldInitiateDropIn || (str = this.mDeviceGruu) == null || str.isEmpty() || this.mCallInitScreenName == null || this.mDisplayTitleName == null) ? false : true;
    }

    @VisibleForTesting
    boolean canIntercomMyHomegroup() {
        return this.sipClientState.isEnhancedProcessedCall();
    }

    @VisibleForTesting
    protected CallType getCallType() {
        return this.mCallType;
    }

    @VisibleForTesting
    protected GetDevices getDevices() {
        return new GetDevices();
    }

    public void handleCanNotPlaceCall(@Nullable Activity activity) {
        int i;
        SetupCallHelper.Source source;
        boolean isAnyActiveCallPresent = CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent();
        int i2 = R.string.call_in_progress_dialog_title;
        if (isAnyActiveCallPresent) {
            i = R.string.call_in_progress_dialog_message;
            source = SetupCallHelper.Source.ActiveCommsCall;
        } else {
            i = R.string.call_interrupt_phone_dialog_message;
            source = SetupCallHelper.Source.ActivePstnCall;
        }
        LOG.e("Cannot place a new call when already in another call");
        Utils.showDialog(activity, i2, i);
        SetupCallHelper.recordInitiationMetrics((String) null, this.sipClientState.getCspId(), SetupCallHelper.ResultType.CANCELLED, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(this.mCallType).withCallOrigin(Call.Side.Local).withSource(source).withReason("Existing call"));
        SetupCallHelper.recordInitiationMetrics((String) null, this.sipClientState.getCspId(), 412, SetupCallHelper.MetadataBuilder.newBuilder().withCallType(this.mCallType).withCallOrigin(Call.Side.Local).withSource(source).withReason("Existing call"));
        CallUtils.handleCallInitiationErrors();
    }

    public void makeACall(@Nullable final Activity activity) {
        SipClientState.CallState callState;
        SipClientState.CallState callState2 = this.sipClientState.getCallState();
        if (callState2 != SipClientState.CallState.CALLING && callState2 != (callState = SipClientState.CallState.CALLING_INITIATED)) {
            this.sipClientState.setCallState(callState);
            if (this.mContext == null) {
                MetricsHelper.recordSingleOccurrence(CounterMetric.generateOperational(MetricKeys.MAKE_A_CALL_CONTEXT_NULL));
                return;
            } else if (Utils.isOfflineDialogShown(activity, true, this.mPageSource, this.mAlertSource)) {
                LOG.e("Offline. Cannot place a call");
                CallUtils.handleCallInitiationErrors();
                return;
            } else if (!canPlaceCall()) {
                handleCanNotPlaceCall(activity);
                return;
            } else if (TextUtils.isEmpty(this.mCalleeCommsId) && this.mCalleePhoneNumber == null) {
                LOG.e("Recipient ID or phone number not found. Unable to call");
                CallUtils.handleCallInitiationErrors();
                return;
            } else {
                this.sipClientState.setCallType(this.mCallType);
                this.sipClientState.setCallProvider(this.mCallProvider);
                this.sipClientState.setSrtpKey(this.srtpKey);
                this.sipClientState.setEnhancedProcessingState(this.mEnhancedProcessingState);
                this.sipClientState.setRealTimeTextMetrics(new RealTimeTextMetrics(RealTimeTextConstants.RTT_NOT_REQUESTED, "DISABLED"));
                if (this.callID == null) {
                    this.callID = CallUtils.generateCallId();
                }
                this.mStartTimeMs = System.currentTimeMillis();
                MetricsHelper.startTimerMetric(TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_RING));
                if (this.mCallType.isDropIn()) {
                    MetricsHelper.startTimerMetric(TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_DROP_IN));
                }
                if (this.capabilitiesManager.isCallCaptioningEnabled()) {
                    new MPUSettingHandler(this.commsIdentityManager.getHomeGroupId("CallHelper.makeACall", false), this.sipClientState, Arrays.asList(Constants.GET_MPU_ENABLED, Constants.GET_SETTING), this.mCalleeCommsId, this.mCallProvider, new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$CallHelper$jINnSh9pGXiBh36VIHJRfguSELM
                        @Override // java.lang.Runnable
                        public final void run() {
                            CallHelper.this.lambda$makeACall$0$CallHelper(activity);
                        }
                    }).execute(new Void[0]);
                    return;
                } else {
                    lambda$makeACall$0$CallHelper(activity);
                    return;
                }
            }
        }
        LOG.i("Cannot place call, another call has already been initiated");
    }

    @VisibleForTesting
    CallParams prepareCallParams() {
        CallParams callParams = new CallParams();
        callParams.callerCommsId = this.commsIdentityManager.getCommsId("CallHelper.prepareCallParams", false);
        callParams.recipientId = this.mCalleeCommsId;
        callParams.launchFragmentKey = Constants.FRAGMENT_OUTGOING_CALL_KEY;
        return callParams;
    }

    public void setEnhancedProcessingState(@NonNull EnhancedProcessingState enhancedProcessingState) {
        this.mEnhancedProcessingState = enhancedProcessingState;
    }

    public void setupNotAvailableScreen(@NonNull CallParams callParams, @NonNull String str) {
        this.sipClientState.setCallType(CallType.NONE);
        this.sipClientState.setCallProvider("");
        Intent intent = new Intent();
        intent.putExtra("COMMS_ID", this.mCalleeCommsId);
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, this.mDisplayTitleName);
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_END_CALL_KEY);
        intent.putExtra(Constants.Calling.SCREEN_NAME, Constants.Calling.END_CALL_SCREEN);
        intent.putExtra(Constants.CALLEE_COMMS_ID, callParams.recipientId);
        intent.putExtra(Constants.CALLER_COMMS_ID, callParams.callerCommsId);
        intent.putExtra(Constants.CALLEE_SIP_URI, callParams.calleeSipUri);
        intent.setAction(Constants.SHOW_CALL_UI);
        intent.putExtra(Constants.CALL_END_STATUS, str);
        CallUtils.handleCallInitiationErrors();
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    public void setupNotAvailableScreenWithErrorMessage(@NonNull String str) {
        setupNotAvailableScreen(prepareCallParams(), str);
    }

    public CallHelper withAlertSource(AlertSource alertSource) {
        this.mAlertSource = alertSource;
        return this;
    }

    public CallHelper withCallID(@NonNull String str) {
        this.callID = str;
        return this;
    }

    public CallHelper withCallInitScreenName(@Nullable String str) {
        this.mCallInitScreenName = str;
        return this;
    }

    public CallHelper withCallProvider(@NonNull String str) {
        this.mCallProvider = str;
        return this;
    }

    public CallHelper withDeviceGruu(String str) {
        this.mDeviceGruu = str;
        return this;
    }

    public CallHelper withDeviceStatus(boolean z) {
        this.mIsDeviceOnline = z;
        return this;
    }

    public CallHelper withDisplayTitleName(String str) {
        if (str == null) {
            this.mDisplayTitleName = "";
        } else {
            this.mDisplayTitleName = str;
        }
        return this;
    }

    public CallHelper withDropInCall(boolean z) {
        this.mCallType = CallType.compute(z, this.mCallType.isVideo(), this.mCallType.isDeviceTargeted(), this.mCallType.isA2A(), this.mCallType.isFromVox());
        return this;
    }

    public CallHelper withNDTCall(boolean z) {
        this.mCallType = CallType.compute(this.mCallType.isDropIn(), this.mCallType.isVideo(), z, this.mCallType.isA2A(), this.mCallType.isFromVox());
        return this;
    }

    public CallHelper withPageSourceName(String str) {
        this.mPageSource = str;
        return this;
    }

    public CallHelper withRecipientID(String str) {
        this.mCalleeCommsId = str;
        return this;
    }

    public CallHelper withRecipientPhoneNumber(@NonNull ContactPhoneNumber contactPhoneNumber) {
        if (!TextUtils.isEmpty(contactPhoneNumber.getPhoneNumber())) {
            this.mCalleePhoneNumber = new ContactPhoneNumber(contactPhoneNumber);
            this.mCalleePhoneNumber.setPhoneNumber(CoboUtils.formatPhoneNumber(contactPhoneNumber.getPhoneNumber()));
        } else {
            this.mCalleePhoneNumber = null;
        }
        this.mCallType = CallType.compute(this.mCallType.isDropIn(), this.mCallType.isVideo(), this.mCallType.isDeviceTargeted(), this.mCalleePhoneNumber == null, this.mCallType.isFromVox());
        return this;
    }

    public CallHelper withSRTPKey(@NonNull String str) {
        this.srtpKey = str;
        return this;
    }

    public CallHelper withShouldInitiateDropIn(boolean z) {
        this.mShouldInitiateDropIn = z;
        return this;
    }

    public CallHelper withVideoCall(boolean z) {
        this.mCallType = CallType.compute(this.mCallType.isDropIn(), z, this.mCallType.isDeviceTargeted(), this.mCallType.isA2A(), this.mCallType.isFromVox());
        return this;
    }

    public CallHelper withVoxInitiated(boolean z) {
        this.mCallType = CallType.compute(this.mCallType.isDropIn(), this.mCallType.isVideo(), this.mCallType.isDeviceTargeted(), this.mCallType.isA2A(), z);
        return this;
    }

    public CallHelper withRecipientPhoneNumber(@Nullable String str, @Nullable String str2) {
        if (!TextUtils.isEmpty(str)) {
            ContactPhoneNumber contactPhoneNumber = new ContactPhoneNumber();
            contactPhoneNumber.setPhoneNumber(str);
            contactPhoneNumber.setType(PhoneNumberType.fromAcmsType(str2));
            return withRecipientPhoneNumber(contactPhoneNumber);
        }
        return this;
    }
}
