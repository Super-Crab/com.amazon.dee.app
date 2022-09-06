package com.amazon.alexa.redesign.view.templates.domainCardTemplate;

import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class DomainCardTemplateViewItem extends RecyclerViewItem {
    private final DomainCardTemplateModel cardModel;

    public DomainCardTemplateViewItem(DomainCardTemplateModel domainCardTemplateModel) {
        this.cardModel = domainCardTemplateModel;
    }

    public JSONObject getPayload() {
        return this.cardModel.getCardPayload();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.CustomTemplate;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public Map<String, Object> getTopLevelMetricsObject() {
        return this.cardModel.getTopLevelMetricsObject();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public boolean isDismissible() {
        return this.cardModel.isDismissible();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    /* renamed from: getModel  reason: collision with other method in class */
    public DomainCardTemplateModel mo2390getModel() {
        return this.cardModel;
    }
}
