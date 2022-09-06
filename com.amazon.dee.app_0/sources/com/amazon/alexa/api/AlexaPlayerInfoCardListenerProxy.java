package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaPlayerInfoCardListenerProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaPlayerInfoCardListenerProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
        public void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
        public void onReceivedPlayerInfoCard(String str, boolean z) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaPlayerInfoCardListenerProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy";
        static final int TRANSACTION_onAudioItemStateChanged = 2;
        static final int TRANSACTION_onReceivedPlayerInfoCard = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaPlayerInfoCardListenerProxy {
            public static AlexaPlayerInfoCardListenerProxy sDefaultImpl;
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

            @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
            public void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (alexaPlayerInfoState != null) {
                        obtain.writeInt(1);
                        alexaPlayerInfoState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAudioItemStateChanged(alexaPlayerInfoState, str, j);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
            public void onReceivedPlayerInfoCard(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReceivedPlayerInfoCard(str, z);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaPlayerInfoCardListenerProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaPlayerInfoCardListenerProxy)) ? new Proxy(iBinder) : (AlexaPlayerInfoCardListenerProxy) queryLocalInterface;
        }

        public static AlexaPlayerInfoCardListenerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaPlayerInfoCardListenerProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaPlayerInfoCardListenerProxy;
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
                onReceivedPlayerInfoCard(parcel.readString(), parcel.readInt() != 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onAudioItemStateChanged(parcel.readInt() != 0 ? AlexaPlayerInfoState.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readLong());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) throws RemoteException;

    void onReceivedPlayerInfoCard(String str, boolean z) throws RemoteException;
}
