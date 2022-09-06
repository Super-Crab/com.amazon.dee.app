package com.amazon.alexa.redesign.view.templates.skillsSingleTemplate;

import com.amazon.alexa.redesign.entity.templates.SkillsSingleTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class SkillsSingleTemplateViewItem extends RecyclerViewItem {
    private final SkillsSingleTemplateModel model;

    public SkillsSingleTemplateViewItem(SkillsSingleTemplateModel skillsSingleTemplateModel) {
        this.model = skillsSingleTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.SkillsSingle;
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
    public SkillsSingleTemplateModel mo2390getModel() {
        return this.model;
    }
}
