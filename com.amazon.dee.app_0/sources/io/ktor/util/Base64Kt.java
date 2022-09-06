package io.ktor.util;

import com.amazon.device.messaging.ADMConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Base64.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0001H\u0007\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u000eH\u0007\u001a\f\u0010\u000f\u001a\u00020\u0001*\u00020\u0001H\u0007\u001a\f\u0010\u000f\u001a\u00020\u0001*\u00020\u000eH\u0007\u001a\f\u0010\u0010\u001a\u00020\u0005*\u00020\u0005H\u0000\u001a\f\u0010\u0011\u001a\u00020\u0007*\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"BASE64_ALPHABET", "", "BASE64_INVERSE_ALPHABET", "", "BASE64_MASK", "", "BASE64_PAD", "", "clearFrom", "", "", ADMConstants.EXTRA_FROM, "", "decodeBase64", "Lkotlinx/io/core/ByteReadPacket;", "encodeBase64", "fromBase64", "toBase64", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Base64Kt {
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int[] BASE64_INVERSE_ALPHABET;
    private static final byte BASE64_MASK = 63;
    private static final char BASE64_PAD = '=';

    static {
        int indexOf$default;
        int[] iArr = new int[256];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) BASE64_ALPHABET, (char) i, 0, false, 6, (Object) null);
            iArr[i] = indexOf$default;
        }
        BASE64_INVERSE_ALPHABET = iArr;
    }

    public static final void clearFrom(@NotNull byte[] receiver$0, int i) {
        IntRange until;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        until = RangesKt___RangesKt.until(i, receiver$0.length);
        Iterator<Integer> it2 = until.iterator();
        while (it2.hasNext()) {
            receiver$0[((IntIterator) it2).nextInt()] = 0;
        }
    }

    @InternalAPI
    @NotNull
    public static final String decodeBase64(@NotNull ByteReadPacket receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[4];
        while (receiver$0.m12336getRemaining() > 0) {
            int readAvailable = receiver$0.readAvailable(bArr);
            int length = bArr.length;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                i2 |= fromBase64(bArr[i]) << ((3 - i3) * 6);
                i++;
                i3++;
            }
            int length2 = bArr.length - 2;
            int length3 = bArr.length - readAvailable;
            if (length2 >= length3) {
                while (true) {
                    sb.append((char) ((i2 >> (length2 * 8)) & 255));
                    if (length2 != length3) {
                        length2--;
                    }
                }
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @InternalAPI
    @NotNull
    public static final String encodeBase64(@NotNull ByteReadPacket receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[3];
        while (receiver$0.m12336getRemaining() > 0) {
            int readAvailable = receiver$0.readAvailable(bArr);
            clearFrom(bArr, readAvailable);
            int length = ((bArr.length - readAvailable) * 8) / 6;
            int i = ((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255);
            int length2 = bArr.length;
            if (length2 >= length) {
                while (true) {
                    sb.append(toBase64((i >> (length2 * 6)) & 63));
                    if (length2 == length) {
                        break;
                    }
                    length2--;
                }
            }
            for (int i2 = 0; i2 < length; i2++) {
                sb.append('=');
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final byte fromBase64(byte b) {
        return (byte) (((byte) BASE64_INVERSE_ALPHABET[b & 255]) & 63);
    }

    public static final char toBase64(int i) {
        return BASE64_ALPHABET.charAt(i);
    }

    @InternalAPI
    @NotNull
    public static final String decodeBase64(@NotNull String receiver$0) {
        int lastIndex;
        String str;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            lastIndex = StringsKt__StringsKt.getLastIndex(receiver$0);
            while (true) {
                if (lastIndex < 0) {
                    str = "";
                    break;
                }
                if (!(receiver$0.charAt(lastIndex) == '=')) {
                    str = receiver$0.substring(0, lastIndex + 1);
                    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    break;
                }
                lastIndex--;
            }
            BytePacketBuilder.writeStringUtf8(str);
            return decodeBase64(BytePacketBuilder.build());
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @InternalAPI
    @NotNull
    public static final String encodeBase64(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            BytePacketBuilder.writeStringUtf8(receiver$0);
            return encodeBase64(BytePacketBuilder.build());
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }
}
