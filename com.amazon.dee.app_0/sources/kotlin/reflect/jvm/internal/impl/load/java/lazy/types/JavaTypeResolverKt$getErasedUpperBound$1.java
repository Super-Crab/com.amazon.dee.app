package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes3.dex */
public final class JavaTypeResolverKt$getErasedUpperBound$1 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ TypeParameterDescriptor $this_getErasedUpperBound;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaTypeResolverKt$getErasedUpperBound$1(TypeParameterDescriptor typeParameterDescriptor) {
        super(0);
        this.$this_getErasedUpperBound = typeParameterDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SimpleType mo12560invoke() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can't compute erased upper bound of type parameter `");
        outline107.append(this.$this_getErasedUpperBound);
        outline107.append('`');
        SimpleType createErrorType = ErrorUtils.createErrorType(outline107.toString());
        Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy… type parameter `$this`\")");
        return createErrorType;
    }
}
