package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.voice.tta.features.Feature;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Feature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"feature", "Lcom/amazon/alexa/voice/tta/features/Feature$Named;", "name", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class FeatureKt {
    @NotNull
    public static final Feature.Named feature(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new Feature.Named(name);
    }
}
