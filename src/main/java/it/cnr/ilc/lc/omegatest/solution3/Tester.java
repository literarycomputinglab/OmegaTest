package it.cnr.ilc.lc.omegatest.solution3;

import it.cnr.ilc.lc.omegatest.Neo4jSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class Tester {

    public final static String TEXTCONTENT = "La mi' fava sa di menta chi l'assaggia 'un se ne penta";
    public final static String BASE = "BaseExtension";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Tester tester = new Tester();

        /* crea il nodo content*/
        //tester.test1();
        /* crea il nodo annotazione */
        //  tester.test2();
        //tester.test2_1();
        /* crea un nuovo locus */
        // tester.test3();

        /* crea annotazione di annotazioni */
        //tester.test4();

        /* crea annotazione di content di annotazione */
        tester.test5();
    }

    private void test1() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source<TextContent> source = new Source();
        source.setContent(new TextContent());
        source.getContent().setText(Tester.TEXTCONTENT);
        session.save(source);

        session.getTransaction().commit();
    }

    /**/
    private void test2() throws InstantiationException, IllegalAccessException {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Source source = session.load(Source.class, 30l);

        TextLocus locus = new TextLocus();
        locus.setSource(source);
        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(Tester.BASE, BaseExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseExtension> nota = Annotation.newAnnotation(Tester.BASE);

        System.err.println(nota.toString());

        BaseExtension ex = nota.getExtension();
        ex.setField1("nota");
        //        List<Locus> loci = new ArrayList<Locus>();
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

        Annotation annotation = session.load(Annotation.class, 32l);
        TextContent content = new TextContent();

        content.setText("Bobbemalle");
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

        Annotation annotation = session.load(Annotation.class, 32l, 5);

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
        Annotation annotation = session.load(Annotation.class, 32l);

        Annotation.register(Tester.BASE, BaseExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseExtension> nota2 = Annotation.newAnnotation(Tester.BASE);

        BaseExtension bex = nota2.getExtension();

        bex.setField1("nota2 che annota nota");

        Relation rel = new Relation();

        rel.setSourceAnnotation(nota2);
        rel.setTargetAnnotation(annotation);
        rel.setRelType(Relation.RelationType.BASE);

        nota2.setRelations(new ArrayList<Relation>());
        nota2.getRelations().add(rel);

        session.save(nota2);
        session.getTransaction().commit();

    }
    /**/

    private void test5() throws InstantiationException, IllegalAccessException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test 5");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Annotation<TextContent, BaseExtension> annotationTarget = session.load(Annotation.class, 32l);
        System.err.println("content " + annotationTarget.getContent().getText());
        Annotation.register(Tester.BASE, BaseExtension.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseExtension> annotationSource = Annotation.newAnnotation(Tester.BASE);

        BaseExtension bex = annotationSource.getExtension();

        bex.setField1("nota che annota il content di una nota");

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

}
