package org.capgemini.charityWebsite.CharityWebsite.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
//template for all users
@Entity//to define a class as entity
@Table(
        name="Users"
)//used to define table properties
@NoArgsConstructor//annotation is to generate no argument constructor
@Data// for getter a,setter,to string methods
@AllArgsConstructor// for all argument constructor
public class User {
        @Id//primary in a table
        @SequenceGenerator(
                name = "userIdGenerator",
                sequenceName = "userIdGenerator",
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userIdGenerator")//primary key generator
        private  Long userId;

        @NotBlank(message = "cannot be empty")
        @Column(length = 50, nullable = false)
        private String firstName;

        @Column(length = 50)
        private  String lastName;

        @Embedded // to embed class
        private  Address address;

        @Column(length = 20)
        private String contactNumber;

        public User(String firstName, String lastName, Address address, String contactNumber) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.address = address;
                this.contactNumber = contactNumber;
        }
}
