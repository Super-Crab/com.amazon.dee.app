package amazon.speech.simclient.metrics;

import amazon.speech.simclient.metrics.MetricsRecord;
import amazon.speech.simclient.metrics.recorder.MetricsRecorder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class MetricsRecord<RecordType extends MetricsRecord<?>> {
    private final Metadata mMetadata = new Metadata();
    private MetricsRecorder mMetricsRecorder;
    private String mName;
    private String mProgramName;
    private String mSourceName;

    /* loaded from: classes.dex */
    public static class Metadata {
        private final Map<String, String> mData = new HashMap();

        public void add(String str, String str2) {
            if (str == null || str2 == null) {
                return;
            }
            this.mData.put(str, str2);
        }

        public void clear() {
            this.mData.clear();
        }

        public boolean containsKey(String str) {
            return this.mData.containsKey(str);
        }

        public Set<Map.Entry<String, String>> entrySet() {
            return this.mData.entrySet();
        }

        public String get(String str) {
            return this.mData.get(str);
        }

        public String remove(String str) {
            return this.mData.remove(str);
        }

        public void add(Metadata metadata) {
            if (metadata == null) {
                return;
            }
            add(metadata.mData);
        }

        public void add(Map<String, String> map) {
            if (map == null) {
                return;
            }
            this.mData.putAll(map);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType addMetadata(String str, String str2) {
        this.mMetadata.add(str, str2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType clearMetaData() {
        this.mMetadata.clear();
        return this;
    }

    public Metadata getMetadata() {
        return this.mMetadata;
    }

    MetricsRecorder getMetricsRecorder() {
        return this.mMetricsRecorder;
    }

    public String getName() {
        return this.mName;
    }

    public String getProgramName() {
        return this.mProgramName;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public void record() {
        this.mMetricsRecorder.record(this);
    }

    public void recycle() {
        this.mMetadata.clear();
        this.mProgramName = null;
        this.mSourceName = null;
        this.mName = null;
        this.mMetricsRecorder = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType removeMetadata(String str) {
        this.mMetadata.remove(str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public RecordType setMetricsRecorder(MetricsRecorder metricsRecorder) {
        this.mMetricsRecorder = metricsRecorder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType setName(String str) {
        this.mName = str;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType setProgramName(String str) {
        this.mProgramName = str;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType setSourceName(String str) {
        this.mSourceName = str;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType addMetadata(Metadata metadata) {
        this.mMetadata.add(metadata);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecordType addMetadata(Map<String, String> map) {
        this.mMetadata.add(map);
        return this;
    }
}
