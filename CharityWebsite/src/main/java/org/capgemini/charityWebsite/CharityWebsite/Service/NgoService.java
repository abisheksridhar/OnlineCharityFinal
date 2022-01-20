package org.capgemini.charityWebsite.CharityWebsite.Service;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;

public interface NgoService {

    boolean updateUserInfo(Long id, Ngo data) throws UserNotFoundException;

    boolean updatePassword(loginData data) throws UserNotFoundException;

    boolean ngoRegistration(Ngo ngo) throws UserExistException;

    boolean ngoLogin(loginData data) throws UserNotFoundException;
}
