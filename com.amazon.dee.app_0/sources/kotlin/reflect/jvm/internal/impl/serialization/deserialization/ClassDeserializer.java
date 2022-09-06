package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClassDeserializer.kt */
/* loaded from: classes4.dex */
public final class ClassDeserializer {
    @NotNull
    private static final Set<ClassId> BLACK_LIST;
    public static final Companion Companion = new Companion(null);
    private final Function1<ClassKey, ClassDescriptor> classes;
    private final DeserializationComponents components;

    /* compiled from: ClassDeserializer.kt */
    /* loaded from: classes4.dex */
    public static final class ClassKey {
        @Nullable
        private final ClassData classData;
        @NotNull
        private final ClassId classId;

        public ClassKey(@NotNull ClassId classId, @Nullable ClassData classData) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            this.classId = classId;
            this.classData = classData;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof ClassKey) && Intrinsics.areEqual(this.classId, ((ClassKey) obj).classId);
        }

        @Nullable
        public final ClassData getClassData() {
            return this.classData;
        }

        @NotNull
        public final ClassId getClassId() {
            return this.classId;
        }

        public int hashCode() {
            return this.classId.hashCode();
        }
    }

    /* compiled from: ClassDeserializer.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Set<ClassId> getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Set<ClassId> of;
        of = SetsKt__SetsJVMKt.setOf(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.cloneable.toSafe()));
        BLACK_LIST = of;
    }

    public ClassDeserializer(@NotNull DeserializationComponents components) {
        Intrinsics.checkParameterIsNotNull(components, "components");
        this.components = components;
        this.classes = this.components.getStorageManager().createMemoizedFunctionWithNullableValues(new ClassDeserializer$classes$1(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00b9 A[EDGE_INSN: B:104:0x00b9->B:96:0x00b9 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor createClass(kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer.ClassKey r13) {
        /*
            r12 = this;
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r13.getClassId()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r1 = r12.components
            java.lang.Iterable r1 = r1.getFictitiousClassDescriptorFactories()
            java.util.Iterator r1 = r1.iterator()
        Le:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L21
            java.lang.Object r2 = r1.next()
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory r2 = (kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory) r2
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = r2.createClass(r0)
            if (r2 == 0) goto Le
            return r2
        L21:
            java.util.Set<kotlin.reflect.jvm.internal.impl.name.ClassId> r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer.BLACK_LIST
            boolean r1 = r1.contains(r0)
            r2 = 0
            if (r1 == 0) goto L2b
            return r2
        L2b:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData r13 = r13.getClassData()
            if (r13 == 0) goto L32
            goto L3c
        L32:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r13 = r12.components
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder r13 = r13.getClassDataFinder()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData r13 = r13.findClassData(r0)
        L3c:
            if (r13 == 0) goto Lf0
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r1 = r13.component1()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r10 = r13.component2()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion r11 = r13.component3()
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r13 = r13.component4()
            kotlin.reflect.jvm.internal.impl.name.ClassId r3 = r0.getOuterClassId()
            java.lang.String r4 = "classId.shortClassName"
            if (r3 == 0) goto L7a
            r5 = 2
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = deserializeClass$default(r12, r3, r2, r5, r2)
            boolean r5 = r3 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
            if (r5 != 0) goto L60
            r3 = r2
        L60:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r3 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor) r3
            if (r3 == 0) goto L79
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getShortClassName()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            boolean r0 = r3.hasNestedClass$deserialization(r0)
            if (r0 != 0) goto L72
            return r2
        L72:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r3.getC()
        L76:
            r4 = r0
            goto Le5
        L79:
            return r2
        L7a:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r3 = r12.components
            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider r3 = r3.getPackageFragmentProvider()
            kotlin.reflect.jvm.internal.impl.name.FqName r5 = r0.getPackageFqName()
            java.lang.String r6 = "classId.packageFqName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.util.List r3 = r3.getPackageFragments(r5)
            java.util.Iterator r3 = r3.iterator()
        L91:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto Lb8
            java.lang.Object r5 = r3.next()
            r6 = r5
            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r6
            boolean r7 = r6 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment
            if (r7 == 0) goto Lb4
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment r6 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment) r6
            kotlin.reflect.jvm.internal.impl.name.Name r7 = r0.getShortClassName()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r4)
            boolean r6 = r6.hasTopLevelClass(r7)
            if (r6 == 0) goto Lb2
            goto Lb4
        Lb2:
            r6 = 0
            goto Lb5
        Lb4:
            r6 = 1
        Lb5:
            if (r6 == 0) goto L91
            goto Lb9
        Lb8:
            r5 = r2
        Lb9:
            r4 = r5
            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r4
            if (r4 == 0) goto Lf0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r3 = r12.components
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r6 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r0 = r10.getTypeTable()
            java.lang.String r2 = "classProto.typeTable"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r6.<init>(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable$Companion r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r2 = r10.getVersionRequirementTable()
            java.lang.String r5 = "classProto.versionRequirementTable"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r5)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r7 = r0.create(r2)
            r9 = 0
            r5 = r1
            r8 = r11
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r3.createContext(r4, r5, r6, r7, r8, r9)
            goto L76
        Le5:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
            r3 = r0
            r5 = r10
            r6 = r1
            r7 = r11
            r8 = r13
            r3.<init>(r4, r5, r6, r7, r8)
            return r0
        Lf0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer.createClass(kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer$ClassKey):kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor");
    }

    public static /* synthetic */ ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer, ClassId classId, ClassData classData, int i, Object obj) {
        if ((i & 2) != 0) {
            classData = null;
        }
        return classDeserializer.deserializeClass(classId, classData);
    }

    @Nullable
    public final ClassDescriptor deserializeClass(@NotNull ClassId classId, @Nullable ClassData classData) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return this.classes.mo12165invoke(new ClassKey(classId, classData));
    }
}
