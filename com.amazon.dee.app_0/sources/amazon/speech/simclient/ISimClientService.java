package amazon.speech.simclient;

import amazon.speech.model.DirectiveEnvelope;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ISimClientService extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimClientService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onDropDirectives(List<DirectiveEnvelope> list) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onPrepareDirectives(List<DirectiveEnvelope> list) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onStartDirectives(List<DirectiveEnvelope> list) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimClientService {
        private static final String DESCRIPTOR = "amazon.speech.simclient.ISimClientService";
        static final int TRANSACTION_onDropDirectives = 3;
        static final int TRANSACTION_onPrepareDirectives = 1;
        static final int TRANSACTION_onStartDirectives = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimClientService {
            public static ISimClientService sDefaultImpl;
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

            @Override // amazon.speech.simclient.ISimClientService
            public void onDropDirectives(List<DirectiveEnvelope> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDropDirectives(list);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientService
            public void onPrepareDirectives(List<DirectiveEnvelope> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepareDirectives(list);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientService
            public void onStartDirectives(List<DirectiveEnvelope> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartDirectives(list);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimClientService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimClientService)) {
                return (ISimClientService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimClientService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimClientService iSimClientService) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimClientService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimClientService;
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
                onPrepareDirectives(parcel.createTypedArrayList(DirectiveEnvelope.CREATOR));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStartDirectives(parcel.createTypedArrayList(DirectiveEnvelope.CREATOR));
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onDropDirectives(parcel.createTypedArrayList(DirectiveEnvelope.CREATOR));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onDropDirectives(List<DirectiveEnvelope> list) throws RemoteException;

    void onPrepareDirectives(List<DirectiveEnvelope> list) throws RemoteException;

    void onStartDirectives(List<DirectiveEnvelope> list) throws RemoteException;
}
