package amazon.speech.simclient;
/* loaded from: classes.dex */
public class Header {
    private static final int PARCEL_DESCRIPTION_BITMASK = 0;
    private final String mLabel;
    private final String mName;
    private final String mNamespace;
    private final long mTimestamp;

    /* loaded from: classes.dex */
    public static class Builder {
        public String namespace = null;
        public String name = null;
        public String label = null;
        public long timestamp = -1;

        public Header build() {
            String str;
            String str2 = this.namespace;
            if (str2 != null && (str = this.name) != null) {
                return new Header(str2, str, this.label, this.timestamp);
            }
            throw new IllegalArgumentException("Header requires namespace and name");
        }

        public Builder label(String str) {
            this.label = str;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder namespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder timestamp(long j) {
            this.timestamp = j;
            return this;
        }
    }

    public Header(String str, String str2) {
        this(str, str2, null, -1L);
    }

    public static boolean compare(long j, long j2) {
        return j == j2;
    }

    public static boolean compare(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        return compare(this.mName, header.mName) && compare(this.mNamespace, header.mNamespace) && compare(this.mLabel, header.mLabel) && compare(this.mTimestamp, header.mTimestamp);
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        String str = this.mName;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mNamespace;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mLabel;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        long j = this.mTimestamp;
        if (j >= 0) {
            i = (int) j;
        }
        return hashCode3 + i;
    }

    public Header(String str, String str2, String str3, long j) {
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            this.mNamespace = str;
            this.mName = str2;
            this.mLabel = str3;
            this.mTimestamp = j;
            return;
        }
        throw new IllegalArgumentException();
    }
}
