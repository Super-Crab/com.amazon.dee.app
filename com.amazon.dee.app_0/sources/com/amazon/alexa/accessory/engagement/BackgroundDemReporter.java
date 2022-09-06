package com.amazon.alexa.accessory.engagement;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BackgroundDemReporter implements DemReporter {
    private static final String TAG = "BackgroundDemReporter:";
    private final DemReporter delegatedReporter;
    private final Executor executor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackgroundDemReporter(Executor executor, DemReporter demReporter) {
        Preconditions.notNull(executor, "executor");
        Preconditions.notNull(demReporter, "delegatedReporter");
        this.executor = executor;
        this.delegatedReporter = demReporter;
    }

    public /* synthetic */ void lambda$report$0$BackgroundDemReporter(DemType demType, AccessorySession accessorySession, String str) {
        try {
            this.delegatedReporter.report(demType, accessorySession, str);
        } catch (Throwable th) {
            Logger.e("%s Failed to report Device Engagement Metric for accessory device.", th, TAG);
        }
    }

    @Override // com.amazon.alexa.accessory.engagement.DemReporter
    public void report(final DemType demType, final AccessorySession accessorySession, final String str) {
        this.executor.execute(new Runnable() { // from class: com.amazon.alexa.accessory.engagement.-$$Lambda$BackgroundDemReporter$g3lEGr5ZEdOkir1G9m3DHYsfGHY
            @Override // java.lang.Runnable
            public final void run() {
                BackgroundDemReporter.this.lambda$report$0$BackgroundDemReporter(demType, accessorySession, str);
            }
        });
    }
}
