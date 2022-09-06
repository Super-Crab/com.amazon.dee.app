package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CACertificateDescription;
import com.amazonaws.services.iot.model.CertificateValidity;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class CACertificateDescriptionJsonMarshaller {
    private static CACertificateDescriptionJsonMarshaller instance;

    CACertificateDescriptionJsonMarshaller() {
    }

    public static CACertificateDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CACertificateDescriptionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CACertificateDescription cACertificateDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (cACertificateDescription.getCertificateArn() != null) {
            String certificateArn = cACertificateDescription.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (cACertificateDescription.getCertificateId() != null) {
            String certificateId = cACertificateDescription.getCertificateId();
            awsJsonWriter.name("certificateId");
            awsJsonWriter.value(certificateId);
        }
        if (cACertificateDescription.getStatus() != null) {
            String status = cACertificateDescription.getStatus();
            awsJsonWriter.name("status");
            awsJsonWriter.value(status);
        }
        if (cACertificateDescription.getCertificatePem() != null) {
            String certificatePem = cACertificateDescription.getCertificatePem();
            awsJsonWriter.name("certificatePem");
            awsJsonWriter.value(certificatePem);
        }
        if (cACertificateDescription.getOwnedBy() != null) {
            String ownedBy = cACertificateDescription.getOwnedBy();
            awsJsonWriter.name("ownedBy");
            awsJsonWriter.value(ownedBy);
        }
        if (cACertificateDescription.getCreationDate() != null) {
            Date creationDate = cACertificateDescription.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (cACertificateDescription.getAutoRegistrationStatus() != null) {
            String autoRegistrationStatus = cACertificateDescription.getAutoRegistrationStatus();
            awsJsonWriter.name("autoRegistrationStatus");
            awsJsonWriter.value(autoRegistrationStatus);
        }
        if (cACertificateDescription.getLastModifiedDate() != null) {
            Date lastModifiedDate = cACertificateDescription.getLastModifiedDate();
            awsJsonWriter.name("lastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (cACertificateDescription.getCustomerVersion() != null) {
            Integer customerVersion = cACertificateDescription.getCustomerVersion();
            awsJsonWriter.name("customerVersion");
            awsJsonWriter.value(customerVersion);
        }
        if (cACertificateDescription.getGenerationId() != null) {
            String generationId = cACertificateDescription.getGenerationId();
            awsJsonWriter.name("generationId");
            awsJsonWriter.value(generationId);
        }
        if (cACertificateDescription.getValidity() != null) {
            CertificateValidity validity = cACertificateDescription.getValidity();
            awsJsonWriter.name("validity");
            CertificateValidityJsonMarshaller.getInstance().marshall(validity, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
