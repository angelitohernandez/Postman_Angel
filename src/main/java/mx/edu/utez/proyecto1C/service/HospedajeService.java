package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.HospedajeDTO;
import mx.edu.utez.proyecto1C.exeption.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HospedajeService {

    public Map<String, Object> calcularHospedaje(HospedajeDTO dto) {
        String hab = dto.getTipoHabitacion().toUpperCase();
        int personas = dto.getNumeroHuespedes();

        if ("INDIVIDUAL".equals(hab) && personas > 1) {
            throw new BadRequestException("La habitación INDIVIDUAL no acepta más de 1 persona");
        }
        if ("DOBLE".equals(hab) && personas > 2) {
            throw new BadRequestException("La habitación DOBLE no acepta más de 2 personas");
        }
        if ("SUITE".equals(hab) && personas > 4) {
            throw new BadRequestException("La SUITE no acepta más de 4 personas");
        }

        double costoNoche;
        switch (hab) {
            case "INDIVIDUAL":
                costoNoche = 700.0;
                break;
            case "DOBLE":
                costoNoche = 1100.0;
                break;
            case "SUITE":
                costoNoche = 1800.0;
                break;
            default:
                throw new BadRequestException("Tipo de habitación no válido");
        }

        double costoHospedaje = costoNoche * dto.getNumeroNoches();

        if ("BAJA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje -= costoHospedaje * 0.10; // Descuento del 10%
        } else if ("ALTA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje += costoHospedaje * 0.25; // Cargo del 25%
        }

        if (dto.getNumeroNoches() >= 7) {
            costoHospedaje -= costoHospedaje * 0.08;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeDesayuno())) {
            costoDesayuno = dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeEstacionamiento())) {
            costoEstacionamiento = dto.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;

        double impuesto = subtotal * 0.04;

        double total = subtotal + impuesto;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreHuesped", dto.getNombreHuesped());
        respuesta.put("tipoHabitacion", hab);
        respuesta.put("numeroNoches", dto.getNumeroNoches());
        respuesta.put("costoHospedaje", costoHospedaje);
        respuesta.put("costoDesayuno", costoDesayuno);
        respuesta.put("costoEstacionamiento", costoEstacionamiento);
        respuesta.put("subtotal", subtotal);
        respuesta.put("impuestoHospedaje", impuesto);
        respuesta.put("total", total);
        respuesta.put("mensaje", "Cotización de hospedaje calculada con éxito");

        return respuesta;
    }
}
