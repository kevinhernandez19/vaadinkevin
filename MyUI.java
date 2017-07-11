package com.jetbrains;

import javax.servlet.annotation.WebServlet;
import javax.xml.soap.Text;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.steinwedel.messagebox.MessageBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Theme("mytheme")


public class MyUI extends UI {
    Profesor p = new Profesor();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout mainLayout = new VerticalLayout();

        final HorizontalLayout profesorLayout = new HorizontalLayout();
        final TextField nombreProfesorTXT = new TextField("Ingrese Nombre: ");
        final TextField especialidadProfesorTXT = new TextField("Ingrese Especialidad: ");
        final Button agregarProfesorBTN = new Button("Agregue un profesor");

        profesorLayout.addComponents(nombreProfesorTXT,especialidadProfesorTXT,agregarProfesorBTN);

        final HorizontalLayout estudianteLayout = new HorizontalLayout();
        final TextField nombreEstudianteTXT = new TextField("Ingrese Nombre: ");
        final TextField edadEstudianteTXT = new TextField("Ingrese Edad: ");
        final Button agregarEstudianteBTN = new Button("Agregue un estudiante");
        final Grid<Estudiante> listaEGrid = new Grid<>();

        estudianteLayout.addComponents(nombreEstudianteTXT,edadEstudianteTXT,agregarEstudianteBTN,listaEGrid);

        final HorizontalLayout asignaturasLayout = new HorizontalLayout();
        final TextField nombreAsignaturaTXT = new TextField("Asigatura: ");
        final TextField notaAsignaturaTXT = new TextField("Nota: ");
        final TextField cantevalAsignaturaTXT = new TextField("Cantidad de evaluaciones: ");
        final Button agregarAsignaturaBTN = new Button("Agregar asignatura");
        final Grid listadoAsignaturas = new Grid();

        asignaturasLayout.addComponents(nombreAsignaturaTXT,notaAsignaturaTXT,cantevalAsignaturaTXT,
                agregarAsignaturaBTN,listadoAsignaturas);


        
        mainLayout.addComponents(new Label("Información del profesor"),profesorLayout,
                                 new Label("Información del estudiante"),estudianteLayout,
                                 new Label("Información de las asignaturas"),asignaturasLayout);

        agregarProfesorBTN.addClickListener( e -> {
            p.setNombre(nombreProfesorTXT.getValue());
            p.setEspecialidad(especialidadProfesorTXT.getValue());

            MessageBox
                    .create()
                    .withCaption("Profesor")
                    .withMessage(p.getNombre()+" de especialidad "+p.getEspecialidad()+" Agregado.")
                    .withOkButton()
                    .open();

        });

        agregarEstudianteBTN.addClickListener( e-> {
            Estudiante e1 = new Estudiante();

            e1.setNombre(nombreEstudianteTXT.getValue());
            e1.setEdad(Integer.parseInt(edadEstudianteTXT.getValue()));

            p.addEstudiante(e1);

            listaEGrid.removeAllColumns();
            listaEGrid.setItems(p.getEstudiantes());
            listaEGrid.addColumn(Estudiante::getNombre).setCaption("Nombre");
            listaEGrid.addColumn(Estudiante::getEdad).setCaption("Edad");

            MessageBox
                    .create()
                    .withCaption("Estudiante")
                    .withMessage(e1.getNombre()+" de "+e1.getEdad()+" años agregado.")
                    .withOkButton()
                    .open();

            nombreEstudianteTXT.setValue("");
            edadEstudianteTXT.setValue("");
        });

        listaEGrid.addSelectionListener( e -> {
            Set<Estudiante> selE = e.getAllSelectedItems();

            if (selE.size()>=0)
                MessageBox
                        .create()
                        .withCaption("Seleccionado")
                        .withMessage(String.valueOf(selE.size()))
                        .withOkButton()
                        .open();
        });

        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
