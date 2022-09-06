package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
/* loaded from: classes3.dex */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
    final /* synthetic */ ClassDescriptor $annotationClass;
    final /* synthetic */ List $result;
    final /* synthetic */ SourceElement $source;
    private final HashMap<Name, ConstantValue<?>> arguments = new HashMap<>();
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1(BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl, ClassDescriptor classDescriptor, List list, SourceElement sourceElement) {
        this.this$0 = binaryClassAnnotationAndConstantLoaderImpl;
        this.$annotationClass = classDescriptor;
        this.$result = list;
        this.$source = sourceElement;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConstantValue<?> createConstant(Name name, Object obj) {
        ConstantValue<?> createConstantValue = ConstantValueFactory.INSTANCE.createConstantValue(obj);
        if (createConstantValue != null) {
            return createConstantValue;
        }
        ErrorValue.Companion companion = ErrorValue.Companion;
        return companion.create("Unsupported annotation argument: " + name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    public void visit(@Nullable Name name, @Nullable Object obj) {
        if (name != null) {
            this.arguments.put(name, createConstant(name, obj));
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    @Nullable
    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull final Name name, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        final ArrayList arrayList = new ArrayList();
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = this.this$0;
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation = binaryClassAnnotationAndConstantLoaderImpl.loadAnnotation(classId, sourceElement, arrayList);
        if (loadAnnotation == null) {
            Intrinsics.throwNpe();
        }
        return new KotlinJvmBinaryClass.AnnotationArgumentVisitor(loadAnnotation, name, arrayList) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1
            private final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $$delegate_0;
            final /* synthetic */ ArrayList $list;
            final /* synthetic */ Name $name;
            final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $visitor;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$visitor = loadAnnotation;
                this.$name = name;
                this.$list = arrayList;
                this.$$delegate_0 = loadAnnotation;
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visit(@Nullable Name name2, @Nullable Object obj) {
                this.$$delegate_0.visit(name2, obj);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            @Nullable
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull Name name2, @NotNull ClassId classId2) {
                Intrinsics.checkParameterIsNotNull(name2, "name");
                Intrinsics.checkParameterIsNotNull(classId2, "classId");
                return this.$$delegate_0.visitAnnotation(name2, classId2);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            @Nullable
            public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(@NotNull Name name2) {
                Intrinsics.checkParameterIsNotNull(name2, "name");
                return this.$$delegate_0.visitArray(name2);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitClassLiteral(@NotNull Name name2, @NotNull ClassLiteralValue value) {
                Intrinsics.checkParameterIsNotNull(name2, "name");
                Intrinsics.checkParameterIsNotNull(value, "value");
                this.$$delegate_0.visitClassLiteral(name2, value);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitEnd() {
                HashMap hashMap;
                this.$visitor.visitEnd();
                hashMap = BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1.this.arguments;
                hashMap.put(this.$name, new AnnotationValue((AnnotationDescriptor) CollectionsKt.single((List<? extends Object>) this.$list)));
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitEnum(@NotNull Name name2, @NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
                Intrinsics.checkParameterIsNotNull(name2, "name");
                Intrinsics.checkParameterIsNotNull(enumClassId, "enumClassId");
                Intrinsics.checkParameterIsNotNull(enumEntryName, "enumEntryName");
                this.$$delegate_0.visitEnum(name2, enumClassId, enumEntryName);
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    @Nullable
    public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(@NotNull final Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1
            private final ArrayList<ConstantValue<?>> elements = new ArrayList<>();

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
            public void visit(@Nullable Object obj) {
                ConstantValue<?> createConstant;
                ArrayList<ConstantValue<?>> arrayList = this.elements;
                createConstant = BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1.this.createConstant(name, obj);
                arrayList.add(createConstant);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
            public void visitClassLiteral(@NotNull ClassLiteralValue value) {
                Intrinsics.checkParameterIsNotNull(value, "value");
                this.elements.add(new KClassValue(value));
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
            public void visitEnd() {
                HashMap hashMap;
                ValueParameterDescriptor annotationParameterByName = DescriptorResolverUtils.getAnnotationParameterByName(name, BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1.this.$annotationClass);
                if (annotationParameterByName != null) {
                    hashMap = BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1.this.arguments;
                    Name name2 = name;
                    ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
                    List<? extends ConstantValue<?>> compact = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(this.elements);
                    KotlinType type = annotationParameterByName.getType();
                    Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
                    hashMap.put(name2, constantValueFactory.createArrayValue(compact, type));
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
            public void visitEnum(@NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
                Intrinsics.checkParameterIsNotNull(enumClassId, "enumClassId");
                Intrinsics.checkParameterIsNotNull(enumEntryName, "enumEntryName");
                this.elements.add(new EnumValue(enumClassId, enumEntryName));
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralValue value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.arguments.put(name, new KClassValue(value));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    public void visitEnd() {
        this.$result.add(new AnnotationDescriptorImpl(this.$annotationClass.getDefaultType(), this.arguments, this.$source));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
    public void visitEnum(@NotNull Name name, @NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(enumClassId, "enumClassId");
        Intrinsics.checkParameterIsNotNull(enumEntryName, "enumEntryName");
        this.arguments.put(name, new EnumValue(enumClassId, enumEntryName));
    }
}
