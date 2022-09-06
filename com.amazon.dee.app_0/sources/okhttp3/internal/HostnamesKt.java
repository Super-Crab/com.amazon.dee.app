package okhttp3.internal;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.text.StringsKt__StringsKt;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
/* compiled from: hostnames.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003¨\u0006\u000f"}, d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", MetricsUtil.LegacyMetricTypes.LIMIT, "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class HostnamesKt {
    private static final boolean containsInvalidHostnameAsciiCodes(@NotNull String str) {
        int indexOf$default;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                return true;
            }
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", charAt, 0, false, 6, (Object) null);
            if (indexOf$default != -1) {
                return true;
            }
        }
        return false;
    }

    private static final boolean decodeIpv4Suffix(String str, int i, int i2, byte[] bArr, int i3) {
        int i4 = i3;
        while (i < i2) {
            if (i4 == bArr.length) {
                return false;
            }
            if (i4 != i3) {
                if (str.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int i5 = i;
            int i6 = 0;
            while (i5 < i2) {
                char charAt = str.charAt(i5);
                if (charAt < '0' || charAt > '9') {
                    break;
                } else if ((i6 == 0 && i != i5) || (i6 = ((i6 * 10) + charAt) - 48) > 255) {
                    return false;
                } else {
                    i5++;
                }
            }
            if (i5 - i == 0) {
                return false;
            }
            bArr[i4] = (byte) i6;
            i4++;
            i = i5;
        }
        return i4 == i3 + 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0098, code lost:
        if (r12 == r8.length) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009a, code lost:
        if (r13 != (-1)) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009c, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009d, code lost:
        r1 = r12 - r13;
        java.lang.System.arraycopy(r8, r13, r8, r8.length - r1, r1);
        java.util.Arrays.fill(r8, r13, (r8.length - r12) + r13, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b0, code lost:
        return java.net.InetAddress.getByAddress(r8);
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.net.InetAddress decodeIpv6(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 177
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
    }

    private static final String inet6AddressToAscii(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (i4 < bArr.length) {
            int i5 = i4;
            while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                i5 += 2;
            }
            int i6 = i5 - i4;
            if (i6 > i2 && i6 >= 4) {
                i3 = i4;
                i2 = i6;
            }
            i4 = i5 + 2;
        }
        Buffer buffer = new Buffer();
        while (i < bArr.length) {
            if (i == i3) {
                buffer.mo12596writeByte(58);
                i += i2;
                if (i == 16) {
                    buffer.mo12596writeByte(58);
                }
            } else {
                if (i > 0) {
                    buffer.mo12596writeByte(58);
                }
                buffer.mo12598writeHexadecimalUnsignedLong((Util.and(bArr[i], 255) << 8) | Util.and(bArr[i + 1], 255));
                i += 2;
            }
        }
        return buffer.readUtf8();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String toCanonicalHost(@org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            java.lang.String r0 = "$this$toCanonicalHost"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r0 = 2
            r1 = 0
            r2 = 0
            java.lang.String r3 = ":"
            boolean r3 = kotlin.text.StringsKt.contains$default(r5, r3, r1, r0, r2)
            r4 = 1
            if (r3 == 0) goto L60
            java.lang.String r3 = "["
            boolean r3 = kotlin.text.StringsKt.startsWith$default(r5, r3, r1, r0, r2)
            if (r3 == 0) goto L2b
            java.lang.String r3 = "]"
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r5, r3, r1, r0, r2)
            if (r0 == 0) goto L2b
            int r0 = r5.length()
            int r0 = r0 - r4
            java.net.InetAddress r0 = decodeIpv6(r5, r4, r0)
            goto L33
        L2b:
            int r0 = r5.length()
            java.net.InetAddress r0 = decodeIpv6(r5, r1, r0)
        L33:
            if (r0 == 0) goto L5f
            byte[] r1 = r0.getAddress()
            int r2 = r1.length
            r3 = 16
            if (r2 != r3) goto L48
            java.lang.String r5 = "address"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)
            java.lang.String r5 = inet6AddressToAscii(r1)
            return r5
        L48:
            int r1 = r1.length
            r2 = 4
            if (r1 != r2) goto L51
            java.lang.String r5 = r0.getHostAddress()
            return r5
        L51:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "Invalid IPv6 address: '"
            r2 = 39
            java.lang.String r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline73(r1, r5, r2)
            r0.<init>(r5)
            throw r0
        L5f:
            return r2
        L60:
            java.lang.String r5 = java.net.IDN.toASCII(r5)     // Catch: java.lang.IllegalArgumentException -> L8b
            java.lang.String r0 = "IDN.toASCII(host)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)     // Catch: java.lang.IllegalArgumentException -> L8b
            java.util.Locale r0 = java.util.Locale.US     // Catch: java.lang.IllegalArgumentException -> L8b
            java.lang.String r3 = "Locale.US"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)     // Catch: java.lang.IllegalArgumentException -> L8b
            java.lang.String r5 = r5.toLowerCase(r0)     // Catch: java.lang.IllegalArgumentException -> L8b
            java.lang.String r0 = "(this as java.lang.String).toLowerCase(locale)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)     // Catch: java.lang.IllegalArgumentException -> L8b
            int r0 = r5.length()     // Catch: java.lang.IllegalArgumentException -> L8b
            if (r0 != 0) goto L80
            r1 = r4
        L80:
            if (r1 == 0) goto L83
            return r2
        L83:
            boolean r0 = containsInvalidHostnameAsciiCodes(r5)     // Catch: java.lang.IllegalArgumentException -> L8b
            if (r0 == 0) goto L8a
            r5 = r2
        L8a:
            return r5
        L8b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.toCanonicalHost(java.lang.String):java.lang.String");
    }
}
