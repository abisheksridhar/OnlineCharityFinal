package org.capgemini.charityWebsite.CharityWebsite.Repository;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, Long> {
    Ngo findByemailAddress(String emailAddress);
}
