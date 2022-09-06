package com.amazon.dcp.sso;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface IRequestAuthenticationMethod extends IInterface {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IRequestAuthenticationMethod {
        private static final String DESCRIPTOR = "com.amazon.dcp.sso.IRequestAuthenticationMethod";
        static final int TRANSACTION_getAdpToken = 3;
        static final int TRANSACTION_getAdpTokenByDirectedId = 6;
        static final int TRANSACTION_getAuthenticationParametersForRequest = 1;
        static final int TRANSACTION_getAuthenticationParametersForRequestByDirectedId = 4;
        static final int TRANSACTION_signCorpus = 2;
        static final int TRANSACTION_signCorpusByDirectedId = 5;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public static class Proxy implements IRequestAuthenticationMethod {
            public static IRequestAuthenticationMethod sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public String getAdpToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdpToken(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public String getAdpTokenByDirectedId(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdpTokenByDirectedId(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError getAuthenticationParametersForRequest(String str, String str2, String str3, Uri uri, String str4, Map map, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str4);
                    obtain.writeMap(map);
                    obtain.writeByteArray(bArr);
                    try {
                        if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            ReturnValueOrError authenticationParametersForRequest = Stub.getDefaultImpl().getAuthenticationParametersForRequest(str, str2, str3, uri, str4, map, bArr);
                            obtain2.recycle();
                            obtain.recycle();
                            return authenticationParametersForRequest;
                        }
                        obtain2.readException();
                        ReturnValueOrError createFromParcel = obtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(obtain2) : null;
                        obtain2.recycle();
                        obtain.recycle();
                        return createFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError getAuthenticationParametersForRequestByDirectedId(String str, String str2, Uri uri, String str3, Map map, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str3);
                    obtain.writeMap(map);
                    obtain.writeByteArray(bArr);
                    try {
                        if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            ReturnValueOrError authenticationParametersForRequestByDirectedId = Stub.getDefaultImpl().getAuthenticationParametersForRequestByDirectedId(str, str2, uri, str3, map, bArr);
                            obtain2.recycle();
                            obtain.recycle();
                            return authenticationParametersForRequestByDirectedId;
                        }
                        obtain2.readException();
                        ReturnValueOrError createFromParcel = obtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(obtain2) : null;
                        obtain2.recycle();
                        obtain.recycle();
                        return createFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError signCorpus(String str, String str2, String str3, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signCorpus(str, str2, str3, bArr);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError signCorpusByDirectedId(String str, String str2, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signCorpusByDirectedId(str, str2, bArr);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRequestAuthenticationMethod asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRequestAuthenticationMethod)) {
                return (IRequestAuthenticationMethod) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRequestAuthenticationMethod getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRequestAuthenticationMethod iRequestAuthenticationMethod) {
            if (Proxy.sDefaultImpl == null) {
                if (iRequestAuthenticationMethod == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRequestAuthenticationMethod;
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
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        ReturnValueOrError authenticationParametersForRequest = getAuthenticationParametersForRequest(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(Stub.class.getClassLoader()), parcel.createByteArray());
                        parcel2.writeNoException();
                        if (authenticationParametersForRequest != null) {
                            parcel2.writeInt(1);
                            authenticationParametersForRequest.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        ReturnValueOrError signCorpus = signCorpus(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray());
                        parcel2.writeNoException();
                        if (signCorpus != null) {
                            parcel2.writeInt(1);
                            signCorpus.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String adpToken = getAdpToken(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(adpToken);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        ReturnValueOrError authenticationParametersForRequestByDirectedId = getAuthenticationParametersForRequestByDirectedId(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(Stub.class.getClassLoader()), parcel.createByteArray());
                        parcel2.writeNoException();
                        if (authenticationParametersForRequestByDirectedId != null) {
                            parcel2.writeInt(1);
                            authenticationParametersForRequestByDirectedId.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        ReturnValueOrError signCorpusByDirectedId = signCorpusByDirectedId(parcel.readString(), parcel.readString(), parcel.createByteArray());
                        parcel2.writeNoException();
                        if (signCorpusByDirectedId != null) {
                            parcel2.writeInt(1);
                            signCorpusByDirectedId.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String adpTokenByDirectedId = getAdpTokenByDirectedId(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(adpTokenByDirectedId);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    String getAdpToken(String str, String str2) throws RemoteException;

    String getAdpTokenByDirectedId(String str) throws RemoteException;

    ReturnValueOrError getAuthenticationParametersForRequest(String str, String str2, String str3, Uri uri, String str4, Map map, byte[] bArr) throws RemoteException;

    ReturnValueOrError getAuthenticationParametersForRequestByDirectedId(String str, String str2, Uri uri, String str3, Map map, byte[] bArr) throws RemoteException;

    ReturnValueOrError signCorpus(String str, String str2, String str3, byte[] bArr) throws RemoteException;

    ReturnValueOrError signCorpusByDirectedId(String str, String str2, byte[] bArr) throws RemoteException;
}
