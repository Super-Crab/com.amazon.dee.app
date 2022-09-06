package org.codehaus.jackson;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
/* loaded from: classes5.dex */
public enum JsonToken {
    NOT_AVAILABLE(null),
    START_OBJECT(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN),
    END_OBJECT(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE),
    START_ARRAY("["),
    END_ARRAY("]"),
    FIELD_NAME(null),
    VALUE_EMBEDDED_OBJECT(null),
    VALUE_STRING(null),
    VALUE_NUMBER_INT(null),
    VALUE_NUMBER_FLOAT(null),
    VALUE_TRUE("true"),
    VALUE_FALSE(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE),
    VALUE_NULL("null");
    
    final String _serialized;
    final byte[] _serializedBytes;
    final char[] _serializedChars;

    JsonToken(String str) {
        if (str == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
            return;
        }
        this._serialized = str;
        this._serializedChars = str.toCharArray();
        int length = this._serializedChars.length;
        this._serializedBytes = new byte[length];
        for (int i = 0; i < length; i++) {
            this._serializedBytes[i] = (byte) this._serializedChars[i];
        }
    }

    public byte[] asByteArray() {
        return this._serializedBytes;
    }

    public char[] asCharArray() {
        return this._serializedChars;
    }

    public String asString() {
        return this._serialized;
    }

    public boolean isNumeric() {
        return this == VALUE_NUMBER_INT || this == VALUE_NUMBER_FLOAT;
    }

    public boolean isScalarValue() {
        return ordinal() >= VALUE_EMBEDDED_OBJECT.ordinal();
    }
}
