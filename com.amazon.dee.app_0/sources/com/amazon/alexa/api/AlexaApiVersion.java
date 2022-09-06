package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Comparator;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlexaApiVersion implements Parcelable, Comparable<AlexaApiVersion> {
    private static final String SEPARATOR = ".";
    private static final String SEPARATOR_REGEX = "\\.";
    private static final int UNKNOWN_VERSION_VALUE = 0;
    private final int majorVersion;
    private final int minorVersion;
    private final int patchVersion;
    private static final String TAG = AlexaApiVersion.class.getSimpleName();
    public static final Parcelable.Creator<AlexaApiVersion> CREATOR = new Parcelable.Creator<AlexaApiVersion>() { // from class: com.amazon.alexa.api.AlexaApiVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaApiVersion mo760createFromParcel(Parcel parcel) {
            return new AlexaApiVersion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaApiVersion[] mo761newArray(int i) {
            return new AlexaApiVersion[i];
        }
    };

    /* loaded from: classes6.dex */
    public static class DescendingComparator implements Comparator<AlexaApiVersion> {
        @Override // java.util.Comparator
        public int compare(AlexaApiVersion alexaApiVersion, AlexaApiVersion alexaApiVersion2) {
            return alexaApiVersion2.compareTo(alexaApiVersion);
        }
    }

    protected AlexaApiVersion(Parcel parcel) {
        this.majorVersion = parcel.readInt();
        this.minorVersion = parcel.readInt();
        this.patchVersion = parcel.readInt();
    }

    private AlexaApiVersion(String str) {
        int i;
        int i2;
        int i3 = 0;
        String[] split = str != null ? str.split("\\.") : new String[0];
        if (split.length == 3) {
            try {
                i = Integer.valueOf(split[0]).intValue();
                try {
                    i2 = Integer.valueOf(split[1]).intValue();
                    try {
                        i3 = Integer.valueOf(split[2]).intValue();
                    } catch (NumberFormatException unused) {
                        Log.e(TAG, "Failed to parse AlexaApiVersion version. Must be 'integer.integer.integer'.");
                        this.majorVersion = i;
                        this.minorVersion = i2;
                        this.patchVersion = i3;
                    }
                } catch (NumberFormatException unused2) {
                    i2 = 0;
                }
            } catch (NumberFormatException unused3) {
                i = 0;
                i2 = 0;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        this.majorVersion = i;
        this.minorVersion = i2;
        this.patchVersion = i3;
    }

    public static AlexaApiVersion create(String str) {
        return new AlexaApiVersion(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(AlexaApiVersion alexaApiVersion) {
        int i = this.majorVersion - alexaApiVersion.majorVersion;
        int i2 = this.minorVersion - alexaApiVersion.minorVersion;
        return i == 0 ? i2 == 0 ? this.patchVersion - alexaApiVersion.patchVersion : i2 : i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AlexaApiVersion.class != obj.getClass()) {
            return false;
        }
        AlexaApiVersion alexaApiVersion = (AlexaApiVersion) obj;
        return this.majorVersion == alexaApiVersion.majorVersion && this.minorVersion == alexaApiVersion.minorVersion && this.patchVersion == alexaApiVersion.patchVersion;
    }

    public String getValue() {
        return this.majorVersion + "." + this.minorVersion + "." + this.patchVersion;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion), Integer.valueOf(this.patchVersion));
    }

    public boolean isAtLeast(AlexaApiVersion alexaApiVersion) {
        return alexaApiVersion != null && compareTo(alexaApiVersion) >= 0;
    }

    public String toString() {
        return getValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.majorVersion);
        parcel.writeInt(this.minorVersion);
        parcel.writeInt(this.patchVersion);
    }
}
