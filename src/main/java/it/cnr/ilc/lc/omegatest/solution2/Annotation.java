package it.cnr.ilc.lc.omegatest.solution2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 * @param <T>
 */
@NodeEntity
public final class Annotation<T extends Annotation.Extension> extends SuperNode {

    private T extension;
    //private List<Locus> loci;
    
    private Locus locus;
    
    private List<Relationship> relationships;

    public Annotation() {
    }
    
    public Locus getLocus() {
        return locus;
    }

    public void setLocus(Locus locus) {
        this.locus = locus;
    }

    public T getExtension() {
        return extension;
    }

    public void setExtension(T extension) {
        this.extension = extension;
    }

//    public List<Locus> getLoci() {
//        return loci;
//    }

//    public void setLoci(List<Locus> loci) {
//        this.loci = loci;
//    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public static abstract class Extension extends SuperNode {

    }

    private static final Map<Class<? extends Annotation.Extension>, Annotation.Extension> LOOKUP_TABLE = new HashMap<>();

    public static void register(Class<? extends Annotation.Extension> clazz, Annotation.Extension implementation) {
        LOOKUP_TABLE.put(clazz, implementation);
    }

    public static <E extends Annotation.Extension> Annotation<E> newInstance(Class<E> clazz) {
        Annotation<E> annotation = new Annotation<>();
        E extension = (E) LOOKUP_TABLE.get(clazz);
        annotation.setExtension(extension);
        return annotation;
    }

}
