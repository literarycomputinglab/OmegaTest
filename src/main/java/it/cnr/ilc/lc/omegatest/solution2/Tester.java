package it.cnr.ilc.lc.omegatest.solution2;

import it.cnr.ilc.lc.omegatest.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class Tester {

    public static void main(String[] args) {
        Tester tester = new Tester();
        
        /* crea il nodo content*/
        tester.test1();
        
        /* crea il nodo annotazione */
        //tester.test2();
    }

    private void test1() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        TextContent text = new TextContent();
        text.setText("Ciao mamma, guarda come mi diverto!");

        session.save(text);

        session.getTransaction().commit();
    }

    private void test2() {
//        Session session = Neo4jSessionFactory.getNeo4jSession();
//        session.beginTransaction();
//
//        TextContent content = session.load(TextContent.class, 17l);
//
//        TextLocus locus = new TextLocus();
//        locus.setContent(content);
//        locus.setStart(5);
//        locus.setEnd(10);
//
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

}
