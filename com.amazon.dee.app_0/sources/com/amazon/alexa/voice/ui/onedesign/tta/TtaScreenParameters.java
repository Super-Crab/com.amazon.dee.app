package com.amazon.alexa.voice.ui.onedesign.tta;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class TtaScreenParameters implements TtaScreenParametersModel {
    public static final Parcelable.Creator<TtaScreenParameters> CREATOR = new Parcelable.Creator<TtaScreenParameters>() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TtaScreenParameters mo2771createFromParcel(Parcel parcel) {
            return new TtaScreenParameters(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TtaScreenParameters[] mo2772newArray(int i) {
            return new TtaScreenParameters[i];
        }
    };
    private final boolean appSearchEnabled;
    private final String hintText;

    /* loaded from: classes11.dex */
    public static final class Builder {
        boolean appSearchEnabled;
        String hintText;

        public Builder appSearchEnabled(boolean z) {
            this.appSearchEnabled = z;
            return this;
        }

        public TtaScreenParameters build() {
            return new TtaScreenParameters(this);
        }

        public Builder hintText(String str) {
            this.hintText = str;
            return this;
        }
    }

    TtaScreenParameters(Builder builder) {
        this.hintText = builder.hintText;
        this.appSearchEnabled = builder.appSearchEnabled;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TtaScreenParameters.class != obj.getClass()) {
            return false;
        }
        TtaScreenParameters ttaScreenParameters = (TtaScreenParameters) obj;
        String str = this.hintText;
        if (str == null ? ttaScreenParameters.hintText != null : !str.equals(ttaScreenParameters.hintText)) {
            return false;
        }
        return this.appSearchEnabled == ttaScreenParameters.appSearchEnabled;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParametersModel
    public boolean getAppSearchEnabled() {
        return this.appSearchEnabled;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParametersModel
    @Nullable
    public String getHintText() {
        return this.hintText;
    }

    public int hashCode() {
        String str = this.hintText;
        return ((JfifUtil.MARKER_EOI + (str != null ? str.hashCode() : 0)) * 31) + (this.appSearchEnabled ? 1 : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TtaScreenParameters{hintText=");
        outline107.append(this.hintText);
        outline107.append(", appSearchEnabled=");
        outline107.append(this.appSearchEnabled);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hintText);
        parcel.writeByte(this.appSearchEnabled ? (byte) 1 : (byte) 0);
    }

    TtaScreenParameters(Parcel parcel) {
        this.hintText = parcel.readString();
        this.appSearchEnabled = parcel.readByte() != 0;
    }
}
