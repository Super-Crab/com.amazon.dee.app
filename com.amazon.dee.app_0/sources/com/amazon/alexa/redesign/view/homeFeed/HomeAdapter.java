package com.amazon.alexa.redesign.view.homeFeed;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.entity.templates.CarouselGridTemplateModel;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.EmptyStateTemplateModel;
import com.amazon.alexa.redesign.entity.templates.HeroCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.ListTemplateModel;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.SkillsSingleTemplateModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.entity.viewtypes.AlarmsTimersModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.poller.PollingManager;
import com.amazon.alexa.redesign.utils.DiffUtilCallback;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import com.amazon.alexa.redesign.view.templates.carouselChipTemplate.CarouselChipTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.carouselChipTemplate.CarouselChipTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.carouselGridTemplate.CarouselGridTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.carouselGridTemplate.CarouselGridTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.DomainCardTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.DomainCardTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.emptyStateTemplate.EmptyStateTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.emptyStateTemplate.EmptyStateTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.heroTemplate.HeroTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.heroTemplate.HeroTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.listTemplate.ListTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.listTemplate.ListTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.miniTemplate.PollingMiniTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.skillsSingleTemplate.SkillsSingleTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.skillsSingleTemplate.SkillsSingleTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewItem;
import com.amazon.alexa.redesign.view.viewtypes.AlarmsTimersView;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.registry.ViewProviderRegistry;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class HomeAdapter extends RecyclerView.Adapter<BaseCardViewHolder> implements PollingManager.ViewApi {
    private ActionFactory actionFactory;
    private final BridgeStatusService bridgeStatusService;
    private Context context;
    private final EventCapture eventCapture;
    private HomeFeedServiceClient homeFeedServiceClient;
    private final HomeMetricsRecorder metricsRecorder;
    private HomeContract.OEInteractor oeInteractor;
    @VisibleForTesting
    BaseCardViewHolder pollingViewHolder;
    private final HomeContract.Presenter presenter;
    private final ReactInstanceManager reactInstanceManager;
    private final ViewProviderRegistry viewProviderRegistry;
    private final ViewTypeFactory viewTypeFactory;
    private List<RecyclerViewItem> recyclerViewItems = new ArrayList();
    @VisibleForTesting
    Set<BaseCardViewHolder> carouselViewHolders = new HashSet();

    /* renamed from: com.amazon.alexa.redesign.view.homeFeed.HomeAdapter$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType = new int[TemplateType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.VoxIngress.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.MiniTemplate.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.PollingMiniTemplate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.HeroTemplate.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.ListTemplate.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.CustomTemplate.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.EmptyState.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.SkillsSingle.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.CarouselChipTemplate.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$templates$TemplateType[TemplateType.CarouselGridTemplate.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public HomeAdapter(Context context, HomeContract.Presenter presenter, ReactInstanceManager reactInstanceManager, BridgeStatusService bridgeStatusService, ViewProviderRegistry viewProviderRegistry, HomeMetricsRecorder homeMetricsRecorder, EventCapture eventCapture, HomeContract.OEInteractor oEInteractor, ActionFactory actionFactory, HomeFeedServiceClient homeFeedServiceClient) {
        this.context = context;
        this.presenter = presenter;
        this.reactInstanceManager = reactInstanceManager;
        this.bridgeStatusService = bridgeStatusService;
        this.viewProviderRegistry = viewProviderRegistry;
        this.metricsRecorder = homeMetricsRecorder;
        this.eventCapture = eventCapture;
        this.viewTypeFactory = new ViewTypeFactory(context, LayoutInflater.from(context), homeMetricsRecorder);
        this.oeInteractor = oEInteractor;
        this.actionFactory = actionFactory;
        this.homeFeedServiceClient = homeFeedServiceClient;
    }

    private void destroyHomeReactNativeCard(ReactRootView reactRootView) {
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
        }
    }

    private boolean getTwoDiscCards(int i) {
        if (i < 1 || i >= this.recyclerViewItems.size()) {
            return false;
        }
        return itemIsDiscoveryCard(this.recyclerViewItems.get(i)) && itemIsDiscoveryCard(this.recyclerViewItems.get(i - 1));
    }

    private boolean itemIsDiscoveryCard(RecyclerViewItem recyclerViewItem) {
        return (recyclerViewItem instanceof ListTemplateViewItem) || (recyclerViewItem instanceof HeroTemplateViewItem);
    }

    private void renderHomeReactNativeCard(ReactRootView reactRootView, Bundle bundle, ReactInstanceManager reactInstanceManager, String str) {
        reactRootView.startReactApplication(reactInstanceManager, "HomeCard", bundle);
        this.presenter.onReactCardRenderRequest(str);
    }

    @Nullable
    public Pair<Integer, CardModel> findDataByDismissId(String str) {
        int i = 0;
        while (true) {
            CardModel cardModel = null;
            if (i < this.recyclerViewItems.size()) {
                if (this.recyclerViewItems.get(i) != null) {
                    cardModel = this.recyclerViewItems.get(i).mo2390getModel();
                }
                if (cardModel != null && cardModel.getDismissCardId().equals(str)) {
                    return Pair.create(Integer.valueOf(i), cardModel);
                }
                i++;
            } else {
                return null;
            }
        }
    }

    public void forceReplaceViewItem(int i, RecyclerViewItem recyclerViewItem) {
        if (i <= -1 || i >= this.recyclerViewItems.size()) {
            return;
        }
        this.recyclerViewItems.remove(i);
        this.recyclerViewItems.add(i, recyclerViewItem);
        notifyItemChanged(i);
    }

    public void forceUpdateViewItem(int i) {
        this.recyclerViewItems.remove(i);
        this.recyclerViewItems.add(i, this.recyclerViewItems.get(i));
        notifyItemChanged(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.recyclerViewItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i < 0 || i >= this.recyclerViewItems.size()) {
            return -1;
        }
        return this.recyclerViewItems.get(i).getTemplateType().getTemplateTypeIntValue();
    }

    public int getPositionFromCardId(String str) {
        for (int i = 0; i < this.recyclerViewItems.size(); i++) {
            String cardId = this.recyclerViewItems.get(i).getCardId();
            if (cardId != null && cardId.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public RecyclerViewItem getViewItem(int i) {
        if (i <= -1 || i >= this.recyclerViewItems.size()) {
            return null;
        }
        return this.recyclerViewItems.get(i);
    }

    public /* synthetic */ void lambda$updateViewOnTick$0$HomeAdapter(List list, int i) {
        if (list.size() > 0) {
            forceReplaceViewItem(i, new MiniTemplateViewItem((MiniCardTemplateModel) list.get(0)));
        } else {
            removeCardAt(i);
        }
    }

    public void onCancelTimerAction(int i) {
        RecyclerViewItem recyclerViewItem;
        CardModel mo2390getModel;
        if (i < 0 || i >= this.recyclerViewItems.size() || (recyclerViewItem = this.recyclerViewItems.get(i)) == null || (mo2390getModel = recyclerViewItem.mo2390getModel()) == null) {
            return;
        }
        List<ViewTypeModel> childViewModels = mo2390getModel.getChildViewModels();
        if (childViewModels != null && childViewModels.size() > 0) {
            ViewTypeModel viewTypeModel = childViewModels.get(0);
            if (viewTypeModel instanceof AlarmsTimersModel) {
                ((AlarmsTimersModel) viewTypeModel).setState(AlarmsTimersView.STATE_CANCELED);
            }
        }
        forceUpdateViewItem(i);
    }

    public void onDetach() {
    }

    public void recordViewsForVisibleCarouselItems() {
        for (BaseCardViewHolder baseCardViewHolder : this.carouselViewHolders) {
            if (baseCardViewHolder.getLayoutPosition() > -1) {
                if (baseCardViewHolder instanceof CarouselChipTemplateViewHolder) {
                    ((CarouselChipTemplateViewHolder) baseCardViewHolder).recordViewsForVisibleCarouselItems();
                } else if (baseCardViewHolder instanceof CarouselGridTemplateViewHolder) {
                    ((CarouselGridTemplateViewHolder) baseCardViewHolder).recordViewsForVisibleCarouselItems();
                }
            }
        }
    }

    public void removeCardAt(int i) {
        if (i <= -1 || i >= this.recyclerViewItems.size()) {
            return;
        }
        BaseCardViewHolder baseCardViewHolder = this.pollingViewHolder;
        if ((baseCardViewHolder instanceof PollingMiniTemplateViewHolder) && baseCardViewHolder.getAdapterPosition() == i && this.recyclerViewItems.get(i) != null && this.recyclerViewItems.get(i).mo2390getModel() != null && this.recyclerViewItems.get(i).mo2390getModel().canPoll()) {
            ((PollingMiniTemplateViewHolder) this.pollingViewHolder).stopPolling();
        }
        if (this.recyclerViewItems.size() == 2) {
            forceReplaceViewItem(1, new EmptyStateTemplateViewItem(new EmptyStateTemplateModel()));
            return;
        }
        this.recyclerViewItems.remove(i);
        notifyItemRemoved(i);
    }

    public void updateCardModels(@NonNull List<CardModel> list) {
        BaseCardViewHolder baseCardViewHolder;
        ArrayList arrayList = new ArrayList();
        AlertBannerModel alertBannerModel = null;
        VoxIngressTemplateModel voxIngressTemplateModel = null;
        boolean z = false;
        for (CardModel cardModel : list) {
            if (cardModel instanceof AlertBannerModel) {
                alertBannerModel = (AlertBannerModel) cardModel;
            } else if (cardModel instanceof VoxIngressTemplateModel) {
                voxIngressTemplateModel = (VoxIngressTemplateModel) cardModel;
            } else if (cardModel instanceof MiniCardTemplateModel) {
                arrayList.add(new MiniTemplateViewItem((MiniCardTemplateModel) cardModel));
                if (cardModel.canPoll()) {
                    z = true;
                }
            } else if (cardModel instanceof HeroCardTemplateModel) {
                arrayList.add(new HeroTemplateViewItem((HeroCardTemplateModel) cardModel));
            } else if (cardModel instanceof DomainCardTemplateModel) {
                arrayList.add(new DomainCardTemplateViewItem((DomainCardTemplateModel) cardModel));
            } else if (cardModel instanceof ListTemplateModel) {
                arrayList.add(new ListTemplateViewItem((ListTemplateModel) cardModel));
            } else if (cardModel instanceof EmptyStateTemplateModel) {
                arrayList.add(new EmptyStateTemplateViewItem((EmptyStateTemplateModel) cardModel));
            } else if (cardModel instanceof SkillsSingleTemplateModel) {
                arrayList.add(new SkillsSingleTemplateViewItem((SkillsSingleTemplateModel) cardModel));
            } else if (cardModel instanceof CarouselChipTemplateModel) {
                arrayList.add(new CarouselChipTemplateViewItem((CarouselChipTemplateModel) cardModel));
            } else if (cardModel instanceof CarouselGridTemplateModel) {
                arrayList.add(new CarouselGridTemplateViewItem((CarouselGridTemplateModel) cardModel));
            }
        }
        if (!z && (baseCardViewHolder = this.pollingViewHolder) != null && (baseCardViewHolder instanceof PollingMiniTemplateViewHolder)) {
            ((PollingMiniTemplateViewHolder) baseCardViewHolder).stopPolling();
        }
        if (voxIngressTemplateModel != null) {
            arrayList.add(0, new VoxIngressTemplateViewItem(voxIngressTemplateModel, alertBannerModel));
            if (arrayList.size() == 1) {
                arrayList.add(new EmptyStateTemplateViewItem(new EmptyStateTemplateModel()));
            }
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DiffUtilCallback(this.recyclerViewItems, arrayList), true);
            if (!this.recyclerViewItems.isEmpty()) {
                this.recyclerViewItems.clear();
            }
            this.recyclerViewItems.addAll(arrayList);
            calculateDiff.dispatchUpdatesTo(this);
            this.presenter.setOutageAlert(alertBannerModel);
        }
    }

    @Override // com.amazon.alexa.redesign.poller.PollingManager.ViewApi
    public void updateViewOnTick(final List<CardModel> list, final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.homeFeed.-$$Lambda$HomeAdapter$FBbz8iQhqxVXkRPPi12oJMnq1IY
            @Override // java.lang.Runnable
            public final void run() {
                HomeAdapter.this.lambda$updateViewOnTick$0$HomeAdapter(list, i);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseCardViewHolder baseCardViewHolder, int i) {
        baseCardViewHolder.bind(this.recyclerViewItems.get(i), i, getTwoDiscCards(i));
        if (baseCardViewHolder instanceof PollingMiniTemplateViewHolder) {
            this.pollingViewHolder = baseCardViewHolder;
        }
        if ((baseCardViewHolder instanceof CarouselChipTemplateViewHolder) || (baseCardViewHolder instanceof CarouselGridTemplateViewHolder)) {
            this.carouselViewHolders.add(baseCardViewHolder);
        }
        if (this.recyclerViewItems.get(i).mo2390getModel() != null) {
            this.presenter.onBoundCardView(this.recyclerViewItems.get(i).mo2390getModel().getViewUpdateType());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BaseCardViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        BaseCardViewHolder voxIngressTemplateViewHolder;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        switch (TemplateType.from(i).ordinal()) {
            case 1:
                voxIngressTemplateViewHolder = new VoxIngressTemplateViewHolder(from, viewGroup, this.context, this.presenter, this.metricsRecorder);
                break;
            case 2:
                voxIngressTemplateViewHolder = new MiniTemplateViewHolder(from, viewGroup, this.context, this.metricsRecorder, this.viewTypeFactory, this.presenter);
                break;
            case 3:
                Context context = this.context;
                voxIngressTemplateViewHolder = new HeroTemplateViewHolder(from, viewGroup, context, this.metricsRecorder, this.viewTypeFactory, this.presenter, new AnimationPlayer(context));
                break;
            case 4:
                Context context2 = this.context;
                voxIngressTemplateViewHolder = new ListTemplateViewHolder(from, viewGroup, context2, this.metricsRecorder, this.viewTypeFactory, this.presenter, new AnimationPlayer(context2));
                break;
            case 5:
                voxIngressTemplateViewHolder = new DomainCardTemplateViewHolder(from, viewGroup, this.viewProviderRegistry, this.eventCapture, this.presenter, new AnimationPlayer(this.context), this.context);
                break;
            case 6:
                voxIngressTemplateViewHolder = new EmptyStateTemplateViewHolder(from, viewGroup, this.context, this.presenter);
                break;
            case 7:
                Context context3 = this.context;
                voxIngressTemplateViewHolder = new SkillsSingleTemplateViewHolder(from, viewGroup, context3, this.metricsRecorder, this.viewTypeFactory, this.presenter, new AnimationPlayer(context3));
                break;
            case 8:
                voxIngressTemplateViewHolder = new PollingMiniTemplateViewHolder(from, viewGroup, this.context, this.metricsRecorder, this.viewTypeFactory, this.presenter, this, this.actionFactory, this.homeFeedServiceClient, this.oeInteractor);
                break;
            case 9:
                Context context4 = this.context;
                voxIngressTemplateViewHolder = new CarouselChipTemplateViewHolder(from, viewGroup, context4, this.metricsRecorder, this.viewTypeFactory, this.presenter, new AnimationPlayer(context4));
                break;
            case 10:
                Context context5 = this.context;
                voxIngressTemplateViewHolder = new CarouselGridTemplateViewHolder(from, viewGroup, context5, this.metricsRecorder, this.viewTypeFactory, this.presenter, new AnimationPlayer(context5));
                break;
            default:
                this.oeInteractor.recordMalformedDataOccurrence(HomeOEInteractor.UNKNOWN_TEMPLATE_TYPE);
                return null;
        }
        this.presenter.onCreatedCardView();
        return voxIngressTemplateViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(BaseCardViewHolder baseCardViewHolder) {
        super.onViewDetachedFromWindow((HomeAdapter) baseCardViewHolder);
        if (baseCardViewHolder instanceof VoxIngressTemplateViewHolder) {
            baseCardViewHolder.detach();
        }
    }
}
