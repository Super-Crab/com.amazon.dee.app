package com.amazon.deecomms.remoteConfig;

import com.amazon.alexa.location.utils.EndpointsUtil;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class RemoteConfigKeys {
    public static final String ACCESS_PFM_BLACKLIST = "Access.CommsPfmBlacklist";
    public static final String ACMS_ENDPOINT_KEY = "ACMS.Endpoints.PFM.${PFM}";
    public static final String ANNOUNCEMENT_MEDIA_ENDPOINT_KEY;
    public static final String ANNOUNCEMENT_MEDIA_STORAGE_TIMEOUT = "MediaStorage.Announcement.TimeoutsSec.Default";
    public static final String ARCUS_MAX_SYNC_DURATION_MILLISEC = "arcus.Arcus_Max_Sync_Duration_MilliSeconds";
    public static final String ARCUS_RETY_MAX_ATTEMPTS = "arcus.Arcus_Retry_Max_Attempts";
    public static final String ARCUS_SYNC_REPEAT_INTERVAL_MILLISEC = "arcus.Arcus_Sync_Repeat_Interval_MilliSeconds";
    public static final String ARCUS_SYNC_RETRY_INTERVAL_MILLISEC = "arcus.Arcus_Sync_Retry_Interval_MilliSeconds";
    public static final String ARCUS_VOICE_MSG_TRANSCRIPTION_PFM = "TranscriptionConsent.PFMs";
    public static final String CALL_RATING_CALL_FREQUENCY = "CallRating.ShowPerCallFrequency";
    public static final String CALL_RATING_MIN_CALL_DURATION = "CallRating.MinCallDurationSeconds";
    public static final String CALL_RATING_SCREEN_DURATION = "CallRating.MaxScreenDurationSeconds";
    public static final String CDS_ANDROID_SDK_MAX_RETRY = "CloudDriveService.MaxRetryCount.AndroidSDK";
    public static final String CONDITIONS_OF_USE_URL_KEY = "ExternalUrls.ConditionsOfUse.PFM.${PFM}";
    public static final String CONTACTS_CLOUD_MAX_LENGTH = "Contacts.DefaultContactFieldsMaxLength";
    public static final String CONTACTS_MAX_CONCURRENT_UPLOAD = "Contacts.MaxConcurrentUpload";
    public static final String CONTACTS_PHONE_NUMBER_DB_MAX_LENGTH = "Contacts.PhoneNumberMaxLength";
    public static final String CONTACTS_REFRESH_INTERVAL_SEC = "Contacts.RefreshIntervalSec";
    public static final String CONTACTS_UPLOAD_BATCH_SIZE = "Contacts.UploadBatchSize";
    public static final String DUI_ENDPOINT_KEY = "DUI.Endpoints.PFM.${PFM}";
    public static final String ENHANCED_PROCESSING_LEARN_MORE = "ExternalUrls.EnhancedProcessingLearnMore.Path";
    public static final String ENHANCED_PROCESSING_LEARN_MORE_PFM = "ExternalUrls.EnhancedProcessingLearnMore.PFM.${PFM}";
    public static final String FAQ_URL_KEY = "ExternalUrls.FAQ.PFM.${PFM}";
    public static final String GET_DEVICES_CACHE_TIMEOUT = "Presence.RefreshIntervalSec.GetDevices";
    public static final String IDENTITY_FIRST_NAME_MAX_LENGTH = "Identity.FirstNameMaxLength";
    public static final String IDENTITY_LAST_NAME_MAX_LENGTH = "Identity.LastNameMaxLength";
    public static final String MESSAGES_FETCH_BATCH_SIZE = "Messages.FetchBatchSize";
    public static final String MESSAGES_MAX_DELAY_TRANSCRIBING_IN_SEC = "Messages.TranscriptionTimeout";
    public static final String MESSAGE_MEDIA_STORAGE_TIMEOUT = "MediaStorage.Message.TimeoutsSec.Default";
    public static final String MESSAGING_MEDIA_ENDPOINT_KEY = "MediaStorage.Message.Endpoints.PFM.${PFM}";
    public static final String PRESENCE_CACHE_TIMEOUT = "Presence.RefreshIntervalSec.GetActiveContacts";
    public static final String SIP_ENDPOINT_KEY = "SIPProxy.Endpoints.PFM.${PFM}";
    public static final String SIP_PROXY_REGISTRATION_PORT_NUMBER = "arcus.SIPProxy_Registration_PortNumber";
    public static final String SIP_PROXY_REGISTRATION_TIMEOUT_SEC = "SIPProxy.Registration.TimeoutSec";
    public static final String SIP_PSTN_URI_FORMAT_KEY = "SIPProxy.PstnUriFormats.PFM.${PFM}";
    public static final String SOUND_EFFECTS_MEDIA_ENDPOINT_KEY = "MediaStorage.SoundEffects.Endpoints.PFM.${PFM}";
    public static final String TERMS_OF_USE_URL_KEY = "ExternalUrls.TermsOfUse.PFM.${PFM}";

    /* loaded from: classes12.dex */
    public final class Telemetry {
        public static final String BATCH_SIZE = "Telemetry.BatchSize";
        public static final String SAMPLING_INTERVAL_SEC = "Telemetry.SamplingIntervalSec";
        public static final String SEND_INTERVAL_SEC = "Telemetry.SendingIntervalSec";

        private Telemetry() {
        }
    }

    static {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("MediaStorage.Announcement.Endpoints.DataRegion.");
        outline1.append(getDataRegion());
        ANNOUNCEMENT_MEDIA_ENDPOINT_KEY = outline1.toString();
    }

    private RemoteConfigKeys() {
    }

    private static String getDataRegion() {
        char c;
        String apiGatewayEndpoint = ((EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class)).getApiGatewayEndpoint();
        int hashCode = apiGatewayEndpoint.hashCode();
        if (hashCode != -1157106121) {
            if (hashCode == 1962670150 && apiGatewayEndpoint.equals(EndpointsUtil.ENDPOINT_FE_PROD)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (apiGatewayEndpoint.equals(EndpointsUtil.ENDPOINT_EU_PROD)) {
                c = 0;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? RegionUtil.REGION_STRING_NA : RegionUtil.REGION_STRING_FE : RegionUtil.REGION_STRING_EU;
    }
}
