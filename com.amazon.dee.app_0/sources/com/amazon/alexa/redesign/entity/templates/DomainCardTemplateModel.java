package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.CardModel;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class DomainCardTemplateModel extends CardModel {
    private final String cardId;
    private final JSONObject cardPayload;
    private final String contentProvider;
    private final String contentType;

    public DomainCardTemplateModel(@NonNull JSONObject jSONObject) {
        super(jSONObject);
        this.cardPayload = jSONObject;
        this.cardId = jSONObject.optString("customTemplateRoute", "") + "/" + jSONObject.optString("id", "");
        this.contentType = jSONObject.optString("contentType", "");
        this.contentProvider = jSONObject.optString("contentProvider", "");
    }

    @Override // com.amazon.alexa.redesign.entity.CardModel
    @NonNull
    public String getCardId() {
        return this.cardId;
    }

    @NonNull
    public JSONObject getCardPayload() {
        try {
            return new JSONObject(this.cardPayload.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String getContentProvider() {
        return this.contentProvider;
    }

    public String getContentType() {
        return this.contentType;
    }
}
