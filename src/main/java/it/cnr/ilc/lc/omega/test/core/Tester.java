/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ResourceManager;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import sirius.kernel.di.std.Part;

/**
 *
 * @author angelo
 */
public class Tester {

    @Part
    private static ResourceManager resourceManager;

    public static void main(String[] argv) {
        OmegaCore.start();
        System.err.println("Core Inizializzato");
        try {
            test();
        } catch (MimeTypeParseException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        OmegaCore.stop();
    }

    private static void test() throws MimeTypeParseException {
        URI sourceURI = URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147");
        URI contentURI = URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt");
        resourceManager.createSource(sourceURI, new MimeType("text/plain"));
        resourceManager.setContent(sourceURI,contentURI);
        resourceManager.inFolder("archivio", sourceURI);
        System.err.println("in test");
    }
}
