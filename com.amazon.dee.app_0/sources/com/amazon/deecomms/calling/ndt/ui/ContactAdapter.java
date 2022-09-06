package com.amazon.deecomms.calling.ndt.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.ndt.ui.ContactAdapter;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class ContactAdapter extends RecyclerView.Adapter {
    @VisibleForTesting
    public static final int EMPTY = 1;
    @VisibleForTesting
    public static final int LOADING = 2;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactAdapter.class);
    @VisibleForTesting
    public static final int NORMAL = 0;
    private List<PresenceDocument> presenceDocuments;
    private ContactSelectionListener selectionListener;

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final CommsTextView contactName;
        private final CommsTextView contactStatus;
        private final View mainView;
        private final ContactSelectionListener selectionListener;

        public ContactViewHolder(View view, ContactSelectionListener contactSelectionListener) {
            super(view);
            this.mainView = view;
            this.contactName = (CommsTextView) this.mainView.findViewById(R.id.drop_in_targeting_device_name);
            this.contactStatus = (CommsTextView) this.mainView.findViewById(R.id.drop_in_targeting_device_status);
            this.selectionListener = contactSelectionListener;
        }
    }

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View view) {
            super(view);
        }
    }

    public ContactAdapter(ContactSelectionListener contactSelectionListener) {
        this.selectionListener = contactSelectionListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PresenceDocument> list = this.presenceDocuments;
        if (list == null || list.size() <= 0) {
            return 1;
        }
        return this.presenceDocuments.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<PresenceDocument> list = this.presenceDocuments;
        if (list == null) {
            return 2;
        }
        return list.size() == 0 ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == 1) {
            ((ContactViewHolder) viewHolder).contactName.setText(R.string.no_contacts_available);
        } else if (viewHolder.getItemViewType() != 0) {
        } else {
            final ContactViewHolder contactViewHolder = (ContactViewHolder) viewHolder;
            final PresenceDocument presenceDocument = this.presenceDocuments.get(i);
            contactViewHolder.contactName.setText(ContactUtils.getFullName(new ContactName(presenceDocument.getContactName(), presenceDocument.getContactLastName())));
            contactViewHolder.contactName.setTextColor(Utils.getColorFromResource(R.color.device_targeting_device_name));
            if (!presenceDocument.isActive()) {
                contactViewHolder.contactStatus.setText("");
                contactViewHolder.contactStatus.setTextColor(Utils.getColorFromResource(R.color.device_targeting_inactive_device));
            } else {
                contactViewHolder.contactStatus.setText(Utils.getStringFromResource(R.string.device_status_active));
                contactViewHolder.contactStatus.setTextColor(Utils.getColorFromResource(R.color.device_targeting_active_device));
            }
            contactViewHolder.mainView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ndt.ui.-$$Lambda$ContactAdapter$rLnRisDoUh13i-8FtFBOX2gsiVc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ContactAdapter.ContactViewHolder.this.selectionListener.contactSelected(presenceDocument);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            return new LoadingViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drop_in_targeting_list_item, viewGroup, false));
        }
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drop_in_targeting_list_item, viewGroup, false), this.selectionListener);
    }

    public void setPresenceDocuments(List<PresenceDocument> list) {
        if (list != null) {
            this.presenceDocuments = new ArrayList();
            for (PresenceDocument presenceDocument : list) {
                String contactHomegroupId = presenceDocument.getContactHomegroupId();
                if (TextUtils.isEmpty(contactHomegroupId)) {
                    LOG.d("Presence document's commsId is missing, skipping...");
                } else {
                    try {
                        if (!CommunicableEntity.fromCommsID(contactHomegroupId).isHomegroup()) {
                            LOG.d("Presence document's commsId is not a home group, skipping...");
                        } else if (contactHomegroupId.equals(CommsInternal.getInstance().getHomeGroupId())) {
                            LOG.d("Presence document's commsId is this user's homegroup, skipping...");
                        } else {
                            this.presenceDocuments.add(presenceDocument);
                        }
                    } catch (MalformedCommsIDException e) {
                        LOG.e("Presence document's commsId is not a communicable entity, skipping...", e);
                    }
                }
            }
            Collections.sort(this.presenceDocuments, $$Lambda$ContactAdapter$KR_PNi3yJVoyCPnbGIlnKIZ_hs.INSTANCE);
        } else {
            this.presenceDocuments = null;
        }
        notifyDataSetChanged();
    }
}
