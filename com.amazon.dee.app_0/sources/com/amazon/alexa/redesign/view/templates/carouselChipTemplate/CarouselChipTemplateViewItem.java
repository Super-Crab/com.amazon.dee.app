package com.amazon.alexa.redesign.view.templates.carouselChipTemplate;

import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselChipTemplateViewItem extends RecyclerViewItem {
    private final CarouselChipTemplateModel model;

    public CarouselChipTemplateViewItem(CarouselChipTemplateModel carouselChipTemplateModel) {
        this.model = carouselChipTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.CarouselChipTemplate;
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
    public CarouselChipTemplateModel mo2390getModel() {
        return this.model;
    }
}
