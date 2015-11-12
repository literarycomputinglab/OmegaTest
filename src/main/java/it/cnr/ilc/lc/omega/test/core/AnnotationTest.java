/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationText;
import it.cnr.ilc.lc.omega.core.datatype.Text;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeTypeParseException;

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

        try {
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

            
            Text text = Text.of("Un qualsiasi testo da annotare.", URI.create("/source/text/000"));
            BaseAnnotationText bat = BaseAnnotationText.of("Annotazione sul testo",
                    URI.create("/annotation/text/123"));
            bat.addLocus(text, 1, 2);
            bat.save();
            Logger.getLogger(Loader.class.getName()).log(Level.INFO, "annotate end ");

            Text text2 = Text.of(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt"));
          //  text2.save();
            
///       resourceManager.update(Annotation, Locus, Source);
        } catch (InvalidURIException ex) {
            Logger.getLogger(AnnotationTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
