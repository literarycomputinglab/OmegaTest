package it.cnr.ilc.lc.omega.test;

import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationBuilder;
import it.cnr.ilc.lc.omega.core.annotation.BaseAnnotationType;
import it.cnr.ilc.lc.omega.clavius.catalog.FileBuilder;
import it.cnr.ilc.lc.omega.clavius.catalog.entity.Folder;
import it.cnr.ilc.lc.omega.clavius.catalog.entity.FileAnnotationExtension;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.ImageContent;
import it.cnr.ilc.lc.omega.entity.ImageLocus;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Relation;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.TextLocus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author oakgen
 */
public class TesterOLD {
    
    private static final Long SOURCE_ID = 7l;
    private static final Long ANNOTATION_ID = 9l;
    
    public static final String CONTENT_PAGE1_LETTER1 = "Botvitus Nericius Christophoro Clavio Salutem "
            + "Occupationibus tuis Doctissime Clavi parcens, in tertia mea ad te epistola, praesertim vero in secunda illius parte, quae erat de puncto lineae Dinostrati ultimo abs te invento; brevior fortasse fui, quam oportuit, ideoque hac scriptione rem totam paullo uberius, si potero, explicabo. "
            + "Principio igitur lineae conchoidis accidens certissimum est, nimirum, si per lineam Dinostrati transeat, eam per lineas rectas dividere in partes supra infraque aequales, propter motum lineae a summo, velut polo, basim permeantis; qui quidem motus familiam in hoc negotio, vel in primis ducit. "
            + "Certum quoque est punctum, quod aequidistantiae dicitur nempe g esse in linea conchoide, utpote ipsam describens. "
            + "Esse autem illud idem punctum g in linea Dinostrati, ex sola positione, sine ulla ratione geometrica discimus. "
            + "age enim punctum illud, estne certa proportione descriptum, an sit extempore notatum, ut fieri solet, lineae complendae caussa? ";
    
    public static final String CONTENT_PAGE2_LETTER1 = "Si huius generis est, iam constat esse mechanicum, et a nostro instituto alienum; sin illius, cur non demonstratur? "
            + "linea etenim Dinostrati, nequaquam, ne secundum nota quidem puncta geometrice sine demonstratione, describi potest, nedum secundum ignota, cuiusmodi est punctum e hoc est, punctum g in tot aetatum controversia positum. "
            + "Nam quod ad tres quadrantes attinet, concedo omnes esse unum et eundem, sed nego ullum esse quadrantem medium, nisi prius ostendatur punctum g esse in linea iam dicta. "
            + "ex quo sequitur nondum penitus adsolutum esse libellum de inventione puncti ultimi quod tum scripsi; quamvis a perfectione non longe absit verum ego de securitate geometrica loquor. ";
    
    public static final String CONTENT_PAGE1_LETTER2 = "Quia si, ut ratio naturalis docet, propter quod unum quodque tale fuerit illud magis est tale; et punctum g ut iam docui, est incertum, multo incertius erit punctum cum huius veritas ex illius veritate dependeat. "
            + "Quamobrem tuarum partium est, optime magister, quoniam praeclaram hanc segetem tanta dexteritate sevisti, et rigasti, tandem ad maturitatem perductam, humano genere invisendam communices. "
            + "bene vale; et nobis, tum de his tum etiam de Ubaldi novitate, quod commodo tuo fiat, quamprimum responde. "
            + "Mantua Carpentaniae pridie idus Septembris. M. D. LXXXXIIX. ";
    public static final String CONTENT_PAGE2_LETTER2 = "Tuus, more solito, Botvitus Nericius de Sala, gothus manu propria "
            + "religiosissimo et doctissimo viro, Christophoro Clavio Societatis Iesu manu propria Romam manu propria ";
    
    public static final String TEXT_LETTER = CONTENT_PAGE1_LETTER1 + CONTENT_PAGE2_LETTER1 + CONTENT_PAGE1_LETTER2 + CONTENT_PAGE2_LETTER2;
    
