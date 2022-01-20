package org.capgemini.charityWebsite.CharityWebsite.Repository;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Admin;
import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin findByemailAddress(String s);
}
