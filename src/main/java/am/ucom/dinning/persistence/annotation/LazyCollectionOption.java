package am.ucom.dinning.persistence.annotation;

public enum LazyCollectionOption {

    /** eager loading */
    FALSE,

    /** load it when the state is requested */
    TRUE
}
