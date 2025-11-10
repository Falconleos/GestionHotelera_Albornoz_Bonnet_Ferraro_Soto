package manejoJSON;

import claseHotel.Hotel;
import clases.*;
import gestor.*;
import enums.Rol;
import enums.TipoPago;
import enums.Turno;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionJSONserializar {

    public static void modificarJson(Hotel hotel) {

        try {

            // OBJETO RAÍZ
            JSONObject root = new JSONObject();
            JSONObject jHotel = new JSONObject();

            // DATOS BÁSICOS
            jHotel.put("nombre", hotel.getNombre());
            jHotel.put("ubicacion", hotel.getUbicacion());

            // HABITACIONES
            JSONArray jHabitaciones = new JSONArray();
            for (Habitacion h : hotel.getGestorHabitacion().getLista()) {
                JSONObject jH = new JSONObject();
                jH.put("idHabitacion", h.getIdHabitacion());
                jH.put("numero", h.getNumero());
                jH.put("tipo", h.getTipo());
                jH.put("capacidadMaxima", h.getCapacidadMaxima());
                jH.put("precio", h.getPrecio());
                jH.put("descripcion", h.getDescripcion());
                jHabitaciones.put(jH);
            }
            jHotel.put("gestorHabitacion", new JSONObject().put("lista", jHabitaciones));

            // CLIENTES
            JSONArray jClientes = new JSONArray();

            for (Cliente c : hotel.getGestorCliente().getLista()) {
                JSONObject jC = new JSONObject();

                jC.put("idPersona", c.getIdPersona());
                jC.put("dni", c.getDni());
                jC.put("nombre", c.getNombre());
                jC.put("apellido", c.getApellido());
                jC.put("celular", c.getCelular());

                // fechaAlta (null-safe)
                if (c.getFechaAlta() != null) {
                    jC.put("fechaAlta", c.getFechaAlta().toString());
                } else {
                    jC.put("fechaAlta", JSONObject.NULL);
                }

                // comentarios: siempre JSON array
                if (c.getComentarios() != null) {
                    jC.put("comentarios", new JSONArray(c.getComentarios()));
                } else {
                    jC.put("comentarios", new JSONArray());
                }

                jC.put("ciudadOrigen", c.getCiudadOrigen());

                jClientes.put(jC);
            }

            jHotel.put("gestorCliente", new JSONObject().put("lista", jClientes));

            // PERSONAL
            JSONArray jPersonales = new JSONArray();

            for (Personal p : hotel.getGestorPersonal().getLista()) {
                JSONObject jP = new JSONObject();

                jP.put("idPersona", p.getIdPersona());
                jP.put("dni", p.getDni());
                jP.put("nombre", p.getNombre());
                jP.put("apellido", p.getApellido());
                jP.put("celular", p.getCelular());

                // fechaAlta (null-safe)
                if (p.getFechaAlta() != null) {
                    jP.put("fechaAlta", p.getFechaAlta().toString());
                } else {
                    jP.put("fechaAlta", JSONObject.NULL);
                }

                // ENUMS → string exacto como en JSON
                jP.put("rol", p.getRol().name());
                jP.put("turno", p.getTurno().name());

                jPersonales.put(jP);
            }

            jHotel.put("gestorPersonal", new JSONObject().put("lista", jPersonales));

            // SERVICIOS
            JSONArray jServicios = new JSONArray();

            for (Servicio s : hotel.getGestorServicio().getLista()) {
                JSONObject jS = new JSONObject();
                jS.put("idServicio", s.getIdServicio());
                jS.put("detalle", s.getDetalle());
                jS.put("precio", s.getPrecio());
                jServicios.put(jS);
            }

            jHotel.put("gestorServicio", new JSONObject().put("lista", jServicios));

            // RESERVAS
            JSONArray jReservas = new JSONArray();

            for (Reserva r : hotel.getGestorReserva().getLista()) {
                JSONObject jR = new JSONObject();

                jR.put("idReserva", r.getIdReserva());
                jR.put("fechaIngreso", r.getFechaIngreso().toString());
                jR.put("fechaEgreso", r.getFechaEgreso().toString());
                jR.put("pax", r.getPax());
                jR.put("cantidadNoches", r.getCantidadNoches());
                jR.put("valor", r.getValor());
                jR.put("nombreReferencia", r.getNombreReferencia());
                jR.put("apellidoReferencia", r.getApellidoReferencia());
                jR.put("celularReferencia", r.getCelularReferencia());
                jR.put("detallesExtra", r.getDetallesExtra());
                jR.put("personalResponsable", r.getPersonalResponsable());

                // ---- HABITACION anidada ----
                Habitacion h = r.getHabitacion();
                JSONObject jH = new JSONObject();
                jH.put("idHabitacion", h.getIdHabitacion());
                jH.put("numero", h.getNumero());
                jH.put("tipo", h.getTipo());
                jH.put("capacidadMaxima", h.getCapacidadMaxima());
                jH.put("precio", h.getPrecio());
                jH.put("descripcion", h.getDescripcion());

                jR.put("habitacion", jH);

                jReservas.put(jR);
            }
            jHotel.put("gestorReserva", new JSONObject().put("lista", jReservas));

            // ESTADIAS
            JSONArray jEstadias = new JSONArray();

            for (Estadia e : hotel.getGestorEstadia().getLista()) {

                JSONObject jE = new JSONObject();

                jE.put("idEstadia", e.getIdEstadia());
                jE.put("pax", e.getPax());
                jE.put("fechaCheckIn", e.getFechaCheckIn().toString());
                jE.put("fechaCheckOut", e.getFechaCheckOut().toString());
                jE.put("numHabitacion", e.getNumHabitacion());
                jE.put("cancelada", e.isCancelada());

                // fechaCancelacion (null-safe)
                if (e.getFechaCancelacion() != null) {
                    jE.put("fechaCancelacion", e.getFechaCancelacion().toString());
                } else {
                    jE.put("fechaCancelacion", JSONObject.NULL);
                }

                // ---- CLIENTE anidado ----
                Cliente c = e.getCliente();
                JSONObject jC = new JSONObject();
                jC.put("idPersona", c.getIdPersona());
                jC.put("dni", c.getDni());
                jC.put("nombre", c.getNombre());
                jC.put("apellido", c.getApellido());
                jC.put("celular", c.getCelular());
                jC.put("fechaAlta", c.getFechaAlta().toString());
                jC.put("comentarios", new JSONArray(c.getComentarios()));
                jC.put("ciudadOrigen", c.getCiudadOrigen());
                jE.put("cliente", jC);

                // ---- RESERVA anidada ----
                Reserva r = e.getReserva();
                JSONObject jR = new JSONObject();
                jR.put("idReserva", r.getIdReserva());
                jR.put("fechaIngreso", r.getFechaIngreso().toString());
                jR.put("fechaEgreso", r.getFechaEgreso().toString());
                jR.put("pax", r.getPax());
                jR.put("cantidadNoches", r.getCantidadNoches());
                jR.put("valor", r.getValor());
                jR.put("nombreReferencia", r.getNombreReferencia());
                jR.put("apellidoReferencia", r.getApellidoReferencia());
                jR.put("celularReferencia", r.getCelularReferencia());
                jR.put("detallesExtra", r.getDetallesExtra());
                jR.put("personalResponsable", r.getPersonalResponsable());

                // Habitacion dentro de la Reserva
                Habitacion h = r.getHabitacion();
                JSONObject jH = new JSONObject();
                jH.put("idHabitacion", h.getIdHabitacion());
                jH.put("numero", h.getNumero());
                jH.put("tipo", h.getTipo());
                jH.put("capacidadMaxima", h.getCapacidadMaxima());
                jH.put("precio", h.getPrecio());
                jH.put("descripcion", h.getDescripcion());
                jR.put("habitacion", jH);

                jE.put("reserva", jR);

                // ---- Servicios consumidos ----
                JSONArray jServLista = new JSONArray();
                for (Servicio s : e.getListaServicios()) {
                    JSONObject jS = new JSONObject();
                    jS.put("idServicio", s.getIdServicio());
                    jS.put("detalle", s.getDetalle());
                    jS.put("precio", s.getPrecio());
                    jServLista.put(jS);
                }
                jE.put("listaServicios", jServLista);

                jEstadias.put(jE);
            }

            jHotel.put("gestorEstadia", new JSONObject().put("lista", jEstadias));

            // CUENTAS
            JSONArray jCuentas = new JSONArray();

            for (Cuenta cta : hotel.getGestorCuenta().getLista()) {

                JSONObject jCuenta = new JSONObject();

                jCuenta.put("id", cta.getId());
                jCuenta.put("total", cta.getTotal());
                jCuenta.put("pago", cta.isPago());

                // ---- ESTADIA COMPLETA ----
                Estadia e = cta.getEstadia();
                JSONObject jE = new JSONObject();

                jE.put("idEstadia", e.getIdEstadia());
                jE.put("pax", e.getPax());
                jE.put("fechaCheckIn", e.getFechaCheckIn().toString());
                jE.put("fechaCheckOut", e.getFechaCheckOut().toString());
                jE.put("numHabitacion", e.getNumHabitacion());
                jE.put("cancelada", e.isCancelada());

                // fechaCancelacion null-safe
                if (e.getFechaCancelacion() != null) {
                    jE.put("fechaCancelacion", e.getFechaCancelacion().toString());
                } else {
                    jE.put("fechaCancelacion", JSONObject.NULL);
                }

                // ---- CLIENTE dentro de ESTADIA ----
                Cliente c = e.getCliente();
                JSONObject jC = new JSONObject();

                jC.put("idPersona", c.getIdPersona());
                jC.put("dni", c.getDni());
                jC.put("nombre", c.getNombre());
                jC.put("apellido", c.getApellido());
                jC.put("celular", c.getCelular());
                jC.put("fechaAlta", c.getFechaAlta().toString());
                jC.put("comentarios", new JSONArray(c.getComentarios()));
                jC.put("ciudadOrigen", c.getCiudadOrigen());

                jE.put("cliente", jC);

                // ---- RESERVA dentro de ESTADIA ----
                Reserva r = e.getReserva();
                JSONObject jR = new JSONObject();

                jR.put("idReserva", r.getIdReserva());
                jR.put("fechaIngreso", r.getFechaIngreso().toString());
                jR.put("fechaEgreso", r.getFechaEgreso().toString());
                jR.put("pax", r.getPax());
                jR.put("cantidadNoches", r.getCantidadNoches());
                jR.put("valor", r.getValor());
                jR.put("nombreReferencia", r.getNombreReferencia());
                jR.put("apellidoReferencia", r.getApellidoReferencia());
                jR.put("celularReferencia", r.getCelularReferencia());
                jR.put("detallesExtra", r.getDetallesExtra());
                jR.put("personalResponsable", r.getPersonalResponsable());

                // HABITACION dentro de RESERVA
                Habitacion h = r.getHabitacion();
                JSONObject jH = new JSONObject();

                jH.put("idHabitacion", h.getIdHabitacion());
                jH.put("numero", h.getNumero());
                jH.put("tipo", h.getTipo());
                jH.put("capacidadMaxima", h.getCapacidadMaxima());
                jH.put("precio", h.getPrecio());
                jH.put("descripcion", h.getDescripcion());

                jR.put("habitacion", jH);

                jE.put("reserva", jR);

                // ---- SERVICIOS dentro de ESTADIA ----
                JSONArray jServLista = new JSONArray();
                for (Servicio s : e.getListaServicios()) {
                    JSONObject jS = new JSONObject();
                    jS.put("idServicio", s.getIdServicio());
                    jS.put("detalle", s.getDetalle());
                    jS.put("precio", s.getPrecio());
                    jServLista.put(jS);
                }
                jE.put("listaServicios", jServLista);

                // Guardar estadia completa dentro de cuenta
                jCuenta.put("estadia", jE);

                jCuentas.put(jCuenta);
            }

            jHotel.put("gestorCuenta", new JSONObject().put("lista", jCuentas));

            // PAGOS
            JSONArray jPagos = new JSONArray();

            for (Pago p : hotel.getGestorPago().getLista()) {

                JSONObject jPago = new JSONObject();

                jPago.put("idPago", p.getIdPago());
                jPago.put("nombre", p.getNombre());
                jPago.put("apellido", p.getApellido());
                jPago.put("monto", p.getMonto());
                jPago.put("tipoPago", p.getTipoPago().name());
                jPago.put("descuento", p.getDescuento());
                jPago.put("total", p.getTotal());

                // ---- CUENTA dentro del pago ----
                Cuenta cta = p.getCuenta();
                JSONObject jCuenta = new JSONObject();

                jCuenta.put("id", cta.getId());
                jCuenta.put("total", cta.getTotal());
                jCuenta.put("pago", cta.isPago());

                // ---- ESTADIA ----
                Estadia e = cta.getEstadia();
                JSONObject jE = new JSONObject();

                jE.put("idEstadia", e.getIdEstadia());
                jE.put("pax", e.getPax());
                jE.put("fechaCheckIn", e.getFechaCheckIn().toString());
                jE.put("fechaCheckOut", e.getFechaCheckOut().toString());
                jE.put("numHabitacion", e.getNumHabitacion());
                jE.put("cancelada", e.isCancelada());

                if (e.getFechaCancelacion() != null)
                    jE.put("fechaCancelacion", e.getFechaCancelacion().toString());
                else
                    jE.put("fechaCancelacion", JSONObject.NULL);

                // ---- CLIENTE dentro de ESTADIA ----
                Cliente c = e.getCliente();
                JSONObject jC = new JSONObject();

                jC.put("idPersona", c.getIdPersona());
                jC.put("dni", c.getDni());
                jC.put("nombre", c.getNombre());
                jC.put("apellido", c.getApellido());
                jC.put("celular", c.getCelular());
                jC.put("fechaAlta", c.getFechaAlta().toString());
                jC.put("comentarios", new JSONArray(c.getComentarios()));
                jC.put("ciudadOrigen", c.getCiudadOrigen());

                jE.put("cliente", jC);

                // ---- RESERVA dentro de ESTADIA ----
                Reserva r = e.getReserva();
                JSONObject jR = new JSONObject();

                jR.put("idReserva", r.getIdReserva());
                jR.put("fechaIngreso", r.getFechaIngreso().toString());
                jR.put("fechaEgreso", r.getFechaEgreso().toString());
                jR.put("pax", r.getPax());
                jR.put("cantidadNoches", r.getCantidadNoches());
                jR.put("valor", r.getValor());
                jR.put("nombreReferencia", r.getNombreReferencia());
                jR.put("apellidoReferencia", r.getApellidoReferencia());
                jR.put("celularReferencia", r.getCelularReferencia());
                jR.put("detallesExtra", r.getDetallesExtra());
                jR.put("personalResponsable", r.getPersonalResponsable());

                // Habitacion dentro de reserva
                Habitacion h = r.getHabitacion();
                JSONObject jH = new JSONObject();

                jH.put("idHabitacion", h.getIdHabitacion());
                jH.put("numero", h.getNumero());
                jH.put("tipo", h.getTipo());
                jH.put("capacidadMaxima", h.getCapacidadMaxima());
                jH.put("precio", h.getPrecio());
                jH.put("descripcion", h.getDescripcion());

                jR.put("habitacion", jH);

                jE.put("reserva", jR);

                // ---- servicios dentro de estadia ----
                JSONArray jServLista = new JSONArray();
                for (Servicio s : e.getListaServicios()) {
                    JSONObject jS = new JSONObject();
                    jS.put("idServicio", s.getIdServicio());
                    jS.put("detalle", s.getDetalle());
                    jS.put("precio", s.getPrecio());
                    jServLista.put(jS);
                }
                jE.put("listaServicios", jServLista);

                // agregar estadia completa a cuenta
                jCuenta.put("estadia", jE);

                // agregar cuenta completa al pago
                jPago.put("cuenta", jCuenta);

                jPagos.put(jPago);
            }

            jHotel.put("gestorPago", new JSONObject().put("lista", jPagos));





            // ARMAR RAÍZ FINAL
            root.put("hotel", jHotel);

            // GRABAR EN ARCHIVO
            JSONUtiles.grabar(root);

            System.out.println("✅ JSON actualizado correctamente.");


        }catch (JSONException e){
            System.out.println(e.getMessage());
        }
    }

}
