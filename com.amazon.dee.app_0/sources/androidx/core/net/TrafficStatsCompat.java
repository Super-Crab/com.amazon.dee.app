package androidx.core.net;

import android.net.TrafficStats;
import android.os.Build;
import androidx.annotation.NonNull;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
/* loaded from: classes.dex */
public final class TrafficStatsCompat {
    private TrafficStatsCompat() {
    }

    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int i) {
        TrafficStats.incrementOperationCount(i);
    }

    @Deprecated
    public static void setThreadStatsTag(int i) {
        TrafficStats.setThreadStatsTag(i);
    }

    public static void tagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        int i = Build.VERSION.SDK_INT;
        TrafficStats.tagDatagramSocket(datagramSocket);
    }

    @Deprecated
    public static void tagSocket(Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        int i = Build.VERSION.SDK_INT;
        TrafficStats.untagDatagramSocket(datagramSocket);
    }

    @Deprecated
    public static void untagSocket(Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }

    @Deprecated
    public static void incrementOperationCount(int i, int i2) {
        TrafficStats.incrementOperationCount(i, i2);
    }
}
