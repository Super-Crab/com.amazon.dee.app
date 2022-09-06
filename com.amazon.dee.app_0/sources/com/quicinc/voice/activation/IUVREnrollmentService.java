package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IUVRUtteranceTrainingCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface IUVREnrollmentService extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUVREnrollmentService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public void cancelUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public void cancelUtteranceTraining(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public void finishUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public List<Bundle> getUtterancesInfo(Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public void startUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IUVREnrollmentService
        public void startUtteranceTraining(IUVRUtteranceTrainingCallback iUVRUtteranceTrainingCallback, Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUVREnrollmentService {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IUVREnrollmentService";
        static final int TRANSACTION_cancelUserVoiceEnrollment = 3;
        static final int TRANSACTION_cancelUtteranceTraining = 6;
        static final int TRANSACTION_finishUserVoiceEnrollment = 2;
        static final int TRANSACTION_getUtterancesInfo = 4;
        static final int TRANSACTION_startUserVoiceEnrollment = 1;
        static final int TRANSACTION_startUtteranceTraining = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUVREnrollmentService {
            public static IUVREnrollmentService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public void cancelUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelUserVoiceEnrollment(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public void cancelUtteranceTraining(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelUtteranceTraining(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public void finishUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finishUserVoiceEnrollment(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public List<Bundle> getUtterancesInfo(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUtterancesInfo(bundle);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public void startUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startUserVoiceEnrollment(iResultCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IUVREnrollmentService
            public void startUtteranceTraining(IUVRUtteranceTrainingCallback iUVRUtteranceTrainingCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUVRUtteranceTrainingCallback != null ? iUVRUtteranceTrainingCallback.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startUtteranceTraining(iUVRUtteranceTrainingCallback, bundle);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUVREnrollmentService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUVREnrollmentService)) {
                return (IUVREnrollmentService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUVREnrollmentService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUVREnrollmentService iUVREnrollmentService) {
            if (Proxy.sDefaultImpl == null) {
                if (iUVREnrollmentService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUVREnrollmentService;
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
                        IResultCallback asInterface = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        startUserVoiceEnrollment(asInterface, bundle);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface2 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        finishUserVoiceEnrollment(asInterface2, bundle);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface3 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        cancelUserVoiceEnrollment(asInterface3, bundle);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        List<Bundle> utterancesInfo = getUtterancesInfo(bundle);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(utterancesInfo);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        IUVRUtteranceTrainingCallback asInterface4 = IUVRUtteranceTrainingCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        startUtteranceTraining(asInterface4, bundle);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        IResultCallback asInterface5 = IResultCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        cancelUtteranceTraining(asInterface5, bundle);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void cancelUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void cancelUtteranceTraining(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void finishUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    List<Bundle> getUtterancesInfo(Bundle bundle) throws RemoteException;

    void startUserVoiceEnrollment(IResultCallback iResultCallback, Bundle bundle) throws RemoteException;

    void startUtteranceTraining(IUVRUtteranceTrainingCallback iUVRUtteranceTrainingCallback, Bundle bundle) throws RemoteException;
}
