package org.capgemini.charityWebsite.CharityWebsite.Service;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donor;
import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;

public interface DonorService {

    boolean donorRegistration(Donor donor) throws UserExistException;
    boolean updatePassword(loginData data) throws UserNotFoundException;
    boolean updateUserInfo(Long id,Donor donor) throws  UserNotFoundException;

    boolean donorLogin(loginData data) throws UserNotFoundException;
}
