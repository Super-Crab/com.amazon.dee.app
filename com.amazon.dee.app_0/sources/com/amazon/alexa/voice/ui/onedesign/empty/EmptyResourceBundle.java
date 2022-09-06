package com.amazon.alexa.voice.ui.onedesign.empty;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class EmptyResourceBundle implements EmptyResourceBundleModel {
    public static final Parcelable.Creator<EmptyResourceBundle> CREATOR = new Parcelable.Creator<EmptyResourceBundle>() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public EmptyResourceBundle mo2691createFromParcel(Parcel parcel) {
            return new EmptyResourceBundle(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public EmptyResourceBundle[] mo2692newArray(int i) {
            return new EmptyResourceBundle[i];
        }
    };
    private final int contentResourceId;
    private final int iconResourceId;
    private final int titleResourceId;
    private final int userHintResourceId;

    public EmptyResourceBundle(int i, int i2, int i3, int i4) {
        this.titleResourceId = i;
        this.iconResourceId = i2;
        this.contentResourceId = i3;
        this.userHintResourceId = i4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EmptyResourceBundle.class != obj.getClass()) {
            return false;
        }
        EmptyResourceBundle emptyResourceBundle = (EmptyResourceBundle) obj;
        return this.titleResourceId == emptyResourceBundle.titleResourceId && this.iconResourceId == emptyResourceBundle.iconResourceId && this.contentResourceId == emptyResourceBundle.contentResourceId && this.userHintResourceId == emptyResourceBundle.userHintResourceId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundleModel
    public int getContentResourceId() {
        return this.contentResourceId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundleModel
    public int getIconResourceId() {
        return this.iconResourceId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundleModel
    public int getTitleResourceId() {
        return this.titleResourceId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundleModel
    public int getUserHintResourceId() {
        return this.userHintResourceId;
    }

    public int hashCode() {
        return ((((((JfifUtil.MARKER_EOI + this.titleResourceId) * 31) + this.iconResourceId) * 31) + this.contentResourceId) * 31) + this.userHintResourceId;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EmptyResourceBundle{titleResourceId=");
        outline107.append(this.titleResourceId);
        outline107.append(", iconResourceId=");
        outline107.append(this.iconResourceId);
        outline107.append(", contentResourceId=");
        outline107.append(this.contentResourceId);
        outline107.append(", userHintResourceId=");
        return GeneratedOutlineSupport1.outline85(outline107, this.userHintResourceId, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.titleResourceId);
        parcel.writeInt(this.iconResourceId);
        parcel.writeInt(this.contentResourceId);
        parcel.writeInt(this.userHintResourceId);
    }

    EmptyResourceBundle(Parcel parcel) {
        this.titleResourceId = parcel.readInt();
        this.iconResourceId = parcel.readInt();
        this.contentResourceId = parcel.readInt();
        this.userHintResourceId = parcel.readInt();
    }
}
