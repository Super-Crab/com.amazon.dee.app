package amazon.speech.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IEventCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IEventCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.model.IEventCallback
        public boolean isLocal() throws RemoteException {
            return false;
        }

        @Override // amazon.speech.model.IEventCallback
        public void onError(int i) throws RemoteException {
        }

        @Override // amazon.speech.model.IEventCallback
        public void onResponse(List<DirectiveEnvelope> list) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEventCallback {
        private static final String DESCRIPTOR = "amazon.speech.model.IEventCallback";
        static final int TRANSACTION_isLocal = 3;
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onResponse = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEventCallback {
            public static IEventCallback sDefaultImpl;
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

            @Override // amazon.speech.model.IEventCallback
            public boolean isLocal() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLocal();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.model.IEventCallback
            public void onError(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.model.IEventCallback
            public void onResponse(List<DirectiveEnvelope> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResponse(list);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IEventCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IEventCallback)) {
                return (IEventCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IEventCallback iEventCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iEventCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iEventCallback;
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
                onError(parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onResponse(parcel.createTypedArrayList(DirectiveEnvelope.CREATOR));
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isLocal = isLocal();
                parcel2.writeNoException();
                parcel2.writeInt(isLocal ? 1 : 0);
                return true;
            }
        }
    }

    boolean isLocal() throws RemoteException;

    void onError(int i) throws RemoteException;

    void onResponse(List<DirectiveEnvelope> list) throws RemoteException;
}
