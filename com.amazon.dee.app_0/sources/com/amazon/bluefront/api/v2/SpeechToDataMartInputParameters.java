package com.amazon.bluefront.api.v2;
/* loaded from: classes11.dex */
public class SpeechToDataMartInputParameters implements Comparable<SpeechToDataMartInputParameters> {
    private ServiceParameters mServiceParams;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SpeechToDataMartInputParameters) && compareTo((SpeechToDataMartInputParameters) obj) == 0;
    }

    public ServiceParameters getServiceParams() {
        return this.mServiceParams;
    }

    public int hashCode() {
        return (getServiceParams() == null ? 0 : getServiceParams().hashCode()) + 1;
    }

    public void setServiceParams(ServiceParameters serviceParameters) {
        this.mServiceParams = serviceParameters;
    }

    @Override // java.lang.Comparable
    public int compareTo(SpeechToDataMartInputParameters speechToDataMartInputParameters) {
        ServiceParameters serviceParams;
        ServiceParameters serviceParams2;
        if (speechToDataMartInputParameters == null) {
            return -1;
        }
        if (speechToDataMartInputParameters != this && (serviceParams = getServiceParams()) != (serviceParams2 = speechToDataMartInputParameters.getServiceParams())) {
            if (serviceParams == null) {
                return -1;
            }
            if (serviceParams2 == null) {
                return 1;
            }
            int compareTo = serviceParams.compareTo(serviceParams2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
}
