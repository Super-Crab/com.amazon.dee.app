package com.amazon.alexa.biloba.model;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class PersonIdentityWorkflow {
    @SerializedName("id")
    String id;
    @SerializedName(Metrics.STEPS)
    List<PersonIdentityStep> steps;

    public String getId() {
        return this.id;
    }

    public List<PersonIdentityStep> getSteps() {
        return this.steps;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentityWorkflow{id='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", steps=");
        return GeneratedOutlineSupport1.outline94(outline107, this.steps, JsonReaderKt.END_OBJ);
    }
}
