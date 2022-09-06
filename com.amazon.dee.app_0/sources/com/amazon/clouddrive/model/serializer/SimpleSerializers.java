package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SimpleSerializers {
    public static void serializeBigDecimal(BigDecimal bigDecimal, JsonGenerator jsonGenerator) throws IOException {
        if (bigDecimal == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(bigDecimal);
        }
    }

    public static void serializeBigInteger(BigInteger bigInteger, JsonGenerator jsonGenerator) throws IOException {
        if (bigInteger == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(bigInteger);
        }
    }

    public static void serializeBoolean(Boolean bool, JsonGenerator jsonGenerator) throws IOException {
        if (bool == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    public static void serializeByte(Byte b, JsonGenerator jsonGenerator) throws IOException {
        if (b == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber((int) b.byteValue());
        }
    }

    public static void serializeByteBuffer(ByteBuffer byteBuffer, JsonGenerator jsonGenerator) throws IOException {
        if (byteBuffer == null) {
            jsonGenerator.writeNull();
        } else if (byteBuffer.hasArray()) {
            jsonGenerator.writeBinary(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
        } else {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.duplicate().get(bArr);
            jsonGenerator.writeBinary(bArr);
        }
    }

    public static void serializeCharacter(Character ch, JsonGenerator jsonGenerator) throws IOException {
        if (ch == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(String.valueOf(ch));
        }
    }

    public static void serializeDate(Date date, JsonGenerator jsonGenerator) throws IOException {
        if (date == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(date.getTime() / 1000.0d);
        }
    }

    public static void serializeDouble(Double d, JsonGenerator jsonGenerator) throws IOException {
        if (d == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(d.doubleValue());
        }
    }

    public static void serializeFloat(Float f, JsonGenerator jsonGenerator) throws IOException {
        if (f == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    public static void serializeInteger(Integer num, JsonGenerator jsonGenerator) throws IOException {
        if (num == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    public static void serializeLong(Long l, JsonGenerator jsonGenerator) throws IOException {
        if (l == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    public static void serializePrimitiveBoolean(boolean z, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeBoolean(z);
    }

    public static void serializePrimitiveByte(byte b, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber((int) b);
    }

    public static void serializePrimitiveChar(char c, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeString(String.valueOf(c));
    }

    public static void serializePrimitiveDouble(double d, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber(d);
    }

    public static void serializePrimitiveFloat(float f, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber(f);
    }

    public static void serializePrimitiveInt(int i, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber(i);
    }

    public static void serializePrimitiveLong(long j, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber(j);
    }

    public static void serializePrimitiveShort(short s, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumber((int) s);
    }

    public static void serializeShort(Short sh, JsonGenerator jsonGenerator) throws IOException {
        if (sh == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber((int) sh.shortValue());
        }
    }

    public static void serializeString(String str, JsonGenerator jsonGenerator) throws IOException {
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }
}
