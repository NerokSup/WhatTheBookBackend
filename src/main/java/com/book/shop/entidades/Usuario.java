package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String apellidos;

	private String dni;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nac")
	private Date fechaNac;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fecha_creacion;

	private String nombre;

	private String pais;
	
	private String ciudad;
	
	private String direccion;

	private String password;

	private String rol;

	private byte socio;
	
	private float saldo;

	private String telefono;

	private String username;

	public Usuario() {
	}

	public Usuario(int id, String username, String password, String email, String dni, String nombre, String apellidos,
			Date fecha_nac, Date fecha_creacion, String pais, String ciudad, String direccion, String telefono, float saldo, byte socio, String rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fecha_nac;
		this.fecha_creacion = fecha_creacion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.setSaldo(saldo);
		this.socio = socio;
		this.rol = rol;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public byte getSocio() {
		return this.socio;
	}

	public void setSocio(byte socio) {
		this.socio = socio;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * @return the fecha_creacion
	 */
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	/**
	 * @param fecha_creacion the fecha_creacion to set
	 */
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	/**
	 * @return the saldo
	 */
	public float getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

}