package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IUVRUtteranceTrainingCallback extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUVRUtteranceTrainingCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onError(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onFeedback(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onStartProcessing(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onStartRecording(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onStopProcessing(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onStopRecording(Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
        public void onSuccess(Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUVRUtteranceTrainingCallback {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IUVRUtteranceTrainingCallback";
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onFeedback = 7;
        static final int TRANSACTION_onStartProcessing = 3;
        static final int TRANSACTION_onStartRecording = 1;
        static final int TRANSACTION_onStopProcessing = 4;
        static final int TRANSACTION_onStopRecording = 2;
        static final int TRANSACTION_onSuccess = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUVRUtteranceTrainingCallback {
            public static IUVRUtteranceTrainingCallback sDefaultImpl;
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

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onError(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onFeedback(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFeedback(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onStartProcessing(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartProcessing(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onStartRecording(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartRecording(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onStopProcessing(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStopProcessing(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onStopRecording(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStopRecording(bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
            public void onSuccess(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuccess(bundle);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUVRUtteranceTrainingCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUVRUtteranceTrainingCallback)) {
                return (IUVRUtteranceTrainingCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUVRUtteranceTrainingCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUVRUtteranceTrainingCallback iUVRUtteranceTrainingCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iUVRUtteranceTrainingCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUVRUtteranceTrainingCallback;
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
                Bundle bundle = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onStartRecording(bundle);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onStopRecording(bundle);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onStartProcessing(bundle);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onStopProcessing(bundle);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onSuccess(bundle);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onError(bundle);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onFeedback(bundle);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onError(Bundle bundle) throws RemoteException;

    void onFeedback(Bundle bundle) throws RemoteException;

    void onStartProcessing(Bundle bundle) throws RemoteException;

    void onStartRecording(Bundle bundle) throws RemoteException;

    void onStopProcessing(Bundle bundle) throws RemoteException;

    void onStopRecording(Bundle bundle) throws RemoteException;

    void onSuccess(Bundle bundle) throws RemoteException;
}
