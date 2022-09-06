package com.amazon.leaderselection;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
/* loaded from: classes12.dex */
public class Leader implements Parcelable {
    private static final String UNKNOWN_CLASS_NAME = "unknown";
    private static final String UNKNOWN_PACKAGE_NAME = "unknown";
    private final ComponentName componentName;
    public static final Leader UNKNOWN = create("unknown", "unknown");
    public static final Parcelable.Creator<Leader> CREATOR = new a();

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<Leader> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Leader mo4082createFromParcel(Parcel parcel) {
            return new Leader(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Leader[] mo4083newArray(int i) {
            return new Leader[i];
        }
    }

    private Leader(ComponentName componentName) {
        this.componentName = componentName;
    }

    protected Leader(Parcel parcel) {
        this.componentName = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
    }

    static Leader create(ComponentName componentName) {
        return new Leader(componentName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Leader create(String str, String str2) {
        return create(h.a(str, str2));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Leader.class == obj.getClass()) {
            return Objects.equals(this.componentName, ((Leader) obj).componentName);
        }
        return false;
    }

    public ComponentName getComponentName() {
        return this.componentName;
    }

    public int hashCode() {
        return Objects.hash(this.componentName);
    }

    public String toString() {
        return this.componentName.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.componentName, i);
    }
}
