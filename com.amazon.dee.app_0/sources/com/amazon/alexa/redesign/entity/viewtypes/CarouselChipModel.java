package com.amazon.alexa.redesign.entity.viewtypes;

import com.amazon.alexa.redesign.actions.Action;
import java.util.List;
/* loaded from: classes10.dex */
public class CarouselChipModel extends CarouselModel {
    public static final String CAROUSEL_CHIP_TYPE = "CarouselChipView";

    public CarouselChipModel(List<ViewTypeModel> list, Action action, int i) {
        super(list, action, i);
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.CarouselModel, com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return CAROUSEL_CHIP_TYPE;
    }
}
