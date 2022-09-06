package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscDataChannelListener extends IInterface {
    public static final int API_VERSION = 1;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscDataChannelListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
        public void onMessageReceived(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
        public void onStateChange(String str, String str2, RtcscDataChannelState rtcscDataChannelState) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscDataChannelListener {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscDataChannelListener";
        static final int TRANSACTION_getAPIVersion = 3;
        static final int TRANSACTION_onMessageReceived = 2;
        static final int TRANSACTION_onStateChange = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscDataChannelListener {
            public static IRtcscDataChannelListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
            public void onMessageReceived(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMessageReceived(str, str2, bArr, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscDataChannelListener
            public void onStateChange(String str, String str2, RtcscDataChannelState rtcscDataChannelState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (rtcscDataChannelState != null) {
                        obtain.writeInt(1);
                        rtcscDataChannelState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStateChange(str, str2, rtcscDataChannelState);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscDataChannelListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscDataChannelListener)) {
                return (IRtcscDataChannelListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscDataChannelListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscDataChannelListener iRtcscDataChannelListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscDataChannelListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscDataChannelListener;
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
                onStateChange(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? RtcscDataChannelState.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onMessageReceived(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0);
                return true;
            } else if (i != 3) {
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

    void onMessageReceived(String str, String str2, byte[] bArr, boolean z) throws RemoteException;

    void onStateChange(String str, String str2, RtcscDataChannelState rtcscDataChannelState) throws RemoteException;
}
