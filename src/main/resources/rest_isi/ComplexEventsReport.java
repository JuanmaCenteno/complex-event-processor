package rest_isi;

import rest_isi.Consultas;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.*;

@Path("/ComplexEventsReport")
public class ComplexEventsReport {

	private static int nAlertaPuerta = 0;
	private static int nAlertaHabitacion = 0;
	private static int nAlertaProducto = 0;
	private static int nAlertaOrdenador = 0;

	@POST
	@Path("/addEvent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response handleEvent(String eventJson) {
		try {
			// String filePath =
			// "C:\\Users\\Juanma\\Desktop\\UCA\\ISI\\PracticasAnypoint\\isi\\src\\main\\resources\\salidaManolo.txt";
			// PrintStream out = new PrintStream(new FileOutputStream(filePath, true));
			// System.setOut(out);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(eventJson);

			// Identificar el campo raíz para determinar el tipo de evento
			String eventType = rootNode.fieldNames().next();
			if (eventType != null) {
				JsonNode specificEventNode = rootNode.get(eventType);
				// Procesar el evento específico según su tipo
				switch (eventType) {

				case "alertaOrdenador":
					nAlertaOrdenador++;
					EventAlertaOrdenador ev = objectMapper.treeToValue(specificEventNode, EventAlertaOrdenador.class);
					Consultas.consultaOrdenador(ev.getCod_ordenador(), ev.getCod_sala(), ev.getStatus());
					break;

				case "alertaPuerta":
					nAlertaPuerta++;
					EventAlertaPuerta evp = objectMapper.treeToValue(specificEventNode, EventAlertaPuerta.class);
					Consultas.consultaPuerta(evp.getEmpleado(), evp.getNombre(), evp.getApellidos(), evp.getFecha(),
							evp.getTipo());
					break;

				case "alertaProducto":
					nAlertaProducto++;
					EventAlertaProducto evpr = objectMapper.treeToValue(specificEventNode, EventAlertaProducto.class);
					Consultas.consultaProducto(evpr.getCod_producto(), evpr.getUbicacion(), evpr.getActual(),
							evpr.getMinimo());
					break;

				case "alertaHabitacion":
					nAlertaHabitacion++;
					EventAlertaHabitacion evh = objectMapper.treeToValue(specificEventNode,
							EventAlertaHabitacion.class);
					Consultas.consultaHabitacion(evh.getCod_sala(), evh.getEmp1(), evh.getEmp2(), evh.getTemp(),
							evh.getTipo(), evh.getFecha());
					break;

				default:
					// Manejar el caso cuando el tipo de evento no es reconocido
					return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de evento no reconocido").build();
				}

				return Response.status(Response.Status.OK).entity("Evento procesado").build();

			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Campo raíz no encontrado").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar el evento").build();
		}
	}

	@GET
	@Path("/getEvent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEvent(String eventJson) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(eventJson);
			String evento = jsonNode.get("evento").asText(); // Tipo de evento
			String result = "";

			switch (evento) {
			case "alertaHabitacion":
				result = Consultas.getAlertaHabitacion();
				break;

			case "alertaProducto":
				result = Consultas.getAlertaProducto();
				break;

			case "alertaPuerta":
				result = Consultas.getAlertaPuerta();
				break;

			case "alertaOrdenador":
				result = Consultas.getAlertaOrdenador();
				break;
			}

			if (!result.isEmpty()) {
				return Response.status(Response.Status.OK).entity(result).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity("ERROR.").build();
		}

		return Response.status(Response.Status.OK).entity("NINGÚN RESULTADO").build();
	}

	/*
	@POST
	@Path("/updateEvent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEvent(String eventJson) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(eventJson);

			// Identificar el campo raíz para determinar el tipo de evento
			String eventType = rootNode.fieldNames().next();
			if (eventType != null) {
				JsonNode specificEventNode = rootNode.get(eventType);
				// Procesar el evento específico según su tipo
				switch (eventType) {

				case "alertaOrdenador":
					EventAlertaOrdenador ev = objectMapper.treeToValue(specificEventNode, EventAlertaOrdenador.class);
					Consultas.updateOrdenador(ev.getCod_ordenador(), ev.getCod_sala(), ev.getStatus());
					break;

				case "alertaPuerta":
					EventAlertaPuerta evp = objectMapper.treeToValue(specificEventNode, EventAlertaPuerta.class);
					Consultas.updatePuerta(evp.getEmpleado(), evp.getNombre(), evp.getApellidos(), evp.getFecha(),
							evp.getTipo());
					break;

				case "alertaProducto":
					EventAlertaProducto evpr = objectMapper.treeToValue(specificEventNode, EventAlertaProducto.class);
					Consultas.updateProducto(evpr.getCod_producto(), evpr.getUbicacion(), evpr.getActual(),
							evpr.getMinimo());
					break;

				case "alertaHabitacion":
					EventAlertaHabitacion evh = objectMapper.treeToValue(specificEventNode,
							EventAlertaHabitacion.class);
					Consultas.updateHabitacion(evh.getCod_sala(), evh.getEmp1(), evh.getEmp2(), evh.getTemp(),
							evh.getTipo(), evh.getFecha());
					break;

				default:
					// Manejar el caso cuando el tipo de evento no es reconocido
					return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de evento no reconocido").build();
				}

				return Response.status(Response.Status.OK).entity("Evento procesado").build();

			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Campo raíz no encontrado").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar el evento").build();
		}
	}
	*/
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/totalEventos")
	public String totalEventos() {
		return "TOTAL DE EVENTOS:\n\n" + nAlertaHabitacion + " eventos AlertaHabitación\n" + nAlertaProducto
				+ " eventos AlertaProducto\n" + nAlertaOrdenador + " eventos AlertaOrdenador\n" + nAlertaPuerta
				+ " eventos AlertaPuerta\n" + (nAlertaHabitacion + nAlertaOrdenador + nAlertaProducto + nAlertaPuerta)
				+ " eventos en total.";
	}
}