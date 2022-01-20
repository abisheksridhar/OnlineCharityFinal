package org.capgemini.charityWebsite.CharityWebsite.Service;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;

import java.util.List;

public interface DonationService {
    boolean newDonation(Donation donation) throws Exception;

    boolean updateDonation(Long id, Donation donation) throws NotFoundException;

    List<DonationRequest> viewRequests();

    List<Donation> viewDonation();
}
