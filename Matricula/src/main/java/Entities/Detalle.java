/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle")
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(name = "fkIdMatricula", nullable = false)
    private int fkIdMatricula;

    @ManyToOne
    @JoinColumn(name = "fkIdCurso", nullable = false)
    private Curso curso;

    @Column(name = "SubTotal", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "9999.99")
    private BigDecimal subTotal;

    public Detalle() {
    }

    public Detalle(int idDetalle, int fkIdMatricula, Curso curso, BigDecimal subTotal) {
        this.idDetalle = idDetalle;
        this.fkIdMatricula = fkIdMatricula;
        this.curso = curso;
        this.subTotal = subTotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public int getFkIdMatricula() {
        return fkIdMatricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setFkIdMatricula(int fkIdMatricula) {
        this.fkIdMatricula = fkIdMatricula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

}