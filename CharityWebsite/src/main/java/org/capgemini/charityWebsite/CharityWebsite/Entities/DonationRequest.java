package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(
       name = "DonationRequests"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequest {

    @Id
    @SequenceGenerator(
            name = "requestIdGenerator",
            sequenceName = "requestId",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "requestIdGenerator")
    private Long requestId;

    @NotBlank
    private String purposeOfDonation;

    @NotBlank(message = "cannot be empty")
    private String ngoId;

    private String Details;

    private double amountNeeded;

    @Embedded
    private Bank beneficiaryBankDetails;

    private MultipartFile[] document;

    private boolean approved ;

}
