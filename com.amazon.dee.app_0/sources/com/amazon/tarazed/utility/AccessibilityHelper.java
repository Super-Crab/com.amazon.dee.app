package com.amazon.tarazed.utility;

import android.content.Context;
import android.media.AudioAttributes;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.amazon.tarazed.utility.AccessibilityManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessibilityHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/utility/AccessibilityHelper;", "", "()V", "accessibilityManager", "Lcom/amazon/tarazed/utility/AccessibilityManager;", "textToSpeech", "Landroid/speech/tts/TextToSpeech;", "announceForAccessibility1P", "", "context", "Landroid/content/Context;", "message", "", "announceForAccessibility3P", "view", "Landroid/view/View;", "setAccessibilityManager", "DefaultAccessibilityManager", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AccessibilityHelper {
    public static final AccessibilityHelper INSTANCE;
    private static AccessibilityManager accessibilityManager;
    private static TextToSpeech textToSpeech;

    /* compiled from: AccessibilityHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/tarazed/utility/AccessibilityHelper$DefaultAccessibilityManager;", "Lcom/amazon/tarazed/utility/AccessibilityManager;", "()V", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class DefaultAccessibilityManager implements AccessibilityManager {
        @Override // com.amazon.tarazed.utility.AccessibilityManager
        public boolean isEnabled(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return AccessibilityManager.DefaultImpls.isEnabled(this, context);
        }
    }

    static {
        AccessibilityHelper accessibilityHelper = new AccessibilityHelper();
        INSTANCE = accessibilityHelper;
        accessibilityHelper.setAccessibilityManager(new DefaultAccessibilityManager());
    }

    private AccessibilityHelper() {
    }

    public static final /* synthetic */ AccessibilityManager access$getAccessibilityManager$p(AccessibilityHelper accessibilityHelper) {
        AccessibilityManager accessibilityManager2 = accessibilityManager;
        if (accessibilityManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessibilityManager");
        }
        return accessibilityManager2;
    }

    public final void announceForAccessibility1P(@NotNull final Context context, @NotNull final CharSequence message) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AccessibilityManager accessibilityManager2 = accessibilityManager;
        if (accessibilityManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessibilityManager");
        }
        if (!accessibilityManager2.isEnabled(context)) {
            return;
        }
        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() { // from class: com.amazon.tarazed.utility.AccessibilityHelper$announceForAccessibility1P$1
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i) {
                    TextToSpeech textToSpeech2;
                    TextToSpeech textToSpeech3;
                    if (!AccessibilityHelper.access$getAccessibilityManager$p(AccessibilityHelper.INSTANCE).isEnabled(context)) {
                        return;
                    }
                    AccessibilityHelper accessibilityHelper = AccessibilityHelper.INSTANCE;
                    textToSpeech2 = AccessibilityHelper.textToSpeech;
                    if (textToSpeech2 != null) {
                        textToSpeech2.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build());
                    }
                    AnnounceHelper announceHelper = AnnounceHelper.INSTANCE;
                    AccessibilityHelper accessibilityHelper2 = AccessibilityHelper.INSTANCE;
                    textToSpeech3 = AccessibilityHelper.textToSpeech;
                    announceHelper.speakText(textToSpeech3, context, message);
                }
            });
        } else {
            AnnounceHelper.INSTANCE.speakText(textToSpeech, context, message);
        }
    }

    public final void announceForAccessibility3P(@NotNull Context context, @NotNull View view, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AccessibilityManager accessibilityManager2 = accessibilityManager;
        if (accessibilityManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessibilityManager");
        }
        if (!accessibilityManager2.isEnabled(context)) {
            return;
        }
        AccessibilityEvent obtainAnnouncementAccessibilityEvent = AnnounceHelper.INSTANCE.obtainAnnouncementAccessibilityEvent();
        AnnounceHelper.INSTANCE.obtainViewAccessibilityDelegate().onInitializeAccessibilityEvent(view, obtainAnnouncementAccessibilityEvent);
        obtainAnnouncementAccessibilityEvent.getText().add(message);
        view.getParent().requestSendAccessibilityEvent(view, obtainAnnouncementAccessibilityEvent);
    }

    public final void setAccessibilityManager(@NotNull AccessibilityManager accessibilityManager2) {
        Intrinsics.checkParameterIsNotNull(accessibilityManager2, "accessibilityManager");
        accessibilityManager = accessibilityManager2;
    }
}
