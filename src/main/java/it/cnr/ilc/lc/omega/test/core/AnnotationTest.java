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
import it.cnr.ilc.lc.omega.adt.annotation.DublinCore;
import it.cnr.ilc.lc.omega.adt.annotation.Work;
import it.cnr.ilc.lc.omega.adt.annotation.dto.Authors;
import it.cnr.ilc.lc.omega.adt.annotation.dto.Couple;
import it.cnr.ilc.lc.omega.adt.annotation.dto.DTOValue;
import it.cnr.ilc.lc.omega.adt.annotation.dto.Loci;
import it.cnr.ilc.lc.omega.adt.annotation.dto.PubblicationDate;
import it.cnr.ilc.lc.omega.adt.annotation.dto.SegmentOfInterest;
import it.cnr.ilc.lc.omega.adt.annotation.dto.Title;
import it.cnr.ilc.lc.omega.adt.annotation.dto.WorkSource;
import it.cnr.ilc.lc.omega.adt.annotation.dto.catalog.dublincore.*;
import it.cnr.ilc.lc.omega.annotation.catalog.DublinCoreAnnotation;
import it.cnr.ilc.lc.omega.annotation.catalog.ResourceSystemAnnotationType;
import it.cnr.ilc.lc.omega.annotation.structural.WorkAnnotation;
import it.cnr.ilc.lc.omega.core.SearchManager;
import it.cnr.ilc.lc.omega.core.datatype.Text;
import it.cnr.ilc.lc.omega.core.datatype.TextualHit;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.ext.Person;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import it.cnr.ilc.lc.omega.exception.VirtualResourceSystemException;
import it.cnr.ilc.lc.omega.persistence.PersistenceHandler;
import it.cnr.ilc.lc.omega.resourcesystem.Collection;
import it.cnr.ilc.lc.omega.resourcesystem.Resource;
import it.cnr.ilc.lc.omega.resourcesystem.ResourceSystemComponent;
import it.cnr.ilc.lc.omega.resourcesystem.dto.DTOValueRSC;
import it.cnr.ilc.lc.omega.resourcesystem.dto.RSCDescription;
import it.cnr.ilc.lc.omega.resourcesystem.dto.RSCName;
import it.cnr.ilc.lc.omega.resourcesystem.dto.RSCParent;
import it.cnr.ilc.lc.omega.resourcesystem.dto.RSCType;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
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

            //UC1();
           // UC2();
            //UC3(URI.create("//source/text/AAA"));
            //UC4("abbreviazione");
            //UC5(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt"), "abs");
            //UC6();
            //UC7();
            //UC8("bobbe");
            //UC9();
            //UC9b();
            //UC9c();
            //UC10();
            //UC11();
            UC13();
            // Text text2 = Text.of(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt"));
            //  text2.save();
            // searchSourceByURI("//source/text/000", persistence.getEntityManager());
            //   threadSafeTest();
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

    private static void UC2() throws ManagerAction.ActionException, InvalidURIException, MalformedURLException {
        Text text2 = Text.of(URI.create("http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/147/147.txt").toURL());
        text2.save();

    }

    private static void UC3(URI uri) throws ManagerAction.ActionException, InvalidURIException, URISyntaxException {
        Text text = Text.load(uri);

        log.info("loaded=(" + text.toString() + ")");
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

    private static void UC6() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException {

        PubblicationDate pd = DTOValue
                .instantiate(PubblicationDate.class)
                .withValue(new Date(2018, 05, 24, 12, 12)); //PubblicationDate.instantiate().withValue(xxx)
        List<String> list = new ArrayList<>();
        list.add("matteo,salvini");
        list.add("di maio,gigino");
        Authors auth = DTOValue
                .instantiate(Authors.class)
                .withValue(list);
        System.err.println("authors " + auth.getValue());

        Title title = DTOValue
                .instantiate(Title.class)
                .withValue("Governo 2018");
        URI uri = URI.create("/work/italia/governo/2018");

        Work w1 = Work.of(auth, pd, title, uri);

        w1.save();
    }

    private static void UC7() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException {

        String[][] autori = {{"bobbe,malle", "pippo,pluto", "topolino,minnie"}, {"nip,qui", "nip,quo", "nip,qua"}, {"java,merda", "perl,abbestia"}};
        List<Authors> loa = generateAuthorsList(autori);
        int i = 1;
        PubblicationDate pd = DTOValue
                .instantiate(PubblicationDate.class)
                .withValue(new Date()); //PubblicationDate.instantiate().withValue(xxx)
        for (Authors authors : loa) {
            Title title = DTOValue
                    .instantiate(Title.class)
                    .withValue("Titolo di prova del testo n. " + i);
            URI uri = URI.create("work/prova/000" + i);

            Work work = Work.of(authors, pd, title, uri);

            work.save();
            i++;
        }

    }

    private static void UC8(String author) throws InstantiationException, IllegalAccessException, ManagerAction.ActionException {
        EntityManager entityManager = persistence.getEntityManager();
        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(WorkAnnotation.class).get();

        org.apache.lucene.search.Query query = builder
                .keyword()
                .onField("authors.name")
                .matching(author)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        log.info("Searching for WorkAnnotation");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, WorkAnnotation.class);

        List<WorkAnnotation> results = persistenceQuery.getResultList();

        log.info("Result is empty? " + results.isEmpty() + ", no. of element(s) " + results.size());

        for (WorkAnnotation workAnnotation : results) {
            log.info("Title (" + workAnnotation.getTitle() + ")");
            log.info("Authors (" + workAnnotation.getAuthors() + ")");
            List<Person> l = workAnnotation.getAuthors();
            for (Person person : l) {
                log.info("XXX " + person.getName() + ", " + person.getSurname());
            }
            log.info("Annotation URI (" + workAnnotation.getAnnotation().getUri() + ")");
        }

        entityManager.getTransaction().commit();

        log.info("End.");
    }
