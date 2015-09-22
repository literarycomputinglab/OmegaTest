package it.cnr.ilc.lc.omegatest.solution1;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public abstract class Annotation extends SuperNode {

    private Annotation parent;
    private List<Annotation> children;
    private List<Locus> locus;
    private List<Annotation> references;

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

    public List<Annotation> getReferences() {
        return references;
    }

    public void setReferences(List<Annotation> references) {
        this.references = references;
    }

}
