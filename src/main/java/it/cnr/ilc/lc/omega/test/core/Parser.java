/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.claviusxmlparser.ClaviusParser;
import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import it.cnr.ilc.lc.omega.core.ResourceManager;
import it.cnr.ilc.lc.omega.core.parser.OmegaParser;
import it.cnr.ilc.lc.omega.entity.ImageContent;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
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
public class Parser {

    private static final String tpath = "http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/";
    private static final String ipath = "in/my/folder/image";

    @Part
    private static ResourceManager resourceManager;

    @Part
    private static OmegaParser parser;

    public static void main(String[] argv) {

        try {
            OmegaCore.start();
            Logger.getLogger(Loader.class.getName()).log(Level.INFO, "Core initializing...");
            startParse();
        } catch (MimeTypeParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            OmegaCore.stop();
        }

    }

    private static void startParse() throws MimeTypeParseException {
        URI tsourceUri = URI.create(tpath);
        URI isourceUri = URI.create(ipath);

//        Source<TextContent> text = parser.parseTextContent(sourceUri);
//        Source<ImageContent> image = (Source<ImageContent>)parser.parse(URI.create(path)); // valutare come inizializzare bene la calsse parametrizzata
//        Source<TextContent> textsource = parser.parse(sourceUri, TextContent.class);
//        Source<ImageContent> imagesource = parser.parse(sourceUri, ImageContent.class);
//        TextContent content = textsource.getContent();
//        content.setText("con il metodo iper generico");
//        textsource.setContent(content);
        Source<TextContent> text = parser.parse(tsourceUri, TextContent.class);
//        Source<ImageContent> image = parser.parse(isourceUri, ImageContent.class);
        try {

            resourceManager.createSourceContent(text);
            resourceManager.inFolder("ClaviusArchive", URI.create(text.getUri()));
        } catch (ManagerAction.ActionException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        // resourceManager.createSourceContent(image);

        System.err.println("fine parse Content");
    }
}