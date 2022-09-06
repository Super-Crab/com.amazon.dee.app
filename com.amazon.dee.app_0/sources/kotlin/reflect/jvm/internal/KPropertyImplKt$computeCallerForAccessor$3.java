package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KPropertyImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"computeFieldCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Field;", "field", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KPropertyImplKt$computeCallerForAccessor$3 extends Lambda implements Function1<Field, CallerImpl<? extends Field>> {
    final /* synthetic */ boolean $isGetter;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$1 $isJvmStaticProperty$1;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$2 $isNotNullProperty$2;
    final /* synthetic */ KPropertyImpl.Accessor $this_computeCallerForAccessor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KPropertyImplKt$computeCallerForAccessor$3(KPropertyImpl.Accessor accessor, boolean z, KPropertyImplKt$computeCallerForAccessor$2 kPropertyImplKt$computeCallerForAccessor$2, KPropertyImplKt$computeCallerForAccessor$1 kPropertyImplKt$computeCallerForAccessor$1) {
        super(1);
        this.$this_computeCallerForAccessor = accessor;
        this.$isGetter = z;
        this.$isNotNullProperty$2 = kPropertyImplKt$computeCallerForAccessor$2;
        this.$isJvmStaticProperty$1 = kPropertyImplKt$computeCallerForAccessor$1;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final CallerImpl<Field> mo12165invoke(@NotNull Field field) {
        boolean isJvmFieldPropertyInCompanionObject;
        Intrinsics.checkParameterIsNotNull(field, "field");
        isJvmFieldPropertyInCompanionObject = KPropertyImplKt.isJvmFieldPropertyInCompanionObject(this.$this_computeCallerForAccessor.mo11473getProperty().mo11480getDescriptor());
        return (isJvmFieldPropertyInCompanionObject || !Modifier.isStatic(field.getModifiers())) ? this.$isGetter ? this.$this_computeCallerForAccessor.isBound() ? new CallerImpl.FieldGetter.BoundInstance(field, KPropertyImplKt.getBoundReceiver(this.$this_computeCallerForAccessor)) : new CallerImpl.FieldGetter.Instance(field) : this.$this_computeCallerForAccessor.isBound() ? new CallerImpl.FieldSetter.BoundInstance(field, this.$isNotNullProperty$2.mo12560invoke(), KPropertyImplKt.getBoundReceiver(this.$this_computeCallerForAccessor)) : new CallerImpl.FieldSetter.Instance(field, this.$isNotNullProperty$2.mo12560invoke()) : this.$isJvmStaticProperty$1.mo12560invoke() ? this.$isGetter ? this.$this_computeCallerForAccessor.isBound() ? new CallerImpl.FieldGetter.BoundJvmStaticInObject(field) : new CallerImpl.FieldGetter.JvmStaticInObject(field) : this.$this_computeCallerForAccessor.isBound() ? new CallerImpl.FieldSetter.BoundJvmStaticInObject(field, this.$isNotNullProperty$2.mo12560invoke()) : new CallerImpl.FieldSetter.JvmStaticInObject(field, this.$isNotNullProperty$2.mo12560invoke()) : this.$isGetter ? new CallerImpl.FieldGetter.Static(field) : new CallerImpl.FieldSetter.Static(field, this.$isNotNullProperty$2.mo12560invoke());
    }
}
