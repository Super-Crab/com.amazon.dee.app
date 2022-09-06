package com.amazon.deecomms.contacts.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class RelationshipListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String defaultRelationship;
    private final int resource;
    private final String[] values;

    public RelationshipListAdapter(@NonNull Activity activity, int i, @NonNull String[] strArr, @NonNull String str) {
        super(activity, i, strArr);
        this.context = activity;
        this.values = strArr;
        this.resource = i;
        this.defaultRelationship = str;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.values.length;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(this.resource, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.relationship_icon);
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("SHARED_PREFS", 0);
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(Constants.CHILD_CONTACT_RELATIONSHIP_BASE_PREF);
        outline1.append(this.values[i].toUpperCase());
        ((TextView) view.findViewById(R.id.relationship_name)).setText(sharedPreferences.getString(outline1.toString(), this.values[i]));
        if (this.values[i].equalsIgnoreCase(this.defaultRelationship)) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public String getItem(int i) {
        return this.values[i];
    }
}
