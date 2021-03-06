package modelos;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="dbo.BI_Enfermedades")
public class Enfermedad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Nombre")
	private String nombre;
	@OneToMany(mappedBy="enfermedad",cascade= CascadeType.ALL)
	private Set<MascotaEnfermedad> mascotaEnfermedades;
	
	public Enfermedad(){}

	public Enfermedad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public Set<MascotaEnfermedad> getMascotaEnfermedades() {
		return mascotaEnfermedades;
	}

	public void setMascotaEnfermedades(Set<MascotaEnfermedad> mascotaEnfermedades) {
		this.mascotaEnfermedades = mascotaEnfermedades;
	}

	@Override
	public String toString(){
		return getNombre();
	}
	
}
