package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.io.Serializable;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ce implements ci {
    private final ec dr;
    private final ds ia;
    private final ed o;
    public static final Uri hW = Uri.parse("content://com.amazon.sso.device.data");
    public static final Uri hX = Uri.parse("content://com.amazon.sso.device.data.directboot");
    public static final String[] hY = {ADMRegistrationConstants.CALL_EXCEPTION, "exception_message"};
    private static final String[] hZ = {"value", "isPersistent"};
    private static final String TAG = ce.class.getName();

    public ce(ed edVar) {
        this(edVar, new ec(edVar), new ds(edVar));
    }

    private cf a(final String str, final Uri uri) throws DeviceDataStoreException {
        try {
            return (cf) this.dr.a(uri, new dj<cf>() { // from class: com.amazon.identity.auth.device.ce.1
                @Override // com.amazon.identity.auth.device.dj
                /* renamed from: d */
                public cf b(ContentProviderClient contentProviderClient) throws Exception {
                    Cursor query = contentProviderClient.query(uri, ce.hZ, str, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                ce.a(query);
                                String e = ic.e(query, "value");
                                String e2 = ic.e(query, "isPersistent");
                                boolean parseBoolean = Boolean.parseBoolean(e2);
                                String str2 = ce.TAG;
                                String.format("Received Key=%s, Value=%s, Persistent=%s", str, e, e2);
                                io.dm(str2);
                                cf cfVar = new cf(e, parseBoolean);
                                query.close();
                                return cfVar;
                            }
                        } catch (Throwable th) {
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                    String format = String.format("Key %s was not found in the device data store.", str);
                    if (str.equals(DeviceDataKeys.KEY_KE_DEVICE) || str.equals(DeviceDataKeys.KEY_RE_DEVICE)) {
                        format = format + String.format(" This device does not support %s. This error is expected if the device not support %s.", str, str);
                    }
                    throw new DeviceDataStoreException(format);
                }
            });
        } catch (RemoteMAPException e) {
            io.e(TAG, "Got a RemoteMAPException", e);
            if (e.getCause() instanceof DeviceDataStoreException) {
                throw ((DeviceDataStoreException) e.getCause());
            }
            throw new DeviceDataStoreException("Failed to query device data store: " + e.getMessage());
        }
    }

    public static ce t(Context context) {
        return new ce(ed.M(context));
    }

    @Override // com.amazon.identity.auth.device.ci
    public cf aP(String str) throws DeviceDataStoreException {
        if (this.ia.dA()) {
            io.i(TAG, String.format("%s try get device data in direct mode for %s", this.o.getPackageName(), str));
            return a(str, hX);
        }
        String str2 = TAG;
        String.format("%s try get device data out of direct mode for %s", this.o.getPackageName(), str);
        io.dm(str2);
        return a(str, hW);
    }

    public ce(ed edVar, ec ecVar, ds dsVar) {
        this.dr = ecVar;
        this.ia = dsVar;
        this.o = edVar;
    }

    static /* synthetic */ void a(Cursor cursor) throws DeviceDataStoreException {
        DeviceDataStoreException deviceDataStoreException;
        String e = ic.e(cursor, ADMRegistrationConstants.CALL_EXCEPTION);
        String e2 = ic.e(cursor, "exception_message");
        if (!TextUtils.isEmpty(e)) {
            try {
                Serializable du = iu.du(e);
                if (du instanceof DeviceDataStoreException) {
                    deviceDataStoreException = (DeviceDataStoreException) du;
                } else {
                    deviceDataStoreException = new DeviceDataStoreException(String.format("The output of deserialized exception from DeviceDataProvider is not a DeviceDataStoreException instance. Original exception message is %s.", e2));
                }
                throw deviceDataStoreException;
            } catch (Exception e3) {
                io.e(TAG, "Unable to deserialize exception from DeviceDataProvider", e3);
            }
        }
    }
}
