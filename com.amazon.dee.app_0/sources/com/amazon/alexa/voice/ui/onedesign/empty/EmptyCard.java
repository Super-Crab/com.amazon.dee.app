package com.amazon.alexa.voice.ui.onedesign.empty;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class EmptyCard implements EmptyCardModel {
    public static final Parcelable.Creator<EmptyCard> CREATOR = new Parcelable.Creator<EmptyCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.EmptyCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public EmptyCard mo2689createFromParcel(Parcel parcel) {
            return new EmptyCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public EmptyCard[] mo2690newArray(int i) {
            return new EmptyCard[i];
        }
    };
    private final String manageButtonDestination;
    private final CharSequence title;
    private final String type;

    public EmptyCard(@NonNull String str, CharSequence charSequence, String str2) {
        this.type = str;
        this.title = charSequence;
        this.manageButtonDestination = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EmptyCard.class != obj.getClass()) {
            return false;
        }
        EmptyCard emptyCard = (EmptyCard) obj;
        if (!this.type.equals(emptyCard.type)) {
            return false;
        }
        CharSequence charSequence = this.title;
        if (charSequence == null ? emptyCard.title != null : !charSequence.equals(emptyCard.title)) {
            return false;
        }
        String str = this.manageButtonDestination;
        String str2 = emptyCard.manageButtonDestination;
        return str == null ? str2 == null : str.equals(str2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyCardModel
    @Nullable
    public String getManageButtonDestination() {
        return this.manageButtonDestination;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyCardModel
    @Nullable
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyCardModel
    @NonNull
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        int outline7 = GeneratedOutlineSupport1.outline7(this.type, JfifUtil.MARKER_EOI, 31);
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (outline7 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        String str = this.manageButtonDestination;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EmptyCard{type=");
        outline107.append(this.type);
        outline107.append(", title=");
        outline107.append((Object) this.title);
        outline107.append(", manageButtonDestination=");
        return GeneratedOutlineSupport1.outline89(outline107, this.manageButtonDestination, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeString(this.manageButtonDestination);
    }

    EmptyCard(Parcel parcel) {
        this.type = parcel.readString();
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.manageButtonDestination = parcel.readString();
    }
}
