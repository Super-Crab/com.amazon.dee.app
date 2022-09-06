package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.Nullable;
import com.amazon.alexa.redesign.actions.Action;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselModel extends ViewTypeModel {
    @Nullable
    private final Action action;
    private final List<ViewTypeModel> items;

    public CarouselModel(List<ViewTypeModel> list, Action action, int i) {
        super(i);
        this.items = list;
        this.action = action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    @Nullable
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    public List<ViewTypeModel> getItems() {
        return this.items;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return "";
    }
}
