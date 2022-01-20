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
        name="DonorSpecificDetails",
        uniqueConstraints = @UniqueConstraint(
                name = "UniqueUserName",
                columnNames = "emailAddress"
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donor {
    @Id
    @SequenceGenerator(
            name = "donorIdGenerator",
            sequenceName  = "donorIdGenerator",
            initialValue = 100000,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "donorIdGenerator")
    private Long donorId;

    @NotBlank(message = "cannot be empty")
    @Email
    @Column(nullable = false,updatable = false,length = 50)
    private String emailAddress;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = false,length = 50)
    private String password;

    @NotBlank(message = "cannot be empty")
    @Column(length = 50)
    private String trustName;

    @Column(length = 20)
    private String donorOccupation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName = "userId")
    @Autowired
    @Valid
    private User user;

}
