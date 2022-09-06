package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class LogsCollectionEntry<TValue> {
    private static final String LOGS_KEY_SEPARATOR = "###########################";
    private static final String NEW_LINE = "\n";
    private final LogsCollectionType logsCollectionType;
    private final TValue logsValue;

    public LogsCollectionEntry(LogsCollectionType logsCollectionType, TValue tvalue) {
        this.logsCollectionType = logsCollectionType;
        this.logsValue = tvalue;
    }

    public LogsCollectionType getLogsCollectionType() {
        return this.logsCollectionType;
    }

    @VisibleForTesting
    public TValue getLogsValue() {
        return this.logsValue;
    }

    @NonNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n###########################\n");
        outline107.append(this.logsCollectionType.toString());
        outline107.append("\n");
        outline107.append(LOGS_KEY_SEPARATOR);
        outline107.append("\n");
        outline107.append(this.logsValue.toString());
        return outline107.toString();
    }
}
