package duoc.Carrera.assemblers;

import duoc.Carrera.controller.CarreraControllerV2;
import duoc.Carrera.model.Carrera;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarreraModelAssembler implements RepresentationModelAssembler<Carrera, EntityModel<Carrera>> {

    @Override
    public EntityModel<Carrera> toModel(Carrera carrera) {

        return EntityModel.of(carrera,
                linkTo(methodOn(CarreraControllerV2.class)
                        .getCarreraById(carrera.getId()))
                        .withSelfRel(),

                linkTo(methodOn(CarreraControllerV2.class)
                        .getCarreraById(carrera.getId()))
                        .withRel("carerras")
        );
    }
}