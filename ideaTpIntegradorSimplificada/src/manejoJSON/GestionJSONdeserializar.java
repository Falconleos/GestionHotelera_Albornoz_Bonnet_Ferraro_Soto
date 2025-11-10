package manejoJSON;

import claseHotel.Hotel;
import clases.*;
import enums.Rol;
import enums.TipoPago;
import enums.Turno;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionJSONdeserializar {

    public static void cargarHotelDesdeJSON(Hotel hotel) {

        JSONTokener tokener = JSONUtiles.leer("Hotel.json");

        if (tokener == null) {
            System.out.println("No se encontró el archivo Hotel.json");
            return;
        }

        try {
            JSONObject root = new JSONObject(tokener);
            JSONObject jHotel = root.getJSONObject("hotel");

// 1. Nombre y ubicación
            hotel.setNombre(jHotel.getString("nombre"));
            hotel.setUbicacion(jHotel.getString("ubicacion"));

// HABITACIONES
            JSONArray jHabLista = jHotel
                    .getJSONObject("gestorHabitacion")
                    .getJSONArray("lista");
            hotel.getGestorHabitacion().setLista(mapeoHabitaciones(jHabLista));

// CLIENTES
            JSONArray jCliLista = jHotel
                    .getJSONObject("gestorCliente")
                    .getJSONArray("lista");
            hotel.getGestorCliente().setLista(mapeoClientes(jCliLista));
// PERSONAL
            JSONArray jPersonalLista = jHotel
                    .getJSONObject("gestorPersonal")
                    .getJSONArray("lista");
            hotel.getGestorPersonal().setLista(mapeoPersonales(jPersonalLista));

// SERVICIOS
            JSONArray jSerLista = jHotel
                    .getJSONObject("gestorServicio")
                    .getJSONArray("lista");
            hotel.getGestorServicio().setLista(mapeoServicios(jSerLista));

// RESERVAS
            JSONArray jResLista = jHotel
                    .getJSONObject("gestorReserva")
                    .getJSONArray("lista");
            hotel.getGestorReserva().setLista(mapeoReservas(jResLista));

// ESTADIAS
            JSONArray jEstLista = jHotel
                    .getJSONObject("gestorEstadia")
                    .getJSONArray("lista");
            hotel.getGestorEstadia().setLista(mapeoEstadias(jEstLista));

// CUENTAS
            JSONArray jCtaLista = jHotel
                    .getJSONObject("gestorCuenta")
                    .getJSONArray("lista");
            hotel.getGestorCuenta().setLista(mapeoCuentas(jCtaLista));

// PAGOS
            JSONArray jPagLista = jHotel
                    .getJSONObject("gestorPago")
                    .getJSONArray("lista");
            hotel.getGestorPago().setLista(mapeoPagos(jPagLista));

            System.out.println("Hotel cargado exitosamente desde JSON.");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    //mapeo de habitaciones
    public static List<Habitacion> mapeoHabitaciones(JSONArray jHabitaciones) {
        List<Habitacion> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jHabitaciones.length(); i++) {
            try {
                JSONObject jH = jHabitaciones.getJSONObject(i);
                Habitacion h = mapeoHabitacion(jH);
                lista.add(h);

                // Buscar el mayor idPersona
                if (h.getIdHabitacion() > maxId) {
                    maxId = h.getIdHabitacion();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        //Actualizar contador de Habitacion con el ID mayor encontrado
        Habitacion.actualizarContador(maxId);

        return lista;
    }
    //mapeo habitacion
    public static Habitacion mapeoHabitacion(JSONObject jHab) {
        Habitacion h = new Habitacion();

        try {
            h.setIdHabitacion(jHab.getInt("idHabitacion"));
            h.setNumero(jHab.getInt("numero"));
            h.setTipo(jHab.getString("tipo"));
            h.setCapacidadMaxima(jHab.getInt("capacidadMaxima"));
            h.setPrecio(jHab.getDouble("precio"));
            h.setDescripcion(jHab.getString("descripcion"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return h;
    }

    //mapeo de cliente
    public static Cliente mapeoCliente(JSONObject jC) {
        Cliente c = new Cliente();

        try {
            c.setIdPersona(jC.getInt("idPersona"));
            c.setDni(jC.getString("dni"));
            c.setNombre(jC.getString("nombre"));
            c.setApellido(jC.getString("apellido"));
            c.setCelular(jC.getString("celular"));

            // Comentarios como lista de strings
            List<String> comentarios = new ArrayList<>();
            JSONArray jComentarios = jC.optJSONArray("comentarios");
            if (jComentarios != null) {
                for (int i = 0; i < jComentarios.length(); i++) {
                    comentarios.add(jComentarios.getString(i));
                }
            } else if (jC.has("comentarios")) {
                comentarios.add(jC.getString("comentarios"));
            }
            c.setComentarios(comentarios);

            c.setCiudadOrigen(jC.getString("ciudadOrigen"));

            // Manejo seguro de fechaAlta
            if (!jC.isNull("fechaAlta")) {
                String fecha = jC.getString("fechaAlta");
                if (fecha != null && !fecha.isEmpty()) {
                    c.setFechaAlta(LocalDate.parse(fecha));
                }
            } else {
                c.setFechaAlta(null);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

    //mapeo clientes
    public static List<Cliente> mapeoClientes(JSONArray jClientes) {
        List<Cliente> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jClientes.length(); i++) {
            try {
                JSONObject jC = jClientes.getJSONObject(i);
                Cliente c = mapeoCliente(jC);
                lista.add(c);

                // Buscar el mayor idPersona
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        //Actualizar contador de Persona con el ID mayor encontrado
        Persona.actualizarContador(maxId);

        return lista;
    }

    //mapeo Personal
    public static Personal mapeoPersonal(JSONObject jP) {
        Personal p = new Personal();

        try {
            p.setIdPersona(jP.getInt("idPersona"));
            p.setDni(jP.getString("dni"));
            p.setNombre(jP.getString("nombre"));
            p.setApellido(jP.getString("apellido"));
            p.setCelular(jP.getString("celular"));

            // Manejo seguro de fechaAlta
            if (!jP.isNull("fechaAlta")) {
                String fecha = jP.getString("fechaAlta");
                if (fecha != null && !fecha.isEmpty()) {
                    p.setFechaAlta(LocalDate.parse(fecha));
                }
            } else {
                p.setFechaAlta(null);
            }

            // Mapear enums
            p.setRol(Rol.valueOf(jP.getString("rol")));
            p.setTurno(Turno.valueOf(jP.getString("turno")));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    //mapeo personales
    public static List<Personal> mapeoPersonales(JSONArray jPersonales) {
        List<Personal> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jPersonales.length(); i++) {
            try {
                JSONObject jP = jPersonales.getJSONObject(i);
                Personal p = mapeoPersonal(jP);
                lista.add(p);

                // Buscar el mayor idPersona
                if (p.getIdPersona() > maxId) {
                    maxId = p.getIdPersona();
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        //Actualizar contador de Persona con el ID mayor encontrado
        Persona.actualizarContador(maxId);
        return lista;
    }

    //mapeo de Servicios
    public static Servicio mapeoServicio(JSONObject jS) {
        Servicio s = new Servicio();

        try {
            s.setIdServicio(jS.getInt("idServicio"));
            s.setDetalle(jS.getString("detalle"));
            s.setPrecio(jS.getDouble("precio"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return s;
    }

    //mapeo Servicios
    public static List<Servicio> mapeoServicios(JSONArray jServicios) {
        List<Servicio> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jServicios.length(); i++) {
            try {
                JSONObject jS = jServicios.getJSONObject(i);
                Servicio s = mapeoServicio(jS);
                lista.add(s);

                // Buscar el mayor idServicio
                if (s.getIdServicio() > maxId) {
                    maxId = s.getIdServicio();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        //Actualizar contador de Servicio con el ID mayor encontrado
        Servicio.actualizarContador(maxId);
        return lista;
    }

    //mapeo de reservas
    public static List<Reserva> mapeoReservas(JSONArray arr) {
        List<Reserva> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < arr.length(); i++) {
            JSONObject jR = null;
            try {
                jR = arr.getJSONObject(i);
                Reserva r = new Reserva();
                r = mapeoReserva(jR);
                lista.add(r); // invoca al mapeo individual

                // Buscar el mayor idReserva
                if (r.getIdReserva() > maxId) {
                    maxId = r.getIdReserva();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }

        //Actualizar contador de Reserva con el ID mayor encontrado
        Reserva.actualizarContador(maxId);
        return lista;
    }

    //mapeo de reserva
    public static Reserva mapeoReserva(JSONObject jR) {
        Reserva r = new Reserva();

        try {
            r.setIdReserva(jR.getInt("idReserva"));
            r.setFechaIngreso(LocalDate.parse(jR.getString("fechaIngreso")));
            r.setFechaEgreso(LocalDate.parse(jR.getString("fechaEgreso")));
            r.setPax(jR.getInt("pax"));
            r.setCantidadNoches(jR.getLong("cantidadNoches"));

            // Trae la habitación completa (anidada)
            JSONObject jH = jR.getJSONObject("habitacion");
            r.setHabitacion(mapeoHabitacion(jH));

            r.setValor(jR.getDouble("valor"));
            r.setNombreReferencia(jR.getString("nombreReferencia"));
            r.setApellidoReferencia(jR.getString("apellidoReferencia"));
            r.setCelularReferencia(jR.getString("celularReferencia"));
            r.setDetallesExtra(jR.getString("detallesExtra"));
            r.setPersonalResponsable(jR.getString("personalResponsable"));

            return r;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //mapeo estadia
    public static List<Estadia> mapeoEstadias(JSONArray jEstadias) {
        List<Estadia> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jEstadias.length(); i++) {
            JSONObject jEstadia = null;
            try {
                jEstadia = jEstadias.getJSONObject(i);
                Estadia e = new Estadia();
                e = mapeoEstadia(jEstadia);
                lista.add(e); // invoca al mapeo individual

                // Buscar el mayor idCuenta
                if (e.getIdEstadia() > maxId) {
                    maxId = e.getIdEstadia();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        //Actualizar contador de Estadia con el ID mayor encontrado
        Estadia.actualizarContador(maxId);
        return lista;
    }
    //mapeo estadia
    public static Estadia mapeoEstadia(JSONObject jEstadia) {
        Estadia estadia = new Estadia();

        try {
            estadia.setIdEstadia(jEstadia.getInt("idEstadia"));
            estadia.setCliente(mapeoCliente(jEstadia.getJSONObject("cliente")));
            estadia.setReserva(mapeoReserva(jEstadia.getJSONObject("reserva")));
            estadia.setPax(jEstadia.getInt("pax"));
            estadia.setFechaCheckIn(LocalDate.parse(jEstadia.getString("fechaCheckIn")));
            estadia.setFechaCheckOut(LocalDate.parse(jEstadia.getString("fechaCheckOut")));
            estadia.setListaServicios(mapeoServicios(jEstadia.getJSONArray("listaServicios")));
            estadia.setNumHabitacion(jEstadia.getInt("numHabitacion"));
            estadia.setCancelada(jEstadia.getBoolean("cancelada"));

            // Manejo seguro de fechaCancelacion
            if (!jEstadia.isNull("fechaCancelacion")) {
                String fecha = jEstadia.getString("fechaCancelacion");
                if (fecha != null && !fecha.isEmpty()) {
                    estadia.setFechaCancelacion(LocalDate.parse(fecha));
                }
            } else {
                estadia.setFechaCancelacion(null);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return estadia;
    }

    //mapeo cuentas
    public static List<Cuenta> mapeoCuentas(JSONArray jCuentas) {
        List<Cuenta> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jCuentas.length(); i++) {
            JSONObject jCuenta = null;
            try {
                jCuenta = jCuentas.getJSONObject(i);
                Cuenta c = new Cuenta();
                c = mapeoCuenta(jCuenta);
                lista.add(c); // invoca al mapeo individual

                // Buscar el mayor idCuenta
                if (c.getId() > maxId) {
                    maxId = c.getId();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        //Actualizar contador de Cuenta con el ID mayor encontrado
        Cuenta.actualizarContador(maxId);
        return lista;
    }

    //mapeo cuenta
    public static Cuenta mapeoCuenta(JSONObject jCuenta) {
        Cuenta cuenta = new Cuenta();

        try {
            cuenta.setId(jCuenta.getInt("id"));
            cuenta.setEstadia(mapeoEstadia(jCuenta.getJSONObject("estadia")));
            cuenta.setPago(jCuenta.getBoolean("pago"));
            cuenta.setTotal(jCuenta.getDouble("total"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return cuenta;
    }

    //mapeo pagos
    public static List<Pago>mapeoPagos(JSONArray jPagos) {
        List<Pago> lista = new ArrayList<>();
        int maxId = 0;

        for (int i = 0; i < jPagos.length(); i++) {
            try {
                JSONObject jPago = jPagos.getJSONObject(i);
                Pago p = new Pago();
                p=mapeoPago(jPago);
                lista.add(p);

                // Buscar el mayor idCuenta
                if (p.getIdPago() > maxId) {
                    maxId = p.getIdPago();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }

        //Actualizar contador de Pago con el ID mayor encontrado
        Pago.actualizarContador(maxId);

        return lista;
    }

    //mapeo pago
    public static Pago mapeoPago(JSONObject jPago) {
        Pago pago = new Pago();

        try {
            pago.setIdPago(jPago.getInt("idPago"));
            pago.setNombre(jPago.getString("nombre"));
            pago.setApellido(jPago.getString("apellido"));
            pago.setMonto(jPago.getDouble("monto"));
            pago.setCuenta(mapeoCuenta(jPago.getJSONObject("cuenta")));
            pago.setTipoPago(TipoPago.valueOf(jPago.getString("tipoPago")));
            pago.setDescuento(jPago.optInt("descuento"));
            pago.setTotal(jPago.getDouble("total"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return pago;
    }










}
