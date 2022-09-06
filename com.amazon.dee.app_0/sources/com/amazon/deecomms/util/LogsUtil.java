package com.amazon.deecomms.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import androidx.core.content.FileProvider;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class LogsUtil {
    private static final String COMMS_APP_ADDRESS = "comms-app-feedback@amazon.com";
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = "beta_user_log.txt";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, LogsUtil.class);
    private static AtomicBoolean uploadLogsIsInProgress = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ProcessWithTimeout extends Thread {
        Process mProcess;

        public ProcessWithTimeout(Process process) {
            this.mProcess = process;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                this.mProcess.waitFor();
            } catch (InterruptedException e) {
                LogsUtil.LOG.i("Process interrupted", e);
            }
        }

        void waitForProcess(long j) {
            start();
            try {
                join(j);
            } catch (InterruptedException unused) {
                interrupt();
            }
        }
    }

    private LogsUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$saveLogsAndSendViaEmail$0(File file, File file2, Activity activity) {
        try {
            try {
                if (file.exists()) {
                    LOG.i("Deleting the existing log file");
                    if (!file.delete()) {
                        CommsLogger commsLogger = LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("file did not get deleted ");
                        sb.append(file.getAbsolutePath());
                        commsLogger.i(sb.toString());
                    }
                }
                Runtime runtime = Runtime.getRuntime();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("logcat -d -v time -b all -f ");
                sb2.append(file);
                Process exec = runtime.exec(sb2.toString());
                new ProcessWithTimeout(exec).waitForProcess(5000L);
                exec.destroy();
                LOG.i(" Successfully retrieved logs ");
                sendEmail(file2, activity);
            } catch (IOException e) {
                LOG.e(" Exception while trying to retrieve logs", e);
            }
        } finally {
            uploadLogsIsInProgress.set(false);
        }
    }

    public static void saveLogsAndSendViaEmail(final Activity activity) {
        if (uploadLogsIsInProgress.get()) {
            LOG.i("Uploading logs is in progress. Cannot save logs now");
            return;
        }
        uploadLogsIsInProgress.set(true);
        final File file = new File(activity.getApplicationContext().getCacheDir(), LOG_DIR);
        if (!file.exists()) {
            LOG.i(" Creating logDir for AlexaAndroidBetaLogs ");
            if (!file.mkdirs()) {
                return;
            }
        }
        final File file2 = new File(file, LOG_FILE);
        ThreadUtils.runOffMainThread(new Runnable() { // from class: com.amazon.deecomms.util.-$$Lambda$LogsUtil$Rm_79Q2wLhvCCx3Q9J_Lqx3iRew
            @Override // java.lang.Runnable
            public final void run() {
                LogsUtil.lambda$saveLogsAndSendViaEmail$0(file2, file, activity);
            }
        });
    }

    private static void sendEmail(File file, Activity activity) {
        LOG.i(" Sending Email ");
        Context applicationContext = activity.getApplicationContext();
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.setType("vnd.android.cursor.dir/email");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{COMMS_APP_ADDRESS});
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (File file2 : listFiles) {
                if (file2.exists()) {
                    int i = Build.VERSION.SDK_INT;
                    arrayList.add(FileProvider.getUriForFile(applicationContext, "com.amazon.deecomms.common.genericfileprovider", file2));
                }
            }
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        }
        intent.putExtra("android.intent.extra.SUBJECT", applicationContext.getResources().getString(R.string.email_subject));
        intent.putExtra("android.intent.extra.TEXT", applicationContext.getResources().getString(R.string.email_body));
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            LOG.e("Attempting to send email via external client, but client doesn't exist.");
        } else {
            activity.startActivity(Intent.createChooser(intent, applicationContext.getResources().getString(R.string.send_email)));
        }
    }
}
