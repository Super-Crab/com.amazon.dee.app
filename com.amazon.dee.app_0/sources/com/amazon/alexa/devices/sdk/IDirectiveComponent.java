package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
/* loaded from: classes6.dex */
public interface IDirectiveComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IDirectiveComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IDirectiveComponent
        public void deregisterListener(int i) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IDirectiveComponent
        public int registerListener(ResultReceiver resultReceiver) throws RemoteException {
            return 0;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IDirectiveComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IDirectiveComponent";
        static final int TRANSACTION_deregisterListener = 2;
        static final int TRANSACTION_registerListener = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IDirectiveComponent {
            public static IDirectiveComponent sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.devices.sdk.IDirectiveComponent
            public void deregisterListener(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deregisterListener(i);
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

            @Override // com.amazon.alexa.devices.sdk.IDirectiveComponent
            public int registerListener(ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerListener(resultReceiver);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDirectiveComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDirectiveComponent)) {
                return (IDirectiveComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IDirectiveComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDirectiveComponent iDirectiveComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iDirectiveComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iDirectiveComponent;
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
                int registerListener = registerListener(parcel.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(registerListener);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                deregisterListener(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void deregisterListener(int i) throws RemoteException;

    int registerListener(ResultReceiver resultReceiver) throws RemoteException;
}
