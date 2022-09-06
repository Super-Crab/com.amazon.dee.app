package com.amazon.whisperjoin.deviceprovisioningservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface ProvisioningWorkflowEventCallback extends IInterface {

    /* loaded from: classes13.dex */
    public static class Default implements ProvisioningWorkflowEventCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onComplete() throws RemoteException {
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onError(String str, String str2, String str3) throws RemoteException {
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onNext(String str, String str2) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements ProvisioningWorkflowEventCallback {
        private static final String DESCRIPTOR = "com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback";
        static final int TRANSACTION_onComplete = 1;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onNext = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements ProvisioningWorkflowEventCallback {
            public static ProvisioningWorkflowEventCallback sDefaultImpl;
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

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
            public void onComplete() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
            public void onError(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(str, str2, str3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
            public void onNext(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNext(str, str2);
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

        public static ProvisioningWorkflowEventCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ProvisioningWorkflowEventCallback)) {
                return (ProvisioningWorkflowEventCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ProvisioningWorkflowEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (provisioningWorkflowEventCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = provisioningWorkflowEventCallback;
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
                onComplete();
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNext(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void onComplete() throws RemoteException;

    void onError(String str, String str2, String str3) throws RemoteException;

    void onNext(String str, String str2) throws RemoteException;
}
