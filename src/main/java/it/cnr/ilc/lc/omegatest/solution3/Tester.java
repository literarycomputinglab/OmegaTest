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

    public static void main(String[] args) {
        Tester tester = new Tester();

        /* crea il nodo content*/
        //tester.test1();
        /* crea il nodo annotazione */
        //tester.test2();
        /* crea un nuovo locus */
        //tester.test3();

        /* crea annotazione di annotazioni */
        // tester.test4();
        tester.test5();
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

        TextContent content = session.load(TextContent.class, 18l);

        TextLocus locus = new TextLocus();
        locus.setContent(content);
        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register(BaseExtension.class, new BaseExtension()); //MODIFICARE in (TIPO, CLASSE)
        Annotation<BaseExtension> nota = Annotation.newInstance(BaseExtension.class);

        System.err.println(nota.toString());

        Annotation.Extension ex = nota.getExtension();
        BaseExtension bex = (BaseExtension) ex;
        bex.setField1("nota");
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

    private void test3() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test tre");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
//
//        TextContent content = session.load(TextContent.class, 1l);
        Annotation annotation = session.load(Annotation.class, 19l);
//        TextLocus l = session.load(TextLocus.class, 7l);
//        
        System.err.println(annotation.toString());

        Annotation.Extension ex = annotation.getExtension();

        System.err.println(ex.toString()); // questa funziona!

        //   Locus l = ((TextLocus) annotation.getLoci().get(0));
        //  System.err.println(l.toString());
        TextLocus locus = new TextLocus();
        List<Locus> loci = annotation.getLoci();
        Locus l = loci.get(0);
        TextContent content = (TextContent) l.getContent();
        locus.setContent(content);
        locus.setAnnotation(annotation);
        locus.setStart(6);
        locus.setEnd(12);
//        
        annotation.getLoci().add(locus);
        session.save(annotation);
        session.getTransaction().commit();

//        session.getTransaction().close();
    }

    private void test4() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test 4");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
//
        Annotation annotation = session.load(Annotation.class, 19l);

        Annotation.register(BaseExtension.class, new BaseExtension()); //MODIFICARE in (TIPO, CLASSE)
        Annotation<BaseExtension> nota2 = Annotation.newInstance(BaseExtension.class);

        Annotation.Extension ex = nota2.getExtension();
        BaseExtension bex = (BaseExtension) ex;

        bex.setField1("nota2");

        Relation rel = new Relation();

        rel.setSourceAnnotation(nota2);
        rel.setTargetAnnotation(annotation);
        rel.setRelType(Relation.RelationType.BASE);
        nota2.setRelations(new ArrayList<Relation>());
        nota2.getRelations().add(rel);
        session.save(nota2);
        session.getTransaction().commit();

    }

    private void test5() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.err.println("in test 5");
        Session session = Neo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();

        Annotation annotation = session.load(Annotation.class, 19l);

        Annotation.register(ContentExtension.class, new ContentExtension()); //MODIFICARE in (TIPO, CLASSE)
        Annotation.register(BaseExtension.class, new BaseExtension()); //MODIFICARE in (TIPO, CLASSE)
        Annotation<ContentExtension> contentNote = Annotation.newInstance(ContentExtension.class);

        Annotation.Extension ex = contentNote.getExtension();
        ContentExtension cex = (ContentExtension) ex;

        TextContent text = new TextContent();
        text.setText("Testo della nota che annota l'annotazione");
        cex.setContent(text);

        contentNote.setExtension(cex);
        contentNote.setRelations(new ArrayList<Relation>());

        Relation rel = new Relation();

        rel.setRelType(Relation.RelationType.BASE);
        rel.setSourceAnnotation(contentNote);
        rel.setTargetAnnotation(annotation);
        contentNote.getRelations().add(rel);

        Annotation<BaseExtension> baseNote = Annotation.newInstance(BaseExtension.class);
        baseNote.getExtension().setField1("Annotazione al content di una annotazione");

        TextLocus locus = new TextLocus();

        locus.setContent((TextContent) contentNote.getExtension().getContent());
        locus.setAnnotation(baseNote);
        locus.setStart(7);
        locus.setEnd(20);
//        
        baseNote.setLoci(new ArrayList<Locus>());
        baseNote.getLoci().add(locus);
        baseNote.setRelations(new ArrayList<Relation>());
        Relation rel2 = new Relation();

        rel2.setRelType(Relation.RelationType.BASE);
        rel2.setSourceAnnotation(baseNote);
        rel2.setTargetAnnotation(contentNote);

        baseNote.getRelations().add(rel2);
        session.save(baseNote);
        session.getTransaction().commit();

    }

}
