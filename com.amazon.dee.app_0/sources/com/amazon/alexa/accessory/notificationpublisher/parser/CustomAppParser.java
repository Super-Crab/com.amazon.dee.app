package com.amazon.alexa.accessory.notificationpublisher.parser;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3ResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CustomAppParser extends BaseCustomAppParser implements GetFocusFilterTemplatesFromS3ResponseHandler {
    private static final String TAG = "CustomAppParser";
    private BaseTemplateProvider baseTemplateProvider;
    private Context context;

    public CustomAppParser(@NonNull Context context, @NonNull BaseTemplateProvider baseTemplateProvider, @NonNull JSONObject jSONObject) {
        super(jSONObject);
        this.context = context;
        this.baseTemplateProvider = baseTemplateProvider;
    }

    private void fetchParsingTemplatesFromCloud(final GetFocusFilterTemplatesFromS3ResponseHandler getFocusFilterTemplatesFromS3ResponseHandler) {
        final String str;
        Object obj;
        try {
            obj = new StorageWrapper().get(GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE.name()));
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_ETAG_FROM_LOCALSTORAGE_EXCEPTION);
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("fetchParsingTemplatesFromCloud: ");
            outline107.append(e.getMessage());
            Log.e(str2, outline107.toString());
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_fetchParsingTemplatesFromCloud", MetricsRecorder.customAttributesForException(e));
            }
        }
        if (obj != null) {
            str = (String) String.class.cast(obj);
            Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.parser.CustomAppParser.1
                @Override // java.lang.Runnable
                public void run() {
                    GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request("ANDROID", GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE, str, getFocusFilterTemplatesFromS3ResponseHandler, 2);
                }
            });
        }
        str = null;
        Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.parser.CustomAppParser.1
            @Override // java.lang.Runnable
            public void run() {
                GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request("ANDROID", GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE, str, getFocusFilterTemplatesFromS3ResponseHandler, 2);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    protected JSONObject customParsingWithTemplate(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("blackRules");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("whiteRules");
        JSONObject optJSONObject = jSONObject.optJSONObject("replaceRule");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("groupMessageRenderRules");
        JSONArray optJSONArray3 = jSONObject.optJSONArray("replaceNumberWithDigits");
        if (isMatchBlackRules(optJSONArray)) {
            Log.i(TAG, "customParsingWithTemplate -- has black rule matching");
            return null;
        }
        Log.i(TAG, "customParsingWithTemplate -- no black rule matching");
        JSONObject matchWhiteRules = matchWhiteRules(optJSONArray2, optJSONObject);
        if (matchWhiteRules == null) {
            fetchParsingTemplatesFromCloud(this);
            return genericParsingWhenCustomParsingFailed();
        }
        if (GroupNotificationHelper.isGroupMessage(matchWhiteRules)) {
            Log.d(TAG, "customParsingWithTemplate - Populate group messages related fields");
            matchWhiteRules = addFieldsForGroupNotification(matchWhiteRules, optJSONObject2);
        }
        JSONObject updateNumbersReplacedWithDigits = updateNumbersReplacedWithDigits(matchWhiteRules, optJSONArray3);
        JSONHelpers.mergeJSONObjects(this.notification, updateNumbersReplacedWithDigits, BaseParser.FIELDS_TO_KEEP);
        return updateNumbersReplacedWithDigits;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    public /* bridge */ /* synthetic */ String extraField(JSONObject jSONObject, JSONObject jSONObject2) {
        return super.extraField(jSONObject, jSONObject2);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    JSONObject getCommsAppTemplate() {
        return this.baseTemplateProvider.getParserTemplateByAppId(this.notification.optString("packageIdentifier"));
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    JSONObject getCustomGenericAppTemplate() {
        return this.baseTemplateProvider.getCustomGenericParserTemplateByAppId(this.notification.optString("packageIdentifier"));
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3ResponseHandler
    public void handleGetFocusFilterTemplatesFromS3Response(boolean z, String str, String str2, String str3, GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType) {
        GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType2 = GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE;
        String str4 = MetricsConstants.GET_TEMPLATE_FROM_CLOUD_SUCCESS;
        if (templateType == templateType2 && z && !Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String str5 = TAG;
                Log.d(str5, "handleGetFocusFilterTemplatesFromS3Response - " + templateType.getFileName() + RealTimeTextConstants.COLON_SPACE + str);
                String str6 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("handleGetFocusFilterTemplatesFromS3Response - etag: ");
                sb.append(str2);
                Log.d(str6, sb.toString());
                MetricsRecorder.getInstance().recordCounter(str4);
                this.baseTemplateProvider.updateTemplateFromS3(templateType.getFileName(), jSONObject);
                new StorageWrapper().putLocal(templateType.name() + "_etag", str2);
                return;
            } catch (Exception e) {
                Log.e(TAG, "handleGetFocusFilterTemplatesFromS3Response - Error: ", e);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_ERROR);
                return;
            }
        }
        String str7 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("handleGetFocusFilterTemplatesFromS3Response - success: ");
        sb2.append(z);
        sb2.append(" etag is null ? ");
        sb2.append(str2 == null);
        Log.i(str7, sb2.toString());
        MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
        if (!z || str2 == null) {
            str4 = MetricsConstants.GET_TEMPLATE_FROM_CLOUD_ERROR;
        }
        metricsRecorder.recordCounter(str4);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseParser
    public JSONObject parse() {
        try {
            String str = TAG;
            Log.d(str, "parse -- uuid: " + this.notification.optString("uuid"));
            JSONObject commsAppTemplate = getCommsAppTemplate();
            JSONObject customGenericAppTemplate = getCustomGenericAppTemplate();
            if (commsAppTemplate != null && !isInboxStyleMultiAlexaMessages(this.notification)) {
                return customParsingWithTemplate(commsAppTemplate);
            }
            if (customGenericAppTemplate != null) {
                return customParsingWithTemplate(customGenericAppTemplate);
            }
            if (commsAppTemplate != null && customGenericAppTemplate != null) {
                Log.i(TAG, "parse -- Overwrite to use generic parsing for this notification");
                return new GenericParser(this.notification).parse();
            }
            Log.i(TAG, "parse -- Template for this app is unavailable, use generic parser");
            return new GenericParser(this.notification).parse();
        } catch (Exception e) {
            Log.e(TAG, "CustomAppParser Parse Exception.", e);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    public /* bridge */ /* synthetic */ String replaceMessagePhoneNumber(String str) {
        return super.replaceMessagePhoneNumber(str);
    }
}
