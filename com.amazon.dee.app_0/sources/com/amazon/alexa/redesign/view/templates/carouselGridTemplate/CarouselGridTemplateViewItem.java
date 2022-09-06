package com.amazon.alexa.redesign.view.templates.carouselGridTemplate;

import com.amazon.alexa.redesign.entity.templates.CarouselGridTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselGridTemplateViewItem extends RecyclerViewItem {
    private final CarouselGridTemplateModel model;

    public CarouselGridTemplateViewItem(CarouselGridTemplateModel carouselGridTemplateModel) {
        this.model = carouselGridTemplateModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.CarouselGridTemplate;
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
    public CarouselGridTemplateModel mo2390getModel() {
        return this.model;
    }
}
