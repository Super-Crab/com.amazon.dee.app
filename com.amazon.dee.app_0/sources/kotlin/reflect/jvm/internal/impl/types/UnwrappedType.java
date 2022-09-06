package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinType.kt */
/* loaded from: classes4.dex */
public abstract class UnwrappedType extends KotlinType {
    private UnwrappedType() {
        super(null);
    }

    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public abstract UnwrappedType mo12132makeNullableAsSpecified(boolean z);

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public abstract UnwrappedType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner);

    @NotNull
    /* renamed from: replaceAnnotations */
    public abstract UnwrappedType mo12134replaceAnnotations(@NotNull Annotations annotations);

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public final UnwrappedType unwrap() {
        return this;
    }

    public /* synthetic */ UnwrappedType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
