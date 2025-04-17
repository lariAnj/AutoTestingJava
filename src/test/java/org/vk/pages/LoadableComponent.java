package org.vk.pages;

public abstract class LoadableComponent<T extends LoadableComponent<T>> {

    protected abstract void load();
    protected abstract void isLoaded() throws Error;

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            isLoaded();
            return (T) this;
        } catch (Error e) {
//            throw new RuntimeException("Failed to load component: " + e.getMessage(), e);
            System.err.println(("Error encountered during page load: " + e.getMessage()));
            load();
        }
        isLoaded();
        return (T) this;
    }

}