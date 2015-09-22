package it.cnr.ilc.lc.omegatest.solution1;

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 *
 * @author oakgen
 */
@RelationshipEntity
public class TextLocus extends Locus<TextContent> {

    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
