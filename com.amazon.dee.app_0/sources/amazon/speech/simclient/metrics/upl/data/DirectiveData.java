package amazon.speech.simclient.metrics.upl.data;
/* loaded from: classes.dex */
public class DirectiveData {
    private String mChannel;
    private String mName;
    private String mNamespace;
    private long mFirstByteTimestamp = -1;
    private long mDispatchTimestamp = -1;
    private long mParseCompleteTimestamp = -1;

    public String getChannel() {
        return this.mChannel;
    }

    public long getDispatchTimestamp() {
        return this.mDispatchTimestamp;
    }

    public long getFirstByteTimestamp() {
        return this.mFirstByteTimestamp;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public long getParseCompleteTimestamp() {
        return this.mParseCompleteTimestamp;
    }

    public DirectiveData setChannel(String str) {
        this.mChannel = str;
        return this;
    }

    public DirectiveData setDispatchTimestamp(long j) {
        this.mDispatchTimestamp = j;
        return this;
    }

    public DirectiveData setFirstByteTimestamp(long j) {
        this.mFirstByteTimestamp = j;
        return this;
    }

    public DirectiveData setName(String str) {
        this.mName = str;
        return this;
    }

    public DirectiveData setNamespace(String str) {
        this.mNamespace = str;
        return this;
    }

    public DirectiveData setParseCompleteTimestamp(long j) {
        this.mParseCompleteTimestamp = j;
        return this;
    }
}
