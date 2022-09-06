package com.amazon.deecomms.contacts.util;

import android.content.Context;
import android.util.ArrayMap;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.concurrent.Callable;
import rx.Observable;
/* loaded from: classes12.dex */
public final class ContactNameHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactNameHelper.class);

    private ContactNameHelper() {
    }

    public static ContactName getActiveContactName() {
        CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
        ContactName contactName = new ContactName();
        contactName.setFirstName(commsIdentityManager.getFirstName());
        contactName.setLastName(commsIdentityManager.getLastName());
        return contactName;
    }

    public static ContactName getActiveHomeGroupContactName(Context context) {
        ContactName contactName = new ContactName();
        contactName.setFirstName(context.getResources().getString(R.string.my_home));
        contactName.setLastName("");
        return contactName;
    }

    public static Observable<ContactName> getContactName(final Context context, final String str) {
        return Observable.fromCallable(new Callable<ContactName>() { // from class: com.amazon.deecomms.contacts.util.ContactNameHelper.1
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.deecomms.contacts.model.ContactName mo3670call() throws java.lang.Exception {
                /*
                    r10 = this;
                    java.lang.String r0 = "lastName"
                    java.lang.String r1 = "firstName"
                    java.lang.String[] r5 = new java.lang.String[]{r1, r0}
                    r2 = 1
                    java.lang.String[] r7 = new java.lang.String[r2]
                    java.lang.String r2 = r1
                    r3 = 0
                    r7[r3] = r2
                    androidx.loader.content.CursorLoader r9 = new androidx.loader.content.CursorLoader
                    android.content.Context r3 = r2
                    android.net.Uri r4 = com.amazon.deecomms.contacts.database.provider.ContactProviderContract.getContactJoinPhoneNumberUri(r3)
                    java.lang.String r6 = "commsId = ?"
                    r8 = 0
                    r2 = r9
                    r2.<init>(r3, r4, r5, r6, r7, r8)
                    android.database.Cursor r2 = r9.mo3665loadInBackground()
                    if (r2 == 0) goto L53
                    boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L47
                    if (r3 == 0) goto L53
                    com.amazon.deecomms.contacts.model.ContactName r3 = new com.amazon.deecomms.contacts.model.ContactName     // Catch: java.lang.Throwable -> L47
                    r3.<init>()     // Catch: java.lang.Throwable -> L47
                    int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L47
                    java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L47
                    r3.setFirstName(r1)     // Catch: java.lang.Throwable -> L47
                    int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L47
                    java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Throwable -> L47
                    r3.setLastName(r0)     // Catch: java.lang.Throwable -> L47
                    goto L54
                L47:
                    r0 = move-exception
                    throw r0     // Catch: java.lang.Throwable -> L49
                L49:
                    r1 = move-exception
                    r2.close()     // Catch: java.lang.Throwable -> L4e
                    goto L52
                L4e:
                    r2 = move-exception
                    r0.addSuppressed(r2)
                L52:
                    throw r1
                L53:
                    r3 = 0
                L54:
                    if (r2 == 0) goto L59
                    r2.close()
                L59:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.contacts.util.ContactNameHelper.AnonymousClass1.mo3670call():com.amazon.deecomms.contacts.model.ContactName");
            }
        });
    }

    public static ArrayMap<String, FullContactName> getContactNamesFromConcatenatedValues(String str, String str2, String str3, String str4, String str5) {
        String[] split = str.split(Constants.GROUP_CONCAT_SEPARATOR, -1);
        String[] split2 = str2.split(Constants.GROUP_CONCAT_SEPARATOR, -1);
        String[] split3 = str3.split(Constants.GROUP_CONCAT_SEPARATOR, -1);
        String[] split4 = str4.split(Constants.GROUP_CONCAT_SEPARATOR, -1);
        String[] split5 = str5.split(Constants.GROUP_CONCAT_SEPARATOR, -1);
        ArrayMap<String, FullContactName> arrayMap = new ArrayMap<>();
        if (split.length == split2.length && split.length == split3.length && split.length == split2.length && split.length == split3.length) {
            for (int i = 0; i < split.length; i++) {
                if (Utils.isRegisteredCommsId(split[i])) {
                    if (!arrayMap.containsKey(split[i])) {
                        arrayMap.put(split[i], new FullContactName(getActiveContactName().getFirstName(), getActiveContactName().getLastName(), split4[i], split5[i]));
                    }
                } else if (arrayMap.get(split[i]) == null) {
                    arrayMap.put(split[i], new FullContactName(split2[i], split3[i], split4[i], split5[i]));
                }
            }
        } else {
            LOG.e("Database returned unequal commids and contact names when group concat is done");
        }
        return arrayMap;
    }

    public static String getConversationName(ArrayMap<String, FullContactName> arrayMap, String str, String str2, boolean z, Context context) {
        if (arrayMap == null || arrayMap.isEmpty()) {
            return null;
        }
        return getDisplayNameFor(getParticipantContactName(arrayMap, str, str2, context), z, context);
    }

    private static String getDisplayNameFor(FullContactName fullContactName, boolean z, Context context) {
        if (fullContactName == null) {
            return context.getResources().getString(R.string.unknown_contact);
        }
        if (z) {
            return ContactUtils.getFullName(fullContactName);
        }
        return ContactUtils.getPartialName(fullContactName);
    }

    private static FullContactName getParticipantContactName(ArrayMap<String, FullContactName> arrayMap, String str, String str2, Context context) {
        ArrayMap arrayMap2 = new ArrayMap(arrayMap);
        if (arrayMap.size() == 1) {
            if (arrayMap.containsKey(str2)) {
                return new FullContactName(getActiveHomeGroupContactName(context), null);
            }
            return arrayMap.valueAt(0);
        }
        arrayMap2.remove(str);
        arrayMap2.remove(str2);
        if (!arrayMap2.isEmpty()) {
            return (FullContactName) arrayMap2.valueAt(0);
        }
        return new FullContactName(getActiveHomeGroupContactName(context), null);
    }

    public static String getSenderName(ArrayMap<String, FullContactName> arrayMap, String str, boolean z, Context context) {
        if (arrayMap == null || arrayMap.isEmpty()) {
            return null;
        }
        return getDisplayNameFor(arrayMap.get(str), z, context);
    }
}
