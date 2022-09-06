package amazon.speech.simclient.context;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes.dex */
public class DeviceContext {
    private static final String DELIMETER = "#";
    private static final String EMPTY_PAYLOAD = "{}";
    private static final String NULL = "null";
    private String mCaller;
    private Lifecycle mLifecycle;
    private final String mName;
    private final String mNamespace;
    private String mPayload;
    private Date mTimeOfSample;
    private long mUncertaintyInMilliseconds = 0;
    private PublishState mPublishState = PublishState.UNPUBLISHED;

    /* loaded from: classes.dex */
    public enum PublishState {
        UNPUBLISHED,
        PENDING,
        PUBLISHED
    }

    @Deprecated
    public DeviceContext(String str) {
        String[] split = str.split("#");
        if (split.length >= 5) {
            this.mNamespace = split[0];
            this.mName = split[1];
            this.mLifecycle = Lifecycle.fromInt(Integer.parseInt(split[2]));
            this.mCaller = "null".equals(split[3]) ? null : split[3];
            this.mPayload = join("#", (String[]) Arrays.copyOfRange(split, 4, split.length));
            return;
        }
        throw new IllegalArgumentException();
    }

    private String join(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(str);
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public boolean deepEquals(DeviceContext deviceContext) {
        return equals(deviceContext) && this.mCaller.equals(deviceContext.mCaller) && this.mPayload.equals(deviceContext.mPayload) && this.mTimeOfSample.equals(deviceContext.mTimeOfSample) && this.mUncertaintyInMilliseconds == deviceContext.mUncertaintyInMilliseconds && this.mPublishState == deviceContext.mPublishState;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DeviceContext)) {
            return false;
        }
        DeviceContext deviceContext = (DeviceContext) obj;
        return getNamespace().equals(deviceContext.getNamespace()) && getName().equals(deviceContext.getName());
    }

    public String getCaller() {
        return this.mCaller;
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getPayload() {
        String str = this.mPayload;
        return str != null ? str : EMPTY_PAYLOAD;
    }

    public PublishState getPublishState() {
        return this.mPublishState;
    }

    public String getTimeOfSample() {
        return ISO8601DateUtil.getInstance().getTimestamp(this.mTimeOfSample);
    }

    public Date getTimeOfSampleAsDate() {
        return this.mTimeOfSample;
    }

    public long getUncertaintyInMilliseconds() {
        return this.mUncertaintyInMilliseconds;
    }

    public int hashCode() {
        return Objects.hash(getNamespace(), getName());
    }

    public void setPublishState(PublishState publishState) {
        this.mPublishState = publishState;
    }

    public String toString() {
        Object[] objArr = new Object[9];
        objArr[0] = getNamespace();
        objArr[1] = "#";
        objArr[2] = getName();
        objArr[3] = "#";
        objArr[4] = Integer.valueOf(getLifecycle().getValue());
        objArr[5] = "#";
        objArr[6] = getCaller() == null ? "null" : getCaller();
        objArr[7] = "#";
        objArr[8] = getPayload();
        return String.format("%s%s%s%s%d%s%s%s%s", objArr);
    }

    public DeviceContext withCaller(String str) {
        this.mCaller = str;
        return this;
    }

    public DeviceContext withLifecycle(Lifecycle lifecycle) {
        this.mLifecycle = lifecycle;
        return this;
    }

    public DeviceContext withPayload(String str) {
        this.mPayload = str;
        return this;
    }

    public DeviceContext withTimeOfSample(Date date) {
        this.mTimeOfSample = date;
        return this;
    }

    public DeviceContext withUncertaintyInMilliseconds(long j) {
        if (j >= 0) {
            this.mUncertaintyInMilliseconds = j;
            return this;
        }
        throw new IllegalArgumentException("uncertaintyInMilliseconds cannot be negative.");
    }

    public DeviceContext(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            if (str2 != null && !str2.isEmpty()) {
                this.mNamespace = str;
                this.mName = str2;
                this.mTimeOfSample = new Date();
                this.mLifecycle = Lifecycle.TRANSIENT;
                return;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }
}
