package com.amazon.alexa.drive.smart.device;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
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
import com.amazon.alexa.drive.smart.device.SmartDeviceCardAdapter;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.smart.device.data.SmartDevice;
import com.amazon.alexa.drive.smart.device.lock.Lock;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class SmartDeviceCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SmartDeviceCardAdapter";
    private SmartDeviceContract.CardClickListener clickListener;
    protected Context context;
    private final DriveModeThemeManager driveModeThemeManager;
    private final List<Lock> locks = new ArrayList();

    /* loaded from: classes7.dex */
    public class SecureCardViewHolder extends RecyclerView.ViewHolder {
        private final Button button;
        private final TextView descriptionTextView;
        private final ImageView icon;
        private final TextView titleTextView;

        public SecureCardViewHolder(@NonNull View view) {
            super(view);
            this.button = (Button) view.findViewById(R.id.right_button);
            this.icon = (ImageView) view.findViewById(R.id.left_icon);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
            this.descriptionTextView = (TextView) view.findViewById(R.id.description);
        }

        public void bindData(final Lock lock) {
            SmartDeviceCardAdapter smartDeviceCardAdapter = SmartDeviceCardAdapter.this;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(smartDeviceCardAdapter.context, smartDeviceCardAdapter.driveModeThemeManager.getTheme());
            String lockState = lock.getLockState();
            if (!TextUtils.isEmpty(lockState)) {
                this.button.setVisibility(0);
                this.button.setText(lockState);
                this.button.setBackgroundResource(lock.isLocked() ? R.drawable.smart_device_background_away : R.drawable.smart_device_background_home);
            } else {
                this.button.setVisibility(8);
            }
            String description = lock.getDescription();
            if (!TextUtils.isEmpty(description)) {
                this.descriptionTextView.setVisibility(0);
                this.descriptionTextView.setText(description);
            } else {
                this.descriptionTextView.setVisibility(8);
            }
            this.titleTextView.setText(lock.getTitle());
            Drawable icon = lock.getIcon();
            DrawableCompat.setTint(DrawableCompat.wrap(icon), SmartDeviceCardAdapter.this.driveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicIcon110));
            this.icon.setImageDrawable(icon);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartDeviceCardAdapter$SecureCardViewHolder$pULxvs4A6HoEDFTq4woU6hhfFMY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmartDeviceCardAdapter.SecureCardViewHolder.this.lambda$bindData$0$SmartDeviceCardAdapter$SecureCardViewHolder(lock, view);
                }
            });
        }

        public Button getButton() {
            return this.button;
        }

        public /* synthetic */ void lambda$bindData$0$SmartDeviceCardAdapter$SecureCardViewHolder(Lock lock, View view) {
            if (SmartDeviceCardAdapter.this.clickListener != null) {
                SmartDeviceCardAdapter.this.clickListener.onClick(lock);
            }
        }
    }

    public SmartDeviceCardAdapter(Context context, DriveModeThemeManager driveModeThemeManager) {
        this.context = context;
        this.driveModeThemeManager = driveModeThemeManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.locks.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SecureCardViewHolder) viewHolder).bindData(this.locks.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SecureCardViewHolder(LayoutInflater.from(new ContextThemeWrapper(this.context, this.driveModeThemeManager.getTheme())).inflate(R.layout.dm_item_guard, viewGroup, false));
    }

    public void setCards(List<SmartDevice> list) {
        this.locks.clear();
        for (SmartDevice smartDevice : list) {
            if (smartDevice.getSmartDeviceType() == 2) {
                this.locks.add((Lock) smartDevice);
            }
        }
        notifyDataSetChanged();
    }

    public void setOnclickListener(SmartDeviceContract.CardClickListener cardClickListener) {
        this.clickListener = cardClickListener;
    }
}
