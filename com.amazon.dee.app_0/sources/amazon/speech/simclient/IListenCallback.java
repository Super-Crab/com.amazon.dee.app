package amazon.speech.simclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IListenCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IListenCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.IListenCallback
        public void onListenError(int i) throws RemoteException {
        }

        @Override // amazon.speech.simclient.IListenCallback
        public void onListenFinished() throws RemoteException {
        }

        @Override // amazon.speech.simclient.IListenCallback
        public void onListenStarted() throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IListenCallback {
        private static final String DESCRIPTOR = "amazon.speech.simclient.IListenCallback";
        static final int TRANSACTION_onListenError = 1;
        static final int TRANSACTION_onListenFinished = 3;
        static final int TRANSACTION_onListenStarted = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IListenCallback {
            public static IListenCallback sDefaultImpl;
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

            @Override // amazon.speech.simclient.IListenCallback
            public void onListenError(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenError(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.IListenCallback
            public void onListenFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenFinished();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.IListenCallback
            public void onListenStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenStarted();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IListenCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IListenCallback)) {
                return (IListenCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IListenCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IListenCallback iListenCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iListenCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iListenCallback;
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
                onListenError(parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onListenStarted();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onListenFinished();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onListenError(int i) throws RemoteException;

    void onListenFinished() throws RemoteException;

    void onListenStarted() throws RemoteException;
}
