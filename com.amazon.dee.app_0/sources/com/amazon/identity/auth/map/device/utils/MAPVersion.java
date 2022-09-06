package com.amazon.identity.auth.map.device.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.MAPVersionHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidParameterException;
/* loaded from: classes12.dex */
public final class MAPVersion implements Parcelable {
    private static final String VERSION_SEPERATOR = "\\.";
    private final int[] mVersion;
    private static final String LOG_TAG = MAPVersion.class.getName();
    public static final MAPVersion VERSION_ZERO = new MAPVersion("0.0.0");
    public static final Parcelable.Creator<MAPVersion> CREATOR = new Parcelable.Creator<MAPVersion>() { // from class: com.amazon.identity.auth.map.device.utils.MAPVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public MAPVersion mo4074createFromParcel(Parcel parcel) {
            return new MAPVersion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public MAPVersion[] mo4075newArray(int i) {
            return new MAPVersion[i];
        }
    };

    public MAPVersion(Parcel parcel) {
        this.mVersion = new int[parcel.readInt()];
        parcel.readIntArray(this.mVersion);
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MAPVersion Created from PARCEL: ");
        outline107.append(toString());
        MAPLog.i(str, outline107.toString());
    }

    public int compare(MAPVersion mAPVersion) {
        try {
            int[] versionInfo = mAPVersion.getVersionInfo();
            int min = Math.min(this.mVersion.length, mAPVersion.getVersionInfo().length) - 1;
            int i = 0;
            while (i < min && this.mVersion[i] == versionInfo[i]) {
                i++;
            }
            Integer valueOf = Integer.valueOf(this.mVersion[i]);
            Integer valueOf2 = Integer.valueOf(versionInfo[i]);
            if (i == this.mVersion.length && this.mVersion.length == mAPVersion.getVersionInfo().length) {
                return 0;
            }
            if (versionInfo.length != this.mVersion.length && valueOf.equals(valueOf2)) {
                return Integer.valueOf(this.mVersion.length).compareTo(Integer.valueOf(versionInfo.length));
            }
            return valueOf.compareTo(valueOf2);
        } catch (ArrayIndexOutOfBoundsException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("1=");
            outline107.append(toString());
            outline107.append(" vs 2=");
            outline107.append(mAPVersion.toString());
            outline107.append(" ");
            outline107.append(e.getMessage());
            throw new ArrayIndexOutOfBoundsException(outline107.toString());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int[] getVersionInfo() {
        return this.mVersion;
    }

    public String toString() {
        return MAPVersionHelper.getVersionAsString(this.mVersion);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MAPVersion writing ");
        outline107.append(this.mVersion.length);
        outline107.append(" ints to parcel");
        MAPLog.i(str, outline107.toString());
        parcel.writeInt(this.mVersion.length);
        parcel.writeIntArray(this.mVersion);
    }

    public MAPVersion(int[] iArr) {
        if (iArr != null) {
            if (iArr.length != 0) {
                this.mVersion = iArr;
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MAPVersion Created : ");
                outline107.append(toString());
                MAPLog.i(str, outline107.toString());
                return;
            }
            throw new InvalidParameterException("Version must not be empty");
        }
        throw new InvalidParameterException("version must not be null");
    }

    public MAPVersion(String str) {
        MAPLog.i(LOG_TAG, "MAPVersion from String : " + str);
        if (str != null) {
            String[] split = TextUtils.split(str, "\\.");
            this.mVersion = new int[split.length];
            int i = 0;
            for (String str2 : split) {
                try {
                    this.mVersion[i] = Integer.parseInt(str2);
                } catch (NumberFormatException unused) {
                    this.mVersion[i] = 0;
                }
                i++;
            }
            return;
        }
        throw new InvalidParameterException("version must not be null");
    }
}
