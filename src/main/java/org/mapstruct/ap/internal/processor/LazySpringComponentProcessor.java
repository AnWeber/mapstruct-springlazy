package org.mapstruct.ap.internal.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.mapstruct.ap.internal.model.Annotation;
import org.mapstruct.ap.internal.model.Mapper;


public class LazySpringComponentProcessor extends SpringComponentProcessor  {

  @Override
  protected String getComponentModelIdentifier() {
    return "spring_lazy";
  }

  @Override
  protected List<Annotation> getMapperReferenceAnnotations() {
    var annotations = new ArrayList<>(super.getMapperReferenceAnnotations());
    annotations.add(lazy());
    return annotations;
  }

  @Override
  protected List<Annotation> getDelegatorReferenceAnnotations(Mapper mapper) {
    var annotations = new ArrayList<>(super.getDelegatorReferenceAnnotations(mapper));
    annotations.add(lazy());
    return annotations;
  }
  private Annotation lazy() {
    return new Annotation( getTypeFactory().getType( "org.springframework.context.annotation.Lazy" ) );
  }
}
