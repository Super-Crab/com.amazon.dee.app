package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.engagement.LongRepository;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
/* loaded from: classes.dex */
final class DebounceReportingFilter implements DemReporter {
    private static final String LAST_REPORTING_TIMES_REPOSITORY_NAME = "last.accessory.dem.report.times";
    private static final String TAG = "DebounceReportingFilter:";
    private final AdapterFactory<AccessorySession, AccessoryAttributes> accessoryAttributesFactory;
    private final DemReporter delegatedReporter;
    private final LongRepository reportingTimesRepository;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DebounceReportingFilter(@NonNull DemReporter demReporter, @NonNull AdapterFactory<AccessorySession, AccessoryAttributes> adapterFactory, @NonNull LongRepository.Factory factory) {
        Preconditions.notNull(demReporter, "delegatedReporter");
        Preconditions.notNull(adapterFactory, "accessoryAttributesFactory");
        this.delegatedReporter = demReporter;
        this.accessoryAttributesFactory = adapterFactory;
        this.reportingTimesRepository = factory.create(LAST_REPORTING_TIMES_REPOSITORY_NAME);
    }

    private boolean isTodayAfterLastReportingDay(String str) {
        return !this.reportingTimesRepository.contains(str) || DaysSinceEpoch.get() > this.reportingTimesRepository.get(str);
    }

    private void updateLastReportingDay(String str) {
        this.reportingTimesRepository.put(str, DaysSinceEpoch.get());
    }

    @Override // com.amazon.alexa.accessory.engagement.DemReporter
    public void report(@NonNull DemType demType, @NonNull AccessorySession accessorySession, String str) {
        Preconditions.notNull(demType, "demType");
        Preconditions.notNull(accessorySession, "accessorySession");
        String accessoryDeviceSerialNumber = this.accessoryAttributesFactory.createFrom(accessorySession).getAccessoryDeviceSerialNumber();
        String format = String.format(Locale.US, "%s:%s", accessoryDeviceSerialNumber, demType.name());
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Last report of ");
        outline107.append(demType.name());
        outline107.append(" for ");
        outline107.append(accessoryDeviceSerialNumber);
        String sb = outline107.toString();
        if (!isTodayAfterLastReportingDay(format) && demType != DemType.USER_PRESENT) {
            Logger.i("%s %s already occurred today. Will suppress.", TAG, sb);
            return;
        }
        Logger.i("%s %s was before today. Will report.", TAG, sb);
        try {
            this.delegatedReporter.report(demType, accessorySession, str);
            updateLastReportingDay(format);
        } catch (RuntimeException e) {
            Logger.e("%s Failed to report %s", e, TAG, demType.name());
        }
    }
}
