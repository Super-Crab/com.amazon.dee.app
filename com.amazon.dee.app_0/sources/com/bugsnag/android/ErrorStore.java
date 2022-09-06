package com.bugsnag.android;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bugsnag.android.FileStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ErrorStore extends FileStore<Error> {
    static final Comparator<File> ERROR_REPORT_COMPARATOR = new Comparator<File>() { // from class: com.bugsnag.android.ErrorStore.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file == null && file2 == null) {
                return 0;
            }
            if (file == null) {
                return 1;
            }
            if (file2 != null) {
                return file.getName().replaceAll(ErrorStore.STARTUP_CRASH, "").compareTo(file2.getName().replaceAll(ErrorStore.STARTUP_CRASH, ""));
            }
            return -1;
        }
    };
    private static final int LAUNCH_CRASH_POLL_MS = 50;
    private static final long LAUNCH_CRASH_TIMEOUT_MS = 2000;
    private static final String STARTUP_CRASH = "_startupcrash";
    volatile boolean flushOnLaunchCompleted;
    private final Semaphore semaphore;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorStore(@NonNull Configuration configuration, @NonNull Context context, FileStore.Delegate delegate) {
        super(configuration, context, configuration.getErrorStore(), 128, ERROR_REPORT_COMPARATOR, delegate);
        this.flushOnLaunchCompleted = false;
        this.semaphore = new Semaphore(1);
    }

    private List<File> findLaunchCrashReports(Collection<File> collection) {
        ArrayList arrayList = new ArrayList();
        for (File file : collection) {
            if (isLaunchCrashReport(file)) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    private void flushErrorReport(File file) {
        Report report;
        try {
            if (this.config.getBeforeSendTasks().isEmpty()) {
                report = new Report(this.config.getApiKey(), file);
            } else {
                Report report2 = new Report(this.config.getApiKey(), ErrorReader.readError(this.config, file));
                for (BeforeSend beforeSend : this.config.getBeforeSendTasks()) {
                    try {
                    } catch (Throwable th) {
                        Logger.warn("BeforeSend threw an Exception", th);
                    }
                    if (!beforeSend.run(report2)) {
                        deleteStoredFiles(Collections.singleton(file));
                        Logger.info("Deleting cancelled error file " + file.getName());
                        return;
                    }
                    continue;
                }
                report = report2;
            }
            this.config.getDelivery().deliver(report, this.config);
            deleteStoredFiles(Collections.singleton(file));
            Logger.info("Deleting sent error file " + file.getName());
        } catch (DeliveryFailureException e) {
            cancelQueuedFiles(Collections.singleton(file));
            Logger.warn("Could not send previously saved error(s) to Bugsnag, will try again later", e);
        } catch (FileNotFoundException e2) {
            Logger.warn("Ignoring empty file - oldest report on disk was deleted", e2);
        } catch (Exception e3) {
            FileStore.Delegate delegate = this.delegate;
            if (delegate != null) {
                delegate.onErrorIOFailure(e3, file, "Crash Report Deserialization");
            }
            deleteStoredFiles(Collections.singleton(file));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushAsync() {
        if (this.storeDirectory == null) {
            return;
        }
        try {
            Async.run(new Runnable() { // from class: com.bugsnag.android.ErrorStore.3
                @Override // java.lang.Runnable
                public void run() {
                    ErrorStore errorStore = ErrorStore.this;
                    errorStore.flushReports(errorStore.findStoredFiles());
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.warn("Failed to flush all on-disk errors, retaining unsent errors for later.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushOnLaunch() {
        long j = 0;
        if (this.config.getLaunchCrashThresholdMs() != 0) {
            List<File> findStoredFiles = findStoredFiles();
            final List<File> findLaunchCrashReports = findLaunchCrashReports(findStoredFiles);
            findStoredFiles.removeAll(findLaunchCrashReports);
            cancelQueuedFiles(findStoredFiles);
            if (!findLaunchCrashReports.isEmpty()) {
                this.flushOnLaunchCompleted = false;
                Logger.info("Attempting to send launch crash reports");
                try {
                    Async.run(new Runnable() { // from class: com.bugsnag.android.ErrorStore.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ErrorStore.this.flushReports(findLaunchCrashReports);
                            ErrorStore.this.flushOnLaunchCompleted = true;
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.warn("Failed to flush launch crash reports", e);
                    this.flushOnLaunchCompleted = true;
                }
                while (!this.flushOnLaunchCompleted && j < 2000) {
                    try {
                        Thread.sleep(50L);
                        j += 50;
                    } catch (InterruptedException unused) {
                        Logger.warn("Interrupted while waiting for launch crash report request");
                    }
                }
                Logger.info("Continuing with Bugsnag initialisation");
            }
        }
        flushAsync();
    }

    void flushReports(Collection<File> collection) {
        if (!collection.isEmpty()) {
            if (!this.semaphore.tryAcquire(1)) {
                return;
            }
            try {
                Logger.info(String.format(Locale.US, "Sending %d saved error(s) to Bugsnag", Integer.valueOf(collection.size())));
                for (File file : collection) {
                    flushErrorReport(file);
                }
            } finally {
                this.semaphore.release(1);
            }
        }
    }

    @Override // com.bugsnag.android.FileStore
    @NonNull
    String getFilename(Object obj) {
        String str;
        if (obj instanceof Error) {
            Map<String, Object> appData = ((Error) obj).getAppData();
            str = (!(appData instanceof Map) || !(appData.get("duration") instanceof Number) || !isStartupCrash(((Number) appData.get("duration")).longValue())) ? "" : STARTUP_CRASH;
        } else {
            str = "not-jvm";
        }
        return String.format(Locale.US, "%s%d_%s%s.json", this.storeDirectory, Long.valueOf(System.currentTimeMillis()), UUID.randomUUID().toString(), str);
    }

    boolean isLaunchCrashReport(File file) {
        return file.getName().endsWith("_startupcrash.json");
    }

    boolean isStartupCrash(long j) {
        return j < this.config.getLaunchCrashThresholdMs();
    }
}
