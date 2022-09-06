package amazon.speech.simclient.settings;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class SettingsData implements Parcelable {
    public static final Parcelable.Creator<SettingsData> CREATOR = new Parcelable.Creator<SettingsData>() { // from class: amazon.speech.simclient.settings.SettingsData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SettingsData mo51createFromParcel(Parcel parcel) {
            return new SettingsData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SettingsData[] mo52newArray(int i) {
            return new SettingsData[i];
        }
    };
    private static final int PARCEL_FORMAT_VERSION = 0;
    private final Bundle mExtra;
    private final String mName;
    private final Object mValue;

    public SettingsData(String str, boolean z) {
        this(str, z, (Bundle) null);
    }

    public boolean asBoolean() {
        Object obj = this.mValue;
        if (obj != null) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline45(this.mValue, GeneratedOutlineSupport1.outline107("Value is ")));
        }
        throw new IllegalStateException("Value is null");
    }

    public String asString() {
        Object obj = this.mValue;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline45(this.mValue, GeneratedOutlineSupport1.outline107("Value is ")));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SettingsData)) {
            return false;
        }
        SettingsData settingsData = (SettingsData) obj;
        if (!this.mName.equals(settingsData.mName)) {
            return false;
        }
        if (this.mValue == null && settingsData.mValue != null) {
            return false;
        }
        Object obj2 = this.mValue;
        if (obj2 != null && !obj2.equals(settingsData.mValue)) {
            return false;
        }
        if (this.mExtra == null && settingsData.mExtra != null) {
            return false;
        }
        Bundle bundle = this.mExtra;
        if (bundle != null) {
            if (settingsData.mExtra == null) {
                return false;
            }
            Set<String> keySet = bundle.keySet();
            if (!keySet.equals(settingsData.mExtra.keySet())) {
                return false;
            }
            for (String str : keySet) {
                Object obj3 = this.mExtra.get(str);
                Object obj4 = settingsData.mExtra.get(str);
                if (obj3 != obj4 && (obj3 == null || obj4 == null || !obj3.equals(obj4))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    public String getName() {
        return this.mName;
    }

    public String getStringExtra(String str) {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return null;
        }
        return bundle.getString(str);
    }

    public Object getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mValue, this.mExtra);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SettingsData[");
        sb.append(this.mName);
        if (this.mValue != null) {
            sb.append(", mValue=");
            sb.append(this.mValue);
        }
        if (this.mExtra != null) {
            sb.append(", mExtra=");
            sb.append(this.mExtra);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(0);
        parcel.writeString(this.mName);
        parcel.writeValue(this.mValue);
        parcel.writeBundle(this.mExtra);
    }

    public SettingsData(String str, boolean z, Bundle bundle) {
        if (str != null) {
            this.mName = str;
            this.mValue = Boolean.valueOf(z);
            this.mExtra = bundle;
            return;
        }
        throw new IllegalArgumentException("name is null");
    }

    public SettingsData(String str, String str2, Bundle bundle) {
        if (str != null) {
            this.mName = str;
            this.mValue = str2;
            this.mExtra = bundle;
            return;
        }
        throw new IllegalArgumentException("name is null");
    }

    SettingsData(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == 0) {
            this.mName = parcel.readString();
            this.mValue = parcel.readValue(SettingsData.class.getClassLoader());
            this.mExtra = parcel.readBundle();
            return;
        }
        throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline53("Parcel version ", readInt, ", expected ", 0));
    }
}
