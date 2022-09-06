package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.Iterator;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cl {
    private static final String TAG = "cl";
    private static final Uri in = Uri.parse("content://com.amazon.appmanager.preload.device_info.provider");
    private final Context cM;

    public cl(Context context) {
        this.cM = context.getApplicationContext();
    }

    public String bQ() {
        String str;
        final Uri parse;
        if (mz.b(this.cM, in)) {
            parse = in;
        } else {
            Iterator<ProviderInfo> it2 = new ek(this.cM).ed().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str = null;
                    break;
                }
                str = it2.next().authority;
                if (str != null && str.endsWith(".preload.device_info.provider")) {
                    break;
                }
            }
            parse = str != null ? Uri.parse("content://".concat(str)) : null;
        }
        if (parse == null) {
            return null;
        }
        try {
            return (String) new ec(this.cM).a(parse, new dj<String>() { // from class: com.amazon.identity.auth.device.cl.1
                @Override // com.amazon.identity.auth.device.dj
                /* renamed from: c */
                public String b(ContentProviderClient contentProviderClient) throws Exception {
                    Cursor query = contentProviderClient.query(parse, null, null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                return query.getString(0);
                            }
                        } finally {
                            query.close();
                        }
                    }
                    return query != null ? null : null;
                }
            });
        } catch (RemoteMAPException e) {
            mq.b("ExceptionPreloadContentProvider", parse.toString());
            io.e(TAG, "ExceptionPreloadContentProvider", e);
            return null;
        }
    }
}
