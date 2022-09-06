package com.amazon.dcp.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface ISessionUserChanger extends IInterface {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements ISessionUserChanger {
        private static final String DESCRIPTOR = "com.amazon.dcp.sso.ISessionUserChanger";
        static final int TRANSACTION_addSessionUserDirectedIds = 4;
        static final int TRANSACTION_addSessionUsers = 1;
        static final int TRANSACTION_changeSessionUserDirectedIds = 6;
        static final int TRANSACTION_changeSessionUsers = 3;
        static final int TRANSACTION_removeSessionUserDirectedIds = 5;
        static final int TRANSACTION_removeSessionUsers = 2;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public static class Proxy implements ISessionUserChanger {
            public static ISessionUserChanger sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public String[] addSessionUserDirectedIds(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSessionUserDirectedIds(strArr);
                    }
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public ParcelableAccount[] addSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(parcelableAccountArr, 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSessionUsers(parcelableAccountArr);
                    }
                    obtain2.readException();
                    return (ParcelableAccount[]) obtain2.createTypedArray(ParcelableAccount.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public String[] changeSessionUserDirectedIds(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeSessionUserDirectedIds(strArr);
                    }
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public ParcelableAccount[] changeSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(parcelableAccountArr, 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeSessionUsers(parcelableAccountArr);
                    }
                    obtain2.readException();
                    return (ParcelableAccount[]) obtain2.createTypedArray(ParcelableAccount.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public String[] removeSessionUserDirectedIds(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeSessionUserDirectedIds(strArr);
                    }
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.ISessionUserChanger
            public ParcelableAccount[] removeSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(parcelableAccountArr, 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeSessionUsers(parcelableAccountArr);
                    }
                    obtain2.readException();
                    return (ParcelableAccount[]) obtain2.createTypedArray(ParcelableAccount.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISessionUserChanger asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISessionUserChanger)) {
                return (ISessionUserChanger) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISessionUserChanger getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISessionUserChanger iSessionUserChanger) {
            if (Proxy.sDefaultImpl == null) {
                if (iSessionUserChanger == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSessionUserChanger;
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
                        ParcelableAccount[] addSessionUsers = addSessionUsers((ParcelableAccount[]) parcel.createTypedArray(ParcelableAccount.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeTypedArray(addSessionUsers, 1);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableAccount[] removeSessionUsers = removeSessionUsers((ParcelableAccount[]) parcel.createTypedArray(ParcelableAccount.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeTypedArray(removeSessionUsers, 1);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableAccount[] changeSessionUsers = changeSessionUsers((ParcelableAccount[]) parcel.createTypedArray(ParcelableAccount.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeTypedArray(changeSessionUsers, 1);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] addSessionUserDirectedIds = addSessionUserDirectedIds(parcel.createStringArray());
                        parcel2.writeNoException();
                        parcel2.writeStringArray(addSessionUserDirectedIds);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] removeSessionUserDirectedIds = removeSessionUserDirectedIds(parcel.createStringArray());
                        parcel2.writeNoException();
                        parcel2.writeStringArray(removeSessionUserDirectedIds);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] changeSessionUserDirectedIds = changeSessionUserDirectedIds(parcel.createStringArray());
                        parcel2.writeNoException();
                        parcel2.writeStringArray(changeSessionUserDirectedIds);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    String[] addSessionUserDirectedIds(String[] strArr) throws RemoteException;

    ParcelableAccount[] addSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException;

    String[] changeSessionUserDirectedIds(String[] strArr) throws RemoteException;

    ParcelableAccount[] changeSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException;

    String[] removeSessionUserDirectedIds(String[] strArr) throws RemoteException;

    ParcelableAccount[] removeSessionUsers(ParcelableAccount[] parcelableAccountArr) throws RemoteException;
}
