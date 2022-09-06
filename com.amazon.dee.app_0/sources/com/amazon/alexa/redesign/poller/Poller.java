package com.amazon.alexa.redesign.poller;

import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.ContentMetadata;
import com.amazon.alexa.redesign.entity.RefreshData;
import com.amazon.alexa.redesign.poller.PollingManager;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes10.dex */
public class Poller implements PollingManager.PollerApi {
    PollingManager pollingManager;
    TimerTask pollingTask;
    int position;
    RefreshData refreshData;
    Timer timer = new Timer();

    public Poller(PollingManager pollingManager, RefreshData refreshData, int i) {
        this.pollingManager = pollingManager;
        this.refreshData = refreshData;
        this.position = i;
    }

    @Override // com.amazon.alexa.redesign.poller.PollingManager.PollerApi
    public void startPolling() {
        if (this.pollingTask == null) {
            this.pollingTask = new TimerTask() { // from class: com.amazon.alexa.redesign.poller.Poller.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Poller poller = Poller.this;
                    poller.pollingManager.onTick(poller.refreshData.buildRefreshDataUrl(), Poller.this.position);
                }
            };
            this.timer.schedule(this.pollingTask, 0L, this.refreshData.getIntervalInMs());
        }
    }

    public void stopPolling() {
        TimerTask timerTask = this.pollingTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.pollingTask = null;
        }
    }

    @Override // com.amazon.alexa.redesign.poller.PollingManager.PollerApi
    public void updatePollingData(List<CardModel> list) {
        if (list.size() > 0) {
            CardModel cardModel = list.get(0);
            ContentMetadata contentMetadata = cardModel.getContentMetadata();
            RefreshData refreshData = contentMetadata == null ? null : contentMetadata.getRefreshData();
            RefreshData refreshData2 = this.refreshData;
            if (refreshData2 != null && refreshData2.isDifferent(refreshData)) {
                stopPolling();
                this.refreshData = refreshData;
            }
            if (!cardModel.canPoll()) {
                return;
            }
            startPolling();
            return;
        }
        stopPolling();
    }
}
