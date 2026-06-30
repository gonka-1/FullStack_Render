package duoc.Sala.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import duoc.Sala.controller.SalaControllerV2;
import duoc.Sala.model.Sala;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SalaModelAssembler implements RepresentationModelAssembler<Sala, EntityModel<Sala>> {

    @Override
    public EntityModel<Sala> toModel(Sala sala) {
        return EntityModel.of(sala,
                linkTo(methodOn(SalaControllerV2.class).buscarPorId(sala.getIdSala())).withSelfRel(),
                linkTo(methodOn(SalaControllerV2.class).listar()).withRel("salas"));
    }
}