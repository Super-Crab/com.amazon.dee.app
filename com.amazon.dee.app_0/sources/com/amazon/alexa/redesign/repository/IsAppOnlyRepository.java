package com.amazon.alexa.redesign.repository;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.redesign.utils.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.api.ElementLocalStorage;
import org.json.JSONObject;
import rx.schedulers.Schedulers;
/* loaded from: classes10.dex */
public class IsAppOnlyRepository {
    private static final String TAG = "IsAppOnlyRepository";
    private Context context;
    private ElementLocalStorage elementLocalStorage;
    private EventBus eventBus;
    @VisibleForTesting
    boolean isFirstLoad = true;
    private SharedPreferences sharedPreferences;

    public IsAppOnlyRepository(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("AppOnlyStatus", 0);
    }

    @VisibleForTesting
    void checkIfFirstLoadAndSaveAppOnlyStatus(boolean z) {
        if (this.isFirstLoad) {
            this.elementLocalStorage = (ElementLocalStorage) GeneratedOutlineSupport1.outline20(ElementLocalStorage.class);
            this.elementLocalStorage.put("AppOnlyStatus", "isAppOnly", String.valueOf(z), null).subscribeOn(Schedulers.io()).subscribe();
            GeneratedOutlineSupport1.outline143(this.sharedPreferences, "isAppOnly", z);
            this.isFirstLoad = false;
            subscribeToSignout();
        }
    }

    public /* synthetic */ void lambda$subscribeToSignout$1$IsAppOnlyRepository(Message message) {
        this.isFirstLoad = true;
        this.elementLocalStorage.remove("AppOnlyStatus", "isAppOnly").subscribeOn(Schedulers.io()).subscribe();
        this.sharedPreferences.edit().clear().apply();
    }

    public void persistIsAppOnlyStatus(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("metadata");
        if (optJSONObject != null) {
            try {
                checkIfFirstLoadAndSaveAppOnlyStatus(optJSONObject.getBoolean(Constants.IS_APP_ONLY_RESPONSE_FIELD));
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @VisibleForTesting
    void subscribeToSignout() {
        this.eventBus.getNewSubscriber().subscribeFilter($$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$IsAppOnlyRepository$vciEFppgsnmQEO_Di1ibFs7hmD8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                IsAppOnlyRepository.this.lambda$subscribeToSignout$1$IsAppOnlyRepository(message);
            }
        });
    }
}
