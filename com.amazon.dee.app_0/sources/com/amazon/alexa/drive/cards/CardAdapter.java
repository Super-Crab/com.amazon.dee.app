package com.amazon.alexa.drive.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Card> mCards = new ArrayList();
    protected Context mContext;
    protected DriveModeThemeManager mDriveModeThemeManager;
    protected OnCardClickListenerInterface mOnCardClickListener;
    protected WeblabManager mWeblabManager;

    /* loaded from: classes7.dex */
    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView icon;
        private OnCardClickListenerInterface onCardClickListener;
        private final View splitter;
        private TextView title;

        public CardViewHolder(@NonNull View view, OnCardClickListenerInterface onCardClickListenerInterface) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.splitter = view.findViewById(R.id.dm_nav_card_splitter);
            this.icon = (ImageView) view.findViewById(R.id.single_icon);
            this.onCardClickListener = onCardClickListenerInterface;
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OnCardClickListenerInterface onCardClickListenerInterface = this.onCardClickListener;
            if (onCardClickListenerInterface != null) {
                onCardClickListenerInterface.onCardClick(CardAdapter.this.mCards.get(getAdapterPosition()));
            }
        }
    }

    public CardAdapter(Context context, OnCardClickListenerInterface onCardClickListenerInterface, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        this.mOnCardClickListener = onCardClickListenerInterface;
        this.mContext = context;
        this.mDriveModeThemeManager = driveModeThemeManager;
        this.mWeblabManager = weblabManager;
    }

    public List<Card> getCards() {
        return this.mCards;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mCards.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
        Card card = this.mCards.get(i);
        cardViewHolder.title.setText(card.getTitle());
        if (i == this.mCards.size() - 1 && cardViewHolder.splitter != null) {
            cardViewHolder.splitter.setVisibility(8);
        }
        if (cardViewHolder.icon != null) {
            cardViewHolder.icon.setImageResource(card.getIcon());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        if (this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
            inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_single_icon_row, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_traffic, viewGroup, false);
        }
        return new CardViewHolder(inflate, this.mOnCardClickListener);
    }

    public void setCards(List<Card> list) {
        this.mCards = new ArrayList(list);
        notifyDataSetChanged();
    }
}
