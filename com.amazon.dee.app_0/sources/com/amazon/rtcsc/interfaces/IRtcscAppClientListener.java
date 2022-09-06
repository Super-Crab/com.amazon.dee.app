package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscAppClientListener extends IInterface {
    public static final int API_VERSION = 4;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscAppClientListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onError(RtcscErrorCode rtcscErrorCode, String str) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onFirstFrameReceived(String str, RtcscMediaType rtcscMediaType) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onFirstFrameRendered(String str, RtcscSide rtcscSide) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onMediaConnectionStateChanged(String str, RtcscMediaConnectionState rtcscMediaConnectionState) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onSessionAvailable(String str) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onSessionError(String str, RtcscErrorCode rtcscErrorCode, String str2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onSessionRemoved(String str) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onSessionStateChanged(String str, RtcscSessionState rtcscSessionState) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
        public void onVideoEffectChanged(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscAppClientListener {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscAppClientListener";
        static final int TRANSACTION_getAPIVersion = 8;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onFirstFrameReceived = 11;
        static final int TRANSACTION_onFirstFrameRendered = 10;
        static final int TRANSACTION_onMediaConnectionStateChanged = 9;
        static final int TRANSACTION_onMediaStatusChanged = 6;
        static final int TRANSACTION_onSessionAvailable = 1;
        static final int TRANSACTION_onSessionError = 4;
        static final int TRANSACTION_onSessionRemoved = 2;
        static final int TRANSACTION_onSessionStateChanged = 5;
        static final int TRANSACTION_onVideoEffectChanged = 7;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscAppClientListener {
            public static IRtcscAppClientListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onError(RtcscErrorCode rtcscErrorCode, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscErrorCode != null) {
                        obtain.writeInt(1);
                        rtcscErrorCode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(rtcscErrorCode, str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onFirstFrameReceived(String str, RtcscMediaType rtcscMediaType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscMediaType != null) {
                        obtain.writeInt(1);
                        rtcscMediaType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFirstFrameReceived(str, rtcscMediaType);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onFirstFrameRendered(String str, RtcscSide rtcscSide) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscSide != null) {
                        obtain.writeInt(1);
                        rtcscSide.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFirstFrameRendered(str, rtcscSide);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onMediaConnectionStateChanged(String str, RtcscMediaConnectionState rtcscMediaConnectionState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscMediaConnectionState != null) {
                        obtain.writeInt(1);
                        rtcscMediaConnectionState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaConnectionStateChanged(str, rtcscMediaConnectionState);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    int i = 0;
                    if (rtcscSide != null) {
                        obtain.writeInt(1);
                        rtcscSide.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (rtcscMediaType != null) {
                        obtain.writeInt(1);
                        rtcscMediaType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaStatusChanged(str, rtcscSide, rtcscMediaType, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onSessionAvailable(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionAvailable(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onSessionError(String str, RtcscErrorCode rtcscErrorCode, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscErrorCode != null) {
                        obtain.writeInt(1);
                        rtcscErrorCode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionError(str, rtcscErrorCode, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onSessionRemoved(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionRemoved(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onSessionStateChanged(String str, RtcscSessionState rtcscSessionState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscSessionState != null) {
                        obtain.writeInt(1);
                        rtcscSessionState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionStateChanged(str, rtcscSessionState);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscAppClientListener
            public void onVideoEffectChanged(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscVideoEffect != null) {
                        obtain.writeInt(1);
                        rtcscVideoEffect.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVideoEffectChanged(str, rtcscVideoEffect, i);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscAppClientListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscAppClientListener)) {
                return (IRtcscAppClientListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscAppClientListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscAppClientListener iRtcscAppClientListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscAppClientListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscAppClientListener;
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
                RtcscErrorCode rtcscErrorCode = null;
                RtcscMediaType rtcscMediaType = null;
                RtcscSide rtcscSide = null;
                RtcscMediaConnectionState rtcscMediaConnectionState = null;
                RtcscVideoEffect rtcscVideoEffect = null;
                RtcscMediaType rtcscMediaType2 = null;
                RtcscSessionState rtcscSessionState = null;
                RtcscErrorCode rtcscErrorCode2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        onSessionAvailable(parcel.readString());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        onSessionRemoved(parcel.readString());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscErrorCode = RtcscErrorCode.CREATOR.createFromParcel(parcel);
                        }
                        onError(rtcscErrorCode, parcel.readString());
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscErrorCode2 = RtcscErrorCode.CREATOR.createFromParcel(parcel);
                        }
                        onSessionError(readString, rtcscErrorCode2, parcel.readString());
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscSessionState = RtcscSessionState.CREATOR.createFromParcel(parcel);
                        }
                        onSessionStateChanged(readString2, rtcscSessionState);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString3 = parcel.readString();
                        RtcscSide createFromParcel = parcel.readInt() != 0 ? RtcscSide.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            rtcscMediaType2 = RtcscMediaType.CREATOR.createFromParcel(parcel);
                        }
                        onMediaStatusChanged(readString3, createFromParcel, rtcscMediaType2, parcel.readInt() != 0);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString4 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscVideoEffect = RtcscVideoEffect.CREATOR.createFromParcel(parcel);
                        }
                        onVideoEffectChanged(readString4, rtcscVideoEffect, parcel.readInt());
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        int aPIVersion = getAPIVersion();
                        parcel2.writeNoException();
                        parcel2.writeInt(aPIVersion);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString5 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscMediaConnectionState = RtcscMediaConnectionState.CREATOR.createFromParcel(parcel);
                        }
                        onMediaConnectionStateChanged(readString5, rtcscMediaConnectionState);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString6 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscSide = RtcscSide.CREATOR.createFromParcel(parcel);
                        }
                        onFirstFrameRendered(readString6, rtcscSide);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString7 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscMediaType = RtcscMediaType.CREATOR.createFromParcel(parcel);
                        }
                        onFirstFrameReceived(readString7, rtcscMediaType);
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

    void onError(RtcscErrorCode rtcscErrorCode, String str) throws RemoteException;

    void onFirstFrameReceived(String str, RtcscMediaType rtcscMediaType) throws RemoteException;

    void onFirstFrameRendered(String str, RtcscSide rtcscSide) throws RemoteException;

    void onMediaConnectionStateChanged(String str, RtcscMediaConnectionState rtcscMediaConnectionState) throws RemoteException;

    void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) throws RemoteException;

    void onSessionAvailable(String str) throws RemoteException;

    void onSessionError(String str, RtcscErrorCode rtcscErrorCode, String str2) throws RemoteException;

    void onSessionRemoved(String str) throws RemoteException;

    void onSessionStateChanged(String str, RtcscSessionState rtcscSessionState) throws RemoteException;

    void onVideoEffectChanged(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException;
}
