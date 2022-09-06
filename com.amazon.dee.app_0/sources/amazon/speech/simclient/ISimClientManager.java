package amazon.speech.simclient;

import amazon.speech.model.DeviceContext;
import amazon.speech.model.Event;
import amazon.speech.model.EventEnvelope;
import amazon.speech.model.IEventCallback;
import amazon.speech.model.IIdleTimeCallback;
import amazon.speech.simclient.IListenCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ISimClientManager extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimClientManager {
        @Override // amazon.speech.simclient.ISimClientManager
        public void acknowledgeDirective(String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void acknowledgeDirectiveWithTimestamp(String str, long j) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void addAudioFocus(String str, int i, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void addDeviceContext(DeviceContext deviceContext, boolean z) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void addVisualFocus(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void attachClientInstance(IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void cancelSpeechDialogue(String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void clearDeathEvents(IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void displayMultiTurnUi(String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public String getAudioUrl(String str) throws RemoteException {
            return null;
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public List<DeviceContext> getDeviceContext() throws RemoteException {
            return null;
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void listen() throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void listenWithTimeout(int i, IListenCallback iListenCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void releaseAudioFocus(String str, int i, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void releaseVisualFocus(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void removeDeviceContext(DeviceContext deviceContext) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void retrieveDeviceIdleTime(IIdleTimeCallback iIdleTimeCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void routeProxyDirective(String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void sendDestroy() throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void sendEvent(Event event) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void sendEventEnvelope(EventEnvelope eventEnvelope, IEventCallback iEventCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void sendEventWithNotification(Event event, IEventCallback iEventCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.ISimClientManager
        public void setDeathEvents(List<Event> list, IBinder iBinder) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimClientManager {
        private static final String DESCRIPTOR = "amazon.speech.simclient.ISimClientManager";
        static final int TRANSACTION_acknowledgeDirective = 10;
        static final int TRANSACTION_acknowledgeDirectiveWithTimestamp = 17;
        static final int TRANSACTION_addAudioFocus = 12;
        static final int TRANSACTION_addDeviceContext = 2;
        static final int TRANSACTION_addVisualFocus = 14;
        static final int TRANSACTION_attachClientInstance = 16;
        static final int TRANSACTION_cancelSpeechDialogue = 11;
        static final int TRANSACTION_clearDeathEvents = 21;
        static final int TRANSACTION_displayMultiTurnUi = 6;
        static final int TRANSACTION_getAudioUrl = 5;
        static final int TRANSACTION_getDeviceContext = 4;
        static final int TRANSACTION_listen = 7;
        static final int TRANSACTION_listenWithTimeout = 9;
        static final int TRANSACTION_releaseAudioFocus = 13;
        static final int TRANSACTION_releaseVisualFocus = 15;
        static final int TRANSACTION_removeDeviceContext = 3;
        static final int TRANSACTION_retrieveDeviceIdleTime = 23;
        static final int TRANSACTION_routeProxyDirective = 19;
        static final int TRANSACTION_sendDestroy = 18;
        static final int TRANSACTION_sendEvent = 1;
        static final int TRANSACTION_sendEventEnvelope = 22;
        static final int TRANSACTION_sendEventWithNotification = 8;
        static final int TRANSACTION_setDeathEvents = 20;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimClientManager {
            public static ISimClientManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void acknowledgeDirective(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(10, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acknowledgeDirective(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void acknowledgeDirectiveWithTimestamp(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(17, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acknowledgeDirectiveWithTimestamp(str, j);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void addAudioFocus(String str, int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(12, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addAudioFocus(str, i, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void addDeviceContext(DeviceContext deviceContext, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (deviceContext != null) {
                        obtain.writeInt(1);
                        deviceContext.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addDeviceContext(deviceContext, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void addVisualFocus(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(14, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addVisualFocus(str, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void attachClientInstance(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(16, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().attachClientInstance(iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void cancelSpeechDialogue(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(11, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelSpeechDialogue(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void clearDeathEvents(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(21, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().clearDeathEvents(iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void displayMultiTurnUi(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().displayMultiTurnUi(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public String getAudioUrl(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioUrl(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public List<DeviceContext> getDeviceContext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceContext();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceContext.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void listen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().listen();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void listenWithTimeout(int i, IListenCallback iListenCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iListenCallback != null ? iListenCallback.asBinder() : null);
                    if (this.mRemote.transact(9, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().listenWithTimeout(i, iListenCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void releaseAudioFocus(String str, int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(13, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseAudioFocus(str, i, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void releaseVisualFocus(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(15, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseVisualFocus(str, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void removeDeviceContext(DeviceContext deviceContext) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (deviceContext != null) {
                        obtain.writeInt(1);
                        deviceContext.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeDeviceContext(deviceContext);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void retrieveDeviceIdleTime(IIdleTimeCallback iIdleTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iIdleTimeCallback != null ? iIdleTimeCallback.asBinder() : null);
                    if (this.mRemote.transact(23, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().retrieveDeviceIdleTime(iIdleTimeCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void routeProxyDirective(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(19, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().routeProxyDirective(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void sendDestroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(18, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendDestroy();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void sendEvent(Event event) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        obtain.writeInt(1);
                        event.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendEvent(event);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void sendEventEnvelope(EventEnvelope eventEnvelope, IEventCallback iEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eventEnvelope != null) {
                        obtain.writeInt(1);
                        eventEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iEventCallback != null ? iEventCallback.asBinder() : null);
                    if (this.mRemote.transact(22, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendEventEnvelope(eventEnvelope, iEventCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void sendEventWithNotification(Event event, IEventCallback iEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        obtain.writeInt(1);
                        event.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iEventCallback != null ? iEventCallback.asBinder() : null);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendEventWithNotification(event, iEventCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.ISimClientManager
            public void setDeathEvents(List<Event> list, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(20, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDeathEvents(list, iBinder);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimClientManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimClientManager)) {
                return (ISimClientManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimClientManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimClientManager iSimClientManager) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimClientManager == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimClientManager;
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
            if (i != 1598968902) {
                Event event = null;
                EventEnvelope eventEnvelope = null;
                Event event2 = null;
                DeviceContext deviceContext = null;
                DeviceContext deviceContext2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            event = Event.CREATOR.createFromParcel(parcel);
                        }
                        sendEvent(event);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            deviceContext2 = DeviceContext.CREATOR.createFromParcel(parcel);
                        }
                        addDeviceContext(deviceContext2, parcel.readInt() != 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            deviceContext = DeviceContext.CREATOR.createFromParcel(parcel);
                        }
                        removeDeviceContext(deviceContext);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<DeviceContext> deviceContext3 = getDeviceContext();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(deviceContext3);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        String audioUrl = getAudioUrl(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(audioUrl);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        displayMultiTurnUi(parcel.readString());
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        listen();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            event2 = Event.CREATOR.createFromParcel(parcel);
                        }
                        sendEventWithNotification(event2, IEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        listenWithTimeout(parcel.readInt(), IListenCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        acknowledgeDirective(parcel.readString());
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelSpeechDialogue(parcel.readString());
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        addAudioFocus(parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        releaseAudioFocus(parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        addVisualFocus(parcel.readString(), parcel.readStrongBinder());
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        releaseVisualFocus(parcel.readString(), parcel.readStrongBinder());
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        attachClientInstance(parcel.readStrongBinder());
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        acknowledgeDirectiveWithTimestamp(parcel.readString(), parcel.readLong());
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        sendDestroy();
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        routeProxyDirective(parcel.readString());
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        setDeathEvents(parcel.createTypedArrayList(Event.CREATOR), parcel.readStrongBinder());
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        clearDeathEvents(parcel.readStrongBinder());
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            eventEnvelope = EventEnvelope.CREATOR.createFromParcel(parcel);
                        }
                        sendEventEnvelope(eventEnvelope, IEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        retrieveDeviceIdleTime(IIdleTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void acknowledgeDirective(String str) throws RemoteException;

    void acknowledgeDirectiveWithTimestamp(String str, long j) throws RemoteException;

    void addAudioFocus(String str, int i, IBinder iBinder) throws RemoteException;

    void addDeviceContext(DeviceContext deviceContext, boolean z) throws RemoteException;

    void addVisualFocus(String str, IBinder iBinder) throws RemoteException;

    void attachClientInstance(IBinder iBinder) throws RemoteException;

    void cancelSpeechDialogue(String str) throws RemoteException;

    void clearDeathEvents(IBinder iBinder) throws RemoteException;

    void displayMultiTurnUi(String str) throws RemoteException;

    String getAudioUrl(String str) throws RemoteException;

    List<DeviceContext> getDeviceContext() throws RemoteException;

    void listen() throws RemoteException;

    void listenWithTimeout(int i, IListenCallback iListenCallback) throws RemoteException;

    void releaseAudioFocus(String str, int i, IBinder iBinder) throws RemoteException;

    void releaseVisualFocus(String str, IBinder iBinder) throws RemoteException;

    void removeDeviceContext(DeviceContext deviceContext) throws RemoteException;

    void retrieveDeviceIdleTime(IIdleTimeCallback iIdleTimeCallback) throws RemoteException;

    void routeProxyDirective(String str) throws RemoteException;

    void sendDestroy() throws RemoteException;

    void sendEvent(Event event) throws RemoteException;

    void sendEventEnvelope(EventEnvelope eventEnvelope, IEventCallback iEventCallback) throws RemoteException;

    void sendEventWithNotification(Event event, IEventCallback iEventCallback) throws RemoteException;

    void setDeathEvents(List<Event> list, IBinder iBinder) throws RemoteException;
}
