package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaSupportedLocalesListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListener;", "", "onSupportedLocales", "", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "Ljava/util/Locale;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaSupportedLocalesListener {
    void onSupportedLocales(@Nullable Set<Locale> set);
}
