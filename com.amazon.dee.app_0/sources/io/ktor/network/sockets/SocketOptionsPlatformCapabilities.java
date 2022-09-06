package io.ktor.network.sockets;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: JavaSocketOptions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000fJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\tH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/network/sockets/SocketOptionsPlatformCapabilities;", "", "()V", "channelSetOption", "Ljava/lang/reflect/Method;", "datagramSetOption", "serverChannelSetOption", "standardSocketOptions", "", "", "Ljava/lang/reflect/Field;", "setReusePort", "", "channel", "Ljava/nio/channels/DatagramChannel;", "Ljava/nio/channels/ServerSocketChannel;", "Ljava/nio/channels/SocketChannel;", "socketOption", "name", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SocketOptionsPlatformCapabilities {
    public static final SocketOptionsPlatformCapabilities INSTANCE = new SocketOptionsPlatformCapabilities();
    private static final Method channelSetOption;
    private static final Method datagramSetOption;
    private static final Method serverChannelSetOption;
    private static final Map<String, Field> standardSocketOptions;

    static {
        Map emptyMap;
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Field[] fields;
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        try {
            Class<?> cls = Class.forName("java.net.StandardSocketOptions");
            if (cls != null && (fields = cls.getFields()) != null) {
                ArrayList arrayList = new ArrayList();
                for (Field it2 : fields) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    int modifiers = it2.getModifiers();
                    if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers)) {
                        arrayList.add(it2);
                    }
                }
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
                emptyMap = new LinkedHashMap(coerceAtLeast);
                for (Object obj : arrayList) {
                    Field it3 = (Field) obj;
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    emptyMap.put(it3.getName(), obj);
                }
            } else {
                emptyMap = MapsKt__MapsKt.emptyMap();
            }
        } catch (Throwable unused) {
            emptyMap = MapsKt__MapsKt.emptyMap();
        }
        standardSocketOptions = emptyMap;
        try {
            Class<?> cls2 = Class.forName("java.net.SocketOption");
            if (cls2 == null) {
                Intrinsics.throwNpe();
            }
            Class<?> socketChannelClass = Class.forName("java.nio.channels.SocketChannel");
            Intrinsics.checkExpressionValueIsNotNull(socketChannelClass, "socketChannelClass");
            Method[] methods = socketChannelClass.getMethods();
            Intrinsics.checkExpressionValueIsNotNull(methods, "socketChannelClass.methods");
            for (Method method5 : methods) {
                Intrinsics.checkExpressionValueIsNotNull(method5, "method");
                int modifiers2 = method5.getModifiers();
                if ((Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2)) && Intrinsics.areEqual(method5.getName(), "setOption") && method5.getParameterTypes().length == 2 && Intrinsics.areEqual(method5.getReturnType(), socketChannelClass) && Intrinsics.areEqual(method5.getParameterTypes()[0], cls2) && Intrinsics.areEqual(method5.getParameterTypes()[1], Object.class)) {
                    method = method5;
                    break;
                }
            }
        } catch (Throwable unused2) {
        }
        method = null;
        channelSetOption = method;
        try {
            Class<?> cls3 = Class.forName("java.net.SocketOption");
            if (cls3 == null) {
                Intrinsics.throwNpe();
            }
            Class<?> socketChannelClass2 = Class.forName("java.nio.channels.ServerSocketChannel");
            Intrinsics.checkExpressionValueIsNotNull(socketChannelClass2, "socketChannelClass");
            Method[] methods2 = socketChannelClass2.getMethods();
            Intrinsics.checkExpressionValueIsNotNull(methods2, "socketChannelClass.methods");
            for (Method method6 : methods2) {
                Intrinsics.checkExpressionValueIsNotNull(method6, "method");
                int modifiers3 = method6.getModifiers();
                if ((Modifier.isPublic(modifiers3) && !Modifier.isStatic(modifiers3)) && Intrinsics.areEqual(method6.getName(), "setOption") && method6.getParameterTypes().length == 2 && Intrinsics.areEqual(method6.getReturnType(), socketChannelClass2) && Intrinsics.areEqual(method6.getParameterTypes()[0], cls3) && Intrinsics.areEqual(method6.getParameterTypes()[1], Object.class)) {
                    method2 = method6;
                    break;
                }
            }
        } catch (Throwable unused3) {
        }
        method2 = null;
        serverChannelSetOption = method2;
        try {
            Class<?> cls4 = Class.forName("java.net.SocketOption");
            if (cls4 == null) {
                Intrinsics.throwNpe();
            }
            Class<?> socketChannelClass3 = Class.forName("java.nio.channels.DatagramChannel");
            Intrinsics.checkExpressionValueIsNotNull(socketChannelClass3, "socketChannelClass");
            Method[] methods3 = socketChannelClass3.getMethods();
            Intrinsics.checkExpressionValueIsNotNull(methods3, "socketChannelClass.methods");
            int length = methods3.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    method4 = null;
                    break;
                }
                Method method7 = methods3[i];
                Intrinsics.checkExpressionValueIsNotNull(method7, "method");
                int modifiers4 = method7.getModifiers();
                if ((Modifier.isPublic(modifiers4) && !Modifier.isStatic(modifiers4)) && Intrinsics.areEqual(method7.getName(), "setOption") && method7.getParameterTypes().length == 2 && Intrinsics.areEqual(method7.getReturnType(), socketChannelClass3) && Intrinsics.areEqual(method7.getParameterTypes()[0], cls4) && Intrinsics.areEqual(method7.getParameterTypes()[1], Object.class)) {
                    method4 = method7;
                    break;
                }
                i++;
            }
            method3 = method4;
        } catch (Throwable unused4) {
            method3 = null;
        }
        datagramSetOption = method3;
    }

    private SocketOptionsPlatformCapabilities() {
    }

    private final Object socketOption(String str) {
        Object obj;
        Field field = standardSocketOptions.get(str);
        if (field == null || (obj = field.get(null)) == null) {
            throw new IOException(GeneratedOutlineSupport1.outline75("Socket option ", str, " is not supported"));
        }
        return obj;
    }

    public final void setReusePort(@NotNull SocketChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = channelSetOption;
        if (method == null) {
            Intrinsics.throwNpe();
        }
        method.invoke(channel, socketOption, true);
    }

    public final void setReusePort(@NotNull ServerSocketChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = serverChannelSetOption;
        if (method == null) {
            Intrinsics.throwNpe();
        }
        method.invoke(channel, socketOption, true);
    }

    public final void setReusePort(@NotNull DatagramChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = datagramSetOption;
        if (method == null) {
            Intrinsics.throwNpe();
        }
        method.invoke(channel, socketOption, true);
    }
}
