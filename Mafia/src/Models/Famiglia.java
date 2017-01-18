package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Famiglia")
public class Famiglia {    
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "CiudadPrincipal")
    private String ciudadPrincipal;
    @Column(name = "Miembros")
    private int miembros;

    public Famiglia(int id, String nombre, String ciudadPrincipal, int miembros) {
        this.id = id;
        this.nombre = nombre;
        this.ciudadPrincipal = ciudadPrincipal;
        this.miembros = miembros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudadPrincipal() {
        return ciudadPrincipal;
    }

    public void setCiudadPrincipal(String ciudadPrincipal) {
        this.ciudadPrincipal = ciudadPrincipal;
    }

    public int getMiembros() {
        return miembros;
    }

    public void setMiembros(int miembros) {
        this.miembros = miembros;
    }
    
    
}
