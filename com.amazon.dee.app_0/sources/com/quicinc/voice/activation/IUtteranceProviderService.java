package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IUtteranceReceiverCallback;
/* loaded from: classes3.dex */
public interface IUtteranceProviderService extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUtteranceProviderService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUtteranceProviderService
        public void registerAudioReceiverCallback(IUtteranceReceiverCallback iUtteranceReceiverCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUtteranceProviderService
        public void stopRecording(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUtteranceProviderService {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IUtteranceProviderService";
        static final int TRANSACTION_registerAudioReceiverCallback = 1;
        static final int TRANSACTION_stopRecording = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUtteranceProviderService {
            public static IUtteranceProviderService sDefaultImpl;
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

            @Override // com.quicinc.voice.activation.IUtteranceProviderService
            public void registerAudioReceiverCallback(IUtteranceReceiverCallback iUtteranceReceiverCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUtteranceReceiverCallback != null ? iUtteranceReceiverCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerAudioReceiverCallback(iUtteranceReceiverCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUtteranceProviderService
            public void stopRecording(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopRecording(iResultCallback, bundle);
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

        public static IUtteranceProviderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUtteranceProviderService)) {
                return (IUtteranceProviderService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUtteranceProviderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUtteranceProviderService iUtteranceProviderService) {
            if (Proxy.sDefaultImpl == null) {
                if (iUtteranceProviderService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUtteranceProviderService;
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
                IUtteranceReceiverCallback asInterface = IUtteranceReceiverCallback.Stub.asInterface(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                registerAudioReceiverCallback(asInterface, bundle);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                IResultCallback asInterface2 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                stopRecording(asInterface2, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void registerAudioReceiverCallback(IUtteranceReceiverCallback iUtteranceReceiverCallback, Bundle bundle) throws RemoteException;

    void stopRecording(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;
}
