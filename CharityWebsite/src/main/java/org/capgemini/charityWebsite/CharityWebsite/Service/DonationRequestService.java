package org.capgemini.charityWebsite.CharityWebsite.Service;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DonationRequestService {
    boolean newDonationRequest(DonationRequest donationRequest);

    boolean updateDonationRequest(Long id, DonationRequest donationRequest) throws NotFoundException;

    List<Donation> viewDonation(Long Id);

    List<DonationRequest> viewRequests();

    DonationRequest getRequestById(Long id) throws NotFoundException;

    String uploadDocument(MultipartFile[] doc, Long reqId);

    boolean deleteRequest(Long id) throws NotFoundException;
}
