package com.amazon.alexa.growth.coachmark.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ConfigurationProfileContent {
    @SerializedName("coachMarks")
    private List<CoachMarkItem> coachMarkItems;
    @SerializedName("name")
    private String name;

    public ConfigurationProfileContent(String str, List<CoachMarkItem> list) {
        this.name = str;
        this.coachMarkItems = list;
    }

    public List<CoachMarkItem> getCoachMarkItems() {
        return this.coachMarkItems;
    }

    public String getName() {
        return this.name;
    }
}
