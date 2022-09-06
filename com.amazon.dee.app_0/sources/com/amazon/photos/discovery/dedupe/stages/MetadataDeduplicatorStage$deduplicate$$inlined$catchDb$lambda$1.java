package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMapping;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetadataDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "dao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "invoke", "com/amazon/photos/discovery/dedupe/stages/MetadataDeduplicatorStage$deduplicate$3$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class MetadataDeduplicatorStage$deduplicate$$inlined$catchDb$lambda$1 extends Lambda implements Function1<DedupeDao, Unit> {
    final /* synthetic */ ItemMapping $cloudContentMapping$inlined;
    final /* synthetic */ ItemMapping $localContentMapping$inlined;
    final /* synthetic */ DeduplicationRequest $request$inlined;
    final /* synthetic */ MetadataDeduplicatorStage this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MetadataDeduplicatorStage$deduplicate$$inlined$catchDb$lambda$1(MetadataDeduplicatorStage metadataDeduplicatorStage, DeduplicationRequest deduplicationRequest, ItemMapping itemMapping, ItemMapping itemMapping2) {
        super(1);
        this.this$0 = metadataDeduplicatorStage;
        this.$request$inlined = deduplicationRequest;
        this.$cloudContentMapping$inlined = itemMapping;
        this.$localContentMapping$inlined = itemMapping2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(DedupeDao dedupeDao) {
        invoke2(dedupeDao);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull DedupeDao dao) {
        Intrinsics.checkParameterIsNotNull(dao, "dao");
        this.this$0.getItemMappingUtils$AndroidPhotosDiscovery_release().matchAndPersistMappings(this.$cloudContentMapping$inlined, this.$localContentMapping$inlined, dao);
    }
}
