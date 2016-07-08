/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test.core;

import it.cnr.ilc.lc.omega.core.ManagerAction;
import it.cnr.ilc.lc.omega.core.OmegaCore;
// importare package per annotazione delle transazioni
import it.cnr.ilc.lc.omega.adt.annotation.BaseAnnotationText;
import it.cnr.ilc.lc.omega.adt.annotation.Work;
import it.cnr.ilc.lc.omega.adt.annotation.dto.PubblicationDate;
import it.cnr.ilc.lc.omega.core.SearchManager;
import it.cnr.ilc.lc.omega.core.datatype.Text;
import it.cnr.ilc.lc.omega.core.datatype.TextualHit;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import it.cnr.ilc.lc.omega.persistence.PersistenceHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.activation.MimeTypeParseException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import sirius.kernel.di.std.Part;

/**
 *
 * @author simone
 */
public class AnnotationTest {

    private static final Logger log = LogManager.getLogger(AnnotationTest.class);

    @Part
    private static PersistenceHandler persistence;

    @Part
    private static SearchManager searchManager;

    // @Part
    //   private static ResourceManager resourceManager;
    public static void main(String[] argv) throws URISyntaxException {
        OmegaCore.start();

        log.info("Core initializing...");

        new Thread("ANNOTATE") {
            @Override
            public void run() {
                try {
                    annotate();
                } catch (MimeTypeParseException | ManagerAction.ActionException | URISyntaxException ex) {
                    log.fatal("Problem on annotate()", ex);
                }
                persistence.close();
                OmegaCore.stop();
                log.info("Core stopped...");
            }

        }.start();
    }

