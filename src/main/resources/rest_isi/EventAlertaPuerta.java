package rest_isi;

public class EventAlertaPuerta {

	public int getEmpleado() {
		return empleado;
	}
	public void setEmpleado(int empleado) {
		this.empleado = empleado;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public EventAlertaPuerta(int empleado, String nombre, String apellidos, String fecha, String tipo) {
		super();
		this.empleado = empleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.tipo = tipo;
	}
	
	public EventAlertaPuerta() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "EventAlertaPuerta [empleado=" + empleado + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fecha=" + fecha + ", tipo=" + tipo + "]";
	}



	private int empleado;
	private String nombre;
	private String apellidos;
	private String fecha;
	private String tipo;
}
