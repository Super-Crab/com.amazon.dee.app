package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class TransferData implements Serializable {
    private Date acceptDate;
    private Date rejectDate;
    private String rejectReason;
    private Date transferDate;
    private String transferMessage;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TransferData)) {
            return false;
        }
        TransferData transferData = (TransferData) obj;
        if ((transferData.getTransferMessage() == null) ^ (getTransferMessage() == null)) {
            return false;
        }
        if (transferData.getTransferMessage() != null && !transferData.getTransferMessage().equals(getTransferMessage())) {
            return false;
        }
        if ((transferData.getRejectReason() == null) ^ (getRejectReason() == null)) {
            return false;
        }
        if (transferData.getRejectReason() != null && !transferData.getRejectReason().equals(getRejectReason())) {
            return false;
        }
        if ((transferData.getTransferDate() == null) ^ (getTransferDate() == null)) {
            return false;
        }
        if (transferData.getTransferDate() != null && !transferData.getTransferDate().equals(getTransferDate())) {
            return false;
        }
        if ((transferData.getAcceptDate() == null) ^ (getAcceptDate() == null)) {
            return false;
        }
        if (transferData.getAcceptDate() != null && !transferData.getAcceptDate().equals(getAcceptDate())) {
            return false;
        }
        if ((transferData.getRejectDate() == null) ^ (getRejectDate() == null)) {
            return false;
        }
        return transferData.getRejectDate() == null || transferData.getRejectDate().equals(getRejectDate());
    }

    public Date getAcceptDate() {
        return this.acceptDate;
    }

    public Date getRejectDate() {
        return this.rejectDate;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public Date getTransferDate() {
        return this.transferDate;
    }

    public String getTransferMessage() {
        return this.transferMessage;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getTransferMessage() == null ? 0 : getTransferMessage().hashCode()) + 31) * 31) + (getRejectReason() == null ? 0 : getRejectReason().hashCode())) * 31) + (getTransferDate() == null ? 0 : getTransferDate().hashCode())) * 31) + (getAcceptDate() == null ? 0 : getAcceptDate().hashCode())) * 31;
        if (getRejectDate() != null) {
            i = getRejectDate().hashCode();
        }
        return hashCode + i;
    }

    public void setAcceptDate(Date date) {
        this.acceptDate = date;
    }

    public void setRejectDate(Date date) {
        this.rejectDate = date;
    }

    public void setRejectReason(String str) {
        this.rejectReason = str;
    }

    public void setTransferDate(Date date) {
        this.transferDate = date;
    }

    public void setTransferMessage(String str) {
        this.transferMessage = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTransferMessage() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transferMessage: ");
            outline1072.append(getTransferMessage());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRejectReason() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("rejectReason: ");
            outline1073.append(getRejectReason());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTransferDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("transferDate: ");
            outline1074.append(getTransferDate());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAcceptDate() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("acceptDate: ");
            outline1075.append(getAcceptDate());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRejectDate() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("rejectDate: ");
            outline1076.append(getRejectDate());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TransferData withAcceptDate(Date date) {
        this.acceptDate = date;
        return this;
    }

    public TransferData withRejectDate(Date date) {
        this.rejectDate = date;
        return this;
    }

    public TransferData withRejectReason(String str) {
        this.rejectReason = str;
        return this;
    }

    public TransferData withTransferDate(Date date) {
        this.transferDate = date;
        return this;
    }

    public TransferData withTransferMessage(String str) {
        this.transferMessage = str;
        return this;
    }
}
