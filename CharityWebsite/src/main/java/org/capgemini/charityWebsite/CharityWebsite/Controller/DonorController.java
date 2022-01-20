package org.capgemini.charityWebsite.CharityWebsite.Controller;


import org.capgemini.charityWebsite.CharityWebsite.Entities.Donor;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("/donor/registerNgo")
    public ResponseEntity<String> donorRegistration(@Valid @RequestBody Donor donor){
        ResponseEntity<String> Res = null;
        try {
            if(donorService.donorRegistration(donor))
                Res = new ResponseEntity<>
                        ("User Created Successfully", HttpStatus.CREATED);
        } catch (UserExistException e) {
            Res = new ResponseEntity<>
                    ("User with same Email Address already exist",HttpStatus.CONFLICT);
        }
            return Res;
    }
    @PostMapping("/donor/updatePassword")
    public  ResponseEntity<String> updatePassword(@RequestBody loginData data){
        ResponseEntity<String> Res = null;
        try {
            if(donorService.updatePassword(data))
            {
                Res = new ResponseEntity<>
                    ("Password Updated Successfully", HttpStatus.CREATED);
            }
        } catch (UserNotFoundException e) {
            Res = new ResponseEntity<>
                    ("Email id doesn't exist",HttpStatus.CONFLICT);
        }
            return  Res;
    }

    @PostMapping("/donor/updateUserInfo/{id}")
    public  ResponseEntity<String> updateUserInfo(@PathVariable("id") Long Id,@RequestBody Donor data){
        ResponseEntity<String> Res = null;
        try {
            if(donorService.updateUserInfo(Id,data))
            {
                Res = new ResponseEntity<>
                        ("Password Updated Successfully", HttpStatus.CREATED);
            }
        } catch (UserNotFoundException e) {
            Res = new ResponseEntity<>
                    ("Email id doesn't exist",HttpStatus.CONFLICT);
        }
        return  Res;
    }

    @PostMapping("/donor/loginUser")
    public  ResponseEntity<String> donorLogin(@RequestBody loginData data){
        ResponseEntity<String> Res ;
        try {
            if(donorService.donorLogin(data))
            {
                Res = new ResponseEntity<>
                        ("Welcome Back logged in successfully", HttpStatus.OK);
            }
            else
            {
                Res = new ResponseEntity<>
                        ("Wrong Password", HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            Res = new ResponseEntity<>
                    ("Email id doesn't exist",HttpStatus.CONFLICT);
        }
        return  Res;
    }
}
