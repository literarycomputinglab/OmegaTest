/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test;

import it.cnr.ilc.lc.omega.annotation.BaseAnnotationBuilder;
import it.cnr.ilc.lc.omega.annotation.BaseAnnotation;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Relation;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.TextLocus;
import java.net.URI;

/**
 *
 * @author angelo
 */
public class Removetest {

    public static void main(String[] args) throws Exception {
//        Session session = Neo4jSessionFactory.getNeo4jSession();
//        session.beginTransaction();
//
//        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/source/00/01"));
//        
//        source.setContent(Content.contentOf(TextContent.class));
//        source.getContent().setText("pluto");
//        session.save(source);
//
//        TextLocus locus = Locus.locusOf(TextLocus.class, URI.create("/uri/locus/00/01"), Locus.PointsTo.CONTENT);
//        locus.setSource(source);
//
//        locus.setStartLocus(5);
//        locus.setEndLocus(10);
//
//        Annotation.register(TesterOLD.BASE, BaseAnnotation.class); //MODIFICARE in (TIPO, CLASSE)
//
//        Annotation<TextContent, BaseAnnotation> nota
//                = Annotation.newAnnotation(
//                        TesterOLD.BASE,
//                        new BaseAnnotationBuilder()
//                        .text("con builder")
//                        .URI(URI.create("/uri/00/01"))
//                );
//
//        System.err.println(nota.toString());
//
//        /* tolta la costruzione della nota*/
//        //BaseExtension ex = nota.getExtension();
//        //ex.setField1("nota");
//       // locus.setAnnotation(nota);
//
//        nota.addLocus(locus);
//
//       
//        Annotation<TextContent, BaseAnnotation> nota2 = Annotation.newAnnotation(
//                TesterOLD.BASE,
//                new BaseAnnotationBuilder()
//                .text("con builder per nota 2")
//                .URI(URI.create("/uri/00/02"))
//        );
//
//        Relation relation = Relation.newInstance(RelationTypes.PART_OF);
//        relation.setSourceAnnotation(nota2);
//        relation.setTargetAnnotation(nota);
//        nota2.addRelation(relation);
//
//        session.save(nota);
//        session.save(nota2);
//        session.getTransaction().commit();

    }

}
