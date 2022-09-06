package com.amazon.alexa.navigation.menu;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import java.util.List;
/* loaded from: classes9.dex */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationViewHolder> {
    private static final String TAG = "NavigationAdapter";
    List<MenuItem> menuItems;

    /* loaded from: classes9.dex */
    public static class NavigationViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "NavigationViewHolder";
        Context context;
        View divider;
        View holderView;
        LinearLayout itemHolder;
        ImageView itemIcon;
        TextView itemName;

        NavigationViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            ThemeUtil.setTheme(this.context);
            this.itemName = (TextView) view.findViewById(R.id.item_text);
            this.itemName.setTypeface(FontResolver.getFont(context, Font.AMAZON_EMBER_REGULAR));
            this.itemName.setTextColor(ThemeUtil.getColorFromAttribute(context, R.attr.mosaicNeutral10));
            this.itemIcon = (ImageView) view.findViewById(R.id.item_icon);
            this.itemIcon.setColorFilter(ThemeUtil.getColorFromAttribute(context, R.attr.mosaicNeutral10));
            this.itemHolder = (LinearLayout) view.findViewById(R.id.recycler_view_resources);
            this.divider = view.findViewById(R.id.menu_item_divider);
            this.holderView = view;
            int colorFromAttribute = ThemeUtil.getColorFromAttribute(context, R.attr.mosaicNeutral30);
            int colorFromAttribute2 = ThemeUtil.getColorFromAttribute(context, R.attr.mosaicNeutral60);
            this.divider.setBackgroundColor(colorFromAttribute);
            this.itemHolder.setBackground(new RippleDrawable(getRippleColor(colorFromAttribute2), new ColorDrawable(0), new ColorDrawable(-1)));
        }

        private ColorStateList getRippleColor(int i) {
            return new ColorStateList(new int[][]{new int[0]}, new int[]{i});
        }
    }

    public NavigationAdapter(List<MenuItem> list) {
        this.menuItems = list;
    }

    private void bindExpandableItem(ExpandableMenuItem expandableMenuItem, NavigationViewHolder navigationViewHolder) {
        LinearLayout linearLayout = (LinearLayout) navigationViewHolder.holderView.findViewById(R.id.hidden_item_container);
        if (linearLayout.getChildCount() == 0) {
            for (MenuItem menuItem : expandableMenuItem.getMenuItemList()) {
                NavigationViewHolder mo7499onCreateViewHolder = mo7499onCreateViewHolder((ViewGroup) linearLayout, menuItem.getMenuItemLayout());
                bindItem(menuItem, mo7499onCreateViewHolder);
                linearLayout.addView(mo7499onCreateViewHolder.holderView);
            }
        }
        expandableMenuItem.updateExpandable(navigationViewHolder.holderView, false);
    }

    private void bindItem(final MenuItem menuItem, NavigationViewHolder navigationViewHolder) {
        TestId testId = menuItem.getTestId();
        navigationViewHolder.itemHolder.setId(testId.itemTestId);
        navigationViewHolder.itemName.setText(menuItem.getNameId());
        navigationViewHolder.itemName.setId(testId.textTestId);
        navigationViewHolder.itemIcon.setImageResource(menuItem.getIconId());
        navigationViewHolder.itemIcon.setId(testId.iconTestId);
        if (menuItem.getIconColor() != 0) {
            navigationViewHolder.itemIcon.getDrawable().mutate().setColorFilter(navigationViewHolder.itemIcon.getResources().getColor(menuItem.getIconColor()), PorterDuff.Mode.MULTIPLY);
            navigationViewHolder.itemIcon.setTag(Integer.valueOf(menuItem.getIconColor()));
        }
        navigationViewHolder.divider.setVisibility(menuItem.isLineUnderneath() ? 0 : 8);
        LinearLayout linearLayout = navigationViewHolder.itemHolder;
        menuItem.getClass();
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.navigation.menu.-$$Lambda$LaaltTnk_FhWfRodrIsqsNKTZfc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuItem.this.onClick(view);
            }
        });
        if (menuItem instanceof ExpandableMenuItem) {
            bindExpandableItem((ExpandableMenuItem) menuItem, navigationViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.menuItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @LayoutRes
    public int getItemViewType(int i) {
        return this.menuItems.get(i).getMenuItemLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull NavigationViewHolder navigationViewHolder, int i) {
        bindItem(this.menuItems.get(i), navigationViewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public NavigationViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, @LayoutRes int i) {
        return new NavigationViewHolder(viewGroup.getContext(), LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }
}
