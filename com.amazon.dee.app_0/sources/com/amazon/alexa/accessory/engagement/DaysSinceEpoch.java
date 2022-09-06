package com.amazon.alexa.accessory.engagement;

import org.joda.time.Days;
import org.joda.time.Instant;
/* loaded from: classes.dex */
final class DaysSinceEpoch {
    private DaysSinceEpoch() {
        throw new UnsupportedOperationException("No instances of this utility class!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long get() {
        return Days.daysBetween(new Instant(0L).toDateTime().toLocalDate(), Instant.now().toDateTime().toLocalDate()).getDays();
    }
}
