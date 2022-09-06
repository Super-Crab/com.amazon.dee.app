package com.amazon.alexa.accessory.notificationpublisher.parser;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.providers.ThinTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ThinCustomAppParser extends BaseCustomAppParser {
    private static final String TAG = ThinTemplateProvider.class.getSimpleName();
    private Context context;

    public ThinCustomAppParser(@NonNull Context context, @NonNull JSONObject jSONObject) {
        super(jSONObject);
        this.context = context;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    JSONObject customParsingWithTemplate(JSONObject jSONObject) throws Exception {
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
        return ThinTemplateProvider.getInstance().getParserTemplateByAppId(this.notification.optString("packageIdentifier"));
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    JSONObject getCustomGenericAppTemplate() {
        return ThinTemplateProvider.getInstance().getCustomGenericParserTemplateByAppId(this.notification.optString("packageIdentifier"));
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
            Log.e(TAG, "ThinCustomAppParser Parse Exception.", e);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser
    public /* bridge */ /* synthetic */ String replaceMessagePhoneNumber(String str) {
        return super.replaceMessagePhoneNumber(str);
    }
}
