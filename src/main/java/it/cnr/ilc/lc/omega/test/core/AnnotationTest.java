/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import it.cnr.ilc.lc.omega.core.ResourceManager;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.test.BaseAnnotationExtension;
import it.cnr.ilc.lc.omega.test.BaseExtensionBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeTypeParseException;
import sirius.kernel.di.std.Part;

/**
 *
 * @author simone
 */
public class AnnotationTest {

    @Part
    private static ResourceManager resourceManager;

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

        Annotation<TextContent, BaseAnnotationExtension> annotation
                = resourceManager.createAnnotation(BaseAnnotationExtension.class, new BaseExtensionBuilder().field1("testo sciolto dell'abbreviazione"));
        
 ///       resourceManager.update(Annotation, Locus, Source);
        
    }
}
