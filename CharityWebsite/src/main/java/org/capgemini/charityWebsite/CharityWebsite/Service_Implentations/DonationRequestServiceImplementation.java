package org.capgemini.charityWebsite.CharityWebsite.Service_Implentations;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRepo;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRequestRepo;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DonationRequestServiceImplementation implements DonationRequestService {

    @Autowired
    private DonationRequestRepo donationRequestRepo;

    @Autowired
    private DonationRepo donationRepo;
    @Override
    public boolean newDonationRequest(DonationRequest donationRequest) {
        donationRequestRepo.save(donationRequest);
        return true;
    }

    @Override
    public boolean updateDonationRequest(Long id, DonationRequest donationRequest) throws NotFoundException {

        if(donationRequestRepo.findById(id).isPresent())
        {
            DonationRequest temp = donationRequestRepo.findById(id).get();
            donationRequestRepo.save(temp);
            return  true;
        }
        throw new NotFoundException("Id not found");

    }

    @Override
    public List<Donation> viewDonation(Long Id) {

        return donationRepo.findByrequestId(Id);
    }

    @Override
    public List<DonationRequest> viewRequests() {
        return donationRequestRepo.findAll();
    }

    @Override
    public DonationRequest getRequestById(Long id) throws NotFoundException {
        Optional<DonationRequest> req = donationRequestRepo.findById(id);
        if(req.isPresent())
            return (req.get());
        throw new NotFoundException("Cannot find the request");
    }

    @Override
    public String uploadDocument(MultipartFile[] doc, Long reqId) {
        Optional<DonationRequest> nOptional = donationRequestRepo.findById(reqId);
        if(nOptional.isPresent())
        {
            DonationRequest Request = nOptional.get();
            Request.setDocument(doc);
            donationRequestRepo.save(Request);
            return "Document added Successfully";
        }
        return "Invalid request id ";

    }

    @Override
    public boolean deleteRequest(Long id) throws NotFoundException {
       Optional<DonationRequest> temp = donationRequestRepo.findById(id);
       if(Objects.isNull(temp))
       {
           throw new NotFoundException("Id not found");
       }
       donationRequestRepo.deleteById(id);
       return true;
    }

}
