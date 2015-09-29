package it.cnr.ilc.lc.omegatest.solution3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author oakgen
 * @param <T>
 */
@NodeEntity
public final class Annotation<T extends Content, E extends Annotation.Extension> extends Source<T> {

    private E extension;

    @Relationship(type = "TEXTLOCUS") //WARN FORZATO TEXTLOCUS: BUG APERTO SU NEO4J
    private List<Locus> loci;

    public List<Locus> getLoci() {
        return loci;
    }

    public void setLoci(List<Locus> loci) {
        this.loci = loci;
    }

    @Relationship(type = "RELATION")
    private List<Relation> relations;

    public Annotation() {
    }

    public E getExtension() {
        return extension;
    }

    public void setExtension(E extension) {
        this.extension = extension;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public static abstract class Extension extends SuperNode {
        public abstract <T extends Extension> T build(Builder<T> builder);
    }

    private static final Map<String, Class<? extends Annotation.Extension>> LOOKUP_TABLE = new HashMap<>();

    public static void register(String type, Class<? extends Annotation.Extension> clazz) {
        LOOKUP_TABLE.put(type,clazz);
    }

    
       public static <T extends Content, E extends Annotation.Extension> Annotation<T,E> newAnnotation(String type, Builder<E> builder) throws InstantiationException, IllegalAccessException {
           
        try {
            Annotation<T,E> annotation = new Annotation<>();
            Class<?> c = LOOKUP_TABLE.get(type);
            if (null == c)  {
                throw new NullPointerException("No implementation found for type " + type);
            }
            E extension = (E) c.newInstance();
            annotation.setExtension(extension.build(builder));
                   
            return annotation;
        } catch (InstantiationException | IllegalAccessException ex ) {
            Logger.getLogger(Annotation.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

       /*
    public static <E extends Annotation.Extension> Annotation<E> newInstance(Class<E> clazz) {
        Annotation<E> annotation = new Annotation<>();
        E extension = (E) LOOKUP_TABLE.get(clazz);
        annotation.setExtension(extension);
        return annotation;
    }
*/
}
