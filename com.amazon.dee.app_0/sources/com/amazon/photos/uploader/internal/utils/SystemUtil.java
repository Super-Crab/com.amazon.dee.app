package com.amazon.photos.uploader.internal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.amazon.photos.uploader.blockers.NetworkState;
import com.amazon.photos.uploader.internal.OpenForTesting;
import java.io.File;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SystemUtil.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000fJ\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0015H\u0002J\u0006\u0010\u001a\u001a\u00020\u0005J\u0006\u0010\u001b\u001a\u00020\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "", "context", "Landroid/content/Context;", "isDebugMode", "", "(Landroid/content/Context;Z)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "()Z", "currentTimeMillis", "", "getConnectivityManager", "getFileSize", "filePath", "", "getNetworkRequestBuilder", "Landroid/net/NetworkRequest$Builder;", "getNetworkState", "Lcom/amazon/photos/uploader/blockers/NetworkState;", "getOsVersionCode", "", "getPermission", "permission", "isOsVersionAtLeast", "version", "isOsVersionAtLeastM", "isOsVersionAtLeastO", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SystemUtil {
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private final boolean isDebugMode;

    public SystemUtil(@NotNull Context context, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.isDebugMode = z;
        Object systemService = this.context.getSystemService("connectivity");
        if (systemService != null) {
            this.connectivityManager = (ConnectivityManager) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    private final boolean isOsVersionAtLeast(int i) {
        return getOsVersionCode() >= i;
    }

    public final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @NotNull
    public final ConnectivityManager getConnectivityManager() {
        return this.connectivityManager;
    }

    public final long getFileSize(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new File(filePath).length();
    }

    @NotNull
    public final NetworkRequest.Builder getNetworkRequestBuilder() {
        return new NetworkRequest.Builder();
    }

    @NotNull
    public final NetworkState getNetworkState() {
        return new NetworkState(this);
    }

    public final int getOsVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public final int getPermission(@NotNull String permission) {
        Intrinsics.checkParameterIsNotNull(permission, "permission");
        return ContextCompat.checkSelfPermission(this.context, permission);
    }

    public final boolean isDebugMode() {
        return this.isDebugMode;
    }

    public final boolean isOsVersionAtLeastM() {
        return isOsVersionAtLeast(23);
    }

    public final boolean isOsVersionAtLeastO() {
        return isOsVersionAtLeast(26);
    }

    public /* synthetic */ SystemUtil(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z);
    }
}
