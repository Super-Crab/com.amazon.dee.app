package com.amazon.deecomms.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public final class Constants {
    public static final String ABOVE_MIN_VERSION = "X-Alexa-AboveMinVersion";
    public static final String ACCEPT_INCOMING_AUDIO_CALL = "ACCEPT_INCOMING_AUDIO_CALL";
    public static final String ACCEPT_INCOMING_VIDEO_CALL = "ACCEPT_VIDEO_CALL";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_PFM = "Accept-PFM";
    public static final String ACCESSORIES = "ACCESSORIES";
    public static final String ACCESSORY_MUFFIN = "A303PJF6ISQ7IC";
    public static final String ACCESSORY_OOBE = "ACCESSORY_OOBE";
    public static final String ACCESSORY_PRIVACY_INITIAL_STATUS = "initial";
    public static final String ACCESSORY_PRIVACY_STATUS = "privacy_status";
    public static final String ACCESSORY_PUGET = "A16MZVIFVHX6P6";
    public static final String ACCESSORY_UPDATED = "ACCESSORY_UPDATED";
    public static final String ACCESSORY_ZEPHYR = "A3IYPH06PH1HRA";
    public static final String ACCOUNT_DEREGISTER_ACTION = "ACCOUNT_DEREGISTER_ACTION";
    public static final String ACMS_CONTACTS_QUERY_INCLUDE_BLOCK_STATUS = "includeBlockStatus";
    public static final String ACMS_CONTACTS_QUERY_INCLUDE_HG = "includeHomeGroupMembers";
    public static final String ACMS_CONTACTS_QUERY_INCLUDE_MERGED_CONTACTS = "includeMergedContacts";
    public static final String ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL = "includePreferencesByLevel";
    public static final String ACMS_CONTACT_PREFERENCE_LEVEL_HG = "HomeGroup";
    public static final String ACMS_CONTENT_TYPE_CREATE_ENDPOINTS = "application/json";
    public static final String ACMS_CONTENT_TYPE_DELETE_CONVERSATIONS_V2 = "application/vnd.deleteconversation.v2+json";
    public static final String ACMS_CONTENT_TYPE_IMPORT_CONTACTS_V2 = "application/vnd.importcontacts.v2+json";
    public static final String ACMS_CONTENT_TYPE_JSON = "application/json";
    public static final String ACMS_DEFAULT_CONTACT_PREFERENCE_LEVEL = "None";
    public static final String ACMS_HEADER_CLIENT_ID = "X-Amzn-ClientId";
    public static final String ACMS_HEADER_REQUEST_ID = "X-Amzn-RequestId";
    public static final String ACMS_MEDIA_AUTH_TOKEN_TYPE = "MediaService";
    public static final String ACMS_SIP_AUTH_TOKEN_TYPE = "SIP";
    public static final String ALEXA_BETA = "ALEXA_BETA";
    public static final String ALEXA_FRIENDS_AND_FAMILY = "ALEXA_FRIENDS_AND_FAMILY";
    public static final String ALEXA_MEDIA_FOLDER_NAME = "Alexa Shared Photos";
    public static final String ALEXA_SERVICE_CONNECTION_CONNECTED = "ASC_CONNECTED";
    public static final String ALEXA_SERVICE_CONNECTION_DISCONNECTED = "ASC_DISCONNECTED";
    public static final String AMAZON_EMBER_REGULAR_FONT = "fonts/AmazonEmber/Amazon_Ember_Rg.ttf";
    public static final String AMPD = "AMPD";
    public static final String AMP_KEY = "amznMessage";
    public static final String APPLICATION_FOREGROUND_CHECKED_ACTION = "APPLICATION_FOREGROUND_CHECKED_ACTION";
    public static final String APP_ARCUS_CONFIG_ID = "arn:aws:remote-config:us-west-2:412793207129:appConfig:ayuw5hfh";
    public static final String APP_ARCUS_CONFIG_ID_QA = "arn:aws:remote-config:us-west-2:412793207129:appConfig:azq8123c";
    public static final String AUDIO_ANNOUNCEMENT_MESSAGE_TYPE = "announcement/audio";
    public static final String AUDIO_ANNOUNCEMENT_PREFIX = "Download_Announcement_";
    public static final String AUDIO_CACHE_DIR = "audio-cache";
    public static final String AUDIO_DOWNLOAD_RESULT = "AUDIO_DOWNLOAD_RESULT";
    public static final String AUDIO_DOWNLOAD_RESULT_RECEIVER = "AUDIO_DOWNLOAD_RESULT_RECEIVER";
    public static final String AUDIO_GENERIC_PREFIX = "Download_File_";
    public static final String AUDIO_MEDIA_ID = "MediaId";
    public static final String AUDIO_MESSAGE_MESSAGE_TYPE = "message/audio";
    public static final String AUDIO_MESSAGE_PREFIX = "Download_AudioFile_";
    public static final int AUTHTOKEN_EXPIRY_ON_EMPTY_CREATED = 3600000;
    public static final String AUTH_ACT_AS_HEADER_FOR_MEDIA_STORAGE_SERVICE = "X-Authorization-Act-As";
    public static final String AUTH_DOMAIN_AU = ".amazon.com.au";
    public static final String AUTH_DOMAIN_BR = ".amazon.com.br";
    public static final String AUTH_DOMAIN_CA = ".amazon.ca";
    public static final String AUTH_DOMAIN_DE = ".amazon.de";
    public static final String AUTH_DOMAIN_DEVELOPMENT = "development.amazon.com";
    public static final String AUTH_DOMAIN_ES = ".amazon.es";
    public static final String AUTH_DOMAIN_FR = ".amazon.fr";
    public static final String AUTH_DOMAIN_IN = ".amazon.in";
    public static final String AUTH_DOMAIN_IT = ".amazon.it";
    public static final String AUTH_DOMAIN_JP = ".amazon.co.jp";
    public static final String AUTH_DOMAIN_MX = ".amazon.com.mx";
    public static final String AUTH_DOMAIN_NL = ".amazon.nl";
    public static final String AUTH_DOMAIN_UK = ".amazon.co.uk";
    public static final String AUTH_DOMAIN_US = ".amazon.com";
    public static final String AUTH_PFM_AU = "AU";
    public static final String AUTH_PFM_BR = "BR";
    public static final String AUTH_PFM_CA = "CA";
    public static final String AUTH_PFM_DE = "DE";
    public static final String AUTH_PFM_ES = "ES";
    public static final String AUTH_PFM_FR = "FR";
    public static final String AUTH_PFM_GB = "GB";
    public static final String AUTH_PFM_IN = "IN";
    public static final String AUTH_PFM_IT = "IT";
    public static final String AUTH_PFM_JP = "JP";
    public static final String AUTH_PFM_MX = "MX";
    public static final String AUTH_PFM_NL = "NL";
    public static final String AUTH_PFM_US = "US";
    public static final String AUTH_STRING_FOR_MEDIA_STORAGE_SERVICE = "Authorization";
    public static final int AUTH_TOKEN_RENEW_DELTA = 300000;
    public static final String AUTOMATIC_DETECTION = "AUTOMATIC_DETECTION";
    public static final String BACKGROUND = "BACKGROUND";
    public static final String BEGIN_CALL_TIMER = "BEGIN_CALL_TIMER";
    public static final String BUNDLE_ANNOUNCEMENT_ID = "announcementId";
    public static final String BUNDLE_DEVICE_TYPE = "deviceType";
    public static final String BUNDLE_IMAGE_DETAIL_VIEW_PARAMETERS = "imageDetailViewParameters";
    public static final String BUNDLE_KEY_CAN_I_DROP_IN_ON_HIM_LIST = "targetCanIDropInOnHimList";
    public static final String BUNDLE_KEY_COMPANY = "company";
    public static final String BUNDLE_KEY_COMPANY_NAMES = "targetCompanyNames";
    public static final String BUNDLE_KEY_CONTACT_ID = "contact_id";
    public static final String BUNDLE_KEY_CONTACT_NAME_KEY = "contact_name";
    public static final String BUNDLE_KEY_CONTACT_RELATIONSHIP = "BUNDLE_KEY_CONTACT_RELATIONSHIP";
    public static final String BUNDLE_KEY_CONVERSATION_ID = "targetConversation";
    public static final String BUNDLE_KEY_FIRST_NAMES = "targetFirstNames";
    public static final String BUNDLE_KEY_IS_HOMEGROUP_LIST = "targetIsHomegroupList";
    public static final String BUNDLE_KEY_LAST_NAMES = "targetLastNames";
    public static final String BUNDLE_KEY_MY_PROFILE = "BUNDLE_KEY_MY_PROFILE";
    public static final String BUNDLE_KEY_NICK_NAMES = "targetNickNames";
    public static final String BUNDLE_KEY_NOTIFICATION_ID = "notificationId";
    public static final String BUNDLE_KEY_NOTIFICATION_RECIPIENT = "recipient";
    public static final String BUNDLE_KEY_NOTIFICATION_REDIRECT_PATH = "notification_redirect_path";
    public static final String BUNDLE_KEY_NOTIFICATION_TARGET = "target";
    public static final String BUNDLE_KEY_OWNER_COMMS_ID = "ownerCommsId";
    public static final String BUNDLE_KEY_PARTICIPANTS = "targetPariticipants";
    public static final String BUNDLE_KEY_PHONE_NUMBERS = "phoneNumberList";
    public static final String BUNDLE_KEY_REACT_NATIVE_ROUTE = "route";
    public static final String BUNDLE_KEY_REACT_NATIVE_ROUTE_NAME = "routeName";
    public static final String BUNDLE_KEY_RECIPIENT_ID = "targetRecipient";
    public static final String BUNDLE_KEY_SEND_AS_COMMS_ID = "targetSendAsCommsID";
    public static final String BUNDLE_KEY_SHARED_CONTENT_IMAGE_PAYLOAD = "imagePayload";
    public static final String BUNDLE_KEY_VIEW_AS_COMMS_ID = "targetViewAsCommsID";
    public static final String BUNDLE_RECEIVER_TAG = "receiver";
    public static final String BUNDLE_SERIAL_NUMBER = "serialNumber";
    public static final String CALLEE_COMMS_ID = "CALLEE_COMMS_ID";
    public static final String CALLEE_SIP_URI = "CALLEE_SIP_URI";
    public static final String CALLER_COMMS_ID = "CALLER_COMMS_ID";
    public static final String CALLER_SIP_URI = "CALLER_SIP_URI";
    public static final String CALLING_NEW_ARCHITECTURE = "octopus";
    public static final String CALL_ADDED = "CALL_ADDED";
    public static final String CALL_DURATION_KEY = "Duration";
    public static final String CALL_ENDED_BECAUSE_OF_ERROR = "CALL_ENDED_BECAUSE_OF_ERROR";
    public static final String CALL_END_STATUS = "CALL_END_STATUS";
    public static final String CALL_ID = "CALL_ID";
    public static final String CALL_PERMISSION_REQUEST_ACTION = "CALL_PERMISSION_REQUEST_ACTION";
    public static final String CALL_PROVIDER = "CALL_PROVIDER";
    public static final String CALL_RATING = "CALL_RATING";
    public static final String CALL_RATING_SCREEN_DURATION = "CALL_RATING_SCREEN_DURATION";
    public static final String CALL_START_TIME = "CALL_START_TIME";
    public static final String CALL_TYPE = "CALL_TYPE";
    public static final int CALL_VIBRATION_REPEAT_INDEX = 0;
    public static final String CANCEL_ANY_CALL = "CANCEL_ANY_CALL";
    public static final String CANCEL_OUTGOING_CALL = "CANCEL_OUTGOING_CALL";
    public static final String CDS_ACCOUNT_SETUP_SUCCESSFUL = "CDS_ACCOUNT_SETUP_SUCCESSFUL";
    public static final String CDS_CONFLICT_ERROR_ID_ALREADY_EXISTS = "ID_ALREADY_EXISTS";
    public static final String CDS_CONFLICT_ERROR_MD5_DUPLICATE = "MD5_DUPLICATE";
    public static final String CDS_CONFLICT_ERROR_UPLOAD_ALREADY_EXISTS = "NAME_ALREADY_EXISTS";
    public static final String CDS_PARENT_NODE_ID_NOT_FOUND = "PARENT_NODE_ID_NOT_FOUND";
    public static final String CDS_PARENT_NODE_IN_TRASH = "PARENT_NODE_IN_TRASH ";
    public static final String CHAPMAN_CALL = "ChapmanCall";
    public static final String CHAPMAN_GROUP_IDENTITY_TYPE = "HOMEGROUP";
    public static final int CHAR_LIMIT_ANNOUNCEMENT = 148;
    public static final int CHAR_LIMIT_TEXT_MESSAGE = 1000;
    public static final String CHILD_COMMS_ID = "CHILD_COMMS_ID";
    public static final String CHILD_CONTACT_ENTRY_KEY = "CHILD_CONTACT_ENTRY_KEY";
    public static final String CHILD_CONTACT_ID = "CHILD_CONTACT_ID";
    public static final String CHILD_CONTACT_OWNER_NAME = "CHILD_CONTACT_OWNER_NAME";
    public static final String CHILD_CONTACT_RELATIONSHIP_BASE_PREF = "CHILD_CONTACT_RELATIONSHIP_BASE_PREF";
    public static final String CHILD_CONTACT_RELATIONSHIP_LIST = "CHILD_CONTACT_RELATIONSHIP_LIST";
    public static final String CLEANUP_CALL = "CLEANUP_CALL";
    public static final String CLEAR_CALL_ACTIVITY = "CLEAR_CALL_ACTIVITY";
    public static final String CLIENT_ID_PREF = "CLIENT_ID_PREF";
    public static final String CLIENT_MESSAGE = "CLIENT_MESSAGE";
    public static final String CLIENT_MESSAGE_TYPE = "CLIENT_MESSAGE_TYPE";
    public static final String COMMS_ANDROID_APP_DEVICE_TYPE = "A2TF17PFR55MTB";
    public static final String COMMS_BROADCAST_NOTIFICATION = "com.amazon.deecomms.receiver.COMMS_BROADCAST_NOTIFICATION";
    public static final String COMMS_DEVICE_CALLING_SERVICE_REGISTERED_NOTIFICATION = "com.amazon.deecomms.receiver.COMMS_DEVICE_CALLING_SERVICE_REGISTERED_NOTIFICATION";
    public static final String COMMS_ID = "COMMS_ID";
    public static final String COMMS_ID_PREF = "COMMS_ID_PREF";
    public static final String COMMS_ID_PREFIX = "amzn1.comms.";
    public static final String COMMS_PERMISSION = "com.amazon.deecomms.permission";
    public static final String COMMS_SEND_SHARING_ACTION = "com.amazon.intent.action.SEND";
    public static final String COMMS_SUPPORT = "COMMS_SUPPORT";
    public static final String COMMS_VOICE_NAV_ACTION = "com.amazon.intent.action.VOICENAV";
    public static final String CONTACTLIST_FRAGMENT_TAG = "CONTACTLIST_FRAGMENT_TAG";
    public static final String CONTACT_ENTRY_KEY = "CONTACT_ENTRY_KEY";
    public static final String CONTACT_IMPORT = "CONTACT_IMPORT";
    public static final String CONTACT_LIST_LAUNCH_FRAGMENT = "CONTACT_LIST_LAUNCH_FRAGMENT";
    public static final String CONTACT_NAME = "CONTACT_NAME";
    public static final String CONTACT_PREF_DROP_IN = "drop_in";
    public static final String CONTACT_SYNC_PREF = "contactSyncPreference";
    public static final String CONTACT_TARGET = "ContactTarget";
    public static final String CONTACT_UPDATE = "CONTACT_UPDATE";
    public static final String CONVERSATION_ID = "conversationId";
    public static final int CONVERSATION_NOTIFICATION_ID = 10;
    public static final String CONV_TAB_PRESSED = "CONVERSATION_TAB_PRESSED";
    public static final String COUNTRY_CODE_PREF = "COUNTRY_CODE_PREF";
    public static final String DEFAULT_ANNOUNCEMENT_NOTIFICATION_ID = "DEFAULT_ANNOUNCEMENT_NOTIFICATION_ID";
    public static final String DEFAULT_COR_KEY_FOR_ENDPOINTS = "Default";
    static final String DEFAULT_DEVICE_TYPE_ID = "A1WS7SWYYT4UG4";
    public static final char DEFAULT_IMAGE_CHAR = '?';
    static final String DEFAULT_LOCALE = "en-US";
    public static final String DEFAULT_MESSAGE_NOTIFICATION_ID = "DEFAULT_MESSAGE_NOTIFICATION_ID";
    public static final String DEFAULT_PFM_KEY_FOR_ENDPOINTS = "Default";
    public static final String DENIED = "denied";
    public static final String DEVICE_COMMUNICATIONS_OFF = "DEVICE_COMMUNICATIONS_OFF";
    public static final String DEVICE_GRUU = "DEVICE_GRUU";
    public static final String DEVICE_REGISTERED_WITH_DMDS = "DEVICE_REGISTERED_WITH_DMDS";
    public static final String DEVICE_SETTINGS_ROUTE = "v2/device-settings/communications/";
    public static final String DEVICE_SHAPE = "rectangle";
    public static final String DEVICE_TARGET = "DeviceTarget";
    public static final String DEVICE_VERSION = "X-Alexa-DeviceVersion";
    public static final String DIRECTED_ID_PREF = "DIRECTED_ID_PREF";
    public static final String DISABLE_IN_CALL_COMMAND = "DISABLE_IN_CALL_COMMAND";
    public static final String DISMISSED = "dismissed";
    public static final String DND_MODE = "zen_mode";
    public static final String DOMAIN = "DOMAIN";
    public static final String DOWNLOAD_ACT_AS_COMMS_ID = "DownloadActAsCommsID";
    public static final String DTLS = "DTLS";
    public static final int DTMF_TONE_LENGTH = 500;
    public static final int DTMF_TONE_PAUSE = 500;
    public static final String EMAIL_PREF = "EMAIL_PREF";
    public static final String EMPTY_STRING = "";
    public static final String ENABLE_ANNOUNCEMENT_PUSH_NOTIFICATION = "enableAnnouncementPushNotification";
    public static final String END_ACTIVE_CALL = "END_ACTIVE_CALL";
    public static final int END_CALL_FADE_DURATION = 1000;
    public static final int END_RATE_CALL_FADE_DURATION = 4000;
    public static final String EPMS_APP_ID_PREF = "EPMS_APP_ID_PREF";
    public static final String FIRST_NAME_PREF = "FIRST_NAME_PREF";
    public static final String FOREGROUND = "FOREGROUND";
    public static final String FRAGMENT_ACTIVE_CALL_KEY = "FRAGMENT_ACTIVE_CALL_KEY";
    public static final String FRAGMENT_CALL_BOTTOM_SHEET = "FRAGMENT_CALL_BOTTOM_SHEET";
    public static final String FRAGMENT_CALL_INITIATED_KEY = "FRAGMENT_CALL_INITIATED_KEY";
    public static final String FRAGMENT_CONTACT_CARD = "FRAGMENT_CONTACT_CARD";
    public static final String FRAGMENT_END_CALL_KEY = "FRAGMENT_END_CALL_KEY";
    public static final String FRAGMENT_INCOMING_CALL_KEY = "FRAGMENT_INCOMING_CALL_KEY";
    public static final String FRAGMENT_OUTGOING_CALL_KEY = "FRAGMENT_OUTGOING_CALL_KEY";
    public static final String FRAGMENT_START_IMAGE_SHARE = "FRAGMENT_START_IMAGE_SHARE";
    public static final String FRAGMENT_START_NEW_CONVERSATION = "FRAGMENT_START_NEW_CONVERSATION";
    public static final String GCM_MESSAGE_ID_KEY = "google.message_id";
    public static final String GCM_MESSAGE_SENT_TIME = "google.sent_time";
    public static final String GET_MPU_ENABLED = "get_mpu_enabled";
    public static final String GET_SETTING = "get_setting";
    public static final String GONE = "GONE";
    public static final String GROUPID_HEADER = "X-Alexa-GroupId";
    public static final String GROUP_CALL = "GroupCall";
    public static final String GROUP_CONCAT_SEPARATOR = "<><><>";
    public static final String GROUP_CONTACT = "GROUP_CONTACT";
    public static final String GROUP_TARGET = "GroupTarget";
    public static final String GRUU_INDICATOR = ";gr";
    public static final String HASHED_COMMS_ID_PREF = "COMMS_ID_HASHED_PREF";
    public static final String HAS_DEVICES_PREF = "HAS_DEVICES_PREF";
    public static final String HAS_ENCRYPTION_ISSUE = "has_encryption_issue";
    public static final String HOMEGROUP_ID_PREF = "HOMEGROUP_ID_PREF";
    public static final String IDENTITY_PREFERENCE_COBO_PRIVATE_NUMBER = "comms_cobo_private_calling";
    public static final String IDENTITY_PREFERENCE_DROP_IN_DISC = "show_drop_in_announcement";
    public static final String IDENTITY_PREFERENCE_SHARING_FUTE = "show_sharing_ftue";
    public static final String IDENTITY_PREFERENCE_VOICE_MSG_CONSENT = "comms_transcription_improvement_consent";
    public static final String IGNORE_CALL_ID = "IGNORE_CALLID";
    public static final String IMAGE_CROP_PICKER_CACHE_DIR = "react-native-image-crop-picker";
    public static final String INBOUND_ANNOUNCEMENT_ENABLEMENT = "Alexa.Comms.Accessories.inboundAnnouncementsEnablement";
    public static final String INCOMING = "Incoming";
    public static final String INITIAL_CONTACTS_DOWNLOAD_SUCCESS = "initial_contacts_download";
    public static final String INITIAL_CONTACTS_IMPORTS_DONE_PREF = "initial_contacts_import_done";
    public static final String INITIAL_CONTACTS_PERMISSION_PROMPT_SHOWN_PREF = "initial_contacts_permission_dialog_shown";
    public static final String INITIAL_CONVERSATION_SYNC_SUCCESS = "initial_conversation_sync";
    public static final int INVALID_RESOURCE_ID = 0;
    public static final int IN_CLAUSE_MAX_ITEM_SIZE = 999;
    public static final String IS_APP_IN_FOREGROUND = "IS_APP_IN_FOREGROUND";
    public static final String IS_VIDEO_CALL = "IS_VIDEO_CALL";
    public static final String JP_PFM = "JP";
    public static final String KEY_APPLICATION_FOREGROUND_CHANGED = "KEY_APPLICATION_FOREGROUND_CHANGED";
    public static final String KEY_CALLER_NAME = "caller_name";
    public static final String KEY_CALL_INITIATION_SCREEN_NAME = "callInitiationScreenName";
    public static final int KEY_DISMISS_INCOMING_HEADUP_NOTIFICATION = 100;
    public static final String KEY_DROP_IN_NOT_AVAILABLE = "dropInNotAvailable";
    public static final String KEY_GRUU = "gruu";
    public static final String KEY_HAVE_ANSWERED_CALL = "have_answered_call";
    public static final String KEY_HAVE_DECLINED_CALL = "have_declined_call";
    public static final String KEY_NAME = "name";
    public static final String KEY_RECIPIENT_COMMS_ID = "recipientCommsId";
    public static final String KEY_RECIPIENT_PHONE_NUMBER = "recipientPhoneNumber";
    public static final String KEY_STARTED_FROM_PENDING_INTENT = "have_started_from_pending_intent";
    public static final String LAST_CONTACT_SYNCED_TIME_PREF = "LAST_CONTACT_SYNCED_TIME";
    public static final String LAST_CONVERSATIONS_SYNC_TIME = "LAST_CONVERSATIONS_SYNC_TIME";
    public static final String LAST_FORCED_CONTACT_UPDATE_VERSION = "LAST_FORCED_CONTACT_UPDATE_VERSION";
    public static final String LAST_NAME_PREF = "LAST_NAME_PREF";
    public static final String LAST_SQLCIPHER_UPGRADED_VERSION = "LAST_SQLCIPHER_UPGRADED_VERSION";
    public static final String LAST_SYNCED_CONTACT_ID_PREF = "LAST_SYNCED_CONTACT_ID_PREF";
    public static final String LAST_SYNCED_DEVICE_CONTACT_COUNT_PREF = "LAST_SYNCED_DEVICE_CONTACT_COUNT";
    public static final String LAST_USER_INFO_PREF = "LAST_USER_INFO_PREF";
    public static final String LAST_USER_INFO_SYNC_TIME = "LAST_USER_INFO_SYNC_TIME";
    public static final String LAUNCH_FRAGMENT_KEY = "LAUNCH_FRAGMENT_KEY";
    public static final String LIB_NAME = "AlexaBetaAndroidApp";
    public static final String LOCAL_COMMS_ID = "LOCAL_COMMS_ID";
    public static final String LOG_TAG = "KComms";
    public static final String MAIN_ACTIVITY = "com.amazon.dee.app.ui.main.MainActivity";
    public static final String MAIN_IN_FG = "MAIN_IN_FG";
    public static final String MAKE_OUTGOING_CALL = "MAKE_OUTGOING_CALL";
    public static final String MANUAL_DETECTION = "MANUAL_DETECTION";
    public static final String MARKETPLACE_ID_PREF = "MARKETPLACE_ID_PREF";
    public static final int MAX_BUFFERED_FRAMES_FOR_FIREOS = 10;
    public static final int MAX_EMAILS_TO_IMPORT = 25;
    public static final int MAX_HTTP_REQUEST_ATTEMPTS = 2;
    public static final int MAX_PHONE_NUMBERS_TO_IMPORT = 25;
    public static final double MAX_PHONE_SIZE_IN_INCHES = 6.5d;
    public static final String MEDIA_RELAY_INFO_JSON = "MEDIA_RELAY_INFO";
    public static final String MEDIA_SERVICE_DEVICE_ID = "X-Amzn-DeviceId";
    public static final String MEDIA_SERVICE_REQUEST_ID = "X-Amzn-RequestId";
    public static final String MEDIA_STREAM_TYPE_AUDIO = "AUDIO";
    public static final String MEDIA_STREAM_TYPE_VIDEO = "VIDEO";
    public static final String MESSAGE_ID = "messageId";
    public static final String MESSAGE_SYNC_FORCE = "MESSAGE_SYNC_FORCE";
    public static final String MESSAGE_SYNC_SOURCE = "MESSAGE_SYNC_SOURCE";
    public static final int MILLIS_IN_SECONDS = 1000;
    public static final String MIME_AUDIO_OPUS = "audio/opus";
    public static final String MIME_TYPE_UNKNOWN_PERMISSION_DENIED = "unknown/permission-denied";
    public static final String MIME_VIDEO_H264 = "video/avc";
    public static final String MIME_VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String MIME_VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String MISSING_COOKIES = "Missing cookies";
    public static final String MODE_SWITCH_MULTI_MODAL = "MODE_SWITCH_MULTI_MODAL";
    public static final String MODE_SWITCH_TABLET = "MODE_SWITCH_TABLET";
    public static final String MPU_ENABLED = "mpu_enabled";
    public static final String MULTIMODAL_MODE = "MULTIMODAL_MODE";
    public static final String MY_COMMS_USERNAME = "MY_COMMS_USERNAME";
    public static final String NETWORK_CONNECTION_INTENT = "NETWORK_CONNECTION_INTENT";
    public static final long NEW_MESSAGE_VIBRATION_DURATION_IN_MILLISECONDS = 400;
    public static final String NOTIFICATIONS_CALLING_CHANNEL = "com.amazon.deecomms.notifications.channel.calling";
    public static final String NOTIFICATIONS_CALLING_HEADUP_CHANNEL = "com.amazon.deecomms.notifications.channel.calling.headup";
    public static final String NOTIFICATIONS_COMMS_ANNOUNCEMENT_GROUP = "com.amazon.deecomms.notifications.group.announcements";
    public static final String NOTIFICATIONS_COMMS_CHANNEL = "com.amazon.deecomms.notifications.channel.comms";
    public static final String NOTIFICATIONS_COMMS_MESSAGES_GROUP = "com.amazon.deecomms.notifications.group.messages";
    public static final String NOTIFICATION_CALL_TOKEN_START_TIME = "NOTIFICATION_CALL_TOKEN_START_TIME";
    public static final String NOTIFICATION_CLICK_ACTION = "com.amazon.deecomms.notifications.CLICK_ACTION";
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String NOTIFICATION_PAYLOAD_APPLICATION_KEY = "application";
    public static final String NOTIFICATION_TARGET_MESSAGE = "MESSAGE";
    public static final String NOTIFY_ALEXA_CALL = "NOTIFY_ALEXA_CALL";
    public static final String NOTIFY_INCOMING_CALL = "NOTIFY_INCOMING_CALL";
    public static final int NO_ACTIVE_CALL_AVAILABLE = -1;
    public static final int OOBE_CANCELLED = 4;
    public static final String OOBE_COMMS_ID = "OOBE_COMMS_ID";
    public static final String OOBE_COMMS_PROV = "OOBE_COMMS_PROV";
    public static final String OOBE_COMMS_PROVISION_STATUS = "OOBE_COMMS_PROVISION_STATUS";
    public static final int OOBE_DEPROVISIONED = 3;
    public static final String OOBE_DEPROVISIONED_DIALOG_ACCEPTED = "deProvisionDialogAccepted";
    public static final String OOBE_FNAME = "OOBE_FNAME";
    public static final String OOBE_FROM_COMMS_CONTACTS = "OOBE_FROM_COMMS_CONTACTS";
    public static final String OOBE_FROM_CONVERSATIONS = "OOBE_FROM_COMMS_CONVERSATIONS";
    public static final String OOBE_HASHED_COMMS_ID = "OOBE_HASHED_COMMS_ID";
    public static final String OOBE_IS_FNF = "OOBE_IS_FNF";
    public static final String OOBE_LNAME = "OOBE_LNAME";
    public static final String OOBE_PERSON_ID_V2 = "OOBE_PERSON_ID_V2";
    public static final String OOBE_PHONETIC_FIRST_NAME = "OOBE_PHONETIC_FIRST_NAME";
    public static final String OOBE_PHONETIC_LAST_NAME = "OOBE_PHONETIC_LAST_NAME";
    public static final String OOBE_PHONE_CODE = "OOBE_PHONE_CODE";
    public static final String OOBE_PHONE_NUM = "OOBE_PHONE_NUM";
    public static final int OOBE_REQUEST_CODE = 120;
    public static final String OOBE_SKIPPED_PREF = "OOBE_SKIPPED";
    public static final String OOBE_SPEAKER_PROV = "OOBE_SPEAKER_PROV";
    public static final String OOBE_STARTED_FROM = "OOBE_STARTED_FROM";
    public static final String OOBE_STATE = "OOBE_STATE";
    public static final String OOBE_USER_AOR = "OOBE_USER_AOR";
    public static final String OOBE_USER_DIRECTEDID = "OOBE_USER_DIRECTEDID";
    public static final String OOBE_USER_HOMEGROUP_ID = "OOBE_USER_HOMEGROUP_ID";
    public static final String OOBE_USER_PROVISION_STATUS = "provisionedStatus";
    public static final int OPACITY_FULL = 255;
    public static final int OPACITY_HALF = 128;
    public static final int OPTIN_REQUEST = 200;
    public static final int OPTIN_RESPONSE_ENABLE = 20;
    public static final int OPTIN_RESPONSE_LATER = 10;
    public static final String ORIGINATED_FROM_SHARE_SHEET = "ORIGINATED_FROM_SHARE_SHEET";
    public static final String ORIGINATED_FROM_VOICE = "ORIGINATED_FROM_VOICE";
    public static final String OS_ANDROID = "Android";
    public static final String OS_FIREOS = "FireOS";
    public static final String OUTBOUND_ANNOUNCEMENT_ENABLEMENT = "Alexa.Comms.Accessories.outboundAnnouncementsEnablement";
    public static final String OUTGOING = "Outgoing";
    public static final String PERMISSION_REQUEST_SCREEN_NAME = "PERMISSION_REQUEST_SCREEN_NAME";
    public static final String PHONETIC_FIRST_NAME_PREF = "PHONETIC_FIRST_NAME_PREF";
    public static final String PHONETIC_LAST_NAME_PREF = "PHONETIC_LAST_NAME_PREF";
    public static final String PHONE_NUMBER_PREF = "PHONE_NO_PREF";
    public static final String PLAY_STORE_ACTIVITY_FORMAT = "market://details?id=%s";
    public static final String PREF_DROP_IN_DISC_SHOWN = "PREF_DROP_IN_DISC_SHOWN";
    public static final String PRINCIPAL_TYPE_DEVICES = "devices";
    public static final String PROVISION_STATUS_PREF = "PROVISION_STATUS_PREF";
    public static final String PUSH_TOKEN_PREF = "GCM_TOKEN_PREF";
    public static final String REACT_NATIVE_CONTACTS_IMPORT_COMPLETED = "contactsImportCompleted";
    public static final String REACT_NATIVE_CONTACTS_IMPORT_ERROR = "contactsImportError";
    public static final String REACT_NATIVE_CONTACTS_IMPORT_INTENT_ACTION = "ReactNativeSyncUpdate";
    public static final String REACT_NATIVE_CONTACTS_IMPORT_INTENT_KEY = "ReactNativeSyncEventStatus";
    public static final String REACT_NATIVE_CONTACTS_IMPORT_STARTED = "contactsImportStarted";
    public static final String REACT_NATIVE_CONTACTS_LAST_SYNC_STATUS = "RN_CONTACT_SYNC_STATUS";
    public static final String REACT_NATIVE_CONTACTS_SYNC_COMPLETED = "RN_CONTACT_SYNC_COMPLETED";
    public static final String REACT_NATIVE_CVF_ROUTE = "v2/comms/cvf-web-view?successRoute=comms/conversation-list&skipRoute=home/index";
    public static final String REACT_NATIVE_NEW_CONVERSATION_WITH_SHARE_ROUTE_FORMAT = "v2/comms/new-convo?genericSharingEvent=%s";
    public static final String REACT_NATIVE_PHONE_VERIFIED_ROUTE = "v2/comms/phone-verified-primer?successRoute=comms/conversation-list&skipRoute=home/index";
    public static final String REACT_NATIVE_SEND_SHARE_ROUTE_FORMAT = "v2/comms/send-share-content/%s?genericSharingEvent=%s";
    public static final String REACT_NATIVE_SEND_SHARE_TEMPLATE_KEY = "share-sheet-content::text";
    public static final String RECENT_CALLID_VIA_PUSH = "recent_callid_via_push";
    public static final int REGISTER_FOR_PUSH_MAX_ATTEMPTS = 3;
    public static final int REGISTER_FOR_SIP_INVITE = 1;
    public static final int REGISTER_ON_TASK_REMOVED = 2;
    public static final String REGISTRATION_EVENT_TYPE_KEY = "REGISTRATION_TYPE";
    public static final String REJECT_INCOMING_CALL = "REJECT_INCOMING_CALL";
    public static final String REMOTE_INPUT_ANNOUNCEMENT_ID = "REMOTE_INPUT_ANNOUNCEMENT_ID";
    public static final String REMOTE_INPUT_REPLY = "REMOTE_INPUT_REPLY";
    public static final String REMOTE_PARTICIPANT_NAME = "REMOTE_PARTICIPANT_NAME";
    public static final String RESIDENCE_COUNTRY_PREF = "RESIDENCE_COUNTRY_PREF";
    public static final String ROOT_DIRECTED_ID = "ROOT_DIRECTED_ID";
    public static final String ROUTE_AFTER_DELETE_CONTACT = "ROUTE_AFTER_DELETE_CONTACT";
    public static final String ROUTE_NAME = "ROUTE_NAME";
    public static final String SCHEME = "sips";
    public static final String SDES = "SDES";
    public static final int SECONDS_IN_MINUTE = 60;
    public static final String SENDERS_COMMS_ID = "sendersCommsId";
    public static final String SEND_CALL_METRICS = "SEND_CALL_METRICS";
    public static final String SET_SETTING = "set_setting";
    public static final String SET_SIP_CLIENT_STATE = "SET_SIP_CLIENT_STATE";
    public static final String SHARED_CONTENT_TEMPLATE_KEY = "templateKey";
    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String SHARED_PREF_CALLER_ID_SHOWN = "CALLER_ID_SHOWN";
    public static final String SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN = "FIRST_COBO_CALL_WARNING_SHOWN";
    public static final String SHARED_PREF_KEY_COMMS_FIRE_TAB_ENABLED = "commsFireTabEnabledKey";
    public static final String SHARED_PREF_MEDIA_AUTH_TOKEN = "MEDIA_AUTH_TOKEN";
    public static final String SHARED_PREF_MEDIA_AUTH_TOKEN_EXPIRY = "MEDIA_AUTH_TOKEN_EXPIRY";
    public static final String SHARED_PREF_MEDIA_AUTH_TOKEN_HASH = "MEDIA_TOKEN_HASH";
    public static final String SHARED_PREF_SIP_AUTH_TOKEN = "SIP_AUTH_TOKEN";
    public static final String SHARED_PREF_SIP_AUTH_TOKEN_EXPIRY = "SIP_AUTH_TOKEN_EXPIRY";
    public static final String SHARED_PREF_SIP_AUTH_TOKEN_HASH = "SIP_TOKEN_HASH";
    public static final String SHARED_PREF_VOICE_MSG_CONSENT = "VOICE_MSG_CONSENT";
    public static final String SHARE_SHEET_PHOTO_PREFIX = "share-sheet-content::";
    public static final String SHARE_SHEET_TEMPLATE_PREFIX = "share-sheet-content::";
    public static final String SHARE_SHEET_TEXT_PREFIX = "share-sheet-text::";
    public static final String SHARING_MESSAGE_TYPE_LINK = "link";
    public static final String SHARING_MESSAGE_TYPE_PHOTO = "message/photo-message";
    public static final String SHARING_MESSAGE_TYPE_TEXT_HTML = "text/html";
    public static final String SHARING_SDK_ERROR_CODE_INVALID_TEMPLATE = "invalid";
    public static final String SHARING_SDK_ERROR_CODE_PARSE_FAILURE = "parse";
    public static final String SHARING_SDK_ERROR_CODE_UNKNOWN_TEMPLATE = "unknown";
    public static final String SHARING_SDK_ERROR_SOURCE_UNKNOWN_TEMPLATE = "Unknown Template";
    public static final String SHARING_SDK_STATUS_ERROR = "ERROR";
    public static final String SHARING_SDK_STATUS_SUCCESS = "SUCCESS";
    public static final String SHARING_SDK_TEMPLATE_KEY_NOT_FOUND = "TemplateKeyNotFound";
    public static final String SHOULD_START_FOREGROUND_NOTI = "should_start_foreground";
    public static final String SHOULD_SUPPORT_CONTACTS_ON_DEVICES = "SHOULD_SUPPORT_CONTACTS_ON_DEVICES";
    public static final String SHOW_CALL_RATING = "SHOW_CALL_RATING";
    public static final String SHOW_CALL_UI = "SHOW_CALL_UI";
    public static final String SIMPLE_TEXT_ANNOUNCEMENT_MESSAGE_TYPE = "announcement/text-simple";
    public static final String SIP_DOMAIN = "amcs-tachyon.com";
    public static final String SIP_HEADER_ALEXA_VUA = "X-Alexa-Vua";
    public static final String SIP_HEADER_ALEXA_VUA_ENABLED = "Enabled";
    public static final String SIP_USER_AOR = "SIP_USER_AOR";
    public static final String SKYPE_ACTIVITY_FORMAT = "skype:%s?%s";
    public static final String SKYPE_PACKAGE_NAME = "com.skype.raider";
    public static final String START_FROM_SERVICE = "start_from_service";
    public static final int STATUS_CODE_EXCEPTION = 1700;
    public static final int STATUS_CODE_MISSING_COOKIES = 1401;
    public static final String STATUS_ON = "ON";
    public static final String STATUS_WHITELISTED = "WHITELISTED";
    public static final String STRING_INDEX_DEFAULT_DB_VALUE = "";
    public static final String TABLET_MODE = "TABLET_MODE";
    public static final int TARGET_FRAGMENT_REQUEST_CODE = 1;
    public static final String TEXT_ANNOUNCEMENT_MESSAGE_TYPE = "announcement/text";
    public static final String TEXT_PLAIN_MEDIA_TYPE = "text/plain";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String UNKNOWN_CALLER_NAME = "UNKNOWN";
    public static final String UNKNOWN_CLIENT_ID = "UNKNOWN_CLIENT_ID";
    public static final String USER_AGENT = "User-Agent";
    public static final String USER_AOR_PREF = "USER_AOR_PREF";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PROVISIONED_STATUS_CHECKED_ACTION = "USER_PROVISIONED_STATUS_CHECKED_ACTION";
    public static final String USER_PROVISIONING_CHANGE_ACTION = "USER_PROVISIONING_CHANGE_ACTION";
    public static final String VOX_CALL_IN_PROGRESS = "makeAVoxCall";
    public static final String VOX_CALL_UNDEFINED_NAME = "<scrub></scrub>";
    public static final String VOX_METRIC_KEY = "_VOX";
    public static final String SOUND_EFFECT_TYPE = "sound-effect";
    public static final Set<String> VALID_AUDIO_DOWNLOAD_MESSAGE_TYPES = new HashSet(Arrays.asList("message/audio", "announcement/audio", SOUND_EFFECT_TYPE));
    public static final String NOTIFICATION_TARGET_CALLING = "CALLING";
    public static final String NOTIFICATION_TARGET_ANNOUNCEMENT = "ANNOUNCEMENT";
    public static final Set<String> VALID_NOTIFICATION_TARGETS = new HashSet(Arrays.asList("MESSAGE", NOTIFICATION_TARGET_CALLING, NOTIFICATION_TARGET_ANNOUNCEMENT));
    public static final long[] CALL_VIBRATION_PATTERN = {0, 400, 1000};

    /* loaded from: classes12.dex */
    public final class Calling {
        public static final String ACTIVE_CALL_SCREEN = "ACTIVE_CALL_SCREEN";
        public static final String COMMS_ID = "COMMS_ID";
        public static final String CONTACT_NAME = "CONTACT_NAME";
        public static final String CSP_ID = "CSP_ID";
        public static final String DIALOG_CALL_ERROR_KEY = "CALL_ERROR";
        public static final String END_CALL_SCREEN = "END_CALL_SCREEN";
        public static final String ERROR_DIALOG = "ERROR_DIALOG";
        public static final String ERROR_DIALOG_MESSAGE = "ERROR_DIALOG_MESSAGE";
        public static final String ERROR_DIALOG_TITLE = "ERROR_DIALOG_TITLE";
        public static final String INCOMING_CALL_SCREEN = "INCOMING_CALL_SCREEN";
        public static final String INIT_SCREEN = "INIT_SCREEN";
        public static final String MAKE_OUTGOING_CALL_WITH_NEW_ARCHITECTURE = "MAKE_OUTGOING_CALL_WITH_NEW_ARCHITECTURE";
        public static final String MEDIA_STREAM_TYPE_TEXT = "TEXT";
        public static final String METRIC_KEY = "METRIC_KEY";
        public static final String OUTGOING_CALL_SCREEN = "OUTGOING_CALL_SCREEN";
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        public static final String SCREEN_METADATA = "SCREEN_METADATA";
        public static final String SCREEN_NAME = "SCREEN_NAME";

        private Calling() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class Dagger {
        public static final String CURRENT_CALL_SIPSTATE = "CurrentCall";
        public static final String PREVIOUS_CALL_SIPSTATE = "PreviousCall";
    }

    /* loaded from: classes12.dex */
    public static final class DriveModeCardId {
        public static final int ANNOUNCEMENT_CARD = 0;
        public static final int CALLING_CARD = 1;
    }

    /* loaded from: classes12.dex */
    public static final class NativeCalling {
        public static final int ACTION_ANSWER_CALL = 2;
        public static final int ACTION_END_CALL = 3;
        public static final int ACTION_MAKE_CALL = 1;
        public static final int ACTION_NONE = 0;
        public static final String EXTRA_ACTION_AFTER_PERMISSION_GRANTED = "EXTRA_ACTION_AFTER_PERMISSION_GRANTED";
        public static final String EXTRA_ACTION_PCC = "EXTRA_ACTION_PCC";
        public static final String NO_CALL_PERMISSIONS_SOURCE = "NO_CALL_PERMISSIONS_SOURCE";

        private NativeCalling() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class PermissionCode {
        public static final int AUDIO_CALL_RECORD_AUDIO = 1;
        public static final int AUDIO_MESSAGING_RECORD_AUDIO = 8;
        public static final int AUDIO_PSTN_CALL_CONTACT_CARD = 15;
        public static final int DROP_IN_CALL_CONVO_LIST = 13;
        public static final int DROP_IN_HG_CONTACT_CARD = 10;
        public static final int DROP_IN_HG_MESSAGE_THREAD = 12;
        public static final int DROP_IN_PARENT_HG_CONTACT_CARD = 9;
        public static final int DROP_IN_PARENT_HG_MESSAGE_THREAD = 11;
        public static final int OUTGOING_CALL = 14;
        public static final int READ_CONTACTS = 0;
        public static final int RECORD_AUDIO_CONTACT_CARD = 3;
        public static final int RECORD_AUDIO_MESSAGE_THREAD = 4;
        public static final int RECORD_AUDIO_VOICE_MESSAGING_BRIDGE = 17;
        public static final int SEND_SMS_CONTACT_CARD_PERMISSION = 16;
        public static final int VIDEO_CALL_CONTACT_CARD = 5;
        @Deprecated
        public static final int VIDEO_CALL_CONVO_LIST = 14;
        public static final int VIDEO_CALL_MESSAGE_THREAD = 6;
        public static final int VIDEO_CALL_VIDEO_VIEW = 7;
        public static final int WRITE_EXTERNAL_STORAGE = 2;

        private PermissionCode() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class Telemetry {
        public static final String ID = "alexa-android";

        /* loaded from: classes12.dex */
        public static final class CallInitScreenNames {
            public static final String CALL_BOTTOM_SHEET = "CallBottomSheet";
            public static final String CALL_DIALOG = "callDialog";
            public static final String CHILD_CONTACT_CARD = "ChildContactCardScreen";
            public static final String CONTACT_CARD = "ContactCardScreen";
            public static final String CONVERSATIONS = "ConversationListScreen";
            public static final String MESSAGE_THREAD = "MessagingThreadScreen";
            public static final String NOTIFICATION = "notification";
            public static final String VOX = "Voice";

            private CallInitScreenNames() {
            }
        }

        private Telemetry() {
        }
    }

    /* loaded from: classes12.dex */
    public final class VideoConfiguration {
        public static final boolean CAPTURE_TO_TEXTURE = true;
        public static final int FPS = 30;
        public static final boolean IGNORE_CAMERA_EVICTION_ERROR = true;
        public static final boolean SIMULATE_FIRSTFRAME_RECEIVED = false;
        public static final int VIDEO_HEIGHT = 720;
        public static final int VIDEO_MAX_BIT_RATE = 2500;
        public static final int VIDEO_START_BIT_RATE = 900;
        public static final int VIDEO_WIDTH = 1280;

        private VideoConfiguration() {
        }
    }

    private Constants() {
    }
}
