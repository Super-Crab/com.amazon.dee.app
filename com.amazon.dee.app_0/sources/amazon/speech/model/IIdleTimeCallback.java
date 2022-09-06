package amazon.speech.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IIdleTimeCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IIdleTimeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.model.IIdleTimeCallback
        public void onError() throws RemoteException {
        }

        @Override // amazon.speech.model.IIdleTimeCallback
        public void onIdleTime(long j) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IIdleTimeCallback {
        private static final String DESCRIPTOR = "amazon.speech.model.IIdleTimeCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onIdleTime = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IIdleTimeCallback {
            public static IIdleTimeCallback sDefaultImpl;
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

            @Override // amazon.speech.model.IIdleTimeCallback
            public void onError() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.model.IIdleTimeCallback
            public void onIdleTime(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onIdleTime(j);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIdleTimeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IIdleTimeCallback)) {
                return (IIdleTimeCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IIdleTimeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IIdleTimeCallback iIdleTimeCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iIdleTimeCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iIdleTimeCallback;
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
                onError();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onIdleTime(parcel.readLong());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onError() throws RemoteException;

    void onIdleTime(long j) throws RemoteException;
}
