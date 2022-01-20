package org.capgemini.charityWebsite.CharityWebsite.Service_Implentations;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donor;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Repository.DonorRepository;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class DonorServiceImplementation implements DonorService {

    @Autowired
    private DonorRepository donorRepository;
    @Override
    public boolean donorRegistration(Donor donor) throws UserExistException {
            Donor temp = donorRepository.findByemailAddress(donor.getEmailAddress());
            if(!Objects.isNull(temp))
                throw new UserExistException("User Already Exist");
            else
                donorRepository.save(donor);
        return true;
    }

    public boolean updatePassword(loginData data) throws UserNotFoundException
    {
        Donor temp = donorRepository.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            temp.setPassword(data.getUserPassword());
            donorRepository.save(temp);
            return  true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
    }

    public boolean updateUserInfo(Long donorId,Donor donor) throws UserNotFoundException
    {
        if(donorRepository.findById(donorId).isPresent()){
            Donor temp = donorRepository.findById(donorId).get();
            temp.setUser(donor.getUser());
            temp.setDonorOccupation(donor.getDonorOccupation());
            temp.setTrustName(donor.getTrustName());
            donorRepository.save(temp);
            return true;
        }
        else
            throw new UserNotFoundException("User doesn't Exist");
    }

    @Override
    public boolean donorLogin(loginData data)throws UserNotFoundException {
        boolean valid = false;
        Donor temp = donorRepository.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            if(temp.getPassword().equals(data.getUserPassword()))
                valid = true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
        return valid;
    }
}