    private static final String RESOURCEPATH = "/resource/";
    public static final String BASE = "BaseExtension";
    public static final String NOTA = "il contenuto di una nota";
    public static final String TESTO = "un contenuto generico";
    
    public static final int BUFFER_MAX_SIZE = 1024 * 1024 * 4;
    
    public static void main(String[] args) {
        TesterOLD tester = new TesterOLD();

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
//        tester.test6();
        // tester.test7();
        tester.test8();
        
    }
    
    private void test6() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = DeprecatedNeo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
        
        Source<TextContent> source = Source.sourceOf(TextContent.class);
        source.setContent(Content.contentOf(TextContent.class));
        source.getContent().setText(TesterOLD.CONTENT_PAGE1_LETTER1);
        session.save(source);
        
        TextLocus locus = Locus.locusOf(TextLocus.class, Locus.PointsTo.CONTENT);
        // TextLocus locus = new TextLocus();
        locus.setSource(source);
        
        locus.setStart(5);
        locus.setEnd(10);
        
        Annotation.register(TesterOLD.BASE, BaseAnnotationType.class); //MODIFICARE in (TIPO, CLASSE)
        Annotation<TextContent, BaseAnnotationType> nota = 
                Annotation.newAnnotation(TesterOLD.BASE, new BaseAnnotationBuilder().text("con builder"));
        
        System.err.println(nota.toString());

        /* tolta la costruzione della nota*/
        //BaseExtension ex = nota.getExtension();
        //ex.setField1("nota");
        locus.setAnnotation(nota);
        
        nota.addLocus(locus);
        
        Annotation<TextContent, BaseAnnotationType> nota2 = Annotation.newAnnotation(TesterOLD.BASE,
                new BaseAnnotationBuilder()
                .text("con builder per nota 2")
        );
        
        Relation relation = Relation.newInstance(RelationTypes.PART_OF);
        relation.setSourceAnnotation(nota2);
        relation.setTargetAnnotation(nota);
        nota2.addRelation(relation);
        
