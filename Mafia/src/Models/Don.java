package Models;

import javax.persistence.*;

@Entity
@Table(name = "Dons")
public class Don {
    @Id
    @Column(name = "ID")
    private int ID;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellidos")
    private String apellidos;
    @Column(name = "Apodo")
    private String apodo;
    @Column(name = "Procedencia")
    private String procedencia;

    public Don(int ID, String nombre, String apellidos, String apodo, String procedencia) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.apodo = apodo;
        this.procedencia = procedencia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }  
}
