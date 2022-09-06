package com.amazon.alexa.auth.map;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AuthorizationServiceProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AuthorizationServiceProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
        public String getDirectedID() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
        public String getMarketplace() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
        public String getToken() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
        public boolean isLoggedIn() throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
        public void killService() throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AuthorizationServiceProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.auth.map.AuthorizationServiceProxy";
        static final int TRANSACTION_getDirectedID = 4;
        static final int TRANSACTION_getMarketplace = 3;
        static final int TRANSACTION_getToken = 2;
        static final int TRANSACTION_isLoggedIn = 1;
        static final int TRANSACTION_killService = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AuthorizationServiceProxy {
            public static AuthorizationServiceProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
            public String getDirectedID() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDirectedID();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
            public String getMarketplace() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMarketplace();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
            public String getToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
            public boolean isLoggedIn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLoggedIn();
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

            @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
            public void killService() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().killService();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AuthorizationServiceProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AuthorizationServiceProxy)) {
                return (AuthorizationServiceProxy) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static AuthorizationServiceProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AuthorizationServiceProxy authorizationServiceProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (authorizationServiceProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = authorizationServiceProxy;
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
                boolean isLoggedIn = isLoggedIn();
                parcel2.writeNoException();
                parcel2.writeInt(isLoggedIn ? 1 : 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String token = getToken();
                parcel2.writeNoException();
                parcel2.writeString(token);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String marketplace = getMarketplace();
                parcel2.writeNoException();
                parcel2.writeString(marketplace);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                String directedID = getDirectedID();
                parcel2.writeNoException();
                parcel2.writeString(directedID);
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                killService();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    String getDirectedID() throws RemoteException;

    String getMarketplace() throws RemoteException;

    String getToken() throws RemoteException;

    boolean isLoggedIn() throws RemoteException;

    void killService() throws RemoteException;
}
