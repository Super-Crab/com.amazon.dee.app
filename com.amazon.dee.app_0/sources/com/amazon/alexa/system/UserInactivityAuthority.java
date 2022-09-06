package com.amazon.alexa.system;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.JjI;
import com.amazon.alexa.YiP;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class UserInactivityAuthority {
    public static final long BIo = TimeUnit.HOURS.toMillis(1);
    public static final String zZm = "UserInactivityAuthority";
    public int Qle = 0;
    public ScheduledFuture jiA;
    public final AlexaClientEventBus zQM;
    public final ScheduledExecutorService zyO;

    /* loaded from: classes10.dex */
    public static class UserInactivityReportingJob extends JobService {
        public static final String zZm = "UserInactivityReportingJob";
        public JobScheduler BIo;

        public UserInactivityReportingJob() {
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            if (jobParameters.getJobId() == 234613) {
                StringBuilder zZm2 = C0179Pya.zZm("Previously scheduled UserInactivity job stopped: ");
                zZm2.append(jobParameters.getJobId());
                zZm2.toString();
                if (this.BIo == null) {
                    this.BIo = (JobScheduler) getSystemService("jobscheduler");
                }
                this.BIo.cancel(jobParameters.getJobId());
                return false;
            }
            return false;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            return false;
        }

        @VisibleForTesting
        public UserInactivityReportingJob(JobScheduler jobScheduler) {
            this.BIo = jobScheduler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes10.dex */
    public class zZm implements Runnable {
        public zZm() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UserInactivityAuthority.this.jiA();
        }
    }

    @Inject
    public UserInactivityAuthority(AlexaClientEventBus alexaClientEventBus, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.zQM = alexaClientEventBus;
        this.zyO = scheduledExecutorService;
        zyO();
    }

    public Message BIo() {
        return Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.UserInactivityReport.zZm).build(), YiP.zZm(zZm()));
    }

    public synchronized void jiA() {
        Log.i(zZm, "Sending User Inactivity Report");
        this.Qle++;
        this.zQM.zyO(JjI.BIo().zZm(BIo()).zZm());
    }

    public synchronized void zQM() {
        ScheduledFuture scheduledFuture = this.jiA;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.Qle = 0;
        zyO();
    }

    public final synchronized long zZm() {
        return TimeUnit.SECONDS.convert(this.Qle, TimeUnit.HOURS);
    }

    public final void zyO() {
        Log.i(zZm, "scheduleUserInactivityReport");
        ScheduledExecutorService scheduledExecutorService = this.zyO;
        zZm zzm = new zZm();
        long j = BIo;
        this.jiA = scheduledExecutorService.scheduleAtFixedRate(zzm, j, j, TimeUnit.MILLISECONDS);
    }
}
