package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.CotizacionEnvioDTO;
import mx.edu.utez.proyecto1C.exeption.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EnvioService {

    public Map<String, Object> calcularCostoEnvio(CotizacionEnvioDTO dto) {
        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();
        if (volumen > 1000000) {
            throw new BadRequestException("No se aceptan paquetes con un volumen superior a 1,000,000 cm³");
        }

        // 1. Costo base
        double costoTotal = 80.0;

        // 2. Agregar $12 por kg
        costoTotal += dto.getPesoKg() * 12.0;

        // 3. Si el volumen supera 50,000 cm³, agregar $100
        if (volumen > 50000) {
            costoTotal += 100.0;
        }

        // 4 y 5. Incrementar según el tipo de envío
        if ("EXPRESS".equalsIgnoreCase(dto.getTipoEnvio())) {
            costoTotal += costoTotal * 0.40; // Aumentar 40%
        } else if ("MISMO_DIA".equalsIgnoreCase(dto.getTipoEnvio())) {
            costoTotal += costoTotal * 0.70; // Aumentar 70%
        }

        // 6. Si el valor declarado supera $10,000, agregar 2% de seguro
        if (dto.getValorDeclarado() > 10000) {
            costoTotal += dto.getValorDeclarado() * 0.02;
        }

        // Armar respuesta con detalle
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("codigoPostal", dto.getCodigoPostal());
        respuesta.put("volumenCm3", volumen);
        respuesta.put("costoTotal", costoTotal);
        respuesta.put("mensaje", "Cotización calculada con éxito");

        return respuesta;
    }
}
