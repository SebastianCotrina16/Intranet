/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 *
 * @author LEGION
 */

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatricula;
    
    @ManyToOne
    @JoinColumn(name="fkIdUsuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="fkIdSemestre", nullable = false)
    private Semestre semestre;
    
    @ManyToOne
    @JoinColumn(name="fkIdProntoPago", nullable = false)
    private ProntoPago prontoPago;
    
    @ManyToOne
    @JoinColumn(name="fkIdBeca", nullable = false)
    private Beca beca;
    
    @Column(name="Fecha", columnDefinition="date", nullable = false)
    @Past
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name="fkIdEstado", nullable = false)
    private Estado estado;
    
    @Column(name="Vencimiento", columnDefinition="date", nullable = false)
    @Past
    private LocalDate vencimiento;
    
    @Column(name="Total", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "9999.99")
    private BigDecimal total;

    public Matricula() {
    }

    public Matricula(int idMatricula, Usuario usuario, Semestre semestre, ProntoPago prontoPago, Beca beca, LocalDate fecha, Estado estado, LocalDate vencimiento, BigDecimal total) {
        this.idMatricula = idMatricula;
        this.usuario = usuario;
        this.semestre = semestre;
        this.prontoPago = prontoPago;
        this.beca = beca;
        this.fecha = fecha;
        this.estado = estado;
        this.vencimiento = vencimiento;
        this.total = total;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public ProntoPago getProntoPago() {
        return prontoPago;
    }

    public Beca getBeca() {
        return beca;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public void setProntoPago(ProntoPago prontoPago) {
        this.prontoPago = prontoPago;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    
}
