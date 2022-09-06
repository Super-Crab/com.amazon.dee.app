package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl;
/* compiled from: KPropertyImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isJvmStaticProperty", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KPropertyImplKt$computeCallerForAccessor$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ KPropertyImpl.Accessor $this_computeCallerForAccessor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KPropertyImplKt$computeCallerForAccessor$1(KPropertyImpl.Accessor accessor) {
        super(0);
        this.$this_computeCallerForAccessor = accessor;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
        return Boolean.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [boolean, java.lang.Boolean] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final Boolean mo12560invoke() {
        return this.$this_computeCallerForAccessor.mo11473getProperty().mo11480getDescriptor().mo12070getAnnotations().hasAnnotation(UtilKt.getJVM_STATIC());
    }
}
