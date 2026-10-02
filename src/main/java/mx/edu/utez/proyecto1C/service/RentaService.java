package mx.edu.utez.proyecto1C.service;
import mx.edu.utez.proyecto1C.controller.dto.RentaVehiculoDTO;
import mx.edu.utez.proyecto1C.exeption.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RentaService {

    public Map<String, Object> calcularRenta(RentaVehiculoDTO dto) {
        if ("CAMIONETA".equalsIgnoreCase(dto.getTipoVehiculo()) && dto.getEdadConductor() < 25) {
            throw new BadRequestException("No se acepta la renta de CAMIONETA a conductores menores de 25 años");
        }

        double costoDiario;
        switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
            default:
                throw new BadRequestException("Tipo de vehículo no válido");
        }

        double costoRenta = costoDiario * dto.getDiasRenta();

        if (dto.getDiasRenta() >= 7) {
            costoRenta -= costoRenta * 0.10;
        }

        double kmIncluidos = dto.getDiasRenta() * 100.0;
        double cargoKmAdicionales = 0.0;
        if (dto.getKilometrosEstimados() > kmIncluidos) {
            double kmExtra = dto.getKilometrosEstimados() - kmIncluidos;
            cargoKmAdicionales = kmExtra * 4.0;
        }

        double cargoEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicionales) * 0.15;
        }

        double costoSeguro = 0.0;
        if (Boolean.TRUE.equals(dto.getSeguroCompleto())) {
            costoSeguro = 180.0 * dto.getDiasRenta();
        }
        double totalPagar = costoRenta + cargoKmAdicionales + cargoEdad + costoSeguro;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreCliente", dto.getNombreCliente());
        respuesta.put("tipoVehiculo", dto.getTipoVehiculo());
        respuesta.put("diasRenta", dto.getDiasRenta());
        respuesta.put("costoRenta", costoRenta);
        respuesta.put("cargoKmAdicionales", cargoKmAdicionales);
        respuesta.put("cargoEdad", cargoEdad);
        respuesta.put("costoSeguro", costoSeguro);
        respuesta.put("totalPagar", totalPagar);
        respuesta.put("mensaje", "Cotización de renta generada con éxito");

        return respuesta;
    }
}
