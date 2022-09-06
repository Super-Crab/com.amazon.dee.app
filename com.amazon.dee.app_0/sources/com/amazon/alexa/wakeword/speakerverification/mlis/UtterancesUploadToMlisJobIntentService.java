package com.amazon.alexa.wakeword.speakerverification.mlis;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentController;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class UtterancesUploadToMlisJobIntentService extends SafeDequeueJobIntentService {
    private static final int DEFAULT_UPLOAD_RETRIES = 10;
    private static final int JOB_ID = 32092;
    private static final int MINUTES_TO_WAIT = 180000;
    private static final String TAG = UtterancesUploadToMlisJobIntentService.class.getSimpleName();
    private final Initializer mInitializer = InitializerProvider.getInitializer();

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, UtterancesUploadToMlisJobIntentService.class, (int) JOB_ID, intent);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        onHandleWork(this, (PersonIdProvider) GeneratedOutlineSupport1.outline21(PersonIdProvider.class), new SpeakerVerificationMlisClient(this), intent, new SpeakerVerificationAuthority(this, null));
    }

    @VisibleForTesting
    void onHandleWork(@NonNull Context context, @NonNull PersonIdProvider personIdProvider, @NonNull SpeakerVerificationMlisClient speakerVerificationMlisClient, @Nullable Intent intent, @Nullable SpeakerVerificationAuthority speakerVerificationAuthority) {
        int i = intent.getExtras().getInt(EnrollmentController.UPLOAD_UTTERANCES_RETRIES, 10);
        String str = TAG;
        Log.d(str, "onHandleWork: About to attempt upload with remaining retries " + i);
        String personId = personIdProvider.getPersonId();
        if (personId != null && !personId.equals("")) {
            List<EnrollmentUtterance> enrollmentUtterances = speakerVerificationAuthority.getEnrollmentUtterances(personId);
            if (enrollmentUtterances != null && !enrollmentUtterances.isEmpty()) {
                if (!isNetworkAvailable()) {
                    Log.d(TAG, "onHandleWork: network is not available, schedule a retry.");
                    new UtterancesUploadToMlisRetry().scheduleRetry(context, i);
                    return;
                }
                Log.i(TAG, "onHandleWork : start Mlis Uploading. ");
                for (EnrollmentUtterance enrollmentUtterance : enrollmentUtterances) {
                    if (!speakerVerificationMlisClient.startMlisUpload(enrollmentUtterance)) {
                        Log.d(TAG, "onHandleWork: The utterances uploading to MLIS failed, schedule a retry.");
                        new UtterancesUploadToMlisRetry().scheduleRetry(context, i);
                        return;
                    }
                }
                return;
            }
            Log.d(TAG, "onHandleWork: can't get enrollment utterances, upload cancelled.");
            return;
        }
        Log.d(TAG, "onHandleWork: Person Id is null, upload cancelled.");
    }
}
