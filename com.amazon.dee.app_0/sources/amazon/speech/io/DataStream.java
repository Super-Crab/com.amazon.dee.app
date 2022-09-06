package amazon.speech.io;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public abstract class DataStream implements Parcelable {
    public static final Parcelable.Creator<DataStream> CREATOR = new Parcelable.Creator<DataStream>() { // from class: amazon.speech.io.DataStream.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DataStream mo21createFromParcel(Parcel parcel) {
            return (DataStream) parcel.readParcelable(AnonymousClass1.class.getClassLoader());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DataStream[] mo22newArray(int i) {
            return new DataStream[i];
        }
    };

    /* loaded from: classes.dex */
    public interface Metadata {
    }

    @Deprecated
    public static DataStream create(int i) {
        return create(i, 1);
    }

    @Deprecated
    public abstract InputStream createInputStream();

    @Deprecated
    public abstract OutputStream createOutputStream();

    public abstract Metadata getMetadata();

    public abstract DataStreamReader openReader();

    public abstract DataStreamWriter openWriter();

    public abstract void recycle();

    @Deprecated
    public static DataStream create(int i, int i2) {
        return new AudioStreamDataStream(null, i, i2, null);
    }
}
