package com.pure.page.objects;

public abstract class BaseAbstractPage {

    public abstract boolean isOnPage();

    public abstract <T extends BaseAbstractPage> T focusOnFrame();
}
