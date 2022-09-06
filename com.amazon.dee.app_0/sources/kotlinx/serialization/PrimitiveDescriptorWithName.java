package kotlinx.serialization;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Migrations.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of PrimitiveDescriptor factory function")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u001d\u001a\u00020\fH\u0096\u0001J\u0011\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\fH\u0096\u0001J\u0011\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0096\u0001J\u0011\u0010 \u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\fH\u0096\u0001J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0097\u0001J\u0011\u0010\"\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\fH\u0096\u0001R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u0003X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017¨\u0006#"}, d2 = {"Lkotlinx/serialization/PrimitiveDescriptorWithName;", "Lkotlinx/serialization/SerialDescriptor;", "name", "", "original", "(Ljava/lang/String;Lkotlinx/serialization/SerialDescriptor;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "elementsCount", "", "getElementsCount", "()I", "isNullable", "", "()Z", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "getName", "()Ljava/lang/String;", "getOriginal", "()Lkotlinx/serialization/SerialDescriptor;", "serialName", "getSerialName", "getElementAnnotations", "index", "getElementDescriptor", "getElementIndex", "getElementName", "getEntityAnnotations", "isElementOptional", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PrimitiveDescriptorWithName implements SerialDescriptor {
    @NotNull
    private final String name;
    @NotNull
    private final SerialDescriptor original;

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of PrimitiveDescriptor factory function", replaceWith = @ReplaceWith(expression = "PrimitiveDescriptor(name, original.kind)", imports = {}))
    public PrimitiveDescriptorWithName(@NotNull String name, @NotNull SerialDescriptor original) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(original, "original");
        this.name = name;
        this.original = original;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return this.original.getAnnotations();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        return this.original.getElementAnnotations(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        return this.original.getElementDescriptor(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.original.getElementIndex(name);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return this.original.getElementName(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementsCount() {
        return this.original.getElementsCount();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
    @NotNull
    public List<Annotation> getEntityAnnotations() {
        return this.original.getEntityAnnotations();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    /* renamed from: getKind */
    public SerialKind mo12397getKind() {
        return this.original.mo12397getKind();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public final SerialDescriptor getOriginal() {
        return this.original;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.original.getSerialName();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.original.isElementOptional(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return this.original.isNullable();
    }
}
