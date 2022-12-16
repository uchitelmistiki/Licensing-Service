package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Service
@Slf4j
public class LicenseService {

    @Autowired
    private MessageSource messages;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    @CircuitBreaker(name = "licenseService")
    public License getLicense(Long licenseId, String organizationId) {
        return licenseRepository
                .findByOrganizationIdAndId(organizationId, licenseId);
    }

    public License createLicense(License license) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(Long licenseId) {
        licenseRepository.deleteById(licenseId);
        return String.format(messages.getMessage(
                "license.delete.message", null, null), licenseId);
    }
}
