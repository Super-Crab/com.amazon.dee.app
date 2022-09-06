package com.magiear.handsfree.util;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.magiear.handsfree.util.IResultCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface IUVRVendorSettings extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUVRVendorSettings {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public void disableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public void disableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public void enableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public void enableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public List<Bundle> getEnrolledUsers() throws RemoteException {
            return null;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public boolean isAntiSpoofEnabled() throws RemoteException {
            return false;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public boolean isAntiSpoofSupported() throws RemoteException {
            return false;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public boolean isUVRAvailable() throws RemoteException {
            return false;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public boolean isUVREnrolled(Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.magiear.handsfree.util.IUVRVendorSettings
        public void removeUVRModel(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUVRVendorSettings {
        private static final String DESCRIPTOR = "com.magiear.handsfree.util.IUVRVendorSettings";
        static final int TRANSACTION_disableAntiSpoofVerification = 6;
        static final int TRANSACTION_disableUVR = 7;
        static final int TRANSACTION_enableAntiSpoofVerification = 8;
        static final int TRANSACTION_enableUVR = 9;
        static final int TRANSACTION_getEnrolledUsers = 5;
        static final int TRANSACTION_isAntiSpoofEnabled = 1;
        static final int TRANSACTION_isAntiSpoofSupported = 2;
        static final int TRANSACTION_isUVRAvailable = 3;
        static final int TRANSACTION_isUVREnrolled = 4;
        static final int TRANSACTION_removeUVRModel = 10;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUVRVendorSettings {
            public static IUVRVendorSettings sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public void disableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableAntiSpoofVerification(iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public void disableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableUVR(bundle, iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public void enableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableAntiSpoofVerification(iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public void enableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableUVR(bundle, iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public List<Bundle> getEnrolledUsers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledUsers();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public boolean isAntiSpoofEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAntiSpoofEnabled();
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

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public boolean isAntiSpoofSupported() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAntiSpoofSupported();
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

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public boolean isUVRAvailable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUVRAvailable();
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

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public boolean isUVREnrolled(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUVREnrolled(bundle);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVRVendorSettings
            public void removeUVRModel(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeUVRModel(bundle, iResultCallback);
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

        public static IUVRVendorSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUVRVendorSettings)) {
                return (IUVRVendorSettings) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUVRVendorSettings getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUVRVendorSettings iUVRVendorSettings) {
            if (Proxy.sDefaultImpl == null) {
                if (iUVRVendorSettings == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUVRVendorSettings;
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
                Bundle bundle = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAntiSpoofEnabled = isAntiSpoofEnabled();
                        parcel2.writeNoException();
                        parcel2.writeInt(isAntiSpoofEnabled ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAntiSpoofSupported = isAntiSpoofSupported();
                        parcel2.writeNoException();
                        parcel2.writeInt(isAntiSpoofSupported ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isUVRAvailable = isUVRAvailable();
                        parcel2.writeNoException();
                        parcel2.writeInt(isUVRAvailable ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        boolean isUVREnrolled = isUVREnrolled(bundle);
                        parcel2.writeNoException();
                        parcel2.writeInt(isUVREnrolled ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<Bundle> enrolledUsers = getEnrolledUsers();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(enrolledUsers);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        disableAntiSpoofVerification(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        disableUVR(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        enableAntiSpoofVerification(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        enableUVR(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        removeUVRModel(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void disableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException;

    void disableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;

    void enableAntiSpoofVerification(IResultCallback iResultCallback) throws RemoteException;

    void enableUVR(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;

    List<Bundle> getEnrolledUsers() throws RemoteException;

    boolean isAntiSpoofEnabled() throws RemoteException;

    boolean isAntiSpoofSupported() throws RemoteException;

    boolean isUVRAvailable() throws RemoteException;

    boolean isUVREnrolled(Bundle bundle) throws RemoteException;

    void removeUVRModel(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;
}
