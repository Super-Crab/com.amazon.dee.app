package com.amazon.alexa.redesign.entity.viewtypes;

import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class ViewTypeModel {
    private final int viewPosition;

    public ViewTypeModel(int i) {
        this.viewPosition = i;
    }

    public abstract Action getAction();

    public abstract Map<String, Object> getActionMetricsData();

    public int getModelPosition() {
        return this.viewPosition;
    }

    public abstract String getModelType();
}
