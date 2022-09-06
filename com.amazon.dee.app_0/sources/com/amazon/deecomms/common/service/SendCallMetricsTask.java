package com.amazon.deecomms.common.service;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.model.CallQualityMetricsModel;
import com.amazon.deecomms.calling.model.CallingMetricsModel;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.CaptureCallQualityMetrics;
import com.amazon.deecomms.common.network.acmsrecipes.CaptureCallingMetrics;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class SendCallMetricsTask extends AsyncTask<Void, Void, Void> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SendCallMetricsTask.class);
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private final CallQualityMetricsModel mCallQualityMetrics;
    private final CallingMetricsModel mCallingMetrics;

    public SendCallMetricsTask(@NonNull String str, @Nullable String str2) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mCallingMetrics = this.callHistoryHelper.getCallingMetrics(str);
        if (this.mCallingMetrics != null && !Utils.isNullOrEmpty(str2)) {
            this.mCallingMetrics.setCallRating(str2);
        }
        this.mCallQualityMetrics = this.callHistoryHelper.getCallQualityMetrics(str);
    }

    private void sendCallQualityMetrics() throws ServiceException {
        String execute = new CaptureCallQualityMetrics().execute(this.commsIdentityManager.getCommsId("sendCallQualityMetrics", false), this.mCallQualityMetrics);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Successfully sent call quality metrics for call id ");
        outline1.append(this.mCallQualityMetrics.getCallId());
        outline1.append(", response: ");
        outline1.append(execute);
        commsLogger.d(outline1.toString());
    }

    private void sendCallingMetrics() throws ServiceException {
        String execute = new CaptureCallingMetrics().execute(this.commsIdentityManager.getCommsId("sendCallingMetrics", false), this.mCallingMetrics);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Successfully sent calling metrics for call id ");
        outline1.append(this.mCallingMetrics.getCallId());
        outline1.append(", response: ");
        outline1.append(execute);
        commsLogger.d(outline1.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        try {
            if (this.mCallingMetrics == null) {
                LOG.i("No calling metrics available");
            } else {
                sendCallingMetrics();
            }
        } catch (ServiceException e) {
            LOG.e("Error sending calling metrics", e);
        }
        try {
            if (this.mCallQualityMetrics == null) {
                LOG.i("No call quality metrics available");
                return null;
            }
            sendCallQualityMetrics();
            return null;
        } catch (ServiceException e2) {
            LOG.e("Error sending call quality metrics", e2);
            return null;
        }
    }
}
