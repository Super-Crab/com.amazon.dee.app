package com.amazon.deecomms.common.network;
/* loaded from: classes12.dex */
public class AppUrl {
    public static final String ANNOUNCEMENT_MEDIA_DOWNLOAD_RESOURCE_API = "/v1/domains/announcements/media/{0}";
    public static final String ANNOUNCEMENT_MEDIA_UPLOAD_RESOURCE_API = "/v1/domains/announcements/media";
    public static final String AVAILABLE_ACTIONS = "/storytime/available-actions/{0}?participantId={1}";
    public static final String CALL_CONFIGURATIONS_URL = "/configurations/{0}";
    public static final String CAPTURE_CALLING_BIZ_METRICS = "/users/{0}/metrics/calling";
    public static final String CAPTURE_CALL_QUALITY_BIZ_METRICS = "/users/{0}/metrics/callquality";
    public static final String CONTACTS_URL = "/users/{0}/contacts";
    public static final String CONVERSATION_URL = "/users/{0}/conversations/{1}";
    public static final String GET_CONTACT_PRESENCE = "/users/contacts/presence?onlyDropInContacts=true&includeInActiveContacts=true&returnAsHomegroupContacts=true";
    public static final String GET_CONTACT_URL = "/users/{0}/contact/{1}?view=full";
    public static final String GET_CONVERSATIONS_FROM_COMMS_ID = "/users/{0}/conversations";
    public static final String GET_DEVICES = "/homegroups/{0}/devices?target=false";
    public static final String GET_DEVICE_ANNOUNCEMENT_PREFERENCES = "/devicesTypes/{0}/deviceId/{1}/preferences?devicePreferences=announcements";
    public static final String GET_DEVICE_COMMS_PREFERENCES = "/devicesTypes/{0}/deviceId/{1}/preferences?devicePreferences=communications";
    public static final String GET_HOMEGROUP_MEMBERS = "/homegroups/{0}";
    public static final String GET_IDENTITY_V2 = "/users/{0}/identities";
    public static final String GET_OR_CREATE_URL = "/users/{0}/contacts/{1}";
    public static final String GET_PERSONAL_DEVICES = "/users/{0}/devices?contactSyncType=all";
    public static final String GET_PREFERENCE_FOR_CONTACT = "/users/{0}/contacts/{1}/preferences/{2}";
    public static final String GET_RECENT_COMMUNICATIONS = "/contacts/users/{0}/recentCommunications?maximumEntries={1}&lastNHours={2,number,#}";
    public static final String GET_RELATIONSHIPS = "/users/{0}/contacts/relationships";
    public static final String GET_SIP_AUTH_TOKEN = "/auth-token/{0}";
    public static final String GET_TARGET_DEVICE = "/homegroups/{0}/devices?target=true";
    public static final String GET_TELEMETRY_CREDENTIALS = "/telemetry/{0}/credentials/";
    public static final String GET_TURN_CREDENTIALS_FROM_IDENTITY = "/users/{0}/endpoints";
    public static final String GET_USS_SETTING = "/unifiedSettings/{0}/{1}/{2}";
    public static final String IDENTITY_PREFERENCES = "/users/{0}/preferences/{1}";
    public static final String INBOUND_ANNOUNCEMENT_PLAYBACK_API = "/users/{0}/announcementPlaybacks";
    public static final String INCLUDE_HOMEGROUP = "includeHomegroup";
    public static final String INITIATE_OUTBOUND_CALL = "/calling/{0}/initiateOutboundCall";
    public static final String LATEST = "latest";
    public static final String MARK_MESSAGE_READ = "/users/{0}/conversations/{1}/messages";
    public static final String MEDIA_RESOURCE_API = "/v1/media/{0}";
    public static final String MESSAGES_URL = "/users/{0}/conversations/{1}/messages";
    public static final String MODIFIED_BEFORE_TIME = "modifiedBeforeDate";
    public static final String MODIFIED_SINCE_TIME = "modifiedSinceDate";
    public static final String MPU_ENABLED = "/calling/settings/mpu_enabled?anyCommsId={0}&callProvider={1}";
    public static final String OOBE_ACCOUNTS = "/accounts";
    public static final String OOBE_CVF = "/cvf";
    public static final String OOBE_PROVISION_COMMSID = "/accounts/{0}/users";
    public static final String PARAM_MOST_RECENT_MSG_ID = "mostRecentMessageId";
    public static final String PATCH_PREFERENCE = "/users/{0}/contacts/{1}/preferences";
    public static final String PUSH_NOTIFICATION_DEREGISTER = "/users/{0}/devices/{1}/pushApplications/{2}";
    public static final String PUSH_NOTIFICATION_REGISTER = "/users/{0}/devices/{1}/pushApplications";
    public static final String SEND_ANNOUNCEMENTS = "/users/{0}/announcements";
    public static final String SEND_MESSAGE = "/users/{0}/conversations/{1}/messages";
    public static final String SET_DEVICE_ACCOUNT_COMMS_ID = "/users/{0}/sessions";
    public static final String TRIGGER_ACTION = "/storytime/trigger-action/{0}";
    public static final String UPDATE_IDENTITY = "/users/{0}/identities";
    public static final String UPLOAD_ANNOUNCEMENT_MEDIA_RESOURCE_API = "/v1/reverb-audio/";
    public static final String UPLOAD_MEDIA_RESOURCE_API = "/v1/media/";
    private final Endpointer mEndpointer;

    /* loaded from: classes12.dex */
    public static final class ACMS {

        /* loaded from: classes12.dex */
        public static final class QueryParam {

            /* loaded from: classes12.dex */
            public static final class Keys {
                public static final String DEVICE_CLIENT_TYPE = "deviceClientType";
                public static final String DEVICE_TYPE = "deviceType";
                public static final String MESSAGE_BATCH_COUNT = "count";
                public static final String MESSAGE_START_ID = "startId";
                public static final String NETWORK = "network";
                public static final String SORT_ORDER = "sort";

                private Keys() {
                }
            }

            /* loaded from: classes12.dex */
            public static final class Values {
                public static final String ASCENDING = "asc";

                private Values() {
                }
            }

            private QueryParam() {
            }
        }

        private ACMS() {
        }
    }

    public AppUrl(Endpointer endpointer) {
        this.mEndpointer = endpointer;
    }

    public String getACMSServiceURL() {
        return this.mEndpointer.getACMSEndpoint();
    }

    public String getDynamicUIServiceURL() {
        return this.mEndpointer.getDUIEndpoint();
    }

    public String getSIPProxyDomain() {
        return this.mEndpointer.getSipProxyEndpoint();
    }
}
