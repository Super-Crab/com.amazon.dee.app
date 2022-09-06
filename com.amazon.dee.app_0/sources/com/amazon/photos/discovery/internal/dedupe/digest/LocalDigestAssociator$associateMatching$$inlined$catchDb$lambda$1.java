package com.amazon.photos.discovery.internal.dedupe.digest;

import com.amazon.photos.discovery.dao.DedupeDao;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalDigestAssociator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "dao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "invoke", "com/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator$associateMatching$2$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalDigestAssociator$associateMatching$$inlined$catchDb$lambda$1 extends Lambda implements Function1<DedupeDao, Unit> {
    final /* synthetic */ HashSet $combinedIds$inlined;
    final /* synthetic */ DedupeDao $dedupeDao$inlined;
    final /* synthetic */ Map $matchings$inlined;
    final /* synthetic */ Set $unifiedIdsRemovedByCombination$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalDigestAssociator$associateMatching$$inlined$catchDb$lambda$1(DedupeDao dedupeDao, Map map, HashSet hashSet, Set set) {
        super(1);
        this.$dedupeDao$inlined = dedupeDao;
        this.$matchings$inlined = map;
        this.$combinedIds$inlined = hashSet;
        this.$unifiedIdsRemovedByCombination$inlined = set;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(DedupeDao dedupeDao) {
        invoke2(dedupeDao);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull DedupeDao dao) {
        Set minus;
        Intrinsics.checkParameterIsNotNull(dao, "dao");
        for (Set set : this.$matchings$inlined.values()) {
            this.$combinedIds$inlined.addAll(set);
            long combineByUnifiedIds = dao.combineByUnifiedIds(set);
            Set set2 = this.$unifiedIdsRemovedByCombination$inlined;
            minus = SetsKt___SetsKt.minus(set, Long.valueOf(combineByUnifiedIds));
            set2.addAll(minus);
        }
    }
}
