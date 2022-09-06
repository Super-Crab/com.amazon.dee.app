package com.amazon.tarazed.core.sessionclient.sessioncache;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface ISessionClientCache extends IInterface {

    /* loaded from: classes13.dex */
    public static class Default implements ISessionClientCache {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void clearLaunchRequest() throws RemoteException {
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public String getLaunchRequest() throws RemoteException {
            return null;
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public String getSessionCredentials(String str) throws RemoteException {
            return null;
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void putLaunchRequest(String str) throws RemoteException {
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void putSessionCredentials(String str, String str2) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements ISessionClientCache {
        private static final String DESCRIPTOR = "com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache";
        static final int TRANSACTION_clearLaunchRequest = 3;
        static final int TRANSACTION_getLaunchRequest = 4;
        static final int TRANSACTION_getSessionCredentials = 1;
        static final int TRANSACTION_putLaunchRequest = 5;
        static final int TRANSACTION_putSessionCredentials = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements ISessionClientCache {
            public static ISessionClientCache sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
            public void clearLaunchRequest() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearLaunchRequest();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
            public String getLaunchRequest() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaunchRequest();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
            public String getSessionCredentials(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSessionCredentials(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
            public void putLaunchRequest(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().putLaunchRequest(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
            public void putSessionCredentials(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().putSessionCredentials(str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISessionClientCache asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISessionClientCache)) {
                return (ISessionClientCache) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISessionClientCache getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISessionClientCache iSessionClientCache) {
            if (Proxy.sDefaultImpl == null) {
                if (iSessionClientCache == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSessionClientCache;
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
                String sessionCredentials = getSessionCredentials(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(sessionCredentials);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                putSessionCredentials(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                clearLaunchRequest();
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                String launchRequest = getLaunchRequest();
                parcel2.writeNoException();
                parcel2.writeString(launchRequest);
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                putLaunchRequest(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void clearLaunchRequest() throws RemoteException;

    String getLaunchRequest() throws RemoteException;

    String getSessionCredentials(String str) throws RemoteException;

    void putLaunchRequest(String str) throws RemoteException;

    void putSessionCredentials(String str, String str2) throws RemoteException;
}
