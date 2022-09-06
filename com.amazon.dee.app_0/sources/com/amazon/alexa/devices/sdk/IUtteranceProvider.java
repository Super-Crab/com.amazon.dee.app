package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.IWakeWordDetectionListener;
import java.util.List;
/* loaded from: classes6.dex */
public interface IUtteranceProvider extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IUtteranceProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public boolean listen(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public int startRecognition(List<String> list, int i, IWakeWordDetectionListener iWakeWordDetectionListener) throws RemoteException {
            return 0;
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public boolean stopCapture(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public int stopRecognition(int i) throws RemoteException {
            return 0;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IUtteranceProvider {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IUtteranceProvider";
        static final int TRANSACTION_listen = 4;
        static final int TRANSACTION_startRecognition = 1;
        static final int TRANSACTION_stopCapture = 3;
        static final int TRANSACTION_stopRecognition = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IUtteranceProvider {
            public static IUtteranceProvider sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
            public boolean listen(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listen(i, str);
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

            @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
            public int startRecognition(List<String> list, int i, IWakeWordDetectionListener iWakeWordDetectionListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iWakeWordDetectionListener != null ? iWakeWordDetectionListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecognition(list, i, iWakeWordDetectionListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
            public boolean stopCapture(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopCapture(i, str);
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

            @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
            public int stopRecognition(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopRecognition(i);
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

        public static IUtteranceProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUtteranceProvider)) {
                return (IUtteranceProvider) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IUtteranceProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IUtteranceProvider iUtteranceProvider) {
            if (Proxy.sDefaultImpl == null) {
                if (iUtteranceProvider == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iUtteranceProvider;
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
                int startRecognition = startRecognition(parcel.createStringArrayList(), parcel.readInt(), IWakeWordDetectionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(startRecognition);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int stopRecognition = stopRecognition(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(stopRecognition);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean stopCapture = stopCapture(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(stopCapture ? 1 : 0);
                return true;
            } else if (i != 4) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean listen = listen(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(listen ? 1 : 0);
                return true;
            }
        }
    }

    boolean listen(int i, String str) throws RemoteException;

    int startRecognition(List<String> list, int i, IWakeWordDetectionListener iWakeWordDetectionListener) throws RemoteException;

    boolean stopCapture(int i, String str) throws RemoteException;

    int stopRecognition(int i) throws RemoteException;
}
