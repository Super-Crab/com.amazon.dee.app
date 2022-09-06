package com.amazon.alexa.accessorykit.finishsetup.persistence;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DefaultRecordSupplier implements FinishSetupRecordSupplier {
    private static final String RECORDS_NAMESPACE = "accessories-fas-records";
    private final PersistentStorage store;

    public DefaultRecordSupplier(PersistentStorage.Factory factory) {
        Preconditions.notNull(factory, "factory");
        this.store = factory.create(RECORDS_NAMESPACE);
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecordSupplier
    @Nullable
    public FinishSetupRecord get(String str) {
        try {
            return new FinishSetupRecord(new JSONObject(this.store.getString(str)));
        } catch (Exception e) {
            Logger.e("Unable to get record with identifier: %s", e, str);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecordSupplier
    public boolean has(String str) {
        return this.store.contains(str);
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecordSupplier
    public void remove(String str) {
        this.store.edit().remove(str).commit();
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.persistence.FinishSetupRecordSupplier
    public void set(FinishSetupRecord finishSetupRecord) {
        try {
            this.store.edit().set(finishSetupRecord.getIdentifier(), finishSetupRecord.toJsonObject().toString()).commit();
        } catch (JSONException e) {
            Logger.e("Unable to set record: %s", e, finishSetupRecord.toString());
        }
    }
}
