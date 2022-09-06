package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IUtteranceReceiverCallback extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUtteranceReceiverCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
        public void onUtteranceReceived(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
        public void onUtteranceVerificationFailure(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUtteranceReceiverCallback {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IUtteranceReceiverCallback";
        static final int TRANSACTION_onUtteranceReceived = 1;
        static final int TRANSACTION_onUtteranceVerificationFailure = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUtteranceReceiverCallback {
            public static IUtteranceReceiverCallback sDefaultImpl;
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

            @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
            public void onUtteranceReceived(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUtteranceReceived(parcelFileDescriptor, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
            public void onUtteranceVerificationFailure(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUtteranceVerificationFailure(parcelFileDescriptor, bundle);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUtteranceReceiverCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUtteranceReceiverCallback)) {
                return (IUtteranceReceiverCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUtteranceReceiverCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUtteranceReceiverCallback iUtteranceReceiverCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iUtteranceReceiverCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUtteranceReceiverCallback;
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
            Bundle bundle = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor parcelFileDescriptor = parcel.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                onUtteranceReceived(parcelFileDescriptor, bundle);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor parcelFileDescriptor2 = parcel.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                onUtteranceVerificationFailure(parcelFileDescriptor2, bundle);
                return true;
            }
        }
    }

    void onUtteranceReceived(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException;

    void onUtteranceVerificationFailure(ParcelFileDescriptor parcelFileDescriptor, Bundle bundle) throws RemoteException;
}
