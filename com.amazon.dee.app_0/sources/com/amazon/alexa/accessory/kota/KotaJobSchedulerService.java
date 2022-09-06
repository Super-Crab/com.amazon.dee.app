package com.amazon.alexa.accessory.kota;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class KotaJobSchedulerService extends JobService {
    public static final String INVENTORY_UPDATE_KEY = "inventoryUpdate";
    public static final String UPDATE_REQUEST_KEY = "updateRequest";
    private Disposable disposable;

    public /* synthetic */ void lambda$onStartJob$0$KotaJobSchedulerService(JobParameters jobParameters) throws Throwable {
        jobFinished(jobParameters, false);
        Logger.d("Successfully completed downloadPackage from JobSchedulerService");
    }

    public /* synthetic */ void lambda$onStartJob$1$KotaJobSchedulerService(JobParameters jobParameters, Throwable th) throws Throwable {
        jobFinished(jobParameters, false);
        Logger.e("Caught an exception when trying to download package with KotaJobSchedulerService", th);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        try {
            UpdateRequest mo1239create = new UpdateRequest.JsonBuilder().mo1239create(new JSONObject(jobParameters.getExtras().getString(UPDATE_REQUEST_KEY)));
            InventoryUpdate mo1239create2 = new InventoryUpdate.JsonFactory().mo1239create(new JSONObject(jobParameters.getExtras().getString(INVENTORY_UPDATE_KEY)));
            Logger.d(String.format(Locale.US, "Downloading kota job activated! Requesting to download file %s-%d-%d %s", mo1239create.getComponentId(), Integer.valueOf(mo1239create.getComponentVersion()), Integer.valueOf(mo1239create2.getTargetSoftwareComponentVersionCode()), mo1239create2.getUrl()));
            if (!Accessories.hasSharedInstance()) {
                KotaMetricsHelper.recordJobActivatedSuccess(false, mo1239create.getDeviceType());
                return false;
            }
            this.disposable = Accessories.getSharedInstance().getKotaDownloader().downloadPackage(mo1239create, mo1239create2, true).subscribeOn(Schedulers.io()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$KotaJobSchedulerService$zjvZtUR70Evq0b2-bm3smaHZn9s
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    KotaJobSchedulerService.this.lambda$onStartJob$0$KotaJobSchedulerService(jobParameters);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$KotaJobSchedulerService$kvAxzaOVwyLUynMcJPIhkMiBRxI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    KotaJobSchedulerService.this.lambda$onStartJob$1$KotaJobSchedulerService(jobParameters, (Throwable) obj);
                }
            });
            KotaMetricsHelper.recordJobActivatedSuccess(true, mo1239create.getDeviceType());
            return true;
        } catch (JSONException unused) {
            KotaMetricsHelper.recordJobActivatedSuccess(false, "unknown");
            return false;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        Logger.d("KotaJobSchedulerService stopped");
        return false;
    }
}
