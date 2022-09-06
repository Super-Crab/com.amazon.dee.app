package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: protoTypeTableUtil.kt */
/* loaded from: classes4.dex */
public final class ProtoTypeTableUtilKt {
    @Nullable
    public static final ProtoBuf.Type abbreviatedType(@NotNull ProtoBuf.Type abbreviatedType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(abbreviatedType, "$this$abbreviatedType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (abbreviatedType.hasAbbreviatedType()) {
            return abbreviatedType.getAbbreviatedType();
        }
        if (!abbreviatedType.hasAbbreviatedTypeId()) {
            return null;
        }
        return typeTable.get(abbreviatedType.getAbbreviatedTypeId());
    }

    @NotNull
    public static final ProtoBuf.Type expandedType(@NotNull ProtoBuf.TypeAlias expandedType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(expandedType, "$this$expandedType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (expandedType.hasExpandedType()) {
            ProtoBuf.Type expandedType2 = expandedType.getExpandedType();
            Intrinsics.checkExpressionValueIsNotNull(expandedType2, "expandedType");
            return expandedType2;
        } else if (!expandedType.hasExpandedTypeId()) {
            throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
        } else {
            return typeTable.get(expandedType.getExpandedTypeId());
        }
    }

    @Nullable
    public static final ProtoBuf.Type flexibleUpperBound(@NotNull ProtoBuf.Type flexibleUpperBound, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(flexibleUpperBound, "$this$flexibleUpperBound");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (flexibleUpperBound.hasFlexibleUpperBound()) {
            return flexibleUpperBound.getFlexibleUpperBound();
        }
        if (!flexibleUpperBound.hasFlexibleUpperBoundId()) {
            return null;
        }
        return typeTable.get(flexibleUpperBound.getFlexibleUpperBoundId());
    }

    public static final boolean hasReceiver(@NotNull ProtoBuf.Function hasReceiver) {
        Intrinsics.checkParameterIsNotNull(hasReceiver, "$this$hasReceiver");
        return hasReceiver.hasReceiverType() || hasReceiver.hasReceiverTypeId();
    }

    @Nullable
    public static final ProtoBuf.Type outerType(@NotNull ProtoBuf.Type outerType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(outerType, "$this$outerType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (outerType.hasOuterType()) {
            return outerType.getOuterType();
        }
        if (!outerType.hasOuterTypeId()) {
            return null;
        }
        return typeTable.get(outerType.getOuterTypeId());
    }

