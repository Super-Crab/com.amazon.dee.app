package com.amazon.communication;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.communication.IInputStream;
import com.amazon.dp.logger.DPLogger;
import java.lang.reflect.Method;
/* loaded from: classes12.dex */
public class LargeArrayOptimizedIInputStream implements IInputStream {
    private static final String DESCRIPTOR = "com.amazon.communication.IInputStream";
    private static final DPLogger log = new DPLogger("TComm.LargeArrayOptimizedIInputStream");
    private static boolean sIsOptimizedReadMethodAvailable;
    private static Method sOptimizedReadMethod;
    private final IBinder mRemote;
    private final IInputStream mStandardImplementation;

    static {
        sOptimizedReadMethod = null;
        sIsOptimizedReadMethodAvailable = false;
        try {
            sOptimizedReadMethod = Parcel.class.getDeclaredMethod("readByteArray", byte[].class, Integer.TYPE, Integer.TYPE);
            sIsOptimizedReadMethodAvailable = true;
        } catch (Exception unused) {
            log.warn("static", "Could not find optimized Parcel.readByteArray(byte[], int, int) method", new Object[0]);
        }
    }

    private LargeArrayOptimizedIInputStream(IBinder iBinder, IInputStream iInputStream) {
        this.mRemote = iBinder;
        this.mStandardImplementation = iInputStream;
    }

    protected static boolean isOptimizedReadMethodAvailable() {
        return sIsOptimizedReadMethodAvailable;
    }

    public static IInputStream makeIInputStream(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        if (queryLocalInterface != null && (queryLocalInterface instanceof IInputStream)) {
            return (IInputStream) queryLocalInterface;
        }
        return new LargeArrayOptimizedIInputStream(iBinder, IInputStream.Stub.asInterface(iBinder));
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.mStandardImplementation.asBinder();
    }

    @Override // com.amazon.communication.IInputStream
    public int available() throws RemoteException {
        return this.mStandardImplementation.available();
    }

    @Override // com.amazon.communication.IInputStream
    public boolean close() throws RemoteException {
        return this.mStandardImplementation.close();
    }

    @Override // com.amazon.communication.IInputStream
    public int readByte() throws RemoteException {
        return this.mStandardImplementation.readByte();
    }

    @Override // com.amazon.communication.IInputStream
    public int readBytes(byte[] bArr) throws RemoteException {
        return this.mStandardImplementation.readBytes(bArr);
    }

    @Override // com.amazon.communication.IInputStream
    public int readBytesIntoOffset(byte[] bArr, int i, int i2) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        if (isOptimizedReadMethodAvailable()) {
            try {
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeByteArray(bArr, i, i2);
                obtain.writeInt(0);
                obtain.writeInt(i2);
                this.mRemote.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                try {
                    sOptimizedReadMethod.invoke(obtain2, bArr, Integer.valueOf(i), Integer.valueOf(i2));
                    return readInt;
                } catch (Exception e) {
                    log.error("readBytesIntoOffset", "error invoking bulk read method via reflection", "offset", Integer.valueOf(i), "length", Integer.valueOf(i2), e);
                    throw new RemoteException("Error invoking optimized read method");
                }
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        return this.mStandardImplementation.readBytesIntoOffset(bArr, i, i2);
    }
}
