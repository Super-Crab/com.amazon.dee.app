package com.amazon.alexa.devices.sdk.presentation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.presentation.ICallStatusCallback;
/* loaded from: classes6.dex */
public interface ILocalSkillEndpoint extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements ILocalSkillEndpoint {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint
        public void sendAskResponse(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements ILocalSkillEndpoint {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint";
        static final int TRANSACTION_sendAskResponse = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements ILocalSkillEndpoint {
            public static ILocalSkillEndpoint sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint
            public void sendAskResponse(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iCallStatusCallback != null ? iCallStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendAskResponse(str, str2, iCallStatusCallback);
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

        public static ILocalSkillEndpoint asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ILocalSkillEndpoint)) {
                return (ILocalSkillEndpoint) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ILocalSkillEndpoint getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ILocalSkillEndpoint iLocalSkillEndpoint) {
            if (Proxy.sDefaultImpl == null) {
                if (iLocalSkillEndpoint == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iLocalSkillEndpoint;
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
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            sendAskResponse(parcel.readString(), parcel.readString(), ICallStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void sendAskResponse(String str, String str2, ICallStatusCallback iCallStatusCallback) throws RemoteException;
}
