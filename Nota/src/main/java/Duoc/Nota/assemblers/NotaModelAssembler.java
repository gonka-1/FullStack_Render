package Duoc.Nota.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Duoc.Nota.controller.NotaControllerV2;
import Duoc.Nota.models.Nota;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class NotaModelAssembler implements RepresentationModelAssembler<Nota, EntityModel<Nota>> {

    @Override
    public EntityModel<Nota> toModel(Nota nota) {
        return EntityModel.of(nota,
                linkTo(methodOn(NotaControllerV2.class).buscarPorId(nota.getId())).withSelfRel(),
                linkTo(methodOn(NotaControllerV2.class).listarTodasLasNotas()).withRel("notas"));
    }
}