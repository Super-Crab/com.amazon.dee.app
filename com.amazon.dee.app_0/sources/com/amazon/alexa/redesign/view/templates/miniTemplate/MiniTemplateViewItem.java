package com.amazon.alexa.redesign.view.templates.miniTemplate;

import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class MiniTemplateViewItem extends RecyclerViewItem {
    private final MiniCardTemplateModel model;

    public MiniTemplateViewItem(MiniCardTemplateModel miniCardTemplateModel) {
        this.model = miniCardTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        if (this.model.canPoll()) {
            return TemplateType.PollingMiniTemplate;
        }
        return TemplateType.MiniTemplate;
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
    public MiniCardTemplateModel mo2390getModel() {
        return this.model;
    }
}
