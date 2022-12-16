package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/organisation/{organizationId}/license")
@Slf4j
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") Long licenseId
    ) {
        License license = licenseService.getLicense(licenseId, organizationId);
        log.info("Sleuth info: ");
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(
            @PathVariable("organizationId")
            String organizationId,
            @RequestBody License request) {
        return ResponseEntity.ok(
                licenseService.updateLicense(
                        request
                )
        );
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License request) {
        return ResponseEntity.ok(
                licenseService.createLicense(
                        request
                )
        );
    }


    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") Long licenseId) {
        return ResponseEntity.ok(
                licenseService.deleteLicense(
                        licenseId
                )
        );
    }
}
