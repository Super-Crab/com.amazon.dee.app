package com.amazon.alexa.biloba.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class PersonIdentityStepConfig {
    @SerializedName("isSetup")
    Boolean isSetup;
    @SerializedName("listSubtitle")
    String listSubtitle;
    @SerializedName("listTitle")
    String listTitle;
    @SerializedName("skip")
    Boolean skip;

    public String getListSubtitle() {
        return this.listSubtitle;
    }

    public String getListTitle() {
        return this.listTitle;
    }

    public Boolean getSetup() {
        return this.isSetup;
    }

    public Boolean getSkip() {
        return this.skip;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentityStepConfig{listTitle='");
        GeneratedOutlineSupport1.outline176(outline107, this.listTitle, Chars.QUOTE, ", listSubtitle='");
        GeneratedOutlineSupport1.outline176(outline107, this.listSubtitle, Chars.QUOTE, ", isSetup=");
        outline107.append(this.isSetup);
        outline107.append(", skip=");
        outline107.append(this.skip);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
