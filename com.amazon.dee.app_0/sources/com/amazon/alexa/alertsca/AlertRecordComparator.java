package com.amazon.alexa.alertsca;

import java.util.Comparator;
/* loaded from: classes6.dex */
public class AlertRecordComparator implements Comparator<AlertRecord> {
    @Override // java.util.Comparator
    public int compare(AlertRecord alertRecord, AlertRecord alertRecord2) {
        return (int) (alertRecord.getScheduledTime().getTime() - alertRecord2.getScheduledTime().getTime());
    }
}
