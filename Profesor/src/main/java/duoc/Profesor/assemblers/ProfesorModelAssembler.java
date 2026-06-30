package duoc.Profesor.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import duoc.Profesor.controller.ProfesorControllerV2;
import duoc.Profesor.model.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {

    @Override
    public EntityModel<Profesor> toModel(Profesor profesor) {

        return EntityModel.of(profesor,
                linkTo(methodOn(ProfesorControllerV2.class)
                        .getProfesorById(profesor.getId()))
                        .withSelfRel(),

                linkTo(methodOn(ProfesorControllerV2.class)
                        .getAllProfesores())
                        .withRel("profesores")
        );
    }
}