package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Bank {
    private  String accountName;
    private String bankName;
    private String accountNumber;
    private String IFSCCode;
}
