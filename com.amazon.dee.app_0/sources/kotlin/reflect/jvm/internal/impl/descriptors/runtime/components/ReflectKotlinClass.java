package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectKotlinClass.kt */
/* loaded from: classes2.dex */
public final class ReflectKotlinClass implements KotlinJvmBinaryClass {
    public static final Factory Factory = new Factory(null);
    @NotNull
    private final KotlinClassHeader classHeader;
    @NotNull
    private final Class<?> klass;

    /* compiled from: ReflectKotlinClass.kt */
    /* loaded from: classes2.dex */
    public static final class Factory {
        private Factory() {
        }

        @Nullable
        public final ReflectKotlinClass create(@NotNull Class<?> klass) {
            Intrinsics.checkParameterIsNotNull(klass, "klass");
            ReadKotlinClassHeaderAnnotationVisitor readKotlinClassHeaderAnnotationVisitor = new ReadKotlinClassHeaderAnnotationVisitor();
            ReflectClassStructure.INSTANCE.loadClassAnnotations(klass, readKotlinClassHeaderAnnotationVisitor);
            KotlinClassHeader createHeader = readKotlinClassHeaderAnnotationVisitor.createHeader();
            if (createHeader != null) {
                return new ReflectKotlinClass(klass, createHeader, null);
            }
            return null;
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ReflectKotlinClass(Class<?> cls, KotlinClassHeader kotlinClassHeader) {
        this.klass = cls;
        this.classHeader = kotlinClassHeader;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ReflectKotlinClass) && Intrinsics.areEqual(this.klass, ((ReflectKotlinClass) obj).klass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public KotlinClassHeader getClassHeader() {
        return this.classHeader;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public ClassId getClassId() {
        return ReflectClassUtilKt.getClassId(this.klass);
    }

    @NotNull
    public final Class<?> getKlass() {
        return this.klass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public String getLocation() {
        String replace$default;
        StringBuilder sb = new StringBuilder();
        String name = this.klass.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "klass.name");
        replace$default = StringsKt__StringsJVMKt.replace$default(name, '.', '/', false, 4, (Object) null);
        return GeneratedOutlineSupport1.outline91(sb, replace$default, ".class");
    }

    public int hashCode() {
        return this.klass.hashCode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void loadClassAnnotations(@NotNull KotlinJvmBinaryClass.AnnotationVisitor visitor, @Nullable byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        ReflectClassStructure.INSTANCE.loadClassAnnotations(this.klass, visitor);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline146(ReflectKotlinClass.class, sb, RealTimeTextConstants.COLON_SPACE);
        sb.append(this.klass);
        return sb.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void visitMembers(@NotNull KotlinJvmBinaryClass.MemberVisitor visitor, @Nullable byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        ReflectClassStructure.INSTANCE.visitMembers(this.klass, visitor);
    }

    public /* synthetic */ ReflectKotlinClass(Class cls, KotlinClassHeader kotlinClassHeader, DefaultConstructorMarker defaultConstructorMarker) {
        this(cls, kotlinClassHeader);
    }
}
