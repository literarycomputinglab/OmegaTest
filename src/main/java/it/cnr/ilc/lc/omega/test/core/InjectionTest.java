/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
import it.cnr.ilc.lc.omega.core.ResourceManager;
import it.cnr.ilc.lc.omega.core.datatype.Text;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sirius.kernel.di.std.Part;
import sirius.kernel.di.std.Register;

/**
 *
 * @author simone
 */
@Register(classes = InjectionTest.class)
public class InjectionTest {

    @Part
    static ResourceManager rm;

    public InjectionTest() {
    }

    public void go() throws URISyntaxException {
        Text t;
        OmegaCore.start();
        try {
            t = Text.of("bobbe", URI.create("a/b/c"));
            System.err.println("Text " + t.toString());
            System.err.println("rm " + rm + " [" + Thread.currentThread().getName() + " ]");
        } catch (ManagerAction.ActionException ex) {
            Logger.getLogger(InjectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidURIException ex) {
            Logger.getLogger(InjectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        OmegaCore.stop();
    }

    public void goThread() throws URISyntaxException {
        OmegaCore.start();

        Logger.getLogger(Loader.class.getName()).log(Level.INFO, "Core initializing...");

        new Thread() {

            @Override
            public void run() {
                try {
                    Text t = Text.of("bobbe", URI.create("a/b/c"));
                    System.err.println("Text " + t.toString());
                } catch (ManagerAction.ActionException ex) {
                    Logger.getLogger(InjectionTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidURIException ex) {
                    Logger.getLogger(InjectionTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println("rm " + rm + " [" + Thread.currentThread().getName() + " ]");

                OmegaCore.stop();
            }

        }.start();
        Logger.getLogger(Loader.class.getName()).log(Level.INFO, "Core stopped...");

    }

    public static void main(String[] argv) throws URISyntaxException {
        InjectionTest it = new InjectionTest();
        it.goThread();
    }
}
