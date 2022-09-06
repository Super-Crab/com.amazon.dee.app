package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaTemplateCardListenerProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaTemplateCardListenerProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaTemplateCardListenerProxy
        public void onReceivedCard(String str) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaTemplateCardListenerProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaTemplateCardListenerProxy";
        static final int TRANSACTION_onReceivedCard = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaTemplateCardListenerProxy {
            public static AlexaTemplateCardListenerProxy sDefaultImpl;
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

            @Override // com.amazon.alexa.api.AlexaTemplateCardListenerProxy
            public void onReceivedCard(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReceivedCard(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaTemplateCardListenerProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaTemplateCardListenerProxy)) ? new Proxy(iBinder) : (AlexaTemplateCardListenerProxy) queryLocalInterface;
        }

        public static AlexaTemplateCardListenerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaTemplateCardListenerProxy alexaTemplateCardListenerProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaTemplateCardListenerProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaTemplateCardListenerProxy;
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
                onReceivedCard(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onReceivedCard(String str) throws RemoteException;
}
