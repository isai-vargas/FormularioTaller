package com.example.hojatrabajo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrdenTrabajoActivity extends AppCompatActivity {

    private EditText editTextId, editTextFecha, editTextNombreCliente, editTextTelefonoCliente, editTextCorreoCliente,
            editTextMarcaVehiculo, editTextModeloVehiculo, editTextAnoVehiculo, editTextNumeroPlaca,
            editTextNombreMecanico, editTextIdEmpleadoMecanico, editTextEstado, editTextFechaInicio, editTextFechaFinalizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_trabajo);

        // Inicializar los campos
        editTextId = findViewById(R.id.editTextId);
        editTextFecha = findViewById(R.id.editTextFecha);
        editTextNombreCliente = findViewById(R.id.editTextNombreCliente);
        editTextTelefonoCliente = findViewById(R.id.editTextTelefonoCliente);
        editTextCorreoCliente = findViewById(R.id.editTextCorreoCliente);
        editTextMarcaVehiculo = findViewById(R.id.editTextMarcaVehiculo);
        editTextModeloVehiculo = findViewById(R.id.editTextModeloVehiculo);
        editTextAnoVehiculo = findViewById(R.id.editTextAnoVehiculo);
        editTextNumeroPlaca = findViewById(R.id.editTextNumeroPlaca);
        editTextNombreMecanico = findViewById(R.id.editTextNombreMecanico);
        editTextIdEmpleadoMecanico = findViewById(R.id.editTextIdEmpleadoMecanico);
        editTextEstado = findViewById(R.id.editTextEstado);
        editTextFechaInicio = findViewById(R.id.editTextFechaInicio);
        editTextFechaFinalizacion = findViewById(R.id.editTextFechaFinalizacion);

        Button buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(v -> enviarDatos());
    }

    private void enviarDatos() {
        String id = editTextId.getText().toString();
        String fecha = editTextFecha.getText().toString();
        String nombreCliente = editTextNombreCliente.getText().toString();
        String telefonoCliente = editTextTelefonoCliente.getText().toString();
        String correoCliente = editTextCorreoCliente.getText().toString();
        String marcaVehiculo = editTextMarcaVehiculo.getText().toString();
        String modeloVehiculo = editTextModeloVehiculo.getText().toString();
        int anoVehiculo = Integer.parseInt(editTextAnoVehiculo.getText().toString());
        String numeroPlaca = editTextNumeroPlaca.getText().toString();
        String nombreMecanico = editTextNombreMecanico.getText().toString();
        String idEmpleadoMecanico = editTextIdEmpleadoMecanico.getText().toString();
        String estado = editTextEstado.getText().toString();
        String fechaInicio = editTextFechaInicio.getText().toString();
        String fechaFinalizacion = editTextFechaFinalizacion.getText().toString();

        // Crear el objeto JSON
        JSONObject ordenTrabajoJson = new JSONObject();
        try {
            ordenTrabajoJson.put("id", id);
            ordenTrabajoJson.put("fecha", fecha);
            ordenTrabajoJson.put("cliente", new JSONObject()
                    .put("nombre", nombreCliente)
                    .put("telefono", telefonoCliente)
                    .put("correo", correoCliente));
            ordenTrabajoJson.put("vehiculo", new JSONObject()
                    .put("marca", marcaVehiculo)
                    .put("modelo", modeloVehiculo)
                    .put("ano", anoVehiculo)
                    .put("numeroPlaca", numeroPlaca));
            ordenTrabajoJson.put("mecanico", new JSONObject()
                    .put("nombre", nombreMecanico)
                    .put("idEmpleado", idEmpleadoMecanico));
            ordenTrabajoJson.put("estado", estado);
            ordenTrabajoJson.put("fechaInicio", fechaInicio);
            ordenTrabajoJson.put("fechaFinalizacion", fechaFinalizacion);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creando JSON", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar los datos a la API
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/api/ordenes-trabajo");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Enviar el JSON
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = ordenTrabajoJson.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Comprobar la respuesta
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    runOnUiThread(() -> Toast.makeText(this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Error al enviar datos: " + responseCode, Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
