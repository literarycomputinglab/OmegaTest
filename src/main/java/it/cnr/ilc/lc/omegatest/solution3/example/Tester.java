package it.cnr.ilc.lc.omegatest.solution3.example;

import it.cnr.ilc.lc.omegatest.Neo4jSessionFactory;
import it.cnr.ilc.lc.omegatest.solution3.Annotation;
import it.cnr.ilc.lc.omegatest.solution3.Locus;
import it.cnr.ilc.lc.omegatest.solution3.Relation;
import it.cnr.ilc.lc.omegatest.solution3.RelationType;
import it.cnr.ilc.lc.omegatest.solution3.Source;
import it.cnr.ilc.lc.omegatest.solution3.TextContent;
import it.cnr.ilc.lc.omegatest.solution3.TextLocus;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class Tester {

    private static final Long SOURCE_ID = 7l;
    private static final Long ANNOTATION_ID = 9l;

    public static final String TEXTCONTENT = "Il contenuto della risorsa principale";
    public static final String BASE = "BaseExtension";
    public static final String NOTA = "il contenuto di una nota";
    public static final String TESTO = "un contenuto generico";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Tester tester = new Tester();

        /* crea il nodo content*/
//        tester.test1();
        /* crea il nodo annotazione */
//          tester.test2();
//        tester.test2_1();
        /* crea un nuovo locus */
//         tester.test3();

        /* crea annotazione di annotazioni */
//        tester.test4();

        /* crea annotazione di content di annotazione */
        //tester.test5();
        /* test builder */
        tester.test6();

    }

    private void test1() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source<TextContent> source = Source.sourecOf(TextContent.class);
        source.setContent(new TextContent());
        source.getContent().setText(Tester.TEXTCONTENT);
        session.save(source);

        session.getTransaction().commit();
    }

    /**/
    private void test2() throws InstantiationException, IllegalAccessException {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source source = session.load(Source.class, SOURCE_ID);

        TextLocus locus = new TextLocus();
        locus.setSource(source);
        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(Tester.BASE, BaseAnnotationExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseAnnotationExtension> nota = Annotation.newAnnotation(Tester.BASE, new BaseExtensionBuilder().field1("nota"));

        System.err.println(nota.toString());
//
//        BaseExtension ex = nota.getExtension();
//        ex.setField1("nota");
//          List<Locus> loci = new ArrayList<Locus>();
        //        locus.setAnnotation(nota);
        //        loci.add(locus);
        locus.setAnnotation(nota);
        nota.setLoci(new ArrayList<Locus>());
        nota.getLoci().add(locus);

        session.save(nota);
        session.getTransaction().commit();

        //        Note note = new Note();
        //        note.setField1("Ciao");
        //        note.setField2("Bello");
        //
        //        Annotation<Note> annotation = new Annotation<>();
        //        annotation.setExtension(note);
        //        annotation.setLocus(new ArrayList<Locus>());
        //        annotation.getLocus().add(locus);
        //        
        //        locus.setAnnotation(annotation);
        //
        //        session.save(annotation);
        //
        //        session.getTransaction().commit();
    }

    private void test2_1() throws InstantiationException, IllegalAccessException {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        // load ha bisogno di riparametrizzare i generics
        Annotation<TextContent, BaseAnnotationExtension> annotation = session.load(Annotation.class, ANNOTATION_ID);
        TextContent content = new TextContent();

        content.setText(Tester.TESTO);
        annotation.setContent(content);

        session.save(annotation);
        session.getTransaction().commit();

    }
    /**/

    private void test3() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test tre");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Annotation annotation = session.load(Annotation.class, ANNOTATION_ID, 5);

        System.err.println(annotation.toString());

        TextLocus locus = new TextLocus();
        List<Locus> loci = annotation.getLoci();
        Locus l = loci.get(0);
        Source source = l.getSource();
        System.err.println("source " + source.toString());
        System.err.println("source.getcontent (" + source.getContent() + ")");
        TextContent content = (TextContent) source.getContent();
        System.err.println("content: " + content.getText());
        locus.setSource(source);
        locus.setAnnotation(annotation);
        locus.setStart(6);
        locus.setEnd(12);
        //        
        annotation.getLoci().add(locus);
        session.save(annotation);
        session.getTransaction().commit();

        //        session.getTransaction().close();
    }
    /**/

    private void test4() throws InstantiationException, IllegalAccessException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test 4");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
        //
        Annotation annotation = session.load(Annotation.class, ANNOTATION_ID);

        Annotation.register(Tester.BASE, BaseAnnotationExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseAnnotationExtension> nota2 = Annotation.newAnnotation(Tester.BASE, new BaseExtensionBuilder().field1("nota2 che annota nota"));

//        BaseExtension bex = nota2.getExtension();
//
//        bex.setField1("nota2 che annota nota");
//        Relation<BaseRelationExtension> rel = new Relation<>();
//        rel.setSourceAnnotation(nota2);
//        rel.setTargetAnnotation(annotation);
//        BaseRelationExtension bre = new BaseRelationExtension();
//        bre.setField1("base type relation");
//        rel.setExtension(bre);
        nota2.setRelations(new ArrayList<Relation>());
//        nota2.getRelations().add(rel);

        session.save(nota2);
        session.getTransaction().commit();

    }
    /**/

    private void test5() throws InstantiationException, IllegalAccessException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test 5");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Annotation<TextContent, BaseAnnotationExtension> annotationTarget = session.load(Annotation.class, 1l);
        System.err.println("content " + annotationTarget.getContent().getText());
        Annotation.register(Tester.BASE, BaseAnnotationExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseAnnotationExtension> annotationSource = Annotation.newAnnotation(Tester.BASE, new BaseExtensionBuilder().field1("nota che annota il content di una nota"));

//        BaseExtension bex = annotationSource.getExtension();
//        bex.setField1("nota che annota il content di una nota");
        TextLocus locus = new TextLocus();

        locus.setSource(annotationTarget);
        locus.setAnnotation(annotationSource);
        locus.setStart(7);
        locus.setEnd(20);
        //        
        annotationSource.setLoci(new ArrayList<Locus>());
        annotationSource.getLoci().add(locus);

        session.save(annotationSource);
        session.getTransaction().commit();

    }

    private void test6() throws InstantiationException, IllegalAccessException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source<TextContent> source = Source.sourecOf(TextContent.class);
        source.setContent(new TextContent());
        source.getContent().setText(Tester.TEXTCONTENT);
        session.save(source);

        TextLocus locus = new TextLocus();
        locus.setSource(source);

        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(Tester.BASE, BaseAnnotationExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseAnnotationExtension> nota = Annotation.newAnnotation(Tester.BASE, new BaseExtensionBuilder().field1("con builder"));

        System.err.println(nota.toString());

        /* tolta la costruzione della nota*/
        //BaseExtension ex = nota.getExtension();
        //ex.setField1("nota");
        locus.setAnnotation(nota);

        nota.setLoci(new ArrayList<Locus>());
        nota.getLoci().add(locus);

        Annotation<TextContent, BaseAnnotationExtension> nota2 = Annotation.newAnnotation(Tester.BASE, new BaseExtensionBuilder().field1("con builder per nota 2"));


        
        Relation relation = new Relation();
        relation.setType(RelationTypes.PART_OF);
        relation.setSourceAnnotation(nota2);
        relation.setTargetAnnotation(nota);
        nota2.setRelations(new ArrayList<Relation>());
        nota2.getRelations().add(relation);
        
        session.save(nota);
        session.save(nota2);
        session.getTransaction().commit();

    }

}
