package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CotizacionEnvioDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    @Max(value = 50, message = "No se aceptan paquetes que pesen más de 50 kg")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser mayor a 0")
    @Max(value = 150, message = "El largo no puede ser superior a 150 cm")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El ancho debe ser mayor a 0")
    @Max(value = 150, message = "El ancho no puede ser superior a 150 cm")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser mayor a 0")
    @Max(value = 150, message = "El alto no puede ser superior a 150 cm")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    @Pattern(
            regexp = "^(ESTANDAR|EXPRESS|MISMO_DIA)$",
            message = "El tipo de envío debe ser ESTANDAR, EXPRESS o MISMO_DIA"
    )
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}
