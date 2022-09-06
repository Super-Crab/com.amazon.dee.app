package com.amazon.deecomms.ndt;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.DeviceBottomSheetTarget;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.DeviceTargetingException;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rx.Subscriber;
/* loaded from: classes12.dex */
public class DeviceListSubscriber extends Subscriber<GetDevicesResponse> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceListSubscriber.class);
    public static boolean isProcessing = false;
    private WeakReference<Fragment> currentFragment;
    private final boolean filterDeviceListForChild;
    private final InitiationLogicContract initiationLogic;
    private String metricKey;
    private String recipientCommsID;
    private int requestCodePermission;

    public DeviceListSubscriber(InitiationLogicContract initiationLogicContract, String str, Fragment fragment, String str2, int i, boolean z) {
        this.initiationLogic = initiationLogicContract;
        this.recipientCommsID = str;
        this.currentFragment = new WeakReference<>(fragment);
        this.metricKey = str2;
        this.requestCodePermission = i;
        this.filterDeviceListForChild = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DeviceModel> filterNonDropinableDevices(@NonNull List<DeviceModel> list) {
        ArrayList arrayList = new ArrayList();
        for (DeviceModel deviceModel : list) {
            if (deviceModel.getDeviceStatus().getDeviceDropInAvailability() != DropInAvailability.OFF && deviceModel.getDeviceStatus().getDeviceCommsAvailability() == DeviceCommsAvailability.ON) {
                arrayList.add(deviceModel);
            }
        }
        return arrayList;
    }

    @Override // rx.Observer
    public void onCompleted() {
        isProcessing = false;
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        LOG.e("Error while doing targeted drop-in:", th);
        Fragment fragment = this.currentFragment.get();
        isProcessing = false;
        if (fragment != null && fragment.isVisible() && fragment.getActivity() != null && !fragment.getActivity().isFinishing() && !Utils.isOfflineDialogShown(fragment.getActivity(), true, fragment.getClass().getName(), AlertSource.newClassSource(DeviceListSubscriber.class.getName()))) {
            Utils.showDialog(fragment.getActivity(), R.string.error_title, R.string.dropin_failure_msg);
        }
        this.currentFragment = null;
    }

    @Override // rx.Observer
    public void onNext(@NonNull final GetDevicesResponse getDevicesResponse) {
        new AsyncTask<Void, Void, GetDevicesResponse>() { // from class: com.amazon.deecomms.ndt.DeviceListSubscriber.1
            GetDevicesResponse devicesResponse;

            {
                this.devicesResponse = getDevicesResponse;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public GetDevicesResponse doInBackground(Void... voidArr) {
                boolean z;
                Iterator<DeviceModel> it2 = this.devicesResponse.getDeviceModels().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    } else if (TextUtils.isEmpty(it2.next().getDeviceGruu())) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    try {
                        this.devicesResponse = CommsDaggerWrapper.getComponent().getDevicesSource().getServerData();
                    } catch (ServiceException | DeviceTargetingException | InterruptedException | NullPointerException e) {
                        DeviceListSubscriber.LOG.e("Error while fetching deviceModels from server", e);
                        SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallOrigin(Call.Side.Local).withRequestId(CommsDaggerWrapper.getComponent().getDevicesSource().getRequestId()).withSource(SetupCallHelper.Source.SipCallPreparation);
                        SetupCallHelper.ResultType resultType = SetupCallHelper.ResultType.EXPECTED;
                        Integer valueOf = Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode());
                        StringBuilder outline1 = GeneratedOutlineSupport.outline1("getDevices failed with error: ");
                        outline1.append(e.getMessage());
                        SetupCallHelper.recordInitiationMetrics(null, null, resultType, valueOf, withSource.withReason(outline1.toString()));
                    }
                }
                return this.devicesResponse;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(GetDevicesResponse getDevicesResponse2) {
                List<DeviceModel> deviceModels;
                Fragment fragment = (Fragment) DeviceListSubscriber.this.currentFragment.get();
                if (fragment != null && fragment.getActivity() != null && !fragment.getActivity().isFinishing()) {
                    if (getDevicesResponse2 == null) {
                        getDevicesResponse2 = getDevicesResponse;
                    }
                    getDevicesResponse2.setDeviceModels(DeviceListSubscriber.this.filterNonDropinableDevices(getDevicesResponse2.getDeviceModels()));
                    if (DeviceListSubscriber.this.filterDeviceListForChild) {
                        deviceModels = Utils.getDevicesForChildAccount(getDevicesResponse2.getDeviceModels(), DeviceListSubscriber.this.recipientCommsID);
                    } else {
                        deviceModels = getDevicesResponse2.getDeviceModels();
                    }
                    DeviceBottomSheet newInstance = DeviceBottomSheet.newInstance(DeviceListSubscriber.this.initiationLogic, DeviceListSubscriber.this.recipientCommsID, DeviceListSubscriber.this.metricKey, DeviceListSubscriber.this.requestCodePermission, 0, deviceModels.size(), DeviceListSubscriber.this.filterDeviceListForChild);
                    if (fragment instanceof DeviceBottomSheetTarget) {
                        ((DeviceBottomSheetTarget) fragment).setBottomSheetDialogFragment(newInstance);
                        newInstance.setTargetFragment(fragment, 1);
                    }
                    if (deviceModels.size() <= 1) {
                        newInstance.deviceSelected(deviceModels.isEmpty() ? null : deviceModels.get(0), null);
                    } else {
                        FragmentTransaction beginTransaction = fragment.getFragmentManager().beginTransaction();
                        beginTransaction.add(newInstance, newInstance.getTag());
                        beginTransaction.commitAllowingStateLoss();
                    }
                }
                DeviceListSubscriber.this.currentFragment = null;
            }
        }.execute(new Void[0]);
    }
}
