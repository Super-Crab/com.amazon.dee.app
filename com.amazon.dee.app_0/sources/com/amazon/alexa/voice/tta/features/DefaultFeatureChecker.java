package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.voice.tta.features.Feature;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FeatureChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/DefaultFeatureChecker;", "Lcom/amazon/alexa/voice/tta/features/FeatureChecker;", "featureQuery", "Lcom/amazon/alexa/feature/consumer/api/FeatureQuery;", "(Lcom/amazon/alexa/feature/consumer/api/FeatureQuery;)V", "isFeatureEnabled", "", "feature", "Lcom/amazon/alexa/voice/tta/features/Feature;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class DefaultFeatureChecker implements FeatureChecker {
    private final FeatureQuery featureQuery;

    public DefaultFeatureChecker(@NotNull FeatureQuery featureQuery) {
        Intrinsics.checkParameterIsNotNull(featureQuery, "featureQuery");
        this.featureQuery = featureQuery;
    }

    @Override // com.amazon.alexa.voice.tta.features.FeatureChecker
    public boolean isFeatureEnabled(@NotNull Feature feature) {
        Intrinsics.checkParameterIsNotNull(feature, "feature");
        if (feature instanceof Feature.Named) {
            return this.featureQuery.isActive(((Feature.Named) feature).getName());
        }
        if (feature instanceof Feature.Any) {
            List<Feature> children = ((Feature.Any) feature).getChildren();
            if (!(children instanceof Collection) || !children.isEmpty()) {
                for (Feature feature2 : children) {
                    if (isFeatureEnabled(feature2)) {
                        return true;
                    }
                }
            }
        } else if (feature instanceof Feature.All) {
            List<Feature> children2 = ((Feature.All) feature).getChildren();
            if ((children2 instanceof Collection) && children2.isEmpty()) {
                return true;
            }
            for (Feature feature3 : children2) {
                if (!isFeatureEnabled(feature3)) {
                }
            }
            return true;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return false;
    }
}
