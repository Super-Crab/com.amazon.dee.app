package com.amazon.identity.auth.device.token;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.amazon.identity.auth.device.gy;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.token.IInternalListener;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class RemoteInternalListenerWrapper extends gy implements Parcelable {
    public static final Parcelable.Creator<RemoteInternalListenerWrapper> CREATOR = new Parcelable.Creator<RemoteInternalListenerWrapper>() { // from class: com.amazon.identity.auth.device.token.RemoteInternalListenerWrapper.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: d */
        public RemoteInternalListenerWrapper createFromParcel(Parcel parcel) {
            return new RemoteInternalListenerWrapper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: n */
        public RemoteInternalListenerWrapper[] newArray(int i) {
            return new RemoteInternalListenerWrapper[i];
        }
    };
    private static final String TAG = "RemoteInternalListenerWrapper";
    private final IInternalListener mIInternalListener;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a extends IInternalListener.Stub {
        private final gy pP;

        public a(gy gyVar) {
            this.pP = gyVar;
        }

        @Override // com.amazon.identity.auth.device.token.IInternalListener
        public void finish(Bundle bundle) {
            gy gyVar = this.pP;
            if (gyVar != null) {
                gyVar.finish(bundle);
            }
        }
    }

    public RemoteInternalListenerWrapper(IInternalListener iInternalListener) {
        this.mIInternalListener = iInternalListener;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.identity.auth.device.gy
    public void finish(Bundle bundle) {
        IInternalListener iInternalListener = this.mIInternalListener;
        if (iInternalListener != null) {
            try {
                iInternalListener.finish(bundle);
            } catch (RemoteException e) {
                io.e(TAG, "finish callback failed", e);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        IInternalListener iInternalListener = this.mIInternalListener;
        if (iInternalListener != null) {
            parcel.writeStrongBinder(iInternalListener.asBinder());
        }
    }

    public RemoteInternalListenerWrapper(gy gyVar) {
        this(new a(gyVar));
    }

    public RemoteInternalListenerWrapper(Parcel parcel) {
        this(IInternalListener.Stub.asInterface(parcel.readStrongBinder()));
    }
}
