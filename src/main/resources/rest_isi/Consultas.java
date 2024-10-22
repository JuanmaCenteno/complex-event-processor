package rest_isi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;

public class Consultas {

	// POST - NUEVO EVENTO
	public static void consultaHabitacion(int cod_sala, int emp1, int emp2, double temp, String tipo, String fecha) {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);

		try {
			java.sql.Connection connection = connector.connect();

			String insertQuery = "INSERT INTO alertahabitacion (cod_sala, emp1, emp2, temp, tipo, fecha) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(insertQuery);
			// Leemos por cada elemento del array

			preparedStatement.setInt(1, cod_sala);
			preparedStatement.setInt(2, emp1);
			preparedStatement.setInt(3, emp2);
			preparedStatement.setDouble(4, temp);
			preparedStatement.setString(5, tipo);
			preparedStatement.setString(6, fecha);

			preparedStatement.addBatch(); // Agregamos la consulta al lote para ejecución en bloque

			// Ejecutamos el lote de inserciones
			preparedStatement.executeBatch();

			// Cerramos la conexión
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void consultaOrdenador(int cod_ordenador, int cod_sala, String status) {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);

		try {
			java.sql.Connection connection = connector.connect();

			String insertQuery = "INSERT INTO alertaordenador (cod_ordenador, cod_sala, status) VALUES (?,?,?)";
			PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(insertQuery);
			// Leemos por cada elemento del array

			preparedStatement.setInt(1, cod_ordenador);
			preparedStatement.setInt(2, cod_sala);
			preparedStatement.setString(3, status);

			preparedStatement.addBatch(); // Agregamos la consulta al lote para ejecución en bloque

			// Ejecutamos el lote de inserciones
			preparedStatement.executeBatch();

			// Cerramos la conexión
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void consultaProducto(int cod_producto, String ubicacion, int actual, int minimo) {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);

		try {
			java.sql.Connection connection = connector.connect();

			String insertQuery = "INSERT INTO alertaproducto (cod_producto, ubicacion, actual, minimo) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(insertQuery);
			// Leemos por cada elemento del array

			preparedStatement.setInt(1, cod_producto);
			preparedStatement.setString(2, ubicacion);
			preparedStatement.setInt(3, actual);
			preparedStatement.setInt(4, minimo);

			preparedStatement.addBatch(); // Agregamos la consulta al lote para ejecución en bloque

			// Ejecutamos el lote de inserciones
			preparedStatement.executeBatch();

			// Cerramos la conexión
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void consultaPuerta(int empleado, String nombre, String apellidos, String fecha, String tipo) {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);

		try {
			java.sql.Connection connection = connector.connect();

			String insertQuery = "INSERT INTO alertapuerta (empleado, nombre, apellidos, fecha, tipo) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(insertQuery);
			// Leemos por cada elemento del array

			preparedStatement.setInt(1, empleado);
			preparedStatement.setString(2, nombre);
			preparedStatement.setString(3, apellidos);
			preparedStatement.setString(4, fecha);
			preparedStatement.setString(5, tipo);

			preparedStatement.addBatch(); // Agregamos la consulta al lote para ejecución en bloque

			// Ejecutamos el lote de inserciones
			preparedStatement.executeBatch();

			// Cerramos la conexión
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET - OBTENER EVENTOS
	public static String getAlertaOrdenador() {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);
		Map<String, Map<String, String>> resultados = new HashMap<>();

		try {
			java.sql.Connection connection = connector.connect();

			Statement statement = connection.createStatement();

			// Ejecutar la consulta select
			ResultSet resultSet = statement.executeQuery("SELECT * FROM alertaordenador");
			while (resultSet.next()) {
				String codOrdenador = resultSet.getString("cod_ordenador");
				String codSala = resultSet.getString("cod_sala");
				String status = resultSet.getString("status");

				// Crear un Map con clave-valor para cada fila
				Map<String, String> fila = new HashMap<>();
				fila.put("cod_ordenador", codOrdenador);
				fila.put("cod_sala", codSala);
				fila.put("status", status);

				// Agregar el Map al resultado usando el código del ordenador como clave
				resultados.put(codOrdenador, fila);
			}

			// Cerramos la conexión
			resultSet.close();
			statement.close();
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return convertirMapAJSON(resultados);
	}

	public static String getAlertaPuerta() {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);
		Map<String, Map<String, String>> resultados = new HashMap<>();

		try {
			java.sql.Connection connection = connector.connect();

			Statement statement = connection.createStatement();

			// Ejecutar la consulta select
			ResultSet resultSet = statement.executeQuery("SELECT * FROM alertapuerta");
			while (resultSet.next()) {
				String empleado = resultSet.getString("empleado");
				String nombre = resultSet.getString("nombre");
				String apellidos = resultSet.getString("apellidos");
				String fecha = resultSet.getString("fecha");
				String tipo = resultSet.getString("tipo");

				// Crear un Map con clave-valor para cada fila
				Map<String, String> fila = new HashMap<>();
				fila.put("empleado", empleado);
				fila.put("nombre", nombre);
				fila.put("apellidos", apellidos);
				fila.put("fecha", fecha);
				fila.put("tipo", tipo);
				
				// Agregar el Map al resultado usando el código del ordenador como clave
				resultados.put(empleado, fila);
			}

			// Cerramos la conexión
			resultSet.close();
			statement.close();
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return convertirMapAJSON(resultados);
	}
	
	public static String getAlertaProducto() {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);
		Map<String, Map<String, String>> resultados = new HashMap<>();

		try {
			java.sql.Connection connection = connector.connect();

			Statement statement = connection.createStatement();

			// Ejecutar la consulta select
			ResultSet resultSet = statement.executeQuery("SELECT * FROM alertaproducto");
			while (resultSet.next()) {
				String cod_producto = resultSet.getString("cod_producto");
				String ubicacion = resultSet.getString("ubicacion");
				String actual = resultSet.getString("actual");
				String minimo = resultSet.getString("minimo");

				// Crear un Map con clave-valor para cada fila
				Map<String, String> fila = new HashMap<>();
				fila.put("cod_producto", cod_producto);
				fila.put("ubicacion", ubicacion);
				fila.put("actual", actual);
				fila.put("minimo", minimo);
				
				// Agregar el Map al resultado usando el código del ordenador como clave
				resultados.put(cod_producto, fila);
			}

			// Cerramos la conexión
			resultSet.close();
			statement.close();
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return convertirMapAJSON(resultados);
	}
	
	public static String getAlertaHabitacion() {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);
		Map<String, Map<String, String>> resultados = new HashMap<>();

		try {
			java.sql.Connection connection = connector.connect();

			Statement statement = connection.createStatement();

			// Ejecutar la consulta select
			ResultSet resultSet = statement.executeQuery("SELECT * FROM alertahabitacion");
			while (resultSet.next()) {
				String cod_sala = resultSet.getString("cod_sala");
				String emp1 = resultSet.getString("emp1");
				String emp2 = resultSet.getString("emp2");
				String temp = resultSet.getString("temp");
				String tipo = resultSet.getString("tipo");
				String fecha = resultSet.getString("fecha");

				// Crear un Map con clave-valor para cada fila
				Map<String, String> fila = new HashMap<>();
				fila.put("cod_sala", cod_sala);
				fila.put("emp1", emp1);
				fila.put("emp2", emp2);
				fila.put("temp", temp);
				fila.put("tipo", tipo);
				fila.put("fecha", fecha);
				
				// Agregar el Map al resultado usando el código del ordenador como clave
				resultados.put(cod_sala, fila);
			}

			// Cerramos la conexión
			resultSet.close();
			statement.close();
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return convertirMapAJSON(resultados);
	}
	
	// UPDATE
	/*
	public static void updateHabitacion(int cod_sala, int emp1, int emp2, double temp, String tipo, String fecha) {
		// Conectamos a la base de datos
		String dbUrl = "jdbc:mysql://localhost:3306/proyecto_isi";
		String dbUser = "root";
		String dbPassword = "";
		MySQLConnector connector = new MySQLConnector(dbUrl, dbUser, dbPassword);

		try {
			java.sql.Connection connection = connector.connect();

			String insertQuery = "UPDATE alertahabitacion SET cod_sala = ?, emp1 = ?, emp2 = ?, temp = ?, tipo = ?, fecha = ? WHERE ";
			PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(insertQuery);
			// Leemos por cada elemento del array

			preparedStatement.setInt(1, cod_sala);
			preparedStatement.setInt(2, emp1);
			preparedStatement.setInt(3, emp2);
			preparedStatement.setDouble(4, temp);
			preparedStatement.setString(5, tipo);
			preparedStatement.setString(6, fecha);

			preparedStatement.addBatch(); // Agregamos la consulta al lote para ejecución en bloque

			// Ejecutamos el lote de inserciones
			preparedStatement.executeBatch();

			// Cerramos la conexión
			connector.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	// CONVERTIR A JSON
	private static String convertirMapAJSON(Map<String, Map<String, String>> map) {
        String jsonString = "";

        try {
            // Crear un ObjectMapper (parte de la librería Jackson)
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertir el Map a JSON
            jsonString = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
