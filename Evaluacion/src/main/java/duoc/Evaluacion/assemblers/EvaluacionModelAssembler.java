package duoc.Evaluacion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import duoc.Evaluacion.controller.EvaluacionControllerV2;
import duoc.Evaluacion.model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionControllerV2.class).buscarPorId(evaluacion.getIdEvaluacion())).withSelfRel(),
                linkTo(methodOn(EvaluacionControllerV2.class).listar()).withRel("evaluaciones"));
    }
}