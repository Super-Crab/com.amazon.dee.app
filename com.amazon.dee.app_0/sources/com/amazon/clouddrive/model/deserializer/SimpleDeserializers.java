package com.amazon.clouddrive.model.deserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SimpleDeserializers {
    public static BigDecimal deserializeBigDecimal(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return jsonParser.getDecimalValue();
    }

    public static BigInteger deserializeBigInteger(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return jsonParser.getBigIntegerValue();
    }

    public static Boolean deserializeBoolean(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Boolean.valueOf(jsonParser.getBooleanValue());
    }

    public static Byte deserializeByte(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Byte.valueOf(jsonParser.getByteValue());
    }

    public static ByteBuffer deserializeByteBuffer(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return ByteBuffer.wrap(jsonParser.getBinaryValue());
    }

    public static Character deserializeCharacter(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        if (jsonParser.getTextLength() == 1) {
            return Character.valueOf(jsonParser.getTextCharacters()[jsonParser.getTextOffset()]);
        }
        throw new JsonParseException("Expected char, got string", jsonParser.getTokenLocation());
    }

    public static Date deserializeDate(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return new Date((long) (jsonParser.getDoubleValue() * 1000.0d));
    }

    public static Double deserializeDouble(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Double.valueOf(jsonParser.getDoubleValue());
    }

    public static Float deserializeFloat(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Float.valueOf(jsonParser.getFloatValue());
    }

    public static Integer deserializeInteger(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Integer.valueOf(jsonParser.getIntValue());
    }

    public static Long deserializeLong(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Long.valueOf(jsonParser.getLongValue());
    }

    public static boolean deserializePrimitiveBoolean(JsonParser jsonParser) throws IOException {
        return jsonParser.getBooleanValue();
    }

    public static byte deserializePrimitiveByte(JsonParser jsonParser) throws IOException {
        return jsonParser.getByteValue();
    }

    public static char deserializePrimitiveChar(JsonParser jsonParser) throws IOException {
        if (jsonParser.getTextLength() == 1) {
            return jsonParser.getTextCharacters()[jsonParser.getTextOffset()];
        }
        throw new JsonParseException("Expected char, got string", jsonParser.getTokenLocation());
    }

    public static double deserializePrimitiveDouble(JsonParser jsonParser) throws IOException {
        return jsonParser.getDoubleValue();
    }

    public static float deserializePrimitiveFloat(JsonParser jsonParser) throws IOException {
        return jsonParser.getFloatValue();
    }

    public static int deserializePrimitiveInt(JsonParser jsonParser) throws IOException {
        return jsonParser.getIntValue();
    }

    public static long deserializePrimitiveLong(JsonParser jsonParser) throws IOException {
        return jsonParser.getLongValue();
    }

    public static short deserializePrimitiveShort(JsonParser jsonParser) throws IOException {
        return jsonParser.getShortValue();
    }

    public static Short deserializeShort(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Short.valueOf(jsonParser.getShortValue());
    }

    public static String deserializeString(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return jsonParser.getText();
    }
}
