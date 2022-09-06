package com.amazon.alexa.redesign.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.DismissIdBuilder;
import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public abstract class CardModel {
    protected String cardId;
    private String cardTitle;
    protected String cardType;
    protected List<ViewTypeModel> childModels;
    private boolean clickToDismiss;
    private String contentId;
    private ContentMetadata contentMetadata;
    private String contentProvider;
    private String contentType;
    protected boolean isColdStart;
    private boolean isDismissible;
    private String lastRouteName;
    protected Map<String, Object> metricsData;
    protected String templateType;
    protected String viewUpdateType;

    public CardModel() {
        this.isDismissible = false;
        this.clickToDismiss = false;
        this.contentId = "";
        this.contentProvider = "";
        this.contentType = "";
        this.isColdStart = true;
        this.viewUpdateType = "";
        this.lastRouteName = "";
        this.metricsData = new HashMap();
    }

    private void initContentMetadata(JSONObject jSONObject) {
        try {
            this.contentMetadata = new ContentMetadata(jSONObject);
        } catch (Exception unused) {
            this.contentMetadata = null;
        }
    }

    public boolean canPoll() {
        return getContentMetadata() != null && getContentMetadata().getRefreshData() != null && getContentMetadata().getRefreshData().isEnabled() && getContentMetadata().getRefreshData().getIntervalInMs() > 0;
    }

    public String getCardId() {
        return this.cardId;
    }

    public String getCardTitle() {
        return this.cardTitle;
    }

    public String getCardType() {
        return this.cardType;
    }

    @Nullable
    public List<ViewTypeModel> getChildViewModels() {
        List<ViewTypeModel> list = this.childModels;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    public boolean getClickToDismiss() {
        return this.clickToDismiss;
    }

    public ContentMetadata getContentMetadata() {
        return this.contentMetadata;
    }

    public String getDismissCardId() {
        return DismissIdBuilder.buildDismissId(this.contentId, this.contentProvider, this.contentType);
    }

    public String getLastRouteName() {
        return this.lastRouteName;
    }

    public String getPublisher() {
        return null;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    @NonNull
    public Map<String, Object> getTopLevelMetricsObject() {
        return this.metricsData;
    }

    public String getViewUpdateType() {
        return this.viewUpdateType;
    }

    public boolean isColdStart() {
        return this.isColdStart;
    }

    @VisibleForTesting
    boolean isDismissible(@NonNull JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("contentActions");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null && ActionFactory.DISMISS_ACTION.equals(optJSONObject.optString(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE, ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setIsColdStart(boolean z) {
        this.isColdStart = z;
    }

    public void setLastRouteName(String str) {
        this.lastRouteName = str;
    }

    public void setViewUpdateType(String str) {
        this.viewUpdateType = str;
    }

    public boolean isDismissible() {
        return this.isDismissible;
    }

    public CardModel(@NonNull JSONObject jSONObject) {
        this.isDismissible = false;
        this.clickToDismiss = false;
        this.contentId = "";
        this.contentProvider = "";
        this.contentType = "";
        this.isColdStart = true;
        this.viewUpdateType = "";
        this.lastRouteName = "";
        this.metricsData = HomeCardsProducer.getTopLevelMetricsData(jSONObject);
        this.contentId = jSONObject.optString("contentId", "");
        this.contentProvider = jSONObject.optString("contentProvider", "");
        this.contentType = jSONObject.optString("contentType", "");
        this.clickToDismiss = jSONObject.optBoolean("clickToDismiss", false);
        this.isDismissible = isDismissible(jSONObject);
        initContentMetadata(jSONObject);
    }

    public CardModel(JSONObject jSONObject, List<ViewTypeModel> list) {
        this(jSONObject);
        this.childModels = new ArrayList(list);
    }
}
