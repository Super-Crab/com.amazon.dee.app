package com.amazon.alexa.devices.sdk;

import amazon.speech.audio.v2.AudioStreamReader;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;
/* loaded from: classes6.dex */
public interface IWakeWordDetectionListener extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IWakeWordDetectionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IWakeWordDetectionListener
        public void onStop(String str, int i) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IWakeWordDetectionListener
        public String onWakeWordDetected(String str, String str2, int i, long j, long j2, int i2, AudioStreamReader audioStreamReader, Map map, String str3) throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IWakeWordDetectionListener {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IWakeWordDetectionListener";
        static final int TRANSACTION_onStop = 2;
        static final int TRANSACTION_onWakeWordDetected = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IWakeWordDetectionListener {
            public static IWakeWordDetectionListener sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.IWakeWordDetectionListener
            public void onStop(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStop(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IWakeWordDetectionListener
            public String onWakeWordDetected(String str, String str2, int i, long j, long j2, int i2, AudioStreamReader audioStreamReader, Map map, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i2);
                    if (audioStreamReader != null) {
                        obtain.writeInt(1);
                        audioStreamReader.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeMap(map);
                    obtain.writeString(str3);
                    try {
                        if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            String onWakeWordDetected = Stub.getDefaultImpl().onWakeWordDetected(str, str2, i, j, j2, i2, audioStreamReader, map, str3);
                            obtain2.recycle();
                            obtain.recycle();
                            return onWakeWordDetected;
                        }
                        obtain2.readException();
                        String readString = obtain2.readString();
                        obtain2.recycle();
                        obtain.recycle();
                        return readString;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWakeWordDetectionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWakeWordDetectionListener)) {
                return (IWakeWordDetectionListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IWakeWordDetectionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWakeWordDetectionListener iWakeWordDetectionListener) {
            if (Proxy.sDefaultImpl == null) {
                if (iWakeWordDetectionListener == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iWakeWordDetectionListener;
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
                String onWakeWordDetected = onWakeWordDetected(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? AudioStreamReader.CREATOR.createFromParcel(parcel) : null, parcel.readHashMap(Stub.class.getClassLoader()), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(onWakeWordDetected);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onStop(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void onStop(String str, int i) throws RemoteException;

    String onWakeWordDetected(String str, String str2, int i, long j, long j2, int i2, AudioStreamReader audioStreamReader, Map map, String str3) throws RemoteException;
}
