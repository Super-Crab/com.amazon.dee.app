package com.amazon.alexa.drive.landing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.landing.LandingPageCardAdapter;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.util.DriveModeAnimationUtils;
import com.amazon.alexa.drivemode.api.DoubleIconsCard;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SecureCard;
import com.amazon.alexa.drivemode.api.SingleIconCard;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes7.dex */
public class LandingPageCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int COMMS_CARD_PRIORITY_ID = 2;
    private static final int ENTERTAINMENT_CARD_PRIORITY_ID = 0;
    private static final int LOCK_DEVICE_LANDING_PAGE_CARD_ID = 4098;
    private static final int NAVIGATION_CARD_PRIORITY_ID = 1;
    private static final int SMART_HOME_CARD_PRIORITY_ID = 3;
    private static final String TAG = "LandingPageCardAdapter";
    private static final int UNKNOWN_CARD_PRIORITY_ID = 4;
    public static final int VIEW_TYPE_DOUBLE_ICONS_CARD = 2;
    public static final int VIEW_TYPE_SECURE_CARD = 3;
    public static final int VIEW_TYPE_SINGLE_ICON_CARD = 1;
    public static final int VIEW_TYPE_UNKNOWN_CARD = 0;
    protected Context context;
    private DriveModeThemeManager driveModeThemeManager;
    protected List<DriveModeCard> cards = new ArrayList();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* renamed from: com.amazon.alexa.drive.landing.LandingPageCardAdapter$2  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass2 {
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
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeCard$CardDomain[DriveModeCard.CardDomain.SMART_HOME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public class ButtonCardViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private final int buttonPadding;
        private final TextView descriptionTextView;
        private final ImageView icon;
        private final TextView titleTextView;

        public ButtonCardViewHolder(@NonNull View view) {
            super(view);
            this.buttonPadding = 10;
            this.button = (Button) view.findViewById(R.id.right_button);
            this.icon = (ImageView) view.findViewById(R.id.left_icon);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
            this.descriptionTextView = (TextView) view.findViewById(R.id.description);
        }

        public void bindData(final SecureCard secureCard) {
            if (!TextUtils.isEmpty(secureCard.getButtonText())) {
                this.button.setVisibility(0);
                this.button.setText(secureCard.getButtonText());
                this.button.setBackgroundResource(secureCard.isSecure() ? secureCard.getSecuredButtonBackground() : secureCard.getUnsecuredButtonBackground());
                this.button.setTypeface(FontResolver.getFont(LandingPageCardAdapter.this.context, Font.AMAZON_EMBER_BOLD));
                Button button = this.button;
                LandingPageCardAdapter landingPageCardAdapter = LandingPageCardAdapter.this;
                int convertDPToPixels = landingPageCardAdapter.convertDPToPixels(landingPageCardAdapter.context, 10.0f);
                LandingPageCardAdapter landingPageCardAdapter2 = LandingPageCardAdapter.this;
                button.setPadding(convertDPToPixels, 0, landingPageCardAdapter2.convertDPToPixels(landingPageCardAdapter2.context, 10.0f), 0);
                if (secureCard.getCardId() == 4098) {
                    String str = secureCard.isSecure() ? JoinPoint.SYNCHRONIZATION_LOCK : JoinPoint.SYNCHRONIZATION_UNLOCK;
                    if (secureCard.getLastUserAction() != null && !secureCard.getLastUserAction().equals(str)) {
                        DriveModeAnimationUtils.loadingPulseAnimation(this.button);
                    }
                }
            } else {
                this.button.setVisibility(8);
            }
            String description = secureCard.getDescription();
            if (!TextUtils.isEmpty(description)) {
                this.descriptionTextView.setVisibility(0);
                this.descriptionTextView.setText(description);
            } else {
                this.descriptionTextView.setVisibility(8);
            }
            this.titleTextView.setText(secureCard.getTitle());
            Drawable icon = secureCard.getIcon();
            LandingPageCardAdapter landingPageCardAdapter3 = LandingPageCardAdapter.this;
            DrawableCompat.setTint(DrawableCompat.wrap(icon), LandingPageCardAdapter.this.driveModeThemeManager.getColorFromAttribute(new ContextThemeWrapper(landingPageCardAdapter3.context, landingPageCardAdapter3.driveModeThemeManager.getTheme()), R.attr.mosaicIcon110));
            this.icon.setImageDrawable(icon);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$ButtonCardViewHolder$LtfEcSCZsHyLdRpapRwwmaVaYN8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SecureCard.this.onCardClicked();
                }
            });
        }

        public Button getButton() {
            return this.button;
        }

        public TextView getDescription() {
            return this.descriptionTextView;
        }

        public ImageView getIcon() {
            return this.icon;
        }

        public TextView getTitle() {
            return this.titleTextView;
        }

        public void setButton(Button button) {
            this.button = button;
        }
    }

    /* loaded from: classes7.dex */
    public class DoubleCardViewHolder extends RecyclerView.ViewHolder {
        private final View hintContent;
        private final TextView hintText;
        private ImageView leftIcon;
        private ImageView rightIcon;
        private TextView subtitle;
        private TextView subtitleAddendum;
        private TextView title;

        public DoubleCardViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.subtitle = (TextView) view.findViewById(R.id.subtitle);
            this.subtitleAddendum = (TextView) view.findViewById(R.id.subtitle_addendum);
            this.leftIcon = (ImageView) view.findViewById(R.id.left_icon);
            this.rightIcon = (ImageView) view.findViewById(R.id.right_icon);
            this.hintText = (TextView) view.findViewById(R.id.hint_text);
            this.hintContent = view.findViewById(R.id.hint_content);
        }

        public void bindData(final DoubleIconsCard doubleIconsCard) {
            LandingPageCardAdapter landingPageCardAdapter = LandingPageCardAdapter.this;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(landingPageCardAdapter.context, landingPageCardAdapter.driveModeThemeManager.getTheme());
            if (doubleIconsCard.getTitle() != null) {
                getTitle().setText(doubleIconsCard.getTitle());
            }
            if (doubleIconsCard.getSubtitle() != null) {
                getSubtitle().setText(doubleIconsCard.getSubtitle());
            }
            if (doubleIconsCard.getSubtitleColor() == null) {
                getSubtitle().setTextColor(LandingPageCardAdapter.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicNeutral20));
            } else {
                getSubtitle().setTextColor(doubleIconsCard.getSubtitleColor().intValue());
            }
            Drawable leftIcon = doubleIconsCard.getLeftIcon();
            if (doubleIconsCard.allowIconTinting()) {
                DrawableCompat.setTint(DrawableCompat.wrap(leftIcon), LandingPageCardAdapter.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
            }
            getLeftIcon().setImageDrawable(leftIcon);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$DoubleCardViewHolder$xokSld5K9ANXFLWJ7X_X91yh5NI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DoubleIconsCard.this.onCardClicked();
                }
            });
            if (doubleIconsCard.getRightIcon() != null) {
                getRightIcon().setVisibility(0);
                getRightIcon().setImageDrawable(doubleIconsCard.getRightIcon());
                getRightIcon().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.LandingPageCardAdapter.DoubleCardViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        doubleIconsCard.onRightIconClicked();
                    }
                });
                if (doubleIconsCard.getRightIconContentDescription() != null) {
                    getRightIcon().setContentDescription(doubleIconsCard.getRightIconContentDescription());
                }
            } else {
                getRightIcon().setVisibility(8);
            }
            if (doubleIconsCard.getSubtitleAddendum() != null) {
                getSubtitleAddendum().setText(doubleIconsCard.getSubtitleAddendum());
                getSubtitleAddendum().setVisibility(0);
                if (doubleIconsCard.getSubtitleAddendumColor() == null) {
                    getSubtitleAddendum().setTextColor(LandingPageCardAdapter.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicNeutral20));
                } else {
                    getSubtitleAddendum().setTextColor(doubleIconsCard.getSubtitleAddendumColor().intValue());
                }
            } else {
                getSubtitleAddendum().setVisibility(8);
            }
            if (doubleIconsCard.getHint() != null) {
                getHintContent().setVisibility(0);
                getHintText().setText(doubleIconsCard.getHint());
                getHintContent().setOnClickListener(new HintOnClickListener(doubleIconsCard));
                return;
            }
            getHintContent().setVisibility(8);
        }

        public View getHintContent() {
            return this.hintContent;
        }

        public TextView getHintText() {
            return this.hintText;
        }

        public ImageView getLeftIcon() {
            return this.leftIcon;
        }

        public ImageView getRightIcon() {
            return this.rightIcon;
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class HintOnClickListener implements View.OnClickListener {
        private static final int HINT_CLICKABLE_COOL_DOWN_MS = 1000;
        private static final float HINT_COOL_DOWN_ALPHA = 0.5f;
        private final DoubleIconsCard card;

        HintOnClickListener(DoubleIconsCard doubleIconsCard) {
            this.card = doubleIconsCard;
        }

        public /* synthetic */ void lambda$onClick$0$LandingPageCardAdapter$HintOnClickListener(View view) {
            view.setOnClickListener(this);
            view.setAlpha(1.0f);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            this.card.onHintClicked();
            if (this.card.getCardId() == 256 && this.card.getCardDomain() == DriveModeCard.CardDomain.NAVIGATION) {
                view.setOnClickListener(null);
                view.setAlpha(HINT_COOL_DOWN_ALPHA);
                view.postDelayed(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$HintOnClickListener$K0p_TRuP8X6i61KzWsYX52p5gEA
                    @Override // java.lang.Runnable
                    public final void run() {
                        LandingPageCardAdapter.HintOnClickListener.this.lambda$onClick$0$LandingPageCardAdapter$HintOnClickListener(view);
                    }
                }, 1000L);
            }
        }
    }

    /* loaded from: classes7.dex */
    public class SingleCardViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView icon;
        private TextView title;

        public SingleCardViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.description = (TextView) view.findViewById(R.id.description);
            this.icon = (ImageView) view.findViewById(R.id.single_icon);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$bindData$0(SingleIconCard singleIconCard, View view) {
            singleIconCard.onCardClicked();
            DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, singleIconCard.getTitle());
        }

        public void bindData(final SingleIconCard singleIconCard) {
            if (singleIconCard.getTitle() != null) {
                getTitle().setText(singleIconCard.getTitle());
            }
            String description = singleIconCard.getDescription();
            if (description == null || description.isEmpty()) {
                String unused = LandingPageCardAdapter.TAG;
                this.description.setVisibility(8);
            } else {
                getDescription().setText(singleIconCard.getDescription());
            }
            if (singleIconCard.getIcon() != null) {
                Drawable icon = singleIconCard.getIcon();
                if (singleIconCard.allowIconTinting()) {
                    LandingPageCardAdapter landingPageCardAdapter = LandingPageCardAdapter.this;
                    DrawableCompat.setTint(DrawableCompat.wrap(icon), LandingPageCardAdapter.this.driveModeThemeManager.getColorFromAttribute(new ContextThemeWrapper(landingPageCardAdapter.context, landingPageCardAdapter.driveModeThemeManager.getTheme()), R.attr.mosaicIcon110));
                }
                getIcon().setImageDrawable(icon);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$SingleCardViewHolder$H525m_pxS1ROsaemEV4LNzPCGB8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LandingPageCardAdapter.SingleCardViewHolder.lambda$bindData$0(SingleIconCard.this, view);
                }
            });
        }

        public TextView getDescription() {
            return this.description;
        }

        public ImageView getIcon() {
            return this.icon;
        }

        public TextView getTitle() {
            return this.title;
        }
    }

    public LandingPageCardAdapter(Context context, DriveModeThemeManager driveModeThemeManager) {
        this.context = context;
        this.driveModeThemeManager = driveModeThemeManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCardPriority(DriveModeCard driveModeCard) {
        int ordinal = driveModeCard.getCardDomain().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return 2;
            }
            if (ordinal == 2) {
                return 1;
            }
            return ordinal != 3 ? 4 : 3;
        }
        return 0;
    }

    public void addCard(DriveModeCard driveModeCard) {
        this.cards.add(driveModeCard);
        Collections.sort(this.cards, new Comparator<DriveModeCard>() { // from class: com.amazon.alexa.drive.landing.LandingPageCardAdapter.1
            @Override // java.util.Comparator
            public int compare(DriveModeCard driveModeCard2, DriveModeCard driveModeCard3) {
                return Integer.compare(LandingPageCardAdapter.this.getCardPriority(driveModeCard2), LandingPageCardAdapter.this.getCardPriority(driveModeCard3));
            }
        });
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$j1A0I13IaKnIctgnfU2EGc5IlEE
            @Override // java.lang.Runnable
            public final void run() {
                LandingPageCardAdapter.this.lambda$addCard$0$LandingPageCardAdapter();
            }
        });
    }

    public void clearCards() {
        final int size = this.cards.size();
        this.cards.clear();
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$s5kAmdcXmcLUlPogrtp5jX2M3rc
            @Override // java.lang.Runnable
            public final void run() {
                LandingPageCardAdapter.this.lambda$clearCards$3$LandingPageCardAdapter(size);
            }
        });
    }

    int convertDPToPixels(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
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
        if (driveModeCard instanceof DoubleIconsCard) {
            return 2;
        }
        return driveModeCard instanceof SecureCard ? 3 : 0;
    }

    public /* synthetic */ void lambda$addCard$0$LandingPageCardAdapter() {
        notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$clearCards$3$LandingPageCardAdapter(int i) {
        notifyItemRangeRemoved(0, i);
    }

    public /* synthetic */ void lambda$removeCard$2$LandingPageCardAdapter(int i) {
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.cards.size());
    }

    public /* synthetic */ void lambda$updateCard$1$LandingPageCardAdapter() {
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            ((SingleCardViewHolder) viewHolder).bindData((SingleIconCard) this.cards.get(i));
        } else if (itemViewType == 2) {
            ((DoubleCardViewHolder) viewHolder).bindData((DoubleIconsCard) this.cards.get(i));
        } else if (itemViewType != 3) {
        } else {
            ((ButtonCardViewHolder) viewHolder).bindData((SecureCard) this.cards.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        String str = "onCreateViewHolder " + i;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.driveModeThemeManager.getTheme());
        if (i != 1) {
            if (i == 2) {
                return new DoubleCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_double_icons, viewGroup, false));
            }
            if (i != 3) {
                return new SingleCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_empty, viewGroup, false));
            }
            return new ButtonCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_guard, viewGroup, false));
        }
        return new SingleCardViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_item_single_icon, viewGroup, false));
    }

    public void removeCard(final int i) {
        if (i < this.cards.size() && i >= 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeCard ");
            outline107.append(this.cards.get(i).getCardId());
            outline107.toString();
            this.cards.remove(i);
            this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$q6EXqQCX-FVdRkgTxLbP9D3burc
                @Override // java.lang.Runnable
                public final void run() {
                    LandingPageCardAdapter.this.lambda$removeCard$2$LandingPageCardAdapter(i);
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
            this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageCardAdapter$pwbifT2BECcZ0g8f12vFHsRlbdc
                @Override // java.lang.Runnable
                public final void run() {
                    LandingPageCardAdapter.this.lambda$updateCard$1$LandingPageCardAdapter();
                }
            });
            return;
        }
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Invalid card position: ", i, " cards: ");
        outline109.append(this.cards.size());
        Log.w(str, outline109.toString());
    }
}
