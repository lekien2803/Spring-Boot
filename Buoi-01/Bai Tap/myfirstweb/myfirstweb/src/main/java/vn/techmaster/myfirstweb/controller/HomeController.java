package vn.techmaster.myfirstweb.controller;

import java.util.Calendar;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.myfirstweb.model.Book;
import vn.techmaster.myfirstweb.model.Message;

@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping(value = "/hi", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public String hello() {
    return "<h1>Hello World</h1><h3>Cool</h3>";
  }

  @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Book getBook() {
    return new Book("Dế Mèn Phiêu Luu Ky", "Tô Hoài", "1945");
  }

  @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Book book_xml() {
    return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
  }

  @GetMapping("/add/{a}/{b}")
  @ResponseBody
  public int add(@PathVariable("a") int aa, @PathVariable("b") int bb) {
    return aa + bb;
  }

  @GetMapping("/name/{your_name}")
  @ResponseBody
  public String hi(@PathVariable("your_name") String yourName) {
    return "Hi " + yourName;
  }

  @GetMapping("/year/{year}")
  @ResponseBody
  public int getAge(@PathVariable("year") int year) {
    return Calendar.getInstance().get(Calendar.YEAR) - year;
  }

  @GetMapping("/random/{length}")
  @ResponseBody
  public String randomString(@PathVariable("length") int n) {

    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {

      int index = (int) (AlphaNumericString.length()
          * Math.random());

      sb.append(AlphaNumericString
          .charAt(index));
    }

    return sb.toString();
  }

  @GetMapping("/add")
  @ResponseBody
  public int add2(@RequestParam("a") int a, @RequestParam("b") int b) {
    return a + b;
  }

  @PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Message echoMessage(@RequestBody Message message) {
    return message;
  }

  @PostMapping("/hi")
  @ResponseBody
  public Message sayHi(@RequestBody Message message) {
    return message;
  }

}
