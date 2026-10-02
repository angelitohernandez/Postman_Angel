package mx.edu.utez.proyecto1C.controller;
import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.RentaVehiculoDTO;
import mx.edu.utez.proyecto1C.service.RentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services/rentas")
public class RentaController {

    @Autowired
    private RentaService rentaService;

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizarRenta(@Valid @RequestBody RentaVehiculoDTO dto) {
        Map<String, Object> resultado = rentaService.calcularRenta(dto);
        return ResponseEntity.ok(resultado);
    }
}
