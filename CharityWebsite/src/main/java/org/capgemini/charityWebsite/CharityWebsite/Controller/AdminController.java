package org.capgemini.charityWebsite.CharityWebsite.Controller;

import org.capgemini.charityWebsite.CharityWebsite.Entities.*;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserExistException;
import org.capgemini.charityWebsite.CharityWebsite.Exception.UserNotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //annotation to define controller which handles web request
@RequestMapping("/")// to map request and service
public class AdminController {

    @Autowired // injection
    private AdminService adminService;

    @PostMapping("/admin/register") // to post a data from server
    public ResponseEntity<String> adminRegistration(@Valid @RequestBody Admin admin){
        ResponseEntity<String> Res = null;
        try {
            if(adminService.adminRegistration(admin))
                Res = new ResponseEntity<>
                        ("User Created Successfully", HttpStatus.CREATED);
        } catch (UserExistException e) {
            Res = new ResponseEntity<>
                    ("User with same Email Address already exist",HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            Res = new ResponseEntity<>
                    (e.getMessage(),HttpStatus.CONFLICT);
        }
        return Res;
    }
    @PostMapping("/Admin/updatePassword")
    public  ResponseEntity<String> updatePassword(@RequestBody loginData data){
        ResponseEntity<String> Res = null;
        try {
            if(adminService.updatePassword(data))
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

    @PostMapping("/admin/updateUserInfo/{id}")
    public  ResponseEntity<String> updateUserInfo(@PathVariable("id") Long Id, @RequestBody Admin data){
        ResponseEntity<String> Res = null;
        try {
            if(adminService.updateUserInfo(Id,data))
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

    @PostMapping("/admin/loginUser")
    public  ResponseEntity<String> adminLogin(@RequestBody loginData data){
        ResponseEntity<String> Res ;
        try {
            if(adminService.adminLogin(data))
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
    @GetMapping("admin/viewRequest")
    public ResponseEntity<List<DonationRequest>> viewRequest(){
        ResponseEntity<List<DonationRequest>>  Res;
        List<DonationRequest> Result = adminService.viewRequest();
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }

    @GetMapping("admin/viewDonation")
    public ResponseEntity<List<Donation>> viewDonation(){
        ResponseEntity<List<Donation>>  Res;
        List<Donation> Result = adminService.viewDonation();
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }

    @PostMapping("/admin/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable("id") Long id)
    {
        ResponseEntity<String> Res = null;
        try {
            if(adminService.approveRequest(id))
                Res = new ResponseEntity<>("approved Request",HttpStatus.OK);
        } catch (NotFoundException e) {
            Res = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  Res;
    }
}
