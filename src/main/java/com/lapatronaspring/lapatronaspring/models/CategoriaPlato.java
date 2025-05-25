package com.lapatronaspring.lapatronaspring.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPlato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaPlato;
    private String nombre;

    public Long getIdCategoriaPlato() {
        return idCategoriaPlato;
    }

    public void setIdCategoriaPlato(Long idCategoriaPlato) {
        this.idCategoriaPlato = idCategoriaPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
