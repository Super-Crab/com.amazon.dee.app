package com.amazon.tarazed.utility;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnounceHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J \u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/utility/AnnounceHelper;", "", "()V", "obtainAnnouncementAccessibilityEvent", "Landroid/view/accessibility/AccessibilityEvent;", "obtainViewAccessibilityDelegate", "Landroid/view/View$AccessibilityDelegate;", "speakText", "", "textToSpeech", "Landroid/speech/tts/TextToSpeech;", "context", "Landroid/content/Context;", "message", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnounceHelper {
    public static final AnnounceHelper INSTANCE = new AnnounceHelper();

    private AnnounceHelper() {
    }

    @NotNull
    public final AccessibilityEvent obtainAnnouncementAccessibilityEvent() {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
        Intrinsics.checkExpressionValueIsNotNull(obtain, "AccessibilityEvent.obtai…yEvent.TYPE_ANNOUNCEMENT)");
        return obtain;
    }

    @NotNull
    public final View.AccessibilityDelegate obtainViewAccessibilityDelegate() {
        return new View.AccessibilityDelegate();
    }

    public final void speakText(@Nullable TextToSpeech textToSpeech, @NotNull Context context, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (textToSpeech != null) {
            int i = Build.VERSION.SDK_INT;
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            Configuration configuration = resources.getConfiguration();
            Intrinsics.checkExpressionValueIsNotNull(configuration, "context.resources.configuration");
            textToSpeech.setLanguage(configuration.getLocales().get(0));
        }
        if (textToSpeech != null) {
            textToSpeech.speak(message, 1, null, UUID.randomUUID().toString());
        }
    }
}
