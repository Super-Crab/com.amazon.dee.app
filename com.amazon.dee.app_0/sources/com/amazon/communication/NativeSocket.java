package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
/* loaded from: classes12.dex */
public final class NativeSocket {
    private static final String LIB_NAME = "native_socket";
    private static final String OS_NAME_DARWIN = "Mac OS X";
    private static final String OS_NAME_LINUX = "Linux";
    private static final String PLATFORM_DARWIN = "darwin";
    private static final String PLATFORM_LINUX = "linux";
    private static final RuntimeKind RUNTIME_KIND = getRuntimeKind();

    /* renamed from: com.amazon.communication.NativeSocket$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$NativeSocket$RuntimeKind = new int[RuntimeKind.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$NativeSocket$RuntimeKind[RuntimeKind.Android.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$NativeSocket$RuntimeKind[RuntimeKind.Other.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum RuntimeKind {
        Android,
        Other
    }

    static {
        if (jniLoadFromPath(LIB_NAME) || jniLoadFromJarDir(LIB_NAME)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("native_socket.");
        outline107.append(getPlatformName());
        if (!jniLoadFromJarDir(outline107.toString())) {
            throw new RuntimeException(String.format("Native library not found: %s (%s)", LIB_NAME, System.mapLibraryName(LIB_NAME)));
        }
    }

    private NativeSocket() {
    }

    public static void enableKeepAlive(Socket socket, int i, int i2, int i3) throws SocketException {
        int fd = getFd(socket);
        int keepAlive = setKeepAlive(fd, 1);
        if (keepAlive == 0) {
            int keepIdle = setKeepIdle(fd, i);
            if (keepIdle == 0) {
                int keepIntvl = setKeepIntvl(fd, i2);
                if (keepIntvl == 0) {
                    int keepCnt = setKeepCnt(fd, i3);
                    if (keepCnt != 0) {
                        throw new SocketException(String.format("Set TCP_KEEPCNT err, %d", Integer.valueOf(keepCnt)));
                    }
                    return;
                }
                throw new SocketException(String.format("Set TCP_KEEPINTVL err, %d", Integer.valueOf(keepIntvl)));
            }
            throw new SocketException(String.format("Set TCP_KEEPIDLE err, %d", Integer.valueOf(keepIdle)));
        }
        throw new SocketException(String.format("Set SO_KEEPALIVE err, %d", Integer.valueOf(keepAlive)));
    }

    public static int getFd(Socket socket) throws SocketException {
        int ordinal = RUNTIME_KIND.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return getFdOther(socket);
            }
            throw new SocketException("Unknown runtime kind");
        }
        return getFdAndroid(socket);
    }

    private static int getFdAndroid(Socket socket) throws SocketException {
        try {
            Field declaredField = Socket.class.getDeclaredField("impl");
            declaredField.setAccessible(true);
            Field declaredField2 = SocketImpl.class.getDeclaredField("fd");
            declaredField2.setAccessible(true);
            Field declaredField3 = FileDescriptor.class.getDeclaredField("descriptor");
            declaredField3.setAccessible(true);
            return declaredField3.getInt((FileDescriptor) declaredField2.get((SocketImpl) declaredField.get(socket)));
        } catch (IllegalAccessException e) {
            throw new SocketException(e.toString());
        } catch (NoSuchFieldException e2) {
            throw new SocketException(e2.toString());
        }
    }

    private static int getFdOther(Socket socket) throws SocketException {
        try {
            Field declaredField = socket.getClass().getDeclaredField("sc");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(socket);
            Field declaredField2 = obj.getClass().getDeclaredField("fd");
            declaredField2.setAccessible(true);
            Field declaredField3 = FileDescriptor.class.getDeclaredField("fd");
            declaredField3.setAccessible(true);
            return declaredField3.getInt((FileDescriptor) declaredField2.get(obj));
        } catch (IllegalAccessException e) {
            throw new SocketException(e.toString());
        } catch (NoSuchFieldException e2) {
            throw new SocketException(e2.toString());
        }
    }

    private static String getPlatformName() {
        String property = System.getProperty("os.name");
        if (property != null) {
            if (OS_NAME_LINUX.equals(property)) {
                return PLATFORM_LINUX;
            }
            if (OS_NAME_DARWIN.equals(property)) {
                return PLATFORM_DARWIN;
            }
        }
        throw new RuntimeException(String.format("Unknown OS name: %s", property));
    }

    private static RuntimeKind getRuntimeKind() {
        try {
            Class.forName("android.os.Build");
            return RuntimeKind.Android;
        } catch (ClassNotFoundException unused) {
            return RuntimeKind.Other;
        }
    }

    private static boolean jniLoadFromJarDir(String str) {
        try {
            System.load(new File(new File(NativeSocket.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile(), System.mapLibraryName(str)).getAbsolutePath());
            return true;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }

    private static boolean jniLoadFromPath(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public static native int setKeepAlive(int i, int i2);

    public static native int setKeepCnt(int i, int i2);

    public static native int setKeepIdle(int i, int i2);

    public static native int setKeepIntvl(int i, int i2);
}
