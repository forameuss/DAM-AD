package modelos;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="dbo.BI_Clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(name="Telefono")
	private String telefono;
	@Column(name="Direccion")
	private String direccion;
	@Column(name="NumeroCuenta")
	private String numeroCuenta;
	@Column(name="Nombre")
	private String nombre;
	@OneToMany(mappedBy="cliente",cascade= CascadeType.ALL)
	private Set<Mascota> mascotas=new HashSet();

	public Cliente() {
	}

	public Cliente(String telefono, String direccion, String numeroCuenta, String nombre, Set<Mascota> mascotas) {
		this.telefono = telefono;
		this.direccion = direccion;
		this.numeroCuenta = numeroCuenta;
		this.nombre = nombre;
		this.mascotas = mascotas;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(Set<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	@Override
	public String toString(){
		return getCodigo()+": "+getNombre();
	}
}
