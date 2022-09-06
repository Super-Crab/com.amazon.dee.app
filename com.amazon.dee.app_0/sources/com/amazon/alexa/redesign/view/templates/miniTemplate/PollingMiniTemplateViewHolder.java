package com.amazon.alexa.redesign.view.templates.miniTemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.poller.Poller;
import com.amazon.alexa.redesign.poller.PollingManager;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.HomeAdapter;
/* loaded from: classes10.dex */
public class PollingMiniTemplateViewHolder extends MiniTemplateViewHolder {
    private ActionFactory actionFactory;
    private HomeAdapter homeAdapter;
    private HomeFeedServiceClient homeFeedServiceClient;
    private HomeContract.OEInteractor oeInteractor;
    private Poller poller;
    private PollingManager pollingManager;

    public PollingMiniTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter) {
        super(layoutInflater, viewGroup, context, homeMetricsRecorder, viewTypeFactory, presenter);
    }

    private void initPoller(MiniCardTemplateModel miniCardTemplateModel, int i) {
        if (this.pollingManager == null || this.poller == null) {
            this.pollingManager = new PollingManager();
            this.poller = new Poller(this.pollingManager, miniCardTemplateModel.getContentMetadata().getRefreshData(), i);
            this.pollingManager.init(this.poller, this.homeFeedServiceClient, this.homeAdapter, this.actionFactory, this.oeInteractor);
        }
        this.poller.startPolling();
    }

    private boolean userHasAccessToPolling() {
        return ((FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get()).hasAccess("CH_HOME_CONTENT_REFRESH_ANDROID", false);
    }

    @Override // com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder, com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
        stopPolling();
    }

    public void stopPolling() {
        Poller poller = this.poller;
        if (poller != null) {
            poller.stopPolling();
        }
    }

    public PollingMiniTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter, HomeAdapter homeAdapter, ActionFactory actionFactory, HomeFeedServiceClient homeFeedServiceClient, HomeContract.OEInteractor oEInteractor) {
        super(layoutInflater, viewGroup, context, homeMetricsRecorder, viewTypeFactory, presenter);
        this.homeAdapter = homeAdapter;
        this.actionFactory = actionFactory;
        this.homeFeedServiceClient = homeFeedServiceClient;
        this.oeInteractor = oEInteractor;
    }

    @Override // com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder, com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(MiniTemplateViewItem miniTemplateViewItem, int i, boolean z) {
        super.bind(miniTemplateViewItem, i, z);
        if (miniTemplateViewItem.mo2390getModel() != null && miniTemplateViewItem.mo2390getModel().canPoll() && userHasAccessToPolling()) {
            initPoller(miniTemplateViewItem.mo2390getModel(), i);
            return;
        }
        Poller poller = this.poller;
        if (poller == null) {
            return;
        }
        poller.stopPolling();
    }
}
