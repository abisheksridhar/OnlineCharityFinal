package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
        name = "NgoUserDetails",
        uniqueConstraints = @UniqueConstraint(
                name="UniqueEmailAndLicense",
                columnNames = {"emailAddress","licenseNumber"}
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ngo {
    @Id
    @SequenceGenerator(
            name="NgoIdGenerator",
            sequenceName="NgoIdGenerator",
            initialValue = 100000,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "NgoIdGenerator")
    private  Long NgoId;

    @NotBlank(message = "cannot be empty")
    @Email
    @Column(nullable = false,updatable = false,length = 50)
    private String emailAddress;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = false,length = 50)
    private String password;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = false,length = 20)
    private String licenseNumber;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = false,length = 50)
    private String organisationName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId",referencedColumnName = "userId")
    @Autowired
    @Valid
    private  User user;

}
