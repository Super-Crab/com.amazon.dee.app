package com.amazon.deecomms.contacts.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.common.ui.DividerItemDecoration;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.CallBottomSheetType;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactSourceType;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes12.dex */
public class CallBottomSheetDialogFragment extends BottomSheetDialogFragment implements CallSelectionListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallBottomSheetDialogFragment.class);
    private List<CallBottomSheetType> bottomSheetTypeList;
    private CallInitiator callInitiator;
    private DialogInterface.OnCancelListener cancelListener;
    private String commsId;
    private String contactId;
    private View contentView;
    private DialogInterface.OnDismissListener dismissListener;
    private InitiationLogicFactory initiationLogicFactory;
    private boolean isAlexaEnabled;
    private CallBottomSheetAdapter mCallAdapter;
    private boolean mIsChild;
    private boolean mIsMosaicThemeEnabled;
    private boolean mIsThemedUIEnabled;
    private String name;

    private static List<ContactPhoneNumber> filterShermanPhoneNumbers(List<ContactPhoneNumber> list, Context context) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean shouldShowPSTNForNonCOBORegion = CoboUtils.shouldShowPSTNForNonCOBORegion();
        boolean isCoboCallingAvailable = CoboUtils.isCoboCallingAvailable();
        boolean isPhone = DeviceInfo.isPhone(context);
        for (ContactPhoneNumber contactPhoneNumber : list) {
            if ((shouldShowPSTNForNonCOBORegion && isPhone) || (contactPhoneNumber.isCOBOEnabled() && isCoboCallingAvailable)) {
                arrayList.add(contactPhoneNumber);
            }
        }
        return arrayList;
    }

    private int getLayout() {
        if (this.mIsMosaicThemeEnabled) {
            return R.layout.mosaic_call_bottom_sheet_dialog;
        }
        if (this.mIsThemedUIEnabled) {
            return R.layout.fiesta_call_bottom_sheet_dialog;
        }
        return R.layout.call_bottom_sheet_dialog;
    }

    public static CallBottomSheetDialogFragment newInstance(ContactEntry contactEntry, String str, CallBottomSheetType[] callBottomSheetTypeArr, boolean z, boolean z2, InitiationLogicFactory initiationLogicFactory) {
        CallBottomSheetDialogFragment callBottomSheetDialogFragment = new CallBottomSheetDialogFragment();
        callBottomSheetDialogFragment.contactId = contactEntry.getId();
        callBottomSheetDialogFragment.name = ContactUtils.determineContactDisplayName(contactEntry.getFullContactName(), true);
        callBottomSheetDialogFragment.commsId = str;
        callBottomSheetDialogFragment.callInitiator = new CallInitiator(MetricKeys.CALL_INITIATED_FROM_BOTTOM_SHEET);
        callBottomSheetDialogFragment.isAlexaEnabled = contactEntry.isAlexaEnabled();
        callBottomSheetDialogFragment.bottomSheetTypeList = Arrays.asList(callBottomSheetTypeArr);
        callBottomSheetDialogFragment.mIsThemedUIEnabled = z;
        callBottomSheetDialogFragment.mIsChild = contactEntry.isChild();
        callBottomSheetDialogFragment.mIsMosaicThemeEnabled = z2;
        callBottomSheetDialogFragment.initiationLogicFactory = initiationLogicFactory;
        return callBottomSheetDialogFragment;
    }

    private static List<ContactPhoneNumber> resolveDuplicateAndSkypeContactPhoneNumbers(List<ContactPhoneNumber> list) {
        HashMap hashMap = new HashMap();
        for (ContactPhoneNumber contactPhoneNumber : list) {
            String phoneNumber = contactPhoneNumber.getPhoneNumber();
            String contactSource = contactPhoneNumber.getContactSource();
            if (hashMap.containsKey(phoneNumber)) {
                if (ContactSourceType.SkypeContact.getName().equals(((ContactPhoneNumber) hashMap.get(phoneNumber)).getContactSource()) && !ContactSourceType.SkypeContact.getName().equals(contactSource) && !ContactSourceType.MergedContact.getName().equals(contactSource)) {
                    hashMap.put(phoneNumber, contactPhoneNumber);
                }
            } else if (!Utils.isFireOS() || (!ContactSourceType.SkypeContact.getName().equals(contactSource) && !ContactSourceType.MergedContact.getName().equals(contactSource))) {
                hashMap.put(phoneNumber, contactPhoneNumber);
            }
        }
        return new ArrayList(hashMap.values());
    }

    private void setUpPhoneNumbers() {
        View view = this.contentView;
        if (view == null) {
            return;
        }
        view.findViewById(R.id.phone_portion).setVisibility(0);
        boolean z = true;
        ((CommsTextView) this.contentView.findViewById(R.id.call_bottom_sheet_header)).setText(getString(R.string.call_bottom_sheet_header, this.name));
        FragmentActivity activity = getActivity();
        final InitiationLogicContract create = this.initiationLogicFactory.create(this.callInitiator, activity, activity, new CallHelper(), null, Constants.Telemetry.CallInitScreenNames.CALL_BOTTOM_SHEET);
        LinearLayout linearLayout = (LinearLayout) this.contentView.findViewById(R.id.alexa_audio_layout);
        linearLayout.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.CallBottomSheetDialogFragment.1
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view2) {
                create.initiateAudioCall(CallBottomSheetDialogFragment.this.commsId, CallBottomSheetDialogFragment.this.name);
                CallBottomSheetDialogFragment.this.dismiss();
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) this.contentView.findViewById(R.id.alexa_video_layout);
        linearLayout2.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.CallBottomSheetDialogFragment.2
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view2) {
                create.initiateVideoCall(CallBottomSheetDialogFragment.this.commsId, CallBottomSheetDialogFragment.this.name);
                CallBottomSheetDialogFragment.this.dismiss();
            }
        });
        if (!this.isAlexaEnabled) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(8);
        } else if (this.mIsChild) {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
        } else {
            if (this.bottomSheetTypeList.contains(CallBottomSheetType.AUDIO)) {
                linearLayout.setVisibility(0);
            }
            if (this.bottomSheetTypeList.contains(CallBottomSheetType.VIDEO)) {
                linearLayout2.setVisibility(0);
            }
        }
        if (!this.bottomSheetTypeList.contains(CallBottomSheetType.PhoneNumber)) {
            return;
        }
        boolean isPhone = DeviceInfo.isPhone(getContext());
        if (!CoboUtils.shouldShowPSTNForNonCOBORegion() || !isPhone) {
            z = CoboUtils.isCoboCallingAvailable();
        }
        RecyclerView recyclerView = (RecyclerView) this.contentView.findViewById(R.id.phone_number_list);
        if (!this.mIsChild && z) {
            recyclerView.setVisibility(0);
        } else {
            recyclerView.setVisibility(8);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mCallAdapter = new CallBottomSheetAdapter(this, this.mIsThemedUIEnabled, this.mIsMosaicThemeEnabled, getContext());
        setPhoneNumbers(filterShermanPhoneNumbers(resolveDuplicateAndSkypeContactPhoneNumbers(ContactsProviderUtils.getPhoneNumbersByContactId(getContext(), this.contactId)), getContext()));
        recyclerView.setAdapter(this.mCallAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
    }

    private void showPSTNDialer(ContactPhoneNumber contactPhoneNumber) {
        Intent intent = new Intent("android.intent.action.DIAL");
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("tel:");
        outline1.append(contactPhoneNumber.getPhoneNumber());
        intent.setData(Uri.parse(outline1.toString()));
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CALL_OPEN_DIALER);
        startActivity(intent);
    }

    @Override // com.amazon.deecomms.contacts.ui.CallSelectionListener
    public void callItemSelected(ContactPhoneNumber contactPhoneNumber) {
        FragmentActivity activity = getActivity();
        InitiationLogicContract create = this.initiationLogicFactory.create(this.callInitiator, activity, activity, new CallHelper(), null, Constants.Telemetry.CallInitScreenNames.CALL_BOTTOM_SHEET);
        if (contactPhoneNumber.getContactSource().equals(ContactSourceType.SkypeContact.getName())) {
            create.initiateSkypeCall(contactPhoneNumber.getPhoneNumber());
            dismiss();
            return;
        }
        if (CoboUtils.shouldShowPSTNForNonCOBORegion()) {
            if (!DeviceInfo.isPhone(getContext())) {
                create.initiateCoboCallFromActivity(this.commsId, this.name, contactPhoneNumber);
            } else {
                showPSTNDialer(contactPhoneNumber);
            }
        } else {
            create.initiateCoboCallFromActivity(this.commsId, this.name, contactPhoneNumber);
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    /* renamed from: getDialog */
    public Dialog mo3821getDialog() {
        return super.mo3821getDialog();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.cancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        DialogInterface.OnDismissListener onDismissListener = this.dismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    public void setOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        this.cancelListener = onCancelListener;
    }

    public void setOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
    }

    public void setPhoneNumbers(List<ContactPhoneNumber> list) {
        CallBottomSheetAdapter callBottomSheetAdapter = this.mCallAdapter;
        if (callBottomSheetAdapter != null) {
            callBottomSheetAdapter.setPhoneNumbers(list);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public void setupDialog(Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        this.contentView = View.inflate(getContext(), getLayout(), null);
        setUpPhoneNumbers();
        dialog.setContentView(this.contentView);
    }
}