    @Nullable
    public static final ProtoBuf.Type receiverType(@NotNull ProtoBuf.Function receiverType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiverType, "$this$receiverType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiverType.hasReceiverType()) {
            return receiverType.getReceiverType();
        }
        if (!receiverType.hasReceiverTypeId()) {
            return null;
        }
        return typeTable.get(receiverType.getReceiverTypeId());
    }

    @NotNull
    public static final ProtoBuf.Type returnType(@NotNull ProtoBuf.Function returnType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(returnType, "$this$returnType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (returnType.hasReturnType()) {
            ProtoBuf.Type returnType2 = returnType.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType2, "returnType");
            return returnType2;
        } else if (!returnType.hasReturnTypeId()) {
            throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
        } else {
            return typeTable.get(returnType.getReturnTypeId());
        }
    }

    @NotNull
    public static final List<ProtoBuf.Type> supertypes(@NotNull ProtoBuf.Class supertypes, @NotNull TypeTable typeTable) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(supertypes, "$this$supertypes");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> supertypeList = supertypes.getSupertypeList();
        if (!(!supertypeList.isEmpty())) {
            supertypeList = null;
        }
        if (supertypeList == null) {
            List<Integer> supertypeIdList = supertypes.getSupertypeIdList();
            Intrinsics.checkExpressionValueIsNotNull(supertypeIdList, "supertypeIdList");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(supertypeIdList, 10);
            supertypeList = new ArrayList<>(collectionSizeOrDefault);
            for (Integer it2 : supertypeIdList) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                supertypeList.add(typeTable.get(it2.intValue()));
            }
        }
        return supertypeList;
    }

    @Nullable
    public static final ProtoBuf.Type type(@NotNull ProtoBuf.Type.Argument type, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$this$type");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasType()) {
            return type.getType();
        }
        if (!type.hasTypeId()) {
            return null;
        }
        return typeTable.get(type.getTypeId());
    }

    @NotNull
    public static final ProtoBuf.Type underlyingType(@NotNull ProtoBuf.TypeAlias underlyingType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(underlyingType, "$this$underlyingType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (underlyingType.hasUnderlyingType()) {
            ProtoBuf.Type underlyingType2 = underlyingType.getUnderlyingType();
            Intrinsics.checkExpressionValueIsNotNull(underlyingType2, "underlyingType");
            return underlyingType2;
        } else if (!underlyingType.hasUnderlyingTypeId()) {
            throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
        } else {
            return typeTable.get(underlyingType.getUnderlyingTypeId());
        }
    }

    @NotNull
    public static final List<ProtoBuf.Type> upperBounds(@NotNull ProtoBuf.TypeParameter upperBounds, @NotNull TypeTable typeTable) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(upperBounds, "$this$upperBounds");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> upperBoundList = upperBounds.getUpperBoundList();
        if (!(!upperBoundList.isEmpty())) {
            upperBoundList = null;
        }
        if (upperBoundList == null) {
            List<Integer> upperBoundIdList = upperBounds.getUpperBoundIdList();
            Intrinsics.checkExpressionValueIsNotNull(upperBoundIdList, "upperBoundIdList");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(upperBoundIdList, 10);
            upperBoundList = new ArrayList<>(collectionSizeOrDefault);
            for (Integer it2 : upperBoundIdList) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                upperBoundList.add(typeTable.get(it2.intValue()));
            }
        }
        return upperBoundList;
    }

    @Nullable
    public static final ProtoBuf.Type varargElementType(@NotNull ProtoBuf.ValueParameter varargElementType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(varargElementType, "$this$varargElementType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (varargElementType.hasVarargElementType()) {
            return varargElementType.getVarargElementType();
        }
        if (!varargElementType.hasVarargElementTypeId()) {
            return null;
        }
        return typeTable.get(varargElementType.getVarargElementTypeId());
    }

    public static final boolean hasReceiver(@NotNull ProtoBuf.Property hasReceiver) {
        Intrinsics.checkParameterIsNotNull(hasReceiver, "$this$hasReceiver");
        return hasReceiver.hasReceiverType() || hasReceiver.hasReceiverTypeId();
    }

    @Nullable
    public static final ProtoBuf.Type receiverType(@NotNull ProtoBuf.Property receiverType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiverType, "$this$receiverType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiverType.hasReceiverType()) {
            return receiverType.getReceiverType();
        }
        if (!receiverType.hasReceiverTypeId()) {
            return null;
        }
        return typeTable.get(receiverType.getReceiverTypeId());
    }

    @NotNull
    public static final ProtoBuf.Type type(@NotNull ProtoBuf.ValueParameter type, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$this$type");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasType()) {
            ProtoBuf.Type type2 = type.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "type");
            return type2;
        } else if (!type.hasTypeId()) {
            throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
        } else {
            return typeTable.get(type.getTypeId());
        }
    }

    @NotNull
    public static final ProtoBuf.Type returnType(@NotNull ProtoBuf.Property returnType, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(returnType, "$this$returnType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (returnType.hasReturnType()) {
            ProtoBuf.Type returnType2 = returnType.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType2, "returnType");
            return returnType2;
        } else if (!returnType.hasReturnTypeId()) {
            throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
        } else {
            return typeTable.get(returnType.getReturnTypeId());
        }
    }
}
