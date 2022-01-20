package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
        name="adminDetails",
        uniqueConstraints = @UniqueConstraint(
                name = "UniqueEmail",
                columnNames = "emailAddress"
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adminId;

    @NotBlank(message = "cannot be empty")
    @Email
    private  String emailAddress;

    @NotBlank(message = "cannot be empty")
    @Size(min = 6)
    @Column(length = 50)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId",referencedColumnName = "userId")
    @Autowired
    @Valid
    private  User user;
}
