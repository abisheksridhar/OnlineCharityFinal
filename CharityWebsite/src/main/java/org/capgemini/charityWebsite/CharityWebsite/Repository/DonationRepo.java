package org.capgemini.charityWebsite.CharityWebsite.Repository;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation,Long> {

    List<Donation> findByrequestId(Long id);
}
