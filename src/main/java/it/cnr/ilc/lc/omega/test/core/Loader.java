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
public class Loader {

    private static final String[] LETTERS = {"136", "139", "147", "149", "153", "171", "204", "223", "249", "267", "273", "296"};
    private static final String BASEPATH = "http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/";
    private static final String EXT = ".txt";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String TXTMIME = "text/plain";
    private static final String ARCHIVE = "ClaviusArchive";

    @Part
    private static ResourceManager resourceManager;

    public static void main(String[] argv) {
        OmegaCore.start();
         Logger.getLogger(Loader.class.getName()).log(Level.INFO, "Core initializing...");
        try {
            load();
        } catch (MimeTypeParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        OmegaCore.stop();
    }

    private static void load() throws MimeTypeParseException {
        for (String letter : Loader.LETTERS) {
            String path = Loader.BASEPATH + Loader.SEPARATOR + letter;
            URI sourceURI = URI.create(path);
            URI contentURI = URI.create(path + Loader.SEPARATOR + letter + Loader.EXT);
            resourceManager.createSource(sourceURI, new MimeType(Loader.TXTMIME));
            resourceManager.setContent(sourceURI, contentURI);
            resourceManager.inFolder(Loader.ARCHIVE, sourceURI);
        }
//        System.err.println("in test");
    }
}
