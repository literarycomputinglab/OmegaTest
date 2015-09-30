package it.cnr.ilc.lc.omegatest.solution3;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oakgen
 */
@RelationshipEntity
public class Relation extends SuperNode {

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

    private String type;

    public String getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type.name();
    }

}
