package ru.loginovskiy.windowlsn.uttonexmpl;

public class Button
{
    public final String buttonName;
    public final String action = "Click";
    Button(String name)
    {
        buttonName=name;
    }
    public void click()
    {
        new ActionListener(new ActionEvent(this, action));
    }
    public String getSource()
    {
        return buttonName;
    }
}
