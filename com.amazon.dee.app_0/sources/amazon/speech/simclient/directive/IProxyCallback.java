package amazon.speech.simclient.directive;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IProxyCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IProxyCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.directive.IProxyCallback
        public void onCancellation() throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.IProxyCallback
        public void onSuccess() throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IProxyCallback {
        private static final String DESCRIPTOR = "amazon.speech.simclient.directive.IProxyCallback";
        static final int TRANSACTION_onCancellation = 2;
        static final int TRANSACTION_onSuccess = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IProxyCallback {
            public static IProxyCallback sDefaultImpl;
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

            @Override // amazon.speech.simclient.directive.IProxyCallback
            public void onCancellation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCancellation();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.IProxyCallback
            public void onSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuccess();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProxyCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IProxyCallback)) {
                return (IProxyCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IProxyCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IProxyCallback iProxyCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iProxyCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iProxyCallback;
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
                onSuccess();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onCancellation();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onCancellation() throws RemoteException;

    void onSuccess() throws RemoteException;
}
