package com.amazon.deecomms.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.calling.model.notification.CallInvite;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.IncomingPushAndroidService;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.provider.MMSDKManager;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.notifications.PushTypeHelper;
import com.amazon.deecomms.notifications.filters.AvoidAnnouncementPushInDriveModeFilter;
import com.amazon.deecomms.notifications.filters.AvoidMessagePushFromSelfFilter;
import com.amazon.deecomms.notifications.filters.AvoidMessagePushInDriveModeFilter;
import com.amazon.deecomms.notifications.filters.ValidateAmazonPayloadFilter;
import com.amazon.deecomms.notifications.filters.ValidateApplicationIdFilter;
import com.amazon.deecomms.notifications.filters.ValidatePayloadAndAvoidDuplicationFilter;
import com.amazon.deecomms.notifications.filters.ValidateRecipientFilter;
import com.amazon.deecomms.notifications.filters.ValidateTargetAndPayloadContentFilter;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.LinkHeader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PushNotificationManager {
    private static final String GCM_SENDER_ID = "242259773361";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PushNotificationManager.class);
    private static AtomicBoolean mRegistrationInProgress = new AtomicBoolean(false);
    private static final int primaryUserIndex = 0;
    private final CapabilitiesManager capabilitiesManager;
    private final Context context;
    private final CommsIdentityManager mCommsIdentityManager;
    private final DeviceUtils mDeviceUtils;
    private final IdentityPreferencesProvider mIdentityPreferencesProvider;
    private final PushProcessor mPushProcessor;
    private final ModeSwitchHelper modeSwitchHelper;
    private final SecuredSharedPreference securedSharedPreference;
    private final JacksonJSONConverter jsonConverter = new JacksonJSONConverter();
    private String mEpmsCommsAppId = null;
    private String clientID = null;
    private String registerMetricKey = null;
    private String deregisterMetricKey = null;
    private AtomicBoolean isFetchingDirectedId = new AtomicBoolean(false);

    /* renamed from: com.amazon.deecomms.notifications.PushNotificationManager$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus = new int[PushProcessStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_APPLICATION_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_RECIPIENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_TARGET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_AMAZON_PAYLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_INCOMING_CALL_DURATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_INCOMING_CALL_DROP_IN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_MISSED_CALL_DROP_IN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_TEXT_MESSAGE_TEXT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_AUDIO_MESSAGE_MEDIA_ID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_CALLING_PUSH_PAYLOAD.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_CALLING_TOKEN_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_CALLING_TOKEN_VERSION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_CALLING_TOKEN_INFO_VERSION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.MISSING_CALLING_TOKEN_INFO.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_APPLICATION_ID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_RECIPIENT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.UNSUPPORTED_TARGET.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.UNKNOWN_PUSH_TYPE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_PUSH_PAYLOAD_MESSAGE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_PUSH_PAYLOAD_DELETE_CONVERSATION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_PUSH_PAYLOAD_READ_RECEIPT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_PUSH_PAYLOAD_TRANSCRIPTION_UPDATE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.INVALID_SENDER.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.DUPLICATE_PUSH_MESSAGE.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$PushProcessStatus[PushProcessStatus.RECEIVED_FROM_SELF.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum PushNotificationType {
        LEGACY,
        DMPS
    }

    public PushNotificationManager(Context context, SecuredSharedPreference securedSharedPreference, CommsIdentityManager commsIdentityManager, ModeSwitchHelper modeSwitchHelper, CapabilitiesManager capabilitiesManager, IdentityPreferencesProvider identityPreferencesProvider, DeviceUtils deviceUtils, DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        this.context = context;
        this.mPushProcessor = createProcessor(driveModeSharedPreferencesUseCase);
        this.securedSharedPreference = securedSharedPreference;
        this.mIdentityPreferencesProvider = identityPreferencesProvider;
        this.mCommsIdentityManager = commsIdentityManager;
        this.modeSwitchHelper = modeSwitchHelper;
        this.capabilitiesManager = capabilitiesManager;
        this.mDeviceUtils = deviceUtils;
    }

    private PushProcessor createProcessor(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        return PushProcessor.create().add((Task<PushProcessStatus, Bundle>) new ValidateTargetAndPayloadContentFilter()).add((Task<PushProcessStatus, Bundle>) new ValidateApplicationIdFilter()).add((Task<PushProcessStatus, Bundle>) new ValidateAmazonPayloadFilter()).add((Task<PushProcessStatus, Bundle>) new ValidateRecipientFilter()).add((Task<PushProcessStatus, Bundle>) new ValidatePayloadAndAvoidDuplicationFilter()).add((Task<PushProcessStatus, Bundle>) new AvoidMessagePushFromSelfFilter()).add((Task<PushProcessStatus, Bundle>) new AvoidMessagePushInDriveModeFilter(driveModeSharedPreferencesUseCase)).add((Task<PushProcessStatus, Bundle>) new AvoidAnnouncementPushInDriveModeFilter(driveModeSharedPreferencesUseCase));
    }

    private void fetchRootDirectedIdAndRegisterPush() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.notifications.PushNotificationManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                IHttpClient.Response mo3640execute;
                try {
                    mo3640execute = new ACMSClient(MetricKeys.OP_GET_USERS_FOR_OOBE).request(AppUrl.OOBE_ACCOUNTS).authenticated().get().mo3640execute();
                } catch (ServiceException e) {
                    CommsLogger commsLogger = PushNotificationManager.LOG;
                    commsLogger.e("Failed to get accounts with exception: " + e);
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.ROOT_DIRECT_ID_RETRIEVAL_ERROR, "ServiceException " + e.getMessage());
                }
                if (mo3640execute != null && mo3640execute.isSuccessful()) {
                    try {
                        String str = Person.create(new JSONArray(mo3640execute.getBody()).getJSONObject(0)).directedId;
                        if (str == null || str.isEmpty()) {
                            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.VALUE_EMPTY_ROOT_DIRECT_ID, "fetchRootDirectId");
                        }
                        Utils.writeStringPreferenceToSharedPrefs(PushNotificationManager.this.context, Constants.ROOT_DIRECTED_ID, str);
                        PushNotificationManager.LOG.i("Successfully stored rootDirectedId");
                    } catch (JSONException e2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("ParseError ");
                        sb.append(e2.getMessage());
                        MetricsHelper.recordOperationalMetricWithSource(MetricKeys.ROOT_DIRECT_ID_RETRIEVAL_ERROR, sb.toString());
                        PushNotificationManager.LOG.e("Error parsing response", e2);
                    }
                    return null;
                }
                PushNotificationManager.LOG.i("Fetching directedId was not successful or response was null");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ResponseError ");
                sb2.append(mo3640execute);
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.ROOT_DIRECT_ID_RETRIEVAL_ERROR, sb2.toString());
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r2) {
                PushNotificationManager.this.isFetchingDirectedId.set(false);
                PushNotificationManager.this.attemptPushRegistration();
            }
        }.execute(new Void[0]);
    }

    private String getPersistedKey(String str) {
        return getPersistedKey(str, null);
    }

    @SuppressLint({"NewApi"})
    private void handleCallingNotification(@NonNull String str, @NonNull String str2) {
        if (this.modeSwitchHelper.isTabletMode() && !MMSDKManager.isCommsOnLenovoSmartTabletEnabled(this.context)) {
            Intent intent = new Intent(this.context, (Utils.isAndroid10AndAboveFOS() || Utils.isAndroid10ChangesEnabled()) ? IncomingPushAndroidService.class : DeviceCallingAndroidService.class);
            intent.putExtra(Constants.REGISTRATION_EVENT_TYPE_KEY, 1);
            intent.putExtra(Constants.GCM_MESSAGE_ID_KEY, str);
            intent.putExtra(Constants.AMP_KEY, str2);
            try {
                if ((Utils.isOreoAndAbove() && Utils.isFireOS()) || Utils.isAndroid10ChangesEnabled()) {
                    if (Utils.getBooleanPreferenceFromSharedPrefs(this.context, Constants.IS_APP_IN_FOREGROUND, false)) {
                        this.context.startService(intent);
                    } else {
                        intent.putExtra(Constants.SHOULD_START_FOREGROUND_NOTI, true);
                        CallInvite callInvite = (CallInvite) this.jsonConverter.fromJson(str2, CallInvite.class);
                        intent.putExtra(Constants.KEY_CALLER_NAME, callInvite.getTokenCallerName());
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.HEADUP_NOTIFICATION_CALL_VIA_PUSH);
                        LOG.i("[Comms-calling]: handleCallingNotification tokenCallId: %s", callInvite.getTokenCallId());
                        Utils.writeStringPreferenceToSharedPrefs(this.context, Constants.RECENT_CALLID_VIA_PUSH, callInvite.getTokenCallId());
                        this.context.startForegroundService(intent);
                    }
                } else {
                    this.context.startService(intent);
                }
            } catch (IllegalStateException e) {
                LOG.e("Caught exception while handling call notification: ", e);
                CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_DCAS_START);
                generateOperational.getMetadata().put("source", str2);
                generateOperational.getMetadata().put("EventValue", str);
                generateOperational.getMetadata().put("errorSource", e.getMessage());
                generateOperational.getMetadata().put("duration", Long.valueOf(System.currentTimeMillis()));
                MetricsHelper.recordSingleOccurrence(generateOperational);
            }
            NotificationUtils.logInfo(LOG, str, "Push target was CALLING, sending amznPayload to DCAS");
            return;
        }
        LOG.i("Ignoring incoming call as we are in multimodal mode");
        MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODESWITCH_INCOMING_PUSHCALL_IGNORE, Constants.MULTIMODAL_MODE, Constants.MANUAL_DETECTION);
    }

    private void handleNotification(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) {
        if (TextUtils.isEmpty(str2)) {
            new HashMap().put("errorSource", MetricKeys.VALUE_INVALID_AMAZON_PAYLOAD);
            LOG.w("Received notification for handling but it was missing amznMessage payload; dropped");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return;
        }
        String string = bundle.getString("target");
        LOG.w("Received notification for handling with a target of: " + string);
        char c = 65535;
        int hashCode = string.hashCode();
        if (hashCode != -1820904121) {
            if (hashCode != 1266623652) {
                if (hashCode == 1672907751 && string.equals("MESSAGE")) {
                    c = 0;
                }
            } else if (string.equals(Constants.NOTIFICATION_TARGET_CALLING)) {
                c = 1;
            }
        } else if (string.equals(Constants.NOTIFICATION_TARGET_ANNOUNCEMENT)) {
            c = 2;
        }
        if (c == 0) {
            triggerSendCommsBroadcastNotification(str, str2, "MESSAGE");
        } else if (c == 1) {
            handleCallingNotification(str, str2);
        } else if (c != 2) {
            NotificationUtils.logInfo(LOG, str, "Push target was not valid");
        } else if (!this.capabilitiesManager.isAnnouncementPushNotificationEnabled()) {
            LOG.i("Received an announcement push, but feature is not enabled; dropped");
            return;
        } else {
            triggerSendCommsBroadcastNotification(str, str2, Constants.NOTIFICATION_TARGET_ANNOUNCEMENT);
        }
        logNotificationHandledMetric(string, str2);
    }

    private Bundle jsonStringToBundle(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                bundle.putString(str2, jSONObject.getString(str2));
            }
            return bundle;
        } catch (JSONException unused) {
            LOG.e("Encountered an issue parsing the push notification JSON");
            return null;
        }
    }

    private void logNotificationDroppedMetric(PushProcessStatus pushProcessStatus, String str, PushNotificationType pushNotificationType) {
        String str2;
        switch (pushProcessStatus.ordinal()) {
            case 1:
                NotificationUtils.logWarning(LOG, str, "Received push but the application id was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_APPLICATION_ID;
                break;
            case 2:
                NotificationUtils.logWarning(LOG, str, "Received push but the recipient was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_RECIPIENT;
                break;
            case 3:
                NotificationUtils.logWarning(LOG, str, "Received push but the target was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_TARGET;
                break;
            case 4:
                NotificationUtils.logWarning(LOG, str, "Received push but the amzn payload was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_AMAZON_PAYLOAD;
                break;
            case 5:
            case 11:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            default:
                str2 = null;
                break;
            case 6:
                NotificationUtils.logWarning(LOG, str, "Received push but duration was missing for incoming call; ignoring");
                str2 = MetricKeys.VALUE_MISSING_INCOMING_CALL_DURATION;
                break;
            case 7:
                NotificationUtils.logWarning(LOG, str, "Received push but drop-in was missing for incoming call; ignoring");
                str2 = MetricKeys.VALUE_MISSING_INCOMING_CALL_DROP_IN;
                break;
            case 8:
                NotificationUtils.logWarning(LOG, str, "Received push but drop-in was missing for a missed call; ignoring");
                str2 = MetricKeys.VALUE_MISSING_MISSED_CALL_DROP_IN;
                break;
            case 9:
                NotificationUtils.logWarning(LOG, str, "Received push but text was missing for a text message; ignoring");
                str2 = MetricKeys.VALUE_MISSING_TEXT_MESSAGE_TEXT;
                break;
            case 10:
                NotificationUtils.logWarning(LOG, str, "Received push but mediaId was missing for an audio msg; ignoring");
                str2 = MetricKeys.VALUE_MISSING_AUDIO_MESSAGE_MEDIA_ID;
                break;
            case 12:
                NotificationUtils.logWarning(LOG, str, "Received push but the calling payload was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_CALLING_PUSH_PAYLOAD;
                break;
            case 13:
                NotificationUtils.logWarning(LOG, str, "Received push but the call token value was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_CALLING_TOKEN_VALUE;
                break;
            case 14:
                NotificationUtils.logWarning(LOG, str, "Received push but the call token version was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_CALLING_TOKEN_VERSION;
                break;
            case 15:
                NotificationUtils.logWarning(LOG, str, "Received push but the call token info version was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_CALLING_TOKEN_INFO_VERSION;
                break;
            case 16:
                NotificationUtils.logWarning(LOG, str, "Received push but the call token info was missing; ignoring");
                str2 = MetricKeys.VALUE_MISSING_CALLING_TOKEN_INFO;
                break;
            case 22:
                NotificationUtils.logWarning(LOG, str, "Received push but the recipient comms Id was not my own; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_NOT_INTENDED_RECIPIENT;
                break;
            case 23:
                NotificationUtils.logWarning(LOG, str, "Received push but the target is unsupported; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_UNSUPPORTED_TARGET;
                break;
            case 24:
                NotificationUtils.logWarning(LOG, str, "Received push but pushType was PushType.Unknown; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_UNSUPPORTED_PUSH_TYPE;
                break;
            case 25:
                NotificationUtils.logWarning(LOG, str, "Received push but applicationId did not match CommsAppId; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_UNSUPPORTED_APPLICATION_ID;
                break;
            case 26:
                NotificationUtils.logWarning(LOG, str, "Received delete-conversation push but unable to parse payload");
                str2 = MetricKeys.NOTIFICATION_DROPPED_INVALID_DELETE_CONVERSATION_PAYLOAD;
                break;
            case 27:
                NotificationUtils.logWarning(LOG, str, "Received MESSAGE push but unable to parse payload");
                str2 = MetricKeys.NOTIFICATION_DROPPED_INVALID_MESSAGE_PAYLOAD;
                break;
            case 28:
                NotificationUtils.logWarning(LOG, str, "Received read receipt push but unable to parse payload");
                str2 = MetricKeys.NOTIFICATION_DROPPED_INVALID_READ_RECEIPT_PAYLOAD;
                break;
            case 29:
                NotificationUtils.logWarning(LOG, str, "Received transcription-update push but unable to parse payload");
                str2 = MetricKeys.NOTIFICATION_DROPPED_INVALID_TRANSCRIPT_UPDATE_PAYLOAD;
                break;
            case 30:
                NotificationUtils.logWarning(LOG, str, "Received push but the sender comms id was not valid; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_INVALID_SENDER_ID;
                break;
            case 31:
                NotificationUtils.logWarning(LOG, str, "Received push but it was a duplicate; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_DUPLICATE;
                break;
            case 32:
                NotificationUtils.logWarning(LOG, str, "Received push but it was from self; ignoring");
                str2 = MetricKeys.NOTIFICATION_DROPPED_SENDER_WAS_SELF;
                break;
        }
        HashMap hashMap = new HashMap();
        if (pushProcessStatus.ordinal() < PushProcessStatus.SEPARATOR_FOR_PAYLOAD_AND_AMZN_MSG.ordinal()) {
            hashMap.put("errorSource", str2);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.CNOTIF_PARSER_FAILED_PUSH_PAYLOAD, 1.0d, hashMap);
        } else if (pushProcessStatus.ordinal() < PushProcessStatus.SEPARATOR_FOR_PARSING_AND_FILTER.ordinal()) {
            hashMap.put("errorSource", str2);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.CNOTIF_PARSER_FAILED_AMZN_MSG, 1.0d, hashMap);
            hashMap.put("errorSource", MetricKeys.VALUE_INVALID_AMAZON_PAYLOAD);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.CNOTIF_PARSER_FAILED_PUSH_PAYLOAD, 1.0d, hashMap);
        } else if (!TextUtils.isEmpty(str2)) {
            MetricsHelper.recordSingleOccurrenceOperational(str2);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("source", pushNotificationType);
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.NOTIFICATION_DROPPED_METRIC, 1.0d, hashMap2);
    }

    private void logNotificationHandledMetric(String str, String str2) {
        Message message;
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.CNOTIF_PARSER_FAILED_PUSH_PAYLOAD, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null);
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, MetricKeys.CNOTIF_PARSER_FAILED_AMZN_MSG, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null);
        if ("MESSAGE".equals(str)) {
            if (PushTypeHelper.determineType(str2) != PushTypeHelper.PushType.Message || (message = (Message) this.jsonConverter.fromJson(str2, Message.class)) == null) {
                return;
            }
            publishMessageMetrics(message);
        } else if (!Constants.NOTIFICATION_TARGET_CALLING.equals(str)) {
        } else {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_CALL_RECEIVED);
            Utils.writeLongPreferenceToSharedPrefs(this.context, Constants.NOTIFICATION_CALL_TOKEN_START_TIME, new Date(System.currentTimeMillis()).getTime());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void publishMessageMetrics(@NonNull Message message) {
        char c;
        String str;
        String type = message.getType();
        switch (type.hashCode()) {
            case -1330413003:
                if (type.equals("message/text")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 213629550:
                if (type.equals("message/contact-invitation")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 849467995:
                if (type.equals("event/missed-call")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 939518131:
                if (type.equals("event/call")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1689780174:
                if (type.equals("message/audio")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1700385756:
                if (type.equals("message/media")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1846772201:
                if (type.equals("message/contact-connection-success")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2029986329:
                if (type.equals("message/shared-content")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                str = "audio";
                break;
            case 1:
                str = "call";
                break;
            case 2:
                str = "miss";
                break;
            case 3:
                str = "text";
                break;
            case 4:
                str = "invite";
                break;
            case 5:
                str = "inviteOk";
                break;
            case 6:
                str = LinkHeader.Parameters.Media;
                break;
            case 7:
                str = "share";
                break;
            default:
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("dealing with an unknown message type: ");
                outline1.append(message.getType());
                commsLogger.w(outline1.toString());
                str = "unknown";
                break;
        }
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_MESSAGE_RECEIVED);
        MetricsHelper.recordSingleOccurrenceOperational("comms.notif.msg.incoming." + str);
    }

    private void setEpmsCommsAppId(String str) {
        this.securedSharedPreference.putNonEmptyString(Constants.EPMS_APP_ID_PREF, str);
        this.mEpmsCommsAppId = str;
    }

    private void setPushToken(String str, @NonNull String str2) {
        if (str != null && !str.isEmpty()) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.VALUE_NONEMPTY_PUSH_TOKEN, str2);
        } else {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.VALUE_EMPTY_PUSH_TOKEN, str2);
        }
        this.securedSharedPreference.putNonEmptyString(Constants.PUSH_TOKEN_PREF, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupRegistrationValues() {
        try {
            this.clientID = DeviceDataStore.getInstance(this.context).getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            this.registerMetricKey = MetricKeys.OP_REG_FOR_PUSH_NOTIFY_DMPS;
            this.deregisterMetricKey = MetricKeys.OP_DEREG_FOR_PUSH_NOTIFY_DMPS;
        } catch (DeviceDataStoreException e) {
            LOG.e("Error while getValue from deviceDataStore.", e);
        }
        if (TextUtils.isEmpty(this.clientID)) {
            LOG.i("Device serial number was null.");
            this.clientID = CommsInternal.getInstance().getClientID();
            if (Utils.isFireOS()) {
                this.registerMetricKey = MetricKeys.OP_REG_FOR_PUSH_NOTIFY_ADM;
                this.deregisterMetricKey = MetricKeys.OP_DEREG_FOR_PUSH_NOTIFY_ADM;
                return;
            }
            this.registerMetricKey = MetricKeys.OP_REG_FOR_PUSH_NOTIFY_GCM;
            this.deregisterMetricKey = MetricKeys.OP_DEREG_FOR_PUSH_NOTIFY_GCM;
        }
    }

    private void triggerSendCommsBroadcastNotification(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        NotificationUtils.logInfo(LOG, str, "Sending notification broadcast for processing");
        Bundle bundle = new Bundle();
        bundle.putString(Constants.AMP_KEY, str2);
        bundle.putString("target", str3);
        bundle.putString(Constants.GCM_MESSAGE_ID_KEY, str);
        Intent intent = new Intent(this.context, CommsDefaultPushHandler.class);
        intent.putExtras(bundle);
        intent.setAction(Constants.COMMS_BROADCAST_NOTIFICATION);
        this.context.sendBroadcast(intent);
    }

    @VisibleForTesting
    void attemptPushRegistration() {
        LOG.i("Attempt to register for push with ACMS in a background thread");
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.notifications.PushNotificationManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (TextUtils.isEmpty(PushNotificationManager.this.clientID)) {
                    PushNotificationManager.LOG.i("ClientId was not set, so retrieving it");
                    PushNotificationManager.this.setupRegistrationValues();
                }
                HashMap hashMap = new HashMap();
                String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(), PushNotificationManager.this.registerMetricKey, ".skip");
                if (PushNotificationManager.this.context == null) {
                    PushNotificationManager.LOG.e("Unable to register for push notifications, context was null");
                    hashMap.put("errorSource", MetricKeys.VALUE_MISSING_CONTEXT);
                    MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".context"), 1.0d, hashMap);
                    return null;
                } else if (TextUtils.isEmpty(PushNotificationManager.this.clientID)) {
                    PushNotificationManager.LOG.e("Unable to register for push notifications, clientID was null or empty");
                    hashMap.put("errorSource", MetricKeys.VALUE_MISSING_CLIENT_ID);
                    MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".clientID"), 1.0d, hashMap);
                    return null;
                } else if (TextUtils.isEmpty(PushNotificationManager.this.getPersistedKey(Constants.PUSH_TOKEN_PREF, null))) {
                    PushNotificationManager.LOG.e("Unable to register for push notifications, pushToken was null or empty");
                    hashMap.put("errorSource", MetricKeys.VALUE_MISSING_PUSH_TOKEN);
                    MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".token"), 1.0d, hashMap);
                    return null;
                } else {
                    String commsId = PushNotificationManager.this.mCommsIdentityManager.getCommsId("PushNotificationManager.attemptPushRegistration", false);
                    if (TextUtils.isEmpty(commsId)) {
                        PushNotificationManager.LOG.e("Unable to register for push notifications, commsID was null or empty");
                        hashMap.put("errorSource", MetricKeys.VALUE_MISSING_COMMS_ID);
                        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".commsID"), 1.0d, hashMap);
                        return null;
                    }
                    String epmsCommsAppId = PushNotificationManager.this.getEpmsCommsAppId();
                    if (TextUtils.isEmpty(epmsCommsAppId)) {
                        PushNotificationManager.LOG.e("Unable to register for push notifications, appID was null or empty");
                        hashMap.put("errorSource", MetricKeys.VALUE_MISSING_APP_ID);
                        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".appID"), 1.0d, hashMap);
                        return null;
                    }
                    String stringPreferenceFromSharedPrefs = Utils.getStringPreferenceFromSharedPrefs(PushNotificationManager.this.context, Constants.ROOT_DIRECTED_ID, null);
                    if (TextUtils.isEmpty(stringPreferenceFromSharedPrefs)) {
                        PushNotificationManager.LOG.e("Unable to register for push notifications, rootDirectedId was null or empty");
                        hashMap.put("errorSource", MetricKeys.VALUE_MISSING_ROOT_DIRECTED_ID);
                        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline91, ".rootDirectedID"), 1.0d, hashMap);
                        return null;
                    }
                    PushNotificationService pushNotificationService = PushNotificationService.DMPS;
                    PushNotificationService fallback = PushNotificationManager.this.getFallback();
                    boolean z = false;
                    for (int i = 3; !z && i > 0; i--) {
                        PushNotificationManager.LOG.d(String.format("Attempt %s to reg for push", Integer.valueOf((3 - i) + 1)));
                        z = ACMSRegistrationManager.registerForPush(PushNotificationManager.this.context, epmsCommsAppId, PushNotificationManager.this.clientID, pushNotificationService, fallback, PushNotificationManager.this.getPersistedKey(Constants.PUSH_TOKEN_PREF, null), commsId, stringPreferenceFromSharedPrefs, PushNotificationManager.this.registerMetricKey);
                    }
                    if (z) {
                        PushNotificationManager.LOG.i("Successfully registered for push notifications with ACMS");
                    } else {
                        PushNotificationManager.LOG.e(String.format("Did NOT successfully register for push with ACMS after %d attempts", 3));
                    }
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    public void deregisterForPush() {
        String epmsCommsAppId = getEpmsCommsAppId();
        if (TextUtils.isEmpty(this.clientID)) {
            setupRegistrationValues();
        }
        ACMSRegistrationManager.deregisterForPush(this.context, epmsCommsAppId, this.clientID, this.deregisterMetricKey);
    }

    public void deregisterForPushAsynchronously() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.notifications.PushNotificationManager.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                PushNotificationManager.this.deregisterForPush();
                return null;
            }
        }.execute(new Void[0]);
    }

    public String getEpmsCommsAppId() {
        if (TextUtils.isEmpty(this.mEpmsCommsAppId)) {
            this.mEpmsCommsAppId = getPersistedKey(Constants.EPMS_APP_ID_PREF, null);
        }
        return this.mEpmsCommsAppId;
    }

    @VisibleForTesting
    PushNotificationService getFallback() {
        if (Utils.isFireOS()) {
            return PushNotificationService.ADM;
        }
        return null;
    }

    public String getGcmSenderId() {
        return GCM_SENDER_ID;
    }

    public boolean onPush(Bundle bundle) {
        PushNotificationType pushNotificationType;
        LOG.i("[Comms-calling]: onPush");
        String string = bundle.getString(Constants.GCM_MESSAGE_ID_KEY);
        boolean z = true;
        NotificationUtils.logInfo(LOG, string, String.format("Push received with sent time %s", String.valueOf(bundle.get(Constants.GCM_MESSAGE_SENT_TIME))));
        if (!this.mCommsIdentityManager.isCoreIdentityPopulated("PushNotificationManager.onPush", false)) {
            NotificationUtils.logWarning(LOG, string, "CommsInternal#onPush called, ignoring because CommsIdentity is not set.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return false;
        } else if (CommsInternal.getInstance().isLowInternalStorage()) {
            LOG.i("CommsInternal#onPush called, ignoring because of low disk storage");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_LOW_STORAGE_METRIC);
            return false;
        } else {
            if (bundle.containsKey("data")) {
                bundle = jsonStringToBundle(bundle.getString("data"));
                pushNotificationType = PushNotificationType.DMPS;
            } else {
                pushNotificationType = PushNotificationType.LEGACY;
            }
            String string2 = bundle.getString(Constants.AMP_KEY);
            PushProcessStatus execute = this.mPushProcessor.execute(bundle);
            String str = "DMPS path";
            if (execute == PushProcessStatus.CONTINUE) {
                NotificationUtils.logInfo(LOG, string, "Received comms push; sending it off for deeper processing");
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_RECEIVED);
                MetricsHelper.recordCounterMetricOperational(MetricKeys.NOTIFICATION_ENCRYPTED_RECEIVED, (pushNotificationType != PushNotificationType.DMPS || Utils.isFireOS()) ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : 1.0d);
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("PushNotificationManager received payload from: ");
                if (pushNotificationType != PushNotificationType.DMPS) {
                    str = "legacy path";
                }
                outline1.append(str);
                NotificationUtils.logInfo(commsLogger, string, outline1.toString());
                handleNotification(string, string2, bundle);
            } else {
                if (execute != PushProcessStatus.RECEIVED_FROM_SELF && execute != PushProcessStatus.DUPLICATE_PUSH_MESSAGE && execute != PushProcessStatus.IN_DRIVE_MODE) {
                    z = false;
                }
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Received push; dropping... amznPayload");
                outline12.append(LOG.sensitive(string2));
                outline12.append(", from ");
                if (pushNotificationType != PushNotificationType.DMPS) {
                    str = "legacy path ";
                }
                outline12.append(str);
                outline12.append(", reason: ");
                outline12.append(execute);
                NotificationUtils.logInfo(commsLogger2, string, outline12.toString());
                logNotificationDroppedMetric(execute, string, pushNotificationType);
            }
            return z;
        }
    }

    public void registerForPush() {
        boolean z = false;
        CommsProvisionStatus provisionStatus = this.mCommsIdentityManager.getProvisionStatus(true, "PushNotificationManager.registerForPush", false);
        if (provisionStatus.equals(CommsProvisionStatus.PROVISIONED) || provisionStatus.equals(CommsProvisionStatus.AUTO_PROVISIONED)) {
            z = true;
        }
        if (!z) {
            LOG.i("Did not register for push with ACMS; not yet provisioned");
        } else if (!GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(this.context, this.capabilitiesManager)) {
            LOG.i("Did not register for push with ACMS; comms is disabled for this device");
        } else if (this.isFetchingDirectedId.get()) {
            LOG.i("Already fetching directedId, return");
        } else if (TextUtils.isEmpty(Utils.getStringPreferenceFromSharedPrefs(this.context, Constants.ROOT_DIRECTED_ID, null))) {
            LOG.i("Need to get root directedId");
            this.isFetchingDirectedId.set(true);
            fetchRootDirectedIdAndRegisterPush();
        } else {
            LOG.i("Found rootDirectedId, will continue with push registration");
            attemptPushRegistration();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPersistedKey(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.securedSharedPreference.getString(str, str2);
    }

    public void registerForPush(String str, String str2) {
        this.securedSharedPreference.putNonEmptyString(Constants.EPMS_APP_ID_PREF, str);
        this.mEpmsCommsAppId = str;
        setPushToken(str2, ADMRegistrationConstants.METHOD_REGISTER);
        registerForPush();
    }
}
