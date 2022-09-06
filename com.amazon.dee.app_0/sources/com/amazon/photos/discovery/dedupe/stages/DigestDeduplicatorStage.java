package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.dedupe.Deduplicator;
import com.amazon.photos.discovery.dedupe.DeduplicatorResult;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.Injectable;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.digest.LocalDigestAssociator;
import java.util.HashSet;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DigestDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/DigestDeduplicatorStage;", "Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "Lcom/amazon/photos/discovery/internal/Injectable;", "()V", "cloudAssociator", "Lcom/amazon/photos/discovery/internal/dedupe/digest/CloudDigestAssociator;", "getCloudAssociator$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/digest/CloudDigestAssociator;", "setCloudAssociator$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/digest/CloudDigestAssociator;)V", "localAssociator", "Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;", "getLocalAssociator$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;", "setLocalAssociator$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;)V", "deduplicate", "Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "inject", "", JsonFields.COMPONENT, "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DigestDeduplicatorStage implements Deduplicator, Injectable {
    @Inject
    @NotNull
    public CloudDigestAssociator cloudAssociator;
    @Inject
    @NotNull
    public LocalDigestAssociator localAssociator;

    @Override // com.amazon.photos.discovery.dedupe.Deduplicator
    @NotNull
    public DeduplicatorResult deduplicate(@NotNull DeduplicationRequest request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        HashSet hashSet = new HashSet();
        LocalDigestAssociator localDigestAssociator = this.localAssociator;
        if (localDigestAssociator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAssociator");
        }
        LocalDigestAssociator.LocalDigestAssociationResult associateMatching = localDigestAssociator.associateMatching(request.getUnifiedItems(), request.getDedupeDao());
        hashSet.addAll(associateMatching.getModifiedUnifiedIds());
        CloudDigestAssociator cloudDigestAssociator = this.cloudAssociator;
        if (cloudDigestAssociator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cloudAssociator");
        }
        hashSet.addAll(cloudDigestAssociator.associateMatching(associateMatching.getUnifiedItemsToProcess(), request.getDedupeDao()));
        return new DeduplicatorResult(hashSet.size(), ConstantsKt.DIGEST_DEDUPLICATOR_STAGE);
    }

    @NotNull
    public final CloudDigestAssociator getCloudAssociator$AndroidPhotosDiscovery_release() {
        CloudDigestAssociator cloudDigestAssociator = this.cloudAssociator;
        if (cloudDigestAssociator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cloudAssociator");
        }
        return cloudDigestAssociator;
    }

    @NotNull
    public final LocalDigestAssociator getLocalAssociator$AndroidPhotosDiscovery_release() {
        LocalDigestAssociator localDigestAssociator = this.localAssociator;
        if (localDigestAssociator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAssociator");
        }
        return localDigestAssociator;
    }

    @Override // com.amazon.photos.discovery.internal.Injectable
    public void inject(@NotNull DiscoveryComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        component.inject(this);
    }

    public final void setCloudAssociator$AndroidPhotosDiscovery_release(@NotNull CloudDigestAssociator cloudDigestAssociator) {
        Intrinsics.checkParameterIsNotNull(cloudDigestAssociator, "<set-?>");
        this.cloudAssociator = cloudDigestAssociator;
    }

    public final void setLocalAssociator$AndroidPhotosDiscovery_release(@NotNull LocalDigestAssociator localDigestAssociator) {
        Intrinsics.checkParameterIsNotNull(localDigestAssociator, "<set-?>");
        this.localAssociator = localDigestAssociator;
    }
}
