package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.nearmiss.NearMissManifest;
import io.reactivex.rxjava3.core.Completable;
import org.json.JSONException;
/* loaded from: classes.dex */
public final class DummyMlisClient implements MlisClient {
    @Override // com.amazon.alexa.accessory.nearmiss.MlisClient
    public Completable upload(NearMissManifest nearMissManifest) {
        try {
            Logger.d("Near miss upload was requested from Dummy MLIS client. manifest=%s", nearMissManifest.toJsonObject().toString());
        } catch (JSONException e) {
            Logger.e("Dummy mlis client failed to print manifest", e);
        }
        return Completable.complete();
    }
}
