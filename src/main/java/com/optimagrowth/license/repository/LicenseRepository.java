package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, Long> {

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndId(String organizationId, Long licenseId);
}
