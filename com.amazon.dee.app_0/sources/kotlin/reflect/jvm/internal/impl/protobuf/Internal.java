package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
/* loaded from: classes4.dex */
public class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);

    /* loaded from: classes4.dex */
    public interface EnumLite {
        int getNumber();
    }

    /* loaded from: classes4.dex */
    public interface EnumLiteMap<T extends EnumLite> {
        /* renamed from: findValueByNumber */
        T mo11983findValueByNumber(int i);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return Utf8.isValidUtf8(bArr);
    }

    public static String toStringUtf8(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }
}
