package androidx.core.os;

import android.os.Parcel;
@Deprecated
/* loaded from: classes.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    /* renamed from: createFromParcel */
    T mo7259createFromParcel(Parcel parcel, ClassLoader classLoader);

    /* renamed from: newArray */
    T[] mo7260newArray(int i);
}
