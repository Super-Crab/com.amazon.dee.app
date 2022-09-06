package com.amazon.alexa.sharing.sharingsdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.R;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.picker.util.ImageUtil;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEvent;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEventPayload;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.templates.ErrorTemplate;
import com.amazon.alexa.sharing.sharingsdk.templates.HeroImageWithCaptionTemplate;
import com.amazon.alexa.sharing.sharingsdk.templates.PhotoTemplate;
import com.amazon.alexa.sharing.sharingsdk.templates.PreviewSendShare;
import com.amazon.alexa.sharing.sharingsdk.templates.ReactionEventSendingPayload;
import com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate;
import com.amazon.alexa.sharing.sharingsdk.util.SharingSDKUtil;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.devicesetupservice.reporting.State;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class ContentSharingService implements ContentSharingSDK {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContentSharingService.class);
    private static final String RECV_PAYLOAD_REQUEST_TYPE = "rcv-payload";
    private static final String SEND_PAYLOAD_REQUEST_TYPE = "send-payload";
    private static final String SOURCE = "ContentSharingService";
    Context context;
    FeatureServiceUtil featureServiceUtil;
    AlexaCommsCoreIdentityService identityService;
    private final CommsMetricsEmitter metricsEmitter;

    @Inject
    public ContentSharingService(CommsMetricsEmitter commsMetricsEmitter, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, FeatureServiceUtil featureServiceUtil, Context context) {
        this.metricsEmitter = commsMetricsEmitter;
        this.context = context;
        this.identityService = alexaCommsCoreIdentityService;
        this.featureServiceUtil = featureServiceUtil;
    }

    private void emitShareSheetRouteFailure(@Nullable String str, @NonNull String str2) {
        Object[] objArr = new Object[1];
        if (str == null) {
            str = "unknown";
        }
        objArr[0] = str;
        this.metricsEmitter.recordOccurrenceMetric(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FAIL_DOMAIN_FORMAT, objArr), getCustomMetricEntries(str2, null, null));
    }

    private void emitShareSheetRouteFeatureDisabled(@NonNull String str, @NonNull String str2) {
        this.metricsEmitter.recordOccurrenceMetric(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FEATURE_DISABLED_FORMAT, str), getCustomMetricEntries(str2, null, null));
    }

    private void generateRouteOpenSuccess(String str) {
        HashMap outline133 = GeneratedOutlineSupport1.outline133("contentProvider", str);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.ALEXA_SHARING_SHARE_SHEET_OPEN, outline133);
        this.metricsEmitter.recordOccurrenceMetric(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_OPEN_DOMAIN_FORMAT, str), outline133);
        this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FAIL, outline133);
    }

    private Map<String, Object> getCustomMetricEntries(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        if (str3 != null && !str3.isEmpty()) {
            hashMap.put("sourceContext", str3);
        } else if (str != null) {
            hashMap.put("sourceContext", str);
        }
        if (str2 != null) {
            hashMap.put("requestId", str2);
        }
        return hashMap;
    }

    private String getDomainShortCode(String str) {
        return (str.isEmpty() || str.length() <= 9) ? str : str.substring(0, 9);
    }

    private String getTemplateShortCode(ReactMessageMetadata reactMessageMetadata) {
        String templateName = reactMessageMetadata.getTemplateName();
        String str = com.amazon.deecomms.common.metrics.MetricKeys.EMPTY_VALUE;
        if (templateName != null && templateName.length() != 0) {
            if ("HeroImageWithCaptionTemplate".equals(templateName)) {
                str = reactMessageMetadata.getDomainType().isEmpty() ? "hero" : reactMessageMetadata.getDomainType();
            } else if ("PhotoTemplate".equals(templateName)) {
                str = "photo";
            } else if ("AddReactionEvent".equals(templateName)) {
                str = "add-react";
            } else if ("RemoveReactionEvent".equals(templateName)) {
                str = "del-react";
            } else if ("PreviewSendShare".equals(templateName)) {
                str = new SharingSDKUtil().getDomainTypeFromMessageType(reactMessageMetadata.getMessageType());
            }
        }
        return str.length() > 9 ? str.substring(0, 9) : str;
    }

    private String getValidPathFromShareIntentUri(@NonNull Context context, @NonNull ImageUtil imageUtil, @Nullable Uri uri) {
        if (uri == null) {
            return "";
        }
        try {
            return imageUtil.resolveRealPath(uri.toString(), context, uri, false);
        } catch (IOException unused) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not resolve real path for file ");
            outline107.append(uri.toString());
            commsLogger.e(outline107.toString());
            return uri.toString();
        }
    }

    private boolean isValidCommsUser() {
        if (!this.identityService.getIdentity().isSignedInUser()) {
            LOG.i("[ShareSheet - Exit Scenario] Invalid User, isRegistered false from IdentityService");
            return false;
        }
        String commsId = this.identityService.getIdentity().getCommsId();
        if (commsId != null && !commsId.isEmpty()) {
            String provisioningStatus = this.identityService.getIdentity().getProvisioningStatus();
            if (provisioningStatus.equals("AUTO_PROVISIONED") || provisioningStatus.equals(State.PROVISIONED)) {
                return true;
            }
            CommsLogger commsLogger = LOG;
            commsLogger.i("[ShareSheet - Exit Scenario] Invalid User, provision status is " + provisioningStatus);
            return false;
        }
        LOG.i("[ShareSheet - Exit Scenario] Invalid User, has nil commsId");
        return false;
    }

    private boolean isValidImage(@NonNull String str) {
        String lowerCase = str.toLowerCase();
        for (String str2 : new String[]{"tiff", "xbm", "psb", "psd", "icns", "dng", "x-photoshop", "photoshop", "svg+xml"}) {
            if (lowerCase.endsWith(str2)) {
                return false;
            }
        }
        return true;
    }

    private String parseUnknownIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        StringBuilder sb = new StringBuilder();
        if (extras != null) {
            for (String str : extras.keySet()) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("[ShareSheet - Unknown Intent] ", str, " : ");
                outline115.append(extras.get(str) != null ? extras.get(str) : AppInformation.NULL);
                outline115.append("\n");
                sb.append(outline115.toString());
            }
        }
        return sb.toString();
    }

    private void recordParseRequestFailureMetrics(String str, ReactMessageMetadata reactMessageMetadata, String str2) {
        String templateShortCode = getTemplateShortCode(reactMessageMetadata);
        String globalMessageId = reactMessageMetadata.getGlobalMessageId();
        String domainShortCode = getDomainShortCode(reactMessageMetadata.getDomainType());
        Map<String, Object> customMetricEntries = getCustomMetricEntries(GeneratedOutlineSupport1.outline76("ContentSharingService::", str, "::", templateShortCode), globalMessageId, str2);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SHARING_SDK_PARSE_REQ_FAILURE, customMetricEntries);
        this.metricsEmitter.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.fail." + str, customMetricEntries);
        this.metricsEmitter.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.fail." + templateShortCode, customMetricEntries);
        if (domainShortCode.isEmpty() || domainShortCode.equals(templateShortCode)) {
            return;
        }
        this.metricsEmitter.recordOccurrenceMetric(GeneratedOutlineSupport1.outline72("alexa.sharing.sdk.parseReq.fail.", domainShortCode), customMetricEntries);
    }

    private void recordParseRequestSuccessMetrics(String str, ReactMessageMetadata reactMessageMetadata) {
        String templateShortCode = getTemplateShortCode(reactMessageMetadata);
        String globalMessageId = reactMessageMetadata.getGlobalMessageId();
        String domainType = reactMessageMetadata.getDomainType();
        String domainShortCode = getDomainShortCode(domainType);
        Map<String, Object> customMetricEntries = getCustomMetricEntries(GeneratedOutlineSupport1.outline76("ContentSharingService::", str, "::", templateShortCode), globalMessageId, null);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SHARING_SDK_PARSE_REQ_SUCCESS, customMetricEntries);
        CommsMetricsEmitter commsMetricsEmitter = this.metricsEmitter;
        commsMetricsEmitter.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.success." + str, customMetricEntries);
        CommsMetricsEmitter commsMetricsEmitter2 = this.metricsEmitter;
        commsMetricsEmitter2.recordNonOccurrenceMetric("alexa.sharing.sdk.parseReq.fail." + str, customMetricEntries);
        CommsMetricsEmitter commsMetricsEmitter3 = this.metricsEmitter;
        commsMetricsEmitter3.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.success." + templateShortCode, customMetricEntries);
        if (domainType.isEmpty() || domainShortCode.equals(templateShortCode)) {
            return;
        }
        CommsMetricsEmitter commsMetricsEmitter4 = this.metricsEmitter;
        commsMetricsEmitter4.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.success." + domainShortCode, customMetricEntries);
    }

    private Intent startMainActivityWithPhotoShareIntent(@NonNull Intent intent, Context context) {
        if (!this.featureServiceUtil.isShareSheetEnabled()) {
            LOG.i("[ShareSheet - Exit Scenario] This user is not enabled to share photos. returning to home");
            showErrorToUser(context, context.getString(R.string.sharing_failed_unsupported_feature));
            emitShareSheetRouteFeatureDisabled("photo", "sharing_failed_photo_feature_off");
            return startMainActivityWithRouteToHome(context);
        }
        Uri extractContentURIFromPhotoIntent = extractContentURIFromPhotoIntent(intent);
        if (extractContentURIFromPhotoIntent == null) {
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("photo", "sharing_failed_content_nil");
            return startMainActivityWithRouteToHome(context);
        }
        try {
            return startMainActivityWithSendShareContentUri(context, extractContentURIFromPhotoIntent);
        } catch (AlexaSharingSDKException unused) {
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("photo", "sharing_failed_parse");
            return startMainActivityWithRouteToHome(context);
        }
    }

    private Intent startMainActivityWithRouteToHome(@NonNull Context context) {
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FAIL, Collections.EMPTY_MAP);
        return startMainActivity(context, Constants.ALEXA_APP_HOME_PAGE_PATH);
    }

    private Intent startMainActivityWithTextShareIntent(Intent intent, Context context) {
        if (!this.featureServiceUtil.isLinkSharingEnabled()) {
            LOG.i("[ShareSheet - Exit Scenario] This user is not enabled to share links. returning to home");
            showErrorToUser(context, context.getString(R.string.sharing_failed_unsupported_feature));
            emitShareSheetRouteFeatureDisabled("link", "sharing_failed_link_feature_off");
            return startMainActivityWithRouteToHome(context);
        }
        try {
            return startMainActivityWithSendShareText(context, intent.getExtras());
        } catch (Exception unused) {
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("link", "sharing_failed_parse");
            return startMainActivityWithRouteToHome(context);
        }
    }

    @VisibleForTesting
    Intent createIntentForMainActivity(@NonNull Context context) {
        try {
            return new Intent(context, Class.forName("com.amazon.dee.app.ui.main.MainActivity"));
        } catch (ClassNotFoundException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Main activity not found: ");
            outline107.append(e.getCause());
            throw new IllegalArgumentException(outline107.toString(), e);
        }
    }

    @VisibleForTesting
    String encodeEvent(@NonNull Object obj) {
        return Uri.encode(new Gson().toJson(obj));
    }

    @VisibleForTesting
    Uri extractContentURIFromPhotoIntent(@NonNull Intent intent) {
        Bundle extras = intent.getExtras();
        Uri uri = (Uri) extras.get("android.intent.extra.STREAM");
        if (uri == null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[ShareSheet - Unknown Intent] This activity only knows how to parse the STREAM extra, but instead received a payload with: ");
            outline107.append(extras.toString());
            commsLogger.w(outline107.toString(), parseUnknownIntent(intent));
            return null;
        }
        LOG.d("[Content-Sharing-Service] New intent received for sharing with payload: ", uri);
        return uri;
    }

    GenericSharingMessageEventPayload getPayloadFromIntentExtras(Bundle bundle, Context context, ImageUtil imageUtil) {
        String string = bundle.getString("android.intent.extra.TEXT");
        String string2 = bundle.getString("android.intent.extra.SUBJECT");
        String validPathFromShareIntentUri = getValidPathFromShareIntentUri(context, imageUtil, (Uri) bundle.get("share_screenshot_as_stream"));
        GenericSharingMessageEventPayload.Builder builder = new GenericSharingMessageEventPayload.Builder();
        return builder.setText(string2 + " " + string).setName(string2).setDescription("").setMessageType("text/html").setImageUrl(validPathFromShareIntentUri).withDeepLinkAction(string, "AlexaMobileAndroidSharing - SDK 1.0.0").setType("message/shared-message").setMessageType("message/shared-content").build();
    }

    @VisibleForTesting
    SharedMessageTemplate getSharedMessageTemplate(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull ImageUtil imageUtil) {
        SharedMessageTemplate createFromEventPayload;
        String templateName = reactMessageMetadata.getTemplateName();
        char c = 65535;
        try {
            switch (templateName.hashCode()) {
                case -397456212:
                    if (templateName.equals("PhotoTemplate")) {
                        c = 1;
                        break;
                    }
                    break;
                case -396456019:
                    if (templateName.equals("RemoveReactionEvent")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1718117104:
                    if (templateName.equals("AddReactionEvent")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1753224121:
                    if (templateName.equals("HeroImageWithCaptionTemplate")) {
                        c = 2;
                        break;
                    }
                    break;
                case 2059814511:
                    if (templateName.equals("PreviewSendShare")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                createFromEventPayload = PreviewSendShare.createFromEventPayload(reactMessageMetadata);
            } else if (c == 1) {
                createFromEventPayload = PhotoTemplate.parseMessageMetadata(reactMessageMetadata, this.context, imageUtil);
            } else if (c == 2) {
                createFromEventPayload = HeroImageWithCaptionTemplate.parseJSONEventPayload(reactMessageMetadata);
            } else if (c != 3 && c != 4) {
                LOG.w("The templateName <" + templateName + "> is unknown to this app.Rendering an error template using extracted globalMessageId:", reactMessageMetadata.getGlobalMessageId());
                recordSharingParseExceptionMetrics(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_INVALID_TEMPLATE, reactMessageMetadata.getGlobalMessageId(), templateName + ": Name not found", templateName);
                return new ErrorTemplate(reactMessageMetadata, "unknown", "Unknown Template");
            } else {
                createFromEventPayload = ReactionEventSendingPayload.createFromEventPayload(reactMessageMetadata);
            }
            recordSharingParseSuccessMetrics(reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName());
            return createFromEventPayload;
        } catch (AlexaSharingSDKException e) {
            LOG.e(GeneratedOutlineSupport1.outline75("The templateName <", templateName, "> does not contain all information necessary to render inside this app. Rendering an error template using globalMessageId:"), reactMessageMetadata.getTemplateKey());
            CommsLogger commsLogger = LOG;
            commsLogger.e("Invalid data was found after parsing the event using GSON. This payload with this raw data cannot be rendered in this app: ", commsLogger.sensitive(reactMessageMetadata.getRawData()));
            recordSharingParseExceptionMetrics(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_INVALID_TEMPLATE, reactMessageMetadata.getGlobalMessageId(), e.getCode(), templateName);
            return new ErrorTemplate(reactMessageMetadata, e.getCode(), e.getMessage());
        } catch (JsonSyntaxException e2) {
            LOG.e(GeneratedOutlineSupport1.outline75("The templateName <", templateName, "> does not contain all information necessary to render inside this app. Rendering an error template using globalMessageId:"), reactMessageMetadata.getTemplateKey());
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("Invalid data was found after parsing the event using GSON. This payload with this raw data cannot be rendered in this app: ", commsLogger2.sensitive(reactMessageMetadata.getRawData()));
            recordSharingParseExceptionMetrics(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_JSON, reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName() + ":JSON Syntax: " + e2.getMessage(), reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "json", e2.getMessage());
        } catch (Exception e3) {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Catch-all exception fired for the templateName <", templateName, "> resulted in a failure.Rendering an error template using extracted templateKey: ");
            outline115.append(reactMessageMetadata.getTemplateKey());
            commsLogger3.e(outline115.toString(), e3);
            recordSharingParseExceptionMetrics(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_CATCH_ALL, reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName() + ": Catch all failure. " + e3.getMessage(), reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "invalid", e3.getMessage());
        }
    }

    @NonNull
    @VisibleForTesting
    SharedMessageTemplate getSharedMessageTemplateFromMetadata(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull ImageUtil imageUtil) {
        SharedMessageTemplate sharedMessageTemplate = getSharedMessageTemplate(reactMessageMetadata, imageUtil);
        if (!sharedMessageTemplate.isValidTemplate()) {
            reactMessageMetadata.setDomainType("error");
            String globalMessageId = reactMessageMetadata.getGlobalMessageId();
            recordSharingParseExceptionMetrics(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_UNKNOWN_TEMPLATE, globalMessageId, sharedMessageTemplate.getTemplateName() + ": Template is invalid", reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "invalid", sharedMessageTemplate.getTemplateName() + "::invalid");
        }
        return sharedMessageTemplate;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.ContentSharingSDK
    public Intent handleAndroidShareIntent(@NonNull Intent intent) {
        Context context = this.context;
        if (!isValidCommsUser()) {
            showErrorToUser(context, context.getString(R.string.sharing_failed_not_comms_provisioned_message));
            emitShareSheetRouteFailure("bad-user", "sharing_failed_non_comms_user");
            return startMainActivityWithRouteToHome(context);
        }
        String type = intent.getType();
        if (type == null) {
            LOG.w("[ShareSheet - Exit Scenario] Mimetype is nil.");
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("bad-mime", "sharing_failed_mime_nil");
            return startMainActivityWithRouteToHome(context);
        } else if (type.contains("image/") && isValidImage(type)) {
            return startMainActivityWithPhotoShareIntent(intent, context);
        } else {
            if (type.contains("text/")) {
                return startMainActivityWithTextShareIntent(intent, context);
            }
            LOG.w("[ShareSheet - Exit Scenario] Mimetype is unknown.");
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("bad-mime", GeneratedOutlineSupport1.outline72("sharing_failed_mime_unknown::", type));
            return startMainActivityWithRouteToHome(context);
        }
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.ContentSharingSDK
    public void onReceiveSharingParseEvent(@NonNull String str, @NonNull ResponseResolver responseResolver) {
        LOG.i("A new SharedContent event rcv-payload has been received from react-native.");
        this.metricsEmitter.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.rcv-payload", Collections.EMPTY_MAP);
        sendResponseToRN(responseResolver, RECV_PAYLOAD_REQUEST_TYPE, parseTemplateFromMessage(str));
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.ContentSharingSDK
    public void onSendSharingParseEvent(@NonNull String str, @NonNull ResponseResolver responseResolver) {
        LOG.i("A new SharedContent event send-payload has been received from react-native.");
        this.metricsEmitter.recordOccurrenceMetric("alexa.sharing.sdk.parseReq.send-payload", Collections.EMPTY_MAP);
        sendResponseToRN(responseResolver, SEND_PAYLOAD_REQUEST_TYPE, parseTemplateFromMessage(str));
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.ContentSharingSDK
    @NonNull
    public SharedMessageTemplate parseTemplateFromMessage(@NonNull String str) {
        return getSharedMessageTemplateFromMetadata(ReactMessageMetadata.fromJson(str), new ImageUtil(this.context));
    }

    @VisibleForTesting
    void recordSharingParseExceptionMetrics(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        Map<String, Object> customMetricEntries = getCustomMetricEntries(GeneratedOutlineSupport1.outline72("ContentSharingService::", str4), str2, str3);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SHARING_SDK_PARSE_FAILURE, customMetricEntries);
        this.metricsEmitter.recordOccurrenceMetric(str, customMetricEntries);
    }

    @VisibleForTesting
    void recordSharingParseSuccessMetrics(@NonNull String str, @NonNull String str2) {
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SHARING_SDK_PARSE_SUCCESS, getCustomMetricEntries(GeneratedOutlineSupport1.outline72("ContentSharingService::", str2), str, null));
    }

    @VisibleForTesting
    void sendResponseToRN(@NonNull ResponseResolver responseResolver, @NonNull String str, @NonNull SharedMessageTemplate sharedMessageTemplate) {
        try {
            responseResolver.resolve(new Gson().toJson(sharedMessageTemplate.toResponsePayload()));
            recordParseRequestSuccessMetrics(str, sharedMessageTemplate.getMessageMetadata());
        } catch (AlexaSharingSDKException | RuntimeException e) {
            LOG.e("RuntimeException encountered creating a sendParsedPayload event inside ContentSharingService - Check your payload!", e);
            recordParseRequestFailureMetrics(str, sharedMessageTemplate.getMessageMetadata(), e.getMessage());
            responseResolver.resolve(null);
        }
    }

    protected void showErrorToUser(@NonNull final Context context, @NonNull final String str) {
        LOG.w("[ShareSheet - Exit Scenario] The user was shown this message, as the app redirected to home screen: ", str);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.sharing.sharingsdk.-$$Lambda$ContentSharingService$Z42UDVRFHx2fnzczlWvewsVzrCA
            @Override // java.lang.Runnable
            public final void run() {
                Toast.makeText(context, str, 1).show();
            }
        });
    }

    @SuppressLint({"QueryPermissionsNeeded"})
    @VisibleForTesting
    Intent startMainActivity(@NonNull Context context, @NonNull String str) {
        Intent createIntentForMainActivity = createIntentForMainActivity(context);
        createIntentForMainActivity.setData(Uri.parse(str));
        createIntentForMainActivity.setFlags(268435456);
        createIntentForMainActivity.setAction("android.intent.action.VIEW");
        createIntentForMainActivity.addCategory("android.intent.category.DEFAULT");
        if (createIntentForMainActivity.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(createIntentForMainActivity);
            return createIntentForMainActivity;
        }
        LOG.e("Unable to start activity for: %s", createIntentForMainActivity);
        return null;
    }

    @VisibleForTesting
    Intent startMainActivityWithSendShareContentUri(@NonNull Context context, @NonNull Uri uri) throws AlexaSharingSDKException {
        generateRouteOpenSuccess("photo");
        ReactMessageMetadata fromContentURI = ReactMessageMetadata.fromContentURI(uri);
        return startMainActivity(context, String.format("v2/comms/send-share-content/%s?genericSharingEvent=%s", fromContentURI.getTemplateKey(), encodeEvent(getSharedMessageTemplateFromMetadata(fromContentURI, new ImageUtil(context)).toResponsePayload())));
    }

    @VisibleForTesting
    Intent startMainActivityWithSendShareText(@NonNull Context context, @NonNull Bundle bundle) {
        GenericSharingMessageEventPayload payloadFromIntentExtras = getPayloadFromIntentExtras(bundle, context, new ImageUtil(context));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("share-sheet-text::");
        outline107.append(payloadFromIntentExtras.hashCode());
        String sb = outline107.toString();
        GenericSharingMessageEvent genericSharingMessageEvent = new GenericSharingMessageEvent(payloadFromIntentExtras, "PreviewSendShare", "", sb);
        generateRouteOpenSuccess(new SharingSDKUtil().getDomainTypeFromEvent(genericSharingMessageEvent));
        return startMainActivity(context, String.format("v2/comms/send-share-content/%s?genericSharingEvent=%s", sb, encodeEvent(genericSharingMessageEvent)));
    }
}
