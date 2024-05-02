package com.tuvarna.geo.entity;

import org.locationtech.jts.geom.MultiPolygon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"dsmw\"")
public class Soil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;

    @Column(nullable = false, unique = false)
    private float snum;

    @Column(nullable = false, unique = true)
    private String faosoil;

    @Column(nullable = false, unique = false)
    private String domsoi;

    @Column(nullable = true, unique = false)
    private String phase1;

    @Column(nullable = true, unique = false)
    private String phase2;

    @Column(nullable = true, unique = false)
    private String misclu1;

    @Column(nullable = true, unique = false)
    private String misclu2;

    @Column(nullable = true, unique = false)
    private String permafrost;

    @Column(nullable = true, unique = false)
    private double cntcode;

    @Column(nullable = true, unique = false)
    private String cntname;

    @Column(nullable = false, unique = false)
    private double sqkm;

    @Column(nullable = false, unique = false)
    private String country;

    @Transient
    @Column(nullable = false, unique = false)
    private MultiPolygon geom;
}
