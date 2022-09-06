package com.magiear.handsfree.util;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.magiear.handsfree.util.IEnrollmentCallback;
import com.magiear.handsfree.util.IResultCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface IUVREnroller extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IUVREnroller {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public void cancelUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public void cancelUtteranceTraining(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public void finishUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public List<Bundle> getUtterances() throws RemoteException {
            return null;
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public void startUserVoiceEnrollment(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IUVREnroller
        public void startUtteranceTraining(Bundle bundle, IEnrollmentCallback iEnrollmentCallback) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUVREnroller {
        private static final String DESCRIPTOR = "com.magiear.handsfree.util.IUVREnroller";
        static final int TRANSACTION_cancelUserVoiceEnrollment = 2;
        static final int TRANSACTION_cancelUtteranceTraining = 3;
        static final int TRANSACTION_finishUserVoiceEnrollment = 6;
        static final int TRANSACTION_getUtterances = 1;
        static final int TRANSACTION_startUserVoiceEnrollment = 4;
        static final int TRANSACTION_startUtteranceTraining = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUVREnroller {
            public static IUVREnroller sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public void cancelUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelUserVoiceEnrollment(iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public void cancelUtteranceTraining(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelUtteranceTraining(iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public void finishUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishUserVoiceEnrollment(iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public List<Bundle> getUtterances() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUtterances();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public void startUserVoiceEnrollment(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
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
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startUserVoiceEnrollment(bundle, iResultCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IUVREnroller
            public void startUtteranceTraining(Bundle bundle, IEnrollmentCallback iEnrollmentCallback) throws RemoteException {
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
                    obtain.writeStrongBinder(iEnrollmentCallback != null ? iEnrollmentCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startUtteranceTraining(bundle, iEnrollmentCallback);
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

        public static IUVREnroller asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUVREnroller)) {
                return (IUVREnroller) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUVREnroller getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUVREnroller iUVREnroller) {
            if (Proxy.sDefaultImpl == null) {
                if (iUVREnroller == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUVREnroller;
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
                        List<Bundle> utterances = getUtterances();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(utterances);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelUserVoiceEnrollment(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelUtteranceTraining(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        startUserVoiceEnrollment(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        startUtteranceTraining(bundle, IEnrollmentCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        finishUserVoiceEnrollment(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void cancelUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException;

    void cancelUtteranceTraining(IResultCallback iResultCallback) throws RemoteException;

    void finishUserVoiceEnrollment(IResultCallback iResultCallback) throws RemoteException;

    List<Bundle> getUtterances() throws RemoteException;

    void startUserVoiceEnrollment(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;

    void startUtteranceTraining(Bundle bundle, IEnrollmentCallback iEnrollmentCallback) throws RemoteException;
}
