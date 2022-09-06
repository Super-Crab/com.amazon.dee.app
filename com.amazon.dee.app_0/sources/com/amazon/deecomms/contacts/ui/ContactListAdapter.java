package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.LanguageUtil;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ContactListAdapter extends CursorAdapter {
    private static final int DISPLAY_DIVIDER = 1;
    private static final int HIDE_DIVIDER = 2;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactListAdapter.class);
    private static final int STATE_REGULAR_LIST_ITEM = 2;
    private static final int STATE_SECTIONED_LIST_ITEM = 1;
    private static final int STATE_UNKNOWN = 0;
    private static final int UNKNOWN_DIVIDER = 0;
    private final Context mContext;
    private final boolean mIsThemedUI;
    private int[] mListItemDividerVisibilityStates;
    private int[] mListItemStates;
    private final int mNonAlexaContactColorId;
    private final String mUserPFM;
    private final boolean shouldShowCheckbox;
    @Inject
    ThemingUpdateUtil themingUpdateUtil;

    /* loaded from: classes12.dex */
    final class ContactViewHolder {
        private final ImageView mCheckBox;
        private final View mDivider;
        private final View mInfoDivider;
        private final TextView mInfoSection;
        private final TextView mName;
        private final TextView mSection;
        private String recipientName;

        public ContactViewHolder(View view) {
            this.mName = (TextView) view.findViewById(R.id.first_name);
            this.mSection = (TextView) view.findViewById(R.id.section_name);
            this.mDivider = view.findViewById(R.id.divider);
            this.mInfoSection = (TextView) view.findViewById(R.id.information_section);
            this.mInfoDivider = view.findViewById(R.id.info_divider);
            this.mCheckBox = (ImageView) view.findViewById(R.id.contact_selected_checkbox);
            if (ContactListAdapter.this.mIsThemedUI && ContactListAdapter.this.themingUpdateUtil.isMosaicThemingEnabled()) {
                ContactListAdapter.this.themingUpdateUtil.applyColorToTextView(this.mName, ContactListAdapter.this.mContext, R.attr.mosaicNeutral10);
                ContactListAdapter.this.themingUpdateUtil.applyBackgroundColorToView(this.mDivider, ContactListAdapter.this.mContext, R.attr.mosaicNeutral50);
                ContactListAdapter.this.themingUpdateUtil.applyBackgroundColorToView(this.mSection, ContactListAdapter.this.mContext, R.attr.mosaicNeutral50);
                ContactListAdapter.this.themingUpdateUtil.applyColorToTextView(this.mSection, ContactListAdapter.this.mContext, R.attr.mosaicNeutral20);
                ContactListAdapter.this.themingUpdateUtil.applyBackgroundColorToView(this.mInfoDivider, ContactListAdapter.this.mContext, R.attr.mosaicNeutral10);
                ContactListAdapter.this.themingUpdateUtil.applyTintColorToImageView(this.mCheckBox, ContactListAdapter.this.mContext, R.attr.mosaicNeutral20);
            }
            this.mCheckBox.setVisibility(ContactListAdapter.this.shouldShowCheckbox ? 0 : 8);
        }

        private Character getFirstCharByJP(Cursor cursor) {
            char charAt;
            if (cursor == null) {
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME_SORT_FORMAT));
            String string2 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME_SORT_FORMAT));
            String string3 = cursor.getString(cursor.getColumnIndex("lastName"));
            if (!TextUtils.isEmpty(string2)) {
                charAt = string2.charAt(0);
            } else if (!TextUtils.isEmpty(string)) {
                charAt = string.charAt(0);
            } else if (!TextUtils.isEmpty(string3)) {
                charAt = string3.charAt(0);
            } else {
                charAt = getFullName(cursor).charAt(0);
            }
            return Character.valueOf(charAt);
        }

        private String getFullName(Cursor cursor) {
            if (cursor == null) {
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndex("firstName"));
            String string2 = cursor.getString(cursor.getColumnIndex("lastName"));
            String string3 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME));
            if (TextUtils.isEmpty(string3)) {
                string3 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME));
            }
            return ContactUtils.getFullName(new FullContactName(string, string2, string3, cursor.getString(cursor.getColumnIndex("company"))));
        }

        private boolean isInValidCharacterRange(Character ch) {
            boolean z = false;
            boolean z2 = ch.charValue() >= ContactListAdapter.this.mContext.getString(R.string.contact_list_section_start_alphabet_small).charAt(0) && ch.charValue() <= ContactListAdapter.this.mContext.getString(R.string.contact_list_section_end_alphabet_small).charAt(0);
            boolean z3 = ch.charValue() >= ContactListAdapter.this.mContext.getString(R.string.contact_list_section_start_alphabet_large).charAt(0) && ch.charValue() <= ContactListAdapter.this.mContext.getString(R.string.contact_list_section_end_alphabet_large).charAt(0);
            if (z2 || z3) {
                z = true;
            }
            return ContactListAdapter.this.mUserPFM.equals("JP") ? z | LanguageUtil.isHiragana(ch.charValue()) : z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0062, code lost:
            if (r7 != null) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00f2, code lost:
            if (r8 != null) goto L42;
         */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void render(android.database.Cursor r12) {
            /*
                Method dump skipped, instructions count: 458
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.contacts.ui.ContactListAdapter.ContactViewHolder.render(android.database.Cursor):void");
        }
    }

    public ContactListAdapter(Context context, boolean z, boolean z2) {
        super(context, (Cursor) null, 0);
        this.mContext = context;
        this.mIsThemedUI = z2;
        this.mUserPFM = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false);
        CommsDaggerWrapper.getComponent().inject(this);
        this.shouldShowCheckbox = z;
        if (CoboUtils.isCoboCallingAvailable()) {
            this.mNonAlexaContactColorId = R.color.contact_list_name;
        } else {
            this.mNonAlexaContactColorId = R.color.non_cobo_non_alexa_contact_list_name;
        }
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ((ContactViewHolder) view.getTag()).render(cursor);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate;
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[ContactListAdapter] mosaic themening is enabled: ");
        outline1.append(this.themingUpdateUtil.isMosaicThemingEnabled());
        commsLogger.d(outline1.toString());
        if (this.mIsThemedUI && this.themingUpdateUtil.isMosaicThemingEnabled()) {
            ThemeUtil.setTheme(context);
        }
        if (this.mIsThemedUI) {
            inflate = layoutInflater.inflate(R.layout.fiesta_contact_list_item, (ViewGroup) null);
        } else {
            inflate = layoutInflater.inflate(R.layout.contact_list_item, (ViewGroup) null);
        }
        if (this.mIsThemedUI && this.themingUpdateUtil.isMosaicThemingEnabled()) {
            this.themingUpdateUtil.applyBackgroundColorToView(inflate, this.mContext, R.attr.mosaicBackground);
        }
        ContactViewHolder contactViewHolder = new ContactViewHolder(inflate);
        contactViewHolder.render(cursor);
        inflate.setTag(contactViewHolder);
        return inflate;
    }

    @Override // android.widget.CursorAdapter
    public Cursor swapCursor(Cursor cursor) {
        int[] iArr = null;
        this.mListItemStates = cursor == null ? null : new int[cursor.getCount()];
        if (cursor != null) {
            iArr = new int[cursor.getCount()];
        }
        this.mListItemDividerVisibilityStates = iArr;
        return super.swapCursor(cursor);
    }
}
