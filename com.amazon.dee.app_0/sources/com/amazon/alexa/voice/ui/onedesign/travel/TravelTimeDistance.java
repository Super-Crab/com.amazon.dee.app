package com.amazon.alexa.voice.ui.onedesign.travel;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class TravelTimeDistance implements TravelTimeDistanceModel {
    public static final Parcelable.Creator<TravelTimeDistance> CREATOR = new Parcelable.Creator<TravelTimeDistance>() { // from class: com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistance.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TravelTimeDistance mo2768createFromParcel(Parcel parcel) {
            return new TravelTimeDistance(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TravelTimeDistance[] mo2769newArray(int i) {
            return new TravelTimeDistance[i];
        }
    };
    private final int condition;
    private final CharSequence destination;
    private final CharSequence distanceToDestination;
    private final CharSequence origin;
    private final List<CharSequence> roadSegments;
    private final CharSequence timeToDestination;
    private final int travelCardType;

    /* loaded from: classes11.dex */
    public static final class Builder {
        int condition;
        CharSequence destination;
        CharSequence distanceToDestination;
        CharSequence origin;
        List<CharSequence> roadSegments = new ArrayList();
        CharSequence timeToDestination;
        int travelCardType;

        public TravelTimeDistance build() {
            if (this.timeToDestination != null) {
                if (this.distanceToDestination != null) {
                    if (this.origin != null) {
                        if (this.destination != null) {
                            if (this.roadSegments != null) {
                                return new TravelTimeDistance(this);
                            }
                            throw new IllegalArgumentException("roadSegments == null");
                        }
                        throw new IllegalArgumentException("destination == null");
                    }
                    throw new IllegalArgumentException("origin == null");
                }
                throw new IllegalArgumentException("distanceToDestination == null");
            }
            throw new IllegalArgumentException("timeToDestination == null");
        }

        public Builder condition(int i) {
            this.condition = i;
            return this;
        }

        public Builder destination(@NonNull CharSequence charSequence) {
            this.destination = charSequence;
            return this;
        }

        public Builder distanceToDestination(@NonNull CharSequence charSequence) {
            this.distanceToDestination = charSequence;
            return this;
        }

        public Builder origin(@NonNull CharSequence charSequence) {
            this.origin = charSequence;
            return this;
        }

        public Builder roadSegments(@NonNull List<CharSequence> list) {
            this.roadSegments = list;
            return this;
        }

        public Builder timeToDestination(@NonNull CharSequence charSequence) {
            this.timeToDestination = charSequence;
            return this;
        }

        public Builder travelCardType(int i) {
            this.travelCardType = i;
            return this;
        }
    }

    TravelTimeDistance(Builder builder) {
        this.travelCardType = builder.travelCardType;
        this.timeToDestination = builder.timeToDestination;
        this.distanceToDestination = builder.distanceToDestination;
        this.condition = builder.condition;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.roadSegments = builder.roadSegments;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TravelTimeDistance.class != obj.getClass()) {
            return false;
        }
        TravelTimeDistance travelTimeDistance = (TravelTimeDistance) obj;
        return this.travelCardType == travelTimeDistance.travelCardType && this.timeToDestination.equals(travelTimeDistance.timeToDestination) && this.distanceToDestination.equals(travelTimeDistance.distanceToDestination) && this.condition == travelTimeDistance.condition && this.origin.equals(travelTimeDistance.origin) && this.destination.equals(travelTimeDistance.destination) && this.roadSegments.equals(travelTimeDistance.roadSegments);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    public int getCondition() {
        return this.condition;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    @NonNull
    public CharSequence getDestination() {
        return this.destination;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    @NonNull
    public CharSequence getDistanceToDestination() {
        return this.distanceToDestination;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    @NonNull
    public CharSequence getOrigin() {
        return this.origin;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    @NonNull
    public List<CharSequence> getRoadSegments() {
        return this.roadSegments;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    @NonNull
    public CharSequence getTimeToDestination() {
        return this.timeToDestination;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceModel
    public int getTravelCardType() {
        return this.travelCardType;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.timeToDestination, (this.travelCardType + JfifUtil.MARKER_EOI) * 31, 31);
        return this.roadSegments.hashCode() + GeneratedOutlineSupport1.outline5(this.destination, GeneratedOutlineSupport1.outline5(this.origin, (GeneratedOutlineSupport1.outline5(this.distanceToDestination, outline5, 31) + this.condition) * 31, 31), 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TravelTimeDistance{travelCardType=");
        outline107.append(this.travelCardType);
        outline107.append(", timeToDestination=");
        outline107.append((Object) this.timeToDestination);
        outline107.append(", distanceToDestination=");
        outline107.append((Object) this.distanceToDestination);
        outline107.append(", condition=");
        outline107.append(this.condition);
        outline107.append(", origin=");
        outline107.append((Object) this.origin);
        outline107.append(", destination=");
        outline107.append((Object) this.destination);
        outline107.append(", roadSegments=");
        return GeneratedOutlineSupport1.outline94(outline107, this.roadSegments, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.travelCardType);
        TextUtils.writeToParcel(this.timeToDestination, parcel, i);
        TextUtils.writeToParcel(this.distanceToDestination, parcel, i);
        parcel.writeInt(this.condition);
        TextUtils.writeToParcel(this.origin, parcel, i);
        TextUtils.writeToParcel(this.destination, parcel, i);
        List<CharSequence> list = this.roadSegments;
        if (list != null) {
            parcel.writeInt(list.size());
            for (CharSequence charSequence : this.roadSegments) {
                TextUtils.writeToParcel(charSequence, parcel, i);
            }
            return;
        }
        parcel.writeInt(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    TravelTimeDistance(Parcel parcel) {
        this.travelCardType = parcel.readInt();
        this.timeToDestination = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.distanceToDestination = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.condition = parcel.readInt();
        this.origin = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.destination = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        int readInt = parcel.readInt();
        this.roadSegments = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.roadSegments.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
        }
    }
}
