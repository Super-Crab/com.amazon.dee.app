package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.voice.tta.features.Feature;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* compiled from: Features.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/Features;", "", "()V", "APP_SEARCH", "Lcom/amazon/alexa/voice/tta/features/Feature$Any;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class Features {
    public static final Features INSTANCE = new Features();
    @JvmField
    @NotNull
    public static final Feature.Any APP_SEARCH = FeatureKt.feature("ALEXA_VOX_ANDROID_APP_SEARCH_DEV").or(FeatureKt.feature("ALEXA_VOX_ANDROID_APP_SEARCH_BETA")).or(FeatureKt.feature("ALEXA_VOX_ANDROID_APP_SEARCH_LAUNCH"));

    private Features() {
    }
}
