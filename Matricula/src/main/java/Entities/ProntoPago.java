/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author LEGION
 */
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prontopago")
public class ProntoPago {
    @Id
    private int idProntoPago;

    public ProntoPago() {
    }

    public ProntoPago(int idProntoPago) {
        this.idProntoPago = idProntoPago;
    }

    public int getIdProntoPago() {
        return idProntoPago;
    }

    public void setIdProntoPago(int idProntoPago) {
        this.idProntoPago = idProntoPago;
    }
    
}