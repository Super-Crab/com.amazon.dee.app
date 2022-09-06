package amazon.speech.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes.dex */
public abstract class DeviceContext implements Parcelable {
    public static final boolean DEBUG = false;
    public static final boolean DEFAULT_PERSISTENT_VALUE = false;
    static final String PROP_NAME = "name";
    static final String PROP_NAMESPACE = "namespace";
    static final String PROP_PAYLOAD = "payload";
    protected final String mName;
    protected final String mNamespace;
    protected final boolean mPersistent;
    public static final String LOG_TAG = DeviceContext.class.getSimpleName();
    public static final Parcelable.Creator<DeviceContext> CREATOR = new Parcelable.Creator<DeviceContext>() { // from class: amazon.speech.model.DeviceContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DeviceContext mo23createFromParcel(Parcel parcel) {
            int dataPosition = parcel.dataPosition();
            Type valueOf = Type.valueOf(parcel.readString());
            parcel.setDataPosition(dataPosition);
            int ordinal = valueOf.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return new PayloadDeviceContext(parcel);
                }
                throw new IllegalArgumentException("Invalid context type");
            }
            return new KeyValueDeviceContext(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DeviceContext[] mo24newArray(int i) {
            return new DeviceContext[i];
        }
    };

    /* renamed from: amazon.speech.model.DeviceContext$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$model$DeviceContext$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$model$DeviceContext$Type[Type.Payload.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$model$DeviceContext$Type[Type.KeyValue.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        KeyValue,
        Payload
    }

    public DeviceContext(String str, String str2) {
        this(str, str2, false);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DeviceContext)) {
            return false;
        }
        DeviceContext deviceContext = (DeviceContext) obj;
        return TextUtils.equals(getNamespace(), deviceContext.getNamespace()) && TextUtils.equals(getName(), deviceContext.getName());
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public abstract String getPayload();

    public boolean getPersistent() {
        return this.mPersistent;
    }

    public abstract Type getType();

    public int hashCode() {
        int hashCode = getNamespace() != null ? getNamespace().hashCode() + 33 : 1;
        return getName() != null ? (hashCode * 33) + getName().hashCode() : hashCode;
    }

    public String toString() {
        return String.format("namespace=%s, name=%s, payload=%s, persistent=%s", getNamespace(), getName(), getPayload(), Boolean.valueOf(getPersistent()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mNamespace);
        parcel.writeString(this.mName);
    }

    public DeviceContext(String str, String str2, boolean z) {
        if (str != null && !str.isEmpty()) {
            if (str2 != null && !str2.isEmpty()) {
                this.mNamespace = str;
                this.mName = str2;
                this.mPersistent = z;
                return;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public DeviceContext(Parcel parcel) {
        parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        this.mNamespace = readString;
        this.mName = readString2;
        this.mPersistent = false;
    }
}
