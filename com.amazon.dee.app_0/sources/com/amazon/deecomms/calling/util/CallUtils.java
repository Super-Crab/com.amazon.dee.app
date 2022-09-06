package com.amazon.deecomms.calling.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.device.SipDeviceState;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.comms.ringservice.webrtc.utils.WebRTCMediaStatsUtils;
import com.amazon.deecomms.R;
import com.amazon.deecomms.alexa.HalloConstants;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.TargetDeviceModel;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.model.CommsFeatureStatus;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.google.common.base.Optional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.Subscription;
/* loaded from: classes12.dex */
public final class CallUtils {
    public static final String INTENT_ACTION_SPEAKER = "amzn.action.speaker.state";
    private static final String RING_SERVICE_NO_CALL_ID = "NO_CALL_ID";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallUtils.class);
    public static AtomicBoolean disableIncomingCalls = new AtomicBoolean(false);

    private CallUtils() {
    }

    public static void acceptCall(Context context, String str) {
        SipClientState currentCallSipClientState = CommsDaggerWrapper.getComponent().getCurrentCallSipClientState();
        if (currentCallSipClientState.getCallId() == null || !currentCallSipClientState.getCallId().equals(str)) {
            return;
        }
        if (!currentCallSipClientState.getCallType().isAudio() && !Utils.areAccessoriesConnected() && !Utils.isInDriveMode()) {
            acceptIncomingCall(context, true);
        } else {
            acceptIncomingCall(context, false);
        }
    }

    public static void acceptIncomingCall(Context context, boolean z) {
        Intent intent;
        LOG.i("Accepting call");
        Intent intent2 = new Intent(context, DeviceCallingAndroidService.class);
        if (z) {
            intent2.setAction(Constants.ACCEPT_INCOMING_VIDEO_CALL);
        } else {
            intent2.setAction(Constants.ACCEPT_INCOMING_AUDIO_CALL);
        }
        context.startService(intent2);
        if (CommsDaggerWrapper.getComponent().getCallInitiationAuthority().isNewCallInitiationUIFlowEnabled(Optional.absent(), Optional.absent())) {
            intent = new Intent(context, NewCallActivity.class);
        } else {
            intent = new Intent(context, CallActivity.class);
        }
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_ACTIVE_CALL_KEY);
        intent.setFlags(268566528);
        context.startActivity(intent);
    }

    public static void cancelAnyCall(String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Cancel any call with call id :" + str);
        Intent intent = new Intent(CommsDaggerWrapper.getComponent().getContext(), DeviceCallingAndroidService.class);
        intent.setAction(Constants.CANCEL_ANY_CALL);
        intent.putExtra(Constants.CALL_ID, str);
        CommsDaggerWrapper.getComponent().getContext().startService(intent);
    }

    public static void cancelOutgoingCall(Context context) {
        LOG.i("Cancel outgoing call");
        Intent intent = new Intent(context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.CANCEL_OUTGOING_CALL);
        context.startService(intent);
    }

    public static boolean checkPermissions(@NonNull Fragment fragment, @NonNull AlertSource alertSource, int i, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener, @NonNull Context context) {
        String str;
        String str2;
        String[] permissionsNeeded = getPermissionsNeeded(fragment.getContext(), z);
        if (permissionsNeeded.length > 0) {
            String deniedCallingPermissionsRationale = PermissionsHelper.getDeniedCallingPermissionsRationale(context, z);
            if (z) {
                LOG.i(" Requesting permissions for outgoing Video/Drop in Call");
                str = MetricKeys.ALERT_PERM_MIC_AND_CAMERA;
                str2 = MetricKeys.SCREEN_NAME_OUTGOING_VIDEO_CALL;
            } else {
                LOG.i(" Requesting permissions for outgoing Audio Call");
                str = MetricKeys.ALERT_PERM_MIC;
                str2 = MetricKeys.SCREEN_NAME_OUTGOING_CALL;
            }
            String str3 = str;
            String str4 = str2;
            if (!(fragment instanceof CommsMasterFragment)) {
                fragment = fragment.getParentFragment();
            }
            PermissionsHelper.requestPermission(fragment, deniedCallingPermissionsRationale, permissionsNeeded, i, str3, str4, alertSource, false, onDismissListener);
            handleCallInitiationErrors();
            return false;
        }
        return true;
    }

    public static Subscription doTargetedDropIn(Subscriber<GetDevicesResponse> subscriber) {
        return CommsDaggerWrapper.getComponent().getDevicesSource().getDevicesObservable(true).subscribe((Subscriber<? super GetDevicesResponse>) subscriber);
    }

    public static void enableVideoStreamInVideoCall(SipClientState sipClientState, boolean z) {
        if (sipClientState.hasUserTurnedVideoOff() && z) {
            LOG.d("The user has chosen to retain the video switched off");
        } else if (!isVideoOrDropInVideoCall() || sipClientState.getCurrentActiveCall() == null) {
        } else {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Enabling video: " + z);
            sipClientState.getCurrentActiveCall().setLocalVideoState(z);
        }
    }

    public static void endActiveCall(Context context) {
        LOG.i("End active call");
        Intent intent = new Intent(context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.END_ACTIVE_CALL);
        context.startService(intent);
    }

    public static String generateCallId() {
        return UUID.randomUUID().toString();
    }

    @NonNull
    public static String getCallSideMetricValue(@NonNull Call.Side side) {
        return side == Call.Side.Local ? MetricKeys.VALUE_DIRECTION_OUTGOING : MetricKeys.VALUE_DIRECTION_INCOMING;
    }

    @Nullable
    public static String getCallerNameForCommsId(@NonNull String str, @NonNull String str2) {
        LOG.d("Trying to get the display name for comms id");
        ContactEntry contactEntry = new GetOrCreateContact(str2).getContactEntry(str, true);
        if (contactEntry == null) {
            LOG.w("Unable to find contact with callerCommsId");
            return null;
        }
        String fullName = ContactUtils.getFullName(contactEntry.getFullContactName());
        if (!TextUtils.isEmpty(fullName)) {
            return fullName;
        }
        LOG.w("Unable to get Caller name;");
        return null;
    }

    public static int getCellularCallState(@NonNull CallManager callManager, @NonNull TelephonyManager telephonyManager, @NonNull Context context) {
        if (Utils.getBooleanPreferenceFromSharedPrefs(context, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false) && callManager.isInAlexaCallMode()) {
            LOG.i("Resetting cellular call state to idle because user is in AlexaCallMode");
            return 0;
        }
        return telephonyManager.getCallState();
    }

    @Nullable
    public static String getConnectionType(@NonNull Call call) {
        MediaStats mediaStats = call.getMediaStats();
        if (mediaStats != null) {
            String localConnectionType = WebRTCMediaStatsUtils.getLocalConnectionType(mediaStats);
            String remoteConnectionType = WebRTCMediaStatsUtils.getRemoteConnectionType(mediaStats);
            if (!TextUtils.isEmpty(localConnectionType) && !TextUtils.isEmpty(remoteConnectionType)) {
                return String.format("%s-to-%s", localConnectionType, remoteConnectionType);
            }
            return null;
        }
        return null;
    }

    @VisibleForTesting
    public static String[] getPermissionsNeeded(@NonNull Context context, boolean z) {
        String[] permissionListForAudio;
        if (z) {
            permissionListForAudio = PermissionsHelper.getPermissionListForVideoCalling();
        } else {
            permissionListForAudio = PermissionsHelper.getPermissionListForAudio();
        }
        return PermissionsHelper.checkPermissions(context, permissionListForAudio);
    }

    @NonNull
    public static SipDeviceState getSipDeviceState(@NonNull SipClientState sipClientState, @NonNull String str) {
        String str2 = sipClientState.getCallState().toString();
        SipDeviceState.SipCallState sipCallState = new SipDeviceState.SipCallState();
        sipCallState.setCallId(str);
        sipCallState.setSide("");
        Call currentActiveCall = sipClientState.getCurrentActiveCall();
        if (currentActiveCall != null && currentActiveCall.getOrigin() != null) {
            sipCallState.setSide("Local".equals(currentActiveCall.getOrigin().name()) ? HalloConstants.CALLER_SIDE : "callee");
        }
        sipCallState.setCallState(str2);
        sipCallState.setCaller(sipClientState.getCaller());
        sipCallState.setCallee(sipClientState.getCallee());
        sipCallState.setIsDropIn(sipClientState.getCallType().isDropIn());
        sipCallState.setSipRegistered(sipClientState.getSipRegistrationStatus() == DeviceCallingService.State.Registered);
        sipCallState.setCallProvider(sipClientState.getCallProvider());
        SipDeviceState sipDeviceState = new SipDeviceState();
        sipDeviceState.setSipCallState(sipCallState);
        long currentTimeMillis = System.currentTimeMillis();
        sipDeviceState.setDeviceSentAbsoluteTimestamp(String.valueOf(currentTimeMillis));
        sipDeviceState.setDeviceSentRelativeTimestamp(String.valueOf(currentTimeMillis));
        sipDeviceState.setCLIENT_IDENTIFIER("Domain:Application:Communications:Calling");
        LOG.d(String.format("Outgoing Device Context: %s", sipDeviceState.toString()));
        return sipDeviceState;
    }

    public static void handleCallInitiationErrors() {
        handleCallInitiationErrors(CommsDaggerWrapper.getComponent().getCurrentCallSipClientState());
    }

    public static boolean isCallIdValid(@Nullable String str) {
        return !TextUtils.isEmpty(str) && !RING_SERVICE_NO_CALL_ID.equals(str);
    }

    public static boolean isDropInCall() {
        return CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallType().isDropIn();
    }

    public static boolean isNamedDeviceTargetedDropInCall(String str) {
        return TextUtils.equals(str, CommsInternal.getInstance().getHomeGroupId());
    }

    public static boolean isVideoOrDropInVideoCall() {
        return CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallType().isVideo() && !Utils.areAccessoriesConnected() && !Utils.isInDriveMode();
    }

    public static void notifySpeakerStateChange(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(INTENT_ACTION_SPEAKER));
    }

    public static void recordCallCompleted(@NonNull Call call, int i, String str) {
        double d = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(i)) == HttpStatusCodeFamily.SUCCESS ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : 1.0d;
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.CALL_DROPPED);
        MetricsHelper.addCallAttributes(generateOperational, call);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("direction", getCallSideMetricValue(call.getOrigin()));
        metadata.put("statusCode", Integer.valueOf(i));
        if (str != null) {
            metadata.put("errorSource", str);
        }
        MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(d));
    }

    public static void rejectCall(Context context) {
        Intent intent = new Intent(context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.REJECT_INCOMING_CALL);
        context.startService(intent);
    }

    public static void resetEnhancedProcessingStateIfNeeded(@Nullable DeviceModel deviceModel, @Nullable TargetDeviceModel targetDeviceModel) {
        List<CommsFeatureStatus> commsFeatureStatuses;
        if (deviceModel == null) {
            TargetDeviceModel.DeviceAttributes targetDevice = targetDeviceModel.getTargetDevice();
            commsFeatureStatuses = targetDevice != null ? targetDevice.getCommsFeatureStatusList() : null;
        } else {
            commsFeatureStatuses = deviceModel.getCommsFeatureStatuses();
        }
        if (commsFeatureStatuses != null) {
            for (CommsFeatureStatus commsFeatureStatus : commsFeatureStatuses) {
                if (Constants.MPU_ENABLED.equals(commsFeatureStatus.getFeature()) && !commsFeatureStatus.isSupported()) {
                    CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().setEnhancedProcessingState(EnhancedProcessingState.OFF);
                    CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().setSrtpKey(Constants.SDES);
                }
            }
        }
    }

    public static CallType setCallType(boolean z, boolean z2, boolean z3, boolean z4) {
        CallType compute = CallType.compute(z, z2, z3, z4);
        CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().setCallType(compute);
        CommsLogger commsLogger = LOG;
        commsLogger.i(" Call Type " + compute);
        return compute;
    }

    public static boolean shouldUpdateCallUIVisibility(String str, String str2) {
        return str == null || str2 == null || !str2.equals(Constants.FRAGMENT_END_CALL_KEY);
    }

    public static void showCOBOWarningAlert(@NonNull Context context, @NonNull final Runnable runnable) {
        if (((Boolean) SharedPreferencesUtils.getCacheValue(context, Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, false)).booleanValue()) {
            runnable.run();
            return;
        }
        SharedPreferencesUtils.persistCacheValues(context, Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, true);
        new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlexaCustomDialogStyle)).setTitle(R.string.cobo_first_call_warning_title).setMessage(R.string.cobo_first_call_warning_message).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$CallUtils$gyROVXk25idf7NUlvQIvXsk6GBg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                runnable.run();
            }
        }).setCancelable(false).show();
    }

    public static void toggleMuteAlexaCallForAccessories(boolean z, @NonNull Call call) {
        boolean z2 = !call.getMediaStatus().isLocalAudioEnabled();
        if (!CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener().isAnyAccessoryConnected()) {
            LOG.i("Not toggling mute status of Alexa call as there is no accessory");
        } else if (z2 == z) {
            LOG.i("The alexa call mute status is aligned with intended state, doing nothing.");
        } else {
            LOG.i("Mute status to toggled is: " + z);
            CommsDaggerWrapper.getComponent().getCommandProcessor().enableAudio(z ^ true);
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.ACCESSORY_MUTE_ALEXA_MUTED);
            generateOperational.getMetadata().put("EventValue", Boolean.valueOf(z));
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
        }
    }

    public static void handleCallInitiationErrors(@NonNull SipClientState sipClientState) {
        if (sipClientState.getCallState() == SipClientState.CallState.CALLING_INITIATED || sipClientState.getCallState() == SipClientState.CallState.CALLING) {
            sipClientState.setCallState(SipClientState.CallState.INACTIVE);
        }
    }

    public static boolean checkPermissions(@NonNull Activity activity, @NonNull AlertSource alertSource, int i, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener, @NonNull Context context) {
        String str;
        String str2;
        String[] permissionsNeeded = getPermissionsNeeded(activity, z);
        if (permissionsNeeded.length > 0) {
            String deniedCallingPermissionsRationale = PermissionsHelper.getDeniedCallingPermissionsRationale(context, z);
            if (z) {
                LOG.i(" Requesting permissions for outgoing Video/Drop in Call");
                str = MetricKeys.ALERT_PERM_MIC_AND_CAMERA;
                str2 = MetricKeys.SCREEN_NAME_OUTGOING_VIDEO_CALL;
            } else {
                LOG.i(" Requesting permissions for outgoing Audio Call");
                str = MetricKeys.ALERT_PERM_MIC;
                str2 = MetricKeys.SCREEN_NAME_OUTGOING_CALL;
            }
            PermissionsHelper.requestPermission(activity, deniedCallingPermissionsRationale, permissionsNeeded, i, str, str2, alertSource, false, onDismissListener);
            return false;
        }
        return true;
    }
}
