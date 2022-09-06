package com.amazon.alexa.accessory.speechapi.csm;

import amazon.alexa.locale.AlexaLocaleHelper;
import amazon.alexa.locale.AlexaLocaleListener;
import android.content.Context;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaLocaleHelperWrapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/AlexaLocaleHelperWrapper;", "", "()V", "getAlexaLocale", "Ljava/util/Locale;", "context", "Landroid/content/Context;", "registerListener", "", "alexaLocaleListener", "Lamazon/alexa/locale/AlexaLocaleListener;", "useAlexaLocale", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class AlexaLocaleHelperWrapper {
    @Nullable
    public Locale getAlexaLocale(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return AlexaLocaleHelper.getAlexaLocale(context);
    }

    public void registerListener(@NotNull Context context, @NotNull AlexaLocaleListener alexaLocaleListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(alexaLocaleListener, "alexaLocaleListener");
        AlexaLocaleHelper.registerListener(context, alexaLocaleListener);
    }

    public void useAlexaLocale(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        AlexaLocaleHelper.useAlexaLocale(context);
    }
}
