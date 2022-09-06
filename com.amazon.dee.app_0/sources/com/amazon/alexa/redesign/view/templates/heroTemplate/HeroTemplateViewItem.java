package com.amazon.alexa.redesign.view.templates.heroTemplate;

import com.amazon.alexa.redesign.entity.templates.HeroCardTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class HeroTemplateViewItem extends RecyclerViewItem {
    private final HeroCardTemplateModel model;

    public HeroTemplateViewItem(HeroCardTemplateModel heroCardTemplateModel) {
        this.model = heroCardTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.HeroTemplate;
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
    public HeroCardTemplateModel mo2390getModel() {
        return this.model;
    }
}
