package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactSourceType;
import com.amazon.deecomms.contacts.ui.CallBottomSheetAdapter;
import java.util.List;
/* loaded from: classes12.dex */
public class CallBottomSheetAdapter extends RecyclerView.Adapter {
    private static final int EMPTY = 1;
    private static final int NORMAL = 0;
    private Context appContext;
    private boolean mIsMosaicThemeEnabled;
    private boolean mIsThemedUIEnabled;
    private List<ContactPhoneNumber> phoneNumberList;
    private CallSelectionListener selectionListener;

    /* loaded from: classes12.dex */
    public class CallViewHolder extends RecyclerView.ViewHolder {
        private final View mainView;
        private final CommsTextView phoneNumberDescription;
        private final CommsTextView phoneNumberTitle;
        private final CallSelectionListener selectionListener;

        public CallViewHolder(View view, CallSelectionListener callSelectionListener) {
            super(view);
            this.mainView = view;
            this.phoneNumberTitle = (CommsTextView) this.mainView.findViewById(R.id.call_bottom_sheet_item_title);
            this.phoneNumberDescription = (CommsTextView) this.mainView.findViewById(R.id.call_bottom_sheet_item_description);
            this.selectionListener = callSelectionListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallBottomSheetAdapter(@NonNull CallSelectionListener callSelectionListener, boolean z, boolean z2, Context context) {
        this.selectionListener = callSelectionListener;
        this.mIsThemedUIEnabled = z;
        this.mIsMosaicThemeEnabled = z2;
        this.appContext = context;
    }

    private int getLayout() {
        if (this.mIsMosaicThemeEnabled) {
            return R.layout.mosaic_call_bottom_sheet_list_item;
        }
        if (this.mIsThemedUIEnabled) {
            return R.layout.fiesta_call_bottom_sheet_list_item;
        }
        return R.layout.call_bottom_sheet_list_item;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ContactPhoneNumber> list = this.phoneNumberList;
        if (list == null || list.size() <= 0) {
            return 1;
        }
        return this.phoneNumberList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<ContactPhoneNumber> list = this.phoneNumberList;
        return (list == null || list.size() == 0) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        String str;
        if (viewHolder.getItemViewType() == 1) {
            ((CallViewHolder) viewHolder).phoneNumberTitle.setText(R.string.no_phone_numbers_available);
            return;
        }
        final CallViewHolder callViewHolder = (CallViewHolder) viewHolder;
        final ContactPhoneNumber contactPhoneNumber = this.phoneNumberList.get(i);
        if (ContactSourceType.SkypeContact.getName().equals(contactPhoneNumber.getContactSource())) {
            callViewHolder.phoneNumberTitle.setText(R.string.phone_number_type_skype);
        } else {
            if (contactPhoneNumber.getRawType() != null) {
                str = contactPhoneNumber.getRawType();
            } else {
                str = contactPhoneNumber.getType().toString();
            }
            callViewHolder.phoneNumberTitle.setText(str);
        }
        callViewHolder.phoneNumberDescription.setText(CoboUtils.formatPhoneNumberForDisplay(contactPhoneNumber.getPhoneNumber()));
        callViewHolder.mainView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$CallBottomSheetAdapter$a5nNwiqWxqeUGHfsHLe7znFiA_A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CallBottomSheetAdapter.CallViewHolder.this.selectionListener.callItemSelected(contactPhoneNumber);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CallViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(), viewGroup, false), this.selectionListener);
    }

    public void setPhoneNumbers(List<ContactPhoneNumber> list) {
        this.phoneNumberList = list;
        notifyDataSetChanged();
    }
}
