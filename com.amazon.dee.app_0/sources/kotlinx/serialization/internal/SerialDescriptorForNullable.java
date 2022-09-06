package kotlinx.serialization.internal;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.deecomms.common.Constants;
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
import org.jetbrains.annotations.Nullable;
/* compiled from: NullableSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0013\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001e\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\nH\u0096\u0001J\u0011\u0010 \u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0001J\u0011\u0010!\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\nH\u0096\u0001J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0097\u0001J\b\u0010#\u001a\u00020\nH\u0016J\u0011\u0010$\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\nH\u0096\u0001J\b\u0010%\u001a\u00020\u0015H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017¨\u0006&"}, d2 = {"Lkotlinx/serialization/internal/SerialDescriptorForNullable;", "Lkotlinx/serialization/SerialDescriptor;", "original", "(Lkotlinx/serialization/SerialDescriptor;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "elementsCount", "", "getElementsCount", "()I", "isNullable", "", "()Z", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "name", "", "getName", "()Ljava/lang/String;", "serialName", "getSerialName", "equals", "other", "", "getElementAnnotations", "index", "getElementDescriptor", "getElementIndex", "getElementName", "getEntityAnnotations", "hashCode", "isElementOptional", "toString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialDescriptorForNullable implements SerialDescriptor {
    private final SerialDescriptor original;
    @NotNull
    private final String serialName;

    public SerialDescriptorForNullable(@NotNull SerialDescriptor original) {
        Intrinsics.checkParameterIsNotNull(original, "original");
        this.original = original;
        this.serialName = this.original.getSerialName() + WebConstants.UriConstants.QUESTIONMARK_KEY;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SerialDescriptorForNullable) && !(Intrinsics.areEqual(this.original, ((SerialDescriptorForNullable) obj).original) ^ true);
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
        return this.original.getName();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    public int hashCode() {
        return this.original.hashCode() * 31;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.original.isElementOptional(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return true;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.original);
        sb.append(Constants.DEFAULT_IMAGE_CHAR);
        return sb.toString();
    }
}
