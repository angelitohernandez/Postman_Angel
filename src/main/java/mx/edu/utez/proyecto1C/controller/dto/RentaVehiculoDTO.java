package mx.edu.utez.proyecto1C.controller.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentaVehiculoDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Min(value = 18, message = "El conductor debe ser mayor o igual a 18 años")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    @Pattern(
            regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$",
            message = "El tipo de vehículo debe ser COMPACTO, SEDAN, SUV o CAMIONETA"
    )
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Min(value = 1, message = "Los días de renta deben ser al menos 1")
    @Max(value = 30, message = "La renta no puede superar los 30 días")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros estimados no pueden ser negativos")
    @Max(value = 5000, message = "Los kilómetros estimados no pueden superar los 5,000 km")
    private Double kilometrosEstimados;

    @NotNull(message = "Debe indicar si contrata seguro completo")
    private Boolean seguroCompleto;
}
