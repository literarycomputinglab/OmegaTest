package it.cnr.ilc.lc.omegatest.solution2;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public class Annotation extends SuperNode {

    private Annotation parent;
    private List<Annotation> children;
    private List<Locus> locus;
    private List<Relationship> relationships;

    public List<Annotation> getChildren() {
        return children;
    }

    public void setChildren(List<Annotation> children) {
        this.children = children;
    }

    public Annotation getParent() {
        return parent;
    }

    public void setParent(Annotation parent) {
        this.parent = parent;
    }

    public List<Locus> getLocus() {
        return locus;
    }

    public void setLocus(List<Locus> locus) {
        this.locus = locus;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

}