        session.save(nota);
        session.save(nota2);
        session.getTransaction().commit();
        
    }
    
    private void test7() {
        Session session = DeprecatedNeo4jSessionFactory.getNeo4jSession();
        session.beginTransaction();
        
        Annotation.register("File", FileAnnotationExtension.class); //MODIFICARE in (TIPO, CLASSE)

        Source<TextContent> sourcePage1Letter1 = Source.sourceOf(TextContent.class);
        sourcePage1Letter1.setContent(Content.contentOf(TextContent.class));
        sourcePage1Letter1.getContent().setText(TesterOLD.CONTENT_PAGE1_LETTER1);
        TextLocus locus = Locus.locusOf(TextLocus.class, Locus.PointsTo.SOURCE);
        locus.setSource(sourcePage1Letter1);
        Annotation<TextContent, FileAnnotationExtension> page1Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source 1"));
        locus.setAnnotation(page1Letter1); // ATTENZIONE

      
        page1Letter1.addLocus(locus);
        
        session.save(page1Letter1);
        
        Source<TextContent> sourcePage2Letter1 = Source.sourceOf(TextContent.class);
        sourcePage2Letter1.setContent(Content.contentOf(TextContent.class));
        sourcePage2Letter1.getContent().setText(TesterOLD.CONTENT_PAGE2_LETTER1);
        locus = Locus.locusOf(TextLocus.class, Locus.PointsTo.SOURCE);
        locus.setSource(sourcePage2Letter1);
        Annotation<TextContent, FileAnnotationExtension> page2Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source 2"));
        locus.setAnnotation(page2Letter1);
        page2Letter1.addLocus(locus);
        
        session.save(page2Letter1);
        
        Source<TextContent> sourcePage1Letter2 = Source.sourceOf(TextContent.class);
        sourcePage1Letter2.setContent(Content.contentOf(TextContent.class));
        sourcePage1Letter2.getContent().setText(TesterOLD.CONTENT_PAGE1_LETTER2);
        locus = Locus.locusOf(TextLocus.class, Locus.PointsTo.SOURCE);
        locus.setSource(sourcePage1Letter2);
        Annotation<TextContent, FileAnnotationExtension> page1Letter2 = Annotation.newAnnotation("File", new FileBuilder().name("Source 3"));
        locus.setAnnotation(page1Letter2);
        
        page1Letter2.addLocus(locus);
        session.save(page1Letter2);
        
        Source<TextContent> sourcePage2Letter2 = Source.sourceOf(TextContent.class);
        sourcePage2Letter2.setContent(Content.contentOf(TextContent.class));
        sourcePage2Letter2.getContent().setText(TesterOLD.CONTENT_PAGE2_LETTER2);
        locus = Locus.locusOf(TextLocus.class, Locus.PointsTo.SOURCE);
        locus.setSource(sourcePage2Letter2);
        Annotation<TextContent, FileAnnotationExtension> page2Letter2 = Annotation.newAnnotation("File", new FileBuilder().name("Source 4"));
        locus.setAnnotation(page2Letter2);
        
        page2Letter2.addLocus(locus);
        
        session.save(page2Letter2);
        
        Folder archive = new Folder();
        archive.setName("Archive");
        
        Folder fondo = new Folder();
        fondo.setName("Fondo");
        archive.addFolder(fondo);
        
        Folder manuscript530 = new Folder();
        manuscript530.setName("Manuscript 530");
        fondo.addFolder(manuscript530);
        manuscript530.addFile(page1Letter1);
        manuscript530.addFile(page2Letter1);
        manuscript530.addFile(page1Letter2);
        manuscript530.addFile(page2Letter2);
        
        Folder manuscript533 = new Folder();
        manuscript533.setName("Manuscript 533");
        fondo.addFolder(manuscript533);
        
        session.save(archive);
        
        session.getTransaction().commit();
    }
    
    private void test8() {
        byte[] imageBuffer = new byte[BUFFER_MAX_SIZE];
        Byte[] imageByteArray = null;
        BufferedInputStream buffer = null;
        
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            InputStream ISImage129r = this.getClass().getClassLoader().getResourceAsStream("img/129r_mini.jpg");
            InputStream ISImage129v = this.getClass().getClassLoader().getResourceAsStream("img/129v_mini.jpg");
            InputStream ISImage130r = this.getClass().getClassLoader().getResourceAsStream("img/130r_mini.jpg");
            InputStream ISImage130v = this.getClass().getClassLoader().getResourceAsStream("img/130v_mini.jpg");
            
            Session session = DeprecatedNeo4jSessionFactory.getNeo4jSession();
            session.beginTransaction();
            Annotation.register("File", FileAnnotationExtension.class);

            // Associating Byte array values with bytes. (byte[] to Byte[])
            imageByteArray = convertBytes(imageBuffer, buffer, ISImage129r); // controllare
            ISImage129r.close();
            
            Source<ImageContent> sourcePage1 = Source.sourceOf(ImageContent.class);
            sourcePage1.setContent(Content.contentOf(ImageContent.class));
            sourcePage1.getContent().setImage(imageByteArray);
            sourcePage1.getContent().setMimetype("image/jpeg");
            ImageLocus locus = Locus.locusOf(ImageLocus.class, Locus.PointsTo.SOURCE);
            locus.setSource(sourcePage1);
            Annotation<ImageContent, FileAnnotationExtension> page1Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source1"));
            locus.setAnnotation(page1Letter1); // ATTENZIONE I DUE nodi della relazione devono essere settati insieme!
            page1Letter1.addLocus(locus);
            session.save(page1Letter1);
            
            imageByteArray = null;
            imageByteArray = convertBytes(imageBuffer, buffer, ISImage129v); // controllare
            ISImage129v.close();
            
            Source<ImageContent> sourcePage2 = Source.sourceOf(ImageContent.class);
            sourcePage2.setContent(Content.contentOf(ImageContent.class));
            sourcePage2.getContent().setImage(imageByteArray);
            sourcePage2.getContent().setMimetype("image/jpeg");
            ImageLocus locus2 = Locus.locusOf(ImageLocus.class, Locus.PointsTo.SOURCE);
            locus2.setSource(sourcePage2);
            Annotation<ImageContent, FileAnnotationExtension> page2Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source2"));
            locus2.setAnnotation(page2Letter1); // ATTENZIONE I DUE nodi della relazione devono essere settati insieme!
            page2Letter1.addLocus(locus2);
            session.save(page2Letter1);
            
            imageByteArray = null;
            imageByteArray = convertBytes(imageBuffer, buffer, ISImage130r); // controllare
            ISImage130r.close();
            
            Source<ImageContent> sourcePage3 = Source.sourceOf(ImageContent.class);
            sourcePage3.setContent(Content.contentOf(ImageContent.class));
            sourcePage3.getContent().setImage(imageByteArray);
            sourcePage3.getContent().setMimetype("image/jpeg");
            ImageLocus locus3 = Locus.locusOf(ImageLocus.class, Locus.PointsTo.SOURCE);
            locus3.setSource(sourcePage3);
            Annotation<ImageContent, FileAnnotationExtension> page3Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source3"));
            locus3.setAnnotation(page3Letter1); // ATTENZIONE I DUE nodi della relazione devono essere settati insieme!
            page3Letter1.addLocus(locus3);
            session.save(page3Letter1);
            
            imageByteArray = null;
            imageByteArray = convertBytes(imageBuffer, buffer, ISImage130v); // controllare
            ISImage130v.close();
            
            Source<ImageContent> sourcePage4 = Source.sourceOf(ImageContent.class);
            sourcePage4.setContent(Content.contentOf(ImageContent.class));
            sourcePage4.getContent().setImage(imageByteArray);
            sourcePage4.getContent().setMimetype("image/jpeg");
            ImageLocus locus4 = Locus.locusOf(ImageLocus.class, Locus.PointsTo.SOURCE);
            locus4.setSource(sourcePage4);
            Annotation<ImageContent, FileAnnotationExtension> page4Letter1 = Annotation.newAnnotation("File", new FileBuilder().name("Source4"));
            locus4.setAnnotation(page4Letter1); // ATTENZIONE I DUE nodi della relazione devono essere settati insieme!
            page4Letter1.addLocus(locus4);
            session.save(page4Letter1);
            
            Source<TextContent> sourceTextLetter = Source.sourceOf(TextContent.class);
            sourceTextLetter.setContent(Content.contentOf(TextContent.class));
            sourceTextLetter.getContent().setText(TesterOLD.TEXT_LETTER);
           
            TextLocus textlocus = Locus.locusOf(TextLocus.class, Locus.PointsTo.SOURCE);
            textlocus.setSource(sourceTextLetter);
            Annotation<TextContent, FileAnnotationExtension> TextLetter1 = Annotation.newAnnotation("File", new FileBuilder().name("TextualSource"));
            textlocus.setAnnotation(TextLetter1); // ATTENZIONE prima di fare addLocus deve essere fatta la setAnnotation sul locus
            TextLetter1.addLocus(textlocus);
            
            session.save(TextLetter1);
            
            Folder letter = new Folder();
            letter.setName("MaterialForletter");
            Folder archivio = new Folder();
            archivio.setName("archivio");
            archivio.addFolder(letter);
            
            letter.addFile(page1Letter1);
            letter.addFile(page2Letter1);
            letter.addFile(page3Letter1);
            letter.addFile(page4Letter1);
            letter.addFile(TextLetter1);
            session.save(archivio);
            
            session.getTransaction().commit();
        } catch (IOException ex) {
            Logger.getLogger(TesterOLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private Byte[] convertBytes(byte[] imageBuffer, BufferedInputStream buffer, InputStream image) throws IOException {
        buffer = new BufferedInputStream(image);
        Byte[] imageByteArray = new Byte[buffer.read(imageBuffer)];
        for (int i = 0; i < imageByteArray.length; i++) {
            imageByteArray[i] = imageBuffer[i];
        }
        buffer.close(); // WORN gestire l'ECCEZIONE bene!
        buffer = null;
        return imageByteArray;
    }
    
}
