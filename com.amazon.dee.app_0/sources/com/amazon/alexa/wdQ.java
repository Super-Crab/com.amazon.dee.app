package com.amazon.alexa;

import android.content.ComponentName;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.Qrg;
import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.client.alexaservice.networking.adapters.CapabilityAdapter;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.messages.PackageName;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SqliteExternalCapabilitiesDao.java */
@Singleton
/* loaded from: classes.dex */
public class wdQ implements lWz {
    public static final CapabilityAdapter BIo = new CapabilityAdapter();
    public static final String zZm = "com.amazon.alexa.wdQ";
    public final ZVz zQM;
    public final Gson zyO;

    @Inject
    public wdQ(ZVz zVz, Gson gson) {
        this.zQM = zVz;
        this.zyO = gson;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0077: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:33:0x0077 */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.alexa.KHc BIo(java.lang.String r15) {
        /*
            r14 = this;
            r0 = 0
            com.amazon.alexa.ZVz r1 = r14.zQM     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            if (r1 == 0) goto L38
            r3 = 1
            java.lang.String r4 = "externalCapabilitiesRegistration"
            r5 = 0
            java.lang.String r6 = "capabilityComponentName == ?"
            r12 = 1
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r13 = 0
            r7[r13] = r15     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r2 = r1
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Exception -> L36 java.lang.Throwable -> L76
            if (r3 == 0) goto L28
            com.amazon.alexa.KHc r0 = r14.zZm(r2)     // Catch: java.lang.Exception -> L36 java.lang.Throwable -> L76
        L28:
            java.lang.String r3 = "externalCapabilitiesRegistration"
            java.lang.String r4 = "capabilityComponentName == ?"
            java.lang.String[] r5 = new java.lang.String[r12]     // Catch: java.lang.Exception -> L36 java.lang.Throwable -> L76
            r5[r13] = r15     // Catch: java.lang.Exception -> L36 java.lang.Throwable -> L76
            r1.delete(r3, r4, r5)     // Catch: java.lang.Exception -> L36 java.lang.Throwable -> L76
            r15 = r0
            r0 = r2
            goto L4f
        L36:
            r1 = move-exception
            goto L59
        L38:
            java.lang.String r1 = com.amazon.alexa.wdQ.zZm     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r2.<init>()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            java.lang.String r3 = "Database null. Remove Capability failed for "
            r2.append(r3)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r2.append(r15)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r15 = r0
        L4f:
            if (r0 == 0) goto L75
            r0.close()
            goto L75
        L55:
            r15 = move-exception
            goto L78
        L57:
            r1 = move-exception
            r2 = r0
        L59:
            java.lang.String r3 = com.amazon.alexa.wdQ.zZm     // Catch: java.lang.Throwable -> L76
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76
            r4.<init>()     // Catch: java.lang.Throwable -> L76
            java.lang.String r5 = "Remove Capability failed for "
            r4.append(r5)     // Catch: java.lang.Throwable -> L76
            r4.append(r15)     // Catch: java.lang.Throwable -> L76
            java.lang.String r15 = r4.toString()     // Catch: java.lang.Throwable -> L76
            android.util.Log.e(r3, r15, r1)     // Catch: java.lang.Throwable -> L76
            if (r2 == 0) goto L74
            r2.close()
        L74:
            r15 = r0
        L75:
            return r15
        L76:
            r15 = move-exception
            r0 = r2
        L78:
            if (r0 == 0) goto L7d
            r0.close()
        L7d:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wdQ.BIo(java.lang.String):com.amazon.alexa.KHc");
    }

    public boolean jiA(KHc kHc) {
        String zQM = zQM(kHc);
        try {
            SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
            if (TextUtils.isEmpty(zQM)) {
                Log.e(zZm, "Failed to read expected data from ExternalCapabilityPreregistrationEntity. Null or missing Component data");
                return false;
            }
            if (zZm(zQM)) {
                if (!BIo(kHc)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Entry already exists for ");
                    sb.append(zQM);
                    sb.toString();
                    return true;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Clearing existing capability for update: ");
                sb2.append(zQM);
                sb2.toString();
                BIo(zQM);
            }
            ContentValues zZm2 = zZm(kHc);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Values ");
            sb3.append(zZm2);
            sb3.toString();
            if (writableDatabase.insert("externalCapabilitiesRegistration", null, zZm2) == -1) {
                String str = zZm;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Failed to store capability in database. ");
                sb4.append(zQM);
                Log.e(str, sb4.toString());
                return false;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Stored capability in database ");
            sb5.append(zQM);
            sb5.toString();
            return true;
        } catch (Exception e) {
            String str2 = zZm;
            Log.e(str2, "Failed to store capability in database " + zQM, e);
            return false;
        }
    }

    @NonNull
    public final String zQM(KHc kHc) {
        if (kHc == null) {
            Log.e(zZm, "cannot read database key, externalCapabilityPreregistrationEntity is null");
            return "";
        }
        return zZm(((Qrg) kHc).zZm);
    }

    public final String zZm(ComponentName componentName) {
        if (componentName != null && !TextUtils.isEmpty(componentName.flattenToString())) {
            return componentName.flattenToString();
        }
        Log.e(zZm, "cannot read database key, ComponentName is null");
        return "";
    }

    public KHc zyO(KHc kHc) {
        if (kHc == null) {
            Log.e(zZm, "Cannot remove ExternalCapabilityPreregistrationEntity due to null value");
            return null;
        }
        String zZm2 = zZm(((Qrg) kHc).zZm);
        if (TextUtils.isEmpty(zZm2)) {
            Log.e(zZm, "cannot remove capability as componentName is null");
            return null;
        }
        return BIo(zZm2);
    }

    public boolean zZm(Set<KHc> set) {
        if (set == null) {
            Log.e(zZm, "Set of registrations is null, cannot save set.");
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
                writableDatabase.beginTransaction();
                Iterator<KHc> it2 = set.iterator();
                while (true) {
                    boolean z = true;
                    if (it2.hasNext()) {
                        KHc next = it2.next();
                        String zQM = zQM(next);
                        if (zZm(zQM)) {
                            if (BIo(next)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Clearing existing capability for update: ");
                                sb.append(zQM);
                                sb.toString();
                                BIo(zQM);
                            } else {
                                z = false;
                            }
                        }
                        if (z && !jiA(next)) {
                            String str = zZm;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Adding set of Capabilities failed on: ");
                            sb2.append(zQM);
                            Log.i(str, sb2.toString());
                            writableDatabase.endTransaction();
                            return false;
                        }
                    } else {
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.e(zZm, "Adding set of Capabilities failed", e);
                if (0 != 0) {
                    sQLiteDatabase.endTransaction();
                }
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
    }

    public void zQM() {
        try {
            this.zQM.close();
        } catch (Exception e) {
            Log.e(zZm, "Failed to tear down the database.", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean BIo(com.amazon.alexa.KHc r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r2 = r16.zQM(r17)
            r3 = 0
            r4 = 1
            r5 = 0
            com.amazon.alexa.ZVz r0 = r1.zQM     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            android.database.sqlite.SQLiteDatabase r6 = r0.getReadableDatabase()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r7 = 1
            java.lang.String r8 = "externalCapabilitiesRegistration"
            r9 = 0
            java.lang.String r10 = "capabilityComponentName == ?"
            java.lang.String[] r11 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r11[r3] = r2     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            boolean r0 = r6.moveToNext()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L82
            if (r0 == 0) goto L2b
            com.amazon.alexa.KHc r5 = r1.zZm(r6)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L82
        L2b:
            r6.close()
            goto L50
        L2f:
            r0 = move-exception
            goto L35
        L31:
            r0 = move-exception
            goto L84
        L33:
            r0 = move-exception
            r6 = r5
        L35:
            java.lang.String r7 = com.amazon.alexa.wdQ.zZm     // Catch: java.lang.Throwable -> L82
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82
            r8.<init>()     // Catch: java.lang.Throwable -> L82
            java.lang.String r9 = "Get CapabilityAgent failed for "
            r8.append(r9)     // Catch: java.lang.Throwable -> L82
            r8.append(r2)     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = r8.toString()     // Catch: java.lang.Throwable -> L82
            android.util.Log.e(r7, r2, r0)     // Catch: java.lang.Throwable -> L82
            if (r6 == 0) goto L50
            r6.close()
        L50:
            r0 = r17
            com.amazon.alexa.Qrg r0 = (com.amazon.alexa.Qrg) r0
            com.amazon.alexa.jVi r0 = r0.zQM
            java.lang.String r0 = r0.getValue()
            com.amazon.alexa.Qrg r5 = (com.amazon.alexa.Qrg) r5
            com.amazon.alexa.jVi r2 = r5.zQM
            java.lang.String r2 = r2.getValue()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Testing for upgraded versions. Pending version: "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r6 = " Current Version: "
            r5.append(r6)
            r5.append(r2)
            r5.toString()
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L81
            return r3
        L81:
            return r4
        L82:
            r0 = move-exception
            r5 = r6
        L84:
            if (r5 == 0) goto L89
            r5.close()
        L89:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wdQ.BIo(com.amazon.alexa.KHc):boolean");
    }

    public boolean zZm(boolean z, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
        if (!zZm(alexaCapabilityAgentRegistration.componentName.flattenToString())) {
            Log.e("TAG", "requested capability agent does not exist");
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("isFollowing", Boolean.valueOf(z));
            writableDatabase.update("externalCapabilitiesRegistration", contentValues, "capabilityComponentName == ?", new String[]{alexaCapabilityAgentRegistration.componentName.flattenToString()});
            return true;
        } catch (Exception e) {
            Log.e(zZm, "Failed to update following status", e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.amazon.alexa.KHc> zZm() {
        /*
            r13 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.amazon.alexa.ZVz r2 = r13.zQM     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            android.database.sqlite.SQLiteDatabase r3 = r2.getReadableDatabase()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r4 = 1
            java.lang.String r5 = "externalCapabilitiesRegistration"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
        L1a:
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4f
            if (r3 == 0) goto L28
            com.amazon.alexa.KHc r3 = r13.zZm(r2)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4f
            r0.add(r3)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4f
            goto L1a
        L28:
            r2.close()
            java.lang.String r1 = "getAllCapabilities Total number read: "
            java.lang.StringBuilder r1 = com.amazon.alexa.C0179Pya.zZm(r1)
            int r2 = r0.size()
            r1.append(r2)
            r1.toString()
            return r0
        L3c:
            r0 = move-exception
            goto L42
        L3e:
            r0 = move-exception
            goto L51
        L40:
            r0 = move-exception
            r2 = r1
        L42:
            java.lang.String r3 = com.amazon.alexa.wdQ.zZm     // Catch: java.lang.Throwable -> L4f
            java.lang.String r4 = "getAllCapabilities failed, returning empty list"
            android.util.Log.e(r3, r4, r0)     // Catch: java.lang.Throwable -> L4f
            if (r2 == 0) goto L4e
            r2.close()
        L4e:
            return r1
        L4f:
            r0 = move-exception
            r1 = r2
        L51:
            if (r1 == 0) goto L56
            r1.close()
        L56:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wdQ.zZm():java.util.List");
    }

    public List<KHc> BIo() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = this.zQM.getReadableDatabase().query(true, "externalCapabilitiesRegistration", null, "isFollowing == ?", new String[]{"1"}, null, null, null, null);
                while (cursor.moveToNext()) {
                    arrayList.add(zZm(cursor));
                }
                cursor.close();
                StringBuilder zZm2 = C0179Pya.zZm("Get Following Capabilities Total number read: ");
                zZm2.append(arrayList.size());
                zZm2.toString();
                return arrayList;
            } catch (Exception e) {
                Log.e(zZm, "Get following CapabilityAgent failed, returning empty list", e);
                ArrayList arrayList2 = new ArrayList();
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList2;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public boolean zZm(@NonNull String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            Log.e(zZm, "Cannot find capabilityAgent as database key is an empty string.");
            return false;
        }
        try {
            i = (int) DatabaseUtils.queryNumEntries(this.zQM.getReadableDatabase(), "externalCapabilitiesRegistration", "capabilityComponentName == ?", new String[]{str});
        } catch (Exception e) {
            String str2 = zZm;
            Log.e(str2, "Does CapabilityAgent exist for " + str, e);
            i = 0;
        }
        return i > 0;
    }

    public final ContentValues zZm(KHc kHc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("capabilityComponentName", zQM(kHc));
        contentValues.put("isFollowing", (Boolean) true);
        Qrg qrg = (Qrg) kHc;
        contentValues.put("sourcePackageName", qrg.BIo.getValue());
        contentValues.put("capabilityAgentVersion", qrg.zQM.getValue());
        contentValues.put("externalCapabilityAgentRegistrationData", this.zyO.toJson(qrg.zyO));
        contentValues.put("autoUpdatePreference", Boolean.valueOf(((GdN) qrg.jiA).zZm));
        contentValues.put("registrationStatus", this.zyO.toJson(qrg.Qle));
        return contentValues;
    }

    @VisibleForTesting
    public KHc zZm(Cursor cursor) throws IOException {
        try {
            ComponentName unflattenFromString = ComponentName.unflattenFromString(cursor.getString(cursor.getColumnIndexOrThrow("capabilityComponentName")));
            String string = cursor.getString(cursor.getColumnIndexOrThrow("sourcePackageName"));
            String string2 = cursor.getString(cursor.getColumnIndexOrThrow("capabilityAgentVersion"));
            qzJ qzj = (qzJ) this.zyO.fromJson(cursor.getString(cursor.getColumnIndexOrThrow("registrationStatus")), (Class<Object>) qzJ.class);
            boolean parseBoolean = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("autoUpdatePreference")));
            String string3 = cursor.getString(cursor.getColumnIndexOrThrow("externalCapabilityAgentRegistrationData"));
            ArrayList<Capability> arrayList = new ArrayList<>();
            Iterator<JsonElement> it2 = new JsonParser().parse(string3).getAsJsonArray().iterator();
            while (it2.hasNext()) {
                arrayList.add(BIo.fromJson(it2.next().toString()));
            }
            Qrg.zZm zzm = new Qrg.zZm();
            if (unflattenFromString != null) {
                zzm.zZm = unflattenFromString;
                return zzm.zZm(PackageName.create(string)).zZm(jVi.zZm(string2)).zZm(arrayList).zZm(zjD.zZm(true)).zZm(new GdN(parseBoolean)).zZm(qzj).zZm(zjD.zZm(true)).zZm();
            }
            throw new NullPointerException("Null componentName");
        } catch (JsonIOException e) {
            Log.e(zZm, "Error reading from the reader", e);
            return null;
        } catch (JsonSyntaxException e2) {
            Log.e(zZm, "Not a valid Json for ExternalCapabilityAgentRegistrationData", e2);
            return null;
        } catch (NullPointerException e3) {
            Log.e(zZm, "One or more expected fields are null", e3);
            return null;
        }
    }
}
