package com.amazon.alexa.cantilever;

import androidx.annotation.NonNull;
import com.amazon.alexa.cantilever.HelpMetricsConstants;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Logger;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class HelpMetricsService {
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpMetricsService.class);
    private final LazyComponent<Mobilytics> mobilytics;

    public HelpMetricsService() {
        this(ComponentRegistry.getInstance());
    }

    private void recordError(String str, @NonNull String str2) {
        Lib.Log.d(TAG, GeneratedOutlineSupport1.outline76("Error name: ", str, ", description: ", str2));
        this.mobilytics.mo10268get().recordOccurrence(str, true, HelpMetricsConstants.ComponentName.CANTILEVER, HelpMetricsConstants.ComponentName.CANTILEVER_SUB);
    }

    private void recordOccurrence(String str) {
        Logger logger = Lib.Log;
        Tag tag = TAG;
        logger.d(tag, "Occurrence: " + str);
        this.mobilytics.mo10268get().recordOccurrence(str, true, HelpMetricsConstants.ComponentName.CANTILEVER, HelpMetricsConstants.ComponentName.CANTILEVER_SUB);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadAttempt() {
        recordOccurrence(HelpMetricsConstants.EventName.LOAD_ATTEMPT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadCancel() {
        recordOccurrence(HelpMetricsConstants.EventName.LOAD_CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadHttpError(int i, @NonNull String str) {
        recordError(GeneratedOutlineSupport1.outline49(HelpMetricsConstants.EventName.LOAD_ERROR_PREFIX_HTTP, i), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadOtherError(int i, @NonNull String str) {
        recordError(HelpMetricsConstants.EventName.LOAD_ERROR_PREFIX_OTHER, i + ":" + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadSignIn() {
        recordOccurrence(HelpMetricsConstants.EventName.LOAD_SIGNIN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordHelpLoadSuccess() {
        recordOccurrence(HelpMetricsConstants.EventName.LOAD_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordJsEvent(String str) {
        recordOccurrence(GeneratedOutlineSupport1.outline72(HelpMetricsConstants.EventName.JS_EVENT_PREFIX, str));
    }

    public HelpMetricsService(ComponentRegistry componentRegistry) {
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
    }
}
