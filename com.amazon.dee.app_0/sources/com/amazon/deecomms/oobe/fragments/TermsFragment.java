package com.amazon.deecomms.oobe.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.oobe.OOBEActivity;
/* loaded from: classes12.dex */
public class TermsFragment extends MainOOBEFragment {
    private int leftPadding;

    private void addText(TextView textView, String str, String str2) {
        Spannable spannable;
        int dimension = (int) getResources().getDimension(R.dimen.oobe_padding_xsmall);
        if (Utils.isNullOrEmpty(str2)) {
            textView.setPadding(0, 0, 0, dimension);
            spannable = (Spannable) Html.fromHtml(str);
        } else {
            textView.setPadding(this.leftPadding, 0, 0, dimension);
            spannable = (Spannable) Html.fromHtml(str2 + ". " + str);
        }
        MainOOBEFragment.removeUnderlines(spannable);
        textView.setText(spannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setFiestaStyle(View view) {
        int color = ContextCompat.getColor(getContext(), R.color.fiesta_text_primary);
        ((TextView) view.findViewById(R.id.terms_of_use_text_0)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_1)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_2)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_3)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_4)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_5)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_6)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_7)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_8)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_9)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_10)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_11)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_12)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_13)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_14)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_15)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_16)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_17)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_18)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_19)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_20)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_21)).setTextColor(color);
        ((TextView) view.findViewById(R.id.terms_of_use_text_22)).setTextColor(color);
    }

    private void setTerms(View view) {
        String[] stringArray = getResources().getStringArray(R.array.tou_terms_array);
        this.leftPadding = (int) getResources().getDimension(R.dimen.oobe_list_item_indent);
        addText((TextView) view.findViewById(R.id.terms_of_use_text_0), stringArray[0]);
        addText((TextView) view.findViewById(R.id.terms_of_use_text_1), stringArray[1], "1");
        this.leftPadding *= 2;
        addText((TextView) view.findViewById(R.id.terms_of_use_text_2), stringArray[2], "a");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_3), stringArray[3], "b");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_4), stringArray[4], "c");
        this.leftPadding /= 2;
        addText((TextView) view.findViewById(R.id.terms_of_use_text_5), stringArray[5], "2");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_6), stringArray[6], "3");
        this.leftPadding *= 2;
        addText((TextView) view.findViewById(R.id.terms_of_use_text_7), stringArray[7], "a");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_8), stringArray[8], "b");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_9), stringArray[9], "c");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_10), stringArray[10], "d");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_11), stringArray[11], "e");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_12), stringArray[12], "f");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_13), stringArray[13], "g");
        this.leftPadding /= 2;
        addText((TextView) view.findViewById(R.id.terms_of_use_text_14), stringArray[14], "4");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_15), stringArray[15], "5");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_16), stringArray[16], "6");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_17), stringArray[17], "7");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_18), stringArray[18], "8");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_19), stringArray[19], "9");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_20), stringArray[20], "10");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_21), stringArray[21], "11");
        addText((TextView) view.findViewById(R.id.terms_of_use_text_22), stringArray[22]);
        if (isThemedUIEnabled()) {
            setFiestaStyle(view);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_oobe_terms_of_use, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.terms_of_use, viewGroup, false);
        }
        setTerms(inflate);
        ((Button) inflate.findViewById(R.id.tou_continue_btn)).setOnClickListener(nextFragmentClickHandler());
        setHeaderTitle(getString(R.string.tou_title));
        if (getActivity() != null && !getActivity().isFinishing()) {
            ((OOBEActivity) getActivity()).showHeaderBar();
            ((OOBEActivity) getActivity()).hideBackButton();
            ((OOBEActivity) getActivity()).hideSkipButton();
        }
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_TERMS_START);
    }

    private void addText(TextView textView, String str) {
        addText(textView, str, null);
    }
}
