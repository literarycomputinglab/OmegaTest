package it.cnr.ilc.lc.omegatest.solution2;

/**
 *
 * @author oakgen
 * @param <T>
 */
public class Relationship<T extends Annotation> {

    private T annotation;

    public T getAnnotation() {
        return annotation;
    }

    public void setAnnotation(T annotation) {
        this.annotation = annotation;
    }

}
