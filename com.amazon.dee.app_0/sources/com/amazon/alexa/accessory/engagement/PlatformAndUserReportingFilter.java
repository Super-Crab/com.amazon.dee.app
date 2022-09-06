package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
final class PlatformAndUserReportingFilter implements DemReporter {
    private static final String TAG = "PlatformAndUserReportingFilter:";
    private final EnvironmentAttributes environmentAttributes;
    private final DemReporter reportingDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlatformAndUserReportingFilter(@NonNull EnvironmentAttributes environmentAttributes, @NonNull DemReporter demReporter) {
        Preconditions.notNull(environmentAttributes, "environmentAttributes");
        Preconditions.notNull(demReporter, "reportingDelegate");
        this.environmentAttributes = environmentAttributes;
        this.reportingDelegate = demReporter;
    }

    @Override // com.amazon.alexa.accessory.engagement.DemReporter
    public void report(DemType demType, AccessorySession accessorySession, String str) {
        boolean z = false;
        if (!this.environmentAttributes.isUserSignedIn()) {
            Logger.d("%s %s there is no user signed in.", TAG, "Refusing to log device engagement metric because ");
            z = true;
        }
        if (z) {
            return;
        }
        this.reportingDelegate.report(demType, accessorySession, str);
    }
}
