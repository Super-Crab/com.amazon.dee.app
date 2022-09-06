package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialDescriptorBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J3\u0010\u001f\u001a\u00020 \"\u0006\b\u0000\u0010!\u0018\u00012\u0006\u0010\"\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010#\u001a\u00020\u0015H\u0087\bJ0\u0010\u001f\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00102\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010#\u001a\u00020\u0015R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u001a\u0010\u0017\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/serialization/SerialDescriptorBuilder;", "", "serialName", "", "(Ljava/lang/String;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "elementAnnotations", "", "getElementAnnotations$kotlinx_serialization_runtime", "elementDescriptors", "Lkotlinx/serialization/SerialDescriptor;", "getElementDescriptors$kotlinx_serialization_runtime", "elementNames", "getElementNames$kotlinx_serialization_runtime", "elementOptionality", "", "getElementOptionality$kotlinx_serialization_runtime", "isNullable", "()Z", "setNullable", "(Z)V", "getSerialName", "()Ljava/lang/String;", "uniqueNames", "", "element", "", ExifInterface.GPS_DIRECTION_TRUE, "elementName", "isOptional", "descriptor", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialDescriptorBuilder {
    @NotNull
    private List<? extends Annotation> annotations;
    @NotNull
    private final List<List<Annotation>> elementAnnotations;
    @NotNull
    private final List<SerialDescriptor> elementDescriptors;
    @NotNull
    private final List<String> elementNames;
    @NotNull
    private final List<Boolean> elementOptionality;
    private boolean isNullable;
    @NotNull
    private final String serialName;
    private final Set<String> uniqueNames;

    public SerialDescriptorBuilder(@NotNull String serialName) {
        List<? extends Annotation> emptyList;
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        this.serialName = serialName;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.annotations = emptyList;
        this.elementNames = new ArrayList();
        this.uniqueNames = new HashSet();
        this.elementDescriptors = new ArrayList();
        this.elementAnnotations = new ArrayList();
        this.elementOptionality = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void element$default(SerialDescriptorBuilder serialDescriptorBuilder, String str, SerialDescriptor serialDescriptor, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        serialDescriptorBuilder.element(str, serialDescriptor, list, z);
    }

    public final void element(@NotNull String elementName, @NotNull SerialDescriptor descriptor, @NotNull List<? extends Annotation> annotations, boolean z) {
        Intrinsics.checkParameterIsNotNull(elementName, "elementName");
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        if (this.uniqueNames.add(elementName)) {
            this.elementNames.add(elementName);
            this.elementDescriptors.add(descriptor);
            this.elementAnnotations.add(annotations);
            this.elementOptionality.add(Boolean.valueOf(z));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Element with name '", elementName, "' is already registered").toString());
    }

    @NotNull
    public final List<Annotation> getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public final List<List<Annotation>> getElementAnnotations$kotlinx_serialization_runtime() {
        return this.elementAnnotations;
    }

    @NotNull
    public final List<SerialDescriptor> getElementDescriptors$kotlinx_serialization_runtime() {
        return this.elementDescriptors;
    }

    @NotNull
    public final List<String> getElementNames$kotlinx_serialization_runtime() {
        return this.elementNames;
    }

    @NotNull
    public final List<Boolean> getElementOptionality$kotlinx_serialization_runtime() {
        return this.elementOptionality;
    }

    @NotNull
    public final String getSerialName() {
        return this.serialName;
    }

    public final boolean isNullable() {
        return this.isNullable;
    }

    public final void setAnnotations(@NotNull List<? extends Annotation> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.annotations = list;
    }

    public final void setNullable(boolean z) {
        this.isNullable = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void element$default(SerialDescriptorBuilder serialDescriptorBuilder, String elementName, List annotations, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = CollectionsKt__CollectionsKt.emptyList();
        }
        if ((i & 4) != 0) {
            z = false;
        }
        Intrinsics.checkParameterIsNotNull(elementName, "elementName");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            serialDescriptorBuilder.element(elementName, serializer.getDescriptor(), annotations, z);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @ImplicitReflectionSerializer
    public final /* synthetic */ <T> void element(@NotNull String elementName, @NotNull List<? extends Annotation> annotations, boolean z) {
        Intrinsics.checkParameterIsNotNull(elementName, "elementName");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            element(elementName, serializer.getDescriptor(), annotations, z);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }
}
