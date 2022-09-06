package kotlinx.serialization.internal;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import org.jetbrains.annotations.NotNull;
/* compiled from: Util.kt */
@Deprecated(level = DeprecationLevel.WARNING, message = "HexConverter slipped into public API surface accidentally and will be removed in the future releases. You can copy-paste it to your project or (better) find a polished implementation that initially was intended for public uses.")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/internal/HexConverter;", "", "()V", "hexCode", "", "parseHexBinary", "", "s", "printHexBinary", "data", "lowerCase", "", "toHexString", JsonReportFormat.COUNT, "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class HexConverter {
    public static final HexConverter INSTANCE = new HexConverter();
    private static final String hexCode = "0123456789ABCDEF";

    private HexConverter() {
    }

    public static /* synthetic */ String printHexBinary$default(HexConverter hexConverter, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return hexConverter.printHexBinary(bArr, z);
    }

    @NotNull
    public final byte[] parseHexBinary(@NotNull String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        return InternalHexConverter.INSTANCE.parseHexBinary(s);
    }

    @NotNull
    public final String printHexBinary(@NotNull byte[] data, boolean z) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return InternalHexConverter.INSTANCE.printHexBinary(data, z);
    }

    @NotNull
    public final String toHexString(int i) {
        return InternalHexConverter.INSTANCE.toHexString(i);
    }
}
