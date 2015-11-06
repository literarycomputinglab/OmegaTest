/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import it.cnr.ilc.lc.omega.core.ResourceManager;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationBuilder;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationText;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationType;
import it.cnr.ilc.lc.omega.core.datatype.Text;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.TextContent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeTypeParseException;
import sirius.kernel.di.std.Part;

/**
 *
 * @author simone
 */
public class AnnotationTest {

   // @Part
    //   private static ResourceManager resourceManager;
    public static void main(String[] argv) {
        OmegaCore.start();
        Logger.getLogger(Loader.class.getName()).log(Level.INFO, "Core initializing...");
        try {
            annotate();
        } catch (MimeTypeParseException | ManagerAction.ActionException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        OmegaCore.stop();
    }

    private static void annotate() throws MimeTypeParseException, ManagerAction.ActionException {

        // load della source
        // creazione locus
        // creazione annotazione
        // gestione del collegamento tra locus / annotazione / source
        // salvataggio/aggiurnamento delle risorse coinvolte
        // le annotazioni si riferiscono a due tupi di dato diversi sia per scope sia per namespace. Per semplicità le chiamiami di basso livello e di alto livello
     /*   Annotation<TextContent, BaseAnnotationType> annotation
         = resourceManager.createAnnotation(BaseAnnotationType.class,
         new BaseAnnotationBuilder().text("testo della annotazione"));*/
        Logger.getLogger(Loader.class.getName()).log(Level.INFO, "annotate start ");
        BaseAnnotationText bat = BaseAnnotationText.of("base annotation bobbe");
        Text text = new Text();
        bat.addLocus(text, 1, 2);
        bat.save();
        Logger.getLogger(Loader.class.getName()).log(Level.INFO, "annotate end ");

///       resourceManager.update(Annotation, Locus, Source);
    }
}
