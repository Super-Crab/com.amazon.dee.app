package com.amazon.alexa.sharing;

import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes10.dex */
public final class Constants {
    public static final String ALEXA_APP_HOME_PAGE_PATH = "https://alexa.amazon.com/spa/index.html#home";
    public static final String ALEXA_APP_SOURCE = "AlexaApp";
    public static final String ALEXA_MEDIA_FOLDER_NAME = "Alexa Shared Photos";
    public static final String ALEXA_MESSAGING_GLOBAL_SERVICE = "https://alexa-comms-mobile-service.amazon.com";
    public static final String ALEXA_SHARING_CLIENT_SOURCE = "AlexaSharingClient";
    public static final String ALEXA_SHARING_KEY_DOWNLOAD_MEDIA = "downloadMediaFromCDS";
    public static final String ALEXA_SHARING_KEY_PARSE_RCV_PAYLOAD = "parseSharingReceivePayload";
    public static final String ALEXA_SHARING_KEY_PARSE_SEND_PAYLOAD = "parseSharingSendPayload";
    public static final String ALEXA_SHARING_KEY_PARSE_SOCIAL_FEED_RECEIVE_PAYLOAD = "parseSocialFeedReceivePayload";
    public static final String ALEXA_SHARING_KEY_REMOVE_MEDIA_FILES = "removeMediaFilesFromDisk";
    public static final String ALEXA_SHARING_KEY_SENDMSG = "sendNewMessage";
    public static final String ALEXA_SHARING_KEY_UPLOAD_MEDIA = "uploadMediaToCDS";
    public static final String ANNOUNCEMENT_PATH = "/users/%s/announcements";
    public static final String CDS_ACCOUNT_SETUP_SUCCESSFUL = "CDS_ACCOUNT_SETUP_SUCCESSFUL";
    public static final String CDS_CONFLICT_ERROR_ID_ALREADY_EXISTS = "ID_ALREADY_EXISTS";
    public static final String CDS_CONFLICT_ERROR_MD5_DUPLICATE = "MD5_DUPLICATE";
    public static final String CDS_CONFLICT_ERROR_UPLOAD_ALREADY_EXISTS = "NAME_ALREADY_EXISTS";
    public static final String CDS_NODE_NOT_FOUND = "NODE_NOT_FOUND";
    public static final String CDS_PARENT_NODE_ID_NOT_FOUND = "PARENT_NODE_ID_NOT_FOUND";
    public static final String CDS_PARENT_NODE_IN_TRASH = "PARENT_NODE_IN_TRASH ";
    public static final String CREATE_NEW_MESSAGE_PATH = "/api/new-message";
    public static final String DEFAULT_GET_SINGLE_MSG_BACKOFF_TIME_MS = "200";
    public static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    public static final String DEFAULT_SENDMSG_TIMEOUT = "60";
    public static final String ERROR_GENERIC_PARSE_FAILURE = "Sharing payload parse failure";
    public static final String ERROR_PAYLOAD_JSON_PARSE_FAILURE = "Could not parse the provided sharing payload as JSON";
    public static final String ERROR_PAYLOAD_UNKNOWN_TYPE = "Payload contains an unknown type";
    public static final String ERROR_REQUEST_MISSING_REQUIRED_FIELD = "Payload missing required field";
    public static final String ERROR_SHARED_CONTENT_PARSE_FAILURE = "Could not parse the shared content payload";
    public static final String ERROR_SHARED_MEDIA_PARSE_FAILURE = "Could not parse the shared media payload";
    public static final String GET_CONVERSATION_MESSAGES_PATH = "/users/%s/conversations/%s/messages";
    public static final String GET_CONVERSATION_MESSAGES_SINGLE_MESSAGE_QUERY = "?startId=%s&count=1&sequence=false&order=desc&sort=asc&includeReactionSummaries=true";
    public static final String IMAGE_CROP_PICKER_CACHE_DIR = "react-native-image-crop-picker";
    public static final String LOG_TAG = "Alexa-Social-Sharing";
    public static final String MAIN_ACTIVITY = "com.amazon.dee.app.ui.main.MainActivity";
    public static final double MAX_PHONE_SIZE_IN_INCHES = 6.5d;
    public static final String MESSAGE_TYPE_MESSAGE = "message";
    public static final String METRIC_KEY_CONTENT_DETAILS = "contentDetails";
    public static final String METRIC_KEY_CONTENT_ID = "contentId";
    public static final String METRIC_KEY_CONTENT_PROVIDER = "contentProvider";
    public static final String MIME_TYPE_UNKNOWN_PERMISSION_DENIED = "unknown/permission-denied";
    public static final String MOCK_CLIENT_TOKEN = "client.token.uuid.1234";
    public static final String MOCK_CONVERSATION_UUID = "amzn1.comms.messaging.id.conversationV2~Mock.conversation";
    public static final String MOCK_ISO_DATETIME = "2021-02-03T04:05:06.789Z";
    public static final String MOCK_MESSAGE_UUID = "amzn1.comms.message.global.id~Mock.message";
    public static final String MOCK_RECIPIENT_UUID = "amzn1.comms.id.person.amzn1~amzn1.account.Mock.recipient";
    public static final String MOCK_SENDER_UUID = "amzn1.comms.id.person.amzn1~amzn1.account.Mock.sender";
    public static final String REACT_NATIVE_NEW_CONVERSATION_WITH_SHARE_ROUTE_FORMAT = "v2/comms/new-convo?genericSharingEvent=%s";
    public static final String REACT_NATIVE_SEND_SHARE_ROUTE_FORMAT = "v2/comms/send-share-content/%s?genericSharingEvent=%s";
    public static final String SHARED_CONTENT_TEMPLATE_KEY = "templateKey";
    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String SHARE_SHEET_PHOTO_PREFIX = "share-sheet-content::";
    public static final String SHARE_SHEET_TEMPLATE_PREFIX = "share-sheet-content::";
    public static final String SHARE_SHEET_TEXT_PREFIX = "share-sheet-text::";
    public static final String SHARING_MESSAGE_TYPE_LINK = "link";
    public static final String SHARING_MESSAGE_TYPE_MEDIA = "message/media";
    public static final String SHARING_MESSAGE_TYPE_PHOTO = "message/photo-message";
    public static final String SHARING_MESSAGE_TYPE_SHARED_CONTENT = "message/shared-content";
    public static final String SHARING_MESSAGE_TYPE_TEXT_HTML = "text/html";
    public static final String SHARING_SDK_ERROR_CODE_INVALID_TEMPLATE = "invalid";
    public static final String SHARING_SDK_ERROR_CODE_PARSE_FAILURE = "parse";
    public static final String SHARING_SDK_ERROR_CODE_UNKNOWN_TEMPLATE = "unknown";
    public static final String SHARING_SDK_ERROR_SOURCE_UNKNOWN_TEMPLATE = "Unknown Template";
    public static final String SHARING_SDK_STATUS_ERROR = "ERROR";
    public static final String SHARING_SDK_STATUS_SUCCESS = "SUCCESS";
    public static final String SHARING_SDK_TEMPLATE_KEY_NOT_FOUND = "TemplateKeyNotFound";
    public static final String THUMBNAIL_DIMENSION = "600x600";
    public static final Integer THUMBNAIL_VIEWBOX = Integer.valueOf((int) DeviceConfigConstants.VIDEO_BITRATE_600_KBPS);

    private Constants() {
    }
}
