package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClassMapperLite.kt */
/* loaded from: classes4.dex */
final class ClassMapperLite$map$1$1 extends Lambda implements Function2<String, String, Unit> {
    final /* synthetic */ Map $this_apply;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassMapperLite$map$1$1(Map map) {
        super(2);
        this.$this_apply = map;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(String str, String str2) {
        invoke2(str, str2);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String kotlinSimpleName, @NotNull String javaInternalName) {
        Intrinsics.checkParameterIsNotNull(kotlinSimpleName, "kotlinSimpleName");
        Intrinsics.checkParameterIsNotNull(javaInternalName, "javaInternalName");
        Map map = this.$this_apply;
        String outline72 = GeneratedOutlineSupport1.outline72("kotlin/", kotlinSimpleName);
        map.put(outline72, Matrix.MATRIX_TYPE_RANDOM_LT + javaInternalName + ';');
    }
}
