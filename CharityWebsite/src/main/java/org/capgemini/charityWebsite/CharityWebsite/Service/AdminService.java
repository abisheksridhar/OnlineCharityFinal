package org.capgemini.charityWebsite.CharityWebsite.Service;

import org.capgemini.charityWebsite.CharityWebsite.Entities.*;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;

import java.util.List;

public interface AdminService {
    List<Donation> viewDonation();

    List<DonationRequest> viewRequest();

    boolean updateUserInfo(Long id, Admin data)throws UserNotFoundException;

    boolean updatePassword(loginData data) throws UserNotFoundException;

    boolean adminRegistration(Admin admin) throws UserExistException,Exception;

    boolean adminLogin(loginData data) throws UserNotFoundException;


    boolean approveRequest(Long id) throws NotFoundException;
}
