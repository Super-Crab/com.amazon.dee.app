package com.amazon.deecomms.oobe.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.deecomms.R;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.oobe.fragments.PersonItem;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class PersonArrayAdapter extends ArrayAdapter<PersonItem> {
    private final boolean isThemedUIEnabled;
    private final int textViewResourceId;

    public PersonArrayAdapter(Context context, int i, ArrayList<PersonItem> arrayList, boolean z) {
        super(context, i, arrayList);
        this.textViewResourceId = i;
        this.isThemedUIEnabled = z;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        PersonItem.ViewHolder viewHolder;
        PersonItem item = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(this.textViewResourceId, viewGroup, false);
            viewHolder = new PersonItem.ViewHolder();
            viewHolder.userIdentification = (TextView) view.findViewById(R.id.oobe_user_list_name);
            viewHolder.profileIcon = (ImageView) view.findViewById(R.id.oobe_user_list_icon);
            viewHolder.arrow = (ImageView) view.findViewById(R.id.oobe_user_list_rightarrow);
            view.setTag(viewHolder);
        } else {
            viewHolder = (PersonItem.ViewHolder) view.getTag();
        }
        if (item.isPlaceholder) {
            viewHolder.profileIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_add_profile_active_light_36dp));
            viewHolder.profileIcon.setVisibility(0);
            viewHolder.userIdentification.setText(R.string.oobe_user_someone_else);
            viewHolder.arrow.setVisibility(0);
            viewHolder.userIdentification.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
        } else {
            Person person = item.person;
            String string = getContext().getResources().getString(R.string.oobe_user_identification);
            if ("JP".equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false))) {
                viewHolder.userIdentification.setText(String.format(string, person.lastName, person.firstName));
            } else {
                viewHolder.userIdentification.setText(String.format(string, person.firstName, person.lastName));
            }
            if (person.isChild) {
                viewHolder.profileIcon.setImageResource(R.drawable.ic_profile_disabled_light_36dp);
                viewHolder.arrow.setVisibility(4);
                viewHolder.userIdentification.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral30));
            } else {
                viewHolder.profileIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_profile_active_light_36dp));
                viewHolder.arrow.setVisibility(0);
                viewHolder.userIdentification.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
            }
        }
        return view;
    }
}
