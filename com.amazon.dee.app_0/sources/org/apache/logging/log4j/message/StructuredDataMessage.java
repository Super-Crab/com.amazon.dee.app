package org.apache.logging.log4j.message;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.StringBuilders;
@AsynchronouslyFormattable
/* loaded from: classes4.dex */
public class StructuredDataMessage extends MapMessage<StructuredDataMessage, String> {
    private static final int HASHVAL = 31;
    private static final int MAX_LENGTH = 32;
    private static final long serialVersionUID = 1703221292892071920L;
    private StructuredDataId id;
    private final int maxLength;
    private String message;
    private String type;

    /* loaded from: classes4.dex */
    public enum Format {
        XML,
        FULL
    }

    public StructuredDataMessage(String str, String str2, String str3) {
        this(str, str2, str3, 32);
    }

    private void asXml(StructuredDataId structuredDataId, StringBuilder sb) {
        sb.append("<StructuredData>\n");
        sb.append("<type>");
        sb.append(this.type);
        sb.append("</type>\n");
        sb.append("<id>");
        sb.append(structuredDataId);
        sb.append("</id>\n");
        super.asXml(sb);
        sb.append("\n</StructuredData>\n");
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String asString() {
        return asString(Format.FULL, null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StructuredDataMessage.class != obj.getClass()) {
            return false;
        }
        StructuredDataMessage structuredDataMessage = (StructuredDataMessage) obj;
        if (!super.equals(obj)) {
            return false;
        }
        String str = this.type;
        if (str == null ? structuredDataMessage.type != null : !str.equals(structuredDataMessage.type)) {
            return false;
        }
        StructuredDataId structuredDataId = this.id;
        if (structuredDataId == null ? structuredDataMessage.id != null : !structuredDataId.equals(structuredDataMessage.id)) {
            return false;
        }
        String str2 = this.message;
        return str2 == null ? structuredDataMessage.message == null : str2.equals(structuredDataMessage.message);
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        asString(Format.FULL, null, sb);
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.message;
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.MultiformatMessage
    public String[] getFormats() {
        String[] strArr = new String[Format.values().length];
        Format[] values = Format.values();
        int length = values.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            strArr[i2] = values[i].name();
            i++;
            i2++;
        }
        return strArr;
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        return asString(Format.FULL, null);
    }

    public StructuredDataId getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.type;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        StructuredDataId structuredDataId = this.id;
        int hashCode3 = (hashCode2 + (structuredDataId != null ? structuredDataId.hashCode() : 0)) * 31;
        String str2 = this.message;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 + i;
    }

    protected void setId(String str) {
        this.id = new StructuredDataId(str, null, null);
    }

    protected void setMessageFormat(String str) {
        this.message = str;
    }

    protected void setType(String str) {
        if (str.length() <= 32) {
            this.type = str;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("structured data type exceeds maximum length of 32 characters: ", str));
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String toString() {
        return asString(null, null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, boolean z) {
        validateKey(str);
    }

    protected void validateKey(String str) {
        if (this.maxLength > 0 && str.length() > this.maxLength) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Structured data keys are limited to ");
            outline107.append(this.maxLength);
            outline107.append(" characters. key: ");
            outline107.append(str);
            throw new IllegalArgumentException(outline107.toString());
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < '!' || charAt > '~' || charAt == '=' || charAt == ']' || charAt == '\"') {
                throw new IllegalArgumentException("Structured data keys must contain printable US ASCII charactersand may not contain a space, =, ], or \"");
            }
        }
    }

    public StructuredDataMessage(String str, String str2, String str3, int i) {
        this.id = new StructuredDataId(str, (String[]) null, (String[]) null, i);
        this.message = str2;
        this.type = str3;
        this.maxLength = i;
    }

    private Format getFormat(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (Format.XML.name().equalsIgnoreCase(str)) {
                    return Format.XML;
                }
                if (Format.FULL.name().equalsIgnoreCase(str)) {
                    return Format.FULL;
                }
            }
            return null;
        }
        return Format.FULL;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String asString(String str) {
        try {
            return asString((Format) EnglishEnums.valueOf(Format.class, str), null);
        } catch (IllegalArgumentException unused) {
            return asString();
        }
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.util.MultiFormatStringBuilderFormattable
    public void formatTo(String[] strArr, StringBuilder sb) {
        asString(getFormat(strArr), null, sb);
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.MultiformatMessage
    public String getFormattedMessage(String[] strArr) {
        return asString(getFormat(strArr), null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public StructuredDataMessage newInstance(Map<String, String> map) {
        return new StructuredDataMessage(this, map);
    }

    protected void setId(StructuredDataId structuredDataId) {
        this.id = structuredDataId;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, byte b) {
        validateKey(str);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, char c) {
        validateKey(str);
    }

    public final String asString(Format format, StructuredDataId structuredDataId) {
        StringBuilder sb = new StringBuilder();
        asString(format, structuredDataId, sb);
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, double d) {
        validateKey(str);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, float f) {
        validateKey(str);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, int i) {
        validateKey(str);
    }

    public StructuredDataMessage(String str, String str2, String str3, Map<String, String> map) {
        this(str, str2, str3, map, 32);
    }

    public final void asString(Format format, StructuredDataId structuredDataId, StringBuilder sb) {
        String format2;
        boolean equals = Format.FULL.equals(format);
        if (equals) {
            if (getType() == null) {
                return;
            }
            sb.append(getType());
            sb.append(Chars.SPACE);
        }
        StructuredDataId id = getId();
        if (id != null) {
            structuredDataId = id.makeId(structuredDataId);
        }
        if (structuredDataId == null || structuredDataId.getName() == null) {
            return;
        }
        if (Format.XML.equals(format)) {
            asXml(structuredDataId, sb);
            return;
        }
        sb.append(JsonReaderKt.BEGIN_LIST);
        StringBuilders.appendValue(sb, structuredDataId);
        sb.append(Chars.SPACE);
        appendMap(sb);
        sb.append(JsonReaderKt.END_LIST);
        if (!equals || (format2 = getFormat()) == null) {
            return;
        }
        sb.append(Chars.SPACE);
        sb.append(format2);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, long j) {
        validateKey(str);
    }

    public StructuredDataMessage(String str, String str2, String str3, Map<String, String> map, int i) {
        super(map);
        this.id = new StructuredDataId(str, (String[]) null, (String[]) null, i);
        this.message = str2;
        this.type = str3;
        this.maxLength = i;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, Object obj) {
        validateKey(str);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, short s) {
        validateKey(str);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, String str2) {
        validateKey(str);
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2) {
        this(structuredDataId, str, str2, 32);
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, int i) {
        this.id = structuredDataId;
        this.message = str;
        this.type = str2;
        this.maxLength = i;
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, Map<String, String> map) {
        this(structuredDataId, str, str2, map, 32);
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, Map<String, String> map, int i) {
        super(map);
        this.id = structuredDataId;
        this.message = str;
        this.type = str2;
        this.maxLength = i;
    }

    private StructuredDataMessage(StructuredDataMessage structuredDataMessage, Map<String, String> map) {
        super(map);
        this.id = structuredDataMessage.id;
        this.message = structuredDataMessage.message;
        this.type = structuredDataMessage.type;
        this.maxLength = 32;
    }

    protected StructuredDataMessage() {
        this.maxLength = 32;
    }
}