// {
//    {"bobbe,malle","pippo,pluto"}, 
//    {...}http://www.repubblica.it/
// }

    private static void UC9() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, InvalidURIException {
        log.info("Use case 9 starting");
        Text textA = Text.of("Hillary Clinton Curriculum", URI.create("//source/text/hillary/curri"));
        Text textB = Text.of("Donald Trump Curriculum", URI.create("//source/text/donald/curri"));
        Text textC = Text.of("Rocker Duck Curriculum", URI.create("//source/text/rockerduck/curri"));
        Text textD = Text.of("Donald Duck Curriculum", URI.create("//source/text/donaldduck/curri"));

        String[][] autori = {{"bobbe,malle", "pippo,pluto", "topolino,minnie"}};
        List<Authors> loa = generateAuthorsList(autori);
        //int i = 2;
        PubblicationDate pd = DTOValue
                .instantiate(PubblicationDate.class)
                .withValue(new Date()); //PubblicationDate.instantiate().withValue(xxx)

        Title title = DTOValue
                .instantiate(Title.class)
                .withValue("Titolo di prova del testo n. " + 1);
        URI uri = URI.create("/work/electionday2/00" + 1);

        Work work = Work.of(loa.get(0), pd, title, uri);

//        Loci loci = DTOValue.instantiate(Loci.class).withValue(null).withValue(null);
        //       work.addLoci(loci);
        work.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textA.getSource()),
                DTOValue.instantiate(SegmentOfInterest.class).withValue(new Couple<>(0, 8))
        );

        work.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textB.getSource()),
                DTOValue.instantiate(SegmentOfInterest.class).withValue(new Couple<>(5, 21))
        );

        work.save();

        Title title2 = DTOValue
                .instantiate(Title.class)
                .withValue("Titolo di prova del testo n. " + 2);
        URI uri2 = URI.create("/work/electionday2/00" + 2);

        Work work2 = Work.of(loa.get(0), pd, title2, uri2);

