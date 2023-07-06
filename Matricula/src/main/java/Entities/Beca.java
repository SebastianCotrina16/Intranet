/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author LEGION
 */
@Entity
@Table(name = "beca")
public class Beca {
    @Id
    private int idBeca;

    public Beca() {
    }

    public Beca(int idBeca) {
        this.idBeca = idBeca;
    }

    public int getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(int idBeca) {
        this.idBeca = idBeca;
    }
    
}
