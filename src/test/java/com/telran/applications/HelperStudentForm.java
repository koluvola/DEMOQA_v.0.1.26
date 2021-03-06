package com.telran.applications;

import com.telran.models.StudentRegForm;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperStudentForm extends HelperBase {
    public HelperStudentForm(WebDriver wd) {
        super(wd);
    }

    public void selectItemForms() {
        clickByxPath("//div[@class='category-cards']/div[2]");
    }

    public void selectPracticeForm() {
        clickByxPath("//span[.='Practice Form']");
    }

    public void submit() {
        click(By.id("submit"));
    }

    public void fillRegStudetForm(StudentRegForm model) throws InterruptedException {
        type(By.id("firstName"), model.getfName());
        type(By.id("lastName"), model.getlName());
        type(By.id("userEmail"), model.getEmail());
        selectGender(model.getGender());
        pause(500);
        type(By.id("userNumber"), model.getPhone());
        //typeBDay(model.getbDay());
        typeBDaySelet(model.getbDay());
        typeAdress(model.getAddress());
        typeState(model.getState());
        typeCity(model.getCity());
    }

    private void typeAdress(String text) {
        WebElement el = wd.findElement(By.id("currentAddress"));
        //Actions actions= new Actions(wd);
        // actions.moveToElement(el);

        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(0,300)");
        el.click();
        el.clear();
        el.sendKeys(text);

    }


    private void typeBDaySelet(String text) {
        String[] data = text.split(" ");


        wd.findElement(By.id("dateOfBirthInput")).click();
        new Select(wd.findElement(By.cssSelector(".react-datepicker__month-select"))).selectByVisibleText(data[1]);
        new Select(wd.findElement(By.cssSelector(".react-datepicker__year-select"))).selectByVisibleText(data[2]);

        //click(By.xpath(String.format("//div[.='%s']", data[0])));
        List<WebElement> list = wd.findElements(By.xpath(String.format("//div[.='%s']", data[0])));
        WebElement el;
        int day = Integer.parseInt(data[0]);

        if (list.size() > 1 && day > 15) {
            el = list.get(1);
        }else {
            el = list.get(0);
        }
        el.click();
    }

    private void typeBDay(String bday) {
        WebElement element = wd.findElement(By.id("dateOfBirthInput"));
        element.click();
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));

        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }
        element.sendKeys(bday);
        element.sendKeys(Keys.ENTER);


        //type(By.id("dateOfBirthInput"),bday);
        //wd.findElement(By.id("dateOfBirthInput")).sendKeys(Keys.ENTER);

    }

    private void typeState(String state) {
        typeWithJSE(By.id("react-select-3-input"), state);
        wd.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
    }

    private void typeCity(String city) {
        typeWithJSE(By.id("react-select-4-input"), city);
        wd.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
    }
    private void selectHobbies(String hobbie) {
        if (hobbie.equals("Sports")) {
            clickByCss("label[for='hobbies-checkbox-1']");
        } else if (hobbie.equals("Reading")) {
            clickByCss("label[for='hobbies-checkbox-2']");
        } else clickByCss("label[for='hobbies-checkbox-3']");


        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.getElementById('hobbies-checkbox-1').checked=false;");


    }



    private void selectGender(String gender) {

        if (gender.equals("Male")) {
            clickByxPath("//label[@for='gender-radio-1']");
        } else if (gender.equals("Female")) {
            clickByxPath("//label[@for='gender-radio-2']");
        } else {
            clickByxPath("//label[@for='gender-radio-3']");
        }

    }
}