//        Loci loci = DTOValue.instantiate(Loci.class).withValue(null).withValue(null);
        //       work.addLoci(loci);
        work2.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textC.getSource()),
                DTOValue.instantiate(SegmentOfInterest.class).withValue(new Couple<>(1, 6))
        );

        work2.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textD.getSource())//,
                //DTOValue.instantiate(SegmentOfInterest.class).withValue(new Couple<>(7, 18))
        );

        work2.save();
    }

    private static void UC9b() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, InvalidURIException {
        log.info("Use case 9b starting");
        Text textA = Text.load(URI.create("//source/text/hillary/curri"));
        Text textB = Text.load(URI.create("//source/text/donald/curri"));
    
        String[][] autori = {{"bobbe,malle", "pippo,pluto", "topolino,minnie"}};
        List<Authors> loa = generateAuthorsList(autori);
        //int i = 2;
        PubblicationDate pd = DTOValue
                .instantiate(PubblicationDate.class)
                .withValue(new Date()); //PubblicationDate.instantiate().withValue(xxx)

        Title title = DTOValue
                .instantiate(Title.class)
                .withValue("Titolo di prova del testo n. " + 1);
        URI uri = URI.create("/work/electionday3/00" + 1);

        Work work = Work.of(loa.get(0), pd, title, uri);

//        Loci loci = DTOValue.instantiate(Loci.class).withValue(null).withValue(null);
        //       work.addLoci(loci);
        work.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textA.getSource())
        );

        work.addLocus(DTOValue.instantiate(WorkSource.class).withValue(textB.getSource()),
                DTOValue.instantiate(SegmentOfInterest.class).withValue(new Couple<>(8, 13))
        );

        work.save();

    }

    private static void UC9c() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, InvalidURIException {
        log.info("Use case 9c starting");
        Text textA = Text.load(URI.create("//source/text/hillary/curri"));

        String[][] autori = {{"bobbe,malle", "pippo,pluto", "topolino,minnie"}};
        List<Authors> loa = generateAuthorsList(autori);
        //int i = 2;
        PubblicationDate pd = DTOValue
                .instantiate(PubblicationDate.class)
                .withValue(new Date()); //PubblicationDate.instantiate().withValue(xxx)

        Title title = DTOValue
                .instantiate(Title.class)
                .withValue("Titolo di prova del testo n. " + 1);
        URI uri = URI.create("/work/electionday4/00" + 1);

        Work work = Work.of(loa.get(0), pd, title, uri);

        Loci loci = DTOValue.instantiate(Loci.class).withValue(Work.createTextLocus(textA.getSource()));
        //       work.addLoci(loci);

        work.addLoci(loci);

        work.save();

    }

    private static void UC10() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException {

        log.info("Use case 10 starting");

        String[] contributors = {"first contributor", "second contributor", "third contributor"};
//        String[] relations = {DublinCoreAnnotation.DCTerms.ABSTRACT.toString() + ":bobbe è bello"};

        Work work = Work.load(URI.create("/work/electionday2/001"));
        Work work2 = Work.load(URI.create("/work/electionday2/002"));


        log.info("loaded work (" + work.toString() + ")");
//        DublinCore<TextContent> dc = DublinCore.of(work).withTerms(
//                DTOValue.instantiate(DCContributor.class).withValue(contributors),
//                DTOValue.instantiate(Relation.class).withValue("abstract:bobbe è negro"));
        DCContributor contributor = DTOValueDC.instantiate(DCContributor.class).withValue(contributors);

        Couple<DublinCoreAnnotation.DCTerms, DCRelationObject> couple = new Couple(DublinCoreAnnotation.DCTerms.FORMAT,
                DTOValueDC.instantiate(DCRelationObject.class).withValue("application/xml"));

        Couple<DublinCoreAnnotation.DCTerms, DCRelationObject> couple2 = new Couple(DublinCoreAnnotation.DCTerms.ABSTRACT,
                DTOValueDC.instantiate(DCRelationObject.class).withValue("abstract di stoca"));

        DCRelation relation = DTOValueDC.instantiate(DCRelation.class).addValue(couple).addValue(couple2);

        DCTitle title = DTOValueDC.instantiate(DCTitle.class).withValue("Titolo dell'opera");

        DCSubject subject = DTOValueDC.instantiate(DCSubject.class).withValue(new String[]{"subj1", "subj2", "another subj"});

        DCDate date = DTOValueDC.instantiate(DCDate.class).withValue(new Couple<>(new Date(0), "evento boh!"));

        DCCoverage coverage = DTOValueDC.instantiate(DCCoverage.class).withValue(null);
        DCCreator creator = DTOValueDC.instantiate(DCCreator.class).withValue(null);
        //    DCDate date = DTOValueDC.instantiate(DCDate.class).withValue(null);
        DCDescription description = DTOValueDC.instantiate(DCDescription.class).withValue(null);
        DCFormat format = DTOValueDC.instantiate(DCFormat.class).withValue(null);
        DCIdentifier identifier = DTOValueDC.instantiate(DCIdentifier.class).withValue(null);
        DCLanguage language = DTOValueDC.instantiate(DCLanguage.class).withValue(null);
        DCPublisher publisher = DTOValueDC.instantiate(DCPublisher.class).withValue(null);
        DCRights rights = DTOValueDC.instantiate(DCRights.class).withValue(null);
        DCSource source = DTOValueDC.instantiate(DCSource.class).withValue(null);
        DCType type = DTOValueDC.instantiate(DCType.class).withValue(null);

        log.info("relation " + relation.toString());

        DublinCore<TextContent> dc = DublinCore.of(work, URI.create("/dublincore/uri/001"))
                .withTerms(contributor, coverage,
                        creator, date, description, format,
                        identifier, language, publisher,
                        relation, rights, source, subject,
                        title, type);
        dc.save();
        
        

        DublinCore<TextContent> dc2 = DublinCore.of(work2, URI.create("/dublincore/uri/002"))
                .withTerms(contributor, coverage,
                        creator, date, description, format,
                        identifier, language, publisher,
                        relation, rights, source, subject,
                        title, type);

        dc2.save();
       log.info("Dublin Core information stored");

    }

    private static void UC11() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, VirtualResourceSystemException {

        RSCName rootName = DTOValueRSC.instantiate(RSCName.class).withValue("radice");
        RSCDescription rootDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("Cartella root");
        RSCType rootType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.COLLECTION);

        ResourceSystemComponent root = ResourceSystemComponent.of(Collection.class, URI.create("/collection/root/col000"))
                .withParams(rootName, rootDescription, rootType, RSCParent.NOPARENT);

        RSCName firstName = DTOValueRSC.instantiate(RSCName.class).withValue("first level");
        RSCDescription firstDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("Cartella first (figlia di root)");
        RSCType firstType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.COLLECTION);
        RSCParent rootParent = DTOValueRSC.instantiate(RSCParent.class).withValue(root.getCurrentComponent(Collection.class));

        ResourceSystemComponent firstLevel = ResourceSystemComponent.of(Collection.class, URI.create("/collection/root/first/col001"))
                .withParams(firstName, firstDescription, firstType, rootParent);

        RSCName rootElementName = DTOValueRSC.instantiate(RSCName.class).withValue("item in root");
        RSCDescription rootElementDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("item nel folder root");
        RSCType rootElementType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.RESOURCE);

        ResourceSystemComponent itemRootLevel = ResourceSystemComponent.of(Resource.class, URI.create("/collection/root/resource/res001"))
                .withParams(rootElementName, rootElementDescription, rootElementType, rootParent);

        itemRootLevel.setResourceContent(DublinCore.load(URI.create("/dublincore/uri/001")));
        root.add(itemRootLevel);
        root.add(firstLevel);

        RSCName firstLevelElementName = DTOValueRSC.instantiate(RSCName.class).withValue("item in first level");
        RSCDescription firstLevelElementDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("item nel folder first");
        RSCType firstLevelElementType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.RESOURCE);
        RSCParent firstLevelParent = DTOValueRSC.instantiate(RSCParent.class).withValue(firstLevel.getCurrentComponent(Collection.class));

        ResourceSystemComponent itemFirstLevel = ResourceSystemComponent.of(Resource.class, URI.create("/collection/root/first/resource/res002"))
                .withParams(firstLevelElementName, firstLevelElementDescription, firstLevelElementType, firstLevelParent);

        itemFirstLevel.setResourceContent(DublinCore.load(URI.create("/dublincore/uri/002")));
        firstLevel.add(itemFirstLevel);

        root.print(System.err);

        root.save();

    }

