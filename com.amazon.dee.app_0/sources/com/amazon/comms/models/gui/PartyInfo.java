package com.amazon.comms.models.gui;

import com.amazon.comms.models.scrubber.ScrubbedString;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class PartyInfo implements Serializable {
    @JsonProperty
    private ScrubbedString credit;
    private boolean dropInPermission;
    @JsonProperty
    private ScrubbedString endpointDescription;
    @JsonProperty
    private ScrubbedString name;

    /* loaded from: classes11.dex */
    public static class PartyInfoBuilder {
        private ScrubbedString credit;
        private boolean dropInPermission;
        private ScrubbedString endpointDescription;
        private ScrubbedString name;

        PartyInfoBuilder() {
        }

        public PartyInfo build() {
            return new PartyInfo(this.name, this.endpointDescription, this.credit, this.dropInPermission);
        }

        public PartyInfoBuilder credit(String str) {
            this.credit = new ScrubbedString(str);
            return this;
        }

        public PartyInfoBuilder dropInPermission(boolean z) {
            this.dropInPermission = z;
            return this;
        }

        public PartyInfoBuilder endpointDescription(String str) {
            this.endpointDescription = new ScrubbedString(str);
            return this;
        }

        public PartyInfoBuilder name(String str) {
            this.name = new ScrubbedString(str);
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PartyInfo.PartyInfoBuilder(name=");
            outline107.append(this.name);
            outline107.append(", endpointDescription=");
            outline107.append(this.endpointDescription);
            outline107.append(", credit=");
            outline107.append(this.credit);
            outline107.append(", dropInPermission=");
            return GeneratedOutlineSupport1.outline97(outline107, this.dropInPermission, ")");
        }
    }

    public PartyInfo() {
    }

    public static PartyInfoBuilder builder() {
        return new PartyInfoBuilder();
    }

    @JsonIgnore
    public String getCredit() {
        ScrubbedString scrubbedString = this.credit;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    public boolean getDropInPermission() {
        return this.dropInPermission;
    }

    @JsonIgnore
    public String getEndpointDescription() {
        ScrubbedString scrubbedString = this.endpointDescription;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    @JsonIgnore
    public String getName() {
        ScrubbedString scrubbedString = this.name;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    @JsonIgnore
    public void setCredit(String str) {
        this.credit = new ScrubbedString(str);
    }

    @JsonIgnore
    public void setEndpointDescription(String str) {
        this.endpointDescription = new ScrubbedString(str);
    }

    @JsonIgnore
    public void setName(String str) {
        this.name = new ScrubbedString(str);
    }

    public PartyInfo(ScrubbedString scrubbedString, ScrubbedString scrubbedString2, ScrubbedString scrubbedString3, boolean z) {
        this.name = scrubbedString;
        this.endpointDescription = scrubbedString2;
        this.credit = scrubbedString3;
        this.dropInPermission = z;
    }
}
