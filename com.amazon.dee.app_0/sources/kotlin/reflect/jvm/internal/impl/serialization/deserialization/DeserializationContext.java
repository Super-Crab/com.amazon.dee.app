package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: context.kt */
/* loaded from: classes4.dex */
public final class DeserializationContext {
    @NotNull
    private final DeserializationComponents components;
    @Nullable
    private final DeserializedContainerSource containerSource;
    @NotNull
    private final DeclarationDescriptor containingDeclaration;
    @NotNull
    private final MemberDeserializer memberDeserializer;
    @NotNull
    private final BinaryVersion metadataVersion;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final TypeDeserializer typeDeserializer;
    @NotNull
    private final TypeTable typeTable;
    @NotNull
    private final VersionRequirementTable versionRequirementTable;

    public DeserializationContext(@NotNull DeserializationComponents components, @NotNull NameResolver nameResolver, @NotNull DeclarationDescriptor containingDeclaration, @NotNull TypeTable typeTable, @NotNull VersionRequirementTable versionRequirementTable, @NotNull BinaryVersion metadataVersion, @Nullable DeserializedContainerSource deserializedContainerSource, @Nullable TypeDeserializer typeDeserializer, @NotNull List<ProtoBuf.TypeParameter> typeParameters) {
        String str;
        Intrinsics.checkParameterIsNotNull(components, "components");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(typeParameters, "typeParameters");
        this.components = components;
        this.nameResolver = nameResolver;
        this.containingDeclaration = containingDeclaration;
        this.typeTable = typeTable;
        this.versionRequirementTable = versionRequirementTable;
        this.metadataVersion = metadataVersion;
        this.containerSource = deserializedContainerSource;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Deserializer for \"");
        outline107.append(this.containingDeclaration.getName());
        outline107.append('\"');
        String sb = outline107.toString();
        DeserializedContainerSource deserializedContainerSource2 = this.containerSource;
        this.typeDeserializer = new TypeDeserializer(this, typeDeserializer, typeParameters, sb, (deserializedContainerSource2 == null || (str = deserializedContainerSource2.getPresentableString()) == null) ? "[container not found]" : str, false, 32, null);
        this.memberDeserializer = new MemberDeserializer(this);
    }

    public static /* synthetic */ DeserializationContext childContext$default(DeserializationContext deserializationContext, DeclarationDescriptor declarationDescriptor, List list, NameResolver nameResolver, TypeTable typeTable, VersionRequirementTable versionRequirementTable, BinaryVersion binaryVersion, int i, Object obj) {
        if ((i & 4) != 0) {
            nameResolver = deserializationContext.nameResolver;
        }
        NameResolver nameResolver2 = nameResolver;
        if ((i & 8) != 0) {
            typeTable = deserializationContext.typeTable;
        }
        TypeTable typeTable2 = typeTable;
        if ((i & 16) != 0) {
            versionRequirementTable = deserializationContext.versionRequirementTable;
        }
        VersionRequirementTable versionRequirementTable2 = versionRequirementTable;
        if ((i & 32) != 0) {
            binaryVersion = deserializationContext.metadataVersion;
        }
        return deserializationContext.childContext(declarationDescriptor, list, nameResolver2, typeTable2, versionRequirementTable2, binaryVersion);
    }

    @NotNull
    public final DeserializationContext childContext(@NotNull DeclarationDescriptor descriptor, @NotNull List<ProtoBuf.TypeParameter> typeParameterProtos, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull VersionRequirementTable versionRequirementTable, @NotNull BinaryVersion metadataVersion) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        VersionRequirementTable versionRequirementTable2 = versionRequirementTable;
        Intrinsics.checkParameterIsNotNull(versionRequirementTable2, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        DeserializationComponents deserializationComponents = this.components;
        if (!VersionSpecificBehaviorKt.isVersionRequirementTableWrittenCorrectly(metadataVersion)) {
            versionRequirementTable2 = this.versionRequirementTable;
        }
        return new DeserializationContext(deserializationComponents, nameResolver, descriptor, typeTable, versionRequirementTable2, metadataVersion, this.containerSource, this.typeDeserializer, typeParameterProtos);
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        return this.components;
    }

    @Nullable
    public final DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @NotNull
    public final DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @NotNull
    public final MemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    @NotNull
    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    @NotNull
    public final TypeDeserializer getTypeDeserializer() {
        return this.typeDeserializer;
    }

    @NotNull
    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    @NotNull
    public final VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }
}
