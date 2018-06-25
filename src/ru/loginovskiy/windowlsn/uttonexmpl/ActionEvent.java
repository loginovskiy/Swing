package ru.loginovskiy.windowlsn.uttonexmpl;

public class ActionEvent
{
    String sourceName;
    String action;
    ActionEvent(Button b, String action)
    {
        sourceName=b.getSource();
        this.action=action;
    }
}
