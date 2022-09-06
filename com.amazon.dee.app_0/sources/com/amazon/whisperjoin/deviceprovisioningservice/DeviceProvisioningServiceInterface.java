package com.amazon.whisperjoin.deviceprovisioningservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
/* loaded from: classes13.dex */
public interface DeviceProvisioningServiceInterface extends IInterface {

    /* loaded from: classes13.dex */
    public static class Default implements DeviceProvisioningServiceInterface {
        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean addCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean isActive() throws RemoteException {
            return false;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean removeCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
            return false;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean shutdown() throws RemoteException {
            return false;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean start(ProvisioningServiceConfiguration provisioningServiceConfiguration) throws RemoteException {
            return false;
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements DeviceProvisioningServiceInterface {
        private static final String DESCRIPTOR = "com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface";
        static final int TRANSACTION_addCallback = 3;
        static final int TRANSACTION_isActive = 1;
        static final int TRANSACTION_removeCallback = 4;
        static final int TRANSACTION_shutdown = 5;
        static final int TRANSACTION_start = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements DeviceProvisioningServiceInterface {
            public static DeviceProvisioningServiceInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
            public boolean addCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(provisioningWorkflowEventCallback != null ? provisioningWorkflowEventCallback.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addCallback(provisioningWorkflowEventCallback);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
            public boolean isActive() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActive();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
            public boolean removeCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(provisioningWorkflowEventCallback != null ? provisioningWorkflowEventCallback.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeCallback(provisioningWorkflowEventCallback);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
            public boolean shutdown() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shutdown();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
            public boolean start(ProvisioningServiceConfiguration provisioningServiceConfiguration) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (provisioningServiceConfiguration != null) {
                        obtain.writeInt(1);
                        provisioningServiceConfiguration.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().start(provisioningServiceConfiguration);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static DeviceProvisioningServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceProvisioningServiceInterface)) {
                return (DeviceProvisioningServiceInterface) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static DeviceProvisioningServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(DeviceProvisioningServiceInterface deviceProvisioningServiceInterface) {
            if (Proxy.sDefaultImpl == null) {
                if (deviceProvisioningServiceInterface == null) {
                    return false;
                }
                Proxy.sDefaultImpl = deviceProvisioningServiceInterface;
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
                boolean isActive = isActive();
                parcel2.writeNoException();
                parcel2.writeInt(isActive ? 1 : 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean start = start(parcel.readInt() != 0 ? ProvisioningServiceConfiguration.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(start ? 1 : 0);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean addCallback = addCallback(ProvisioningWorkflowEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(addCallback ? 1 : 0);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean removeCallback = removeCallback(ProvisioningWorkflowEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(removeCallback ? 1 : 0);
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean shutdown = shutdown();
                parcel2.writeNoException();
                parcel2.writeInt(shutdown ? 1 : 0);
                return true;
            }
        }
    }

    boolean addCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException;

    boolean isActive() throws RemoteException;

    boolean removeCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException;

    boolean shutdown() throws RemoteException;

    boolean start(ProvisioningServiceConfiguration provisioningServiceConfiguration) throws RemoteException;
}
