package org.vk.pages;

public interface LoadableComponent {
    boolean isLoaded() throws Error;
}

//LoadableComponent<T extends LoadableComponent<T>>
