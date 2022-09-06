package com.amazon.leaderselection;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class LeaderSelectionServiceVersion implements Parcelable {
    private static final String SEPARATOR = ".";
    private static final String SEPARATOR_REGEX = "\\.";
    private static final int UNKNOWN_VERSION_VALUE = 0;
    private final boolean isValid;
    private final int majorVersion;
    private final int minorVersion;
    private final int patchVersion;
    private static final String TAG = LeaderSelectionServiceVersion.class.getSimpleName();
    static final LeaderSelectionServiceVersion UNKNOWN = new LeaderSelectionServiceVersion("0.0.0");
    public static final Parcelable.Creator<LeaderSelectionServiceVersion> CREATOR = new a();

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<LeaderSelectionServiceVersion> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public LeaderSelectionServiceVersion mo4084createFromParcel(Parcel parcel) {
            return new LeaderSelectionServiceVersion(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public LeaderSelectionServiceVersion[] mo4085newArray(int i) {
            return new LeaderSelectionServiceVersion[i];
        }
    }

    private LeaderSelectionServiceVersion(Parcel parcel) {
        this.majorVersion = parcel.readInt();
        this.minorVersion = parcel.readInt();
        this.patchVersion = parcel.readInt();
        this.isValid = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
    }

    /* synthetic */ LeaderSelectionServiceVersion(Parcel parcel, a aVar) {
        this(parcel);
    }

    private LeaderSelectionServiceVersion(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z = false;
        String[] split = str != null ? str.split("\\.") : new String[0];
        if (split.length == 3) {
            try {
                i = Integer.valueOf(split[0]).intValue();
                try {
                    i2 = Integer.valueOf(split[1]).intValue();
                } catch (NumberFormatException unused) {
                    i2 = 0;
                }
            } catch (NumberFormatException unused2) {
                i = 0;
                i2 = 0;
            }
            try {
                int intValue = Integer.valueOf(split[2]).intValue();
                i3 = i;
                i4 = intValue;
                z = true;
            } catch (NumberFormatException unused3) {
                Log.e(TAG, "Failed to parse LeaderSelectionService version. Must be 'integer.integer.integer'.");
                i3 = i;
                i4 = 0;
                this.majorVersion = i3;
                this.minorVersion = i2;
                this.patchVersion = i4;
                this.isValid = z;
            }
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
        }
        this.majorVersion = i3;
        this.minorVersion = i2;
        this.patchVersion = i4;
        this.isValid = z;
    }

    private int compareTo(@NonNull LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        if (!isValid()) {
            return -1;
        }
        if (!leaderSelectionServiceVersion.isValid()) {
            return 1;
        }
        int i = this.majorVersion - leaderSelectionServiceVersion.majorVersion;
        int i2 = this.minorVersion - leaderSelectionServiceVersion.minorVersion;
        return i == 0 ? i2 == 0 ? this.patchVersion - leaderSelectionServiceVersion.patchVersion : i2 : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LeaderSelectionServiceVersion create(String str) {
        return new LeaderSelectionServiceVersion(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LeaderSelectionServiceVersion.class != obj.getClass()) {
            return false;
        }
        LeaderSelectionServiceVersion leaderSelectionServiceVersion = (LeaderSelectionServiceVersion) obj;
        return this.majorVersion == leaderSelectionServiceVersion.majorVersion && this.minorVersion == leaderSelectionServiceVersion.minorVersion && this.patchVersion == leaderSelectionServiceVersion.patchVersion;
    }

    int getMajorVersion() {
        return this.majorVersion;
    }

    int getMinorVersion() {
        return this.minorVersion;
    }

    int getPatchVersion() {
        return this.patchVersion;
    }

    String getValue() {
        return this.majorVersion + "." + this.minorVersion + "." + this.patchVersion;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion), Integer.valueOf(this.patchVersion));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHigherOrEquivalent(LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        return compareTo(leaderSelectionServiceVersion) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLowerOrEquivalentTo(LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        return compareTo(leaderSelectionServiceVersion) <= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSignificantlyHigherThan(LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        boolean z = this.majorVersion > leaderSelectionServiceVersion.getMajorVersion();
        boolean z2 = this.majorVersion == leaderSelectionServiceVersion.getMajorVersion();
        boolean z3 = this.minorVersion > leaderSelectionServiceVersion.getMinorVersion();
        if (!z) {
            return z2 && z3;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValid() {
        return this.isValid;
    }

    public String toString() {
        return getValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.majorVersion);
        parcel.writeInt(this.minorVersion);
        parcel.writeInt(this.patchVersion);
        parcel.writeValue(Boolean.valueOf(this.isValid));
    }
}
