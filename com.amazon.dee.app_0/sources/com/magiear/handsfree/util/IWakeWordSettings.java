package com.magiear.handsfree.util;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.magiear.handsfree.util.IResultCallback;
/* loaded from: classes3.dex */
public interface IWakeWordSettings extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IWakeWordSettings {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public String getLocale() throws RemoteException {
            return null;
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public Bundle getWakeWordParam() throws RemoteException {
            return null;
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public boolean isWakeWordRecognitionEnabled() throws RemoteException {
            return false;
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public void setLocale(String str, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public void setWakeWordParam(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IWakeWordSettings
        public void setWakeWordRecognitionEnabled(boolean z, IResultCallback iResultCallback) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWakeWordSettings {
        private static final String DESCRIPTOR = "com.magiear.handsfree.util.IWakeWordSettings";
        static final int TRANSACTION_getLocale = 4;
        static final int TRANSACTION_getWakeWordParam = 6;
        static final int TRANSACTION_isWakeWordRecognitionEnabled = 2;
        static final int TRANSACTION_setLocale = 3;
        static final int TRANSACTION_setWakeWordParam = 5;
        static final int TRANSACTION_setWakeWordRecognitionEnabled = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWakeWordSettings {
            public static IWakeWordSettings sDefaultImpl;
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

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public String getLocale() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocale();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public Bundle getWakeWordParam() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWakeWordParam();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public boolean isWakeWordRecognitionEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWakeWordRecognitionEnabled();
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

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public void setLocale(String str, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLocale(str, iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public void setWakeWordParam(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
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
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWakeWordParam(bundle, iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IWakeWordSettings
            public void setWakeWordRecognitionEnabled(boolean z, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWakeWordRecognitionEnabled(z, iResultCallback);
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

        public static IWakeWordSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWakeWordSettings)) {
                return (IWakeWordSettings) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IWakeWordSettings getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWakeWordSettings iWakeWordSettings) {
            if (Proxy.sDefaultImpl == null) {
                if (iWakeWordSettings == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iWakeWordSettings;
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
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setWakeWordRecognitionEnabled(z, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isWakeWordRecognitionEnabled = isWakeWordRecognitionEnabled();
                        parcel2.writeNoException();
                        parcel2.writeInt(isWakeWordRecognitionEnabled ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        setLocale(parcel.readString(), IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String locale = getLocale();
                        parcel2.writeNoException();
                        parcel2.writeString(locale);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        setWakeWordParam(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle wakeWordParam = getWakeWordParam();
                        parcel2.writeNoException();
                        if (wakeWordParam != null) {
                            parcel2.writeInt(1);
                            wakeWordParam.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    String getLocale() throws RemoteException;

    Bundle getWakeWordParam() throws RemoteException;

    boolean isWakeWordRecognitionEnabled() throws RemoteException;

    void setLocale(String str, IResultCallback iResultCallback) throws RemoteException;

    void setWakeWordParam(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;

    void setWakeWordRecognitionEnabled(boolean z, IResultCallback iResultCallback) throws RemoteException;
}
