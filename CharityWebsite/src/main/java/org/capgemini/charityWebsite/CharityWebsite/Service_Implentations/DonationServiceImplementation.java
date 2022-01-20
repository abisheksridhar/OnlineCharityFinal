package org.capgemini.charityWebsite.CharityWebsite.Service_Implentations;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRepo;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRequestRepo;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImplementation implements DonationService {

    @Autowired
    private DonationRepo Repo;

    @Autowired
    private DonationRequestRepo reqRepo;

    @Override
    public boolean newDonation(Donation donation) throws Exception {
        Repo.saveAndFlush(donation);
        return true;
    }

    @Override
    public boolean updateDonation(Long id , Donation donation) throws NotFoundException {
        if(Repo.findById(id).isPresent())
        {
            Donation temp = Repo.findById(id).get();
            Repo.save(temp);
            return  true;
        }
        throw new NotFoundException("Id not found");
    }

    @Override
    public List<DonationRequest> viewRequests() {
        return reqRepo.findAll();
    }

    @Override
    public List<Donation> viewDonation() {
        return Repo.findAll();
    }
}
