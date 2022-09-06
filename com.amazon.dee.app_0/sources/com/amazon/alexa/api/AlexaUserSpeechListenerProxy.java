package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaUserSpeechListenerProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaUserSpeechListenerProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaUserSpeechListenerProxy
        public void onAlexaUserSpeechVolumeChanged(float f) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaUserSpeechListenerProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaUserSpeechListenerProxy";
        static final int TRANSACTION_onAlexaUserSpeechVolumeChanged = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaUserSpeechListenerProxy {
            public static AlexaUserSpeechListenerProxy sDefaultImpl;
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

            @Override // com.amazon.alexa.api.AlexaUserSpeechListenerProxy
            public void onAlexaUserSpeechVolumeChanged(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAlexaUserSpeechVolumeChanged(f);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaUserSpeechListenerProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaUserSpeechListenerProxy)) ? new Proxy(iBinder) : (AlexaUserSpeechListenerProxy) queryLocalInterface;
        }

        public static AlexaUserSpeechListenerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaUserSpeechListenerProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaUserSpeechListenerProxy;
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
                onAlexaUserSpeechVolumeChanged(parcel.readFloat());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onAlexaUserSpeechVolumeChanged(float f) throws RemoteException;
}
