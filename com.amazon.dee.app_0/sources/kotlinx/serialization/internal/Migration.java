package kotlinx.serialization.internal;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: Primitives.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lkotlinx/serialization/internal/Migration;", "Lkotlinx/serialization/SerialDescriptor;", "()V", "elementsCount", "", "getElementsCount", "()I", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "serialName", "", "getSerialName", "()Ljava/lang/String;", "getElementAnnotations", "", "", "index", "getElementDescriptor", "getElementIndex", "name", "getElementName", "isElementOptional", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class Migration implements SerialDescriptor {
    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return SerialDescriptor.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementsCount() {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
    @NotNull
    public List<Annotation> getEntityAnnotations() {
        return SerialDescriptor.DefaultImpls.getEntityAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    /* renamed from: getKind */
    public SerialKind mo12397getKind() {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getName() {
        return SerialDescriptor.DefaultImpls.getName(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        throw new IllegalStateException("Class used only for source-level migration".toString());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return SerialDescriptor.DefaultImpls.isNullable(this);
    }
}
