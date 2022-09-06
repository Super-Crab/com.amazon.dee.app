package amazon.speech.nexusclient;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class DeviceIdRetriever {
    private static final String PROVIDER_URI_AUTHORITY = "amazon.speech.sim.metrics.NexusContentProvider";
    private static final String PROVIDER_URI_PATH = "deviceId";
    private static final String PROVIDER_URI_SCHEME = "content";
    private static final String TAG = GeneratedOutlineSupport1.outline39(DeviceIdRetriever.class, GeneratedOutlineSupport1.outline107("SPCH-Nexus-"));
    private String mCachedDeviceId;
    private final ContentResolverWrapper mResolverWrapper;

    /* loaded from: classes.dex */
    public static class ContentResolverWrapper {
        private final ContentResolver mResolver;

        public ContentResolverWrapper(ContentResolver contentResolver) {
            this.mResolver = contentResolver;
        }

        public Cursor query(Uri uri) {
            return this.mResolver.query(uri, null, null, null, null);
        }
    }

    /* loaded from: classes.dex */
    public static class DeviceIdRetrieverException extends Exception {
        public DeviceIdRetrieverException(String str) {
            super(str);
        }

        public DeviceIdRetrieverException(Exception exc) {
            super(exc);
        }
    }

    public DeviceIdRetriever(Context context) {
        this(new ContentResolverWrapper(context.getContentResolver()), null);
    }

    public synchronized String retrieve() throws DeviceIdRetrieverException {
        if (this.mCachedDeviceId == null) {
            Log.i(TAG, "Retrieving Nexus deviceId from ContentResolver");
            Cursor query = this.mResolverWrapper.query(new Uri.Builder().scheme("content").authority(PROVIDER_URI_AUTHORITY).path("deviceId").build());
            try {
                if (query.moveToNext()) {
                    String string = query.getString(0);
                    if (string != null) {
                        this.mCachedDeviceId = string;
                        query.close();
                    } else {
                        throw new DeviceIdRetrieverException("Null deviceId");
                    }
                } else {
                    throw new DeviceIdRetrieverException("Cursor empty");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new DeviceIdRetrieverException(e);
            }
        }
        return this.mCachedDeviceId;
    }

    DeviceIdRetriever(ContentResolverWrapper contentResolverWrapper, String str) {
        this.mResolverWrapper = contentResolverWrapper;
        this.mCachedDeviceId = str;
    }
}
