package kotlin.reflect.jvm.internal.calls;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationConstructorCaller.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010&\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "entry", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1 extends Lambda implements Function1<Map.Entry<? extends String, ? extends Object>, String> {
    public static final AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1 INSTANCE = new AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1();

    AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ String mo12165invoke(Map.Entry<? extends String, ? extends Object> entry) {
        return invoke2((Map.Entry<String, ? extends Object>) entry);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String invoke2(@NotNull Map.Entry<String, ? extends Object> entry) {
        String arrays;
        Intrinsics.checkParameterIsNotNull(entry, "entry");
        String key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof boolean[]) {
            arrays = Arrays.toString((boolean[]) value);
        } else if (value instanceof char[]) {
            arrays = Arrays.toString((char[]) value);
        } else if (value instanceof byte[]) {
            arrays = Arrays.toString((byte[]) value);
        } else if (value instanceof short[]) {
            arrays = Arrays.toString((short[]) value);
        } else if (value instanceof int[]) {
            arrays = Arrays.toString((int[]) value);
        } else if (value instanceof float[]) {
            arrays = Arrays.toString((float[]) value);
        } else if (value instanceof long[]) {
            arrays = Arrays.toString((long[]) value);
        } else if (value instanceof double[]) {
            arrays = Arrays.toString((double[]) value);
        } else {
            arrays = value instanceof Object[] ? Arrays.toString((Object[]) value) : value.toString();
        }
        return GeneratedOutlineSupport1.outline48(key, Chars.EQ, arrays);
    }
}
