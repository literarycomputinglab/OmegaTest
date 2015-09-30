package it.cnr.ilc.lc.omegatest.solution3;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oakgen
 * @param <T>
 */
@RelationshipEntity
public class Relation<T extends Relation.Extension> extends SuperNode {

    private T extension;

    public T getExtension() {
        return extension;
    }

    public void setExtension(T extension) {
        this.extension = extension;
    }

    @StartNode
    private Annotation sourceAnnotation;

    @EndNode
    private Annotation targetAnnotation;

    public Annotation getTargetAnnotation() {
        return targetAnnotation;
    }

    public void setTargetAnnotation(Annotation targetAnnotation) {
        this.targetAnnotation = targetAnnotation;
    }

    public Annotation getSourceAnnotation() {
        return sourceAnnotation;
    }

    public void setSourceAnnotation(Annotation sourceAnnotation) {
        this.sourceAnnotation = sourceAnnotation;
    }

    public abstract static class Extension {

    }
}
