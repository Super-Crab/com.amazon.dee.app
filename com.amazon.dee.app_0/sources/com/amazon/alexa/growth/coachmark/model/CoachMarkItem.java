package com.amazon.alexa.growth.coachmark.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class CoachMarkItem {
    @SerializedName("id")
    private String id;
    @SerializedName("tips")
    private List<Tooltip> tooltips;

    public CoachMarkItem(String str, List<Tooltip> list) {
        this.id = str;
        this.tooltips = list;
    }

    public String getId() {
        return this.id;
    }

    public List<Tooltip> getTooltips() {
        return this.tooltips;
    }
}
