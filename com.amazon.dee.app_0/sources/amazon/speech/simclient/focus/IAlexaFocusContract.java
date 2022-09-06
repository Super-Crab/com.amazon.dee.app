package amazon.speech.simclient.focus;

import amazon.speech.simclient.focus.IFocusChangedCallback;
import amazon.speech.simclient.focus.IFocusModificationCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAlexaFocusContract extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IAlexaFocusContract {
        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void acquireFocus(String str, int i, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void acquireFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, IFocusChangedCallback iFocusChangedCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void acquireSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void attachClientInstance(IBinder iBinder, String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void releaseFocus(String str, int i, IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void releaseFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, boolean z) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void releaseSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.focus.IAlexaFocusContract
        public void setActiveNamespace(String str) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAlexaFocusContract {
        private static final String DESCRIPTOR = "amazon.speech.simclient.focus.IAlexaFocusContract";
        static final int TRANSACTION_acquireFocus = 2;
        static final int TRANSACTION_acquireFocusWithCallback = 5;
        static final int TRANSACTION_acquireSystemVisualFocus = 7;
        static final int TRANSACTION_attachClientInstance = 1;
        static final int TRANSACTION_releaseFocus = 3;
        static final int TRANSACTION_releaseFocusWithCallback = 6;
        static final int TRANSACTION_releaseSystemVisualFocus = 8;
        static final int TRANSACTION_setActiveNamespace = 4;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAlexaFocusContract {
            public static IAlexaFocusContract sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void acquireFocus(String str, int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acquireFocus(str, i, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void acquireFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, IFocusChangedCallback iFocusChangedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iFocusModificationCallback != null ? iFocusModificationCallback.asBinder() : null);
                    obtain.writeStrongBinder(iFocusChangedCallback != null ? iFocusChangedCallback.asBinder() : null);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acquireFocusWithCallback(str, i, iBinder, iFocusModificationCallback, iFocusChangedCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void acquireSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iFocusModificationCallback != null ? iFocusModificationCallback.asBinder() : null);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acquireSystemVisualFocus(str, i, iBinder, iFocusModificationCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void attachClientInstance(IBinder iBinder, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().attachClientInstance(iBinder, str);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void releaseFocus(String str, int i, IBinder iBinder, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseFocus(str, i, iBinder, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void releaseFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iFocusModificationCallback != null ? iFocusModificationCallback.asBinder() : null);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseFocusWithCallback(str, i, iBinder, iFocusModificationCallback, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void releaseSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iFocusModificationCallback != null ? iFocusModificationCallback.asBinder() : null);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseSystemVisualFocus(str, i, iBinder, iFocusModificationCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.focus.IAlexaFocusContract
            public void setActiveNamespace(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setActiveNamespace(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAlexaFocusContract asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAlexaFocusContract)) {
                return (IAlexaFocusContract) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAlexaFocusContract getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAlexaFocusContract iAlexaFocusContract) {
            if (Proxy.sDefaultImpl == null) {
                if (iAlexaFocusContract == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAlexaFocusContract;
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
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        attachClientInstance(parcel.readStrongBinder(), parcel.readString());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        acquireFocus(parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        int readInt = parcel.readInt();
                        IBinder readStrongBinder = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        releaseFocus(readString, readInt, readStrongBinder, z);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        setActiveNamespace(parcel.readString());
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        acquireFocusWithCallback(parcel.readString(), parcel.readInt(), parcel.readStrongBinder(), IFocusModificationCallback.Stub.asInterface(parcel.readStrongBinder()), IFocusChangedCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        releaseFocusWithCallback(parcel.readString(), parcel.readInt(), parcel.readStrongBinder(), IFocusModificationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        acquireSystemVisualFocus(parcel.readString(), parcel.readInt(), parcel.readStrongBinder(), IFocusModificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        releaseSystemVisualFocus(parcel.readString(), parcel.readInt(), parcel.readStrongBinder(), IFocusModificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void acquireFocus(String str, int i, IBinder iBinder) throws RemoteException;

    void acquireFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, IFocusChangedCallback iFocusChangedCallback) throws RemoteException;

    void acquireSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException;

    void attachClientInstance(IBinder iBinder, String str) throws RemoteException;

    void releaseFocus(String str, int i, IBinder iBinder, boolean z) throws RemoteException;

    void releaseFocusWithCallback(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback, boolean z) throws RemoteException;

    void releaseSystemVisualFocus(String str, int i, IBinder iBinder, IFocusModificationCallback iFocusModificationCallback) throws RemoteException;

    void setActiveNamespace(String str) throws RemoteException;
}
