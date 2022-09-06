package com.amazon.alexa.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface UserPerceivedLatencyListenerProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements UserPerceivedLatencyListenerProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.UserPerceivedLatencyListenerProxy
        public void onLatencyData(String str, Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements UserPerceivedLatencyListenerProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.UserPerceivedLatencyListenerProxy";
        static final int TRANSACTION_onLatencyData = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements UserPerceivedLatencyListenerProxy {
            public static UserPerceivedLatencyListenerProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.api.UserPerceivedLatencyListenerProxy
            public void onLatencyData(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLatencyData(str, bundle);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static UserPerceivedLatencyListenerProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof UserPerceivedLatencyListenerProxy)) ? new Proxy(iBinder) : (UserPerceivedLatencyListenerProxy) queryLocalInterface;
        }

        public static UserPerceivedLatencyListenerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (userPerceivedLatencyListenerProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = userPerceivedLatencyListenerProxy;
                return true;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onLatencyData(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onLatencyData(String str, Bundle bundle) throws RemoteException;
}
