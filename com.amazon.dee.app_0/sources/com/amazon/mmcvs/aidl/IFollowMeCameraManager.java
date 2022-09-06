package com.amazon.mmcvs.aidl;

import android.graphics.RectF;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.amazon.mmcvs.aidl.IFollowMeCameraListener;
/* loaded from: classes9.dex */
public interface IFollowMeCameraManager extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IFollowMeCameraManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
        public RectF getROI() throws RemoteException {
            return null;
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
        public boolean isEnabled() throws RemoteException {
            return false;
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
        public Surface registerClient(int i, int i2, IFollowMeCameraListener iFollowMeCameraListener) throws RemoteException {
            return null;
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
        public void setEnableStatus(boolean z) throws RemoteException {
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
        public void unregisterClient() throws RemoteException {
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IFollowMeCameraManager {
        private static final String DESCRIPTOR = "com.amazon.mmcvs.aidl.IFollowMeCameraManager";
        static final int TRANSACTION_getROI = 3;
        static final int TRANSACTION_isEnabled = 4;
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_setEnableStatus = 5;
        static final int TRANSACTION_unregisterClient = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IFollowMeCameraManager {
            public static IFollowMeCameraManager sDefaultImpl;
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

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
            public RectF getROI() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getROI();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (RectF) RectF.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
            public boolean isEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnabled();
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

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
            public Surface registerClient(int i, int i2, IFollowMeCameraListener iFollowMeCameraListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    Surface surface = null;
                    obtain.writeStrongBinder(iFollowMeCameraListener != null ? iFollowMeCameraListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerClient(i, i2, iFollowMeCameraListener);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        surface = (Surface) Surface.CREATOR.createFromParcel(obtain2);
                    }
                    return surface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
            public void setEnableStatus(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEnableStatus(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraManager
            public void unregisterClient() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterClient();
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

        public static IFollowMeCameraManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IFollowMeCameraManager)) {
                return (IFollowMeCameraManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IFollowMeCameraManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IFollowMeCameraManager iFollowMeCameraManager) {
            if (Proxy.sDefaultImpl == null) {
                if (iFollowMeCameraManager == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iFollowMeCameraManager;
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
            boolean z = false;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Surface registerClient = registerClient(parcel.readInt(), parcel.readInt(), IFollowMeCameraListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (registerClient != null) {
                    parcel2.writeInt(1);
                    registerClient.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterClient();
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                RectF roi = getROI();
                parcel2.writeNoException();
                if (roi != null) {
                    parcel2.writeInt(1);
                    roi.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isEnabled = isEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(isEnabled ? 1 : 0);
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    z = true;
                }
                setEnableStatus(z);
                parcel2.writeNoException();
                return true;
            }
        }
    }

    RectF getROI() throws RemoteException;

    boolean isEnabled() throws RemoteException;

    Surface registerClient(int i, int i2, IFollowMeCameraListener iFollowMeCameraListener) throws RemoteException;

    void setEnableStatus(boolean z) throws RemoteException;

    void unregisterClient() throws RemoteException;
}
