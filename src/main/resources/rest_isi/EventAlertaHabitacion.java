package rest_isi;

public class EventAlertaHabitacion {

	public int getCod_sala() {
		return cod_sala;
	}
	public void setCod_sala(int cod_sala) {
		this.cod_sala = cod_sala;
	}
	public int getEmp1() {
		return emp1;
	}
	public void setEmp1(int emp1) {
		this.emp1 = emp1;
	}
	public int getEmp2() {
		return emp2;
	}
	public void setEmp2(int emp2) {
		this.emp2 = emp2;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public EventAlertaHabitacion(int cod_sala, int emp1, int emp2, double temp, String tipo, String fecha) {
		super();
		this.cod_sala = cod_sala;
		this.emp1 = emp1;
		this.emp2 = emp2;
		this.temp = temp;
		this.tipo = tipo;
		this.fecha = fecha;
	}
	
	public EventAlertaHabitacion() {
		
	}
	
	
	@Override
	public String toString() {
		return "EventAlertaHabitacion [cod_sala=" + cod_sala + ", emp1=" + emp1 + ", emp2=" + emp2 + ", temp=" + temp
				+ ", tipo=" + tipo + ", fecha=" + fecha + "]";
	}


	private int cod_sala;
	private int emp1;
	private int emp2;
	private double temp;
	private String tipo;
	private String fecha;
	
	
}
