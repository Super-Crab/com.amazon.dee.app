package amazon.speech.simclient;

import amazon.speech.model.DirectiveEnvelope;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISimCapabilityManager extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimCapabilityManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.ISimCapabilityManager
        public void onDirectiveDropped(DirectiveEnvelope directiveEnvelope) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimCapabilityManager
        public void onDirectiveError(DirectiveEnvelope directiveEnvelope) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimCapabilityManager
        public void onDirectiveFinished(DirectiveEnvelope directiveEnvelope) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimCapabilityManager {
        private static final String DESCRIPTOR = "amazon.speech.simclient.ISimCapabilityManager";
        static final int TRANSACTION_onDirectiveDropped = 3;
        static final int TRANSACTION_onDirectiveError = 2;
        static final int TRANSACTION_onDirectiveFinished = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimCapabilityManager {
            public static ISimCapabilityManager sDefaultImpl;
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

            @Override // amazon.speech.simclient.ISimCapabilityManager
            public void onDirectiveDropped(DirectiveEnvelope directiveEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (directiveEnvelope != null) {
                        obtain.writeInt(1);
                        directiveEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDirectiveDropped(directiveEnvelope);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimCapabilityManager
            public void onDirectiveError(DirectiveEnvelope directiveEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (directiveEnvelope != null) {
                        obtain.writeInt(1);
                        directiveEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDirectiveError(directiveEnvelope);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimCapabilityManager
            public void onDirectiveFinished(DirectiveEnvelope directiveEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (directiveEnvelope != null) {
                        obtain.writeInt(1);
                        directiveEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDirectiveFinished(directiveEnvelope);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimCapabilityManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimCapabilityManager)) {
                return (ISimCapabilityManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimCapabilityManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimCapabilityManager iSimCapabilityManager) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimCapabilityManager == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimCapabilityManager;
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
            DirectiveEnvelope directiveEnvelope = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    directiveEnvelope = DirectiveEnvelope.CREATOR.createFromParcel(parcel);
                }
                onDirectiveFinished(directiveEnvelope);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    directiveEnvelope = DirectiveEnvelope.CREATOR.createFromParcel(parcel);
                }
                onDirectiveError(directiveEnvelope);
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    directiveEnvelope = DirectiveEnvelope.CREATOR.createFromParcel(parcel);
                }
                onDirectiveDropped(directiveEnvelope);
                return true;
            }
        }
    }

    void onDirectiveDropped(DirectiveEnvelope directiveEnvelope) throws RemoteException;

    void onDirectiveError(DirectiveEnvelope directiveEnvelope) throws RemoteException;

    void onDirectiveFinished(DirectiveEnvelope directiveEnvelope) throws RemoteException;
}
