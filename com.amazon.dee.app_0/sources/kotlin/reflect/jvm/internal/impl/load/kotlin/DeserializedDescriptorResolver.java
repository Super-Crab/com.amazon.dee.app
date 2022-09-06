package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Set;
import kotlin.Pair;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeserializedDescriptorResolver.kt */
/* loaded from: classes3.dex */
public final class DeserializedDescriptorResolver {
    public static final Companion Companion = new Companion(null);
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION;
    private static final JvmMetadataVersion KOTLIN_1_3_M1_METADATA_VERSION;
    @NotNull
    private static final JvmMetadataVersion KOTLIN_1_3_RC_METADATA_VERSION;
    @NotNull
    private static final Set<KotlinClassHeader.Kind> KOTLIN_CLASS;
    private static final Set<KotlinClassHeader.Kind> KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART;
    @NotNull
    public DeserializationComponents components;

    /* compiled from: DeserializedDescriptorResolver.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final JvmMetadataVersion getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm() {
            return DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Set<KotlinClassHeader.Kind> of;
        Set<KotlinClassHeader.Kind> of2;
        of = SetsKt__SetsJVMKt.setOf(KotlinClassHeader.Kind.CLASS);
        KOTLIN_CLASS = of;
        of2 = SetsKt__SetsKt.setOf((Object[]) new KotlinClassHeader.Kind[]{KotlinClassHeader.Kind.FILE_FACADE, KotlinClassHeader.Kind.MULTIFILE_CLASS_PART});
        KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = of2;
        KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(1, 1, 2);
        KOTLIN_1_3_M1_METADATA_VERSION = new JvmMetadataVersion(1, 1, 11);
        KOTLIN_1_3_RC_METADATA_VERSION = new JvmMetadataVersion(1, 1, 13);
    }

    private final IncompatibleVersionErrorData<JvmMetadataVersion> getIncompatibility(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
            return null;
        }
        return new IncompatibleVersionErrorData<>(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), JvmMetadataVersion.INSTANCE, kotlinJvmBinaryClass.getLocation(), kotlinJvmBinaryClass.getClassId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSkipMetadataVersionCheck() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getConfiguration().getSkipMetadataVersionCheck();
    }

    private final boolean isCompiledWith13M1(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return !deserializationComponents.getConfiguration().getSkipMetadataVersionCheck() && kotlinJvmBinaryClass.getClassHeader().isPreRelease() && Intrinsics.areEqual(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), KOTLIN_1_3_M1_METADATA_VERSION);
    }

    private final boolean isPreReleaseInvisible(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return (deserializationComponents.getConfiguration().getReportErrorsOnPreReleaseDependencies() && (kotlinJvmBinaryClass.getClassHeader().isPreRelease() || Intrinsics.areEqual(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), KOTLIN_1_1_EAP_METADATA_VERSION))) || isCompiledWith13M1(kotlinJvmBinaryClass);
    }

    private final String[] readData(KotlinJvmBinaryClass kotlinJvmBinaryClass, Set<? extends KotlinClassHeader.Kind> set) {
        KotlinClassHeader classHeader = kotlinJvmBinaryClass.getClassHeader();
        String[] data = classHeader.getData();
        if (data == null) {
            data = classHeader.getIncompatibleData();
        }
        if (data == null || !set.contains(classHeader.getKind())) {
            return null;
        }
        return data;
    }

    @Nullable
    public final MemberScope createKotlinPackagePartScope(@NotNull PackageFragmentDescriptor descriptor, @NotNull KotlinJvmBinaryClass kotlinClass) {
        Pair<JvmNameResolver, ProtoBuf.Package> pair;
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        String[] readData = readData(kotlinClass, KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if (readData != null) {
            String[] strings = kotlinClass.getClassHeader().getStrings();
            try {
            } catch (Throwable th) {
                if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                    throw th;
                }
                pair = null;
            }
            if (strings != null) {
                try {
                    pair = JvmProtoBufUtil.readPackageDataFrom(readData, strings);
                    if (pair == null) {
                        return null;
                    }
                    JvmNameResolver component1 = pair.component1();
                    ProtoBuf.Package component2 = pair.component2();
                    JvmPackagePartSource jvmPackagePartSource = new JvmPackagePartSource(kotlinClass, component2, component1, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass));
                    JvmMetadataVersion metadataVersion = kotlinClass.getClassHeader().getMetadataVersion();
                    DeserializationComponents deserializationComponents = this.components;
                    if (deserializationComponents == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("components");
                    }
                    return new DeserializedPackageMemberScope(descriptor, component2, component1, metadataVersion, jvmPackagePartSource, deserializationComponents, DeserializedDescriptorResolver$createKotlinPackagePartScope$2.INSTANCE);
                } catch (InvalidProtocolBufferException e) {
                    throw new IllegalStateException("Could not read data from " + kotlinClass.getLocation(), e);
                }
            }
        }
        return null;
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents;
    }

    @Nullable
    public final ClassData readClassData$descriptors_jvm(@NotNull KotlinJvmBinaryClass kotlinClass) {
        String[] strings;
        Pair<JvmNameResolver, ProtoBuf.Class> pair;
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        String[] readData = readData(kotlinClass, KOTLIN_CLASS);
        if (readData == null || (strings = kotlinClass.getClassHeader().getStrings()) == null) {
            return null;
        }
        try {
            try {
                pair = JvmProtoBufUtil.readClassDataFrom(readData, strings);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalStateException("Could not read data from " + kotlinClass.getLocation(), e);
            }
        } catch (Throwable th) {
            if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw th;
            }
            pair = null;
        }
        if (pair == null) {
            return null;
        }
        return new ClassData(pair.component1(), pair.component2(), kotlinClass.getClassHeader().getMetadataVersion(), new KotlinJvmBinarySourceElement(kotlinClass, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass)));
    }

    @Nullable
    public final ClassDescriptor resolveClass(@NotNull KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        ClassData readClassData$descriptors_jvm = readClassData$descriptors_jvm(kotlinClass);
        if (readClassData$descriptors_jvm != null) {
            DeserializationComponents deserializationComponents = this.components;
            if (deserializationComponents == null) {
                Intrinsics.throwUninitializedPropertyAccessException("components");
            }
            return deserializationComponents.getClassDeserializer().deserializeClass(kotlinClass.getClassId(), readClassData$descriptors_jvm);
        }
        return null;
    }

    public final void setComponents(@NotNull DeserializationComponentsForJava components) {
        Intrinsics.checkParameterIsNotNull(components, "components");
        this.components = components.getComponents();
    }
}
