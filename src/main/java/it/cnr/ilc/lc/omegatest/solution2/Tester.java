package it.cnr.ilc.lc.omegatest.solution2;

import it.cnr.ilc.lc.omegatest.Neo4jSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class Tester {

    public static void main(String[] args) {
        Tester tester = new Tester();
        
        /* crea il nodo content*/
        //tester.test1();
        
        /* crea il nodo annotazione */
        tester.test2();
        
        /* crea un nuovo locus */
        //tester.test3();
        
        /* crea annotazione di annotazioni */
        //tester.test4();
        
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
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        TextContent content = session.load(TextContent.class,14l);

        TextLocus locus = new TextLocus();
        locus.setContent(content);
        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(BaseExtension.class, new BaseExtension());
        Annotation<BaseExtension> nota = Annotation.newInstance(BaseExtension.class);
        
        System.err.println(nota.toString());
        
        Annotation.Extension ex = nota.getExtension();
        BaseExtension bex = (BaseExtension)ex;
        bex.setField1("nota");
//        List<Locus> loci = new ArrayList<Locus>();
//        locus.setAnnotation(nota);
//        loci.add(locus);
        locus.setAnnotation(nota);
        nota.setLocus(locus);
        
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

    private void test3() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        System.err.println("in test tre");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
//
//        TextContent content = session.load(TextContent.class, 1l);
        Annotation annotation = session.load(Annotation.class, 12l);
//        TextLocus l = session.load(TextLocus.class, 7l);
//        
        System.err.println(annotation.toString());
        
        Annotation.Extension ex = annotation.getExtension();
        
        System.err.println(ex.toString()); // questa funziona!
        
//        Locus l = annotation.getLuogo();
//        
//        System.err.println(l.toString());
//
//        TextLocus locus = new TextLocus();
////        List<Locus> loci = annotation.getLoci();
////        Locus l = loci.get(0);
////        TextContent content = (TextContent)l.getContent();
//        locus.setContent(content);
//        
//        locus.setStart(6);
//        locus.setEnd(12);
//        
//        annotation.getLoci().add(locus);
//        session.save(annotation);
        session.getTransaction().commit();
        
    }

    private void test4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
