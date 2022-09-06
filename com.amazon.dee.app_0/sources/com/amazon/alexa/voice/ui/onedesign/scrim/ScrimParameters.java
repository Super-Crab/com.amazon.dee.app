package com.amazon.alexa.voice.ui.onedesign.scrim;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class ScrimParameters implements ScrimParametersModel {
    public static final Parcelable.Creator<ScrimParameters> CREATOR = new Parcelable.Creator<ScrimParameters>() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ScrimParameters mo2724createFromParcel(Parcel parcel) {
            return new ScrimParameters(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ScrimParameters[] mo2725newArray(int i) {
            return new ScrimParameters[i];
        }
    };
    private final boolean hideCancelButton;
    private final String hint;
    private final boolean showHint;
    private final boolean showTTAIngress;
    private final boolean transparentBackground;

    /* loaded from: classes11.dex */
    public static final class Builder {
        boolean hideCancelButton;
        String hint;
        boolean showHint;
        boolean showTTAIngress;
        boolean transparentBackground;

        public ScrimParameters build() {
            return new ScrimParameters(this);
        }

        public Builder hideCancelButton(boolean z) {
            this.hideCancelButton = z;
            return this;
        }

        public Builder hint(String str) {
            this.hint = str;
            return this;
        }

        public Builder showHint(boolean z) {
            this.showHint = z;
            return this;
        }

        public Builder showTTAIngress(boolean z) {
            this.showTTAIngress = z;
            return this;
        }

        public Builder transparentBackground(boolean z) {
            this.transparentBackground = z;
            return this;
        }
    }

    ScrimParameters(Builder builder) {
        this.hint = builder.hint;
        this.showHint = builder.showHint;
        this.transparentBackground = builder.transparentBackground;
        this.hideCancelButton = builder.hideCancelButton;
        this.showTTAIngress = builder.showTTAIngress;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ScrimParameters.class != obj.getClass()) {
            return false;
        }
        ScrimParameters scrimParameters = (ScrimParameters) obj;
        String str = this.hint;
        if (str == null ? scrimParameters.hint != null : !str.equals(scrimParameters.hint)) {
            return false;
        }
        return this.showHint == scrimParameters.showHint && this.transparentBackground == scrimParameters.transparentBackground && this.hideCancelButton == scrimParameters.hideCancelButton && this.showTTAIngress == scrimParameters.showTTAIngress;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel
    public boolean getHideCancelButton() {
        return this.hideCancelButton;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel
    @Nullable
    public String getHint() {
        return this.hint;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel
    public boolean getShowHint() {
        return this.showHint;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel
    public boolean getShowTTAIngress() {
        return this.showTTAIngress;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel
    public boolean getTransparentBackground() {
        return this.transparentBackground;
    }

    public int hashCode() {
        String str = this.hint;
        return ((((((((JfifUtil.MARKER_EOI + (str != null ? str.hashCode() : 0)) * 31) + (this.showHint ? 1 : 0)) * 31) + (this.transparentBackground ? 1 : 0)) * 31) + (this.hideCancelButton ? 1 : 0)) * 31) + (this.showTTAIngress ? 1 : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ScrimParameters{hint=");
        outline107.append(this.hint);
        outline107.append(", showHint=");
        outline107.append(this.showHint);
        outline107.append(", transparentBackground=");
        outline107.append(this.transparentBackground);
        outline107.append(", hideCancelButton=");
        outline107.append(this.hideCancelButton);
        outline107.append(", showTTAIngress=");
        outline107.append(this.showTTAIngress);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hint);
        parcel.writeByte(this.showHint ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.transparentBackground ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.hideCancelButton ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showTTAIngress ? (byte) 1 : (byte) 0);
    }

    ScrimParameters(Parcel parcel) {
        this.hint = parcel.readString();
        boolean z = true;
        this.showHint = parcel.readByte() != 0;
        this.transparentBackground = parcel.readByte() != 0;
        this.hideCancelButton = parcel.readByte() != 0;
        this.showTTAIngress = parcel.readByte() == 0 ? false : z;
    }
}
