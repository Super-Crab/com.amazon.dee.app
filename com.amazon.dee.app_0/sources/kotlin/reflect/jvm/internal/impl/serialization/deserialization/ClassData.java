package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClassData.kt */
/* loaded from: classes4.dex */
public final class ClassData {
    @NotNull
    private final ProtoBuf.Class classProto;
    @NotNull
    private final BinaryVersion metadataVersion;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final SourceElement sourceElement;

    public ClassData(@NotNull NameResolver nameResolver, @NotNull ProtoBuf.Class classProto, @NotNull BinaryVersion metadataVersion, @NotNull SourceElement sourceElement) {
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(classProto, "classProto");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(sourceElement, "sourceElement");
        this.nameResolver = nameResolver;
        this.classProto = classProto;
        this.metadataVersion = metadataVersion;
        this.sourceElement = sourceElement;
    }

    @NotNull
    public final NameResolver component1() {
        return this.nameResolver;
    }

    @NotNull
    public final ProtoBuf.Class component2() {
        return this.classProto;
    }

    @NotNull
    public final BinaryVersion component3() {
        return this.metadataVersion;
    }

    @NotNull
    public final SourceElement component4() {
        return this.sourceElement;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ClassData)) {
                return false;
            }
            ClassData classData = (ClassData) obj;
            return Intrinsics.areEqual(this.nameResolver, classData.nameResolver) && Intrinsics.areEqual(this.classProto, classData.classProto) && Intrinsics.areEqual(this.metadataVersion, classData.metadataVersion) && Intrinsics.areEqual(this.sourceElement, classData.sourceElement);
        }
        return true;
    }

    public int hashCode() {
        NameResolver nameResolver = this.nameResolver;
        int i = 0;
        int hashCode = (nameResolver != null ? nameResolver.hashCode() : 0) * 31;
        ProtoBuf.Class r2 = this.classProto;
        int hashCode2 = (hashCode + (r2 != null ? r2.hashCode() : 0)) * 31;
        BinaryVersion binaryVersion = this.metadataVersion;
        int hashCode3 = (hashCode2 + (binaryVersion != null ? binaryVersion.hashCode() : 0)) * 31;
        SourceElement sourceElement = this.sourceElement;
        if (sourceElement != null) {
            i = sourceElement.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassData(nameResolver=");
        outline107.append(this.nameResolver);
        outline107.append(", classProto=");
        outline107.append(this.classProto);
        outline107.append(", metadataVersion=");
        outline107.append(this.metadataVersion);
        outline107.append(", sourceElement=");
        outline107.append(this.sourceElement);
        outline107.append(")");
        return outline107.toString();
    }
}
