package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class TrafficCard implements TrafficCardModel {
    public static final Parcelable.Creator<TrafficCard> CREATOR = new Parcelable.Creator<TrafficCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TrafficCard mo2751createFromParcel(Parcel parcel) {
            return new TrafficCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TrafficCard[] mo2752newArray(int i) {
            return new TrafficCard[i];
        }
    };
    private final List<? extends TrafficCardModel.RouteModel> routeList;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends TrafficCardModel.RouteModel> routeList = new ArrayList();

        public TrafficCard build() {
            if (this.routeList != null) {
                return new TrafficCard(this);
            }
            throw new IllegalArgumentException("routeList == null");
        }

        public Builder routeList(@NonNull List<? extends TrafficCardModel.RouteModel> list) {
            this.routeList = list;
            return this;
        }
    }

    TrafficCard(Builder builder) {
        this.routeList = builder.routeList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && TrafficCard.class == obj.getClass() && this.routeList.equals(((TrafficCard) obj).routeList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel
    @NonNull
    public List<? extends TrafficCardModel.RouteModel> getRouteList() {
        return this.routeList;
    }

    public int hashCode() {
        return this.routeList.hashCode() + JfifUtil.MARKER_EOI;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline94(GeneratedOutlineSupport1.outline107("TrafficCard{routeList="), this.routeList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.routeList);
    }

    TrafficCard(Parcel parcel) {
        this.routeList = parcel.createTypedArrayList(Route.CREATOR);
    }

    /* loaded from: classes11.dex */
    public static final class Route implements TrafficCardModel.RouteModel {
        public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCard.Route.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Route mo2753createFromParcel(Parcel parcel) {
                return new Route(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Route[] mo2754newArray(int i) {
                return new Route[i];
            }
        };
        private final int condition;
        private final CharSequence distance;
        private final List<CharSequence> streetList;
        private final CharSequence timeToDestination;

        /* loaded from: classes11.dex */
        public static final class Builder {
            int condition;
            CharSequence distance;
            List<CharSequence> streetList = new ArrayList();
            CharSequence timeToDestination;

            public Route build() {
                if (this.timeToDestination != null) {
                    if (this.distance != null) {
                        if (this.streetList != null) {
                            return new Route(this);
                        }
                        throw new IllegalArgumentException("streetList == null");
                    }
                    throw new IllegalArgumentException("distance == null");
                }
                throw new IllegalArgumentException("timeToDestination == null");
            }

            public Builder condition(int i) {
                this.condition = i;
                return this;
            }

            public Builder distance(@NonNull CharSequence charSequence) {
                this.distance = charSequence;
                return this;
            }

            public Builder streetList(@NonNull List<CharSequence> list) {
                this.streetList = list;
                return this;
            }

            public Builder timeToDestination(@NonNull CharSequence charSequence) {
                this.timeToDestination = charSequence;
                return this;
            }
        }

        Route(Builder builder) {
            this.timeToDestination = builder.timeToDestination;
            this.distance = builder.distance;
            this.streetList = builder.streetList;
            this.condition = builder.condition;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Route.class != obj.getClass()) {
                return false;
            }
            Route route = (Route) obj;
            return this.timeToDestination.equals(route.timeToDestination) && this.distance.equals(route.distance) && this.streetList.equals(route.streetList) && this.condition == route.condition;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel.RouteModel
        public int getCondition() {
            return this.condition;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel.RouteModel
        @NonNull
        public CharSequence getDistance() {
            return this.distance;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel.RouteModel
        @NonNull
        public List<CharSequence> getStreetList() {
            return this.streetList;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel.RouteModel
        @NonNull
        public CharSequence getTimeToDestination() {
            return this.timeToDestination;
        }

        public int hashCode() {
            return ((this.streetList.hashCode() + GeneratedOutlineSupport1.outline5(this.distance, GeneratedOutlineSupport1.outline5(this.timeToDestination, JfifUtil.MARKER_EOI, 31), 31)) * 31) + this.condition;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Route{timeToDestination=");
            outline107.append((Object) this.timeToDestination);
            outline107.append(", distance=");
            outline107.append((Object) this.distance);
            outline107.append(", streetList=");
            outline107.append(this.streetList);
            outline107.append(", condition=");
            return GeneratedOutlineSupport1.outline85(outline107, this.condition, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.timeToDestination, parcel, i);
            TextUtils.writeToParcel(this.distance, parcel, i);
            List<CharSequence> list = this.streetList;
            if (list != null) {
                parcel.writeInt(list.size());
                for (CharSequence charSequence : this.streetList) {
                    TextUtils.writeToParcel(charSequence, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            parcel.writeInt(this.condition);
        }

        /* JADX WARN: Multi-variable type inference failed */
        Route(Parcel parcel) {
            this.timeToDestination = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.distance = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            this.streetList = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                this.streetList.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            this.condition = parcel.readInt();
        }
    }
}
