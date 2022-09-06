package org.joda.time;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class IllegalFieldValueException extends IllegalArgumentException {
    private static final long serialVersionUID = 6305711765985447737L;
    private final DateTimeFieldType iDateTimeFieldType;
    private final DurationFieldType iDurationFieldType;
    private final String iFieldName;
    private final Number iLowerBound;
    private String iMessage;
    private final Number iNumberValue;
    private final String iStringValue;
    private final Number iUpperBound;

    public IllegalFieldValueException(String str, Number number, Number number2, Number number3) {
        super(createMessage(str, number, number2, number3, null));
        this.iDateTimeFieldType = null;
        this.iDurationFieldType = null;
        this.iFieldName = str;
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = number2;
        this.iUpperBound = number3;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(String str, String str2) {
        super(createMessage(str, str2));
        this.iDateTimeFieldType = null;
        this.iDurationFieldType = null;
        this.iFieldName = str;
        this.iStringValue = str2;
        this.iNumberValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, Number number, Number number2, Number number3) {
        super(createMessage(dateTimeFieldType.getName(), number, number2, number3, null));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = number2;
        this.iUpperBound = number3;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, Number number, String str) {
        super(createMessage(dateTimeFieldType.getName(), number, null, null, str));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, String str) {
        super(createMessage(dateTimeFieldType.getName(), str));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iStringValue = str;
        this.iNumberValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DurationFieldType durationFieldType, Number number, Number number2, Number number3) {
        super(createMessage(durationFieldType.getName(), number, number2, number3, null));
        this.iDateTimeFieldType = null;
        this.iDurationFieldType = durationFieldType;
        this.iFieldName = durationFieldType.getName();
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = number2;
        this.iUpperBound = number3;
        this.iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DurationFieldType durationFieldType, String str) {
        super(createMessage(durationFieldType.getName(), str));
        this.iDateTimeFieldType = null;
        this.iDurationFieldType = durationFieldType;
        this.iFieldName = durationFieldType.getName();
        this.iStringValue = str;
        this.iNumberValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }

    private static String createMessage(String str, Number number, Number number2, Number number3, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Value ");
        sb.append(number);
        sb.append(" for ");
        sb.append(str);
        sb.append(Chars.SPACE);
        if (number2 == null) {
            if (number3 == null) {
                sb.append("is not supported");
            } else {
                sb.append("must not be larger than ");
                sb.append(number3);
            }
        } else if (number3 == null) {
            sb.append("must not be smaller than ");
            sb.append(number2);
        } else {
            sb.append("must be in the range [");
            sb.append(number2);
            sb.append(JsonReaderKt.COMMA);
            sb.append(number3);
            sb.append(JsonReaderKt.END_LIST);
        }
        if (str2 != null) {
            sb.append(RealTimeTextConstants.COLON_SPACE);
            sb.append(str2);
        }
        return sb.toString();
    }

    private static String createMessage(String str, String str2) {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Value ");
        if (str2 == null) {
            outline103.append("null");
        } else {
            outline103.append('\"');
            outline103.append(str2);
            outline103.append('\"');
        }
        outline103.append(" for ");
        outline103.append(str);
        outline103.append(Chars.SPACE);
        outline103.append("is not supported");
        return outline103.toString();
    }

    public DateTimeFieldType getDateTimeFieldType() {
        return this.iDateTimeFieldType;
    }

    public DurationFieldType getDurationFieldType() {
        return this.iDurationFieldType;
    }

    public String getFieldName() {
        return this.iFieldName;
    }

    public Number getIllegalNumberValue() {
        return this.iNumberValue;
    }

    public String getIllegalStringValue() {
        return this.iStringValue;
    }

    public String getIllegalValueAsString() {
        String str = this.iStringValue;
        return str == null ? String.valueOf(this.iNumberValue) : str;
    }

    public Number getLowerBound() {
        return this.iLowerBound;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.iMessage;
    }

    public Number getUpperBound() {
        return this.iUpperBound;
    }

    public void prependMessage(String str) {
        if (this.iMessage != null) {
            if (str == null) {
                return;
            }
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(this.iMessage);
            str = outline113.toString();
        }
        this.iMessage = str;
    }
}
