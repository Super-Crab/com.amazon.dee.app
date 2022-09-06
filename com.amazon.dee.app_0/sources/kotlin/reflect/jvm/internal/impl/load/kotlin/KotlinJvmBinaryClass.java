package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinJvmBinaryClass.kt */
/* loaded from: classes3.dex */
public interface KotlinJvmBinaryClass {

    /* compiled from: KotlinJvmBinaryClass.kt */
    /* loaded from: classes3.dex */
    public interface AnnotationArgumentVisitor {
        void visit(@Nullable Name name, @Nullable Object obj);

        @Nullable
        AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId);

        @Nullable
        AnnotationArrayArgumentVisitor visitArray(@NotNull Name name);

        void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralValue classLiteralValue);

        void visitEnd();

        void visitEnum(@NotNull Name name, @NotNull ClassId classId, @NotNull Name name2);
    }

    /* compiled from: KotlinJvmBinaryClass.kt */
    /* loaded from: classes3.dex */
    public interface AnnotationArrayArgumentVisitor {
        void visit(@Nullable Object obj);

        void visitClassLiteral(@NotNull ClassLiteralValue classLiteralValue);

        void visitEnd();

        void visitEnum(@NotNull ClassId classId, @NotNull Name name);
    }

    /* compiled from: KotlinJvmBinaryClass.kt */
    /* loaded from: classes3.dex */
    public interface AnnotationVisitor {
        @Nullable
        AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement);

        void visitEnd();
    }

    /* compiled from: KotlinJvmBinaryClass.kt */
    /* loaded from: classes3.dex */
    public interface MemberVisitor {
        @Nullable
        AnnotationVisitor visitField(@NotNull Name name, @NotNull String str, @Nullable Object obj);

        @Nullable
        MethodAnnotationVisitor visitMethod(@NotNull Name name, @NotNull String str);
    }

    /* compiled from: KotlinJvmBinaryClass.kt */
    /* loaded from: classes3.dex */
    public interface MethodAnnotationVisitor extends AnnotationVisitor {
        @Nullable
        AnnotationArgumentVisitor visitParameterAnnotation(int i, @NotNull ClassId classId, @NotNull SourceElement sourceElement);
    }

    @NotNull
    KotlinClassHeader getClassHeader();

    @NotNull
    ClassId getClassId();

    @NotNull
    String getLocation();

    void loadClassAnnotations(@NotNull AnnotationVisitor annotationVisitor, @Nullable byte[] bArr);

    void visitMembers(@NotNull MemberVisitor memberVisitor, @Nullable byte[] bArr);
}
