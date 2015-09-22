package it.cnr.ilc.lc.omegatest.solution1;

import it.cnr.ilc.lc.omegatest.Neo4jSessionFactory;
import it.cnr.ilc.lc.omegatest.solution1.test.A;
import it.cnr.ilc.lc.omegatest.solution1.test.B;
import it.cnr.ilc.lc.omegatest.solution1.test.CR;
import java.util.ArrayList;
import java.util.Collection;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class Tester {

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.test3();
    }

    private void test1() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        TextContent text = new TextContent();
        text.setText("Ciao mamma, guarda come mi diverto!");

        session.save(text);

        session.getTransaction().commit();
    }

    private void test2b() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        TextContent content = session.load(TextContent.class, 0l, 0);

        TextLocus locus = new TextLocus();
        locus.setContent(content);
        locus.setStart(5);
        locus.setEnd(10);

        Note2 note = new Note2();
        note.setName("Note2");
        note.setField1("Ciao");
        note.setField2("Bello");

        Annotation annotation = new Annotation();
        annotation.setType(note);
        ArrayList<Locus> locuses = new ArrayList<>();
        locuses.add(locus);
        annotation.setLocus(locuses);

        locus.setAnnotation(annotation);

        session.save(annotation);

        session.getTransaction().commit();
    }

    private void test2() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        TextContent content = session.load(TextContent.class, 0l);

        TextLocus locus = new TextLocus();
        locus.setContent(content);
        locus.setStart(5);
        locus.setEnd(10);

        Note note = new Note();
        note.setLocus(new ArrayList<Locus>());
        note.getLocus().add(locus);
        note.setField1("Ciao");
        note.setField2("Bello");

        locus.setAnnotation(note);

        session.save(note);

        session.getTransaction().commit();
    }

    private void test4() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        A a = new A();
        B b = new B();

        CR r = new CR();
        r.setA(a);
        r.setB(b);

        a.setR(r);

        session.save(a);

        session.getTransaction().commit();
    }

    private void test5() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Collection<A> as = session.loadAll(A.class);

        for (A a : as) {
            System.out.println(a.getR());
        }

        session.getTransaction().commit();
    }

    private void test3() {
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Note note = session.load(Note.class, 1l);
        
        System.out.println(note.getLocus().get(0));

        session.getTransaction().commit();
    }

}