    private static void annotate() throws MimeTypeParseException, ManagerAction.ActionException, URISyntaxException {

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
            log.info("annotate() start...");

            // UC1();
            //UC2();
            //UC3(URI.create("//source/text/001"));
            //UC4("abbreviazione");
            UC5(URI.create("//source/text/001"), "abbreviazione");
            // Text text2 = Text.of(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt"));
            //  text2.save();
            // searchSourceByURI("//source/text/000", persistence.getEntityManager());
            // threadSafeTest();
///       resourceManager.update(Annotation, Locus, Source);
        } catch (Exception ex) {
            log.error("BOOM!", ex);
        }
    }

    // annotare il metodo con un qualcosa tipo @transaction
    private static void UC1() throws ManagerAction.ActionException, InvalidURIException { // gestire le transazioni
        //Text.OpenTransaction// Livello più basso
        // OmegaTrasaction -> questo tipo di dato gestisce le transazioni
        Text text = Text.of("Abbr. e' una abbreviazione di abbreviazione.", URI.create("//source/text/000"));
        BaseAnnotationText bat = BaseAnnotationText.of("Annotazione sul testo",
                URI.create("//annotation/text/123"));
        bat.addLocus(text, 1, 5);
        bat.save();
        log.info("annotate() end");
    }

    private static void UC2() throws ManagerAction.ActionException, InvalidURIException {
        Text text2 = Text.of(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt"));
        text2.save();

    }

    private static void UC3(URI uri) throws ManagerAction.ActionException, InvalidURIException, URISyntaxException {
        Text text = Text.load(uri);

        log.info("loaded " + text.toString());
//        TextLocus locus = AbbreviationAnnotation.createTextLocus(text.getSource(), 0, 5);
//        AbbreviationAnnotation a = AbbreviationAnnotation.of("abbreviazione", URI.create("abbreviation/uri/001"));
//        a.addLocus(locus);
//        a.save();

    }

    private static void UC4(String keyword) throws ManagerAction.ActionException, InvalidURIException, URISyntaxException {
        List<Source<TextContent>> lstc = searchManager.searchByKeyword(keyword);

        log.info("loaded " + lstc.toString());

    }

    private static void UC5(URI uri, String abbreviazione) throws ManagerAction.ActionException {
        List<TextualHit> hits;
        Text text = Text.load(uri);
        String textualContent = text.getTextContent(); // meglio textual content???
        hits = text.search(abbreviazione);

        for (TextualHit hit : hits) {
            log.info(hit);
            log.info(textualContent.substring(hit.getStart(), hit.getEnd()));
        }
    }

    
    private static void UC6 (){
        
      //  Work w1 = Work.of(PubblicationDate., title, uri)
    }
    
    public static void searchSourceByURI(String uri, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Source.class).get();

        org.apache.lucene.search.Query query = builder
                .phrase()
                .onField("uri")
                .sentence(uri)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        log.info("Searching for Source");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, Source.class);

        List<Source<TextContent>> results = persistenceQuery.getResultList();

        log.info("Result is empty? " + results.isEmpty() + " " + results.size());

        for (Source<TextContent> source : results) {
            TextContent content = source.getContent();
            log.info("Source text (" + content.getText() + ")");
            log.info("Source text (" + source.getUri() + ")");
        }

        entityManager.getTransaction().commit();

        log.info("End.");

    }

    public static void threadSafeTest() throws InterruptedException {

        PersistenceHandler ph = new PersistenceHandler();

        Thread threadA = new Thread("THREAD_A") {
            public void run() {
                EntityManager em2 = ph.getEntityManager();
                try {

                    em2.getTransaction().begin();
                    log.info("Find in THREAD_A sleeping before read");

                    Source<TextContent> st
                            = em2.find(Source.class, 4l);
                    log.info("Locking mode before lock is " + em2.getLockMode(st));
                    em2.lock(st, LockModeType.OPTIMISTIC);
                    log.info("locked as " + em2.getLockMode(st));

                    //em2.lock(st, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                    em2.getTransaction().commit();
                    em2.close();

                    log.info("Commit, close and wait ...");

                    Thread.sleep(5000);

                    em2 = ph.getEntityManager();
                    em2.getTransaction().begin();
                    log.info("wake up A ... ");
                    st = em2.find(Source.class, st.getId());
                    //em2 = ph.getEntityManager();
                    em2.lock(st, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                    em2.flush();
                    log.info("A, lock entity");
                    em2.getTransaction().commit();
                    em2.close();

                    em2 = ph.getEntityManager();
                    em2.getTransaction().begin();

                    //em2.refresh(st);
//                TextContent tc = TextContent.contentOf(TextContent.class);
//                tc.setText(st.getContent().getText().concat(" bobbeeeeeeeeeeeeeeeee")); 
                    log.info("Find done in THREAD_A, ora scrivo bobbeeeeeeee");
                    log.info("is attached? " + em2.contains(st));
                    String stringText = st.getContent().getText().concat(" bobbeeeeeeeeeeeeeeeee");
                    TextContent content = st.getContent();
                    content.setText(stringText);
                    st.setContent(content);
                    //Thread.sleep(5000);
                    log.info("Commit in THREAD_A");

                    em2.getTransaction().commit();

                    log.info("Commit in THREAD_A done");
                    em2.close();
                    log.info("close in THREAD_A");
                } catch (InterruptedException e) {
                    log.error("AAAAAAAAAAHHHHHHHH", e);
                } catch (OptimisticLockException ee) {
                    log.error("RollBack!!! ", ee);
                }
            }
        };

        Thread threadB = new Thread("THREAD_B") {
            public void run() {
                EntityManager em = ph.getEntityManager();
                try {

                    em.getTransaction().begin();
                    log.info("THREAD_B is sleeping");

                    Thread.sleep(1000);

                    log.info("THREAD_B is now running");
                    Source<TextContent> s
                            = em.find(Source.class, 4l);
                    em.lock(s, LockModeType.OPTIMISTIC);
                    log.info("locked as " + em.getLockMode(s));

                    em.getTransaction().commit();
                    em.close();
                    log.info("Commit, close and wait ...");

                    Thread.sleep(3000);

                    em = ph.getEntityManager();
                    em.getTransaction().begin();
                    s = em.find(Source.class, s.getId());
                    em.lock(s, LockModeType.PESSIMISTIC_FORCE_INCREMENT);

                    log.info("B set " + em.getLockMode(s));
                    em.getTransaction().commit();

                    em.close();

                    em = ph.getEntityManager();
                    //em = ph.getEntityManager();
                    em.getTransaction().begin();
                    //s = em.find(Source.class, s.getId());

                    //em.refresh(s);
                    log.info("Wake up B ... ");
                    //log.info("Find done in THREAD_B, ora scrivo malleeeeeeeeeee");

                    String stringText = s.getContent().getText().concat(" malleeeeeeeeeeee");
                    TextContent content = s.getContent();
                    content.setText(stringText);
                    s.setContent(content);

                    Thread.sleep(3000);
                    em.merge(s);
                    em.getTransaction().commit();

                    log.info("Commit in THREAD_B");
                    em.close();
                    log.info("close in THREAD_B");
                } catch (InterruptedException e) {
                    log.error("AAAAAAAHHHHHHH ", e);
                }
            }
        };

        Thread threadC = new Thread("THREAD_C") {
            public void run() {
                EntityManager em = ph.getEntityManager();
                try {

                    Thread.sleep(1000);

                    em.getTransaction().begin();

                    log.info("THREAD_C is now running");
                    Source<TextContent> s
                            = em.find(Source.class, 4l);
                    em.lock(s, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                    em.flush();

                    log.info("locked as " + em.getLockMode(s));

                    log.info("Read resource with URI " + s.getUri());
                    Thread.sleep(4000);

                    em.getTransaction().commit();

                    em.close();

                    log.info("close in THREAD_C");
                } catch (InterruptedException e) {
                    log.error("AAAAAAAHHHHHHH ", e);
                }
            }
        };

        Thread threadD = new Thread("THREAD_D") {
            public void run() {
                EntityManager em = ph.getEntityManager();
                try {

                    em.getTransaction().begin();

                    log.info("THREAD_D is now running");
                    Source<TextContent> s
                            = em.find(Source.class, 4l);
                    em.lock(s, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                    em.flush();
                    log.info("locked as " + em.getLockMode(s));
                    log.info("sleeping ");
                    Thread.sleep(2000);

                    log.info("Read resource with URI " + s.getUri());
                    log.info("changing... ");
                    s.setUri(URI.create("//source/text/001").toASCIIString());

                    em.getTransaction().commit();

                    em.close();

                    log.info("close in THREAD_D");
                } catch (InterruptedException e) {
                    log.error("AAAAAAAHHHHHHH ", e);
                }

            }
        };

        Thread threadE = new Thread("THREAD_E") {
            public void run() {
                EntityManager em = ph.getEntityManager();
                try {

                    em.getTransaction().begin();

                    log.info("THREAD_E is now running");
                    Annotation ann
                            = em.find(Annotation.class, 1l);
                    em.lock(ann, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                    em.flush();
                    log.info("locked as " + em.getLockMode(ann));
                    log.info("sleeping ");
                    Thread.sleep(1000);

                    log.info("Read resource with URI " + ann.getUri());
                    log.info("changing... ");
                    ann.setUri(URI.create("//annotation/text/1234").toASCIIString());

                    em.getTransaction().commit();

                    em.close();

                    log.info("close in THREAD_E");
                } catch (InterruptedException e) {
                    log.error("AAAAAAAHHHHHHH ", e);
                }

            }
        };
        // threadB.start();
        //threadC.start();
        threadD.start();
        threadE.start();

        //s = em.find(Source.class, 4l);
        //threadC.join();
        threadD.join();
        threadE.join();

        ph.close();
    }

}