//    private static void UC12() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, VirtualResourceSystemException {
//
//        RSCName rootName = DTOValueRSC.instantiate(RSCName.class).withValue("radice");
//        RSCDescription rootDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("Cartella root");
//        RSCType rootType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.COLLECTION);
//
//        ResourceSystemComponent root = ResourceSystemComponent.of(Collection.class, URI.create("/collection/root/2/000"))
//                .withParams(rootName, rootDescription, rootType, RSCParent.NOPARENT);
//
//        RSCName firstName = DTOValueRSC.instantiate(RSCName.class).withValue("first level");
//        RSCDescription firstDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("Cartella first (figlia di root)");
//        RSCType firstType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.COLLECTION);
//        RSCParent rootParent = DTOValueRSC.instantiate(RSCParent.class).withValue(root.getCurrentComponent(Collection.class));
//
//        ResourceSystemComponent firstLevel = ResourceSystemComponent.of(Collection.class, URI.create("/collection/first/2/001"))
//                .withParams(firstName, firstDescription, firstType, rootParent);
//
//        RSCName rootElementName = DTOValueRSC.instantiate(RSCName.class).withValue("item in root");
//        RSCDescription rootElementDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("item nel folder root");
//        RSCType rootElementType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.RESOURCE);
//
//        ResourceSystemComponent itemRootLevel = ResourceSystemComponent.of(Resource.class, URI.create("/root/resource/2/0001"))
//                .withParams(rootElementName, rootElementDescription, rootElementType, rootParent);
//
//        itemRootLevel.setResourceContent(DublinCore.load(URI.create("/dublincore/uri/001")));
//        root.add(itemRootLevel);
//        root.add(firstLevel);
//
//        RSCName firstLevelElementName = DTOValueRSC.instantiate(RSCName.class).withValue("item in first level");
//        RSCDescription firstLevelElementDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("item nel folder first");
//        RSCType firstLevelElementType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.RESOURCE); //ERR: DOVREBBE ESSERE COLLECTION
//        RSCParent firstLevelParent = DTOValueRSC.instantiate(RSCParent.class).withValue(firstLevel.getCurrentComponent(Collection.class));
//
//        ResourceSystemComponent itemFirstLevel = ResourceSystemComponent.of(Resource.class, URI.create("/root/first/resource/2/0002"))
//                .withParams(firstLevelElementName, firstLevelElementDescription, firstLevelElementType, firstLevelParent);
//
//        itemFirstLevel.setResourceContent(DublinCore.load(URI.create("/dublincore/uri/002")));
//        firstLevel.add(itemFirstLevel);
//
//        root.print(System.err);
//
//        root.save();
//
//    }

    private static void UC13() throws InstantiationException, IllegalAccessException, ManagerAction.ActionException, VirtualResourceSystemException {

        ResourceSystemComponent firstLevel = ResourceSystemComponent.load(Collection.class, URI.create("/collection/root/first/col001"));

        RSCName firstLevelElementName = DTOValueRSC.instantiate(RSCName.class).withValue("item.work");
        RSCDescription firstLevelElementDescription = DTOValueRSC.instantiate(RSCDescription.class).withValue("work nel folder first");
        RSCType firstLevelElementType = DTOValueRSC.instantiate(RSCType.class).withValue(ResourceSystemAnnotationType.RESOURCE);
        RSCParent firstLevelParent = DTOValueRSC.instantiate(RSCParent.class).withValue(firstLevel.getCurrentComponent(Collection.class));

        ResourceSystemComponent itemFirstLevel = ResourceSystemComponent.of(Resource.class, URI.create("/root/first/resource/2/work001"))
                .withParams(firstLevelElementName, firstLevelElementDescription, firstLevelElementType, firstLevelParent);

        itemFirstLevel.setResourceContent(Work.load(URI.create("/work/electionday2/001")));

        firstLevel.add(itemFirstLevel);
        firstLevel.save();
        //firstLevel.save();
        ResourceSystemComponent root = ResourceSystemComponent.load(Collection.class, URI.create("/collection/root/col000"));

        root.print(System.err);
//
//        root.print(System.err);
//
    }

    private static List<Authors> generateAuthorsList(String[][] args) throws InstantiationException, IllegalAccessException {

        List<Authors> authors = new ArrayList<>();
        for (String[] arg : args) {
            List<String> list = new ArrayList<>();

            for (String string : arg) {
                list.add(string);
            }
            Authors auth = DTOValue
                    .instantiate(Authors.class)
                    .withValue(list);
            authors.add(auth);
        }

        return authors;
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
                            = em2.find(Source.class, 7l);
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
                    log.info("Content is: " + st.getContent().getText());
                    String stringText = st.getContent().getText().concat(" bobbeeeeeeeeeeeeeeeee");
                    TextContent content = st.getContent();
                    content.setText(stringText);
                    st.setContent(content);
                    //Thread.sleep(5000);
                    log.info("merge in THREAD_A");
                    em2.merge(st);
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
                            = em.find(Source.class, 7l);
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
                            = em.find(Source.class, 7l);
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
        threadA.start();
        //threadB.start();
        threadC.start();
        // threadD.start();
        // threadE.start();

        //s = em.find(Source.class, 4l);
        threadA.join();
        //threadB.join();
        threadC.join();
        // threadD.join();
        // threadE.join();

        ph.close();
    }

}
