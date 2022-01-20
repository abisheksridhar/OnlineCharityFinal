package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginData {

    private String UserName;
    private String UserPassword;
}
