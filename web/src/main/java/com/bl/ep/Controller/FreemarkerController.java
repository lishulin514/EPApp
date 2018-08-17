package com.bl.ep.Controller;

import com.bl.ep.domain.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import freemarker.ext.servlet.HttpSessionHashModel;

@Controller
@RequestMapping("ftl")
public class FreemarkerController {

    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(Model map){

        map.addAttribute("resource", resource);
        return "freemarker/index";
    }

    @RequestMapping("/center")
    public String center(){

        return "freemarker/center/center";
    }
}
