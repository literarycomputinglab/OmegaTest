package it.cnr.ilc.lc.omegatest.solution2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author oakgen
 * @param <T>
 */
@NodeEntity
public final class Annotation<T extends Annotation.Extension> extends SuperNode {

    private T extension;

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

    public T getExtension() {
        return extension;
    }

    public void setExtension(T extension) {
        this.extension = extension;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
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
