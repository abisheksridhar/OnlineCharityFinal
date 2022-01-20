package org.capgemini.charityWebsite.CharityWebsite.Controller;

import org.capgemini.charityWebsite.CharityWebsite.Entities.Donation;
import org.capgemini.charityWebsite.CharityWebsite.Entities.DonationRequest;
import org.capgemini.charityWebsite.CharityWebsite.Exception.NotFoundException;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonationRequestService;
import org.capgemini.charityWebsite.CharityWebsite.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class DonationRequestController {

    @Autowired
    private DonationRequestService donationRequestService;

    @Autowired
    private DonorService donationService;


    @PostMapping("/donationRequest/new")
    public ResponseEntity<String> newDonationRequest(@Valid @RequestBody DonationRequest donationRequest) {
        ResponseEntity<String> Res = null;

        if (donationRequestService.newDonationRequest(donationRequest))
            Res = new ResponseEntity<>
                    ("Request added Successfully", HttpStatus.CREATED);

        return Res;
    }

    @PostMapping("/donationrequest/uploadDocument/{id}")
    public ResponseEntity<String> uploadDocument(@RequestParam MultipartFile[] doc, @PathVariable("id") Long reqId) {
        String result = donationRequestService.uploadDocument(doc, reqId);
        ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @PostMapping("/donationRequest/update")
    public ResponseEntity<String> updateDonationRequest(@PathVariable("id") Long Id, @RequestBody DonationRequest donationRequest) {
        ResponseEntity<String> Res = null;

        try {
            if (donationRequestService.updateDonationRequest(Id, donationRequest))
                Res = new ResponseEntity<>
                        ("Request added Successfully", HttpStatus.CREATED);
        } catch (NotFoundException e) {
            Res = new ResponseEntity<>
                    ("Cannot add your Request contact admin", HttpStatus.CREATED);
        }

        return Res;
    }

    @GetMapping("/donationRequest/viewDonations/{id}")
    public ResponseEntity<List<Donation>> viewDonation(@PathVariable("id") Long id) {
        ResponseEntity<List<Donation>> Res;
        List<Donation> Result = donationRequestService.viewDonation(id);
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }

    @GetMapping("/donationRequest/viewRequest")
    public ResponseEntity<List<DonationRequest>> viewRequests() {
        ResponseEntity<List<DonationRequest>> Res;
        List<DonationRequest> Result = donationRequestService.viewRequests();
        Res = new ResponseEntity<>
                (Result, HttpStatus.CREATED);
        return Res;
    }

    @GetMapping("/ngo/donationRequest/viewRequest/{id}")
    public ResponseEntity<DonationRequest> getRequestById(@PathVariable("id") Long id) {
        ResponseEntity<DonationRequest> response;
        try {
            DonationRequest request = donationRequestService.getRequestById(id);
            response = new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/donationRequest/delete/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        ResponseEntity<String> res = null;
        try {
            if (donationRequestService.deleteRequest(id))
                res = new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        } catch (NotFoundException e) {
            res = new ResponseEntity<>("Id Not Found", HttpStatus.OK);
        }
        return res;
    }
}
