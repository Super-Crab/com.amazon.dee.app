package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaAnnotationArguments.kt */
/* loaded from: classes2.dex */
public abstract class ReflectJavaAnnotationArgument implements JavaAnnotationArgument {
    public static final Factory Factory = new Factory(null);
    @Nullable
    private final Name name;

    /* compiled from: ReflectJavaAnnotationArguments.kt */
    /* loaded from: classes2.dex */
    public static final class Factory {
        private Factory() {
        }

        @NotNull
        public final ReflectJavaAnnotationArgument create(@NotNull Object value, @Nullable Name name) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            return ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(value.getClass()) ? new ReflectJavaEnumValueAnnotationArgument(name, (Enum) value) : value instanceof Annotation ? new ReflectJavaAnnotationAsAnnotationArgument(name, (Annotation) value) : value instanceof Object[] ? new ReflectJavaArrayAnnotationArgument(name, (Object[]) value) : value instanceof Class ? new ReflectJavaClassObjectAnnotationArgument(name, (Class) value) : new ReflectJavaLiteralAnnotationArgument(name, value);
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ReflectJavaAnnotationArgument(@Nullable Name name) {
        this.name = name;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument
    @Nullable
    public Name getName() {
        return this.name;
    }
}
