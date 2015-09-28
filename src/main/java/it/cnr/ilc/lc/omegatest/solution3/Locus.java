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
public abstract class Locus<T extends Content> extends SuperNode {
    
    @StartNode
    private Annotation annotation;

    @EndNode
    private Source<T> source;

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Source<T> getSource() {
        return source;
    }

    public void setSource(Source<T> source) {
        this.source = source;
    }

}
