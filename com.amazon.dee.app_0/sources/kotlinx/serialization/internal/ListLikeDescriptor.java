package kotlinx.serialization.internal;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CollectionDescriptors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\b\u0010\u001b\u001a\u00020\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0007H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001\u0006\u001d\u001e\u001f !\"¨\u0006#"}, d2 = {"Lkotlinx/serialization/internal/ListLikeDescriptor;", "Lkotlinx/serialization/SerialDescriptor;", "elementDesc", "(Lkotlinx/serialization/SerialDescriptor;)V", "getElementDesc", "()Lkotlinx/serialization/SerialDescriptor;", "elementsCount", "", "getElementsCount", "()I", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "equals", "", "other", "", "getElementAnnotations", "", "", "index", "getElementDescriptor", "getElementIndex", "name", "", "getElementName", "hashCode", "isElementOptional", "Lkotlinx/serialization/internal/PrimitiveArrayDescriptor;", "Lkotlinx/serialization/internal/ArrayClassDesc;", "Lkotlinx/serialization/internal/ArrayListClassDesc;", "Lkotlinx/serialization/internal/NamedListClassDescriptor;", "Lkotlinx/serialization/internal/LinkedHashSetClassDesc;", "Lkotlinx/serialization/internal/HashSetClassDesc;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class ListLikeDescriptor implements SerialDescriptor {
    @NotNull
    private final SerialDescriptor elementDesc;
    private final int elementsCount;

    private ListLikeDescriptor(SerialDescriptor serialDescriptor) {
        this.elementDesc = serialDescriptor;
        this.elementsCount = 1;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListLikeDescriptor)) {
            return false;
        }
        ListLikeDescriptor listLikeDescriptor = (ListLikeDescriptor) obj;
        return Intrinsics.areEqual(this.elementDesc, listLikeDescriptor.elementDesc) && Intrinsics.areEqual(getSerialName(), listLikeDescriptor.getSerialName());
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return SerialDescriptor.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        List<Annotation> emptyList;
        if (i == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("List descriptor has only one child element, index: ", i));
    }

    @NotNull
    public final SerialDescriptor getElementDesc() {
        return this.elementDesc;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        if (i == 0) {
            return this.elementDesc;
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("List descriptor has only one child element, index: ", i));
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Integer intOrNull;
        Intrinsics.checkParameterIsNotNull(name, "name");
        intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(name);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(name, " is not a valid list index"));
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return String.valueOf(i);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementsCount() {
        return this.elementsCount;
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
        return StructureKind.LIST.INSTANCE;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getName() {
        return SerialDescriptor.DefaultImpls.getName(this);
    }

    public int hashCode() {
        return getSerialName().hashCode() + (this.elementDesc.hashCode() * 31);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        if (i == 0) {
            return false;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("List descriptor has only one child element, index: ", i));
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return SerialDescriptor.DefaultImpls.isNullable(this);
    }

    public /* synthetic */ ListLikeDescriptor(SerialDescriptor serialDescriptor, DefaultConstructorMarker defaultConstructorMarker) {
        this(serialDescriptor);
    }
}
