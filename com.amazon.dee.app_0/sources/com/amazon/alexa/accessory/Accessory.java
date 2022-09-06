package com.amazon.alexa.accessory;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class Accessory implements Parcelable {
    public static final Parcelable.Creator<Accessory> CREATOR = new Parcelable.Creator<Accessory>() { // from class: com.amazon.alexa.accessory.Accessory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Accessory mo292createFromParcel(Parcel parcel) {
            return new Accessory(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Accessory[] mo293newArray(int i) {
            return new Accessory[i];
        }
    };
    public static final int ERROR_ALREADY_STARTED = 1;
    public static final int ERROR_INTERNAL = 3;
    public static final int ERROR_UNKNOWN = 0;
    public static final int ERROR_UNSUPPORTED = 2;
    private final String address;
    private final String name;
    private final AccessoryTransport.Type transport;

    public Accessory(String str, AccessoryTransport.Type type, String str2) {
        Preconditions.notNull(type, "transport");
        Preconditions.notNull(str, "address");
        this.transport = type;
        this.address = str;
        this.name = str2;
    }

    public static String getErrorString(int i) {
        if (i == 0) {
            return "ERROR_UNKNOWN";
        }
        if (i == 1) {
            return "ERROR_ALREADY_STARTED";
        }
        if (i == 2) {
            return "ERROR_UNSUPPORTED";
        }
        if (i != 3) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown error code ", i));
        }
        return "ERROR_INTERNAL";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Accessory.class != obj.getClass()) {
            return false;
        }
        Accessory accessory = (Accessory) obj;
        return this.transport == accessory.transport && this.address.equals(accessory.address);
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public AccessoryTransport.Type getTransport() {
        return this.transport;
    }

    public boolean hasName() {
        return this.name != null;
    }

    public int hashCode() {
        return this.address.hashCode() + (this.transport.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Accessory{transport=");
        outline107.append(this.transport);
        outline107.append(", address='");
        GeneratedOutlineSupport1.outline176(outline107, this.address, Chars.QUOTE, ", name='");
        return GeneratedOutlineSupport1.outline90(outline107, this.name, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.address);
        parcel.writeInt(this.transport.ordinal());
        parcel.writeString(this.name);
    }

    public Accessory(String str, AccessoryTransport.Type type) {
        this(str, type, null);
    }

    Accessory(Parcel parcel) {
        this.address = parcel.readString();
        this.transport = AccessoryTransport.Type.values()[parcel.readInt()];
        this.name = parcel.readString();
    }
}
