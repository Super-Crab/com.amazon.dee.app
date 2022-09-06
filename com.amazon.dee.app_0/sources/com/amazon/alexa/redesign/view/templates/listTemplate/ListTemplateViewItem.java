package com.amazon.alexa.redesign.view.templates.listTemplate;

import com.amazon.alexa.redesign.entity.templates.ListTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class ListTemplateViewItem extends RecyclerViewItem {
    private final ListTemplateModel model;

    public ListTemplateViewItem(ListTemplateModel listTemplateModel) {
        this.model = listTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.ListTemplate;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public Map<String, Object> getTopLevelMetricsObject() {
        return this.model.getTopLevelMetricsObject();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public boolean isDismissible() {
        return this.model.isDismissible();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    /* renamed from: getModel  reason: collision with other method in class */
    public ListTemplateModel mo2390getModel() {
        return this.model;
    }
}
