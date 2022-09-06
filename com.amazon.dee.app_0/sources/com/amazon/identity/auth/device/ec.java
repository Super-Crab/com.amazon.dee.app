package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ec {
    private static final String TAG = "com.amazon.identity.auth.device.ec";
    private final ContentResolver mContentResolver;
    private final Context mContext;

    public ec(Context context) {
        this(context, context.getContentResolver());
    }

    public int delete(final Uri uri, final String str, final String[] strArr) throws RemoteMAPException {
        return ((Integer) a(uri, new dj<Integer>() { // from class: com.amazon.identity.auth.device.ec.1
            @Override // com.amazon.identity.auth.device.dj
            public /* synthetic */ Integer b(ContentProviderClient contentProviderClient) throws Exception {
                return dT();
            }

            public Integer dT() throws Exception {
                return Integer.valueOf(ec.this.mContentResolver.delete(uri, str, strArr));
            }
        })).intValue();
    }

    public Uri insert(final Uri uri, final ContentValues contentValues) throws RemoteMAPException {
        return (Uri) a(uri, new dj<Uri>() { // from class: com.amazon.identity.auth.device.ec.2
            @Override // com.amazon.identity.auth.device.dj
            public /* synthetic */ Uri b(ContentProviderClient contentProviderClient) throws Exception {
                return dU();
            }

            public Uri dU() throws Exception {
                return ec.this.mContentResolver.insert(uri, contentValues);
            }
        });
    }

    public ec(Context context, ContentResolver contentResolver) {
        this.mContext = context;
        this.mContentResolver = contentResolver;
    }

    @SuppressLint({"NewApi"})
    public <T> T a(Uri uri, dj<T> djVar) throws RemoteMAPException {
        try {
            Context context = this.mContext;
            ProviderInfo a = ek.a(uri, context.getPackageManager());
            if (a != null) {
                ja.C(context, a.packageName);
                int i = 0;
                while (true) {
                    ContentProviderClient contentProviderClient = null;
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        int i2 = Build.VERSION.SDK_INT;
                        contentProviderClient = this.mContentResolver.acquireUnstableContentProviderClient(uri);
                        T b = djVar.b(contentProviderClient);
                        if (contentProviderClient != null) {
                            contentProviderClient.release();
                        }
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return b;
                    } catch (DeviceDataStoreException e) {
                        throw new RemoteMAPException(e);
                    } catch (Exception e2) {
                        if (i <= 0) {
                            try {
                                io.w(TAG, "Got exception querying " + uri + ". Retrying." + e2.getMessage());
                                mq.b("ContentProviderRetry", new String[0]);
                                if (contentProviderClient != null) {
                                    contentProviderClient.release();
                                }
                                Binder.restoreCallingIdentity(clearCallingIdentity);
                                if (i <= 0) {
                                    try {
                                        Thread.sleep(10L);
                                    } catch (InterruptedException e3) {
                                        io.e(TAG, "Got an InterruptedException while retrying calling ".concat(String.valueOf(uri)), e3);
                                        Thread.currentThread().interrupt();
                                    }
                                }
                                i++;
                            } catch (Throwable th) {
                                if (contentProviderClient != null) {
                                    contentProviderClient.release();
                                }
                                Binder.restoreCallingIdentity(clearCallingIdentity);
                                throw th;
                            }
                        } else {
                            io.e(TAG, "Got exception querying " + uri + ". Failing after " + i + " retries.", e2);
                            mq.b("ContentProviderFailure", new String[0]);
                            throw new RemoteMAPException(e2);
                        }
                    }
                }
            } else {
                throw new IllegalStateException(String.format("Authority %s does not exist on the device", uri.getAuthority()));
            }
        } catch (Exception e4) {
            throw new RemoteMAPException(e4);
        }
    }
}
