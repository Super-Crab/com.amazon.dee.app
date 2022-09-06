package com.amazon.alexa.accessorykit.interprocess.environment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes6.dex */
public class EnvironmentServiceReceiver extends BroadcastReceiver {
    static final String ALEXA_API_ENDPOINT_EXTRA_KEY = "alexaApiEndpoint";
    static final String ALEXA_API_HOST_EXTRA_KEY = "alexaApiHost";
    static final String AWS_REGION_EXTRA_KEY = "awsRegion";
    static final String ENDPOINT_EXTRA_KEY = "webEndpoint";
    private static final String TAG = "EnvironmentServiceReceiver:";
    static final String ENVIRONMENT_SERVICE_INTENT_ACTION = "com.amazon.alexa.accessorykit.environment.environmentService";
    static final Intent ENVIRONMENT_SERVICE_BASE_INTENT = new Intent(ENVIRONMENT_SERVICE_INTENT_ACTION).setPackage(AccessoriesFactory.getAppName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onReceive$0(Intent intent, Context context) throws Throwable {
        String stringExtra = intent.getStringExtra(ENDPOINT_EXTRA_KEY);
        String stringExtra2 = intent.getStringExtra(ALEXA_API_HOST_EXTRA_KEY);
        String stringExtra3 = intent.getStringExtra(ALEXA_API_ENDPOINT_EXTRA_KEY);
        String stringExtra4 = intent.getStringExtra(AWS_REGION_EXTRA_KEY);
        Logger.d("%s: onReceive endpoint: %s apiHost: %s", TAG, stringExtra, stringExtra2);
        AccessoryEnvironmentServicePreferences.getInstance(context).setEndpoint(stringExtra);
        AccessoryEnvironmentServicePreferences.getInstance(context).setAlexaApiHost(stringExtra2);
        AccessoryEnvironmentServicePreferences.getInstance(context).setAlexaApiEndpoint(stringExtra3);
        AccessoryEnvironmentServicePreferences.getInstance(context).setAWSRegion(stringExtra4);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        if (!ENVIRONMENT_SERVICE_INTENT_ACTION.equals(intent.getAction())) {
            return;
        }
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.-$$Lambda$EnvironmentServiceReceiver$mcC8BNR_Hauh5v7LhrwN5Ky_I2o
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                EnvironmentServiceReceiver.lambda$onReceive$0(intent, context);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.-$$Lambda$EnvironmentServiceReceiver$xmsMltquf5-htISaIpx_qrza1xY
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                goAsync.finish();
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.-$$Lambda$EnvironmentServiceReceiver$SSQXPih84ySwUkU2NQQVG4ACXDw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                goAsync.finish();
            }
        });
    }
}
