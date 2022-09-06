package com.amazon.alexa.redesign.entity.viewtypes;

import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class ButtonModel extends ViewTypeModel {
    private static final String BASE_BUTTON_MODEL = "ButtonView";
    private final Action action;

    public ButtonModel(int i, Action action) {
        super(i);
        this.action = action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return BASE_BUTTON_MODEL;
    }
}
