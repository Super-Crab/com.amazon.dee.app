package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.INotificationCallback;
/* loaded from: classes6.dex */
public interface INotificationComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements INotificationComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.INotificationComponent
        public void subscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.INotificationComponent
        public void unsubscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements INotificationComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.INotificationComponent";
        static final int TRANSACTION_subscribe = 1;
        static final int TRANSACTION_unsubscribe = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements INotificationComponent {
            public static INotificationComponent sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.INotificationComponent
            public void subscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iNotificationCallback != null ? iNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribe(str, iNotificationCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.INotificationComponent
            public void unsubscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iNotificationCallback != null ? iNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribe(str, iNotificationCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INotificationComponent)) {
                return (INotificationComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static INotificationComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(INotificationComponent iNotificationComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iNotificationComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iNotificationComponent;
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
                subscribe(parcel.readString(), INotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unsubscribe(parcel.readString(), INotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void subscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException;

    void unsubscribe(String str, INotificationCallback iNotificationCallback) throws RemoteException;
}
