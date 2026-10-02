package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.CotizacionEnvioDTO;
import mx.edu.utez.proyecto1C.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizarEnvio(@Valid @RequestBody CotizacionEnvioDTO dto) {
        Map<String, Object> resultado = envioService.calcularCostoEnvio(dto);
        return ResponseEntity.ok(resultado);
    }
}
