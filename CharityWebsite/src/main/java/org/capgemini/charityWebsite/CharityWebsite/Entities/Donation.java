package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @SequenceGenerator(name="donationSequenceGenerator",sequenceName ="donationSequenceGenerator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "donationSequenceGenerator")
    private  Long donationId;


    private Long donorId;

    //@NotBlank(message = "cannot be empty")
    private  Long requestId;



    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date donationDate ;


    private double amountDonated;


    @Embedded
    private Bank bankDetails;
}
