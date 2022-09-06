package com.amazon.alexa.drive.landing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.EntertainmentCard;
import com.amazon.alexa.drive.landing.LandingPageCardAdapterV2;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SingleIconCard;
import com.amazon.alexa.drivemode.api.SingleIconCardV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes7.dex */
public class LandingPageCardAdapterV2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int COMMS_CARD_PRIORITY_ID = 2;
    private static final int ENTERTAINMENT_CARD_PRIORITY_ID = 0;
    private static final int NAVIGATION_CARD_PRIORITY_ID = 1;
    private static final String TAG = "LandingPageCardAdapterV2";
    private static final int UNKNOWN_CARD_PRIORITY_ID = 4;
    public static final int VIEW_TYPE_ENTERTAINMENT_CARD = 3;
    public static final int VIEW_TYPE_SINGLE_ICON_CARD = 1;
    public static final int VIEW_TYPE_SINGLE_ICON_CARD_V2 = 2;
    public static final int VIEW_TYPE_UNKNOWN_CARD = 0;
    protected Context context;
    private DriveModeThemeManager driveModeThemeManager;
    protected List<DriveModeCard> cards = new ArrayList();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* renamed from: com.amazon.alexa.drive.landing.LandingPageCardAdapterV2$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeCard$CardDomain = new int[DriveModeCard.CardDomain.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeCard$CardDomain[DriveModeCard.CardDomain.ENTERTAINMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeCard$CardDomain[DriveModeCard.CardDomain.NAVIGATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeCard$CardDomain[DriveModeCard.CardDomain.COMMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public class EntertainmentCardViewHolder extends RecyclerView.ViewHolder {
        private ImageView albumArtImage;
        private ImageView nextButton;
        private ImageView playPauseButton;
        private ImageView previousButton;
        private TextView subtitle;
        private TextView title;

        public EntertainmentCardViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.subtitle = (TextView) view.findViewById(R.id.subtitle);
            this.albumArtImage = (ImageView) view.findViewById(R.id.single_icon);
            this.playPauseButton = (ImageView) view.findViewById(R.id.play_pause_button);
            this.previousButton = (ImageView) view.findViewById(R.id.previous_button);
            this.nextButton = (ImageView) view.findViewById(R.id.next_button);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$bindData$3(EntertainmentCard entertainmentCard, View view) {
            entertainmentCard.onCardClicked();
            DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, entertainmentCard.getTitle());
        }

        public void bindData(final EntertainmentCard entertainmentCard) {
            LandingPageCardAdapterV2 landingPageCardAdapterV2 = LandingPageCardAdapterV2.this;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(landingPageCardAdapterV2.context, landingPageCardAdapterV2.driveModeThemeManager.getTheme());
            if (entertainmentCard.getTitle() != null) {
                getTitle().setText(entertainmentCard.getTitle());
            }
            if (entertainmentCard.getSubtitle() != null) {
                getSubtitle().setText(entertainmentCard.getSubtitle());
            }
            if (entertainmentCard.getAlbumArtImage() != null) {
                this.albumArtImage.setImageDrawable(entertainmentCard.getAlbumArtImage());
                this.albumArtImage.setClipToOutline(true);
            }
            if (entertainmentCard.getPlayPauseIcon() != null) {
                this.playPauseButton.setImageDrawable(entertainmentCard.getPlayPauseIcon());
                this.playPauseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$EntertainmentCardViewHolder$SbqSBx-6ov7Hz60NJjX_jDplnww
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EntertainmentCard.this.onPlayPauseButtonPressed();
                    }
                });
            }
            if (entertainmentCard.getPreviousIcon() != null) {
                Drawable previousIcon = entertainmentCard.getPreviousIcon();
                DrawableCompat.setTint(DrawableCompat.wrap(previousIcon), LandingPageCardAdapterV2.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
                this.previousButton.setImageDrawable(previousIcon);
                this.previousButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$EntertainmentCardViewHolder$uyiihXd4S6-BGjj9HoekkhZtKnk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EntertainmentCard.this.onPreviousButtonPressed();
                    }
                });
            }
            if (entertainmentCard.getNextIcon() != null) {
                Drawable nextIcon = entertainmentCard.getNextIcon();
                DrawableCompat.setTint(DrawableCompat.wrap(nextIcon), LandingPageCardAdapterV2.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
                this.nextButton.setImageDrawable(nextIcon);
                this.nextButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$EntertainmentCardViewHolder$LKrye8-rIRAL5CFRKGdE-Oi4Xns
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EntertainmentCard.this.onNextButtonPressed();
                    }
                });
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$EntertainmentCardViewHolder$9m36XjmmyUfjGErH8pF2Bgma6vw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LandingPageCardAdapterV2.EntertainmentCardViewHolder.lambda$bindData$3(EntertainmentCard.this, view);
                }
            });
        }

        public ImageView getAlbumArtImage() {
            return this.albumArtImage;
        }

        public ImageView getNextButton() {
            return this.nextButton;
        }

        public ImageView getPlayPauseButton() {
            return this.playPauseButton;
        }

        public ImageView getPreviousButton() {
            return this.previousButton;
        }

        public TextView getSubtitle() {
            return this.subtitle;
        }

        public TextView getTitle() {
            return this.title;
        }
    }

    public LandingPageCardAdapterV2(Context context, DriveModeThemeManager driveModeThemeManager) {
        this.context = context;
        this.driveModeThemeManager = driveModeThemeManager;
    }

    private int getCardPriority(DriveModeCard driveModeCard) {
        int ordinal = driveModeCard.getCardDomain().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return 2;
            }
            return ordinal != 2 ? 4 : 1;
        }
        return 0;
    }

    public void addCard(DriveModeCard driveModeCard) {
        this.cards.add(driveModeCard);
        Collections.sort(this.cards, new Comparator() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$p8fZTLr-3T2hchGBbwaMZsGFNGQ
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return LandingPageCardAdapterV2.this.lambda$addCard$0$LandingPageCardAdapterV2((DriveModeCard) obj, (DriveModeCard) obj2);
            }
        });
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$Nh3CMiDY3ccUQsHk-t82f_i9SV4
            @Override // java.lang.Runnable
            public final void run() {
                LandingPageCardAdapterV2.this.lambda$addCard$1$LandingPageCardAdapterV2();
            }
        });
    }

    public void clearCards() {
        final int size = this.cards.size();
        this.cards.clear();
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$1qJmfeWFvCOcK9iXOrwEHu5_dlQ
            @Override // java.lang.Runnable
            public final void run() {
                LandingPageCardAdapterV2.this.lambda$clearCards$4$LandingPageCardAdapterV2(size);
            }
        });
    }

    public int getDynamicCardsCount() {
        int size = this.cards.size();
        for (DriveModeCard driveModeCard : this.cards) {
            if (driveModeCard instanceof EntertainmentCard) {
                size--;
            }
        }
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.cards.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        DriveModeCard driveModeCard = this.cards.get(i);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getItemViewType ");
        outline107.append(driveModeCard.getCardType());
        outline107.toString();
        if (driveModeCard instanceof SingleIconCard) {
            return 1;
        }
        if (driveModeCard instanceof SingleIconCardV2) {
            return 2;
        }
        return driveModeCard instanceof EntertainmentCard ? 3 : 0;
    }

    public /* synthetic */ int lambda$addCard$0$LandingPageCardAdapterV2(DriveModeCard driveModeCard, DriveModeCard driveModeCard2) {
        return Integer.compare(getCardPriority(driveModeCard), getCardPriority(driveModeCard2));
    }

    public /* synthetic */ void lambda$addCard$1$LandingPageCardAdapterV2() {
        notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$clearCards$4$LandingPageCardAdapterV2(int i) {
        notifyItemRangeRemoved(0, i);
    }

    public /* synthetic */ void lambda$removeCard$3$LandingPageCardAdapterV2(int i) {
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.cards.size());
    }

    public /* synthetic */ void lambda$updateCard$2$LandingPageCardAdapterV2() {
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            Log.i(TAG, "Binding data to single card view");
            ((SingleCardViewHolder) viewHolder).bindData((SingleIconCard) this.cards.get(i));
        } else if (itemViewType == 2) {
            Log.i(TAG, "Binding data to single card view");
            ((SingleCardViewHolder) viewHolder).bindData((SingleIconCardV2) this.cards.get(i));
        } else if (itemViewType != 3) {
        } else {
            Log.i(TAG, "Binding entertainment card");
            ((EntertainmentCardViewHolder) viewHolder).bindData((EntertainmentCard) this.cards.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        String str = TAG;
        Log.i(str, "onCreateViewHolder " + i);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.driveModeThemeManager.getTheme());
        if (i == 1 || i == 2) {
            return new SingleCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_single_icon_grid, viewGroup, false));
        }
        if (i != 3) {
            return new SingleCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_empty, viewGroup, false));
        }
        return new EntertainmentCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_entertainment_card_landing_page_v2, viewGroup, false));
    }

    public void removeCard(final int i) {
        if (i < this.cards.size() && i >= 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeCard ");
            outline107.append(this.cards.get(i).getCardId());
            outline107.toString();
            this.cards.remove(i);
            this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$i-meFAALZdOSx_dO-a8KtokzMPk
                @Override // java.lang.Runnable
                public final void run() {
                    LandingPageCardAdapterV2.this.lambda$removeCard$3$LandingPageCardAdapterV2(i);
                }
            });
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Card position is invalid");
    }

    public void updateCard(int i, DriveModeCard driveModeCard) {
        if (i < this.cards.size() && i >= 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateCard ");
            outline107.append(this.cards.get(i).getCardId());
            outline107.toString();
            this.cards.set(i, driveModeCard);
            this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$d3O7dHp-EhowaUgrLdY4Eo5lxOs
                @Override // java.lang.Runnable
                public final void run() {
                    LandingPageCardAdapterV2.this.lambda$updateCard$2$LandingPageCardAdapterV2();
                }
            });
            return;
        }
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Invalid card position: ", i, " cards: ");
        outline109.append(this.cards.size());
        Log.w(str, outline109.toString());
    }

    /* loaded from: classes7.dex */
    public class SingleCardViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView subtitle;
        private TextView subtitleAddendum;
        private TextView title;

        public SingleCardViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.icon = (ImageView) view.findViewById(R.id.single_icon);
            this.subtitle = (TextView) view.findViewById(R.id.subtitle);
            this.subtitleAddendum = (TextView) view.findViewById(R.id.subtitle_addendum);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$bindData$0(SingleIconCardV2 singleIconCardV2, View view) {
            singleIconCardV2.onCardClicked();
            DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, singleIconCardV2.getTitle());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$bindData$1(SingleIconCard singleIconCard, View view) {
            singleIconCard.onCardClicked();
            DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, singleIconCard.getTitle());
        }

        public void bindData(final SingleIconCardV2 singleIconCardV2) {
            LandingPageCardAdapterV2 landingPageCardAdapterV2 = LandingPageCardAdapterV2.this;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(landingPageCardAdapterV2.context, landingPageCardAdapterV2.driveModeThemeManager.getTheme());
            if (singleIconCardV2.getTitle() != null) {
                getTitle().setText(singleIconCardV2.getTitle());
            }
            if (singleIconCardV2.getSubtitle() != null) {
                getSubtitle().setText(singleIconCardV2.getSubtitle());
            }
            if (singleIconCardV2.getIcon() != null) {
                Drawable icon = singleIconCardV2.getIcon();
                if (singleIconCardV2.allowIconTinting()) {
                    DrawableCompat.setTint(DrawableCompat.wrap(icon), LandingPageCardAdapterV2.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
                }
                getIcon().setImageDrawable(icon);
            }
            if (singleIconCardV2.getSubtitleAddendum() != null) {
                getSubtitleAddendum().setText(singleIconCardV2.getSubtitleAddendum());
                getSubtitleAddendum().setVisibility(0);
                if (singleIconCardV2.getSubtitleAddendumColor() == null) {
                    getSubtitleAddendum().setTextColor(LandingPageCardAdapterV2.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicNeutral20));
                } else {
                    getSubtitleAddendum().setTextColor(singleIconCardV2.getSubtitleAddendumColor().intValue());
                }
            } else {
                getSubtitleAddendum().setVisibility(8);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$SingleCardViewHolder$XQekAGbtiO5j6FAXsAltPtHydGc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LandingPageCardAdapterV2.SingleCardViewHolder.lambda$bindData$0(SingleIconCardV2.this, view);
                }
            });
        }

        public ImageView getIcon() {
            return this.icon;
        }

        public TextView getSubtitle() {
            return this.subtitle;
        }

        public TextView getSubtitleAddendum() {
            return this.subtitleAddendum;
        }

        public TextView getTitle() {
            return this.title;
        }

        public void bindData(final SingleIconCard singleIconCard) {
            LandingPageCardAdapterV2 landingPageCardAdapterV2 = LandingPageCardAdapterV2.this;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(landingPageCardAdapterV2.context, landingPageCardAdapterV2.driveModeThemeManager.getTheme());
            if (singleIconCard.getTitle() != null) {
                getTitle().setText(singleIconCard.getTitle());
            }
            if (singleIconCard.getDescription() != null) {
                getSubtitle().setText(singleIconCard.getDescription());
            }
            if (singleIconCard.getIcon() != null) {
                Drawable icon = singleIconCard.getIcon();
                if (singleIconCard.allowIconTinting()) {
                    DrawableCompat.setTint(DrawableCompat.wrap(icon), LandingPageCardAdapterV2.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
                }
                getIcon().setImageDrawable(icon);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapterV2$SingleCardViewHolder$G1SgktIJq8MmnKYZAaVn3gJzPGQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LandingPageCardAdapterV2.SingleCardViewHolder.lambda$bindData$1(SingleIconCard.this, view);
                }
            });
        }
    }
}
