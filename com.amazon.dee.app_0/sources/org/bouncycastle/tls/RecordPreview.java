package org.bouncycastle.tls;
/* loaded from: classes5.dex */
public final class RecordPreview {
    private final int applicationDataLimit;
    private final int recordSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordPreview(int i, int i2) {
        this.recordSize = i;
        this.applicationDataLimit = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RecordPreview combine(RecordPreview recordPreview, RecordPreview recordPreview2) {
        return new RecordPreview(recordPreview2.getRecordSize() + recordPreview.getRecordSize(), recordPreview2.getApplicationDataLimit() + recordPreview.getApplicationDataLimit());
    }

    public int getApplicationDataLimit() {
        return this.applicationDataLimit;
    }

    public int getRecordSize() {
        return this.recordSize;
    }
}
