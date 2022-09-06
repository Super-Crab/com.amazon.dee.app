package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
/* compiled from: overridingUtils.kt */
/* loaded from: classes4.dex */
public final class OverridingUtilsKt {
    public static final <D extends CallableDescriptor> void retainMostSpecificInEachOverridableGroup(@NotNull Collection<D> retainMostSpecificInEachOverridableGroup) {
        Intrinsics.checkParameterIsNotNull(retainMostSpecificInEachOverridableGroup, "$this$retainMostSpecificInEachOverridableGroup");
        Collection<?> selectMostSpecificInEachOverridableGroup = selectMostSpecificInEachOverridableGroup(retainMostSpecificInEachOverridableGroup, OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1.INSTANCE);
        if (retainMostSpecificInEachOverridableGroup.size() == selectMostSpecificInEachOverridableGroup.size()) {
            return;
        }
        retainMostSpecificInEachOverridableGroup.retainAll(selectMostSpecificInEachOverridableGroup);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(@NotNull Collection<? extends H> selectMostSpecificInEachOverridableGroup, @NotNull Function1<? super H, ? extends CallableDescriptor> descriptorByHandle) {
        Intrinsics.checkParameterIsNotNull(selectMostSpecificInEachOverridableGroup, "$this$selectMostSpecificInEachOverridableGroup");
        Intrinsics.checkParameterIsNotNull(descriptorByHandle, "descriptorByHandle");
        if (selectMostSpecificInEachOverridableGroup.size() <= 1) {
            return selectMostSpecificInEachOverridableGroup;
        }
        LinkedList linkedList = new LinkedList(selectMostSpecificInEachOverridableGroup);
        SmartSet create = SmartSet.Companion.create();
        while (!linkedList.isEmpty()) {
            Object first = CollectionsKt.first((List<? extends Object>) linkedList);
            SmartSet create2 = SmartSet.Companion.create();
            Collection<? super H> extractMembersOverridableInBothWays = OverridingUtil.extractMembersOverridableInBothWays(first, linkedList, descriptorByHandle, new OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1(create2));
            Intrinsics.checkExpressionValueIsNotNull(extractMembersOverridableInBothWays, "OverridingUtil.extractMe…nflictedHandles.add(it) }");
            if (extractMembersOverridableInBothWays.size() == 1 && create2.isEmpty()) {
                Object single = CollectionsKt.single(extractMembersOverridableInBothWays);
                Intrinsics.checkExpressionValueIsNotNull(single, "overridableGroup.single()");
                create.add(single);
            } else {
                Object obj = (Object) OverridingUtil.selectMostSpecificMember(extractMembersOverridableInBothWays, descriptorByHandle);
                Intrinsics.checkExpressionValueIsNotNull(obj, "OverridingUtil.selectMos…roup, descriptorByHandle)");
                CallableDescriptor mo12165invoke = descriptorByHandle.mo12165invoke(obj);
                for (Object it2 : extractMembersOverridableInBothWays) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    if (!OverridingUtil.isMoreSpecific(mo12165invoke, descriptorByHandle.mo12165invoke(it2))) {
                        create2.add(it2);
                    }
                }
                if (!create2.isEmpty()) {
                    create.addAll(create2);
                }
                create.add(obj);
            }
        }
        return create;
    }
}
