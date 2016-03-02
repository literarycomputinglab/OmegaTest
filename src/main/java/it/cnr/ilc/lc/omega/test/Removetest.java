/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test;

import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationBuilder;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationType;
import it.cnr.ilc.lc.omega.core.persistence.Neo4jSessionFactory;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Relation;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.TextLocus;
import java.net.URI;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author angelo
 */
public class Removetest {

    public static void main(String[] args) throws Exception {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/source/00/01"));
        
        source.setContent(Content.contentOf(TextContent.class));
        source.getContent().setText("pluto");
        session.save(source);

        TextLocus locus = Locus.locusOf(TextLocus.class, URI.create("/uri/locus/00/01"), Locus.PointsTo.CONTENT);
        locus.setSource(source);

        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(TesterOLD.BASE, BaseAnnotationType.class); //MODIFICARE in (TIPO, CLASSE)

        Annotation<TextContent, BaseAnnotationType> nota
                = Annotation.newAnnotation(
                        TesterOLD.BASE,
                        new BaseAnnotationBuilder()
                        .text("con builder")
                        .URI(URI.create("/uri/00/01"))
                );

        System.err.println(nota.toString());

        /* tolta la costruzione della nota*/
        //BaseExtension ex = nota.getExtension();
        //ex.setField1("nota");
       // locus.setAnnotation(nota);

        nota.addLocus(locus);

       
        Annotation<TextContent, BaseAnnotationType> nota2 = Annotation.newAnnotation(
                TesterOLD.BASE,
                new BaseAnnotationBuilder()
                .text("con builder per nota 2")
                .URI(URI.create("/uri/00/02"))
        );

        Relation relation = Relation.newInstance(RelationTypes.PART_OF);
        relation.setSourceAnnotation(nota2);
        relation.setTargetAnnotation(nota);
        nota2.addRelation(relation);

        session.save(nota);
        session.save(nota2);
        session.getTransaction().commit();

    }

}
