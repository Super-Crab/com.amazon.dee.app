package com.amazon.photos.discovery.dedupe;

import androidx.annotation.AnyThread;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import com.amazon.photos.discovery.dedupe.stages.DigestBreakUpStage;
import com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage;
import com.amazon.photos.discovery.dedupe.stages.DigestDeduplicatorStage;
import com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage;
import com.amazon.photos.discovery.internal.ConstantsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultDedupeStages.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/DefaultDedupeStages;", "", "()V", "create", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "batchSize", "", "create$AndroidPhotosDiscovery_release", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DefaultDedupeStages {
    public static final DefaultDedupeStages INSTANCE = new DefaultDedupeStages();

    private DefaultDedupeStages() {
    }

    @AnyThread
    @NotNull
    public final List<DedupeStage> create() {
        return create$AndroidPhotosDiscovery_release(200);
    }

    @NotNull
    public final List<DedupeStage> create$AndroidPhotosDiscovery_release(int i) {
        List<DedupeStage> listOf;
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Constraints.Builder()\n  …TED)\n            .build()");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new DedupeStage[]{new DedupeStage(new MetadataDeduplicatorStage(null, 1, null), 1, 0, i, build, ConstantsKt.MDD_STAGE), new DedupeStage(new DigestCalculatorStage(), 2, 1, i, null, ConstantsKt.DIGEST_CALCULATOR_STAGE, 16, null), new DedupeStage(new DigestBreakUpStage(), 3, 2, i, null, ConstantsKt.DIGEST_BREAK_UP_STAGE, 16, null), new DedupeStage(new DigestDeduplicatorStage(), 4, 3, i, build, ConstantsKt.DIGEST_DEDUPLICATOR_STAGE)});
        return listOf;
    }
}
