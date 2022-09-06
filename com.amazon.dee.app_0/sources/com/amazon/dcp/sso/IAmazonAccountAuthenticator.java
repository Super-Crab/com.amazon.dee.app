package com.amazon.dcp.sso;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.dcp.sso.IWebserviceCallback;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface IAmazonAccountAuthenticator extends IInterface {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IAmazonAccountAuthenticator {
        private static final String DESCRIPTOR = "com.amazon.dcp.sso.IAmazonAccountAuthenticator";
        static final int TRANSACTION_callGetCredentialsWebservice = 1;
        static final int TRANSACTION_invalidateAuthToken = 4;
        static final int TRANSACTION_invalidateAuthTokenByTokenType = 5;
        static final int TRANSACTION_peekAuthToken = 3;
        static final int TRANSACTION_storeToken = 2;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public static class Proxy implements IAmazonAccountAuthenticator {
            public static IAmazonAccountAuthenticator sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void callGetCredentialsWebservice(Uri uri, String str, Map map, byte[] bArr, IWebserviceCallback iWebserviceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    obtain.writeByteArray(bArr);
                    obtain.writeStrongBinder(iWebserviceCallback != null ? iWebserviceCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callGetCredentialsWebservice(uri, str, map, bArr, iWebserviceCallback);
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

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void invalidateAuthToken(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().invalidateAuthToken(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void invalidateAuthTokenByTokenType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().invalidateAuthTokenByTokenType(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public String peekAuthToken(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().peekAuthToken(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void storeToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().storeToken(str, str2);
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

        public static IAmazonAccountAuthenticator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAmazonAccountAuthenticator)) {
                return (IAmazonAccountAuthenticator) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAmazonAccountAuthenticator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            if (Proxy.sDefaultImpl == null) {
                if (iAmazonAccountAuthenticator == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAmazonAccountAuthenticator;
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
                callGetCredentialsWebservice(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(Stub.class.getClassLoader()), parcel.createByteArray(), IWebserviceCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                storeToken(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String peekAuthToken = peekAuthToken(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(peekAuthToken);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                invalidateAuthToken(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                invalidateAuthTokenByTokenType(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void callGetCredentialsWebservice(Uri uri, String str, Map map, byte[] bArr, IWebserviceCallback iWebserviceCallback) throws RemoteException;

    void invalidateAuthToken(String str) throws RemoteException;

    void invalidateAuthTokenByTokenType(String str) throws RemoteException;

    String peekAuthToken(String str) throws RemoteException;

    void storeToken(String str, String str2) throws RemoteException;
}
