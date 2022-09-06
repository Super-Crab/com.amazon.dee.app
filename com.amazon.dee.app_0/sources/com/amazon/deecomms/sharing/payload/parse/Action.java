package com.amazon.deecomms.sharing.payload.parse;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.sharing.payload.parse.enums.EntryDataType;
import com.amazon.deecomms.sharing.payload.parse.enums.OperatingSystem;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class Action {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, Action.class);
    @SerializedName("target")
    private ArrayList<Target> targets = new ArrayList<>();

    public static Action withMobileDeepLink(String str, String str2) {
        Action action = new Action();
        ArrayList<Target> arrayList = new ArrayList<>();
        Target target = new Target();
        EntryData entryData = new EntryData();
        URLHolder uRLHolder = new URLHolder();
        ActionApplication actionApplication = new ActionApplication();
        Target target2 = new Target();
        EntryData entryData2 = new EntryData();
        URLHolder uRLHolder2 = new URLHolder();
        ActionApplication actionApplication2 = new ActionApplication();
        uRLHolder.setUrl(str);
        uRLHolder.setOrigin(str2);
        entryData.setUrl(uRLHolder);
        entryData.setType(EntryDataType.MOBILE_ENTRY_DATA);
        target.setEntryData(entryData);
        actionApplication.setOperatingSystem(OperatingSystem.Android);
        target.setActionApplication(actionApplication);
        uRLHolder2.setUrl(str);
        uRLHolder2.setOrigin(str2);
        entryData2.setUrl(uRLHolder2);
        entryData2.setType(EntryDataType.MOBILE_ENTRY_DATA);
        target2.setEntryData(entryData2);
        actionApplication2.setOperatingSystem(OperatingSystem.iOS);
        target2.setActionApplication(actionApplication2);
        arrayList.add(target);
        arrayList.add(target2);
        action.setTargets(arrayList);
        return action;
    }

    public static Action withNoTargets() {
        Action action = new Action();
        action.setTargets(new ArrayList<>());
        return action;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Action.class == obj.getClass()) {
            return Objects.equals(this.targets, ((Action) obj).targets);
        }
        return false;
    }

    public EntryData getAndroidEntryData() {
        ArrayList<Target> arrayList = this.targets;
        if (arrayList == null) {
            return null;
        }
        Iterator<Target> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Target next = it2.next();
            if (next == null) {
                LOG.d("[Content-Sharing] Target Skipped: The entry in the array is not an object");
            } else {
                ActionApplication actionApplication = next.getActionApplication();
                if (actionApplication == null) {
                    LOG.d("[Content-Sharing] Target Skipped: The entry did not contain an <actionApplication> key");
                } else if (actionApplication.getOperatingSystem() != null && Objects.equals(actionApplication.getOperatingSystem().getSerializedName(), OperatingSystem.Android.getSerializedName())) {
                    EntryData entryData = next.getEntryData();
                    if (entryData == null) {
                        LOG.d("[Content-Sharing] Target skipped: The android entry did not contain an <entryData> key");
                    } else if (entryData.getType() != null && Objects.equals(EntryDataType.MOBILE_ENTRY_DATA.getSerializedName(), entryData.getType().getSerializedName())) {
                        return entryData;
                    } else {
                        LOG.d("[Content-Sharing] Target skipped: The android entryData did not containa <type> key matching MobileEntryData");
                    }
                } else {
                    LOG.d("[Content-Sharing] Target skipped: <actionApplication.operatingSystem> was not android");
                }
            }
        }
        return null;
    }

    public ArrayList<Target> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        return Objects.hash(this.targets);
    }

    public void setTargets(ArrayList<Target> arrayList) {
        this.targets = arrayList;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Action{targets=");
        outline1.append(this.targets);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }
}
