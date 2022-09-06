package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.List;
/* loaded from: classes6.dex */
public class au implements Parcelable {
    public static final Parcelable.Creator<au> CREATOR = new Parcelable.Creator<au>() { // from class: com.amazon.alexa.api.au.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public au createFromParcel(Parcel parcel) {
            return new au(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public au[] newArray(int i) {
            return new au[i];
        }
    };
    private List<String> a;

    protected au(Parcel parcel) {
        Preconditions.notNull(parcel, "Parameter in is null.");
        this.a = parcel.createStringArrayList();
    }

    public au(List<String> list) {
        Preconditions.notNull(list, "Parameter locales is null.");
        this.a = list;
    }

    public List<String> a() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.a);
    }
}
