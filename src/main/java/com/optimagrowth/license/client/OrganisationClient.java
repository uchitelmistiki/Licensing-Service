package com.optimagrowth.license.client;

import com.optimagrowth.license.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganisationClient {

    @Autowired
    private RestTemplate restTemplate;


    public Organization getOrganization(Long id) {
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET, null,
                        Organization.class, id);
        return restExchange.getBody();
    }
}

