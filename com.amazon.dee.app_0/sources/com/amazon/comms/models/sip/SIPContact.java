package com.amazon.comms.models.sip;

import com.amazon.comms.models.scrubber.ScrubbedString;
import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
@RedactInLogs
/* loaded from: classes11.dex */
public class SIPContact {
    private String id;
    @JsonProperty
    private ScrubbedString name;
    private String sipInstance;
    private String uri;

    /* loaded from: classes11.dex */
    public static class SIPContactBuilder {
        private String id;
        private ScrubbedString name;
        private String sipInstance;
        private String uri;

        SIPContactBuilder() {
        }

        public SIPContact build() {
            return new SIPContact(this.name, this.uri, this.id, this.sipInstance);
        }

        public SIPContactBuilder id(String str) {
            this.id = str;
            return this;
        }

        public SIPContactBuilder name(String str) {
            this.name = str != null ? new ScrubbedString(str) : null;
            return this;
        }

        public SIPContactBuilder sipInstance(String str) {
            this.sipInstance = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPContact.SIPContactBuilder(name=");
            outline107.append(this.name);
            outline107.append(", uri=");
            outline107.append(this.uri);
            outline107.append(", id=");
            outline107.append(this.id);
            outline107.append(", sipInstance=");
            return GeneratedOutlineSupport1.outline91(outline107, this.sipInstance, ")");
        }

        public SIPContactBuilder uri(String str) {
            this.uri = str;
            return this;
        }
    }

    public SIPContact() {
    }

    public static SIPContactBuilder builder() {
        return new SIPContactBuilder();
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[0]);
    }

    public String getId() {
        return this.id;
    }

    @JsonIgnore
    public String getName() {
        ScrubbedString scrubbedString = this.name;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    public String getSipInstance() {
        return this.sipInstance;
    }

    public String getUri() {
        return this.uri;
    }

    public void setId(String str) {
        this.id = str;
    }

    @JsonIgnore
    public void setName(String str) {
        this.name = str != null ? new ScrubbedString(str) : null;
    }

    public void setSipInstance(String str) {
        this.sipInstance = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    private SIPContact(ScrubbedString scrubbedString, String str, String str2, String str3) {
        this.name = scrubbedString;
        this.uri = str;
        this.id = str2;
        this.sipInstance = str3;
    }
}
