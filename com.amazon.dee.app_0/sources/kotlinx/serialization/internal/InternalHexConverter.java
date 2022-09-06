package kotlinx.serialization.internal;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Util.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/internal/InternalHexConverter;", "", "()V", "hexCode", "", "hexToInt", "", "ch", "", "parseHexBinary", "", "s", "printHexBinary", "data", "lowerCase", "", "toHexString", JsonReportFormat.COUNT, "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InternalHexConverter {
    public static final InternalHexConverter INSTANCE = new InternalHexConverter();
    private static final String hexCode = "0123456789ABCDEF";

    private InternalHexConverter() {
    }

    private final int hexToInt(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        char c2 = 'A';
        if ('A' > c || 'F' < c) {
            c2 = 'a';
            if ('a' > c || 'f' < c) {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    public static /* synthetic */ String printHexBinary$default(InternalHexConverter internalHexConverter, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return internalHexConverter.printHexBinary(bArr, z);
    }

    @NotNull
    public final byte[] parseHexBinary(@NotNull String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        int length = s.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                int hexToInt = hexToInt(s.charAt(i));
                int i2 = i + 1;
                int hexToInt2 = hexToInt(s.charAt(i2));
                if ((hexToInt == -1 || hexToInt2 == -1) ? false : true) {
                    bArr[i / 2] = (byte) ((hexToInt << 4) + hexToInt2);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid hex chars: ");
                    outline107.append(s.charAt(i));
                    outline107.append(s.charAt(i2));
                    throw new IllegalArgumentException(outline107.toString().toString());
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("HexBinary string must be even length".toString());
    }

    @NotNull
    public final String printHexBinary(@NotNull byte[] data, boolean z) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append(hexCode.charAt((b >> 4) & 15));
            sb.append(hexCode.charAt(b & 15));
        }
        if (!z) {
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "r.toString()");
            return sb2;
        }
        String sb3 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb3, "r.toString()");
        String lowerCase = sb3.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    @NotNull
    public final String toHexString(int i) {
        String trimStart;
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (24 - (i2 * 8)));
        }
        boolean z = true;
        trimStart = StringsKt__StringsKt.trimStart(printHexBinary(bArr, true), '0');
        if (trimStart.length() <= 0) {
            z = false;
        }
        if (!z) {
            trimStart = null;
        }
        return trimStart != null ? trimStart : "0";
    }
}
