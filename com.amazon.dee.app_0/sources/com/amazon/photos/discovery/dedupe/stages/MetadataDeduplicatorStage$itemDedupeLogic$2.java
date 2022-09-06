package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.photos.discovery.internal.dedupe.metadata.ItemDedupeLogic;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MetadataDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemDedupeLogic;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MetadataDeduplicatorStage$itemDedupeLogic$2 extends Lambda implements Function0<ItemDedupeLogic> {
    final /* synthetic */ MetadataDeduplicatorStage this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MetadataDeduplicatorStage$itemDedupeLogic$2(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        super(0);
        this.this$0 = metadataDeduplicatorStage;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final ItemDedupeLogic mo12560invoke() {
        return new ItemDedupeLogic(this.this$0.getDateMatcher$AndroidPhotosDiscovery_release(), this.this$0.getNodeUtils$AndroidPhotosDiscovery_release(), this.this$0.getMetrics$AndroidPhotosDiscovery_release());
    }
}
