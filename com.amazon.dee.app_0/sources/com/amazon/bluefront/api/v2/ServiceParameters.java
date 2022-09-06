package com.amazon.bluefront.api.v2;

import com.amazon.bluefront.api.common.Customer;
import com.amazon.bluefront.api.common.Device;
import java.util.Map;
/* loaded from: classes11.dex */
public class ServiceParameters implements Comparable<ServiceParameters> {
    private Customer mCustomer;
    private Device mDevice;
    private Map<String, String> mMetadata;
    private String mRequestId;
    private boolean mSaveRecords;

    private static int compareObjects(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof Comparable) {
                int compareTo = ((Comparable) obj).compareTo(obj2);
                if (compareTo == 0) {
                    return 0;
                }
                return compareTo;
            } else if (obj.equals(obj2)) {
                return 0;
            } else {
                int hashCode = obj.hashCode();
                int hashCode2 = obj2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                return hashCode > hashCode2 ? 1 : 0;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ServiceParameters) && compareTo((ServiceParameters) obj) == 0;
    }

    public Customer getCustomer() {
        return this.mCustomer;
    }

    public Device getDevice() {
        return this.mDevice;
    }

    public Map<String, String> getMetadata() {
        return this.mMetadata;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getRequestId() == null ? 0 : getRequestId().hashCode()) + 1 + (getMetadata() == null ? 0 : getMetadata().hashCode()) + (getDevice() == null ? 0 : getDevice().hashCode()) + (getCustomer() == null ? 0 : getCustomer().hashCode());
        if (isSaveRecords() != null) {
            i = isSaveRecords().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSaveRecords() {
        return Boolean.valueOf(this.mSaveRecords);
    }

    public void setCustomer(Customer customer) {
        this.mCustomer = customer;
    }

    public void setDevice(Device device) {
        this.mDevice = device;
    }

    public void setMetadata(Map<String, String> map) {
        this.mMetadata = map;
    }

    public void setRequestId(String str) {
        this.mRequestId = str;
    }

    public void setSaveRecords(Boolean bool) {
        this.mSaveRecords = bool.booleanValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(ServiceParameters serviceParameters) {
        if (serviceParameters == null) {
            return -1;
        }
        if (serviceParameters == this) {
            return 0;
        }
        int compareObjects = compareObjects(getRequestId(), serviceParameters.getRequestId());
        if (compareObjects != 0) {
            return compareObjects;
        }
        int compareObjects2 = compareObjects(getMetadata(), serviceParameters.getMetadata());
        if (compareObjects2 != 0) {
            return compareObjects2;
        }
        int compareObjects3 = compareObjects(getDevice(), serviceParameters.getDevice());
        if (compareObjects3 != 0) {
            return compareObjects3;
        }
        int compareObjects4 = compareObjects(getCustomer(), serviceParameters.getCustomer());
        if (compareObjects4 != 0) {
            return compareObjects4;
        }
        int compareObjects5 = compareObjects(isSaveRecords(), serviceParameters.isSaveRecords());
        if (compareObjects5 == 0) {
            return 0;
        }
        return compareObjects5;
    }
}
