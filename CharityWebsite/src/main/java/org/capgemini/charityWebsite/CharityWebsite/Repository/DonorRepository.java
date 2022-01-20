package org.capgemini.charityWebsite.CharityWebsite.Repository;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donor;
import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.capgemini.charityWebsite.CharityWebsite.Entities.User;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor,Long> {
    Donor findByemailAddress(String emailId);
}
