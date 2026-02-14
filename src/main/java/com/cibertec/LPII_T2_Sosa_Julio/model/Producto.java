package com.cibertec.LPII_T2_Sosa_Julio.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Integer idProd;

    @Column(name = "nom_prod", nullable = false, length = 100)
    private String nomProd;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual;
}