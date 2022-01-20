package org.capgemini.charityWebsite.CharityWebsite.Controller;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (path="/")
public class DonationController {

    @Autowired
    private DonationService service;

    @PostMapping("/donation/new")
    public ResponseEntity<String> newDonation(@Valid @RequestBody Donation donation){
        ResponseEntity<String>  Res= null;
        try {
            if(service.newDonation(donation))
                   Res = new ResponseEntity<>
                   ("added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            Res = new ResponseEntity<>
                    ("internal error", HttpStatus.CONFLICT);
        }
        return Res;
    }

    @PostMapping("/donation/update/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable("id")Long Id,  @RequestBody Donation donation){
        ResponseEntity<String>  Res= null;
        try {
            if(service.updateDonation(Id,donation))
                Res = new ResponseEntity<>
                        ("updated successfully", HttpStatus.CREATED);
        } catch (NotFoundException e) {
            Res = new ResponseEntity<>
                    ("Not updated", HttpStatus.CONFLICT);
        }
        return Res;
    }


    @GetMapping("/donation/viewRequest")
    public ResponseEntity<List<DonationRequest>> viewRequests(){
        ResponseEntity<List<DonationRequest>>  Res;
        List<DonationRequest> Result = service.viewRequests();
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }

    @GetMapping("/donation/viewDonation")
    public ResponseEntity<List<Donation>> viewDonation(){
        ResponseEntity<List<Donation>>  Res;
        List<Donation> Result = service.viewDonation();
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }
}
