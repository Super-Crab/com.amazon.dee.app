package com.amazon.alexa.redesign.entity.viewtypes;

import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel;
/* loaded from: classes10.dex */
public class AlarmsTimersModel extends IconTitleSubtitleModel {
    public static final String ALARMS_TIMERS_TYPE = "AlarmsTimersView";
    private String state;
    private final long triggerTime;

    public AlarmsTimersModel(IconTitleSubtitleModel.Builder builder, String str, long j) {
        super(builder);
        this.state = str;
        this.triggerTime = j;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel, com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return ALARMS_TIMERS_TYPE;
    }

    public String getState() {
        return this.state;
    }

    public long getTriggerTime() {
        return this.triggerTime;
    }

    public void setState(String str) {
        this.state = str;
    }
}
