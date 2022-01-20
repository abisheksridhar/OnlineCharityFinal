package org.capgemini.charityWebsite.CharityWebsite.Service_Implentations;

import org.capgemini.charityWebsite.CharityWebsite.Entities.*;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Repository.AdminRepo;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRepo;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonationRequestRepo;
import org.capgemini.charityWebsite.CharityWebsite.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private DonationRepo donationRepo;
    
    @Autowired
    private DonationRequestRepo donationRequestRepo;
    @Override
    public List<Donation> viewDonation() {
       return donationRepo.findAll();
    }

    @Override
    public List<DonationRequest> viewRequest() {
        return donationRequestRepo.findAll();
    }

    @Override
    public boolean adminLogin(loginData data) throws UserNotFoundException {
        boolean valid = false;
        Admin temp = adminRepo.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            if(temp.getPassword().equals(data.getUserPassword()))
                valid = true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
        return valid;
    }

    @Override
    public boolean approveRequest(Long id) throws NotFoundException {
        Optional<DonationRequest> request = donationRequestRepo.findById(id);
        if(request.isPresent())
        {
            DonationRequest donationRequest = request.get();
            donationRequest.setApproved(true);
            donationRequestRepo.save(donationRequest);
            return true;
        }
        else
        throw new NotFoundException("Request not found");
    }

    @Override
    public boolean updateUserInfo(Long id, Admin data) throws UserNotFoundException {
        if(adminRepo.findById(id).isPresent()){
            Admin temp =adminRepo.findById(id).get();
            temp.setUser(data.getUser());
            adminRepo.save(temp);
            return true;
        }
        else
            throw new UserNotFoundException("User doesn't Exist");
    }

    @Override
    public boolean updatePassword(loginData data) throws UserNotFoundException {
        Admin temp = adminRepo.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            temp.setPassword(data.getUserPassword());
            adminRepo.save(temp);
            return  true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
    }

    @Override
    public boolean adminRegistration(Admin data) throws UserExistException,Exception {
        Admin temp = adminRepo.findByemailAddress(data.getEmailAddress());
        if(Objects.isNull(temp)){
            try{
            adminRepo.save(data);}catch (Exception e){throw e;}
            return true;
        }
        else
            throw new UserExistException("User Already Exist");
    }
}
