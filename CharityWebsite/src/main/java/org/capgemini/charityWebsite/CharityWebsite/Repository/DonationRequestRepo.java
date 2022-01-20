package org.capgemini.charityWebsite.CharityWebsite.Repository;

import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestRepo extends JpaRepository<DonationRequest,Long> {

}
