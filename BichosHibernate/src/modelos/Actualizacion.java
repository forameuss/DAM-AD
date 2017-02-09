package modelos;

import java.sql.Date;

import javax.persistence.*;
@Entity
@Table(name="dbo.BI_Actualizaciones")
public class Actualizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="Fecha")
    private Date fecha;
    @Column(name="Temperatura")
    private Byte temperatura;
    @Column(name="Peso")
    private Integer peso;
    @Column(name="CodigoMascota")
    private String codigoMascota;
    @Column(name="Raza")
    private String raza;
    @Column(name="Especie")
    private String especie;
    @Column(name="FechaNacimiento")
    private Date fechaNacimiento;
    @Column(name="FechaFallecimiento")
    private Date fechaFallecimiento;
    @Column(name="Alias")
    private String alias;
    @Column(name="CodigoPropietario")
    private Integer codigoPropietario;
    @Column(name="Enfermedad")
    private String enfermedad;

    public Actualizacion(){}

    public Actualizacion(Integer id, Date fecha, Byte temperatura, Integer peso, String codigoMascota, String raza,
                         String especie, Date fechaNacimiento, Date fechaFallecimiento, String alias, Integer codigoPropietario,
                         String enfermedad) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.peso = peso;
        this.codigoMascota = codigoMascota;
        this.raza = raza;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.alias = alias;
        this.codigoPropietario = codigoPropietario;
        this.enfermedad = enfermedad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Byte getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Byte temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getCodigoMascota() {
        return codigoMascota;
    }

    public void setCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getCodigoPropietario() {
        return codigoPropietario;
    }

    public void setCodigoPropietario(Integer codigoPropietario) {
        this.codigoPropietario = codigoPropietario;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return "Actualizacion [id=" + id + ", fecha=" + fecha + ", temperatura=" + temperatura + ", peso=" + peso
                + ", codigoMascota=" + codigoMascota + ", raza=" + raza + ", especie=" + especie + ", fechaNacimiento="
                + fechaNacimiento + ", fechaFallecimiento=" + fechaFallecimiento + ", alias=" + alias
                + ", codigoPropietario=" + codigoPropietario + ", enfermedad=" + enfermedad + "]";
    }


}