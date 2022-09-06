package okhttp3.internal.connection;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RealConnectionPool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003*\u0001\u000e\u0018\u0000 02\u00020\u0001:\u00010B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0012J\u0006\u0010#\u001a\u00020\u0005J\u0006\u0010$\u001a\u00020\u001bJ\u0006\u0010%\u001a\u00020\u0005J\u0018\u0010&\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u000e\u0010'\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0012J.\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010.2\u0006\u0010/\u001a\u00020!R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u00061"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;)V", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "connections", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "cleanup", "now", "connectFailed", "", "failedRoute", "Lokhttp3/Route;", "failure", "Ljava/io/IOException;", "connectionBecameIdle", "", "connection", "connectionCount", "evictAll", "idleConnectionCount", "pruneAndGetAllocationCount", MetricsConstants.Method.CACHE_PUT, "transmitterAcquirePooledConnection", "address", "Lokhttp3/Address;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "routes", "", "requireMultiplexed", "Companion", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RealConnectionPool {
    public static final Companion Companion = new Companion(null);
    private final TaskQueue cleanupQueue;
    private final RealConnectionPool$cleanupTask$1 cleanupTask;
    private final ArrayDeque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    @NotNull
    private final RouteDatabase routeDatabase;

    /* compiled from: RealConnectionPool.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", MetricsConstants.Method.CACHE_GET, "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RealConnectionPool get(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkParameterIsNotNull(connectionPool, "connectionPool");
            return connectionPool.getDelegate$okhttp();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [okhttp3.internal.connection.RealConnectionPool$cleanupTask$1] */
    public RealConnectionPool(@NotNull TaskRunner taskRunner, int i, long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(taskRunner, "taskRunner");
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new Task("OkHttp ConnectionPool") { // from class: okhttp3.internal.connection.RealConnectionPool$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                return RealConnectionPool.this.cleanup(System.nanoTime());
            }
        };
        this.connections = new ArrayDeque<>();
        this.routeDatabase = new RouteDatabase();
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("keepAliveDuration <= 0: ", j).toString());
    }

    private final int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<Transmitter>> transmitters = realConnection.getTransmitters();
        int i = 0;
        while (i < transmitters.size()) {
            Reference<Transmitter> reference = transmitters.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("A connection to ");
                outline107.append(realConnection.route().address().url());
                outline107.append(" was leaked. ");
                outline107.append("Did you forget to close a response body?");
                Platform.Companion.get().logCloseableLeak(outline107.toString(), ((Transmitter.TransmitterReference) reference).getCallStackTrace());
                transmitters.remove(i);
                realConnection.setNoNewExchanges(true);
                if (transmitters.isEmpty()) {
                    realConnection.setIdleAtNanos$okhttp(j - this.keepAliveDurationNs);
                    return 0;
                }
            }
        }
        return transmitters.size();
    }

    public final long cleanup(long j) {
        synchronized (this) {
            Iterator<RealConnection> it2 = this.connections.iterator();
            int i = 0;
            RealConnection realConnection = null;
            long j2 = Long.MIN_VALUE;
            int i2 = 0;
            while (it2.hasNext()) {
                RealConnection connection = it2.next();
                Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                if (pruneAndGetAllocationCount(connection, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long idleAtNanos$okhttp = j - connection.getIdleAtNanos$okhttp();
                    if (idleAtNanos$okhttp > j2) {
                        realConnection = connection;
                        j2 = idleAtNanos$okhttp;
                    }
                }
            }
            if (j2 < this.keepAliveDurationNs && i <= this.maxIdleConnections) {
                if (i > 0) {
                    return this.keepAliveDurationNs - j2;
                } else if (i2 <= 0) {
                    return -1L;
                } else {
                    return this.keepAliveDurationNs;
                }
            }
            this.connections.remove(realConnection);
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            Unit unit = Unit.INSTANCE;
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            Util.closeQuietly(realConnection.socket());
            return 0L;
        }
    }

    public final void connectFailed(@NotNull Route failedRoute, @NotNull IOException failure) {
        Intrinsics.checkParameterIsNotNull(failedRoute, "failedRoute");
        Intrinsics.checkParameterIsNotNull(failure, "failure");
        if (failedRoute.proxy().type() != Proxy.Type.DIRECT) {
            Address address = failedRoute.address();
            address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
        }
        this.routeDatabase.failed(failedRoute);
    }

    public final boolean connectionBecameIdle(@NotNull RealConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            outline107.append(currentThread.getName());
            outline107.append(" MUST hold lock on ");
            outline107.append(this);
            throw new AssertionError(outline107.toString());
        } else if (!connection.getNoNewExchanges() && this.maxIdleConnections != 0) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return false;
        } else {
            this.connections.remove(connection);
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            return true;
        }
    }

    public final synchronized int connectionCount() {
        return this.connections.size();
    }

    public final void evictAll() {
        ArrayList<RealConnection> arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealConnection> it2 = this.connections.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it2, "connections.iterator()");
            while (it2.hasNext()) {
                RealConnection connection = it2.next();
                if (connection.getTransmitters().isEmpty()) {
                    connection.setNoNewExchanges(true);
                    Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                    arrayList.add(connection);
                    it2.remove();
                }
            }
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            Unit unit = Unit.INSTANCE;
        }
        for (RealConnection realConnection : arrayList) {
            Util.closeQuietly(realConnection.socket());
        }
    }

    @NotNull
    public final RouteDatabase getRouteDatabase() {
        return this.routeDatabase;
    }

    public final synchronized int idleConnectionCount() {
        int i;
        ArrayDeque<RealConnection> arrayDeque = this.connections;
        i = 0;
        if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
            for (RealConnection realConnection : arrayDeque) {
                if (realConnection.getTransmitters().isEmpty() && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    public final void put(@NotNull RealConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            outline107.append(currentThread.getName());
            outline107.append(" MUST hold lock on ");
            outline107.append(this);
            throw new AssertionError(outline107.toString());
        }
        this.connections.add(connection);
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
    }

    public final boolean transmitterAcquirePooledConnection(@NotNull Address address, @NotNull Transmitter transmitter, @Nullable List<Route> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(transmitter, "transmitter");
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            outline107.append(currentThread.getName());
            outline107.append(" MUST hold lock on ");
            outline107.append(this);
            throw new AssertionError(outline107.toString());
        }
        Iterator<RealConnection> it2 = this.connections.iterator();
        while (it2.hasNext()) {
            RealConnection connection = it2.next();
            if (!z || connection.isMultiplexed()) {
                if (connection.isEligible$okhttp(address, list)) {
                    Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                    transmitter.acquireConnectionNoEvents(connection);
                    return true;
                }
            }
        }
        return false;
    }
}
