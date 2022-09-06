package com.amazon.alexa.redesign.actions;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class Action {
    protected final String accessibilityLabel;
    protected final String actionType;
    protected final String metaActionType;
    protected int position;
    protected HomeContract.Presenter presenter;
    protected ViewType viewType;

    public Action(String str, String str2, String str3) {
        this.actionType = str;
        this.metaActionType = str2;
        this.accessibilityLabel = str3;
    }

    public void bind(HomeContract.Presenter presenter, ViewType viewType, int i) {
        this.presenter = presenter;
        this.viewType = viewType;
        this.position = i;
    }

    public abstract void execute();

    public abstract void execute(Map<String, Object> map);

    public String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public Map<String, Object> getMetricData() {
        HashMap hashMap = new HashMap();
        hashMap.put(JsonFields.ACTION_TYPE, this.actionType);
        hashMap.put("metaActionType", this.metaActionType);
        return hashMap;
    }

    public boolean getOnClickUserLeavesHome() {
        return true;
    }
}
