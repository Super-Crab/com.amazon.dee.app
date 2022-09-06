package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSubstitution.kt */
/* loaded from: classes4.dex */
public abstract class TypeSubstitution {
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final TypeSubstitution EMPTY = new TypeSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeSubstitution$Companion$EMPTY$1
        @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
        @Nullable
        /* renamed from: get */
        public Void mo12121get(@NotNull KotlinType key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
        /* renamed from: get  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ TypeProjection mo12121get(KotlinType kotlinType) {
            return (TypeProjection) mo12121get(kotlinType);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
        public boolean isEmpty() {
            return true;
        }

        @NotNull
        public String toString() {
            return "Empty TypeSubstitution";
        }
    };

    /* compiled from: TypeSubstitution.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean approximateCapturedTypes() {
        return false;
    }

    public boolean approximateContravariantCapturedTypes() {
        return false;
    }

    @NotNull
    public final TypeSubstitutor buildSubstitutor() {
        TypeSubstitutor create = TypeSubstitutor.create(this);
        Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(this)");
        return create;
    }

    @NotNull
    public Annotations filterAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return annotations;
    }

    @Nullable
    /* renamed from: get */
    public abstract TypeProjection mo12121get(@NotNull KotlinType kotlinType);

    public boolean isEmpty() {
        return false;
    }

    @NotNull
    public KotlinType prepareTopLevelType(@NotNull KotlinType topLevelType, @NotNull Variance position) {
        Intrinsics.checkParameterIsNotNull(topLevelType, "topLevelType");
        Intrinsics.checkParameterIsNotNull(position, "position");
        return topLevelType;
    }
}
