package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class MetaDataPayload implements JsonObjectSerializable {
    private final String appVersion;
    private final String deviceSerialNumber;
    private final String deviceType;
    private final String locale;
    private final String wwEndSeqnum;
    private final String wwStartSeqnum;

    /* loaded from: classes.dex */
    public static final class Builder {
        public String appVersion;
        public String deviceSerialNumber;
        public String deviceType;
        public String locale;
        public String wwEndSeqnum;
        public String wwStartSeqnum;

        public Builder appVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public MetaDataPayload build() {
            Preconditions.notNull(this.appVersion, "appVersion");
            Preconditions.notNull(this.locale, "locale");
            Preconditions.notNull(this.wwStartSeqnum, "wwStartSeqnum");
            Preconditions.notNull(this.wwEndSeqnum, "wwEndSeqnum");
            Preconditions.notNull(this.deviceSerialNumber, "deviceSerialNumber");
            Preconditions.notNull(this.deviceType, "deviceType");
            return new MetaDataPayload(this);
        }

        public Builder deviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder deviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder locale(String str) {
            this.locale = str;
            return this;
        }

        public Builder wwEndSeqnum(int i) {
            this.wwEndSeqnum = String.valueOf(i);
            return this;
        }

        public Builder wwStartSeqnum(int i) {
            this.wwStartSeqnum = String.valueOf(i);
            return this;
        }
    }

    public MetaDataPayload(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.appVersion = builder.appVersion;
        this.locale = builder.locale;
        this.wwStartSeqnum = builder.wwStartSeqnum;
        this.wwEndSeqnum = builder.wwEndSeqnum;
        this.deviceType = builder.deviceType;
        this.deviceSerialNumber = builder.deviceSerialNumber;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("appVersion", this.appVersion).put("locale", this.locale).put("deviceType", this.deviceType).put("deviceSerialNumber", this.deviceSerialNumber).put("wwStartSeqnum", this.wwStartSeqnum).put("wwEndSeqnum", this.wwEndSeqnum);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetaDataPayload{appVersion='");
        GeneratedOutlineSupport1.outline176(outline107, this.appVersion, Chars.QUOTE, ", locale='");
        GeneratedOutlineSupport1.outline176(outline107, this.locale, Chars.QUOTE, ", wwStartSeqnum='");
        GeneratedOutlineSupport1.outline176(outline107, this.wwStartSeqnum, Chars.QUOTE, ", wwEndSeqnum='");
        GeneratedOutlineSupport1.outline176(outline107, this.wwEndSeqnum, Chars.QUOTE, ", deviceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceType, Chars.QUOTE, ", deviceSerialNumber='");
        return GeneratedOutlineSupport1.outline90(outline107, this.deviceSerialNumber, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
