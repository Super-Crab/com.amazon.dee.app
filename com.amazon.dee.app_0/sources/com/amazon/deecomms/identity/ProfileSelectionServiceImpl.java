package com.amazon.deecomms.identity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.nativemodules.CommsEventEmitterBridge;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import dagger.Lazy;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class ProfileSelectionServiceImpl implements ProfileSelectionService {
    private static final String SOURCE = "ProfileSelectionService";
    private final Lazy<CapabilitiesManager> capabilitiesManager;
    private final Lazy<CommsIdentityManager> commsIdentityManager;
    private final Context context;
    private final Lazy<DeviceMetadataStoreRegistrar> deviceMetadataStoreRegistrar;
    private final Lazy<OOBEPersonManager> oobePersonManager;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ProfileSelectionServiceImpl.class);
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final boolean persistValue = false;
    final boolean disableMetric = false;
    private final ACMSClient acmsClient = new ACMSClient(MetricKeys.OP_AUTO_PROV_COMMS_USER_FROM_OOBE);

    public ProfileSelectionServiceImpl(@NonNull Context context, @NonNull Lazy<CapabilitiesManager> lazy, @NonNull Lazy<CommsIdentityManager> lazy2, @NonNull Lazy<DeviceMetadataStoreRegistrar> lazy3, @NonNull Lazy<OOBEPersonManager> lazy4) {
        this.context = context;
        this.capabilitiesManager = lazy;
        this.commsIdentityManager = lazy2;
        this.deviceMetadataStoreRegistrar = lazy3;
        this.oobePersonManager = lazy4;
    }

    private void recordCounterMetric(String str, Double d) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put("source", SOURCE);
        MetricsHelper.recordCounterMetric(generateOperational, d);
    }

    private void resetRegisteredWithDMDS() {
        this.context.getSharedPreferences("SHARED_PREFS", 0).edit().remove(Constants.DEVICE_REGISTERED_WITH_DMDS).apply();
    }

    private Person retrieveProfileInformationFromMessage(Message message) {
        double d = 1.0d;
        try {
            try {
                return (Person) objectMapper.readValue(message.getPayloadAsString(), Person.class);
            } catch (Exception e) {
                d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                throw new IllegalArgumentException("Exception occurred while retrieving profile from payload.", e);
            }
        } finally {
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_INVALID_MESSAGE_COUNT, Double.valueOf(d));
        }
    }

    private boolean shouldProcessMessage(Message message) {
        if (!this.capabilitiesManager.mo358get().isRNProfileOobeEnabled()) {
            LOG.i("RN profile oobe is not enabled. Not processing the event.");
            return false;
        } else if (!message.getPayloadAsString().isEmpty()) {
            return true;
        } else {
            LOG.i("Received empty payload. Not processing the event.");
            return false;
        }
    }

    @VisibleForTesting
    void commitCommsUserWithCommsId(Person person) throws InvalidCommsIdentityException {
        double d = 1.0d;
        try {
            try {
                CommsIdentity commsIdentity = person.toCommsIdentity(SOURCE, false);
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("BEFORE setCurrentUser for profile with commsId: ");
                sb.append(person.commsId);
                sb.append(", homeGroup = ");
                sb.append(person.homeGroupId);
                commsLogger.d(sb.toString());
                this.commsIdentityManager.mo358get().setCurrentUser(commsIdentity);
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("AFTER setCurrentUser for profile with commsId: ");
                sb2.append(person.commsId);
                sb2.append(", homeGroup = ");
                sb2.append(person.homeGroupId);
                commsLogger2.d(sb2.toString());
                this.commsIdentityManager.mo358get().onCurrentUserUpdated();
            } catch (Exception e) {
                d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                LOG.e("Exception occurred when saving profile info to the store.", e);
                throw e;
            }
        } finally {
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_UPDATE_COMMS_STORE_SUCCESS, Double.valueOf(d));
        }
    }

    @VisibleForTesting
    Person getAdditionalCommsAttributes(Person person) {
        Person copy = person.copy();
        try {
            try {
                UserInfo commsUserInfo = getCommsUserInfo(person);
                boolean z = commsUserInfo == null;
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("commsUserInfo is null : ");
                sb.append(z);
                commsLogger.i(sb.toString());
                if (commsUserInfo != null) {
                    copy.homeGroupId = commsUserInfo.getHomeGroupId();
                    copy.aor = commsUserInfo.getAor();
                    copy.hashedCommsId = commsUserInfo.getHashedCommsId();
                }
            } catch (Exception e) {
                LOG.e("Exception occurred when updating Comms Identity.", e);
                recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_UPDATE_COMMS_IDENTITY_SUCCESS, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
            }
            return copy;
        } finally {
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_UPDATE_COMMS_IDENTITY_SUCCESS, Double.valueOf(1.0d));
        }
    }

    UserInfo getCommsUserInfo(Person person) {
        if (Strings.isNullOrEmpty(person.commsId)) {
            LOG.i("Profile doesn't have an associated commsId. Cannot fetch CommsIdentity.");
            return null;
        }
        double d = 1.0d;
        try {
            return (UserInfo) this.acmsClient.request(MessageFormat.format("/users/{0}/identities", person.commsId)).authenticated(person.directedId).get().mo3640execute().convert(UserInfo.class);
        } catch (Exception e) {
            d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            LOG.e("Service Error occurred while fetching UserInfo.", e);
            return null;
        } finally {
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_GET_COMMS_IDENTITY_SUCCESS, Double.valueOf(d));
        }
    }

    @Override // com.amazon.deecomms.api.ProfileSelectionService
    public void handleProfileSelectionEvent(@NonNull Message message) {
        LOG.i("Received Profile Selection Event.");
        double d = 1.0d;
        Double valueOf = Double.valueOf(1.0d);
        try {
            try {
                if (!shouldProcessMessage(message)) {
                    recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_EVENT_SUCCESS, valueOf);
                    return;
                }
                LOG.d("hydrating person from message");
                Person retrieveProfileInformationFromMessage = retrieveProfileInformationFromMessage(message);
                LOG.d("saving provisioning status first to help RN");
                boolean z = false;
                this.commsIdentityManager.mo358get().setMinimumInfo(retrieveProfileInformationFromMessage, SOURCE, false);
                LOG.i("saved provisioning status first to help RN");
                this.oobePersonManager.mo358get().saveOOBESharedPreferences(retrieveProfileInformationFromMessage);
                LOG.i("Saved person to OOBE SharedPreferences");
                if (!Strings.isNullOrEmpty(retrieveProfileInformationFromMessage.commsId)) {
                    LOG.i("commsId exists");
                    if (retrieveProfileInformationFromMessage.commsProvisionStatus == CommsProvisionStatus.PROVISIONED) {
                        z = true;
                    }
                    if (!z) {
                        LOG.i("not deferring commit comms user");
                        commitCommsUserWithCommsId(retrieveProfileInformationFromMessage);
                        LOG.d("Saved profile information into comms identity.");
                    } else {
                        LOG.i("deferring commit comms user");
                    }
                    Person additionalCommsAttributes = getAdditionalCommsAttributes(retrieveProfileInformationFromMessage);
                    LOG.i("committing deferred comms user");
                    commitCommsUserWithCommsId(additionalCommsAttributes);
                    LOG.d("Before Registering with DMDS.");
                    registerWithDMDS();
                    LOG.i("Registered with DMDS.");
                    setContactsSync(additionalCommsAttributes);
                    LOG.i("Updated the Contact Sync setting.");
                } else {
                    LOG.i("No CommsId, so saving rest of the info");
                    this.commsIdentityManager.mo358get().setNoCommsUser(retrieveProfileInformationFromMessage, SOURCE, false, false);
                }
                sendOOBECompleteNotification();
                LOG.i("Successfully processed the profile changed event.");
                recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_EVENT_SUCCESS, valueOf);
            } catch (Exception e) {
                d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                LOG.e("Exception occurred while processing message.", e);
                recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_EVENT_SUCCESS, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
            }
        } catch (Throwable th) {
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_EVENT_SUCCESS, Double.valueOf(d));
            throw th;
        }
    }

    @VisibleForTesting
    void registerWithDMDS() {
        Throwable th;
        double d;
        double d2 = 1.0d;
        try {
            resetRegisteredWithDMDS();
            this.deviceMetadataStoreRegistrar.mo358get().registerDeviceAndCommsIdWithDMDS(SOURCE);
        } catch (Exception e) {
            d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            try {
                LOG.e("Exception occurred when registering Device and CommsId with DMDS.", e);
                d2 = 0.0d;
            } catch (Throwable th2) {
                th = th2;
                recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_DMDS_REGISTRATION_SUCCESS, Double.valueOf(d));
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            d = 1.0d;
            recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_DMDS_REGISTRATION_SUCCESS, Double.valueOf(d));
            throw th;
        }
        recordCounterMetric(MetricKeys.OOBE_PROFILE_CHANGED_DMDS_REGISTRATION_SUCCESS, Double.valueOf(d2));
    }

    @VisibleForTesting
    void sendOOBECompleteNotification() {
        CommsEventEmitterBridge.sendOOBECompleteNotification();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d A[Catch: all -> 0x00a7, Exception -> 0x00a9, TRY_LEAVE, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0012, B:5:0x0018, B:7:0x001c, B:14:0x0032, B:16:0x003d, B:18:0x0041, B:19:0x0045, B:21:0x005a, B:23:0x005f, B:25:0x0087, B:27:0x009b), top: B:40:0x0012, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045 A[Catch: all -> 0x00a7, Exception -> 0x00a9, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0012, B:5:0x0018, B:7:0x001c, B:14:0x0032, B:16:0x003d, B:18:0x0041, B:19:0x0045, B:21:0x005a, B:23:0x005f, B:25:0x0087, B:27:0x009b), top: B:40:0x0012, outer: #1 }] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void setContactsSync(com.amazon.deecomms.oobe.Person r14) {
        /*
            r13 = this;
            java.lang.String r0 = "SHARED_PREFS"
            java.lang.String r1 = "comms.oobe.identityProfileChangedEvent.contactSyncSetting.success"
            java.lang.String r2 = r14.commsId
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r3 = r14.commsProvisionStatus
            android.content.Context r4 = r13.context
            boolean r4 = com.amazon.deecomms.util.DeviceInfo.isPhone(r4)
            java.lang.String r14 = r14.directedId
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r7 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.PROVISIONED     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r8 = 1
            r9 = 0
            if (r3 == r7) goto L2d
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r7 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.AUTO_PROVISIONED     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            if (r3 != r7) goto L2b
            dagger.Lazy<com.amazon.deecomms.core.CapabilitiesManager> r7 = r13.capabilitiesManager     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.Object r7 = r7.mo358get()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            com.amazon.deecomms.core.CapabilitiesManager r7 = (com.amazon.deecomms.core.CapabilitiesManager) r7     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            boolean r7 = r7.isAutoProvisioningEnabled()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            if (r7 == 0) goto L2b
            goto L2d
        L2b:
            r7 = r9
            goto L2e
        L2d:
            r7 = r8
        L2e:
            if (r4 != 0) goto L59
            if (r7 == 0) goto L59
            com.amazon.deecomms.common.network.acmsrecipes.GetPersonalDevices r10 = new com.amazon.deecomms.common.network.acmsrecipes.GetPersonalDevices     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r10.<init>()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            com.amazon.deecomms.contacts.model.GetPersonalDevicesResponse r14 = r10.getMasterDevice(r2, r14)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            if (r14 != 0) goto L45
            com.amazon.comms.log.CommsLogger r14 = com.amazon.deecomms.identity.ProfileSelectionServiceImpl.LOG     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r2 = "Get personal devices call returned null"
            r14.d(r2)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            goto L59
        L45:
            com.amazon.deecomms.util.DeviceInfo r2 = new com.amazon.deecomms.util.DeviceInfo     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r2.<init>()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.Context r10 = r13.context     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r2 = r2.getUniqueDeviceId(r10)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r14 = r14.getMasterDeviceId()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            boolean r14 = r2.equals(r14)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            goto L5a
        L59:
            r14 = r8
        L5a:
            com.amazon.comms.log.CommsLogger r2 = com.amazon.deecomms.identity.ProfileSelectionServiceImpl.LOG     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r10 = "Setting the shouldSupportContacts key to be: %b, isPhone: %b, isProvisioned: %b"
            r11 = 3
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r14)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r11[r9] = r12     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r11[r8] = r4     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r4 = 2
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r11[r4] = r7     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r4 = java.lang.String.format(r10, r11)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r2.i(r4)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.Context r2 = r13.context     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r0, r9)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r4 = "SHOULD_SUPPORT_CONTACTS_ON_DEVICES"
            android.content.SharedPreferences$Editor r14 = r2.putBoolean(r4, r14)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r14.apply()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.Context r14 = r13.context     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.SharedPreferences r14 = r14.getSharedPreferences(r0, r9)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.SharedPreferences$Editor r14 = r14.edit()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.lang.String r0 = "provisionedStatus"
            java.lang.String r2 = r3.name()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            android.content.SharedPreferences$Editor r14 = r14.putString(r0, r2)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r14.apply()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            goto Lb3
        La7:
            r14 = move-exception
            goto Lbb
        La9:
            r14 = move-exception
            r5 = 0
            com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.identity.ProfileSelectionServiceImpl.LOG     // Catch: java.lang.Throwable -> La7
            java.lang.String r2 = "Exception occurred while setting contacts sync setting."
            r0.e(r2, r14)     // Catch: java.lang.Throwable -> La7
        Lb3:
            java.lang.Double r14 = java.lang.Double.valueOf(r5)
            r13.recordCounterMetric(r1, r14)
            return
        Lbb:
            java.lang.Double r0 = java.lang.Double.valueOf(r5)
            r13.recordCounterMetric(r1, r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.identity.ProfileSelectionServiceImpl.setContactsSync(com.amazon.deecomms.oobe.Person):void");
    }
}
