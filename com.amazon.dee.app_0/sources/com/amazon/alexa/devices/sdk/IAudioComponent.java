package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.IUtteranceProvider;
/* loaded from: classes6.dex */
public interface IAudioComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IAudioComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IAudioComponent
        public boolean deregisterUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.devices.sdk.IAudioComponent
        public boolean registerUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.devices.sdk.IAudioComponent
        public void setAudioEncoding(int i) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IAudioComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IAudioComponent";
        static final int TRANSACTION_deregisterUtteranceProvider = 2;
        static final int TRANSACTION_registerUtteranceProvider = 1;
        static final int TRANSACTION_setAudioEncoding = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IAudioComponent {
            public static IAudioComponent sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.devices.sdk.IAudioComponent
            public boolean deregisterUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUtteranceProvider != null ? iUtteranceProvider.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deregisterUtteranceProvider(iUtteranceProvider);
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

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.devices.sdk.IAudioComponent
            public boolean registerUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUtteranceProvider != null ? iUtteranceProvider.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerUtteranceProvider(iUtteranceProvider);
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

            @Override // com.amazon.alexa.devices.sdk.IAudioComponent
            public void setAudioEncoding(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAudioEncoding(i);
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

        public static IAudioComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAudioComponent)) {
                return (IAudioComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAudioComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAudioComponent iAudioComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iAudioComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAudioComponent;
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
                boolean registerUtteranceProvider = registerUtteranceProvider(IUtteranceProvider.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(registerUtteranceProvider ? 1 : 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean deregisterUtteranceProvider = deregisterUtteranceProvider(IUtteranceProvider.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(deregisterUtteranceProvider ? 1 : 0);
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                setAudioEncoding(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    boolean deregisterUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException;

    boolean registerUtteranceProvider(IUtteranceProvider iUtteranceProvider) throws RemoteException;

    void setAudioEncoding(int i) throws RemoteException;
}
