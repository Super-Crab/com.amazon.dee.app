package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.quicinc.voice.activation.IResultCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface IUVRSettingsService extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUVRSettingsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void disableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void enableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public List<Bundle> getEnrolledUsers(Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void getParams(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public boolean isUVRAvailable(Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public boolean isUVREnrolled(Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void loadDefaultVoiceModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void removeUVRModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRSettingsService
        public void setParams(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUVRSettingsService {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IUVRSettingsService";
        static final int TRANSACTION_disableUVR = 6;
        static final int TRANSACTION_enableUVR = 5;
        static final int TRANSACTION_getEnrolledUsers = 9;
        static final int TRANSACTION_getParams = 2;
        static final int TRANSACTION_isUVRAvailable = 3;
        static final int TRANSACTION_isUVREnrolled = 4;
        static final int TRANSACTION_loadDefaultVoiceModel = 8;
        static final int TRANSACTION_removeUVRModel = 7;
        static final int TRANSACTION_setParams = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUVRSettingsService {
            public static IUVRSettingsService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void disableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableUVR(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void enableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enableUVR(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public List<Bundle> getEnrolledUsers(Bundle bundle) throws RemoteException {
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
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledUsers(bundle);
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

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void getParams(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getParams(iResultCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public boolean isUVRAvailable(Bundle bundle) throws RemoteException {
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
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUVRAvailable(bundle);
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

            @Override // com.quicinc.voice.activation.IUVRSettingsService
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

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void loadDefaultVoiceModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().loadDefaultVoiceModel(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void removeUVRModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeUVRModel(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRSettingsService
            public void setParams(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setParams(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUVRSettingsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUVRSettingsService)) {
                return (IUVRSettingsService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUVRSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUVRSettingsService iUVRSettingsService) {
            if (Proxy.sDefaultImpl == null) {
                if (iUVRSettingsService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUVRSettingsService;
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
                        IResultCallback asInterface = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        setParams(asInterface, bundle);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        getParams(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        boolean isUVRAvailable = isUVRAvailable(bundle);
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
                        IResultCallback asInterface2 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        enableUVR(asInterface2, bundle);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface3 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        disableUVR(asInterface3, bundle);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface4 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        removeUVRModel(asInterface4, bundle);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface5 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        loadDefaultVoiceModel(asInterface5, bundle);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        List<Bundle> enrolledUsers = getEnrolledUsers(bundle);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(enrolledUsers);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void disableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void enableUVR(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    List<Bundle> getEnrolledUsers(Bundle bundle) throws RemoteException;

    void getParams(IResultCallback iResultCallback) throws RemoteException;

    boolean isUVRAvailable(Bundle bundle) throws RemoteException;

    boolean isUVREnrolled(Bundle bundle) throws RemoteException;

    void loadDefaultVoiceModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void removeUVRModel(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void setParams(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;
}
