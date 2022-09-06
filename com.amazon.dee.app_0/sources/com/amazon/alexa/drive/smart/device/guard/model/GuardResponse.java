package com.amazon.alexa.drive.smart.device.guard.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class GuardResponse {
    @SerializedName("guard")
    private ModeResult guard;
    @SerializedName("linkedEntities")
    private List<ModeResult> linkedEntities;
    @SerializedName("whiterock")
    private List<ModeResult> whiterock;

    public ModeResult getGuard() {
        return this.guard;
    }

    public List<ModeResult> getLinkedEntities() {
        return this.linkedEntities;
    }

    public List<ModeResult> getWhiterock() {
        return this.whiterock;
    }

    public void setGuard(ModeResult modeResult) {
        this.guard = modeResult;
    }

    public void setLinkedEntities(List<ModeResult> list) {
        this.linkedEntities = list;
    }

    public void setWhiterock(List<ModeResult> list) {
        this.whiterock = list;
    }
}
