package amazon.speech.simclient.genericconnection;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IGenericConnectionStatusCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IGenericConnectionStatusCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback
        public void onResult(String str) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGenericConnectionStatusCallback {
        private static final String DESCRIPTOR = "amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback";
        static final int TRANSACTION_onResult = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IGenericConnectionStatusCallback {
            public static IGenericConnectionStatusCallback sDefaultImpl;
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

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback
            public void onResult(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResult(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGenericConnectionStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGenericConnectionStatusCallback)) {
                return (IGenericConnectionStatusCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IGenericConnectionStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IGenericConnectionStatusCallback iGenericConnectionStatusCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iGenericConnectionStatusCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iGenericConnectionStatusCallback;
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
                onResult(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onResult(String str) throws RemoteException;
}
