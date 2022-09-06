package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
/* loaded from: classes.dex */
public final class GetFocusFilterTemplatesFromS3RequestBody {
    private final String osType;
    private final String templateType;

    /* loaded from: classes.dex */
    public static class Builder {
        private String osType;
        private String templateType;

        public GetFocusFilterTemplatesFromS3RequestBody build() {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.osType), "The provided osType was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.templateType), "The provided templateType was null or empty");
            return new GetFocusFilterTemplatesFromS3RequestBody(this.osType, this.templateType);
        }

        public Builder setOSType(String str) {
            this.osType = str;
            return this;
        }

        public Builder setTemplateType(String str) {
            this.templateType = str;
            return this;
        }
    }

    public String getOsType() {
        return this.osType;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    private GetFocusFilterTemplatesFromS3RequestBody(String str, String str2) {
        this.osType = str;
        this.templateType = str2;
    }
}
