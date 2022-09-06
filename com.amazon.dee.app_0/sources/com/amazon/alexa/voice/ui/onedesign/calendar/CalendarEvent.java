package com.amazon.alexa.voice.ui.onedesign.calendar;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class CalendarEvent implements CalendarEventModel {
    private final CharSequence dayOfMonth;
    private final CharSequence dayOfWeek;
    private final CharSequence id;
    private final CharSequence location;
    private final CharSequence month;
    private final CharSequence name;
    private final CharSequence provider;
    private final Object tag;
    private final CharSequence times;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence dayOfMonth;
        CharSequence dayOfWeek;
        CharSequence id;
        CharSequence location;
        CharSequence month;
        CharSequence name;
        CharSequence provider;
        Object tag;
        CharSequence times;

        public CalendarEvent build() {
            if (this.tag != null) {
                if (this.name != null) {
                    return new CalendarEvent(this);
                }
                throw new IllegalArgumentException("name == null");
            }
            throw new IllegalArgumentException("tag == null");
        }

        public Builder dayOfMonth(CharSequence charSequence) {
            this.dayOfMonth = charSequence;
            return this;
        }

        public Builder dayOfWeek(CharSequence charSequence) {
            this.dayOfWeek = charSequence;
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

        public Builder month(CharSequence charSequence) {
            this.month = charSequence;
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

        public Builder tag(@NonNull Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder times(CharSequence charSequence) {
            this.times = charSequence;
            return this;
        }
    }

    CalendarEvent(Builder builder) {
        this.tag = builder.tag;
        this.name = builder.name;
        this.dayOfWeek = builder.dayOfWeek;
        this.dayOfMonth = builder.dayOfMonth;
        this.month = builder.month;
        this.location = builder.location;
        this.times = builder.times;
        this.id = builder.id;
        this.provider = builder.provider;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CalendarEvent.class != obj.getClass()) {
            return false;
        }
        CalendarEvent calendarEvent = (CalendarEvent) obj;
        if (!this.tag.equals(calendarEvent.tag) || !this.name.equals(calendarEvent.name)) {
            return false;
        }
        CharSequence charSequence = this.dayOfWeek;
        if (charSequence == null ? calendarEvent.dayOfWeek != null : !charSequence.equals(calendarEvent.dayOfWeek)) {
            return false;
        }
        CharSequence charSequence2 = this.dayOfMonth;
        if (charSequence2 == null ? calendarEvent.dayOfMonth != null : !charSequence2.equals(calendarEvent.dayOfMonth)) {
            return false;
        }
        CharSequence charSequence3 = this.month;
        if (charSequence3 == null ? calendarEvent.month != null : !charSequence3.equals(calendarEvent.month)) {
            return false;
        }
        CharSequence charSequence4 = this.location;
        if (charSequence4 == null ? calendarEvent.location != null : !charSequence4.equals(calendarEvent.location)) {
            return false;
        }
        CharSequence charSequence5 = this.times;
        if (charSequence5 == null ? calendarEvent.times != null : !charSequence5.equals(calendarEvent.times)) {
            return false;
        }
        CharSequence charSequence6 = this.id;
        if (charSequence6 == null ? calendarEvent.id != null : !charSequence6.equals(calendarEvent.id)) {
            return false;
        }
        CharSequence charSequence7 = this.provider;
        CharSequence charSequence8 = calendarEvent.provider;
        return charSequence7 == null ? charSequence8 == null : charSequence7.equals(charSequence8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getDayOfMonth() {
        return this.dayOfMonth;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getDayOfWeek() {
        return this.dayOfWeek;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getLocation() {
        return this.location;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getMonth() {
        return this.month;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    @NonNull
    public CharSequence getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getProvider() {
        return this.provider;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    @NonNull
    public Object getTag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEventModel
    public CharSequence getTimes() {
        return this.times;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.name, (this.tag.hashCode() + JfifUtil.MARKER_EOI) * 31, 31);
        CharSequence charSequence = this.dayOfWeek;
        int i = 0;
        int hashCode = (outline5 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.dayOfMonth;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.month;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.location;
        int hashCode4 = (hashCode3 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31;
        CharSequence charSequence5 = this.times;
        int hashCode5 = (hashCode4 + (charSequence5 != null ? charSequence5.hashCode() : 0)) * 31;
        CharSequence charSequence6 = this.id;
        int hashCode6 = (hashCode5 + (charSequence6 != null ? charSequence6.hashCode() : 0)) * 31;
        CharSequence charSequence7 = this.provider;
        if (charSequence7 != null) {
            i = charSequence7.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CalendarEvent{tag=");
        outline107.append(this.tag);
        outline107.append(", name=");
        outline107.append((Object) this.name);
        outline107.append(", dayOfWeek=");
        outline107.append((Object) this.dayOfWeek);
        outline107.append(", dayOfMonth=");
        outline107.append((Object) this.dayOfMonth);
        outline107.append(", month=");
        outline107.append((Object) this.month);
        outline107.append(", location=");
        outline107.append((Object) this.location);
        outline107.append(", times=");
        outline107.append((Object) this.times);
        outline107.append(", id=");
        outline107.append((Object) this.id);
        outline107.append(", provider=");
        outline107.append((Object) this.provider);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
