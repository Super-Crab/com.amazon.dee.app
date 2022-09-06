package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cd {
    private static final String TAG = "com.amazon.identity.auth.device.cd";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a extends cx {
        private final ec dr;
        private String fd;

        public a(Context context) {
            super(context);
            this.dr = new ec(context);
        }

        @Override // com.amazon.identity.auth.device.cx, com.amazon.identity.auth.device.ea
        public synchronized String getDeviceSerialNumber() {
            if (this.fd != null) {
                return this.fd;
            }
            try {
                this.fd = cd.a(this.dr, "dsn");
            } catch (RemoteMAPException e) {
                io.e(cd.TAG, "Unable to retrieve Device Serial Number from Amazon Device Information Component. Falling back to 3P value.", e);
                this.fd = super.getDeviceSerialNumber();
            }
            return this.fd;
        }
    }

    private cd() {
    }

    public static String a(ec ecVar, final String str) throws RemoteMAPException {
        try {
            return (String) ecVar.a(DeviceInformationContract.AUTHORITY_URI, new dj<String>() { // from class: com.amazon.identity.auth.device.cd.1
                @Override // com.amazon.identity.auth.device.dj
                /* renamed from: c */
                public String b(ContentProviderClient contentProviderClient) throws Exception {
                    String[] strArr = {str};
                    Cursor query = contentProviderClient.query(DeviceInformationContract.DeviceInfo.CONTENT_URI, strArr, null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                String e = ic.e(query, strArr[0]);
                                query.close();
                                return e;
                            }
                        } catch (Throwable th) {
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                    throw new RemoteMAPException("Null or empty result returned from Amazon Device Information Provider.");
                }
            });
        } catch (RemoteMAPException e) {
            mq.b("CouldNotContactADIP:".concat(String.valueOf(str)), new String[0]);
            throw e;
        }
    }
}
