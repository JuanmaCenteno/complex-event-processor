package rest_isi;

public class EventAlertaOrdenador {
	public EventAlertaOrdenador() {
    }
	
	
	@Override
	public String toString() {
		return "EventAlertaOrdenador [cod_ordenador=" + cod_ordenador + ", cod_sala=" + cod_sala + ", status=" + status
				+ "]";
	}


	public int getCod_ordenador() {
		return cod_ordenador;
	}
	public void setCod_ordenador(int cod_ordenador) {
		this.cod_ordenador = cod_ordenador;
	}
	public int getCod_sala() {
		return cod_sala;
	}
	public void setCod_sala(int cod_sala) {
		this.cod_sala = cod_sala;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EventAlertaOrdenador(int cod_ordenador, int cod_sala, String status) {
		super();
		this.cod_ordenador = cod_ordenador;
		this.cod_sala = cod_sala;
		this.status = status;
	}
	private int cod_ordenador;
	private int cod_sala;
	private String status;
}
