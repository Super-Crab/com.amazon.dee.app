package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class CalendarCard implements CalendarCardModel {
    public static final Parcelable.Creator<CalendarCard> CREATOR = new Parcelable.Creator<CalendarCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CalendarCard mo2684createFromParcel(Parcel parcel) {
            return new CalendarCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CalendarCard[] mo2685newArray(int i) {
            return new CalendarCard[i];
        }
    };
    private final List<? extends CalendarCardModel.EventModel> eventList;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends CalendarCardModel.EventModel> eventList = new ArrayList();
        CharSequence title;

        public CalendarCard build() {
            if (this.eventList != null) {
                return new CalendarCard(this);
            }
            throw new IllegalArgumentException("eventList == null");
        }

        public Builder eventList(@NonNull List<? extends CalendarCardModel.EventModel> list) {
            this.eventList = list;
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    CalendarCard(Builder builder) {
        this.title = builder.title;
        this.eventList = builder.eventList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CalendarCard.class != obj.getClass()) {
            return false;
        }
        CalendarCard calendarCard = (CalendarCard) obj;
        CharSequence charSequence = this.title;
        if (charSequence == null ? calendarCard.title != null : !charSequence.equals(calendarCard.title)) {
            return false;
        }
        return this.eventList.equals(calendarCard.eventList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel
    @NonNull
    public List<? extends CalendarCardModel.EventModel> getEventList() {
        return this.eventList;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        return this.eventList.hashCode() + ((JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CalendarCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", eventList=");
        return GeneratedOutlineSupport1.outline94(outline107, this.eventList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeTypedList(this.eventList);
    }

    CalendarCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.eventList = parcel.createTypedArrayList(Event.CREATOR);
    }

    /* loaded from: classes11.dex */
    public static final class Event implements CalendarCardModel.EventModel {
        public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard.Event.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Event mo2686createFromParcel(Parcel parcel) {
                return new Event(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Event[] mo2687newArray(int i) {
                return new Event[i];
            }
        };
        private final boolean allDay;
        private final long endTime;
        private final CharSequence id;
        private final CharSequence location;
        private final boolean multiDay;
        private final CharSequence name;
        private final CharSequence provider;
        private final long startTime;

        /* loaded from: classes11.dex */
        public static final class Builder {
            boolean allDay;
            long endTime;
            CharSequence id;
            CharSequence location;
            boolean multiDay;
            CharSequence name;
            CharSequence provider;
            long startTime;

            public Builder allDay(boolean z) {
                this.allDay = z;
                return this;
            }

            public Event build() {
                if (this.name != null) {
                    return new Event(this);
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder endTime(long j) {
                this.endTime = j;
                return this;
            }

            public Builder id(CharSequence charSequence) {
                this.id = charSequence;
                return this;
            }

            public Builder location(CharSequence charSequence) {
                this.location = charSequence;
                return this;
            }

            public Builder multiDay(boolean z) {
                this.multiDay = z;
                return this;
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }

            public Builder provider(CharSequence charSequence) {
                this.provider = charSequence;
                return this;
            }

            public Builder startTime(long j) {
                this.startTime = j;
                return this;
            }
        }

        Event(Builder builder) {
            this.name = builder.name;
            this.startTime = builder.startTime;
            this.endTime = builder.endTime;
            this.allDay = builder.allDay;
            this.multiDay = builder.multiDay;
            this.location = builder.location;
            this.id = builder.id;
            this.provider = builder.provider;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Event.class != obj.getClass()) {
                return false;
            }
            Event event = (Event) obj;
            if (!this.name.equals(event.name) || this.startTime != event.startTime || this.endTime != event.endTime || this.allDay != event.allDay || this.multiDay != event.multiDay) {
                return false;
            }
            CharSequence charSequence = this.location;
            if (charSequence == null ? event.location != null : !charSequence.equals(event.location)) {
                return false;
            }
            CharSequence charSequence2 = this.id;
            if (charSequence2 == null ? event.id != null : !charSequence2.equals(event.id)) {
                return false;
            }
            CharSequence charSequence3 = this.provider;
            CharSequence charSequence4 = event.provider;
            return charSequence3 == null ? charSequence4 == null : charSequence3.equals(charSequence4);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public boolean getAllDay() {
            return this.allDay;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public long getEndTime() {
            return this.endTime;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public CharSequence getId() {
            return this.id;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public CharSequence getLocation() {
            return this.location;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public boolean getMultiDay() {
            return this.multiDay;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public CharSequence getProvider() {
            return this.provider;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel.EventModel
        public long getStartTime() {
            return this.startTime;
        }

        public int hashCode() {
            int outline5 = GeneratedOutlineSupport1.outline5(this.name, JfifUtil.MARKER_EOI, 31);
            long j = this.startTime;
            long j2 = this.endTime;
            int i = (((((((outline5 + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.allDay ? 1 : 0)) * 31) + (this.multiDay ? 1 : 0)) * 31;
            CharSequence charSequence = this.location;
            int i2 = 0;
            int hashCode = (i + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
            CharSequence charSequence2 = this.id;
            int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
            CharSequence charSequence3 = this.provider;
            if (charSequence3 != null) {
                i2 = charSequence3.hashCode();
            }
            return hashCode2 + i2;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event{name=");
            outline107.append((Object) this.name);
            outline107.append(", startTime=");
            outline107.append(this.startTime);
            outline107.append(", endTime=");
            outline107.append(this.endTime);
            outline107.append(", allDay=");
            outline107.append(this.allDay);
            outline107.append(", multiDay=");
            outline107.append(this.multiDay);
            outline107.append(", location=");
            outline107.append((Object) this.location);
            outline107.append(", id=");
            outline107.append((Object) this.id);
            outline107.append(", provider=");
            outline107.append((Object) this.provider);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
            parcel.writeLong(this.startTime);
            parcel.writeLong(this.endTime);
            parcel.writeByte(this.allDay ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.multiDay ? (byte) 1 : (byte) 0);
            TextUtils.writeToParcel(this.location, parcel, i);
            TextUtils.writeToParcel(this.id, parcel, i);
            TextUtils.writeToParcel(this.provider, parcel, i);
        }

        Event(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.startTime = parcel.readLong();
            this.endTime = parcel.readLong();
            boolean z = true;
            this.allDay = parcel.readByte() != 0;
            this.multiDay = parcel.readByte() == 0 ? false : z;
            this.location = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.id = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.provider = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }
}
