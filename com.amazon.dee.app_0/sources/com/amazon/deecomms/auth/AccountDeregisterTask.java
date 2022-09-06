package com.amazon.deecomms.auth;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.PeriodicConfigSyncUtils;
/* loaded from: classes12.dex */
public class AccountDeregisterTask extends AsyncTask<Void, Void, Boolean> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AccountDeregisterTask.class);
    private Context mContext;
    private CounterMetric mDeregistrationError = new CounterMetric(CommsMetric.MetricType.Operational, MetricKeys.DEREGISTRATION_FAILURE_COUNT);
    private CounterMetric mDeregistrationSuccess = new CounterMetric(CommsMetric.MetricType.Operational, MetricKeys.DEREGISTRATION_SUCCESS_COUNT);
    private ProgressDialog mProgressDialog;
    private boolean mShowDialogs;

    public AccountDeregisterTask(Context context, boolean z) {
        this.mContext = context;
        this.mShowDialogs = z;
    }

    private void dismissCurrentDialog() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    private ProgressDialog newProgressDialog(Context context, String str) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(str);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    private void showDialog(ProgressDialog progressDialog) {
        dismissCurrentDialog();
        progressDialog.show();
        this.mProgressDialog = progressDialog;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.mShowDialogs) {
            Context context = this.mContext;
            ProgressDialog newProgressDialog = newProgressDialog(context, context.getResources().getString(R.string.deregister_progress_dialog_message));
            dismissCurrentDialog();
            newProgressDialog.show();
            this.mProgressDialog = newProgressDialog;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @SuppressLint({"CommitPrefEdits"})
    public Boolean doInBackground(Void... voidArr) {
        boolean deregisterAccount = Utils.deregisterAccount();
        if (deregisterAccount) {
            Utils.clearCommsInfo(true);
        }
        return Boolean.valueOf(deregisterAccount);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        super.onPostExecute((AccountDeregisterTask) bool);
        dismissCurrentDialog();
        boolean booleanValue = bool.booleanValue();
        Double valueOf = Double.valueOf(1.0d);
        if (!booleanValue) {
            MetricsHelper.recordCounterMetric(this.mDeregistrationError, valueOf);
            if (!this.mShowDialogs) {
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_LOGOUT, AlertSource.newClassSource(AccountDeregisterTask.class.getName()));
            AlertDialog create = new AlertDialog.Builder(this.mContext).create();
            create.setTitle(R.string.deregister_failure_dialog_title);
            create.setMessage(this.mContext.getResources().getString(R.string.deregister_failure_dialog_message));
            create.setButton(-3, this.mContext.getResources().getString(R.string.dialog_ok_button), $$Lambda$AccountDeregisterTask$4zSmaJbZK9NPcwNioMDXKPze10.INSTANCE);
            create.show();
            return;
        }
        MetricsHelper.recordCounterMetric(this.mDeregistrationSuccess, valueOf);
        LOG.i("Sending broadcast after de-registering account successfully");
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.ACCOUNT_DEREGISTER_ACTION));
        LOG.i("Cancelling all the existing alarms");
        PeriodicConfigSyncUtils.cancelPeriodicConfigSyncing(this.mContext);
        CommsDaggerWrapper.getComponent().getApplicationManager().commsLogout();
    }
}
