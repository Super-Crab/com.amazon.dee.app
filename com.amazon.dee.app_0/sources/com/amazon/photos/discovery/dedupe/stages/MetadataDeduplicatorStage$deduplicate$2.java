package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* compiled from: MetadataDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final /* synthetic */ class MetadataDeduplicatorStage$deduplicate$2 extends MutablePropertyReference0 {
    MetadataDeduplicatorStage$deduplicate$2(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        super(metadataDeduplicatorStage);
    }

    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        return MetadataDeduplicatorStage.access$getDayAggregations$p((MetadataDeduplicatorStage) this.receiver);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "dayAggregations";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(MetadataDeduplicatorStage.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getDayAggregations()Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;";
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        ((MetadataDeduplicatorStage) this.receiver).dayAggregations = (DayAggregations) obj;
    }
}
