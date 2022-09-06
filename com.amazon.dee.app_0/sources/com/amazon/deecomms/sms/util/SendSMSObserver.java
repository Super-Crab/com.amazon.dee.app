package com.amazon.deecomms.sms.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.nativemodules.SendSMSBridge;
import com.facebook.react.bridge.ReadableArray;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class SendSMSObserver extends ContentObserver {
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TYPE = "type";
    private static final int MESSAGE_TYPE_ALL = 0;
    private static final int MESSAGE_TYPE_DRAFT = 3;
    private static final int MESSAGE_TYPE_FAILED = 5;
    private static final int MESSAGE_TYPE_INBOX = 1;
    private static final int MESSAGE_TYPE_OUTBOX = 4;
    private static final int MESSAGE_TYPE_QUEUED = 6;
    private static final int MESSAGE_TYPE_SENT = 2;
    private String address;
    private SendSMSBridge bridge;
    private Map<String, Integer> messageTypes;
    private ContentResolver resolver;
    private ReadableArray successTypes;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SendSMSObserver.class);
    public static final Uri URI = Uri.parse("content://sms/");
    private static final String[] PROJECTION = {"address", "type"};

    public SendSMSObserver(Context context, SendSMSBridge sendSMSBridge, ReadableArray readableArray, String str) {
        super(new Handler(Looper.getMainLooper()));
        this.messageTypes = new HashMap();
        this.messageTypes.put("all", 0);
        this.messageTypes.put("inbox", 1);
        this.messageTypes.put("sent", 2);
        this.messageTypes.put("draft", 3);
        this.messageTypes.put("outbox", 4);
        this.messageTypes.put("failed", 5);
        this.messageTypes.put("queued", 6);
        this.successTypes = readableArray;
        this.address = str;
        this.bridge = sendSMSBridge;
        this.resolver = context.getContentResolver();
    }

    private void messageError(String str) {
        LOG.i("Error callback being sent to JS.");
        this.bridge.sendErrorCallback(str);
        stop();
    }

    private void messageSuccess() {
        LOG.i("Success callback being sent to JS.");
        this.bridge.sendSuccessCallback();
        stop();
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        Cursor query = this.resolver.query(URI, PROJECTION, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("type"));
                    String string = query.getString(query.getColumnIndex("address"));
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Sms content observer came across update of type: ");
                    sb.append(i);
                    commsLogger.i(sb.toString());
                    if (StringUtils.isNotEmpty(this.address) && !this.address.equalsIgnoreCase(string)) {
                        CommsLogger commsLogger2 = LOG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Address didn't match. Row address: ");
                        sb2.append(string);
                        sb2.append(" & expected address: ");
                        sb2.append(this.address);
                        commsLogger2.i(sb2.toString());
                        query.close();
                        return;
                    }
                    boolean z2 = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.successTypes.size()) {
                            break;
                        } else if (i == this.messageTypes.get(this.successTypes.getString(i2)).intValue()) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z2) {
                        messageSuccess();
                    } else if (i == 5) {
                        messageError("message send failed.");
                    }
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    public void start() {
        ContentResolver contentResolver = this.resolver;
        if (contentResolver != null) {
            contentResolver.registerContentObserver(URI, true, this);
            return;
        }
        throw new IllegalStateException("Current SMSSendObserver instance is invalid");
    }

    public void stop() {
        ContentResolver contentResolver = this.resolver;
        if (contentResolver != null) {
            contentResolver.unregisterContentObserver(this);
        }
    }
}
