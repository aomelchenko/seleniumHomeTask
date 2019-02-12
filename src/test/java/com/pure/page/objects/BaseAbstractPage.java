package com.pure.page.objects;

import static com.pure.utils.PageHelper.switchToDefaultContent;

public abstract class BaseAbstractPage {

    public abstract boolean isOnPage();

    public abstract <T extends BaseAbstractPage> T focusOnFrame();

    public void switchToDefaultFrame() {
        switchToDefaultContent();
    }
}
