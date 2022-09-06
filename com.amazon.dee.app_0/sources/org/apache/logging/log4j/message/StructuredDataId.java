package org.apache.logging.log4j.message;

import amazon.speech.model.DirectiveIntent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.Strings;
/* loaded from: classes4.dex */
public class StructuredDataId implements Serializable, StringBuilderFormattable {
    private static final String AT_SIGN = "@";
    private static final int MAX_LENGTH = 32;
    public static final int RESERVED = -1;
    private static final long serialVersionUID = 9031746276396249990L;
    private final int enterpriseNumber;
    private final String name;
    private final String[] optional;
    private final String[] required;
    public static final StructuredDataId TIME_QUALITY = new StructuredDataId("timeQuality", null, new String[]{"tzKnown", "isSynced", "syncAccuracy"});
    public static final StructuredDataId ORIGIN = new StructuredDataId("origin", null, new String[]{"ip", "enterpriseId", "software", "swVersion"});
    public static final StructuredDataId META = new StructuredDataId("meta", null, new String[]{DirectiveIntent.INTENT_KEY_SEQUENCE_ID, "sysUpTime", "language"});

    public StructuredDataId(String str) {
        this(str, (String[]) null, (String[]) null, 32);
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        if (isReserved()) {
            sb.append(this.name);
            return;
        }
        sb.append(this.name);
        sb.append(AT_SIGN);
        sb.append(this.enterpriseNumber);
    }

    public int getEnterpriseNumber() {
        return this.enterpriseNumber;
    }

    public String getName() {
        return this.name;
    }

    public String[] getOptional() {
        return this.optional;
    }

    public String[] getRequired() {
        return this.required;
    }

    public boolean isReserved() {
        return this.enterpriseNumber <= 0;
    }

    public StructuredDataId makeId(StructuredDataId structuredDataId) {
        return structuredDataId == null ? this : makeId(structuredDataId.getName(), structuredDataId.getEnterpriseNumber());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.name.length() + 10);
        formatTo(sb);
        return sb.toString();
    }

    public StructuredDataId(String str, int i) {
        this(str, (String[]) null, (String[]) null, i);
    }

    public StructuredDataId makeId(String str, int i) {
        String[] strArr;
        if (i <= 0) {
            return this;
        }
        String str2 = this.name;
        String[] strArr2 = null;
        if (str2 != null) {
            strArr2 = this.required;
            strArr = this.optional;
            str = str2;
        } else {
            strArr = null;
        }
        return new StructuredDataId(str, i, strArr2, strArr);
    }

    public StructuredDataId(String str, String[] strArr, String[] strArr2) {
        this(str, strArr, strArr2, 32);
    }

    public StructuredDataId(String str, String[] strArr, String[] strArr2, int i) {
        int i2;
        if (str != null) {
            i = i <= 0 ? 32 : i;
            if (str.length() <= i) {
                i2 = str.indexOf(AT_SIGN);
            } else {
                throw new IllegalArgumentException(String.format("Length of id %s exceeds maximum of %d characters", str, Integer.valueOf(i)));
            }
        } else {
            i2 = -1;
        }
        if (i2 > 0) {
            this.name = str.substring(0, i2);
            this.enterpriseNumber = Integer.parseInt(str.substring(i2 + 1));
        } else {
            this.name = str;
            this.enterpriseNumber = -1;
        }
        this.required = strArr;
        this.optional = strArr2;
    }

    public StructuredDataId(String str, int i, String[] strArr, String[] strArr2) {
        this(str, i, strArr, strArr2, 32);
    }

    public StructuredDataId(String str, int i, String[] strArr, String[] strArr2, int i2) {
        if (str != null) {
            if (str.contains(AT_SIGN)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Structured id name cannot contain an ");
                outline107.append(Strings.quote(AT_SIGN));
                throw new IllegalArgumentException(outline107.toString());
            } else if (i > 0) {
                this.name = str;
                this.enterpriseNumber = i;
                String outline74 = GeneratedOutlineSupport1.outline74(str, AT_SIGN, i);
                if (i2 > 0 && outline74.length() > i2) {
                    throw new IllegalArgumentException("Length of id exceeds maximum of " + i2 + " characters: " + outline74);
                }
                this.required = strArr;
                this.optional = strArr2;
                return;
            } else {
                throw new IllegalArgumentException("No enterprise number was supplied");
            }
        }
        throw new IllegalArgumentException("No structured id name was supplied");
    }
}
