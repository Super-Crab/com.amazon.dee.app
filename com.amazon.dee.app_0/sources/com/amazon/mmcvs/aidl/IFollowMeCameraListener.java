package com.amazon.mmcvs.aidl;

import android.graphics.RectF;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes9.dex */
public interface IFollowMeCameraListener extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IFollowMeCameraListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.mmcvs.aidl.IFollowMeCameraListener
        public void onNewROIAvailable(RectF rectF) throws RemoteException {
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IFollowMeCameraListener {
        private static final String DESCRIPTOR = "com.amazon.mmcvs.aidl.IFollowMeCameraListener";
        static final int TRANSACTION_onNewROIAvailable = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IFollowMeCameraListener {
            public static IFollowMeCameraListener sDefaultImpl;
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

            @Override // com.amazon.mmcvs.aidl.IFollowMeCameraListener
            public void onNewROIAvailable(RectF rectF) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rectF != null) {
                        obtain.writeInt(1);
                        rectF.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNewROIAvailable(rectF);
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

        public static IFollowMeCameraListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IFollowMeCameraListener)) {
                return (IFollowMeCameraListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IFollowMeCameraListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IFollowMeCameraListener iFollowMeCameraListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iFollowMeCameraListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iFollowMeCameraListener;
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
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            onNewROIAvailable(parcel.readInt() != 0 ? (RectF) RectF.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void onNewROIAvailable(RectF rectF) throws RemoteException;
}
