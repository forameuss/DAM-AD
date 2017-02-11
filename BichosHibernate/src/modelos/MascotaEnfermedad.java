package modelos;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
@Entity
@Table(name="dbo.BI_Mascotas_Enfermedades")
public class MascotaEnfermedad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="idEnfermedad")
	private Enfermedad enfermedad;

	@Id
	@ManyToOne
	@JoinColumn(name="Mascota")
	private Mascota mascota;
	@Column(name="FechaInicio")
	private Date fechaInicio;
	@Column(name="FechaCura")
	private Date fechaCura;

	public MascotaEnfermedad(){}

	public MascotaEnfermedad(Mascota mascota, Enfermedad enfermedad, Date fechaInicio, Date fechaCura) {
		super();
		this.mascota = mascota;
		this.enfermedad = enfermedad;
		this.fechaInicio = fechaInicio;
		this.fechaCura = fechaCura;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaCura() {
		return fechaCura;
	}

	public void setFechaCura(Date fechaCura) {
		this.fechaCura = fechaCura;
	}

	@Override
	public String toString(){
		return getMascota().getAlias()+", "+getEnfermedad().getNombre();
	}


}