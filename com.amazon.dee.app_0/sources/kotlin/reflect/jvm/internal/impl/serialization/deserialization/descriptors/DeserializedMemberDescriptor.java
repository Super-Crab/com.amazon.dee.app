package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializedMemberDescriptor.kt */
/* loaded from: classes4.dex */
public interface DeserializedMemberDescriptor extends MemberDescriptor, DescriptorWithContainerSource {

    /* compiled from: DeserializedMemberDescriptor.kt */
    /* loaded from: classes4.dex */
    public enum CoroutinesCompatibilityMode {
        COMPATIBLE,
        NEEDS_WRAPPER,
        INCOMPATIBLE
    }

    /* compiled from: DeserializedMemberDescriptor.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @NotNull
        public static List<VersionRequirement> getVersionRequirements(DeserializedMemberDescriptor deserializedMemberDescriptor) {
            return VersionRequirement.Companion.create(deserializedMemberDescriptor.mo12068getProto(), deserializedMemberDescriptor.getNameResolver(), deserializedMemberDescriptor.getVersionRequirementTable());
        }
    }

    @NotNull
    NameResolver getNameResolver();

    @NotNull
    /* renamed from: getProto */
    MessageLite mo12068getProto();

    @NotNull
    TypeTable getTypeTable();

    @NotNull
    VersionRequirementTable getVersionRequirementTable();

    @NotNull
    List<VersionRequirement> getVersionRequirements();
}
