package amazon.speech.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
/* loaded from: classes.dex */
public class EventEnvelope implements Parcelable {
    public static final Parcelable.Creator<EventEnvelope> CREATOR = new Parcelable.Creator<EventEnvelope>() { // from class: amazon.speech.model.EventEnvelope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public EventEnvelope mo29createFromParcel(Parcel parcel) {
            return new EventEnvelope(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public EventEnvelope[] mo30newArray(int i) {
            return new EventEnvelope[i];
        }
    };
    public static final String EVENT_URI_KEY = "EVENT_URI_KEY";
    private final Event mEvent;
    private final Bundle mExtra;

    public EventEnvelope(Event event, Bundle bundle) {
        if (event != null && bundle != null) {
            this.mEvent = event;
            this.mExtra = bundle;
            return;
        }
        throw new IllegalArgumentException();
    }

    private String getExtraString(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String str : this.mExtra.keySet()) {
            Object obj = this.mExtra.get(str);
            sb.append(str + Config.Compare.EQUAL_TO + obj + ",");
        }
        return sb.toString();
    }

    private int getHashcode(Bundle bundle) {
        int i = 1;
        for (String str : this.mExtra.keySet()) {
            Object obj = this.mExtra.get(str);
            i = (i * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EventEnvelope.class != obj.getClass()) {
            return false;
        }
        EventEnvelope eventEnvelope = (EventEnvelope) obj;
        if (!this.mEvent.equals(eventEnvelope.getEvent())) {
            return false;
        }
        Set<String> keySet = this.mExtra.keySet();
        if (keySet.size() != eventEnvelope.mExtra.keySet().size()) {
            return false;
        }
        for (String str : keySet) {
            Object obj2 = this.mExtra.get(str);
            Object obj3 = eventEnvelope.mExtra.get(str);
            if (obj2 == null && obj3 != null) {
                return false;
            }
            if (obj2 != null && !obj2.equals(obj3)) {
                return false;
            }
        }
        return true;
    }

    public Event getEvent() {
        return this.mEvent;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    public int hashCode() {
        return ((this.mEvent.hashCode() + 31) * 31) + getHashcode(this.mExtra);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventEnvelope{[");
        outline107.append(this.mEvent.getNamespace());
        outline107.append(".");
        outline107.append(this.mEvent.getName());
        outline107.append(".");
        outline107.append(this.mEvent.getLabel());
        outline107.append(".");
        outline107.append(this.mEvent.getTimestamp());
        outline107.append("], Payload=");
        outline107.append(this.mEvent.getPayload());
        outline107.append(", Extra={");
        return GeneratedOutlineSupport1.outline91(outline107, getExtraString(this.mExtra), "}}");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mExtra);
        parcel.writeParcelable(this.mEvent, i);
    }

    public EventEnvelope(Event event) {
        this(event, new Bundle());
    }

    protected EventEnvelope(Parcel parcel) {
        this.mExtra = parcel.readBundle();
        this.mEvent = (Event) parcel.readParcelable(Event.class.getClassLoader());
    }
}
