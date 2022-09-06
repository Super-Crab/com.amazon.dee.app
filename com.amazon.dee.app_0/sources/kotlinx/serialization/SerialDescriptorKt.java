package kotlinx.serialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialDescriptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"elementDescriptors", "", "Lkotlinx/serialization/SerialDescriptor;", "elementNames", "", "getElementIndexOrThrow", "", "name", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialDescriptorKt {
    @NotNull
    public static final List<SerialDescriptor> elementDescriptors(@NotNull SerialDescriptor elementDescriptors) {
        Intrinsics.checkParameterIsNotNull(elementDescriptors, "$this$elementDescriptors");
        int elementsCount = elementDescriptors.getElementsCount();
        ArrayList arrayList = new ArrayList(elementsCount);
        for (int i = 0; i < elementsCount; i++) {
            arrayList.add(elementDescriptors.getElementDescriptor(i));
        }
        return arrayList;
    }

    @NotNull
    public static final List<String> elementNames(@NotNull SerialDescriptor elementNames) {
        Intrinsics.checkParameterIsNotNull(elementNames, "$this$elementNames");
        int elementsCount = elementNames.getElementsCount();
        ArrayList arrayList = new ArrayList(elementsCount);
        for (int i = 0; i < elementsCount; i++) {
            arrayList.add(elementNames.getElementName(i));
        }
        return arrayList;
    }

    public static final int getElementIndexOrThrow(@NotNull SerialDescriptor getElementIndexOrThrow, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(getElementIndexOrThrow, "$this$getElementIndexOrThrow");
        Intrinsics.checkParameterIsNotNull(name, "name");
        int elementIndex = getElementIndexOrThrow.getElementIndex(name);
        if (elementIndex != -3) {
            return elementIndex;
        }
        throw new SerializationException(getElementIndexOrThrow.getSerialName() + " does not contain element with name '" + name + Chars.QUOTE, null, 2, null);
    }
}
