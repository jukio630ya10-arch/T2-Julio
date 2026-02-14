package com.cibertec.LPII_T2_Sosa_Julio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_boleta")
    private Integer nroBoleta;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "nom_cliente", nullable = false, length = 100)
    private String nomCliente;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<DetalleBoleta> detalles;
}
