package it.cnr.ilc.lc.omegatest.solution2;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oakgen
 * @param <T>
 */
@RelationshipEntity
public abstract class Locus<T extends Content> extends SuperNode {

    @StartNode
    private Annotation annotation;

    @EndNode
    private T content;

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
