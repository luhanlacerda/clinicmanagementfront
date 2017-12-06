package beans;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import classesBasicas.Convenio;

@FacesConverter(value = "beerConverter")
public class ConvenioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String convenioId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{convenioConverter}", CrudPaciente.class);

        CrudPaciente crudPaciente = (CrudPaciente)vex.getValue(ctx.getELContext());
        return crudPaciente.getConvenio(Integer.valueOf(convenioId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object convenio) {
        return ((Convenio)convenio).getId() + "";
    }

}