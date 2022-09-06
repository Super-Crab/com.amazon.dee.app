package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaSupportedLocalesListenerv2Adapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0014\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r\u0018\u00010\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2Adapter;", "Lcom/amazon/alexa/api/AlexaSupportedLocalesListener;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "(Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "onSupportedLocales", "", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "Ljava/util/Locale;", SystemModelI18nConfigTransformer.KEY_LOCALE_COMBINATIONS, "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaSupportedLocalesListenerv2Adapter implements com.amazon.alexa.api.AlexaSupportedLocalesListener {
    @NotNull
    private final AlexaSupportedLocalesListenerv2 compatListener;

    public AlexaSupportedLocalesListenerv2Adapter(@NotNull AlexaSupportedLocalesListenerv2 compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaSupportedLocalesListenerv2 getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaSupportedLocalesListener
    public void onSupportedLocales(@Nullable Set<Locale> set, @Nullable Set<List<Locale>> set2) {
        this.compatListener.onSupportedLocales(set, set2);
    }
}
