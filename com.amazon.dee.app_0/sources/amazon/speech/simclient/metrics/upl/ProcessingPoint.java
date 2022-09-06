package amazon.speech.simclient.metrics.upl;
/* loaded from: classes.dex */
class ProcessingPoint implements Comparable<ProcessingPoint> {
    public final String mName;
    public final long mTimestamp;
    public final ProcessingType mType;

    /* loaded from: classes.dex */
    public enum ProcessingType {
        ServerProcessing,
        DeviceProcessing,
        DevicePreProcessing
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessingPoint(ProcessingType processingType, String str, long j) {
        if (processingType != null && str != null && !str.isEmpty() && j > 0) {
            this.mType = processingType;
            this.mName = str;
            this.mTimestamp = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.lang.Comparable
    public int compareTo(ProcessingPoint processingPoint) {
        return Long.compare(this.mTimestamp, processingPoint.mTimestamp);
    }
}
