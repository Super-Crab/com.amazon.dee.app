package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DescriptorUtils.kt */
/* loaded from: classes4.dex */
public final class DescriptorUtilsKt$declaresOrInheritsDefaultValue$1<N> implements DFS.Neighbors<N> {
    public static final DescriptorUtilsKt$declaresOrInheritsDefaultValue$1 INSTANCE = new DescriptorUtilsKt$declaresOrInheritsDefaultValue$1();

    DescriptorUtilsKt$declaresOrInheritsDefaultValue$1() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
    @NotNull
    public final List<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor current) {
        int collectionSizeOrDefault;
        Intrinsics.checkExpressionValueIsNotNull(current, "current");
        Collection<ValueParameterDescriptor> overriddenDescriptors = current.getOverriddenDescriptors();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (ValueParameterDescriptor valueParameterDescriptor : overriddenDescriptors) {
            arrayList.add(valueParameterDescriptor.mo11613getOriginal());
        }
        return arrayList;
    }
}
