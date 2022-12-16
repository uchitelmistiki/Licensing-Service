package com.optimagrowth.license.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationChangeModel {

    private String type;

    private String action;

    private Long id;
}
