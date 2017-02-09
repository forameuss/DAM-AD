package modelos;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="dbo.BI_Mascotas_Enfermedades")
public class EnfermedadMascota implements Serializable {

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
	
	public EnfermedadMascota(){}

	public EnfermedadMascota(Enfermedad enfermedad, Mascota mascota, Date fechaInicio, Date fechaCura) {
		super();
		this.enfermedad = enfermedad;
		this.mascota = mascota;
		this.fechaInicio = fechaInicio;
		this.fechaCura = fechaCura;
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
	
}
