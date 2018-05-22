package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 某一类型的商品
 */
@Controller
public class Wbpontroller {
    @RequestMapping(value="/wbp/{id}",method=RequestMethod.GET)
    public String index(Model model, @PathVariable Integer id){
        System.out.println(id);
        return null;
    }
}
