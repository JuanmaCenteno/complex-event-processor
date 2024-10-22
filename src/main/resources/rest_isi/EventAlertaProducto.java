package rest_isi;

public class EventAlertaProducto {
	public int getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getActual() {
		return actual;
	}
	public void setActual(int actual) {
		this.actual = actual;
	}
	public int getMinimo() {
		return minimo;
	}
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}
	public EventAlertaProducto(int cod_producto, String ubicacion, int actual, int minimo) {
		super();
		this.cod_producto = cod_producto;
		this.ubicacion = ubicacion;
		this.actual = actual;
		this.minimo = minimo;
	}
	
	public EventAlertaProducto() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "EventAlertaProducto [cod_producto=" + cod_producto + ", ubicacion=" + ubicacion + ", actual=" + actual
				+ ", minimo=" + minimo + "]";
	}



	private int cod_producto;
	private String ubicacion;
	private int actual;
	private int minimo;
	
	
}
