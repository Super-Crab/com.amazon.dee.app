package com.amazon.alexa.biloba.view.tips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.TipsPageBinding;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.common.recycler.RecyclerViewAdapter;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes6.dex */
public class TipsView extends BilobaViewController {
    private static final String TAG = "TipsView";
    private RecyclerViewAdapter adapter;
    private Context context;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private RecyclerView tipsRecyclerView;
    private TipsViewModel viewModel;

    public TipsView(Context context) {
        LogUtils.d(TAG, "Routed to TipsView");
        this.viewModel = new TipsViewModel();
        this.context = context;
    }

    public void updateTips(List<BaseRecyclerItem> list) {
        this.adapter = new RecyclerViewAdapter();
        this.tipsRecyclerView.setAdapter(this.adapter);
        this.adapter.updateItems(list);
        this.adapter.notifyDataSetChanged();
        recordCardsViewMetric(list, "TipsView");
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.tips);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        TipsPageBinding tipsPageBinding = (TipsPageBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.tips_page, viewGroup, false);
        tipsPageBinding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        tipsPageBinding.setViewmodel(this.viewModel);
        View root = tipsPageBinding.getRoot();
        this.tipsRecyclerView = (RecyclerView) root.findViewById(R.id.tips_page);
        RecyclerView recyclerView = this.tipsRecyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.tipsRecyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
            this.tipsRecyclerView.setAdapter(this.adapter);
        }
        registerViewAttributes((LinearLayout) root.findViewById(R.id.no_connection_banner), this.viewModel);
        return root;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric("TipsView", "");
        subscribeToNetworkChange();
        this.viewModel.getLiveDataCards().observe((LifecycleOwner) this.context, new $$Lambda$TipsView$uoQ0KJjl3pccnlP74RmaNimJaaQ(this));
        this.viewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.viewModel.getLiveDataCards().removeObserver(new $$Lambda$TipsView$uoQ0KJjl3pccnlP74RmaNimJaaQ(this));
    }
}
