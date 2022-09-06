package com.amazon.alexa.devices.sdk.presentation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.SkillEndpointContext;
import com.amazon.alexa.devices.sdk.presentation.ICallStatusCallback;
/* loaded from: classes6.dex */
public interface ICustomApplication extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements ICustomApplication {
        @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
        public void addOrUpdateSkillEndpointContext(String str, SkillEndpointContext skillEndpointContext, ICallStatusCallback iCallStatusCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
        public void removeSkillEndpointContext(String str, ICallStatusCallback iCallStatusCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
        public void sendAskUserEvent(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements ICustomApplication {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.presentation.ICustomApplication";
        static final int TRANSACTION_addOrUpdateSkillEndpointContext = 2;
        static final int TRANSACTION_removeSkillEndpointContext = 3;
        static final int TRANSACTION_sendAskUserEvent = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements ICustomApplication {
            public static ICustomApplication sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
            public void addOrUpdateSkillEndpointContext(String str, SkillEndpointContext skillEndpointContext, ICallStatusCallback iCallStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (skillEndpointContext != null) {
                        obtain.writeInt(1);
                        skillEndpointContext.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iCallStatusCallback != null ? iCallStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addOrUpdateSkillEndpointContext(str, skillEndpointContext, iCallStatusCallback);
                    } else {
                        obtain2.readException();
                    }
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

            @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
            public void removeSkillEndpointContext(String str, ICallStatusCallback iCallStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iCallStatusCallback != null ? iCallStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeSkillEndpointContext(str, iCallStatusCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.presentation.ICustomApplication
            public void sendAskUserEvent(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iCallStatusCallback != null ? iCallStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendAskUserEvent(str, str2, iCallStatusCallback);
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

        public static ICustomApplication asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICustomApplication)) {
                return (ICustomApplication) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ICustomApplication getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICustomApplication iCustomApplication) {
            if (Proxy.sDefaultImpl == null) {
                if (iCustomApplication == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iCustomApplication;
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
                sendAskUserEvent(parcel.readString(), parcel.readString(), ICallStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                addOrUpdateSkillEndpointContext(parcel.readString(), parcel.readInt() != 0 ? SkillEndpointContext.CREATOR.createFromParcel(parcel) : null, ICallStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
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
                removeSkillEndpointContext(parcel.readString(), ICallStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void addOrUpdateSkillEndpointContext(String str, SkillEndpointContext skillEndpointContext, ICallStatusCallback iCallStatusCallback) throws RemoteException;

    void removeSkillEndpointContext(String str, ICallStatusCallback iCallStatusCallback) throws RemoteException;

    void sendAskUserEvent(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException;
}
