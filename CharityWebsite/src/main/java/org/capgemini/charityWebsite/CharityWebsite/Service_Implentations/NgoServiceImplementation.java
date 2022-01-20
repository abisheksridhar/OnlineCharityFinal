package org.capgemini.charityWebsite.CharityWebsite.Service_Implentations;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Repository.NgoRepository;
import org.capgemini.charityWebsite.CharityWebsite.Service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NgoServiceImplementation implements NgoService {
    @Autowired
    private NgoRepository ngoRepository;
    @Override
    public boolean ngoRegistration(Ngo ngo) throws UserExistException {
        Ngo temp = ngoRepository.findByemailAddress(ngo.getEmailAddress());
        if(!Objects.isNull(temp))
            throw new UserExistException("User Already Exist");
        else
            ngoRepository.save(ngo);
        return true;
    }

    public boolean updatePassword(loginData data) throws UserNotFoundException
    {
        Ngo temp = ngoRepository.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            temp.setPassword(data.getUserPassword());
            ngoRepository.save(temp);
            return  true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
    }

    public boolean updateUserInfo(Long ngoId,Ngo ngo) throws UserNotFoundException
    {
        if(ngoRepository.findById(ngoId).isPresent()){
            Ngo temp = ngoRepository.findById(ngoId).get();
            temp.setUser(ngo.getUser());
            temp.setLicenseNumber(ngo.getLicenseNumber());
            temp.setOrganisationName(ngo.getOrganisationName());
            ngoRepository.save(temp);
            return true;
        }
        else
            throw new UserNotFoundException("User doesn't Exist");
    }

    @Override
    public boolean ngoLogin(loginData data)throws UserNotFoundException {
        boolean valid = false;
        Ngo temp = ngoRepository.findByemailAddress(data.getUserName());
        if(!Objects.isNull(temp)) {
            if(temp.getPassword().equals(data.getUserPassword()))
                valid = true;
        }
        else
            throw new  UserNotFoundException("User does not exist");
        return valid;
    }
}
