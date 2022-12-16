package com.optimagrowth.license.controller;

import com.optimagrowth.license.client.OrganisationClient;
import com.optimagrowth.license.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/org")
public class OrganizationController {

    @Autowired
    private OrganisationClient organisationClient;

    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable String organizationId) {
        return ResponseEntity.ok(
                organisationClient.getOrganization(Long.parseLong(organizationId))
        );
    }
}
