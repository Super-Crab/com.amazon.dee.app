package amazon.speech.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() { // from class: amazon.speech.model.Event.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Event mo27createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Event[] mo28newArray(int i) {
            return new Event[i];
        }
    };
    private static final String VERSION_2_SUFFIX = "|V2";
    private String mLabel;
    private String mName;
    private String mNamespace;
    private Payload mPayload;
    private long mTimestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Event.class != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        String str = this.mName;
        if (str == null) {
            if (event.mName != null) {
                return false;
            }
        } else if (!str.equals(event.mName)) {
            return false;
        }
        String str2 = this.mNamespace;
        if (str2 == null) {
            if (event.mNamespace != null) {
                return false;
            }
        } else if (!str2.equals(event.mNamespace)) {
            return false;
        }
        String str3 = this.mLabel;
        if (str3 == null) {
            if (event.mLabel != null) {
                return false;
            }
        } else if (!str3.equals(event.mLabel)) {
            return false;
        }
        if (this.mTimestamp != event.mTimestamp) {
            return false;
        }
        if (getPayload() == null) {
            if (event.getPayload() != null) {
                return false;
            }
        } else if (!getPayload().equals(event.getPayload())) {
            return false;
        }
        return true;
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

    public String getPayload() {
        Payload payload = this.mPayload;
        if (payload == null) {
            return null;
        }
        return payload.getPayload();
    }

    public Payload getRawPayload() {
        return this.mPayload;
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
        int i2 = (hashCode3 + (j < 0 ? 0 : (int) j)) * 31;
        Payload payload = this.mPayload;
        if (payload != null) {
            i = payload.hashCode();
        }
        return i2 + i;
    }

    public void setLabel(String str) {
        if (str != null) {
            this.mLabel = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setName(String str) {
        if (str != null) {
            this.mName = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setNamespace(String str) {
        if (str != null) {
            this.mNamespace = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setPayload(String str) {
        setRawPayload(new SerializedPayload(str));
    }

    public void setRawPayload(Payload payload) {
        this.mPayload = payload;
    }

    public void setTimestamp(long j) {
        if (j >= 0) {
            this.mTimestamp = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getNamespace() + VERSION_2_SUFFIX);
        parcel.writeString(getName());
        parcel.writeString(getLabel());
        parcel.writeLong(getTimestamp());
        parcel.writeString(getPayload());
    }

    public Event() {
    }

    public Event(String str, String str2, Payload payload) {
        this(str, str2, null, -1L);
        this.mPayload = payload;
    }

    public Event(String str, String str2, String str3) {
        this(str, str2, null, -1L);
        this.mPayload = new SerializedPayload(str3);
    }

    public Event(String str, String str2, String str3, long j, Payload payload) {
        this(str, str2, str3, j);
        this.mPayload = payload;
    }

    public Event(String str, String str2, String str3, long j, String str4) {
        this(str, str2, str3, j);
        this.mPayload = new SerializedPayload(str4);
    }

    Event(String str, String str2, String str3, long j) {
        if (str != null && str2 != null) {
            this.mNamespace = str;
            this.mName = str2;
            this.mLabel = str3;
            this.mTimestamp = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    private Event(Parcel parcel) {
        this.mNamespace = parcel.readString();
        this.mName = parcel.readString();
        if (this.mNamespace.endsWith(VERSION_2_SUFFIX)) {
            this.mLabel = parcel.readString();
            this.mTimestamp = parcel.readLong();
            this.mNamespace = this.mNamespace.replace(VERSION_2_SUFFIX, "");
        }
        this.mPayload = new SerializedPayload(parcel.readString());
    }
}
