package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscViewRendererListener extends IInterface {
    public static final int API_VERSION = 1;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscViewRendererListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public int onGetHeight() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public int onGetWidth() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public void onHolderSetFixedSize(int i, int i2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public void onHolderSetSizeFromLayout() throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public void onMeasuredDimension(int i, int i2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
        public void onRequestLayout() throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscViewRendererListener {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscViewRendererListener";
        static final int TRANSACTION_getAPIVersion = 7;
        static final int TRANSACTION_onGetHeight = 6;
        static final int TRANSACTION_onGetWidth = 5;
        static final int TRANSACTION_onHolderSetFixedSize = 2;
        static final int TRANSACTION_onHolderSetSizeFromLayout = 1;
        static final int TRANSACTION_onMeasuredDimension = 4;
        static final int TRANSACTION_onRequestLayout = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscViewRendererListener {
            public static IRtcscViewRendererListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public int onGetHeight() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onGetHeight();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public int onGetWidth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onGetWidth();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public void onHolderSetFixedSize(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHolderSetFixedSize(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public void onHolderSetSizeFromLayout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHolderSetSizeFromLayout();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public void onMeasuredDimension(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMeasuredDimension(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
            public void onRequestLayout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestLayout();
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

        public static IRtcscViewRendererListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscViewRendererListener)) {
                return (IRtcscViewRendererListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscViewRendererListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscViewRendererListener iRtcscViewRendererListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscViewRendererListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscViewRendererListener;
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
                        onHolderSetSizeFromLayout();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        onHolderSetFixedSize(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        onRequestLayout();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        onMeasuredDimension(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int onGetWidth = onGetWidth();
                        parcel2.writeNoException();
                        parcel2.writeInt(onGetWidth);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int onGetHeight = onGetHeight();
                        parcel2.writeNoException();
                        parcel2.writeInt(onGetHeight);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        int aPIVersion = getAPIVersion();
                        parcel2.writeNoException();
                        parcel2.writeInt(aPIVersion);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    int getAPIVersion() throws RemoteException;

    int onGetHeight() throws RemoteException;

    int onGetWidth() throws RemoteException;

    void onHolderSetFixedSize(int i, int i2) throws RemoteException;

    void onHolderSetSizeFromLayout() throws RemoteException;

    void onMeasuredDimension(int i, int i2) throws RemoteException;

    void onRequestLayout() throws RemoteException;
}
