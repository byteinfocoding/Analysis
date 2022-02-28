package top.byteinfo.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "admin",method = RequestMethod.GET)
    public String test1(){
        System.out.println("ok++++++++ok");
        return "ok++++++ok";
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test2(){
        System.out.println("ok++++++++ok");
        return "ok++++++ok";
    }

}
