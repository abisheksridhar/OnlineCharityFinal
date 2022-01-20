package org.capgemini.charityWebsite.CharityWebsite.Controller;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Ngo;
import org.capgemini.charityWebsite.CharityWebsite.Entities.loginData;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class NgoController {

    @Autowired
    private NgoService ngoService;

    @PostMapping("/ngo/registerNgo")
    public ResponseEntity<String> ngoRegistration(@Valid @RequestBody Ngo ngo){
        ResponseEntity<String> Res = null;
        try {
            if(ngoService.ngoRegistration(ngo))
                Res = new ResponseEntity<>
                        ("User Created Successfully", HttpStatus.CREATED);
            else
                Res = new ResponseEntity<>("Cannot create User",HttpStatus.BAD_REQUEST);
        } catch (UserExistException e) {
            Res = new ResponseEntity<>
                    ("User with same Email Address already exist",HttpStatus.CONFLICT);
        }

        return Res;
    }
    @PostMapping("/ngo/updatePassword")
    public  ResponseEntity<String> updatePassword(@RequestBody loginData data){
        ResponseEntity<String> Res = null;
        try {
            if(ngoService.updatePassword(data))
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

    @PostMapping("/ngo/updateUserInfo/{id}")
    public  ResponseEntity<String> updateUserInfo(@PathVariable("id") Long Id, @RequestBody Ngo data){
        ResponseEntity<String> Res = null;
        try {
            if(ngoService.updateUserInfo(Id,data))
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

    @PostMapping("/ngo/loginUser")
    public  ResponseEntity<String> ngoLogin(@RequestBody loginData data){
        ResponseEntity<String> Res ;
        try {
            if(ngoService.ngoLogin(data))
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
