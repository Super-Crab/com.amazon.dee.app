package com.amazon.alexa.voice.tta.search;

import android.util.Log;
import com.amazon.alexa.api.TextResponseMetadata;
import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.voice.tta.simba.PromptData;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FrictionHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/FrictionHelper;", "", "()V", "frictivePrompts", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getFrictivePrompts", "()Ljava/util/HashSet;", "isFrictive", "", "textResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "setFrictivePrompts", "", "frictivePromptsList", "", "Lcom/amazon/alexa/voice/tta/simba/PromptData;", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class FrictionHelper {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "FrictionHelper";
    @NotNull
    private final HashSet<String> frictivePrompts = new HashSet<>();

    /* compiled from: FrictionHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/FrictionHelper$Companion;", "", "()V", "TAG", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public final HashSet<String> getFrictivePrompts() {
        return this.frictivePrompts;
    }

    public final boolean isFrictive(@NotNull TextResponse textResponse) {
        Intrinsics.checkParameterIsNotNull(textResponse, "textResponse");
        TextResponseMetadata metadata = textResponse.getMetadata();
        String str = null;
        String promptId = metadata != null ? metadata.getPromptId() : null;
        TextResponseMetadata metadata2 = textResponse.getMetadata();
        if (metadata2 != null) {
            str = metadata2.getNamespace();
        }
        HashSet<String> hashSet = this.frictivePrompts;
        return hashSet.contains(str + "::" + promptId);
    }

    public final void setFrictivePrompts(@NotNull List<PromptData> frictivePromptsList) {
        Intrinsics.checkParameterIsNotNull(frictivePromptsList, "frictivePromptsList");
        for (PromptData promptData : frictivePromptsList) {
            String promptId = promptData.getPromptId();
            String namespace = promptData.getNamespace();
            boolean z = true;
            if (!(promptId.length() == 0)) {
                if (namespace.length() != 0) {
                    z = false;
                }
                if (!z) {
                    this.frictivePrompts.add(namespace + "::" + promptId);
                }
            }
            Log.e(TAG, "frictivePrompts API returned prompts with empty promptId OR namespace.");
        }
    }
}
