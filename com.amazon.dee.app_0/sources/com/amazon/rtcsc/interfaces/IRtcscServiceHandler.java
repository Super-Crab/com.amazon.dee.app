package com.amazon.rtcsc.interfaces;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
import com.amazon.rtcsc.interfaces.IRtcscViewRendererListener;
/* loaded from: classes13.dex */
public interface IRtcscServiceHandler extends IInterface {
    public static final int API_VERSION = 3;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscServiceHandler {
        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void acceptSession(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void clearImage(String str, String str2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void disconnectSession(String str, RtcscAppDisconnectCode rtcscAppDisconnectCode) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void handleDirective(String str, String str2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void initRenderer(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void onRendererLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void onRendererMeasure(String str, String str2, int i, int i2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void putScreenCapturerData(String str, Intent intent) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void registerCAEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void registerClientDeathListener(RtcscAppInfo rtcscAppInfo, IBinder iBinder) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public boolean registerDataChannelListener(String str, IRtcscDataChannelListener iRtcscDataChannelListener) throws RemoteException {
            return false;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void registerRtcscAppClientListener(RtcscAppInfo rtcscAppInfo, IRtcscAppClientListener iRtcscAppClientListener) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void registerRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo, IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void releaseRenderer(String str, String str2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public boolean sendData(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setEnableHardwareScaler(String str, String str2, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setLocalAudioState(String str, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setLocalVideoState(String str, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setMirror(String str, String str2, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setRemoteAudioState(String str, boolean z) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setScreenCapturerDimensions(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void setVideoEffect(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void signalReadyForSession(String str) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void surfaceCreated(String str, String str2, Surface surface) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void surfaceDestroyed(String str, String str2, Surface surface) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void switchCamera(String str, String str2) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void unregisterCAEventListener(RtcscAppInfo rtcscAppInfo) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public boolean unregisterDataChannelListener(String str) throws RemoteException {
            return false;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void unregisterRtcscAppClientListener(RtcscAppInfo rtcscAppInfo) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void unregisterRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
        public void unregisterScreenCapturerListener(String str) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscServiceHandler {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscServiceHandler";
        static final int TRANSACTION_acceptSession = 8;
        static final int TRANSACTION_clearImage = 23;
        static final int TRANSACTION_disconnectSession = 9;
        static final int TRANSACTION_getAPIVersion = 33;
        static final int TRANSACTION_handleDirective = 1;
        static final int TRANSACTION_initRenderer = 18;
        static final int TRANSACTION_onRendererLayout = 25;
        static final int TRANSACTION_onRendererMeasure = 24;
        static final int TRANSACTION_putScreenCapturerData = 31;
        static final int TRANSACTION_registerCAEventListener = 2;
        static final int TRANSACTION_registerClientDeathListener = 34;
        static final int TRANSACTION_registerDataChannelListener = 15;
        static final int TRANSACTION_registerRtcscAppClientListener = 4;
        static final int TRANSACTION_registerRtcscCustomMetricsPublisherAdapter = 13;
        static final int TRANSACTION_registerScreenCapturerListener = 29;
        static final int TRANSACTION_releaseRenderer = 19;
        static final int TRANSACTION_sendData = 17;
        static final int TRANSACTION_setEnableHardwareScaler = 21;
        static final int TRANSACTION_setLocalAudioState = 6;
        static final int TRANSACTION_setLocalVideoState = 7;
        static final int TRANSACTION_setMirror = 22;
        static final int TRANSACTION_setRemoteAudioState = 35;
        static final int TRANSACTION_setScalingType = 20;
        static final int TRANSACTION_setScreenCapturerDimensions = 32;
        static final int TRANSACTION_setVideoEffect = 12;
        static final int TRANSACTION_signalReadyForSession = 11;
        static final int TRANSACTION_surfaceChanged = 28;
        static final int TRANSACTION_surfaceCreated = 26;
        static final int TRANSACTION_surfaceDestroyed = 27;
        static final int TRANSACTION_switchCamera = 10;
        static final int TRANSACTION_unregisterCAEventListener = 3;
        static final int TRANSACTION_unregisterDataChannelListener = 16;
        static final int TRANSACTION_unregisterRtcscAppClientListener = 5;
        static final int TRANSACTION_unregisterRtcscCustomMetricsPublisherAdapter = 14;
        static final int TRANSACTION_unregisterScreenCapturerListener = 30;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscServiceHandler {
            public static IRtcscServiceHandler sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void acceptSession(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acceptSession(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void clearImage(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearImage(str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void disconnectSession(String str, RtcscAppDisconnectCode rtcscAppDisconnectCode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscAppDisconnectCode != null) {
                        obtain.writeInt(1);
                        rtcscAppDisconnectCode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disconnectSession(str, rtcscAppDisconnectCode);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAPIVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void handleDirective(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handleDirective(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void initRenderer(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iRtcscViewRendererListener != null ? iRtcscViewRendererListener.asBinder() : null);
                    if (rtcscViewDirection != null) {
                        obtain.writeInt(1);
                        rtcscViewDirection.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initRenderer(str, str2, iRtcscViewRendererListener, rtcscViewDirection, str3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void onRendererLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    try {
                        if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().onRendererLayout(str, str2, z, i, i2, i3, i4);
                            obtain2.recycle();
                            obtain.recycle();
                            return;
                        }
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void onRendererMeasure(String str, String str2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRendererMeasure(str, str2, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void putScreenCapturerData(String str, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(31, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().putScreenCapturerData(str, intent);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void registerCAEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iRtcscEventListener != null ? iRtcscEventListener.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCAEventListener(rtcscAppInfo, iRtcscEventListener);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void registerClientDeathListener(RtcscAppInfo rtcscAppInfo, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(34, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerClientDeathListener(rtcscAppInfo, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public boolean registerDataChannelListener(String str, IRtcscDataChannelListener iRtcscDataChannelListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRtcscDataChannelListener != null ? iRtcscDataChannelListener.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerDataChannelListener(str, iRtcscDataChannelListener);
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void registerRtcscAppClientListener(RtcscAppInfo rtcscAppInfo, IRtcscAppClientListener iRtcscAppClientListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iRtcscAppClientListener != null ? iRtcscAppClientListener.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerRtcscAppClientListener(rtcscAppInfo, iRtcscAppClientListener);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void registerRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo, IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iRtcscCustomMetricsPublisherAdapter != null ? iRtcscCustomMetricsPublisherAdapter.asBinder() : null);
                    if (this.mRemote.transact(13, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerRtcscCustomMetricsPublisherAdapter(rtcscAppInfo, iRtcscCustomMetricsPublisherAdapter);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRtcscScreenCapturerListener != null ? iRtcscScreenCapturerListener.asBinder() : null);
                    if (this.mRemote.transact(29, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerScreenCapturerListener(str, iRtcscScreenCapturerListener);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void releaseRenderer(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseRenderer(str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public boolean sendData(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendData(str, str2, bArr, z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setEnableHardwareScaler(String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEnableHardwareScaler(str, str2, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setLocalAudioState(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setLocalAudioState(str, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setLocalVideoState(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setLocalVideoState(str, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setMirror(String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMirror(str, str2, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setRemoteAudioState(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(35, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRemoteAudioState(str, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (rtcscScalingType != null) {
                        obtain.writeInt(1);
                        rtcscScalingType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setScalingType(str, str2, rtcscScalingType);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setScreenCapturerDimensions(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(32, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setScreenCapturerDimensions(str, i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void setVideoEffect(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (rtcscVideoEffect != null) {
                        obtain.writeInt(1);
                        rtcscVideoEffect.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(12, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setVideoEffect(str, rtcscVideoEffect, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void signalReadyForSession(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(11, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().signalReadyForSession(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    try {
                        if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().surfaceChanged(str, str2, surface, i, i2, i3);
                            obtain2.recycle();
                            obtain.recycle();
                            return;
                        }
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void surfaceCreated(String str, String str2, Surface surface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().surfaceCreated(str, str2, surface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void surfaceDestroyed(String str, String str2, Surface surface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().surfaceDestroyed(str, str2, surface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void switchCamera(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(10, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().switchCamera(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void unregisterCAEventListener(RtcscAppInfo rtcscAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCAEventListener(rtcscAppInfo);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public boolean unregisterDataChannelListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterDataChannelListener(str);
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void unregisterRtcscAppClientListener(RtcscAppInfo rtcscAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterRtcscAppClientListener(rtcscAppInfo);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void unregisterRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscAppInfo != null) {
                        obtain.writeInt(1);
                        rtcscAppInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(14, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterRtcscCustomMetricsPublisherAdapter(rtcscAppInfo);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
            public void unregisterScreenCapturerListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(30, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterScreenCapturerListener(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscServiceHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscServiceHandler)) {
                return (IRtcscServiceHandler) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscServiceHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscServiceHandler iRtcscServiceHandler) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscServiceHandler == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscServiceHandler;
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
                boolean z = false;
                RtcscAppInfo rtcscAppInfo = null;
                RtcscAppInfo rtcscAppInfo2 = null;
                Intent intent = null;
                Surface surface = null;
                Surface surface2 = null;
                Surface surface3 = null;
                RtcscScalingType rtcscScalingType = null;
                RtcscViewDirection rtcscViewDirection = null;
                RtcscAppInfo rtcscAppInfo3 = null;
                RtcscAppInfo rtcscAppInfo4 = null;
                RtcscVideoEffect rtcscVideoEffect = null;
                RtcscAppDisconnectCode rtcscAppDisconnectCode = null;
                RtcscAppInfo rtcscAppInfo5 = null;
                RtcscAppInfo rtcscAppInfo6 = null;
                RtcscAppInfo rtcscAppInfo7 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        handleDirective(parcel.readString(), parcel.readString());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        registerCAEventListener(rtcscAppInfo, IRtcscEventListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo7 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        unregisterCAEventListener(rtcscAppInfo7);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo6 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        registerRtcscAppClientListener(rtcscAppInfo6, IRtcscAppClientListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo5 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        unregisterRtcscAppClientListener(rtcscAppInfo5);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setLocalAudioState(readString, z);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setLocalVideoState(readString2, z);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        acceptSession(parcel.readString());
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString3 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscAppDisconnectCode = RtcscAppDisconnectCode.CREATOR.createFromParcel(parcel);
                        }
                        disconnectSession(readString3, rtcscAppDisconnectCode);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        switchCamera(parcel.readString(), parcel.readString());
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        signalReadyForSession(parcel.readString());
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString4 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscVideoEffect = RtcscVideoEffect.CREATOR.createFromParcel(parcel);
                        }
                        setVideoEffect(readString4, rtcscVideoEffect, parcel.readInt());
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo4 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        registerRtcscCustomMetricsPublisherAdapter(rtcscAppInfo4, IRtcscCustomMetricsPublisherAdapter.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo3 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        unregisterRtcscCustomMetricsPublisherAdapter(rtcscAppInfo3);
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean registerDataChannelListener = registerDataChannelListener(parcel.readString(), IRtcscDataChannelListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(registerDataChannelListener ? 1 : 0);
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean unregisterDataChannelListener = unregisterDataChannelListener(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(unregisterDataChannelListener ? 1 : 0);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString5 = parcel.readString();
                        String readString6 = parcel.readString();
                        byte[] createByteArray = parcel.createByteArray();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean sendData = sendData(readString5, readString6, createByteArray, z);
                        parcel2.writeNoException();
                        parcel2.writeInt(sendData ? 1 : 0);
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString7 = parcel.readString();
                        String readString8 = parcel.readString();
                        IRtcscViewRendererListener asInterface = IRtcscViewRendererListener.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            rtcscViewDirection = RtcscViewDirection.CREATOR.createFromParcel(parcel);
                        }
                        initRenderer(readString7, readString8, asInterface, rtcscViewDirection, parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        releaseRenderer(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString9 = parcel.readString();
                        String readString10 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            rtcscScalingType = RtcscScalingType.CREATOR.createFromParcel(parcel);
                        }
                        setScalingType(readString9, readString10, rtcscScalingType);
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString11 = parcel.readString();
                        String readString12 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setEnableHardwareScaler(readString11, readString12, z);
                        parcel2.writeNoException();
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString13 = parcel.readString();
                        String readString14 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setMirror(readString13, readString14, z);
                        parcel2.writeNoException();
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        clearImage(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        onRendererMeasure(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        onRendererLayout(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString15 = parcel.readString();
                        String readString16 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            surface3 = (Surface) Surface.CREATOR.createFromParcel(parcel);
                        }
                        surfaceCreated(readString15, readString16, surface3);
                        parcel2.writeNoException();
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString17 = parcel.readString();
                        String readString18 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            surface2 = (Surface) Surface.CREATOR.createFromParcel(parcel);
                        }
                        surfaceDestroyed(readString17, readString18, surface2);
                        parcel2.writeNoException();
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString19 = parcel.readString();
                        String readString20 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
                        }
                        surfaceChanged(readString19, readString20, surface, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerScreenCapturerListener(parcel.readString(), IRtcscScreenCapturerListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterScreenCapturerListener(parcel.readString());
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString21 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        putScreenCapturerData(readString21, intent);
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        setScreenCapturerDimensions(parcel.readString(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        int aPIVersion = getAPIVersion();
                        parcel2.writeNoException();
                        parcel2.writeInt(aPIVersion);
                        return true;
                    case 34:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            rtcscAppInfo2 = RtcscAppInfo.CREATOR.createFromParcel(parcel);
                        }
                        registerClientDeathListener(rtcscAppInfo2, parcel.readStrongBinder());
                        return true;
                    case 35:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString22 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setRemoteAudioState(readString22, z);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void acceptSession(String str) throws RemoteException;

    void clearImage(String str, String str2) throws RemoteException;

    void disconnectSession(String str, RtcscAppDisconnectCode rtcscAppDisconnectCode) throws RemoteException;

    int getAPIVersion() throws RemoteException;

    void handleDirective(String str, String str2) throws RemoteException;

    void initRenderer(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) throws RemoteException;

    void onRendererLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) throws RemoteException;

    void onRendererMeasure(String str, String str2, int i, int i2) throws RemoteException;

    void putScreenCapturerData(String str, Intent intent) throws RemoteException;

    void registerCAEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) throws RemoteException;

    void registerClientDeathListener(RtcscAppInfo rtcscAppInfo, IBinder iBinder) throws RemoteException;

    boolean registerDataChannelListener(String str, IRtcscDataChannelListener iRtcscDataChannelListener) throws RemoteException;

    void registerRtcscAppClientListener(RtcscAppInfo rtcscAppInfo, IRtcscAppClientListener iRtcscAppClientListener) throws RemoteException;

    void registerRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo, IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) throws RemoteException;

    void registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) throws RemoteException;

    void releaseRenderer(String str, String str2) throws RemoteException;

    boolean sendData(String str, String str2, byte[] bArr, boolean z) throws RemoteException;

    void setEnableHardwareScaler(String str, String str2, boolean z) throws RemoteException;

    void setLocalAudioState(String str, boolean z) throws RemoteException;

    void setLocalVideoState(String str, boolean z) throws RemoteException;

    void setMirror(String str, String str2, boolean z) throws RemoteException;

    void setRemoteAudioState(String str, boolean z) throws RemoteException;

    void setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) throws RemoteException;

    void setScreenCapturerDimensions(String str, int i, int i2) throws RemoteException;

    void setVideoEffect(String str, RtcscVideoEffect rtcscVideoEffect, int i) throws RemoteException;

    void signalReadyForSession(String str) throws RemoteException;

    void surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) throws RemoteException;

    void surfaceCreated(String str, String str2, Surface surface) throws RemoteException;

    void surfaceDestroyed(String str, String str2, Surface surface) throws RemoteException;

    void switchCamera(String str, String str2) throws RemoteException;

    void unregisterCAEventListener(RtcscAppInfo rtcscAppInfo) throws RemoteException;

    boolean unregisterDataChannelListener(String str) throws RemoteException;

    void unregisterRtcscAppClientListener(RtcscAppInfo rtcscAppInfo) throws RemoteException;

    void unregisterRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo) throws RemoteException;

    void unregisterScreenCapturerListener(String str) throws RemoteException;
}
