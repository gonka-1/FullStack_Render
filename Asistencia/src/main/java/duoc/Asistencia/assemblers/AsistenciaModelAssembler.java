package duoc.Asistencia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import duoc.Asistencia.controller.AsistenciaControllerV2;
import duoc.Asistencia.model.Asistencia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaModelAssembler implements RepresentationModelAssembler<Asistencia, EntityModel<Asistencia>> {

    @Override
    public EntityModel<Asistencia> toModel(Asistencia asistencia) {
        return EntityModel.of(asistencia,
                linkTo(methodOn(AsistenciaControllerV2.class).getAsistenciaById(asistencia.getId())).withSelfRel(),
                linkTo(methodOn(AsistenciaControllerV2.class).getAllAsistencias()).withRel("asistencias"));
    }
}