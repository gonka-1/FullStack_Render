package duoc.Matricula.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import duoc.Matricula.controller.MatriculaControllerV2;
import duoc.Matricula.model.Matricula;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class MatriculaModelAssembler implements RepresentationModelAssembler<Matricula, EntityModel<Matricula>> {

    @Override
    public EntityModel<Matricula> toModel(Matricula matricula) {
        return EntityModel.of(matricula,
                linkTo(methodOn(MatriculaControllerV2.class).getMatriculaById(matricula.getId())).withSelfRel(),
                linkTo(methodOn(MatriculaControllerV2.class).getAllMatriculas()).withRel("matriculas"));
    }
}