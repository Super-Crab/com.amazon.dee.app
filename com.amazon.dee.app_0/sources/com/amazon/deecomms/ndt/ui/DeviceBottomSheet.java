package com.amazon.deecomms.ndt.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.ndt.ui.ContactAdapter;
import com.amazon.deecomms.calling.ndt.ui.ContactSelectionListener;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.ui.CustomHeightBottomSheetDialog;
import com.amazon.deecomms.common.ui.DividerItemDecoration;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.contacts.util.ContactNameHelper;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.ndt.state.DeviceState;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DeviceBottomSheet extends BottomSheetDialogFragment implements DeviceSelectionListener, ContactSelectionListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceBottomSheet.class);
    private DialogInterface.OnCancelListener cancelListener;
    private View contentView;
    private PresenceDocument currentContact;
    private DeviceModel currentDevice;
    private DialogInterface.OnDismissListener dismissListener;
    private InitiationLogicContract initiationLogic;
    @Inject
    CapabilitiesManager mCapabilitiesManager;
    private ContactAdapter mContactAdapter;
    private DeviceAdapter mDeviceAdapter;
    private List<DeviceModel> mDevices;
    private boolean mIsThemedUIEnabled;
    private List<PresenceDocument> mPresenceDocuments;
    private String pageSource;
    private int permissionRequestCode;
    private String recipientID;
    private boolean showContacts = false;
    private boolean showDevices = true;
    private boolean useDevicesForChildAccount;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class NoVerticalScrollLinearLayoutManager extends LinearLayoutManager {
        NoVerticalScrollLinearLayoutManager(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return false;
        }
    }

    private void dismissBottomSheet(boolean z) {
        String[] checkPermissions;
        if (getBottomSheetBehavior() == null || getTargetFragment() == null) {
            return;
        }
        if (z) {
            checkPermissions = PermissionsHelper.checkPermissions(getTargetFragment().getContext(), PermissionsHelper.getPermissionListForVideoCalling());
        } else {
            checkPermissions = PermissionsHelper.checkPermissions(getTargetFragment().getContext(), PermissionsHelper.getPermissionListForAudio());
        }
        if (checkPermissions.length != 0) {
            return;
        }
        getBottomSheetBehavior().setHideable(true);
        getBottomSheetBehavior().setState(5);
        this.currentDevice = null;
        this.currentContact = null;
        if (getTargetFragment() == null) {
            return;
        }
        setTargetFragment(null, 1);
    }

    private boolean findOtherPresentDevices(DeviceModel deviceModel) {
        for (DeviceModel deviceModel2 : getMemoryDevices()) {
            if (!deviceModel2.equals(deviceModel) && deviceModel2.getDeviceStatus().getActiveState() == ActiveState.ACTIVE) {
                return true;
            }
        }
        return false;
    }

    private BottomSheetBehavior<?> getBottomSheetBehavior() {
        CustomHeightBottomSheetDialog mo3821getDialog = mo3821getDialog();
        if (mo3821getDialog != null) {
            return mo3821getDialog.getBottomSheetBehavior();
        }
        return null;
    }

    @NonNull
    private List<DeviceModel> getMemoryDevices() {
        GetDevicesResponse memoryData = CommsDaggerWrapper.getComponent().getDevicesSource().getMemoryData();
        if (memoryData != null) {
            if (this.useDevicesForChildAccount) {
                return Utils.getDevicesForChildAccount(memoryData.getDeviceModels(), this.recipientID);
            }
            return memoryData.getDeviceModels();
        }
        return new ArrayList();
    }

    public static DeviceBottomSheet newInstance(InitiationLogicContract initiationLogicContract, String str, String str2, int i, int i2, int i3, boolean z) {
        DeviceBottomSheet deviceBottomSheet = new DeviceBottomSheet();
        deviceBottomSheet.initiationLogic = initiationLogicContract;
        deviceBottomSheet.recipientID = str;
        deviceBottomSheet.pageSource = str2;
        deviceBottomSheet.permissionRequestCode = i;
        boolean z2 = true;
        deviceBottomSheet.showContacts = i2 > 0;
        if (i3 <= 0) {
            z2 = false;
        }
        deviceBottomSheet.showDevices = z2;
        deviceBottomSheet.useDevicesForChildAccount = z;
        return deviceBottomSheet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordClickStreamCounterMetric(@NonNull String str, @NonNull HashMap<String, Object> hashMap) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        Map<String, Object> metadata = generateClickstream.getMetadata();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            metadata.put(entry.getKey().toString(), entry.getValue());
        }
        metadata.put("source", this.pageSource);
        MetricsHelper.recordSingleOccurrence(generateClickstream);
    }

    private void setupContacts() {
        View view = this.contentView;
        if (view == null) {
            return;
        }
        view.findViewById(R.id.contacts_list_portion).setVisibility(0);
        RecyclerView recyclerView = (RecyclerView) this.contentView.findViewById(R.id.contacts_targeting_list);
        recyclerView.setLayoutManager(new NoVerticalScrollLinearLayoutManager(getActivity()));
        this.mContactAdapter = new ContactAdapter(this);
        recyclerView.setAdapter(this.mContactAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
    }

    private void setupDevices() {
        View view = this.contentView;
        if (view == null) {
            return;
        }
        view.findViewById(R.id.device_list_portion).setVisibility(0);
        RecyclerView recyclerView = (RecyclerView) this.contentView.findViewById(R.id.device_targeting_list);
        recyclerView.setLayoutManager(new NoVerticalScrollLinearLayoutManager(getActivity()));
        this.mDeviceAdapter = new DeviceAdapter(this, getMemoryDevices(), this.mIsThemedUIEnabled);
        recyclerView.setAdapter(this.mDeviceAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
    }

    private void showEndCallAndDismissBottomSheet(@NonNull Fragment fragment, boolean z) {
        showEndCallScreen(fragment);
        dismissBottomSheet(z);
    }

    @Override // com.amazon.deecomms.calling.ndt.ui.ContactSelectionListener
    public void contactSelected(@NonNull PresenceDocument presenceDocument) {
        ContactName contactName = new ContactName(presenceDocument.getContactName(), presenceDocument.getContactLastName());
        this.currentContact = presenceDocument;
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && targetFragment.getActivity() != null && !targetFragment.getActivity().isFinishing() && !targetFragment.getActivity().isDestroyed()) {
            this.initiationLogic.initiateContactDropIn(presenceDocument.getContactHomegroupId(), ContactUtils.getFullName(contactName));
            if (getBottomSheetBehavior() != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("errorSource", Boolean.toString(presenceDocument.isActive()));
                recordClickStreamCounterMetric(MetricKeys.CALL_TARGETING_CONTACT, hashMap);
            }
            dismissBottomSheet(true);
            return;
        }
        LOG.w("The activity is finishing or destroyed");
    }

    @Override // com.amazon.deecomms.ndt.ui.DeviceSelectionListener
    public void deviceSelected(DeviceModel deviceModel, DeviceState deviceState) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && targetFragment.getActivity() != null && !targetFragment.getActivity().isFinishing() && !targetFragment.getActivity().isDestroyed()) {
            this.currentDevice = deviceModel;
            boolean z = true;
            if (deviceModel != null) {
                if (deviceState == null) {
                    deviceState = CommsDaggerWrapper.getComponent().getDeviceStateManager().getStateForCurrentDevice(deviceModel);
                }
                boolean isVideoEnabled = deviceModel.getDeviceStatus().isVideoEnabled();
                CommsLogger commsLogger = LOG;
                commsLogger.i("Drop-in device is video enabled :" + isVideoEnabled);
                if (deviceState.isCanBeCalled()) {
                    this.initiationLogic.initiateTargetedDropIn(this.recipientID, this.pageSource, deviceModel.getDeviceGruu(), deviceModel.getDeviceName(), deviceModel.getDeviceStatus().isVideoEnabled(), false);
                    if (getBottomSheetBehavior() != null) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        if (deviceModel.getDeviceStatus().getActiveState() != ActiveState.ACTIVE) {
                            z = false;
                        }
                        hashMap.put("errorSource", Boolean.toString(z));
                        hashMap.put("size", Integer.valueOf(getMemoryDevices().size()));
                        hashMap.put("requestId", Boolean.toString(findOtherPresentDevices(deviceModel)));
                        recordClickStreamCounterMetric(MetricKeys.CALL_TARGETING_DEVICE, hashMap);
                    }
                    dismissBottomSheet(isVideoEnabled);
                    return;
                }
                showEndCallScreen(targetFragment);
                dismissBottomSheet(isVideoEnabled);
                return;
            }
            showEndCallScreen(targetFragment);
            dismissBottomSheet(true);
            return;
        }
        LOG.w("The activity is finishing or destroyed");
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
        CommsDaggerWrapper.getComponent().inject(this);
        this.mIsThemedUIEnabled = this.mCapabilitiesManager.isThemedUIEnabled();
        super.onCreate(bundle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        return new CustomHeightBottomSheetDialog(getActivity(), getTheme()) { // from class: com.amazon.deecomms.ndt.ui.DeviceBottomSheet.1
            @Override // com.amazon.deecomms.common.ui.CustomHeightBottomSheetDialog, android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                super.onShow(dialogInterface);
                DeviceBottomSheet.this.recordClickStreamCounterMetric(MetricKeys.SCREEN_TARGETED_CALL_SHOWN, new HashMap());
            }
        };
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        DialogInterface.OnDismissListener onDismissListener = this.dismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == this.permissionRequestCode) {
            DeviceModel deviceModel = this.currentDevice;
            if (deviceModel != null) {
                deviceSelected(deviceModel, null);
                return;
            }
            PresenceDocument presenceDocument = this.currentContact;
            if (presenceDocument != null) {
                contactSelected(presenceDocument);
                return;
            }
            LOG.w("Neither device or contact was selected after permission result");
            Fragment targetFragment = getTargetFragment();
            if (targetFragment != null && targetFragment.getActivity() != null && !targetFragment.getActivity().isFinishing() && !targetFragment.getActivity().isDestroyed()) {
                showEndCallScreen(targetFragment);
            } else {
                LOG.w("The activity is finishing or destroyed");
            }
        }
    }

    public void setDevices(@NonNull List<DeviceModel> list) {
        if (ContactsProviderUtils.canIDropInOnHomeGroup(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("DeviceBottomSheet.setDevices", false))) {
            this.mDevices = list;
            DeviceAdapter deviceAdapter = this.mDeviceAdapter;
            if (deviceAdapter != null) {
                deviceAdapter.setDeviceList(this.mDevices);
                this.mDevices = null;
                return;
            }
            setupDevices();
        }
    }

    public void setOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        this.cancelListener = onCancelListener;
    }

    public void setOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
    }

    public void setPresenceDocuments(List<PresenceDocument> list) {
        this.mPresenceDocuments = list;
        ContactAdapter contactAdapter = this.mContactAdapter;
        if (contactAdapter != null) {
            contactAdapter.setPresenceDocuments(this.mPresenceDocuments);
        } else if (list.size() > 0) {
            this.showContacts = true;
            setupContacts();
            ContactAdapter contactAdapter2 = this.mContactAdapter;
            if (contactAdapter2 == null) {
                return;
            }
            contactAdapter2.setPresenceDocuments(this.mPresenceDocuments);
        }
        this.mPresenceDocuments = null;
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public void setupDialog(Dialog dialog, int i) {
        View inflate;
        super.setupDialog(dialog, i);
        if (this.mIsThemedUIEnabled) {
            inflate = View.inflate(getContext(), R.layout.fiesta_drop_in_targeting, null);
        } else {
            inflate = View.inflate(getContext(), R.layout.drop_in_targeting, null);
        }
        this.contentView = inflate;
        if (this.showDevices) {
            setupDevices();
            List<DeviceModel> list = this.mDevices;
            if (list != null) {
                setDevices(list);
            }
        }
        if (this.showContacts) {
            setupContacts();
            List<PresenceDocument> list2 = this.mPresenceDocuments;
            if (list2 != null) {
                setPresenceDocuments(list2);
            }
        }
        dialog.setContentView(this.contentView);
    }

    public void showEndCallScreen(@NonNull final Fragment fragment) {
        final AlertSource newClassSource = AlertSource.newClassSource(DeviceBottomSheet.class.getName());
        new AsyncTask<Void, Void, String>() { // from class: com.amazon.deecomms.ndt.ui.DeviceBottomSheet.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                try {
                    ContactEntry fetchContactEntryForCommId = ContactsProviderUtils.fetchContactEntryForCommId(fragment.getContext(), DeviceBottomSheet.this.recipientID);
                    if (fetchContactEntryForCommId.isChild()) {
                        return ContactUtils.getFullName(fetchContactEntryForCommId.getContactName());
                    }
                    return ContactUtils.getFullName(ContactsProviderUtils.fetchContactEntryForCommId(fragment.getContext(), CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("DeviceBottomSheet.showEndCallScreen", false)).getContactName());
                } catch (Exception e) {
                    DeviceBottomSheet.LOG.e("Unable to retrieve contact info for showEndCallScreen", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                CallHelper withRecipientID = new CallHelper().withRecipientID(DeviceBottomSheet.this.recipientID);
                if (TextUtils.isEmpty(str)) {
                    str = ContactUtils.getFullName(ContactNameHelper.getActiveHomeGroupContactName(fragment.getContext()));
                }
                withRecipientID.withDisplayTitleName(str).withDropInCall(true).withVideoCall(true).withDeviceGruu(null).withPageSourceName(DeviceBottomSheet.this.pageSource).withCallProvider(CallProvider.A2A).withAlertSource(newClassSource).withNDTCall(true).makeACall(fragment.getActivity());
            }
        }.execute(new Void[0]);
    }

    @Override // androidx.fragment.app.DialogFragment
    /* renamed from: getDialog  reason: collision with other method in class */
    public CustomHeightBottomSheetDialog mo3821getDialog() {
        return (CustomHeightBottomSheetDialog) super.mo3821getDialog();
    }
}
