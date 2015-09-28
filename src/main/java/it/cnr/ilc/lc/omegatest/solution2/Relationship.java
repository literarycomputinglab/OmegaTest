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
public class Relationship {

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
       
    public static enum relationType{
        BASE, PART, ALL
    }

}
