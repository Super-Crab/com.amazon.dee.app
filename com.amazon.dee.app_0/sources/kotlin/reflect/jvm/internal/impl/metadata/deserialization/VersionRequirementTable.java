package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: VersionRequirement.kt */
/* loaded from: classes4.dex */
public final class VersionRequirementTable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final VersionRequirementTable EMPTY;
    private final List<ProtoBuf.VersionRequirement> infos;

    /* compiled from: VersionRequirement.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final VersionRequirementTable create(@NotNull ProtoBuf.VersionRequirementTable table) {
            Intrinsics.checkParameterIsNotNull(table, "table");
            if (table.getRequirementCount() == 0) {
                return getEMPTY();
            }
            List<ProtoBuf.VersionRequirement> requirementList = table.getRequirementList();
            Intrinsics.checkExpressionValueIsNotNull(requirementList, "table.requirementList");
            return new VersionRequirementTable(requirementList, null);
        }

        @NotNull
        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        EMPTY = new VersionRequirementTable(emptyList);
    }

    private VersionRequirementTable(List<ProtoBuf.VersionRequirement> list) {
        this.infos = list;
    }

    @Nullable
    public final ProtoBuf.VersionRequirement get(int i) {
        return (ProtoBuf.VersionRequirement) CollectionsKt.getOrNull(this.infos, i);
    }

    public /* synthetic */ VersionRequirementTable(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }
}
