package duoc.Seccion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import duoc.Seccion.controller.SeccionControllerV2;
import duoc.Seccion.model.Seccion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SeccionModelAssembler implements RepresentationModelAssembler<Seccion, EntityModel<Seccion>> {

    @Override
    public EntityModel<Seccion> toModel(Seccion seccion) {
        return EntityModel.of(seccion,
                linkTo(methodOn(SeccionControllerV2.class).buscarPorId(seccion.getIdSeccion())).withSelfRel(),
                linkTo(methodOn(SeccionControllerV2.class).listar()).withRel("secciones"));
    }
}