package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.HospedajeDTO;
import mx.edu.utez.proyecto1C.service.HospedajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services/hospedaje")
public class HospedajeController {

    @Autowired
    private HospedajeService hospedajeService;

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizarHospedaje(@Valid @RequestBody HospedajeDTO dto) {
        Map<String, Object> resultado = hospedajeService.calcularHospedaje(dto);
        return ResponseEntity.ok(resultado);
    }
}