package org.anime.web;

import org.anime.model.SavePoint;

public abstract class WebClient {
    public abstract PlayerController load(SavePoint savePoint);
}
