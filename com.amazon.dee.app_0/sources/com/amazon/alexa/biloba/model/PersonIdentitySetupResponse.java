package com.amazon.alexa.biloba.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class PersonIdentitySetupResponse {
    @SerializedName("client")
    PersonIdentityClient client;
    @SerializedName("locale")
    String locale;
    @SerializedName("workflow")
    PersonIdentityWorkflow workflow;

    public PersonIdentityClient getClient() {
        return this.client;
    }

    public String getLocale() {
        return this.locale;
    }

    public PersonIdentityWorkflow getWorkflow() {
        return this.workflow;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentitySetupResponse{locale='");
        GeneratedOutlineSupport1.outline176(outline107, this.locale, Chars.QUOTE, ", client=");
        outline107.append(this.client);
        outline107.append(", workflow=");
        outline107.append(this.workflow);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
