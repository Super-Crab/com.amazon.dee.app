package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscEventListener extends IInterface {
    public static final int API_VERSION = 1;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
        public void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
        public void onRTCSessionContextUpdated(String str) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
        public void onSendEvent(String str, String str2, String str3, String str4) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscEventListener {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscEventListener";
        static final int TRANSACTION_getAPIVersion = 4;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onRTCSessionContextUpdated = 2;
        static final int TRANSACTION_onSendEvent = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscEventListener {
            public static IRtcscEventListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
            public void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscErrorCode != null) {
                        obtain.writeInt(1);
                        rtcscErrorCode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(rtcscErrorCode);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
            public void onRTCSessionContextUpdated(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRTCSessionContextUpdated(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscEventListener
            public void onSendEvent(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSendEvent(str, str2, str3, str4);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscEventListener)) {
                return (IRtcscEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscEventListener iRtcscEventListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscEventListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscEventListener;
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
                onSendEvent(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onRTCSessionContextUpdated(parcel.readString());
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt() != 0 ? RtcscErrorCode.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 4) {
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

    void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException;

    void onRTCSessionContextUpdated(String str) throws RemoteException;

    void onSendEvent(String str, String str2, String str3, String str4) throws RemoteException;
}
