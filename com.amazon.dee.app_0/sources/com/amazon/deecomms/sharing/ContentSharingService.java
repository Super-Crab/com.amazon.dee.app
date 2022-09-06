package com.amazon.deecomms.sharing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.sharing.api.SharingClient;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEvent;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEventPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import com.amazon.deecomms.sharing.templates.HeroImageWithCaptionTemplate;
import com.amazon.deecomms.sharing.templates.PhotoTemplate;
import com.amazon.deecomms.sharing.templates.PreviewSendShare;
import com.amazon.deecomms.sharing.templates.ReactionEventSendingPayload;
import com.amazon.deecomms.sharing.templates.SharedMessageTemplate;
import com.amazon.deecomms.sharing.utils.SharingUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class ContentSharingService implements CommsContentSharingSDK {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContentSharingService.class);
    private static final String SOURCE = "ContentSharingService";
    private final Lazy<CapabilitiesManager> capabilitiesManager;
    private final Lazy<CommsIdentityManager> commsIdentityManager;
    private final Lazy<Context> context;
    private final Lazy<IdentityService> identityService;

    public ContentSharingService(@NonNull Lazy<CommsIdentityManager> lazy, @NonNull Lazy<CapabilitiesManager> lazy2, @NonNull Lazy<IdentityService> lazy3, @NonNull Lazy<Context> lazy4) {
        this.commsIdentityManager = lazy;
        this.capabilitiesManager = lazy2;
        this.identityService = lazy3;
        this.context = lazy4;
    }

    private String convertContentURIToBase64Image(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.context.mo358get().getContentResolver(), Uri.parse(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("[Content-Sharing-Service] Failed to parse the image content (I/O): " + str, e);
            return "";
        } catch (SecurityException e2) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("[Content-Sharing-Service] Failed to parse the image content (Security): " + str, e2);
            return "";
        } catch (Exception e3) {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("[Content-Sharing-Service] Failed to parse the image content (General): " + str, e3);
            return "";
        }
    }

    private void emitShareSheetRouteFailure(@Nullable String str, @NonNull String str2) {
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperationalWithSource(str != null ? String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FAIL_DOMAIN_FORMAT, str) : MetricKeys.ALEXA_SHARING_SHARE_SHEET_FAIL, str2, null), Double.valueOf(1.0d));
    }

    private void emitShareSheetRouteFeatureDisabled(@NonNull String str, @NonNull String str2) {
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperationalWithSource(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_FEATURE_DISABLED_FORMAT, str), str2, null), Double.valueOf(1.0d));
    }

    private void generateRouteOpenSuccess(String str) {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.ALEXA_SHARING_SHARE_SHEET_OPEN);
        HashMap hashMap = new HashMap();
        hashMap.put("contentProvider", str);
        generateOperational.setMetadata(hashMap);
        Double valueOf = Double.valueOf(1.0d);
        MetricsHelper.recordCounterMetric(generateOperational, valueOf);
        CounterMetric generateOperational2 = CounterMetric.generateOperational(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_OPEN_DOMAIN_FORMAT, str));
        generateOperational2.setMetadata(hashMap);
        MetricsHelper.recordCounterMetric(generateOperational2, valueOf);
    }

    private String getEventTypeShortCode(String str) {
        boolean equals = EventBusEventType.SEND_SHARING_PAYLOAD.toString().equals(str);
        Double valueOf = Double.valueOf(1.0d);
        if (equals) {
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational("comms.eventbus.event.received." + str), valueOf);
            return "send-payload";
        } else if (!EventBusEventType.RECEIVE_SHARING_PAYLOAD.toString().equals(str)) {
            return str.length() > 12 ? str.substring(str.length() - 12) : str;
        } else {
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational("comms.eventbus.event.received." + str), valueOf);
            return "rcv-payload";
        }
    }

    private String getTemplateShortCode(ReactMessageMetadata reactMessageMetadata) {
        String templateName = reactMessageMetadata.getTemplateName();
        if (templateName == null || templateName.length() == 0) {
            return MetricKeys.EMPTY_VALUE;
        }
        if ("HeroImageWithCaptionTemplate".equals(templateName)) {
            return reactMessageMetadata.getDomainType().isEmpty() ? "hero" : reactMessageMetadata.getDomainType();
        } else if ("PhotoTemplate".equals(templateName)) {
            return "photo";
        } else {
            if ("AddReactionEvent".equals(templateName)) {
                return "add-react";
            }
            if ("RemoveReactionEvent".equals(templateName)) {
                return "del-react";
            }
            if ("PreviewSendShare".equals(templateName)) {
                return new SharingUtils().getDomainTypeFromMessageType(reactMessageMetadata.getMessageType());
            }
            return templateName.length() > 9 ? templateName.substring(0, 9) : templateName;
        }
    }

    private Intent handleShareIntentUsingSharingClient(@NonNull Intent intent) {
        LOG.i("Handling share intent with sharing client");
        try {
            return getSharingClient().handleShareIntent(intent);
        } catch (Exception e) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("alexa.sharing.cx.shareSheet.redirect.fail.");
            outline1.append(e.getClass().getSimpleName());
            CounterMetric generateOperational = CounterMetric.generateOperational(outline1.toString());
            HashMap outline133 = GeneratedOutlineSupport1.outline133("source", SOURCE);
            outline133.put("errorSource", Log.getStackTraceString(e));
            generateOperational.setMetadata(outline133);
            MetricsHelper.recordSingleOccurrence(generateOperational);
            LOG.e("Exception processing request with sharing client: ", e);
            return null;
        }
    }

    private boolean isValidCommsUser(@NonNull Context context) {
        if (!(this.identityService.mo358get().isRegistered(SOURCE) && this.identityService.mo358get().isAuthenticated(SOURCE))) {
            LOG.i("[ShareSheet - Exit Scenario] Invalid User, isRegistered false from IdentityService");
            return false;
        }
        String commsId = this.commsIdentityManager.mo358get().getCommsId(SOURCE, false);
        if (commsId != null && commsId != "") {
            CommsProvisionStatus provisionStatus = this.commsIdentityManager.mo358get().getProvisionStatus(true, SOURCE, false);
            if (provisionStatus == CommsProvisionStatus.AUTO_PROVISIONED || provisionStatus == CommsProvisionStatus.PROVISIONED) {
                return true;
            }
            CommsLogger commsLogger = LOG;
            commsLogger.i("[ShareSheet - Exit Scenario] Invalid User, provision status is " + provisionStatus);
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

    private boolean parseSharingPayloadAndEmitEventToReactNative(@NonNull Message message, @NonNull EventBus eventBus) {
        return emitEventToCommsReactNative(eventBus, EventBusEventType.RECEIVE_SHARING_PAYLOAD, parseTemplateFromMessage(message));
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

    private CounterMetric recordSharingEventFailureMetrics(String str, ReactMessageMetadata reactMessageMetadata) {
        String templateShortCode = getTemplateShortCode(reactMessageMetadata);
        String globalMessageId = reactMessageMetadata.getGlobalMessageId();
        String domainType = reactMessageMetadata.getDomainType();
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SHARING_SDK_EVENT_BUS_FAILURE);
        Double valueOf = Double.valueOf(1.0d);
        generateOperational.setCounter(valueOf);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", str + "::" + domainType);
        metadata.put("requestId", globalMessageId);
        generateOperational.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational);
        CounterMetric generateOperational2 = CounterMetric.generateOperational("comms.share.emit.event.fail." + str);
        generateOperational2.setCounter(valueOf);
        generateOperational2.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational2);
        CounterMetric generateOperational3 = CounterMetric.generateOperational("comms.share.emit.event.fail." + templateShortCode);
        generateOperational3.setCounter(valueOf);
        generateOperational3.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational3);
        if (!domainType.isEmpty() && !domainType.equals(templateShortCode)) {
            CounterMetric generateOperational4 = CounterMetric.generateOperational("comms.share.emit.event.fail." + domainType);
            generateOperational4.setCounter(valueOf);
            generateOperational4.setMetadata(metadata);
            MetricsHelper.recordSingleOccurrence(generateOperational4);
        }
        return generateOperational3;
    }

    private CounterMetric recordSharingEventSuccessMetrics(String str, ReactMessageMetadata reactMessageMetadata) {
        String templateShortCode = getTemplateShortCode(reactMessageMetadata);
        String globalMessageId = reactMessageMetadata.getGlobalMessageId();
        String domainType = reactMessageMetadata.getDomainType();
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SHARING_SDK_EVENT_BUS_SUCCESS);
        Double valueOf = Double.valueOf(1.0d);
        generateOperational.setCounter(valueOf);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", str + "::" + templateShortCode);
        metadata.put("requestId", globalMessageId);
        generateOperational.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational);
        CounterMetric generateOperational2 = CounterMetric.generateOperational("comms.share.emit.event.success." + str);
        generateOperational2.setCounter(valueOf);
        generateOperational2.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational2);
        CounterMetric generateOperational3 = CounterMetric.generateOperational("comms.share.emit.event.success." + templateShortCode);
        generateOperational3.setCounter(valueOf);
        generateOperational3.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational3);
        if (!domainType.isEmpty() && !domainType.equals(templateShortCode)) {
            CounterMetric generateOperational4 = CounterMetric.generateOperational("comms.share.emit.event.success." + domainType);
            generateOperational4.setCounter(valueOf);
            generateOperational4.setMetadata(metadata);
            MetricsHelper.recordSingleOccurrence(generateOperational4);
        }
        return generateOperational3;
    }

    private Intent startMainActivityWithPhotoShareIntent(@NonNull Intent intent, Context context) {
        if (!this.capabilitiesManager.mo358get().isShareSheetEnabled()) {
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
        } catch (SharingSDKException unused) {
            showErrorToUser(context, context.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("photo", "sharing_failed_parse");
            return startMainActivityWithRouteToHome(context);
        }
    }

    private Intent startMainActivityWithRouteToHome(@NonNull Context context) {
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(MetricKeys.ALEXA_SHARING_SHARE_SHEET_OPEN), Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
        return startMainActivity(context, com.amazon.alexa.sharing.Constants.ALEXA_APP_HOME_PAGE_PATH);
    }

    private Intent startMainActivityWithTextShareIntent(Intent intent, Context context) {
        if (!this.capabilitiesManager.mo358get().isLinkSharingEnabled()) {
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
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Content-Sharing-Service] Main activity not found: ");
            outline1.append(e.getCause());
            throw new IllegalArgumentException(outline1.toString());
        }
    }

    @VisibleForTesting
    boolean emitEventToCommsReactNative(@NonNull EventBus eventBus, @NonNull EventBusEventType eventBusEventType, @NonNull SharedMessageTemplate sharedMessageTemplate) {
        String eventTypeShortCode = getEventTypeShortCode(eventBusEventType.toString());
        try {
            eventBus.publish(new Message.Builder().setEventType(eventBusEventType.toString()).setPayload(new Gson().toJson(sharedMessageTemplate.toEventBusPayload())).build());
            StringBuilder sb = new StringBuilder();
            sb.append("comms.eventbus.event.published.");
            sb.append(eventBusEventType.toString());
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(sb.toString()), Double.valueOf(1.0d));
            recordSharingEventSuccessMetrics(eventTypeShortCode, sharedMessageTemplate.getMessageMetadata());
            return true;
        } catch (SharingSDKException | RuntimeException e) {
            LOG.e("[Content-Sharing-Service] RuntimeException encountered creating a sendParsedPayload event inside ContentSharingService - Check your payload!", e);
            recordSharingEventFailureMetrics(eventTypeShortCode, sharedMessageTemplate.getMessageMetadata());
            return false;
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
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("[ShareSheet - Unknown Intent] This activity only knows how to parse the STREAM extra, but instead received a payload with: ");
            outline1.append(extras.toString());
            commsLogger.w(outline1.toString(), parseUnknownIntent(intent));
            return null;
        }
        LOG.d("[Content-Sharing-Service] New intent received for sharing with payload: ", uri);
        return uri;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @VisibleForTesting
    SharedMessageTemplate getSharedMessageTemplate(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Compression compression) {
        char c;
        SharedMessageTemplate createFromEventPayload;
        String templateName = reactMessageMetadata.getTemplateName();
        try {
            switch (templateName.hashCode()) {
                case -397456212:
                    if (templateName.equals("PhotoTemplate")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -396456019:
                    if (templateName.equals("RemoveReactionEvent")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1718117104:
                    if (templateName.equals("AddReactionEvent")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1753224121:
                    if (templateName.equals("HeroImageWithCaptionTemplate")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2059814511:
                    if (templateName.equals("PreviewSendShare")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                createFromEventPayload = PreviewSendShare.createFromEventPayload(reactMessageMetadata);
            } else if (c == 1) {
                createFromEventPayload = PhotoTemplate.parseMessageMetadata(reactMessageMetadata, this.context.mo358get(), compression);
            } else if (c == 2) {
                createFromEventPayload = HeroImageWithCaptionTemplate.parseJSONEventPayload(reactMessageMetadata);
            } else if (c != 3 && c != 4) {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("[Content-Sharing-Service] The templateName <");
                sb.append(templateName);
                sb.append("> is unknown to this app.Rendering an error template using extracted globalMessageId:");
                commsLogger.w(sb.toString(), reactMessageMetadata.getGlobalMessageId());
                String globalMessageId = reactMessageMetadata.getGlobalMessageId();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(templateName);
                sb2.append(": Name not found");
                recordSharingParseUnknownMetric(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_INVALID_TEMPLATE, globalMessageId, sb2.toString(), templateName);
                return new ErrorTemplate(reactMessageMetadata, "unknown", "Unknown Template");
            } else {
                createFromEventPayload = ReactionEventSendingPayload.createFromEventPayload(reactMessageMetadata);
            }
            recordSharingParseSuccessMetrics(reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName());
            return createFromEventPayload;
        } catch (SharingSDKException e) {
            LOG.e(GeneratedOutlineSupport1.outline75("[Content-Sharing-Service] The templateName <", templateName, "> does not contain all information necessary to render inside this app. Rendering an error template using globalMessageId:"), reactMessageMetadata.getTemplateKey());
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("[Content-Sharing-Service] Invalid data was found after parsing the event using GSON. This payload with this raw data cannot be rendered in this app: ", commsLogger2.sensitive(reactMessageMetadata.getRawData()));
            recordSharingParseExceptionMetrics(e, MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_INVALID_TEMPLATE, reactMessageMetadata.getGlobalMessageId(), e.getCode(), templateName);
            return new ErrorTemplate(reactMessageMetadata, e.getCode(), e.getMessage());
        } catch (JsonSyntaxException e2) {
            LOG.e(GeneratedOutlineSupport1.outline75("[Content-Sharing-Service] The templateName <", templateName, "> does not contain all information necessary to render inside this app. Rendering an error template using globalMessageId:"), reactMessageMetadata.getTemplateKey());
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("[Content-Sharing-Service] Invalid data was found after parsing the event using GSON. This payload with this raw data cannot be rendered in this app: ", commsLogger3.sensitive(reactMessageMetadata.getRawData()));
            recordSharingParseExceptionMetrics(e2, MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_JSON, reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName() + ":JSON Syntax: " + e2.getMessage(), reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "json", e2.getMessage());
        } catch (Exception e3) {
            CommsLogger commsLogger4 = LOG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("[Content-Sharing-Service] Catch-all exception fired for the templateName <", templateName, "> resulted in a failure.Rendering an error template using extracted templateKey: ");
            outline115.append(reactMessageMetadata.getTemplateKey());
            commsLogger4.e(outline115.toString(), e3);
            recordSharingParseExceptionMetrics(e3, MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_CATCH_ALL, reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateName() + ": Catch all failure. " + e3.getMessage(), reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "invalid", e3.getMessage());
        }
    }

    @NonNull
    @VisibleForTesting
    SharedMessageTemplate getSharedMessageTemplateFromMetadata(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Compression compression) {
        SharedMessageTemplate sharedMessageTemplate = getSharedMessageTemplate(reactMessageMetadata, compression);
        if (!sharedMessageTemplate.isValidTemplate()) {
            reactMessageMetadata.setDomainType("error");
            String globalMessageId = reactMessageMetadata.getGlobalMessageId();
            recordSharingParseUnknownMetric(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE_UNKNOWN_TEMPLATE, globalMessageId, sharedMessageTemplate.getTemplateName() + ": Template is invalid", reactMessageMetadata.getTemplateName());
            return new ErrorTemplate(reactMessageMetadata, "invalid", sharedMessageTemplate.getTemplateName() + "::invalid");
        }
        return sharedMessageTemplate;
    }

    SharingClient getSharingClient() {
        return (SharingClient) GeneratedOutlineSupport1.outline20(SharingClient.class);
    }

    @Override // com.amazon.deecomms.sharing.CommsContentSharingSDK
    public Intent handleAndroidShareIntent(@NonNull Intent intent) {
        Context mo358get = this.context.mo358get();
        if (this.capabilitiesManager.mo358get().hasSharingDecouplingAccess()) {
            MetricsHelper.recordSingleOccurrence(CounterMetric.generateOperational(MetricKeys.ALEXA_SHARING_REDIRECT_WITH_SHARING_CLIENT));
            return handleShareIntentUsingSharingClient(intent);
        }
        LOG.i("Handling share intent with DCAL content sharing service");
        if (!isValidCommsUser(mo358get)) {
            showErrorToUser(mo358get, mo358get.getString(R.string.sharing_failed_not_comms_provisioned_message));
            emitShareSheetRouteFailure("bad-user", "sharing_failed_non_comms_user");
            return startMainActivityWithRouteToHome(mo358get);
        }
        String type = intent.getType();
        if (type == null) {
            LOG.w("[ShareSheet - Exit Scenario] Mimetype is nil.");
            showErrorToUser(mo358get, mo358get.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("bad-mime", "sharing_failed_mime_nil");
            return startMainActivityWithRouteToHome(mo358get);
        } else if (type.contains("image/") && isValidImage(type)) {
            return startMainActivityWithPhotoShareIntent(intent, mo358get);
        } else {
            if (type.contains("text/")) {
                return startMainActivityWithTextShareIntent(intent, mo358get);
            }
            LOG.w("[ShareSheet - Exit Scenario] Mimetype is unknown.");
            showErrorToUser(mo358get, mo358get.getString(R.string.sharing_failed_content_not_supported));
            emitShareSheetRouteFailure("bad-mime", GeneratedOutlineSupport.outline0("sharing_failed_mime_unknown::", type));
            return startMainActivityWithRouteToHome(mo358get);
        }
    }

    @Override // com.amazon.deecomms.sharing.CommsContentSharingSDK
    public boolean onReceiveSharingParseEvent(@NonNull Message message, @NonNull EventBus eventBus) {
        LOG.i("[Content-Sharing-Service] A new SharedContent event has been received from react-native.");
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperational("comms.eventbus.event.received." + EventBusEventType.RECEIVE_SHARING_PARSE.toString()), Double.valueOf(1.0d));
        return emitEventToCommsReactNative(eventBus, EventBusEventType.RECEIVE_SHARING_PAYLOAD, parseTemplateFromMessage(message));
    }

    @Override // com.amazon.deecomms.sharing.CommsContentSharingSDK
    public boolean onSendSharingParseEvent(@NonNull Message message, @NonNull EventBus eventBus) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("comms.eventbus.event.received.");
        outline1.append(EventBusEventType.SEND_SHARING_PARSE.toString());
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(outline1.toString()), Double.valueOf(1.0d));
        return emitEventToCommsReactNative(eventBus, EventBusEventType.SEND_SHARING_PAYLOAD, parseTemplateFromMessage(message));
    }

    @NonNull
    public SharedMessageTemplate parseTemplateFromMessage(@NonNull Message message) {
        return parseTemplateFromMessage(message.getPayloadAsString());
    }

    @VisibleForTesting
    CounterMetric recordSharingParseExceptionMetrics(@NonNull Throwable th, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        CounterMetric generateOperationalException = CounterMetric.generateOperationalException(th, MetricKeys.SHARING_SDK_PAYLOAD_FAILURE, str2, -1, str3);
        Map<String, Object> metadata = generateOperationalException.getMetadata();
        metadata.put("source", "ContentSharingService::" + str4);
        metadata.put("requestId", str2);
        metadata.put("errorSource", str3);
        metadata.put("statusCode", -1);
        generateOperationalException.setMetadata(metadata);
        Double valueOf = Double.valueOf(1.0d);
        generateOperationalException.setCounter(valueOf);
        MetricsHelper.recordSingleOccurrence(generateOperationalException);
        CounterMetric generateOperationalException2 = CounterMetric.generateOperationalException(th, str, str2, -1, str3);
        generateOperationalException2.setCounter(valueOf);
        generateOperationalException2.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperationalException2);
        return generateOperationalException2;
    }

    @VisibleForTesting
    CounterMetric recordSharingParseSuccessMetrics(@NonNull String str, @NonNull String str2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SHARING_SDK_PAYLOAD_SUCCESS);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", "ContentSharingService::" + str2);
        metadata.put("requestId", str);
        metadata.put("statusCode", -1);
        generateOperational.setMetadata(metadata);
        generateOperational.setCounter(Double.valueOf(1.0d));
        MetricsHelper.recordSingleOccurrence(generateOperational);
        return generateOperational;
    }

    @VisibleForTesting
    CounterMetric recordSharingParseUnknownMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SHARING_SDK_PAYLOAD_FAILURE);
        Double valueOf = Double.valueOf(1.0d);
        generateOperational.setCounter(valueOf);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", "ContentSharingService::" + str4);
        metadata.put("errorSource", str3);
        metadata.put("requestId", str2);
        metadata.put("statusCode", -1);
        generateOperational.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational);
        CounterMetric generateOperational2 = CounterMetric.generateOperational(str);
        generateOperational2.setCounter(valueOf);
        generateOperational2.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational2);
        return generateOperational2;
    }

    protected void showErrorToUser(@NonNull final Context context, @NonNull final String str) {
        LOG.w("[ShareSheet - Exit Scenario] The user was shown this message, as the app redirected to home screen: ", str);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.sharing.-$$Lambda$ContentSharingService$LOVuI3AOCPxF1cTGa7b3gcY_9-4
            @Override // java.lang.Runnable
            public final void run() {
                Toast.makeText(context, str, 1).show();
            }
        });
    }

    @VisibleForTesting
    Intent startMainActivity(@NonNull Context context, @NonNull String str) {
        Intent createIntentForMainActivity = createIntentForMainActivity(context);
        createIntentForMainActivity.setData(Uri.parse(str));
        createIntentForMainActivity.setFlags(268435456);
        createIntentForMainActivity.setAction("android.intent.action.VIEW");
        createIntentForMainActivity.addCategory("android.intent.category.DEFAULT");
        context.startActivity(createIntentForMainActivity);
        return createIntentForMainActivity;
    }

    @VisibleForTesting
    Intent startMainActivityWithSendShareContentUri(@NonNull Context context, @NonNull Uri uri) throws SharingSDKException {
        generateRouteOpenSuccess("photo");
        ReactMessageMetadata fromContentURI = ReactMessageMetadata.fromContentURI(uri);
        return startMainActivity(context, String.format("v2/comms/send-share-content/%s?genericSharingEvent=%s", fromContentURI.getTemplateKey(), encodeEvent(getSharedMessageTemplateFromMetadata(fromContentURI, new Compression(context)).toEventBusPayload())));
    }

    @VisibleForTesting
    Intent startMainActivityWithSendShareText(@NonNull Context context, @NonNull Bundle bundle) {
        GenericSharingMessageEventPayload fromAndroidIntentExtras = GenericSharingMessageEventPayload.fromAndroidIntentExtras(bundle, context, new Compression(context));
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("share-sheet-text::");
        outline1.append(fromAndroidIntentExtras.hashCode());
        String sb = outline1.toString();
        GenericSharingMessageEvent genericSharingMessageEvent = new GenericSharingMessageEvent(fromAndroidIntentExtras, "PreviewSendShare", "", sb);
        generateRouteOpenSuccess(new SharingUtils().getDomainTypeFromEvent(genericSharingMessageEvent));
        return startMainActivity(context, String.format("v2/comms/send-share-content/%s?genericSharingEvent=%s", sb, encodeEvent(genericSharingMessageEvent)));
    }

    @Override // com.amazon.deecomms.sharing.CommsContentSharingSDK
    @NonNull
    public SharedMessageTemplate parseTemplateFromMessage(@NonNull String str) {
        return getSharedMessageTemplateFromMetadata(ReactMessageMetadata.fromJson(str), new Compression(this.context.mo358get()));
    }
}
