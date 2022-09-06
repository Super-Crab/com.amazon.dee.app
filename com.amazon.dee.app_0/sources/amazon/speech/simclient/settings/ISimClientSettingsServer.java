package amazon.speech.simclient.settings;

import amazon.speech.simclient.settings.ISettingsCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISimClientSettingsServer extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimClientSettingsServer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
        public void querySetting(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
        public void registerCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
        public void registerCallback2(String str, ISettingsCallback iSettingsCallback, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
        public void unregisterCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
        public void unregisterCallback2(String str, IBinder iBinder) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimClientSettingsServer {
        private static final String DESCRIPTOR = "amazon.speech.simclient.settings.ISimClientSettingsServer";
        static final int TRANSACTION_querySetting = 1;
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_registerCallback2 = 4;
        static final int TRANSACTION_unregisterCallback = 3;
        static final int TRANSACTION_unregisterCallback2 = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimClientSettingsServer {
            public static ISimClientSettingsServer sDefaultImpl;
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

            @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
            public void querySetting(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().querySetting(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
            public void registerCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
            public void registerCallback2(String str, ISettingsCallback iSettingsCallback, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback2(str, iSettingsCallback, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
            public void unregisterCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.settings.ISimClientSettingsServer
            public void unregisterCallback2(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback2(str, iBinder);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimClientSettingsServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimClientSettingsServer)) {
                return (ISimClientSettingsServer) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimClientSettingsServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimClientSettingsServer iSimClientSettingsServer) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimClientSettingsServer == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimClientSettingsServer;
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
                querySetting(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                registerCallback(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterCallback(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                registerCallback2(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterCallback2(parcel.readString(), parcel.readStrongBinder());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void querySetting(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void registerCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void registerCallback2(String str, ISettingsCallback iSettingsCallback, IBinder iBinder) throws RemoteException;

    void unregisterCallback(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void unregisterCallback2(String str, IBinder iBinder) throws RemoteException;
}
