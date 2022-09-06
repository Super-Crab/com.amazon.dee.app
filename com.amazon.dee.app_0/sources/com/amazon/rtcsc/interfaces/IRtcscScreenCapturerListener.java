package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscScreenCapturerListener extends IInterface {
    public static final int API_VERSION = 1;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscScreenCapturerListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
        public void onPutScreenCapturerData() throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscScreenCapturerListener {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener";
        static final int TRANSACTION_getAPIVersion = 2;
        static final int TRANSACTION_onPutScreenCapturerData = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscScreenCapturerListener {
            public static IRtcscScreenCapturerListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAPIVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
            public void onPutScreenCapturerData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPutScreenCapturerData();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscScreenCapturerListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscScreenCapturerListener)) {
                return (IRtcscScreenCapturerListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscScreenCapturerListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscScreenCapturerListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscScreenCapturerListener;
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
                onPutScreenCapturerData();
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                int aPIVersion = getAPIVersion();
                parcel2.writeNoException();
                parcel2.writeInt(aPIVersion);
                return true;
            }
        }
    }

    int getAPIVersion() throws RemoteException;

    void onPutScreenCapturerData() throws RemoteException;
}
