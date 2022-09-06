package kotlinx.serialization.internal;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: Primitives.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/internal/PrimitiveSerialDescriptor;", "Lkotlinx/serialization/SerialDescriptor;", "serialName", "", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/PrimitiveKind;", "(Ljava/lang/String;Lkotlinx/serialization/PrimitiveKind;)V", "elementsCount", "", "getElementsCount", "()I", "getKind", "()Lkotlinx/serialization/PrimitiveKind;", "getSerialName", "()Ljava/lang/String;", "error", "", "getElementAnnotations", "", "", "index", "getElementDescriptor", "getElementIndex", "name", "getElementName", "isElementOptional", "", "toString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PrimitiveSerialDescriptor implements SerialDescriptor {
    @NotNull
    private final PrimitiveKind kind;
    @NotNull
    private final String serialName;

    public PrimitiveSerialDescriptor(@NotNull String serialName, @NotNull PrimitiveKind kind) {
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        this.serialName = serialName;
        this.kind = kind;
    }

    private final Void error() {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return SerialDescriptor.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        error();
        throw null;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementsCount() {
        return 0;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
    @NotNull
    public List<Annotation> getEntityAnnotations() {
        return SerialDescriptor.DefaultImpls.getEntityAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getName() {
        return SerialDescriptor.DefaultImpls.getName(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return SerialDescriptor.DefaultImpls.isNullable(this);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PrimitiveDescriptor(");
        outline107.append(getSerialName());
        outline107.append(')');
        return outline107.toString();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    /* renamed from: getKind */
    public PrimitiveKind mo12397getKind() {
        return this.kind;
    }
}
